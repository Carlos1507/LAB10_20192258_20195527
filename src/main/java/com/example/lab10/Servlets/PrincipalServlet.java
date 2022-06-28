package com.example.lab10.Servlets;

import com.example.lab10.Daos.PrincipalDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "PrincipalServlet", value = "/PrincipalServlet")
public class PrincipalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("a") == null? "listar" : request.getParameter("a");
        PrincipalDao principalDao = new PrincipalDao();
        switch (action){
            case "listar" -> {
                request.setAttribute("listaViajes",principalDao.listarViajes("20214192")); //luego cambiar con session

                RequestDispatcher view = request.getRequestDispatcher("principal.jsp");
                view.forward(request, response);
            }
            case "crear" -> {
                RequestDispatcher view = request.getRequestDispatcher("crearViaje.jsp");
                view.forward(request, response);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("a") == null? "listar" : request.getParameter("a");
        PrincipalDao principalDao = new PrincipalDao();
        switch (action){
            case "buscar" -> {
                String nameCiudad = request.getParameter("ciudad");
                request.setAttribute("listaViajes",principalDao.listarViajesFiltrada("20214192",nameCiudad)); //luego cambiar con session

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

                principalDao.crearViajeEnEstudiante("20214192",idViaje); //luego cambiar con session



                response.sendRedirect(request.getContextPath() + "/PrincipalServlet");
            }
        }
    }
}
