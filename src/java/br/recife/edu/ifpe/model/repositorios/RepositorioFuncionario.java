/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.recife.edu.ifpe.model.repositorios;

import br.recife.edu.ifpe.model.classes.Funcionario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author debora
 */
public class RepositorioFuncionario {
    private static RepositorioFuncionario myself = null;
    
    private List<Funcionario> funcionarios = null;
    
    private RepositorioFuncionario(){
        this.funcionarios = new ArrayList<>();
    }
    
    public static RepositorioFuncionario getCurrentInstance(){
        if(myself == null)
            myself = new RepositorioFuncionario();
        
        return myself;
    }
    
    public void create(Funcionario f){
        this.funcionarios.add(f);
    }
    
    public void update(Funcionario f){
        
        for(Funcionario aux: this.funcionarios){
            if(aux.getCodigo() == f.getCodigo()){
                
                aux.setDepartamento(f.getDepartamento());
                aux.setNome(f.getNome());
                return;
                
            }
        }
        
    }
    
    public Funcionario read(int codigo){
        for(Funcionario aux: this.funcionarios){
            if(aux.getCodigo() == codigo){
                return aux;
            }
        }
        return null;
    }
    
    public void delete(Funcionario f){
        this.funcionarios.remove(f);
    }
    
    public List<Funcionario> readAll(){
        return this.funcionarios;
    }
}
