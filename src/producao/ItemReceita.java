package producao;
public class ItemReceita{
    private Insumo insumo;
    private double quantidadeNecessaria;

    public ItemReceita(Insumo insumo, double quantidadeNecessaria){
        this.insumo = insumo;
        this.quantidadeNecessaria = quantidadeNecessaria;
    }

    public Insumo getInsumo() { return insumo; }
    public double getQuantidadeNecessaria() { return quantidadeNecessaria; }

    @Override
    public String toString() {
        return insumo.getNome() + " (quantidade: " + this.quantidadeNecessaria + " " + insumo.getMedida() + ")";
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (objeto == null) return false;
        
        ItemReceita itemBusca = (ItemReceita) objeto;
        return this.insumo.equals(itemBusca.insumo);
    }
}