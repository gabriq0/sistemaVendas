package catalogo;
import producao.*;

public class Produto {
    private String nome;
    private String idProduto;
    private double valordeVenda;
    private Receita receita;

    public Produto(String nome, String idProduto, double valordeVenda){
        this.nome = nome;
        this.idProduto = idProduto;
        this.valordeVenda = valordeVenda;
        this.receita = null; //esse construtor é apenas para produtos comprados / revendidos. não precisa de receita
    }

    public Produto(String nome, String idProduto, double valordeVenda, Receita receita){
        this.nome = nome;
        this.idProduto = idProduto;
        this.valordeVenda = valordeVenda;
        this.receita = receita; //esse construtor é para os itens que serão produzidos, precisa de receita para criá-los.
    }
    
    public String getNome(){ return this.nome; }
    public String getIdProduto() { return this.idProduto; }
    public double getValordeVenda() { return this.valordeVenda; }
    public Receita getReceita() { return receita; }

    @Override
    public String toString(){ 
        return this.nome; 
    }

    @Override
    public boolean equals(Object objeto){
        if(this == objeto) return true;
        if(objeto == null) return false;

        Produto produtoBusca = (Produto) objeto;
        return this.idProduto.equals(produtoBusca.idProduto);
    }
}

