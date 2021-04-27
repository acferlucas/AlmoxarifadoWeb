
package br.recife.edu.ifpe.controller.tags;

import br.recife.edu.ifpe.model.classes.Produto;
import br.recife.edu.ifpe.model.repositorios.RepositorioProdutos;
import java.io.IOException;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class CarregaProdutosTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        super.doTag(); //To change body of generated methods, choose Tools | Templates.
        
        List<Produto> produtos = RepositorioProdutos.getCurrentInstance().readAll();
        
        getJspContext().setAttribute("produtos", produtos,PageContext.PAGE_SCOPE);
    }
    
    
    
}
