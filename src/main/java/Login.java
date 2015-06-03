import Loja.LLogin;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

/**
 * Created by gap on 01/06/15.
 */
public class Login extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(Login.class.getName());

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LLogin login;
        HttpSession sessao = request.getSession();

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String username = request.getParameter("user");
        String password = request.getParameter("pass");

        LOGGER.info("Tentativa de login de: " + username);

        login = new LLogin();
        sessao.setAttribute("login", login);

        if ( login.login(username, password) ) {
            LOGGER.info("Login: " + username + " logado como: " + login.getPermission() + "!");

            if (login.getPermission().equals("admin")) {
                response.sendRedirect("/LojaVirtual/admin");
            } else if (login.getPermission().equals("client")) {
                response.sendRedirect("/LojaVirtual/client");
            }
            else {
                out.println("<body style='background-color: #4c759a'><p style='color: #9c0a0a'><b>-> Usuario sem permissões.</b></p>");
                out.println("<a href='/LojaVirtual'><br>Voltar</a></body>");
            }

        }
        else {
            out.println("<body style='background-color: #4c759a'><p style='color: #9c0a0a'><b>-> Senha inválida, tente novamente.</b></p>");
            out.println("<a href='/LojaVirtual'><br>Voltar</a></body>");
            LOGGER.info("Tentativa de login de: " + username + " falhou!");
        }
        out.close();

    }
}