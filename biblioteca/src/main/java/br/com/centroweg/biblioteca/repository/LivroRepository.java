package br.com.centroweg.biblioteca.repository;

import br.com.centroweg.biblioteca.model.Livro;
import br.com.centroweg.biblioteca.util.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LivroRepository {
    public Livro save(Livro livro) throws SQLException{
        String command = """
                INSERT INTO livro
                (titulo, autor, ano_publicacao)
                VALUES (?,?,?)
                """;
        try(Connection conn = Conexao.conect();
            PreparedStatement stmt = conn.prepareStatement(
                    command, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAnoPublicacao());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                livro.setId(rs.getInt(1));
                return livro;
            }
        }
        throw new SQLException("Erro no cadastro");
    }

    public List<Livro> getAll() throws SQLException{
        List<Livro> livros = new ArrayList<>();
        String command = """
                SELECT id,
                       titulo,
                       autor,
                       ano_publicacao
                FROM livro
                """;
        try(Connection conn = Conexao.conect();
            PreparedStatement stmt = conn.prepareStatement(command)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                livros.add(new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("ano_publicacao")
                ));
            }
        }
        return livros;
    }

    public Livro getById(Integer id) throws SQLException{
        String command = """
                SELECT id,
                       titulo,
                       autor,
                       ano_publicacao
                FROM livro
                WHERE id = ?
                """;
        try(Connection conn = Conexao.conect();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("ano_publicacao"));
            }
        }
        throw new RuntimeException("Livro não encontrado");
    }

    public boolean exists(Integer id) throws SQLException{
        String command = """
                SELECT id 
                FROM livro
                WHERE id = ?
                """;
        try(Connection conn = Conexao.conect();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? true : false;
        }
    }

    public void update(Livro livro) throws SQLException{
        String command = """
                UPDATE livro
                SET titulo = ?,
                    autor = ?,
                    ano_publicacao = ?
                WHERE id = ?
                """;
        try(Connection conn = Conexao.conect();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAnoPublicacao());
            stmt.setLong(4, livro.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(Integer id) throws SQLException{
        String command = """
                DELETE FROM livro
                WHERE id = ?
                """;
        try(Connection conn = Conexao.conect();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
