
package br.recife.edu.ifpe.controller.tags;

import br.recife.edu.ifpe.model.classes.LoteSaida;
import br.recife.edu.ifpe.model.repositorios.RepositorioLoteSaida;
import java.io.IOException;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;


public class CarregaLoteSaidaTag extends SimpleTagSupport{

    @Override
    public void doTag() throws JspException, IOException {
        super.doTag(); //To change body of generated methods, choose Tools | Templates.
        
        List<LoteSaida> loteSaida = RepositorioLoteSaida.getCurrentInstance().readAll();
        
        getJspContext().setAttribute("lotesSaidaInseridos", loteSaida, PageContext.PAGE_SCOPE);
    }
    
}
