<%@ page import="com.example.lab10.Beans.ViajeBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="estudianteSession" scope="session" type="com.example.lab10.Beans.EstudianteBean"/>
<jsp:useBean id="listaViajes" scope="request" type="java.util.ArrayList<com.example.lab10.Beans.ViajeBean>" />
<html>
<head>
    <title>TeleViajes</title>
  <!--<link rel="shortcut icon" href="/" type="image/x-icon">-->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link href="estilosPrincipal.css" rel="stylesheet" type="text/css">
  <style>
    <% if(estudianteSession.getStatus().equals("Normal")){%>
    nav {
      background: blue;
      height: 80px;
      width: 100%;
    }
    <%} else if (estudianteSession.getStatus().equals("Silver")){%>
    nav {
    background: silver;
    height: 80px;
    width: 100%;
    }
    <%} else if (estudianteSession.getStatus().equals("Gold")){%>
    nav {
    background: goldenrod;
    height: 80px;
    width: 100%;
    }
    <%} else if (estudianteSession.getStatus().equals("Platinum")){%>
    nav {
    background: black;
    height: 80px;
    width: 100%;
    }
    <%}%>

    .lista {
      background-color: white;
      margin-top: 50px;
      padding-left: 20px;
      padding-right: 20px;
    }
  </style>
</head>
<nav>
  <input type="checkbox" id="check">
  <label for="check"  class="checkbtn">
    <i class="fa fa-bars"></i>
  </label>
  <label class="logo"><span>Televiajes</span></label>
  <ul>
    <label class="nombre"><span><%=estudianteSession.getNombre()%></span></label>
    <label class="status"><span><%=estudianteSession.getStatus()%></span></label>
    <li><a href="#">Cerrar Sesión</a></li>
  </ul>
</nav>
<section>
<div class='container lista'>
  <h1 class='mb-3'>Lista de viajes</h1>

  <a href="<%=request.getContextPath()%>/PrincipalServlet?a=crear" class="btn btn-primary mb-4">Agregar nuevo viaje</a>

  <form method="post" class="row g-3" action="<%=request.getContextPath()%>/PrincipalServlet?a=buscar">
    <div class="col-auto">
      <p>Búsqueda por ciudad: </p>
    </div>
    <div class="col-auto">
      <input type="text" class="form-control" name="ciudad" id="ciudad" placeholder="Origen o Destino">
      <div class="invalid-feedback">
        In this case is not necessary
      </div>
    </div>
    <div class="col-auto">
      <button type="submit" class="btn btn-primary mb-3">Buscar</button>
    </div>
  </form>

  <table class="table">
    <thead>
      <tr>
        <th>Identificador</th>
        <th>Fecha reserva</th>
        <th>Fecha viaje</th>
        <th>Ciudad origen</th>
        <th>Ciudad destino</th>
        <th>Seguro</th>
        <th>Num boletos</th>
        <th>Costo total (S/.)</th>
        <th></th>
        <th></th>
      </tr>
    </thead>
    <tbody>
      <%for(ViajeBean viaje : listaViajes){ %>
      <tr>
        <td><%=viaje.getIdViaje()%></td>
        <td><%=viaje.getFechaReserva()%></td>
        <td><%=viaje.getFechaViaje()%></td>
        <td><%=viaje.getCiudadOrigen()%></td>
        <td><%=viaje.getCiudadDestino()%></td>
        <td><%=viaje.getSeguro()%></td>
        <td><%=viaje.getNumBoletos()%></td>
        <td><%=viaje.getCostoTotal()%></td>
        <td><a href="<%=request.getContextPath()%>/PrincipalServlet?a=editar&id=<%=viaje.getIdViaje()%>">Editar</a></td>
        <td><a href="<%=request.getContextPath()%>/PrincipalServlet?a=borrar&id=<%=viaje.getIdViaje()%>">Borrar</a></td>
      </tr>
      <%}%>
    </tbody>

  </table>
</div>
</section>
</body>
</html>
