package Loja;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class LProd {

    private final static Logger LOGGER = Logger.getLogger(LLogin.class.getName());
    private final static String JDBC_URL = "jdbc:mysql://localhost:3306/LojaVirtual";
    private final static String JDBC_USERNAME = "root";
    private final static String JDBC_PASSWORD = "";

    List<Produto> carrinho = null;

    public LProd() {
        carrinho = new ArrayList<Produto>();
    }

    public boolean comprarCarrinho(String user, String name, String end, String cpf) {

        Connection connection = null;
        PreparedStatement ps = null;
        boolean result = false;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);

            ps = connection.prepareStatement("INSERT INTO orders(user, name, end, cpf) VALUES(?, ?, ?, ?)");
            ps.setString(1, user);
            ps.setString(2, name);
            ps.setString(3, end);
            ps.setString(4, cpf);

            if ( ps.executeUpdate() > 0 ) {
                boolean breaked = false;
                for (Produto produto : carrinho) {

                    ps = connection.prepareStatement("INSERT INTO order_detail VALUES(LAST_INSERT_ID(), ?)");
                    ps.setInt(1, produto.getId());

                    if ( ps.executeUpdate() < 1 ) {
                        connection.rollback();
                        breaked = true;
                        break;

                    }
                }
                result = ( breaked ? false : true );
            }

        } catch( Exception e ) {
            LOGGER.info("LProd Exception: " + e);
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (Exception e) {
                LOGGER.info("Erro ao finalizar recursos: " + e);
            }
        }
        if ( result ) { limparCarrinho(); }
        return result;
    }

    public void limparCarrinho() {
        carrinho.clear();
    }

    public boolean delFromCart(Integer id) {
        for (Produto prod : carrinho) {
            if (prod.getId() == id) {
                carrinho.remove(prod);
                return true;
            }
        }
        return false;
    }

    public boolean addToCart(Integer id) {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);

            ps = connection.prepareStatement("SELECT p.id_product, p.name, p.price FROM products p WHERE p.id_product = ?");
            ps.setInt(1, id);
            ps.executeQuery();
            rs = ps.getResultSet();

            if ( rs.next() ) {
                carrinho.add(new Produto(rs.getInt("id_product"), rs.getString("name"), rs.getFloat("price") ));
                result = true;
            }

        } catch( Exception e ) {
            LOGGER.info("LProd Exception: " + e);
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                LOGGER.info("Erro ao finalizar recursos: " + e);
            }
        }

        return result;

    }

    public List<Produto> listarCarrinho() {
        return carrinho;
    }

    public List<Produto> listarProdutos() {

        List<Produto> produtos = new ArrayList<Produto>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);

            ps = connection.prepareStatement("SELECT p.id_product, p.name, p.price FROM products p");
            ps.executeQuery();
            rs = ps.getResultSet();

            while ( rs.next() ) {
                produtos.add(new Produto(rs.getInt("id_product"), rs.getString("name"), rs.getFloat("price") ));
            }

        } catch( Exception e ) {
            LOGGER.info("LProd Exception: " + e);
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                LOGGER.info("Erro ao finalizar recursos: " + e);
            }
        }
        return produtos;
    }

    public boolean cadastrarProduto(Integer id, String nome, float preco) {

        Connection connection = null;
        PreparedStatement ps = null;
        boolean result = false;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);

            ps = connection.prepareStatement("INSERT INTO products VALUES(?, ?, ?)");
            ps.setInt(1, id);
            ps.setString(2, nome);
            ps.setFloat(3, preco);

            if ( ps.executeUpdate() > 0 ) {
                LOGGER.info("Produto '" + nome + "' adicionado com sucesso!");
                result = true;
            }

        } catch( Exception e ) {
            LOGGER.info("LProd Exception: " + e);
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

}
