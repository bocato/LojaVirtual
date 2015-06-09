package Loja;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

public class LLogin {

    private final static Logger LOGGER = Logger.getLogger(LLogin.class.getName());
    private final static String JDBC_URL = "jdbc:mysql://localhost:3306/LojaVirtual";
    private final static String JDBC_USERNAME = "root";
    private final static String JDBC_PASSWORD = "";

    private String username;
    private String permission;
    private boolean logged;

    public LLogin() {
        this.username = null;
        this.permission = null;
        this.logged = false;
    }

    public boolean login(String username, String password) {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);

            ps = connection.prepareStatement("SELECT p.name_permission FROM user u INNER JOIN user_permission up ON u.user = up.user INNER JOIN permission p ON up.id_permission = p.id_permission WHERE u.user = ? AND u.pass = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeQuery();
            rs = ps.getResultSet();

            if ( rs.next() ) {
                String permission = rs.getString("name_permission");
                setUsername(username);
                setPermission(permission);
                result = true;
                LOGGER.info("LLogin: " + username + " logado como: " + permission + "!");
            }
            else {
                LOGGER.info("Tentativa de login de: " + username + " falhou!");
            }

        } catch( Exception e ) {
            LOGGER.info("LLogin Exception: " + e);
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                LOGGER.info("Erro ao finalizar recursos: " + e);
            }
        }
        setLogged(result);
        return result;
    }

    public boolean cadastrarCliente(String user, String pass) {

        Connection connection = null;
        PreparedStatement ps = null;
        boolean result = false;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);

            ps = connection.prepareStatement("INSERT INTO user VALUES(?, ?)");
            ps.setString(1, user);
            ps.setString(2, pass);

            if ( ps.executeUpdate() > 0 ) {
                LOGGER.info("Cliente '" + user + "' adicionado com sucesso!");

                ps = connection.prepareStatement("INSERT INTO user_permission VALUES(?, 1)");
                ps.setString(1, user);
                if ( ps.executeUpdate() > 0 ) {
                    result = true;
                }
                else {
                    connection.rollback();
                }

            }

        } catch( Exception e ) {
            LOGGER.info("LLogin Exception: " + e);
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (Exception e) {
                LOGGER.info("Erro ao finalizar recursos: " + e);
            }
        }
        return result;
    }

    private void setLogged(boolean logged) {
        this.logged = logged;
    }

    private void setPermission(String permission) {
        this.permission = permission;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public boolean getLogged() {
        return logged;
    }

    public String getPermission() {
        return permission;
    }

    public String getUsername() {
        return username;
    }

}
