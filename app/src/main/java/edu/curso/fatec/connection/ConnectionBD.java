package edu.curso.fatec.connection;
/*
dados do docker pra conectar 

jdbc:mariadb://localhost:3306/escola

banco: escola
usuário: root
senha: root

*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {

    private static String url = "jdbc:mariadb://localhost:3306/escola";
    private static String user = "root";
    private static String pass = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

}
