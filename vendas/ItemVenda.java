package vendas;
import catalogo.*;

public class ItemVenda {
    private Produto item;
    private int quantidade;
    private double subtotal;

    public ItemVenda(Produto item, int quantidade){
        this.item = item;
        this.quantidade = quantidade;
        this.subtotal = item.getValordeVenda() * quantidade;
    }

    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }

    public Produto getItem(){
        return this.item;
    }
    public int getQuantidade(){
        return this.quantidade;
    }
    public double getSubtotal(){
        return this.subtotal;
    }

    @Override
    public String toString(){
        return "nome: " + item.getNome() + "(" + getQuantidade() + ") | R$ " + getSubtotal();
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (objeto == null) return false;
        
        ItemVenda itemBusca = (ItemVenda) objeto;
        return this.item.equals(itemBusca.item);
    }
}
