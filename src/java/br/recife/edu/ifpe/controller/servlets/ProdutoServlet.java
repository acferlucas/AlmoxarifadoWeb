/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.recife.edu.ifpe.controller.servlets;

import br.recife.edu.ifpe.model.classes.ItemEstoque;
import br.recife.edu.ifpe.model.classes.Produto;
import br.recife.edu.ifpe.model.repositorios.RepositorioEstoque;
import br.recife.edu.ifpe.model.repositorios.RepositorioProdutos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author acfers
 */
@WebServlet(name = "ProdutoServlet", urlPatterns = {"/ProdutoServlet"})
public class ProdutoServlet extends HttpServlet {

   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int codigo = Integer.parseInt(request.getParameter("codigo"));

        
        Produto p = RepositorioProdutos.getCurrentInstance().read(codigo);
        request.setAttribute("produto", p);
        
        getServletContext().getRequestDispatcher("/produtos.jsp").forward(request, response);
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String nome = request.getParameter("nome");
        String marca = request.getParameter("marca");
        String categoria = request.getParameter("categoria");
        String descricao = request.getParameter("descricao");
        String atualizar =  request.getParameter("atualizar");
       
        Produto p = new Produto();
        p.setCodigo(codigo);
        p.setNome(nome);
        p.setMarca(marca);
        p.setCategoria(categoria);
        p.setDescricao(descricao);
        
        HttpSession session = request.getSession();
        if(atualizar == null) {
       
        RepositorioProdutos.getCurrentInstance().create(p);
        
        ItemEstoque item = new ItemEstoque();
        item.setProduto(p);
        item.setQuantidade(0);
        item.setCodigo(p.getCodigo());
        
        RepositorioEstoque.getCurrentInstance().read().addItem(item);
        
         session.setAttribute("msg", "Produto "+p.getNome()+" Cadastrado com Sucesso");
       
        }else {
         
            RepositorioProdutos.getCurrentInstance().update(p);
            session.setAttribute("msg", "Produto "+p.getNome()+" atualizado com Sucesso");
        
        }
        
        response.sendRedirect("produtos.jsp");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doDelete(request, response); //To change body of generated methods, choose Tools | Templates.
    
        int codigo =  Integer.parseInt(request.getParameter("codigo"));
        
        Produto p = RepositorioProdutos.getCurrentInstance().read(codigo);
        
        RepositorioProdutos.getCurrentInstance().delete(p);
        
        HttpSession session = request.getSession();
        
        session.setAttribute("msg", "Produto "+p.getNome()+" foi deletado com sucesso");
        
        
        
    }
    
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
