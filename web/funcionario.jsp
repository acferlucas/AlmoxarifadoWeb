

<%@page import="br.recife.edu.ifpe.model.classes.Funcionario"%>
<%@page import="java.util.List"%>
<%@page import="br.recife.edu.ifpe.model.repositorios.RepositorioFuncionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tela Funcionario</title>
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
                color: #f5f5f5;
            }
            th {

                text-align: center;
                width: 100px;
                background-color: #36304a;
                color: #FFF;
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
            tr:nth-child(even){
                background-color: #f5f5f5;
            }







        </style>
    </head>
    <body>
        <header>
            <h1>Funcionarios Cadastrados!</h1>
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
            <button onclick="modalopen()">Novo Funcionario</button>
            <div id="modal" style="position: absolute; left:100px; top: 20px; border: 1px solid black;">
                <%@include file="cadastrofuncionario.jsp" %>
                <button onclick="modalclose()">Close</button>
            </div>
            <div id="modal2" style="position: absolute; left:100px; top: 20px; border: 1px solid black;">
                <%@include file="visualizarfuncionario.jsp" %>
                <button onclick="modal2close()">Close</button>
            </div>
            <%
                List<Funcionario> Funcionarios = RepositorioFuncionario.getCurrentInstance().readAll();
            %>
            <table>
                <tr>
                    <th>Codigo</th>
                    <th>Nome</th>
                    <th>Departamento</th>
                    <th>Operações</th>
                </tr>
                <%
                    for (Funcionario f : Funcionarios) {
                %>
                <tr>
                    <td><%=f.getCodigo()%></td>
                    <td><%=f.getNome()%></td>
                    <td><%=f.getDepartamento()%></td>
                    <td><a href="FuncionarioServlet?codigo=<%= f.getCodigo()%>&redirect=visualiza"><img src="imagens/icons8-view-24.png"></a>
                        <a href="FuncionarioServlet?codigo=<%= f.getCodigo()%>&redirect=atualiza"><img src="imagens/icons8-update-file-24.png"></a>
                        <a href="#" onclick="deleteFuncionario(<%= f.getCodigo()%>)"><img src="imagens/icons8-delete-file-24 (1).png"></a></td>
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
            
            function deleteFuncionario(codigo){
                
                fetch("FuncionarioServlet?codigo="+codigo,{method:'delete'})
                        .then(function(response){
                            location.reload();
                        });
            }


        </script>
    </body>
</html>
