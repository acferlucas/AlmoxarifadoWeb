/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.recife.edu.ifpe.model.repositorios;

import br.recife.edu.ifpe.model.classes.Estoque;
import br.recife.edu.ifpe.model.classes.ItemEstoque;
import java.util.ArrayList;
import java.util.List;

public class RepositorioEstoque {
    
    private static RepositorioEstoque myself = null;
    
    private List<Estoque> estoque = null;
    
    private RepositorioEstoque(){
        this.estoque = new ArrayList<>();
        this.estoque.add(new Estoque());
    }
    
    public static RepositorioEstoque getCurrentInstance(){
        if(myself == null)
            myself = new RepositorioEstoque();
        
        return myself;
    }
    
    public void create(Estoque e){
        this.estoque.add(e);
    }
    
    public Estoque read(){
        return this.estoque.get(0);
    }
    
    public void update(int codigoProduto,int quantidade){
        for(ItemEstoque i: this.estoque.get(0).getItens()){
            if(i.getProduto().getCodigo() == codigoProduto){
                
                i.setQuantidade(quantidade);
                return;
                
            }
        }
    }
    
}
