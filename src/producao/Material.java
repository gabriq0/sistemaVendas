package producao;
public class Material {
    private String idMaterial;
    private String nome;
    private String medida;

    public Material(String idMaterial, String nome, String medida){
        this.idMaterial = idMaterial;
        this.nome = nome;
        this.medida = medida;
    }

    public void setIdMaterial(String idMaterial) { this.idMaterial = idMaterial; }
    public void setNome(String nome) { this.nome = nome; }
    public void setMedida(String medida) { this.medida = medida;}

    public String getIdMaterial() { return idMaterial; }
    public String getMedida() { return medida; }
    public String getNome() { return nome; }

    @Override
    public String toString() {
        return this.nome + " (" + this.idMaterial + ")";
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (objeto == null) return false;
        
        Material itemMaterial = (Material) objeto;
        return this.idMaterial.equals(itemMaterial.idMaterial);
    }

}
