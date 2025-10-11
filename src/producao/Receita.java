package producao;
import listas.Lista;

public class Receita {
    private Lista<ItemReceita> itensdaReceita = new Lista<ItemReceita>();

    public void adicionarIngredienteReceita(Insumo insumo, double quantidadeNecessaria){
        ItemReceita novoInsumo = new ItemReceita(insumo, quantidadeNecessaria);
        ItemReceita itemExiste = this.itensdaReceita.compararItens(novoInsumo);  

        if(itemExiste != null){
            this.itensdaReceita.excluirItem(itemExiste);
            this.itensdaReceita.insereFinal(novoInsumo);
        }
        else this.itensdaReceita.insereFinal(novoInsumo);
    }

    public Lista<ItemReceita> getItensdaReceita() { return this.itensdaReceita; }

    @Override
    public String toString() {
        if (this.itensdaReceita.listaVazia()) return "Ficha técnica vazia.";
        return "--- Ficha Técnica ---\n" + this.itensdaReceita.toString() + "--------------------";
    }
}

