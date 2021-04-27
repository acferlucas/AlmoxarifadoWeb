

<%@page import="br.recife.edu.ifpe.model.classes.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visualizar Funcionario jsp</title>
    </head>
    <body>
        <h1>Funcionario Cadastrado!</h1>
        
        <%
            Funcionario funcionario = (Funcionario)request.getAttribute("funcionario");
            if(funcionario != null){
        %>
        <table>
            <tr>
                <th>Codigo</th>
                <td><%= funcionario.getCodigo()%>></td>
            </tr>
            <tr>
                <th>Nome</th>
                <td><%= funcionario.getNome()%>></td>
            </tr>
            <tr>
                <th>Marca</th>
                <td><%= funcionario.getDepartamento()%>></td>
            </tr>
        </table>
            <%}%>
            
    </body>
</html>
