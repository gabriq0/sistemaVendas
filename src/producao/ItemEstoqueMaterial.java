package producao;

public class ItemEstoqueMaterial {
    private Material material;
    private double quantidade;

    public ItemEstoqueMaterial(Material material, double quantidade) {
        this.material = material;
        this.quantidade = quantidade;
    }

    public Material getMaterial() { return this.material; }
    public double getQuantidade() { return this.quantidade; }
    public void setQuantidade(double quantidade) { this.quantidade = quantidade; }

    @Override
    public String toString() {
        return material.getNome() + "(quantidade: " +  this.quantidade + " " + material.getMedida() + ")";
    }
        
    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (objeto == null) return false;
        
        ItemEstoqueMaterial itemBusca = (ItemEstoqueMaterial) objeto;
        return this.material.equals(itemBusca.material);
    }
}
