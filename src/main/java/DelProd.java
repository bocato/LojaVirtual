import Loja.LProd;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class DelProd extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(DelProd.class.getName());

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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

        LProd prod;
        HttpSession sessao = request.getSession();

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        Integer prodi = Integer.valueOf(request.getParameter("id"));

        prod = (LProd) sessao.getAttribute("prod");
        if (prod == null) {
            prod = new LProd();
            sessao.setAttribute("prod", prod);
        }

        LOGGER.info("Tentativa de remover do carrinho o produto: " + request.getParameter("id"));

        if ( prod.delFromCart(prodi)) {
            out.println("<body style='background-color: #4c759a'><p style='color: #9c0a0a'><b>-> Produto removido do carrinho com sucesso.</b></p>");
            out.println("<a href='/LojaVirtual/cart'><br>Voltar</a></body>");
        }
        else {
            out.println("<body style='background-color: #4c759a'><p style='color: #9c0a0a'><b>-> Erro ao remover produto do carrinho.</b></p>");
            out.println("<a href='/LojaVirtual/cart'><br>Voltar</a></body>");
        }
        out.close();

    }

}