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
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
     public static boolean cadastrarProduto (ProdutosDTO p) throws SQLException{
        try{
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
        } catch (SQLException se){
            System.out.println("Erro ao cadastrar registros no banco de dados");
            return false;
        }
        
    }
        
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

