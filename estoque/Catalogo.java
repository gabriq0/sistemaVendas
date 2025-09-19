import clientes.Cliente;
import listas.Lista;
public class Catalogo {
    private Lista<NovoItem> catalogo = new Lista<NovoItem>();

    public void adicionarItem(Produto produto, float qtd){
        NovoItem auxiliar = new NovoItem(produto, 0);
        NovoItem itemExiste = this.catalogo.checarLista(auxiliar);  
        //cria dois itens para comparar/checar se o novoProduto recebido pelo método, já existe na lista.

        if(itemExiste != null){
            float valorDoProduto = itemExiste.getQtd(); //se o produto já está na lista, ele vai somar a nova quantidade adicionada com a anterior.
            itemExiste.setQtd(valorDoProduto + qtd);
        }
        else{
            NovoItem novoProduto = new NovoItem(produto, qtd); //caso não esteja no catálogo, ele será adicionado criando um novo bloco para ele.
            this.catalogo.insereFinal(novoProduto);
        }
    }

    public void retirarProdutoNoCatalogo(Produto produtoAlvo){
        NovoItem alvo = new NovoItem(produtoAlvo, 0);
        this.catalogo.excluirItem(alvo); 
        //esse método serve apenas para retirar o produto inteiramente. ele não vai retirar um qtd específica, por exemplo.
    }

    @Override
    public String toString() {
        return this.catalogo.toString();
    }

}
