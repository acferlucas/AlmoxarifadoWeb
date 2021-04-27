/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testadorProduto;

import br.recife.edu.ifpe.model.classes.Estoque;
import br.recife.edu.ifpe.model.classes.ItemEntrada;
import br.recife.edu.ifpe.model.classes.ItemEstoque;
import br.recife.edu.ifpe.model.classes.ItemSaida;
import br.recife.edu.ifpe.model.classes.LoteEntrada;
import br.recife.edu.ifpe.model.classes.LoteSaida;
import br.recife.edu.ifpe.model.classes.Produto;
import br.recife.edu.ifpe.model.repositorios.RepositorioEstoque;
import br.recife.edu.ifpe.model.repositorios.RepositorioLoteEntrada;
import br.recife.edu.ifpe.model.repositorios.RepositorioProdutos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acfers
 */
public class TestadorClasses {
    
    public static void main(String[] args) {
        
        Produto p = new Produto();
        Produto carro = new Produto();
       
        
        p.setCodigo(1);
        p.setNome("Corola");
        p.setMarca("Toyota");
        p.setCategoria("Veiculos");
        p.setDescricao("Carro muito bonito e elegante ");
        
       carro.setCodigo(2);
       carro.setNome("captur");
       carro.setMarca("Renor");
       carro.setCategoria("Carro");
       carro.setDescricao("Carro robusto");
        
        
        RepositorioProdutos.getCurrentInstance().create(p);
        RepositorioProdutos.getCurrentInstance().create(carro);
        
        ItemEntrada IE = new ItemEntrada();
        ItemEntrada IE2 = new ItemEntrada();
        
        IE.setCodigo(p.getCodigo());
        IE.setProduto(p);
        IE.setQuantidade(5);
        
        IE2.setCodigo(carro.getCodigo());
        IE2.setProduto(carro);
        IE2.setQuantidade(5);
        
        List<ItemEntrada> itens = new ArrayList<>();
        itens.add(IE);
        itens.add(IE2);
        
        LoteEntrada le = new LoteEntrada();
        
        le.setItens(itens);
        
        RepositorioLoteEntrada.getCurrentInstance().create(le);
       
        List<LoteEntrada> lotedeentrada = RepositorioLoteEntrada.getCurrentInstance().readAll();
        
        ItemEstoque IS = new ItemEstoque();
        ItemEstoque IS2 = new ItemEstoque();
        
        IS.setCodigo(IE.getCodigo());
        IS.setProduto(IE.getProduto());
        IS.setQuantidade(IE.getQuantidade());
        
        IS2.setCodigo(IE2.getCodigo());
        IS2.setProduto(IE2.getProduto());
        IS2.setQuantidade(IE2.getQuantidade());
        
        
        Estoque e = new Estoque();
        
        e.addItem(IS);
        e.addItem(IS2);
        
        RepositorioEstoque.getCurrentInstance().create(e);
        
        List<ItemEstoque> meuEstoque = e.getItens();
        
        for(ItemEstoque estoque : meuEstoque){
            
            System.out.print(" :"+estoque.getCodigo());   
            System.out.print(" :"+estoque.getProduto().getNome());
            System.out.println(" :"+estoque.getQuantidade());
        }
        
       // ItemSaida i = new ItemSaida();
       // i.setProduto(e.get)
        
        //LoteSaida ls = new LoteSaida();
        //ls.addItem(i);
        
    }
    
}
