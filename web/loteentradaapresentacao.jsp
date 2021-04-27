<%-- 
    Document   : loteentradaapresentacao
    Created on : 25/03/2021, 12:50:18
    Author     : acfers
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            .modal{
                
                position: absolute;
                background: white;
                top:100px;
                left:100px;
            }

            h1 {
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
            tr td:nth-child(3){
                width: 150px;
            }
            th:nth-child(4){
                
                width: 150px;
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
        <h1>Lotes Inseridos!</h1>
        <a href="index.html">Home</a>
        <ifpe:carregaloteentrada/>
        <table>
            <tr>
                <th>Data</th>
                <th>Codigo</th>
                <th>Quantidade</th>
                <th>Visualizar Itens</th>
            </tr>
        <c:forEach var="item" items="${lotesEntradaInseridos}">
            
            <tr>
                <td><fmt:formatDate value="${item.data}" type="date"/></td>
                <td>${item.codigo}</td>
                <td>${item.quantidadeTotal}</td>
                <td><a href="#" onclick="carregaItens(${item.codigo})">Visualizar Itens</a></td>
            </tr>
        </c:forEach>
        </table>
        <script>
            var  dive;
            function carregaItens(codigo){
            console.log("Entrei na função");
            fetch("LoteEntradaServlet?codigo="+codigo,{method:"get"}).then(function(response){
                    response.text().then(function(text){
                        let objeto = JSON.parse(text);
                        
                       dive  = document.createElement("div");
                       
                       dive.setAttribute("class", "modal");
                       
                       document.body.appendChild(dive);
                       
                       
                       dive.innerHTML = objeto.codigo+"<br>"+objeto.descricao+"<br>";
                       let tabela = document.createElement("table");
                       tabela.setAttribute("border", "1");
                       
                       dive.appendChild(tabela);
                       
                       for(let i = 0 ; i < objeto.itens.length; i ++){
                           
                           let tr = document.createElement("tr");
                           
                           let td1 = document.createElement("td");
                           td1.innerHTML = objeto.itens[i].codigo;
                           
                           let td2 = document.createElement("td");
                           td2.innerHTML = objeto.itens[i].nomeProduto;
                           
                           tr.appendChild(td1);
                           tr.appendChild(td2);
                           
                           tabela.appendChild(tr);
                       }
                       
                       let botao = document.createElement("button");
                       botao.appendChild(document.createTextNode("fechar"));
                       
                       dive.appendChild(botao);
                       
                       botao.addEventListener("click",function(){
                           
                           document.body.removeChild(dive);
                           dive ="";
                           
                       });
                        
                    });
                });
            }
            
        </script>
    </body>
</html>
