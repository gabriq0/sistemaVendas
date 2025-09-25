import catalogo.*;
import vendas.*;
import clientes.Cliente;
import listas.Lista;

public class teste {
    public static void main(String[] args) {
        Catalogo catalogo = new Catalogo();
        Entrega servicoAtendimento = new Entrega(catalogo);

     // 1. Setup de Produtos e Estoque
        Produto perfume1 = new Produto("perfume legal","p1", 120.0);
        Produto perfume2 = new Produto("perfume crime", "p2", 70.5);
        Produto perfume3 = new Produto("perfume secreto", "p3", 12.7);
        Produto hidratante1 = new Produto("hidra-tante", "p4", 49.5);
        Produto creme1 = new Produto("creme de cabelo", "p5", 20.0);
        
        catalogo.adicionarProduto(perfume1, 10); 
        catalogo.adicionarProduto(perfume2, 10);
        catalogo.adicionarProduto(perfume3, 5);
        catalogo.adicionarProduto(hidratante1, 3);
        catalogo.adicionarProduto(creme1, 8);   
        
        System.out.println("--- ESTOQUE INICIAL ---");
        catalogo.mostrarCatalogo();
        System.out.println("-----------------------");

        // 2. Preparação da Venda
        Cliente cliente = new Cliente("ana costa", "ana@email.com", "brasil");
        Lista<ItemVenda> carrinho = new Lista<>();
        
        // Criação da Venda: passamos o Catálogo, o Carrinho e a lista de Pedidos Pendentes
        Venda vendaDaAna = new Venda(cliente, catalogo, carrinho);
        
        System.out.println("\n--- ITENS ADICIONADOS AO CARRINHO ---");
        
        vendaDaAna.adicionarItem(hidratante1, 5); //vai ficar faltando, a compra ficará pendente.
        vendaDaAna.adicionarItem(perfume1, 2);
        vendaDaAna.adicionarItem(creme1, 1);
        
        System.out.printf("total: R$ " + vendaDaAna.getTotal()); 
        
        // 3. CHAMADA CRUCIAL: Finalizando a Venda
        System.out.println("\n--- INICIANDO FINALIZAÇÃO DE VENDA ---");
        
        // AQUI ESTÁ A CHAMADA: Passamos o ServicoAtendimento como argumento.
        vendaDaAna.finalizarVenda("PIX", servicoAtendimento);
        
        System.out.println("\n--- ESTOQUE FINAL ---");
        catalogo.mostrarCatalogo();
        System.out.println("-----------------------");
    }
}
