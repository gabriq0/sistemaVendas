package producao;
import listas.*;

public class EstoqueInsumos {
    private Lista<ItemEstoqueInsumo> estoque = new Lista<ItemEstoqueInsumo>();

    public void adicionarInsumo(Insumo insumo, double quantidade){
        ItemEstoqueInsumo novoInsumo = new ItemEstoqueInsumo(insumo, quantidade);
        ItemEstoqueInsumo itemExiste = this.estoque.compararItens(novoInsumo);  

        if(itemExiste != null){
            double quantidadeAnterior = itemExiste.getQuantidade(); //se o produto já está na lista, ele vai somar a nova quantidade adicionada com a anterior.
            itemExiste.setQuantidade(quantidadeAnterior + quantidade);
        }
        else this.estoque.insereFinal(novoInsumo);
    }
    
    public void mostrarEstoque(){
        if(this.estoque.listaVazia()) System.out.println("estoque de insumos vazio!!");
        System.out.println(this.estoque.toString());
    }

    //um pouco confuso, mas, essa é a quantidade do material (insumo). tipo: 500 ml de álcool
    public double getQuantidadeInsumo(Insumo insumo){
        ItemEstoqueInsumo itemParaBusca = new ItemEstoqueInsumo(insumo, 0);
        ItemEstoqueInsumo itemExistente = estoque.compararItens(itemParaBusca);
        
        if(itemExistente != null) return itemExistente.getQuantidade(); 
        else return 0;
    }

    public boolean usarInsumos(Insumo insumo, double quantidadeUsada){
        ItemEstoqueInsumo auxiliar = new ItemEstoqueInsumo(insumo, 0);
        ItemEstoqueInsumo itemExiste = this.estoque.compararItens(auxiliar);
    
        if (itemExiste != null) {
            double quantidadeAnterior = itemExiste.getQuantidade();
            itemExiste.setQuantidade(quantidadeAnterior - quantidadeUsada);
            return true;
        }
        return false;
    }

    public double verificarFalta(Insumo insumo, double quantidadeDesejada){
        ItemEstoqueInsumo auxiliar = new ItemEstoqueInsumo(insumo, 0);
        ItemEstoqueInsumo itemExiste = this.estoque.compararItens(auxiliar);

        if(itemExiste != null){
            double quantidadeAtual = itemExiste.getQuantidade();
            
            if(quantidadeAtual >= quantidadeDesejada) return 0; //retorna zero, pois não está em falta.
            else return quantidadeDesejada - quantidadeAtual; //retorna quantos itens faltam para chegar a qtd desejada.
        }

        return quantidadeDesejada; //se o item não existe, falta tudo. 
    }
}
