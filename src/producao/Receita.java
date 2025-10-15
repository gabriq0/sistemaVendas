package producao;
import listas.Lista;

public class Receita {
    private Lista<ItemReceita> itensdaReceita = new Lista<ItemReceita>();

    public void adicionarIngredienteReceita(Material material, double quantidadeNecessaria){
        ItemReceita novoMaterial = new ItemReceita(material, quantidadeNecessaria);
        ItemReceita itemExiste = this.itensdaReceita.compararItens(novoMaterial);  

        if(itemExiste != null){
            this.itensdaReceita.excluirItem(itemExiste);
            this.itensdaReceita.insereFinal(novoMaterial);
            //nesse caso se a receita já existe e tal ingrediente está lá, o que isso faz é mudar a quantidade  
        }
        else this.itensdaReceita.insereFinal(novoMaterial);
    }

    public Lista<ItemReceita> getItensdaReceita() { return this.itensdaReceita; }

    @Override
    public String toString() {
        if (this.itensdaReceita.listaVazia()) return "sem informções na receita...";
        return "--- Receita ---\n" + this.itensdaReceita.toString() + "--------------------";
    }
}

