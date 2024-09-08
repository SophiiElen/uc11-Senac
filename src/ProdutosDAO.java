/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    //Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public static boolean cadastrarProduto(ProdutosDTO p) throws SQLException {
        try {
            conectaDAO conn = new conectaDAO();
            conn.connectDB();

            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?);";
            PreparedStatement query = conn.getConn().prepareStatement(sql);

            query.setString(1, p.getNome());
            query.setString(2, String.valueOf(p.getValor()));
            p.setStatus("A venda");
            query.setString(3, p.getStatus());

            query.execute();

            conn.disconnectDB();
            return true;
        } catch (SQLException se) {
            System.out.println("Erro ao cadastrar registros no banco de dados");
            return false;
        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        conectaDAO conn = new conectaDAO();
        conn.connectDB();
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();

        try {
            String sql = "SELECT * FROM produtos";
            prep = conn.getConn().prepareStatement(sql);
            resultset = prep.executeQuery();

            /*prep = conn.prepareStatement("SELECT * FROM produtos");
            resultset = prep.executeQuery();*/
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(Integer.valueOf(resultset.getString("id")));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(Integer.valueOf(resultset.getString("valor")));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getErrorCode());
        } finally {
            conn.disconnectDB();
        }

        return listagem;
    }

    public void venderProduto(int produtoId) throws SQLException {
        conectaDAO conn = new conectaDAO();
        conn.connectDB();
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
        PreparedStatement prep = null;

        try {
            prep = conn.getConn().prepareStatement(sql);
            prep.setInt(1, produtoId);
            prep.executeUpdate();
            System.out.println("Produto atualizado para 'Vendido' com sucesso.");
            
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o status do produto: " + e.getMessage());
        } 
        finally {
            if (prep != null) {
                try {
                    prep.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar o PreparedStatement: " + e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.getConn().close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar a conexão: " + e.getMessage());
                }
            }
        }
    }

}
