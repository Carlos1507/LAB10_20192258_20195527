package com.example.lab10.Servlets;

import com.example.lab10.Beans.EstudianteBean;
import com.example.lab10.Beans.ViajeBean;
import com.example.lab10.Daos.LoginDao;
import com.example.lab10.Daos.PrincipalDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "PrincipalServlet", value = "/PrincipalServlet")
public class PrincipalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        LoginDao loginDao = new LoginDao();
        HttpSession session = request.getSession();
        EstudianteBean estud = (EstudianteBean) session.getAttribute("estudianteSession");
        if(estud!=null){
            String action = request.getParameter("a") == null? "listar" : request.getParameter("a");
            PrincipalDao principalDao = new PrincipalDao();
            switch (action){
                case "listar" -> {
                    estud.setStatus(loginDao.obtenerStatus(estud));
                    request.setAttribute("listaViajes",principalDao.listarViajes(estud.getCodigoPUCP()));
                    RequestDispatcher view = request.getRequestDispatcher("principal.jsp");
                    view.forward(request, response);
                }
                case "crear" -> {
                    RequestDispatcher view = request.getRequestDispatcher("crearViaje.jsp");
                    view.forward(request, response);
                }
                case "editar" -> {
                    String idViaje = request.getParameter("id");
                    ViajeBean viajeBean = principalDao.buscarViaje(idViaje);
                    if(viajeBean != null){
                        session.setAttribute("estudiante", estud);
                        request.setAttribute("viajeBean",viajeBean);
                        RequestDispatcher view = request.getRequestDispatcher("editarViaje.jsp");
                        view.forward(request, response);
                    }else{
                        response.sendRedirect(request.getContextPath() + "/PrincipalServlet");
                    }
                }
                case "borrar" -> {
                    String idViaje = request.getParameter("id");
                    principalDao.borrarPorEstudiante(idViaje);
                    principalDao.borrar(idViaje);
                    response.sendRedirect(request.getContextPath() + "/PrincipalServlet");
                }
            }
        }else{
            response.sendRedirect(request.getContextPath()+"/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        EstudianteBean estud = (EstudianteBean) session.getAttribute("estudianteSession");
        if(estud!=null){
            String action = request.getParameter("a") == null? "listar" : request.getParameter("a");
            PrincipalDao principalDao = new PrincipalDao();
            switch (action){
                case "buscar" -> {
                    String nameCiudad = request.getParameter("ciudad");
                    request.setAttribute("listaViajes",principalDao.listarViajesFiltrada(estud.getCodigoPUCP(),nameCiudad)); //luego cambiar con session

                    RequestDispatcher view = request.getRequestDispatcher("principal.jsp");
                    view.forward(request, response);
                }
                case "guardar" -> {
                    String idViaje = "";
                    String[] nums = {"0","1","2","3","4","5","6","7","8","9"};
                    for (int i = 0; i < 8; i++ ) {
                        idViaje += nums[(int) Math.round(Math.random() * 9)];
                    }

                    String fechaViaje = request.getParameter("fechaViaje");
                    //Fecha reserva es la actual
                    LocalDate fechaReserva = LocalDate.now();
                    String origen = request.getParameter("origen");
                    String destino = request.getParameter("destino");
                    String seguro = request.getParameter("seguro");
                    String numBoletos = request.getParameter("numBoletos");
                    String costoTotal = request.getParameter("costoTotal");

                    principalDao.crearViaje(idViaje,fechaViaje,fechaReserva.toString(),origen,destino,seguro,numBoletos,costoTotal);

                    principalDao.crearViajeEnEstudiante(estud.getCodigoPUCP(),idViaje); //luego cambiar con session

                    response.sendRedirect(request.getContextPath() + "/PrincipalServlet");
                }
                case "actualizar" -> {
                    String idViaje = request.getParameter("idViaje");
                    String fechaViaje = request.getParameter("fechaViaje");
                    String fechaReserva = request.getParameter("fechaReserva");
                    String origen = request.getParameter("origen");
                    String destino = request.getParameter("destino");
                    String seguro = "";
                    if(request.getParameter("seguro1") != null){
                        seguro = request.getParameter("seguro1"); //si es que lo edita el usuario
                    }else {
                        seguro = request.getParameter("seguro2");  //el original
                    }
                    String numBoletos = request.getParameter("numBoletos");
                    String costoTotal = request.getParameter("costoTotal");

                    principalDao.editarViaje(idViaje,fechaViaje,fechaReserva,origen,destino,seguro,numBoletos,costoTotal);

                    response.sendRedirect(request.getContextPath() + "/PrincipalServlet");
                }
            }
        }else{
            response.sendRedirect(request.getContextPath()+"/");
        }

    }
}
