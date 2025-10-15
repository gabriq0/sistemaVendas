package producao;
import listas.*;

public class EstoqueMaterial {
    private Lista<ItemEstoqueMaterial> estoque = new Lista<ItemEstoqueMaterial>();

    public void adicionarMaterial(Material material, double quantidade){
        ItemEstoqueMaterial novoMaterial = new ItemEstoqueMaterial(material, quantidade);
        ItemEstoqueMaterial itemExiste = this.estoque.compararItens(novoMaterial);  

        if(itemExiste != null){
            double quantidadeAnterior = itemExiste.getQuantidade();
            itemExiste.setQuantidade(quantidadeAnterior + quantidade);
        }
        else this.estoque.insereFinal(novoMaterial);
    }
    
    public void mostrarEstoque(){
        if(this.estoque.listaVazia()) System.out.println("estoque de materiais vazio!!");
        System.out.println(this.estoque.toString());
    }

    //um pouco confuso, mas, essa é a quantidade do material. tipo: 500 ml de álcool
    public double getQuantidadeMaterial(Material material){
        ItemEstoqueMaterial itemParaBusca = new ItemEstoqueMaterial(material, 0);
        ItemEstoqueMaterial itemExistente = estoque.compararItens(itemParaBusca);
        
        if(itemExistente != null) return itemExistente.getQuantidade(); 
        else return 0;
    }

    //usar material significa essencialmente remover a quantidade usada dele no estoque!!
    public boolean usarMaterial(Material material, double quantidadeUsada){
        ItemEstoqueMaterial auxiliar = new ItemEstoqueMaterial(material, 0);
        ItemEstoqueMaterial itemExiste = this.estoque.compararItens(auxiliar);
    
        if (itemExiste != null) {
            double quantidadeAnterior = itemExiste.getQuantidade();
            itemExiste.setQuantidade(quantidadeAnterior - quantidadeUsada);
            return true;
        }
        return false;
    }

    public double verificarFalta(Material material, double quantidadeDesejada){
        ItemEstoqueMaterial auxiliar = new ItemEstoqueMaterial(material, 0);
        ItemEstoqueMaterial itemExiste = this.estoque.compararItens(auxiliar);

        if(itemExiste != null){
            double quantidadeAtual = itemExiste.getQuantidade();
            
            if(quantidadeAtual >= quantidadeDesejada) return 0; //retorna zero, pois não está em falta.
            else return quantidadeDesejada - quantidadeAtual; //retorna quantos itens faltam para chegar a qtd desejada.
        }

        return quantidadeDesejada; //se o item não existe, falta tudo. 
    }

    public int getTamanhoEstoque() { return this.estoque.tamanhoLista(); }

    @Override
    public String toString() {
        return this.estoque.toString();
    }
}
