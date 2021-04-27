/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.recife.edu.ifpe.controller.servlets;

import br.recife.edu.ifpe.model.classes.Funcionario;
import br.recife.edu.ifpe.model.repositorios.RepositorioFuncionario;
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
@WebServlet(name = "FuncionarioServlet", urlPatterns = {"/FuncionarioServlet"})
public class FuncionarioServlet extends HttpServlet {

    

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

        
        Funcionario f = RepositorioFuncionario.getCurrentInstance().read(codigo);
        request.setAttribute("funcionario", f);
        
        getServletContext().getRequestDispatcher("/funcionario.jsp").forward(request, response);
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
        String departamento = request.getParameter("departamento");
        String atualizar =  request.getParameter("atualizar");
       
        Funcionario f = new Funcionario();
        f.setCodigo(codigo);
        f.setNome(nome);
        f.setDepartamento(departamento);
        
        
        HttpSession session = request.getSession();
        if(atualizar == null) {
       
        RepositorioFuncionario.getCurrentInstance().create(f);
        
        session.setAttribute("msg", "Funcionario "+f.getNome()+" Cadastrado com Sucesso");
       
        }else {
         
            RepositorioFuncionario.getCurrentInstance().update(f);
            session.setAttribute("msg", "Funcionario "+f.getNome()+" atualizado com Sucesso");
        
        }
        
        response.sendRedirect("funcionario.jsp");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doDelete(request, response); //To change body of generated methods, choose Tools | Templates.
        
        int codigo =  Integer.parseInt(request.getParameter("codigo"));
        
        Funcionario f = RepositorioFuncionario.getCurrentInstance().read(codigo);
        
        RepositorioFuncionario.getCurrentInstance().delete(f);
        
        HttpSession session = request.getSession();
        
        session.setAttribute("msg", "Funcionario "+f.getNome()+" foi deletado com sucesso");
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
