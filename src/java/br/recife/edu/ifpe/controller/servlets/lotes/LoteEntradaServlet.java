/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.recife.edu.ifpe.controller.servlets.lotes;

import br.recife.edu.ifpe.controller.servlets.lotes.*;
import br.recife.edu.ifpe.model.classes.Estoque;
import br.recife.edu.ifpe.model.classes.ItemEntrada;
import br.recife.edu.ifpe.model.classes.ItemEstoque;
import br.recife.edu.ifpe.model.classes.LoteEntrada;
import br.recife.edu.ifpe.model.classes.Produto;
import br.recife.edu.ifpe.model.repositorios.RepositorioEstoque;
import br.recife.edu.ifpe.model.repositorios.RepositorioLoteEntrada;
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
@WebServlet(name = "LoteEntradaServlet", urlPatterns = {"/LoteEntradaServlet"})
public class LoteEntradaServlet extends HttpServlet {

   
   

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
        
        LoteEntrada loteEntrada = RepositorioLoteEntrada.getCurrentInstance().read(codigo);
        
        String responseJSON = "{\"codigo\":"+loteEntrada.getCodigo()+","+
                "\"descricao\":\""+loteEntrada.getDescricao()+"\",\"itens\":[";
        for(ItemEntrada item : loteEntrada.getItens()){
            responseJSON += "{\"codigo\":"+item.getCodigo()+",\"nomeProduto\":\""+item.getProduto().getNome()+"\"}";
            if(loteEntrada.getItens().indexOf(item) != loteEntrada.getItens().size()-1){
               responseJSON += ",";
            }
        }
        responseJSON += "]}";
        
        response.setContentType("text/plain");
        try(PrintWriter out = response.getWriter()){
            out.println(responseJSON);
        }
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
        
        HttpSession session = request.getSession();
        LoteEntrada LE = (LoteEntrada)session.getAttribute("loteEntrada");
        
        for(ItemEntrada i : LE.getItens()){
            if(i.getQuantidade() > 10){
                session.setAttribute("msg", "Voce está tentando inserir mais de 10 items do produto "+ i.getProduto().getNome()+" No lote");
                response.sendError(500);
                
                return;
            }
        }
        Estoque estoque = RepositorioEstoque.getCurrentInstance().read();
        
        for(ItemEntrada i : LE.getItens()){
            for(ItemEstoque ie : estoque.getItens()){
               if(i.getProduto().getCodigo() == ie.getProduto().getCodigo()){
                   ie.adiciona(i.getQuantidade());
                   break;
               }
            }
        }
        RepositorioLoteEntrada.getCurrentInstance().create(LE);
        
        
        session.removeAttribute("loteEntrada");
        
        session.setAttribute("msg", "O lote de Entrada foi inserido com sucesso");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp); //To change body of generated methods, choose Tools | Templates.
    
        String operacao = req.getParameter("operacao");
        int codigo = Integer.parseInt(req.getParameter("codigo"));
        HttpSession session = req.getSession(); 
        
        LoteEntrada LE = (LoteEntrada)session.getAttribute("loteEntrada");
        
        if(LE == null){
            
            LE = new LoteEntrada();
            session.setAttribute("loteEntrada", LE);
        }
        
        if(operacao.equals("mais")){
        
        boolean controle = false;
        
        for(ItemEntrada i : LE.getItens()){
            if(i.getProduto().getCodigo() == codigo){
                i.setQuantidade(i.getQuantidade()+1);
                controle = true;
                 session.setAttribute("msg", "O produto foi Incrementado no lote"); 
                break; 
            }
        }
    
        if(!controle){
            ItemEntrada item = new ItemEntrada();
            
            Produto p = RepositorioProdutos.getCurrentInstance().read(codigo);
            item.setProduto(p);
            item.setCodigo((int)(Math.random() * 10000));
            item.setQuantidade(1);
            
            LE.addItem(item);
            
            session.setAttribute("msg", "O produto "+p.getNome()+"Foi inserido no lote ");
        }else{
            session.setAttribute("msg", "O produto foi Incrementado no lote");
        }
        }else if(operacao.equals("menos")) {
            for(ItemEntrada i : LE.getItens()){
                if(i.getProduto().getCodigo() == codigo){
                    
                    if(i.getQuantidade() == 1 ){
                        
                        LE.getItens().remove(i);
                        if(LE.getItens().size() == 0){
                            session.removeAttribute("loteEntrada");
                        }
                        session.setAttribute("msg", "O produto "+i.getProduto().getNome()+"Foi Removido no lote ");
                        break;
                    }
                    
                    i.setQuantidade(i.getQuantidade() - 1 );
                    break;
                }
            }
        }
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
