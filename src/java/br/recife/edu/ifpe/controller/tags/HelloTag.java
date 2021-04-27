
package br.recife.edu.ifpe.controller.tags;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;


public class HelloTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        super.doTag(); //To change body of generated methods, choose Tools | Templates.
        
        getJspContext().getOut().write("<h1>Olá mundo</h1>");
    
        }
     
}
