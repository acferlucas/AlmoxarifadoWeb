/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.recife.edu.ifpe.model.repositorios;

import br.recife.edu.ifpe.model.classes.Produto;
import java.util.ArrayList;
import java.util.List;

public class RepositorioProdutos {
    
    private static RepositorioProdutos myself = null;
    
    private List<Produto> produtos = null;
    
    private RepositorioProdutos(){
        this.produtos = new ArrayList<>();
    }
    
    public static RepositorioProdutos getCurrentInstance(){
        if(myself == null)
            myself = new RepositorioProdutos();
        
        return myself;
    }
    
    public void create(Produto p){
        this.produtos.add(p);
    }
    
    public void update(Produto p){
        
        for(Produto aux: this.produtos){
            if(aux.getCodigo() == p.getCodigo()){
                
                aux.setNome(p.getNome());
                aux.setMarca(p.getMarca());
                aux.setCategoria(p.getCategoria());
                aux.setDescricao(p.getDescricao());
                return;
                
            }
        }
        
    }
    
    public Produto read(int codigo){
        for(Produto aux: this.produtos){
            if(aux.getCodigo() == codigo){
                return aux;
            }
        }
        return null;
    }
    
    public void delete(Produto p){
        this.produtos.remove(p);
    }
    
    public List<Produto> readAll(){
        return this.produtos;
    }
    
}
