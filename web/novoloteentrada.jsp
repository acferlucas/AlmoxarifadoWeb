<%-- 
    Document   : novoloteentrada
    Created on : 24/03/2021, 22:25:58
    Author     : acfers
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="ifpe" uri="br.recife.edu.ifpe.customtags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lote Entrada</title>
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
            
            h2 {
                margin-bottom: 10px;
                color: white;
            }
            th {

                text-align: center;
                width: 100px;
                background-color: #36304a;
                color: #FFF;
            }
            tr:nth-child(even) {
            background-color:  #f5f5f5;
             }
            table{

                margin-top: 20px;
                border-collapse: collapse;
                font-family: Arial;
                font-size: 18px;
            }
            td {
                text-align: center;
                background-color: white;
                width: 100px;
            }
            .plus{
                font-size: 15pt;
                text-decoration: none;
                color:black;
                font-weight: bold;
            }
            button {
                
                margin-top: 40px;
                width: 120px;
                height: 25px;
                background-color:#36304a;
                border:none;
                border-radius:10px;
                color : white;
            }
            button:hover {
                
                cursor: pointer;
                background: grey;
                transition: 1s;
            }
        </style>
    </head>
    <body>
        <h1>Cadastro Lote Entrada</h1>
        <p><c:out value="${msg}"/></p>
            <c:remove var="msg" scope="session"/>
        <ifpe:carregaprodutos/>
            <h2>Produtos Cadastrados</h2>
        <table>
            <tr>
                <th>Codigo</th>
                <th>Nome</th>
                <th>Marca</th>
                <th>Categoria</th>
                <th>Inserir</th>
            </tr>

            <c:forEach var="pAux" items="${produtos}">
                <tr>
                    <td>${pAux.codigo}</td>
                    <td>${pAux.nome}</td>
                    <td>${pAux.marca}</td>
                    <td>${pAux.categoria}</td>
                    <td><a href="#" class="plus" onclick="adiciona(${pAux.codigo})">+</a></td>
                </tr>
            </c:forEach>
        </table>
            <h2>Produtos Inseridos no Lote de Entrada</h2>
            <c:if test="${loteEntrada != null}">
        <table>
            <tr>
                <th>Codigo</th>
                <th>Nome</th>
                <th>Marca</th>
                <th>Categoria</th>
                <th>Quantidade</th>
                <th>Inserir</th>
            </tr>

            <c:forEach var="i" items="${loteEntrada.itens}">
                <tr>
                    <td>${i.produto.codigo}</td>
                    <td>${i.produto.nome}</td>
                    <td>${i.produto.marca}</td>
                    <td>${i.produto.categoria}</td>
                    <td>${i.quantidade}</td>
                    <td>|<a href="#" class="plus" onclick="remover(${i.produto.codigo})">-</a>
                        |<a href="#" class="plus" onclick="adiciona(${i.produto.codigo})">+</a></td>
                </tr>
            </c:forEach>
        </table>
        <button onclick="cadastrar()">Cadastrar Lote</button>
        </c:if>
        
        
        <script>
            function adiciona(codigo){
                fetch("LoteEntradaServlet?operacao=mais&codigo="+codigo,{method:"put"})
                        .then(function(){
                           location.reload(); 
                        });
            }
            
            function remover(codigo){
                fetch("LoteEntradaServlet?operacao=menos&codigo="+codigo,{method:"put"})
                        .then(function(){
                           location.reload(); 
                        });
            }
            
            function cadastrar(){
                fetch("LoteEntradaServlet",{method:"post"})
                        .then(function(response){
                            if(response.status == 500){
                                
                                location.reload(); 
                            }else{
                                location.href = "loteentradaapresentacao.jsp";
                            }
                        }).catch(function(erro){
                            location.reload(); 
                        });
            }
        </script>
    
    </body>
</html>
