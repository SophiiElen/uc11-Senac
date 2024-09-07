
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Adm
 */
public class conectaDAO {

    private Connection conn;

    public Connection getConn() {
        return conn;
    }

    public Connection connectDB() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/uc11?useSSL=false", "root", "Eclesiastes31@");
            System.out.println("Conexão realizada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + ex.getMessage());
        }
        return conn;
    }

    public void disconnectDB() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexão encerrada com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + ex.getMessage());
            }
        } else {
            System.out.println("Conexão já está fechada ou nunca foi aberta.");
        }
    }

}
