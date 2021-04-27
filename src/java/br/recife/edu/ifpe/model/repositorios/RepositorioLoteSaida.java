/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.recife.edu.ifpe.model.repositorios;

import br.recife.edu.ifpe.model.classes.LoteSaida;
import java.util.ArrayList;
import java.util.List;

public class RepositorioLoteSaida {
    
    private static RepositorioLoteSaida myself = null;
    
    private List<LoteSaida> lotes = null;
    
    private RepositorioLoteSaida(){
        this.lotes = new ArrayList<>();
    }
    
    public static RepositorioLoteSaida getCurrentInstance(){
        if(myself == null)
            myself = new RepositorioLoteSaida();
        
        return myself;
    }
    
    public void create(LoteSaida ls){
        this.lotes.add(ls);
    }
    
    public void update(LoteSaida ls){
        
        for(LoteSaida aux: this.lotes){
            if(aux.getCodigo() == ls.getCodigo()){
                
                aux.setDescricao(ls.getDescricao());
                return;
                
            }
        }
        
    }
    
    public LoteSaida read(int codigo){
        for(LoteSaida aux: this.lotes){
            if(aux.getCodigo() == codigo){
                return aux;
            }
        }
        return null;
    }
    
    public void delete(LoteSaida ls){
        this.lotes.remove(ls);
    }
    
    public List<LoteSaida> readAll(){
        return this.lotes;
    }
    
}
