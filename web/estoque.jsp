<%-- 
    Document   : estoque
    Created on : 29/03/2021, 22:34:17
    Author     : acfers
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="ifpe" uri="br.recife.edu.ifpe.customtags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body {
                
                background: linear-gradient(to right, #4158d0, #c850c0);
                display: flex;
                justify-content: center;
                flex-direction: column;
                align-items: center;
            }
            h1 {
                margin-bottom: 10px;
                color: white;
            }
            
            
            th {

                text-align: center;
                width: 120px;
                background-color: #36304a;
                color: #FFF;
            }
            th:nth-child(2){
                
                width: 200px;
            }
            th:nth-child(3){
                width: 250px;
            }
           
            table{

                margin-top: 50px;
                border-collapse: collapse;
                font-family: Arial;
                font-size: 18px;
            }
            td {
                text-align: center;
                background-color: white;
                width: 100px;
            }
            
            h1 + a{
                
                margin-top: 20px;
                text-decoration:none;
                height: 35px;
                width: 100px;
                background-color: #36304a;
                border-radius:10px;
                color: white;
                display:flex;
                justify-content:center;
                align-items: center;
            }
        </style>
    </head>
    <body>
        <h1>Produtos Inseridos no Estoque</h1>
        <a href="index.html">Home</a>
        <ifpe:carregaestoque/>
        
        <table>
            <tr>
                <th>Código</th>
                <th>Nome do Produto</th>
                <th>Quantidade em Estoque</th>
            </tr>
            <c:forEach var="item" items="${estoque.itens}">
            <tr>
                <td><c:out value="${item.codigo}"/></td>
                <td><c:out value="${item.produto.nome}"/></td>
                <td><c:out value="${item.quantidade}"/></td>
            </tr>    
            </c:forEach>
            
        </table>
        
    </body>
</html>
