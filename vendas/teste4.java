package vendas;

import catalogo.Catalogo;
import catalogo.Produto;
import clientes.Cliente;
import listas.Lista;

public class teste4 {
    public static void main(String[] args) {
        Catalogo meuCatalogo = new Catalogo();
        Entrega servicoAtendimento = new Entrega(meuCatalogo);

     // 1. Setup de Produtos e Estoque
        Produto tenis = new Produto("Tênis Esportivo","1", 350.0);
        Produto bota = new Produto("Bota de Couro", "2",450.0);
        
        meuCatalogo.adicionarProduto(tenis, 10); // 10 em estoque
        meuCatalogo.adicionarProduto(bota, 2);   // 2 em estoque
        
        System.out.println("--- ESTOQUE INICIAL ---");
        meuCatalogo.mostrarCatalogo();
        System.out.println("-----------------------");

        // 2. Preparação da Venda
        Cliente clienteDaVez = new Cliente("Ana Costa", "ana@email.com", "8888-7777");
        Lista<ItemVenda> carrinho = new Lista<>();
        
        // Criação da Venda: passamos o Catálogo, o Carrinho e a lista de Pedidos Pendentes
        Venda vendaDaAna = new Venda(clienteDaVez,meuCatalogo, carrinho);
        
        System.out.println("\n--- ITENS ADICIONADOS AO CARRINHO ---");
        vendaDaAna.adicionarItem(tenis, 1);     // Em estoque
        vendaDaAna.adicionarItem(bota, 3);      // Em falta (precisa de 1, pois só tem 2)
        
        System.out.printf("Total da Venda: R$ %.2f\n", vendaDaAna.getTotal()); 
        
        // 3. CHAMADA CRUCIAL: Finalizando a Venda
        System.out.println("\n--- INICIANDO FINALIZAÇÃO DE VENDA ---");
        
        // AQUI ESTÁ A CHAMADA: Passamos o ServicoAtendimento como argumento.
        vendaDaAna.finalizarVenda("PIX", servicoAtendimento);
        
        System.out.println("\n--- ESTOQUE FINAL ---");
        meuCatalogo.mostrarCatalogo();
        System.out.println("-----------------------");
    }
}
