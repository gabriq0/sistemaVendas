package catalogo;
import producao.*;

public class Produto {
    private String nome;
    private String idProduto;
    private Receita receita;

    public Produto(String nome, String idProduto){
        this.nome = nome;
        this.idProduto = idProduto;
        this.receita = null; 
        //esse construtor é apenas para produtos que não precisam de receita.
    }
    public Produto(String nome, String idProduto, Receita receita){
        this.nome = nome;
        this.idProduto = idProduto;
        this.receita = receita; 
        //esse construtor é para os itens que serão produzidos, precisa de receita para criá-los.
    }
    
    public String getNome(){ return this.nome; }
    public String getIdProduto() { return this.idProduto; }
    public Receita getReceita() { return receita; }

    @Override
    public String toString(){ return this.nome; }

    @Override
    public boolean equals(Object objeto){
        if(this == objeto) return true;
        if(objeto == null) return false;

        Produto produtoBusca = (Produto) objeto;
        return this.idProduto.equals(produtoBusca.idProduto);
    }
}

