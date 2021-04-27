<%-- 
    Document   : produtos
    Created on : 18/03/2021, 21:31:06
    Author     : acfers
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="br.recife.edu.ifpe.model.repositorios.RepositorioProdutos"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.recife.edu.ifpe.model.classes.Produto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="ifpe" uri="br.recife.edu.ifpe.customtags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tela Produtos</title>
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
            p {
                width: 400px;
                text-align: left;
                color: white;
                font-size: 20px;
            }
            header p {
                color: #66ff66;
                font-size: 15px;
            }

            a:nth-child(4){
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
        <header>
            <h1>Produtos Cadastrados!</h1>
            <%
                String mensagem = (String) session.getAttribute("msg");
                if (mensagem != null) {
                    out.println("<p>" + mensagem + "<p>");
                    session.removeAttribute("msg");
                }
            %>
        </header>
        <main id="main">
            <button><a href="index.html" style="text-decoration: none;">Pagina Home</a></button>
            <button onclick="modalopen()">Novo Produto</button>
            <div id="modal" style="position: absolute; left:100px; top: 20px; border: 1px solid black;">
                <%@include file="cadastroproduto.jsp" %>
                <button onclick="modalclose()">Close</button>
            </div>
            <div id="modal2" style="position: absolute; left:100px; top: 20px; border: 1px solid black;">
                <%@include file="visualizarproduto.jsp" %>
                <button onclick="modal2close()">Close</button>
            </div>
            <%
                List<Produto> produtos = RepositorioProdutos.getCurrentInstance().readAll();
                
                request.setAttribute("produtos", produto);
            %>
            <table>
                <tr>
                    <th>Codigo</th>
                    <th>Nome</th>
                    <th>Marca</th>
                    <th>Categoria</th>
                    <th>Operações</th>
                </tr>
                <%
                    for (Produto p : produtos) {
                %>
                <tr>
                    <td><%=p.getCodigo()%></td>
                    <td><%=p.getNome()%></td>
                    <td><%=p.getMarca()%></td>
                    <td><%=p.getCategoria()%></td>
                    <td><a href="ProdutoServlet?codigo=<%= p.getCodigo()%>&redirect=visualiza"><img src="imagens/icons8-view-24.png"></a>
                        <a href="ProdutoServlet?codigo=<%= p.getCodigo()%>&redirect=atualiza"><img src="imagens/icons8-update-file-24.png"></a>
                        <a href="#" onclick="deleteProduto(<%= p.getCodigo()%>)"><img src="imagens/icons8-delete-file-24 (1).png"></a></td>
                </tr>
                <%
                    }
                %>
            </table>
        </main>
        <script>

            var modal = document.getElementById("modal");
            const main = document.getElementById("main");
            var modal2 = document.getElementById("modal2");
            <%
                String redirect = request.getParameter("redirect");
                if(redirect == null){
            %>
                main.removeChild(modal);
                main.removeChild(modal2);
                
            <%}else if(redirect.equals("visualiza")) {%>
                main.removeChild(modal);
                
            <%}else{%>
                main.removeChild(modal2);
                
            <%}%>
            function modalclose() {
                main.removeChild(modal);
            }
            function modal2close() {
                main.removeChild(modal2);
            }

            function modalopen() {
                main.appendChild(modal);
            }
            
            function deleteProduto(codigo){
                
                fetch("ProdutoServlet?codigo="+codigo,{method:'delete'})
                        .then(function(response){
                            location.reload();
                        });
            }


        </script>
        <a href="novoloteentrada.jsp">Novo Lote</a>
    </body>
</html>

