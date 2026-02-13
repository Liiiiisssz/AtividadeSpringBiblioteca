package br.com.centroweg.biblioteca.repository;

import br.com.centroweg.biblioteca.model.Emprestimo;
import br.com.centroweg.biblioteca.util.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmprestimoRepository {
    public Emprestimo save(Emprestimo emprestimo) throws SQLException{
        String command = """
                INSERT INTO emprestimo
                (livro_id, usuario_id, data_emprestimo, data_devolucao)
                VALUES (?,?,?,?)
                """;
        try(Connection conn = Conexao.conect();
            PreparedStatement stmt = conn.prepareStatement(
                    command, Statement.RETURN_GENERATED_KEYS)){
            stmt.setInt(1, emprestimo.getLivroId());
            stmt.setInt(2, emprestimo.getUsuarioId());
            stmt.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
            if (emprestimo.getDataDevolucao() != null) {
                stmt.setDate(4, Date.valueOf(emprestimo.getDataDevolucao()));
            } else {
                stmt.setNull(4, Types.DATE);
            }
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                emprestimo.setId(rs.getInt(1));
                return emprestimo;
            }
        }
        throw new SQLException("Erro no cadastro");
    }

    public List<Emprestimo> getAll() throws SQLException{
        List<Emprestimo> emprestimos = new ArrayList<>();
        String command = """
                SELECT id,
                       livro_id,
                       usuario_id,
                       data_emprestimo,
                       data_devolucao
                FROM emprestimo
                """;
        try(Connection conn = Conexao.conect();
            PreparedStatement stmt = conn.prepareStatement(command)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Date devolucao = rs.getDate("data_devolucao");
                emprestimos.add(new Emprestimo(
                        rs.getInt("id"),
                        rs.getInt("livro_id"),
                        rs.getInt("usuario_id"),
                        rs.getDate("data_emprestimo").toLocalDate(),
                        devolucao != null ? devolucao.toLocalDate() : null
                ));
            }
        }
        return emprestimos;
    }

    public Emprestimo getById(Integer id) throws SQLException{
        String command = """
                SELECT id,
                       livro_id,
                       usuario_id,
                       data_emprestimo,
                       data_devolucao
                FROM emprestimo
                WHERE id = ?
                """;
        try(Connection conn = Conexao.conect();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Date devolucao = rs.getDate("data_devolucao");
                return new Emprestimo(
                        rs.getInt("id"),
                        rs.getInt("livro_id"),
                        rs.getInt("usuario_id"),
                        rs.getDate("data_emprestimo").toLocalDate(),
                        devolucao != null ? devolucao.toLocalDate() : null
                );
            }
        }
        throw new SQLException("Emprestimo não encontrado");
    }

    public boolean exists(Integer id) throws SQLException{
        String command = """
                SELECT id
                FROM emprestimo
                WHERE id = ?
                """;
        try(Connection conn = Conexao.conect();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? true : false;
        }
    }

    public void update(Emprestimo emprestimo) throws SQLException{
        String command = """
                UPDATE emprestimo
                SET livro_id = ?,
                    usuario_id = ?,
                    data_emprestimo = ?,
                    data_devolucao = ?
                WHERE id = ?
                """;
        try(Connection conn = Conexao.conect();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setInt(1, emprestimo.getLivroId());
            stmt.setInt(2, emprestimo.getUsuarioId());
            stmt.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
            if (emprestimo.getDataDevolucao() != null){
                stmt.setDate(4, Date.valueOf(emprestimo.getDataDevolucao()));
            } else {
                stmt.setNull(4, Types.DATE);
            }
            stmt.setInt(5, emprestimo.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(Integer id) throws SQLException{
        String command = """
                DELETE FROM emprestimo
                WHERE id = ?
                """;
        try(Connection conn = Conexao.conect();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void devolucao(Emprestimo emprestimo) throws SQLException{
        String command = """
                UPDATE emprestimo
                SET data_devolucao = ?
                WHERE id = ?
                """;
        try(Connection conn = Conexao.conect();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setDate(1, Date.valueOf(emprestimo.getDataDevolucao()));
            stmt.setInt(2, emprestimo.getId());
            stmt.executeUpdate();
        }
    }

    public boolean livroEmprestado(Integer livroId) throws SQLException{
        String command = """
                SELECT EXISTS(
                    SELECT 1
                    FROM emprestimo
                    WHERE livro_id = ?
                    AND (data_devolucao IS NULL
                         OR data_devolucao >= CURRENT_DATE)
                )
                """;
        try(Connection conn = Conexao.conect();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setInt(1, livroId);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? true : false;
        }
    }

    public List<Emprestimo> emprestimosUsuario(Integer id) throws SQLException{
        List<Emprestimo> emprestimos = new ArrayList<>();
        String command = """
                SELECT id,
                       livro_id,
                       usuario_id,
                       data_emprestimo,
                       data_devolucao
                FROM emprestimo
                WHERE usuario_id = ?
                """;
        try(Connection conn = Conexao.conect();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Date devolucao = rs.getDate("data_devolucao");
                emprestimos.add(new Emprestimo(
                        rs.getInt("id"),
                        rs.getInt("livro_id"),
                        rs.getInt("usuario_id"),
                        rs.getDate("data_emprestimo").toLocalDate(),
                        devolucao != null ? devolucao.toLocalDate() : null
                ));
            }
        }
        return emprestimos;
    }
}
