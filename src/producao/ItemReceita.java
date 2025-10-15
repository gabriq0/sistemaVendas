package producao;
public class ItemReceita{
    private Material material;
    private double quantidadeNecessaria;

    public ItemReceita(Material material, double quantidadeNecessaria){
        this.material = material;
        this.quantidadeNecessaria = quantidadeNecessaria;
    }

    public Material getMaterial() { return material; }
    public double getQuantidadeNecessaria() { return quantidadeNecessaria; }

    @Override
    public String toString() {
        return material.getNome() + " (quantidade: " + this.quantidadeNecessaria + " " + material.getMedida() + ")";
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (objeto == null) return false;
        
        ItemReceita itemBusca = (ItemReceita) objeto;
        return this.material.equals(itemBusca.material);
    }
}