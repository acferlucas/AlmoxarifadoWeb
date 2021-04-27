/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.recife.edu.ifpe.model.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoteSaida {
    
    private int codigo;
    private List<ItemSaida> itens;
    private Date data;
    private Funcionario responsavel;
            
    /*
    * Neste campo ficarão armazenadas as informações de documentos
    */
    private String descricao;
    
    public LoteSaida(){
        this.itens = new ArrayList<>();
        this.data = new Date();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public List<ItemSaida> getItens() {
        return itens;
    }

    public void setItens(List<ItemSaida> itens) {
        this.itens = itens;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public void addItem(ItemSaida i){
        this.itens.add(i);
    }

    public Funcionario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Funcionario responsavel) {
        this.responsavel = responsavel;
    }
    
}
