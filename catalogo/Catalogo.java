package catalogo;

import listas.Lista;

public class Catalogo {
    private Lista<ItemEstoque> catalogo = new Lista<ItemEstoque>();

    public void adicionarProduto(Produto produto, int quantidadeEstoque){
        ItemEstoque auxiliar = new ItemEstoque(produto, 0);
        ItemEstoque itemExiste = this.catalogo.compararItens(auxiliar);  
        //cria dois itens para comparar/checar se o novoProduto recebido pelo método, já existe na lista.

        if(itemExiste != null){
            int quantidadeAnterior = itemExiste.getQuantidadeEstoque(); //se o produto já está na lista, ele vai somar a nova quantidade adicionada com a anterior.
            itemExiste.setQuantidadeEstoque(quantidadeAnterior + quantidadeEstoque);
        }
        else{
            ItemEstoque novoProduto = new ItemEstoque(produto, quantidadeEstoque); //caso não esteja no catálogo, ele será adicionado criando um novo bloco para ele.
            this.catalogo.insereFinal(novoProduto);
        }
    }

    public void excluirProduto(Produto produtoAlvo){
        ItemEstoque alvo = new ItemEstoque(produtoAlvo, 0);
        this.catalogo.excluirItem(alvo); 
        //esse método serve apenas para retirar o produto inteiramente. ele não vai retirar um qtd específica, por exemplo.
    }

    public void mostrarCatalogo(){
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

    public void retirarUnidade(Produto produtoAlvo, int quantidadeParaReduzir){
        ItemEstoque auxiliar = new ItemEstoque(produtoAlvo, 0);
        ItemEstoque itemExiste = this.catalogo.compararItens(auxiliar);

        if(itemExiste != null){
            int quantidadeAnterior = itemExiste.getQuantidadeEstoque();
            int reducao = quantidadeAnterior - quantidadeParaReduzir;
            if(reducao < 0) reducao = 0;
            itemExiste.setQuantidadeEstoque(reducao);
        }
    }

    @Override
    public String toString() {
        return this.catalogo.toString();
    }
}
