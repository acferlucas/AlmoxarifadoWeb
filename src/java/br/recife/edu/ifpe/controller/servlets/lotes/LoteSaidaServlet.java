
package br.recife.edu.ifpe.controller.servlets.lotes;

import br.recife.edu.ifpe.model.classes.Estoque;
import br.recife.edu.ifpe.model.classes.Funcionario;
import br.recife.edu.ifpe.model.classes.ItemEstoque;
import br.recife.edu.ifpe.model.classes.ItemSaida;
import br.recife.edu.ifpe.model.classes.LoteSaida;
import br.recife.edu.ifpe.model.classes.Produto;
import br.recife.edu.ifpe.model.repositorios.RepositorioEstoque;
import br.recife.edu.ifpe.model.repositorios.RepositorioLoteSaida;
import br.recife.edu.ifpe.model.repositorios.RepositorioProdutos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "LoteSaidaServlet", urlPatterns = {"/LoteSaidaServlet"})
public class LoteSaidaServlet extends HttpServlet {

    

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

        LoteSaida loteSaida = RepositorioLoteSaida.getCurrentInstance().read(codigo);

        String responseJSON = "{\"codigo\":" + loteSaida.getCodigo() + ","
                + "\"descricao\":\"" + loteSaida.getDescricao() + "\",\"itens\":[";
        for (ItemSaida item : loteSaida.getItens()) {
            responseJSON += "{\"codigo\":" + item.getCodigo() + ",\"nomeProduto\":\"" + item.getProduto().getNome() + "\""
                    + ",\"quantidade\":" + item.getQuantidade() + "}";
            if (loteSaida.getItens().indexOf(item) != loteSaida.getItens().size() - 1) {
                responseJSON += ",";
            }
        }
        responseJSON += "]}";

        response.setContentType("text/plain");

        try (PrintWriter out = response.getWriter()) {
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

        LoteSaida ls = (LoteSaida) session.getAttribute("loteSaida");
        
        Estoque estoque = RepositorioEstoque.getCurrentInstance().read();

        for (ItemSaida i : ls.getItens()) {
            for (ItemEstoque ie : estoque.getItens()) {
                
                //Adicionado mais um IF pra verificar compatibilidade de produtos (manipulado VS estoque)
                if (i.getProduto() == ie.getProduto()) {
                    
                    //Se for o mesmo produto, compara se a quantidade subtraida é menor que o que há em estoque)
                    if (i.getQuantidade() > ie.getQuantidade()) {
                        session.setAttribute("msg", "Não é possível remover mais do que há no estoque.");
                        response.sendError(500);
                        return;
                    }
                }
            }
        }
        for (ItemSaida i : ls.getItens()) {
            for (ItemEstoque is : estoque.getItens()) {
                if (i.getProduto().getCodigo() == is.getProduto().getCodigo()) {
                    is.subtrai(i.getQuantidade());
                    break;
                }
            }
        }

        session.setAttribute("msg", "O lote de saida foi inserido com sucesso.");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPut(request, response);

        String operacao = request.getParameter("operacao");

        int codigo = Integer.parseInt(request.getParameter("codigo"));
        
        int quantidade = 1; //(int) (Math.random()*10000);
        
        boolean controle = false;
        
        HttpSession session = request.getSession();
        
        LoteSaida ls = (LoteSaida) session.getAttribute("loteSaida");

        if (ls == null) {
            ls = new LoteSaida();

            session.setAttribute("loteSaida", ls);
        }

        if (operacao.equals("mais")) {

            
            for (ItemSaida i : ls.getItens()) {
                if (i.getProduto().getCodigo() == codigo) {
                    i.setQuantidade(i.getQuantidade() + quantidade);
                    controle = true;
                    session.setAttribute("msg", "O produto foi incrementado no lote.");
                    break;
                }
            }

            if (!controle) {
                ItemSaida item = new ItemSaida();

                Produto p = RepositorioProdutos.getCurrentInstance().read(codigo);

                item.setProduto(p);
                item.setCodigo(p.getCodigo());
                item.setQuantidade(1);

                ls.addItem(item);

                session.setAttribute("msg", "O produto " + p.getNome() + " foi inserido no lote.");
            }
        } else if (operacao.equals("menos")) {

            for (ItemSaida i : ls.getItens()) {
                if (i.getProduto().getCodigo() == codigo) {
                    if (i.getQuantidade() == 1) {
                        ls.getItens().remove(i);
                        if (ls.getItens().size() == 0) {
                            session.removeAttribute("loteSaida");
                        }
                        session.setAttribute("msg", "O produto " + i.getProduto().getNome() + " foi removido do lote.");
                        break;
                    }

                    i.setQuantidade(i.getQuantidade() - 1);
                    break;
                }
            }
        }else if (operacao.equals("neutra")) { //ELSE IF adicionado para poder tratar o registro de funcionario responsável
            
            //Busca todos os funcionários cadastrados
            List<Funcionario> listaDeFuncionarios = (List<Funcionario>) session.getAttribute("listaDeFuncionarios");
            
            //Para cada funcionário, verifica qual corresponde ao código enviado na requisição
            for (Funcionario f : listaDeFuncionarios) {       
                
                //Se algum for encontrado...
                if (f.getCodigo() == codigo) {
                    
                    //...ele será adicionado ao lote de saida
                    ls.setResponsavel(f);                    
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
