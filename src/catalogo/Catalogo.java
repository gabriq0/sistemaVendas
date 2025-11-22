package catalogo;
import excecoes.EstoqueInsuficienteException;
import listas.Lista;

public class Catalogo {
    private Lista<ItemEstoque> catalogo = new Lista<ItemEstoque>();

    public boolean adicionarProduto(Produto produto, int quantidadeEstoque){
        ItemEstoque auxiliar = new ItemEstoque(produto, 0);
        ItemEstoque itemExiste = this.catalogo.compararItens(auxiliar);  
        //cria dois itens para comparar/checar se o novoProduto recebido pelo método, já existe na lista.

        if(itemExiste != null){
            int quantidadeAnterior = itemExiste.getQuantidadeEstoque(); //se o produto já está na lista, ele vai somar a nova quantidade adicionada com a anterior.
            itemExiste.setQuantidadeEstoque(quantidadeAnterior + quantidadeEstoque);
            return true;
        }
        else{
            ItemEstoque novoProduto = new ItemEstoque(produto, quantidadeEstoque); //caso não esteja no catálogo, ele será adicionado criando um novo bloco para ele.
            this.catalogo.insereFinal(novoProduto);
            return true;
        }
    }

    public void excluirProduto(Produto produtoAlvo){
        ItemEstoque alvo = new ItemEstoque(produtoAlvo, 0);
        this.catalogo.excluirItem(alvo); 
        //esse método serve apenas para retirar o produto inteiramente. ele não vai retirar um qtd específica, por exemplo.
    }

    public void mostrarCatalogo(){
        if(this.catalogo.listaVazia()) System.out.println("catalogo de produtos vazio!!");
        System.out.println(this.catalogo.toString());
    }

    public int somarEstoqueTotal(){
        int total = 0;
        for(int i = 0; i < this.catalogo.tamanhoLista(); i++){
            ItemEstoque temp = this.catalogo.pegarBloco(i);
            total += temp.getQuantidadeEstoque();
        }
        return total;
    }

    public void reduzirAjusteManual(Produto produtoAlvo, int quantidadeParaReduzir){
        ItemEstoque auxiliar = new ItemEstoque(produtoAlvo, 0);
        ItemEstoque itemExiste = this.catalogo.compararItens(auxiliar);

        if(itemExiste != null){
            int quantidadeAnterior = itemExiste.getQuantidadeEstoque();
            int reducao = quantidadeAnterior - quantidadeParaReduzir;
            if(reducao < 0) reducao = 0;
            itemExiste.setQuantidadeEstoque(reducao);
        }
        //esse método é para apenas retirar uma quantidade desejada de um item no estoque.
    }

    public void reduzirParaVenda(Produto produtoAlvo, int quantidadeParaReduzir) throws EstoqueInsuficienteException{
        int falta = verificarFalta(produtoAlvo, quantidadeParaReduzir);
        if(falta > 0) throw new EstoqueInsuficienteException(produtoAlvo, falta); 
        //se o item está faltando não dá pra reduzir.

        ItemEstoque auxiliar = new ItemEstoque(produtoAlvo, 0);
        ItemEstoque itemExiste = this.catalogo.compararItens(auxiliar);
    
        if (itemExiste != null) {
            int quantidadeAnterior = itemExiste.getQuantidadeEstoque();
            itemExiste.setQuantidadeEstoque(quantidadeAnterior - quantidadeParaReduzir);
        }
    }

    public int verificarFalta(Produto produtoAlvo, int quantidadeDesejada){
        ItemEstoque auxiliar = new ItemEstoque(produtoAlvo, 0);
        ItemEstoque itemExiste = this.catalogo.compararItens(auxiliar);

        if(itemExiste != null){
            int quantidadeAtual = itemExiste.getQuantidadeEstoque();
            
            if(quantidadeAtual >= quantidadeDesejada) return 0; //retorna zero, pois não está em falta.
            else return quantidadeDesejada - quantidadeAtual; //retorna quantos itens faltam para chegar a qtd desejada.
        }

        return quantidadeDesejada; //se o item não existe, falta tudo. 
    }

    public int getTamanhoCatalogo() { return this.catalogo.tamanhoLista(); }

    @Override
    public String toString() {
        return this.catalogo.toString();
    }
}
