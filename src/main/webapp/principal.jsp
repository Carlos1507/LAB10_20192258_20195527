<%--
  Created by IntelliJ IDEA.
  User: CARLOS
  Date: 27/06/2022
  Time: 23:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TeleViajes</title>
  <!--<link rel="shortcut icon" href="/" type="image/x-icon">-->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link href="estilosPrincipal.css" rel="stylesheet" type="text/css">
  <style>
    nav {
      background: green;
      height: 80px;
      width: 100%;
    }
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
    <label class="nombre"><span>Angelo Ramos</span></label>
    <label class="status"><span>Normal</span></label>
    <li><a href="#">Cerrar Sesión</a></li>
  </ul>
</nav>
<section>
<div class='container lista'>
  <h1 class='mb-3'>Lista de empleados</h1>
  <a href="" class="btn btn-primary mb-4">Agregar nuevo empleado</a>
  <table class="table">
    <thead>
    <tr>
      <th>#</th>
      <th>Employee</th>
      <th>Email</th>
      <th>Job ID</th>
      <th>Salary</th>
      <th>Commision</th>
      <th>Manager ID</th>
      <th>Department ID</th>
      <th></th>
      <th></th>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td>1</td>
      <td>Nombre1</td>
      <td>Correo 1</td>
      <td>JOBID1</td>
      <td>Salary 1</td>
      <td>PCT 1</td>
      <td>manager id 1</td>
      <td>department id 1</td>
      <td><a href="">Editar</a></td>
      <td><a href="">Borrar</a></td>
    </tr>
    </tbody>

  </table>
</div>
</section>
</body>
</html>
