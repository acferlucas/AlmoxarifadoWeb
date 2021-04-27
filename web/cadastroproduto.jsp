<%-- 
    Document   : cadastroproduto
    Created on : 18/03/2021, 20:14:41
    Author     : acfers
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>JSP Page</title>
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
                    out.println("<h1>Cadastro produto</h1>");
                }else if(redirecth1.equals("atualiza")) {
                    out.println("<h1>atualizar produto</h1>");
                }
      %>
      
  </header>  
  <main>
    <form method="post" action="ProdutoServlet">
      <div>
        <p>Código</p>
        <input type="text" name="codigo" value="${(param.redirect != null && param["redirect"] eq 'atualiza')?produto.codigo:''}"/>
      </div>  
      <div>
        <p>Nome</p>
        <input type="text" name="nome" value="${(param.redirect != null && param["redirect"] eq 'atualiza')?produto.nome:''}" />
      </div>
      <div>
        <p>Marca</p>
        <input type="text" name="marca" value="${(param.redirect != null && param["redirect"] eq 'atualiza')?produto.marca:''}"/>
      </div>
      <div>
        <p>Categoria</p>
        <input type="text" name="categoria" value="${(param.redirect != null && param["redirect"] eq 'atualiza')?produto.categoria:''}"/>
      </div>
      <div>
        <p>Descriação</p>
        <textarea name="descricao">${(param.redirect != null && param["redirect"] eq 'atualiza')?produto.descricao:''}</textarea>
      </div>
        <input type="hidden" name="${(param.redirect != null && param.redirect eq 'atualiza')?'atualizar':'cadastrar'}" value="1">
        <input type="submit" value="${(param.redirect != null && param.redirect eq 'atualiza')?'atualizar':'cadastrar'}"/>
    </form>
  </main>
</body>
</html>
