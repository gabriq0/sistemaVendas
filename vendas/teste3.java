package vendas;

import catalogo.Catalogo;
import catalogo.Produto;
import clientes.Cliente;
import listas.Lista;

public class teste3 {
    public static void main(String[] args) {
        Catalogo cat = new Catalogo();
        
        //criando produtos e colocando eles em catalogo:

        Produto perfume1 = new Produto("perfume jz", "p1", 100.0);
        Produto perfume2 = new Produto("perfume secreto", "p2", 220.0);
        Produto hidratante1 = new Produto("hidratante legal", "p3", 80.0);
        
        cat.adicionarProduto(perfume1, 10);
        cat.adicionarProduto(perfume2, 5);
        cat.adicionarProduto(hidratante1, 20);

        System.out.println("--- ESTOQUE INICIAL ---");
        cat.mostrarCatalogo();
        System.out.println("-----------------------");

        Lista<ItemVenda> carrinho = new Lista<>();
        
        //criando a instancia da classe de venda, enviando as informações e fazendo uma venda.

        Venda venda = new Venda(new Cliente("pepe", "10", "brasil"), cat, carrinho);

        //adicionando itens à venda, isso é o equivalente a colocar um item no carrinho de compras.

        venda.adicionarItem(perfume1, 4);
        venda.adicionarItem(hidratante1, 3);
        
        //finaliza a venda e recebe a forma que o cliente usa pra pagar.

        boolean sucesso = venda.finalizarVenda("dinheiro");

        if (sucesso) {
            System.out.println("\n--- ESTOQUE APÓS A VENDA ---");
            cat.mostrarCatalogo();
            System.out.println("-----------------------------");
            //isso é apenas para mostrar a diferença de itens removidos no estoque neste exemplo.
        }
    }
}