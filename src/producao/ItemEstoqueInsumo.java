package producao;

public class ItemEstoqueInsumo {
    private Insumo insumo;
    private double quantidade;

    public ItemEstoqueInsumo(Insumo insumo, double quantidade) {
        this.insumo = insumo;
        this.quantidade = quantidade;
    }

    public Insumo getInsumo() { return insumo; }
    public double getQuantidade() { return quantidade; }
    public void setQuantidade(double quantidade) { this.quantidade = quantidade; }

    @Override
    public String toString() {
        return insumo.getNome() + "(quantidade: " +  this.quantidade + " " + insumo.getMedida() + ")";
    }
        
    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (objeto == null) return false;
        
        ItemEstoqueInsumo itemBusca = (ItemEstoqueInsumo) objeto;
        return insumo.equals(itemBusca.insumo);
    }
}
