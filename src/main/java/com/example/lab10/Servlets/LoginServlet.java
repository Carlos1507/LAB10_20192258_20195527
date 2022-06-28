package com.example.lab10.Servlets;
import com.example.lab10.Beans.EstudianteBean;
import com.example.lab10.Daos.LoginDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mensaje = request.getParameter("error")==null?"":request.getParameter("error");
        request.setAttribute("error",mensaje);
        RequestDispatcher view = request.getRequestDispatcher("iniciaSesion.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginDao loginDao = new LoginDao();
        EstudianteBean estudiante;
        String username = request.getParameter("usuario");
        String password = request.getParameter("contrasena");
        boolean isteleco = loginDao.validarTeleco(username);
        if (!isteleco){
            response.sendRedirect(request.getContextPath() + "/LoginServlet?error=noEsTeleco");
        }else{
            estudiante = loginDao.validarContrasenia(username, password);
            if(estudiante==null){
                response.sendRedirect(request.getContextPath() + "/LoginServlet?error=credencialesIncorr");
            }else{
                HttpSession session = request.getSession();
                estudiante.setStatus(loginDao.obtenerStatus(estudiante));
                session.setMaxInactiveInterval(60 * 10);
                System.out.println("Sesión exitosa");
                response.sendRedirect(request.getContextPath() + "/PrincipalServlet");
            }
        }
    }
}
