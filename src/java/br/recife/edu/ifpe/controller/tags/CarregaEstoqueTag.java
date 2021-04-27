
package br.recife.edu.ifpe.controller.tags;

import br.recife.edu.ifpe.model.classes.Estoque;
import br.recife.edu.ifpe.model.repositorios.RepositorioEstoque;
import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;


public class CarregaEstoqueTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        super.doTag(); //To change body of generated methods, choose Tools | Templates.
    
        Estoque e = RepositorioEstoque.getCurrentInstance().read();
        
        getJspContext().setAttribute("estoque", e, PageContext.PAGE_SCOPE);
    
    }
    
}
