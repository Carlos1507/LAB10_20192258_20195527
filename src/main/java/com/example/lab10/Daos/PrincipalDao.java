package com.example.lab10.Daos;

import com.example.lab10.Beans.ViajeBean;

import java.sql.*;
import java.util.ArrayList;

public class PrincipalDao extends BaseDao{

    public ArrayList<ViajeBean> listarViajes(){
        ArrayList<ViajeBean> listaViajes = new ArrayList<>();
        try (Connection connection = this.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("select * from viaje");){

            while (rs.next()){
                ViajeBean viaje = new ViajeBean();
                viaje.setIdViaje(rs.getString(1));
                viaje.setFechaReserva(rs.getString(2));
                viaje.setFechaViaje(rs.getString(3));
                viaje.setCiudadOrigen(rs.getString(4));
                viaje.setCiudadDestino(rs.getString(5));
                viaje.setSeguro(rs.getString(6));
                viaje.setNumBoletos(rs.getInt(7));
                viaje.setCostoTotal(rs.getFloat(8));
                listaViajes.add(viaje);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se pudo ejecutar");
        }
        return listaViajes;
    }
}
