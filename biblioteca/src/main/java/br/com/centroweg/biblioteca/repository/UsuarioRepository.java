package br.com.centroweg.biblioteca.repository;

import br.com.centroweg.biblioteca.model.Usuario;
import br.com.centroweg.biblioteca.util.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {
    public Usuario save(Usuario usuario) throws SQLException{
        String command = """
                INSERT INTO usuario
                (nome, email)
                VALUES (?,?)
                """;
        try(Connection conn = Conexao.conect();
            PreparedStatement stmt = conn.prepareStatement(
                    command, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                usuario.setId(rs.getInt(1));
                return usuario;
            }
        }
        throw new SQLException("Erro no cadastro");
    }

    public List<Usuario> getAll() throws SQLException{
        List<Usuario> usuarios = new ArrayList<>();
        String command = """
                SELECT id,
                       nome,
                       email
                FROM usuario
                """;
        try(Connection conn = Conexao.conect();
            PreparedStatement stmt = conn.prepareStatement(command)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getInt( "id"),
                        rs.getString("nome"),
                        rs.getString("email")
                ));
            }
        }
        return usuarios;
    }

    public Usuario getById(Integer id) throws SQLException{
        String command = """
                SELECT id,
                       nome,
                       email
                FROM usuario
                WHERE id = ?
                """;
        try(Connection conn = Conexao.conect();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email")
                );
            }
        }
        throw new SQLException("Usuario não encontrado");
    }

    public boolean exists(Integer id) throws SQLException{
        String command = """
                SELECT id
                FROM usuario
                WHERE id = ?
                """;
        try(Connection conn = Conexao.conect();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? true : false;
        }
    }

    public void update(Usuario usuario) throws SQLException{
        String command = """
                UPDATE usuario
                SET nome = ?,
                    email = ?
                WHERE id = ?
                """;
        try(Connection conn = Conexao.conect();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setInt(3, usuario.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(Integer id) throws SQLException{
        String command = """
                DELETE FROM usuario
                WHERE id = ?
                """;
        try(Connection conn = Conexao.conect();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
