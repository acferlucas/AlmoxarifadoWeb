<%-- 
    Document   : visualizarproduto
    Created on : 19/03/2021, 17:31:00
    Author     : acfers
--%>

<%@page import="br.recife.edu.ifpe.model.classes.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visualizar Produto jsp</title>
    </head>
    <body>
        <h1>Produto Cadastrado!</h1>
        
        <%
            Produto produto = (Produto)request.getAttribute("produto");
            if(produto != null){
        %>
        <table>
            <tr>
                <th>Codigo</th>
                <td><%= produto.getCodigo()%>></td>
            </tr>
            <tr>
                <th>Nome</th>
                <td><%= produto.getNome()%>></td>
            </tr>
            <tr>
                <th>Marca</th>
                <td><%= produto.getMarca()%>></td>
            </tr>
            <tr>
                <th>Modelo</th>
                <td><%= produto.getCategoria()%>></td>
            </tr>
            <tr>
                <th>Descrição</th>
                <td><%= produto.getDescricao()%>></td>
            </tr>
        </table>
            <%}%>
            
    </body>
</html>
