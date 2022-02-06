/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.LoginDAO;
import Model.UsuarioModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            UsuarioModel usr = new UsuarioModel();
            LoginDAO ldao = new LoginDAO();
            int resp = 0;
            int rol = 0;
            if (request.getParameter("btnLogin") != null) {
                String user = request.getParameter("txtUser");
                String pass = request.getParameter("txtPass");

                usr.setNickname(user);
                usr.setContrasenia(pass);
                try {
                    resp = ldao.verificarCredenciales(usr);
                    rol = ldao.verificarRol(usr);
                } catch (Exception e) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
                }
                if (resp != 0) {
                    switch (rol) {
                        case 1:
                            response.sendRedirect("/Login/cliente.jsp");
                            break;
                        case 2:
                            response.sendRedirect("/Login/admin.jsp");
                            break;
                        case 3:
                            response.sendRedirect("/Login/mecanico.jsp");
                            break;              
                    }
                } else {
                    response.sendRedirect("/Login/index.jsp?resp=" + resp);
                }

            }
        }
    }

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

}
