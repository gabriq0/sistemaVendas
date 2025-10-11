package producao;
public class Insumo {
    private String idInsumo;
    private String nome;
    private String medida;

    public Insumo(String idInsumo, String nome, String medida){
        this.idInsumo = idInsumo;
        this.nome = nome;
        this.medida = medida;
    }

    public void setIdInsumo(String idInsumo) { this.idInsumo = idInsumo; }
    public void setNome(String nome) { this.nome = nome; }
    public void setMedida(String medida) { this.medida = medida;}

    public String getIdInsumo() { return idInsumo; }
    public String getMedida() { return medida; }
    public String getNome() { return nome; }

    @Override
    public String toString() {
        return "Insumo: " + this.nome + " (" + this.idInsumo + ")";
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (objeto == null) return false;
        
        Insumo itemInsumo = (Insumo) objeto;
        return this.idInsumo.equals(itemInsumo.idInsumo);
    }

}
