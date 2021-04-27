<%-- 
    Document   : lotesaidaapresentacao
    Created on : 29/03/2021, 19:24:50
    Author     : acfers
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="ifpe" uri="br.recife.edu.ifpe.customtags"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>2ª entrega</title>       
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
        <h1>Lotes removidos</h1>

        <h3><c:out value="${msg}"/></h3>
        <c:remove var="msg" scope="session"/>

        <ifpe:carregalotesaida/>
         
        <table>
            <tr><th>Data</th><th>Hora</th><th>Código</th><th>Quant. Total</th>
            <th>Responsável</th><th>Código resp.</th><th>Itens</th></tr>
            <c:forEach var="item" items="${lotesSaidaInseridos}">

                <tr>
                    <td><fmt:formatDate dateStyle="short" timeStyle="short" value="${item.data}" type="date"/></td>
                    <td><fmt:formatDate value="${item.data}" type="time"/></td>
                    <td>${item.codigo}</td>
                    <td>${item.quantidadeTotal}</td>
                    <td>${item.responsavel.nome}</td>
                    <td>${item.responsavel.codigo}</td>
                    <td><a href='#' onclick="carregaItens(${item.codigo})">Carregar</a></td>
                </tr>

            </c:forEach>
        </table>

        <script>
            var meuDiv;
            function carregaItens(codigo) {
                fetch("LoteSaidaServlet?codigo=" + codigo, {method: "get"}).then(function (response) {
                    response.text().then(function (text) {
                        let objeto = JSON.parse(text);
                        meuDiv = document.createElement("div");
                        meuDiv.setAttribute("class", "modal-css");
                        document.body.appendChild(meuDiv);
//                        meuDiv.innerHTML = objeto.codigo + "<br>" + objeto.descricao + "<br>";
                        meuDiv.innerHTML = "<br><br>";
                        let tabela = document.createElement("table");
                        tabela.setAttribute("border", "1");
                        tabela.setAttribute("class", "tabela-interna");
                        meuDiv.appendChild(tabela);
                        
                        //Criando header da tabela                          
                        let tr = document.createElement("tr");
                        let th1 = document.createElement("th");
                        th1.innerHTML = "Código";
                        let th2 = document.createElement("th");
                        th2.innerHTML = "Item";
                        let th3 = document.createElement("th");
                        th3.innerHTML = "Quant.";
                        
                        tr.appendChild(th1);
                        tr.appendChild(th2);
                        tr.appendChild(th3);
                        
                        tabela.appendChild(tr);
                        //Fim do header da tabela
                        for (let i = 0; i < objeto.itens.length; i++) {
                            let tr = document.createElement("tr");
                            let td1 = document.createElement("td");
                            td1.innerHTML = objeto.itens[i].codigo;
                            let td2 = document.createElement("td");
                            td2.innerHTML = objeto.itens[i].nomeProduto;
                            let td3 = document.createElement("td");
                            td3.innerHTML = objeto.itens[i].quantidade;
                            tr.appendChild(td1);
                            tr.appendChild(td2);
                            tr.appendChild(td3);
                            tabela.appendChild(tr);
                        }
                        let botao = document.createElement("button");
                        botao.setAttribute("class", "btn-close");
                        botao.appendChild(document.createTextNode("X"));
                        meuDiv.appendChild(botao);
                        botao.addEventListener("click", function () {
                            document.body.removeChild(meuDiv);
                            meuDiv = "";
                        });
                    });
                });
            }
        </script>
    </body>
</html>
