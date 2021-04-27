

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Cadastro Funcionario</title>
  <style>
    *{
      margin: 0;
      padding: 0;
      box-sizing: border-box;
    }
    form {
      padding: 20px;
    }
    
  </style>
</head>
<body>
  <header>
      
      <%
                String redirecth1 = request.getParameter("redirect");
                if(redirecth1 == null){
                    out.println("<h1>Cadastro Funcionario</h1>");
                }else if(redirecth1.equals("atualiza")) {
                    out.println("<h1>atualizar Funcionario</h1>");
                }
      %>
      
  </header>  
  <main>
    <form method="post" action="FuncionarioServlet">
      <div>
        <p>Código</p>
        <input type="text" name="codigo" value="${(param.redirect != null && param["redirect"] eq 'atualiza')?funcionario.codigo:''}"/>
      </div>  
      <div>
        <p>Nome</p>
        <input type="text" name="nome" value="${(param.redirect != null && param["redirect"] eq 'atualiza')?funcionario.nome:''}" />
      </div>
      <div>
        <p>Departamento</p>
        <input type="text" name="departamento" value="${(param.redirect != null && param["redirect"] eq 'atualiza')?funcionario.departamento:''}"/>
      </div>
        <input type="hidden" name="${(param.redirect != null && param.redirect eq 'atualiza')?'atualizar':'cadastrar'}" value="1">
        <input type="submit" value="${(param.redirect != null && param.redirect eq 'atualiza')?'atualizar':'cadastrar'}"/>
    </form>
  </main>
</body>
</html>