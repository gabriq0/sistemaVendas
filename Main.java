import catalogo.*;
import clientes.*;
import listas.*;
import vendas.*;
import excecoes.*;

public class Main {
    public static void main(String[] args) {
        Sistema sistema = Sistema.getInstance();
        Catalogo catalogoVendedor = sistema.getCatalogoVendedor();
        TabelaPreco tabelaVendedor = sistema.getTabelaVendedor();

        Produto perfume = new Produto("perfume de banana", "p01");
        tabelaVendedor.definirPreco(perfume, 10.00);
        catalogoVendedor.adicionarProduto(perfume, 20); // Temos 20 no stock

        Cliente cliente1 = new Cliente("gabri", "gabri@yahoo.com", "end");
        Lista<ItemVenda> carrinho1 = new Lista<>();
        Venda venda1 = new Venda(cliente1, catalogoVendedor, carrinho1, tabelaVendedor);
        
        try{
            venda1.adicionarItem(perfume, 25); 
        } catch(QuantidadeInvalidaException e){
            System.out.println(e.getMessage());
        } catch(ProdutoSemPrecoException e){
            System.out.println(e.getMessage());
        }

        System.out.println("--- Tentando finalizar venda ---");

        try {
            venda1.finalizarVenda("dinheiro");
            System.out.println("sucesso"); 
        } catch (EstoqueInsuficienteException e) {
            System.out.println("ERRO NA VENDA: " + e.getMessage());
        } catch (CarrinhoVazioException e){
            System.out.println(e.getMessage());
        }
    }
}