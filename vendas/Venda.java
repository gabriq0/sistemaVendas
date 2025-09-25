package vendas;

import java.time.*;
import java.time.format.DateTimeFormatter;
import catalogo.*;
import clientes.*;
import listas.*;

public class Venda {
    private String idVenda;
    private String dataTransacao;
    private String formaPagamento;
    private Cliente cliente;
    private double total;
    private Catalogo catalogo;
    private Lista<ItemVenda> itensVendidos;

    public Venda(Cliente cliente, Catalogo catalogo, Lista<ItemVenda> itensVendidos){
        this.idVenda = "V-" + LocalDateTime.now();
        this.dataTransacao = getDataTransacao();
        this.cliente = cliente;
        this.itensVendidos = new Lista<ItemVenda>();
        this.total = 0.0;
        this.catalogo = catalogo;
        this.itensVendidos = itensVendidos;
    }

    public String getDataTransacao() {
        LocalDateTime horadeVenda = LocalDateTime.now();
        DateTimeFormatter estiloBrasil = DateTimeFormatter.ofPattern("HH:mm - dd/MM/yy");
        dataTransacao = horadeVenda.format(estiloBrasil);
        return dataTransacao; //essa formatação está aí, pois o formato original é bem estranho/confuso.
    }

    public String getIdVenda(){
        return this.idVenda;
    }

    public double getTotal(){
        return this.total;
    }

    public void adicionarItem(Produto produto, int quantidade) {
        ItemVenda item = new ItemVenda(produto, quantidade);
        this.itensVendidos.insereFinal(item);
        this.total += item.getSubtotal();
    }

    public void retirarItem(Produto produtoAlvo, int quantidadeParaReduzir){
        ItemVenda auxiliar = new ItemVenda(produtoAlvo, 0);
        ItemVenda itemExiste = this.itensVendidos.compararItens(auxiliar);

        if(itemExiste != null){
            int quantidadeAnterior = itemExiste.getQuantidade();
            int reducao = quantidadeAnterior - quantidadeParaReduzir;
            if(reducao < 0) reducao = 0;
            itemExiste.setQuantidade(reducao);
        }
        //isso é para caso seja necessário retirar um item da lista de compras. o equivalente de tirar um item do carrinho de compras.
    }

    public boolean finalizarVenda(String formaPagamento, Entrega entrega){
        if(this.itensVendidos.listaVazia()){
            System.out.println("nenhum item! venda cancelada.");
            return false;
        }

        Lista<Pedido> pedidosPendentes = new Lista<>();

        System.out.println("\n--- CHECANDO ESTOQUE ---");

        for (int i = 0; i < this.itensVendidos.tamanhoLista(); i++) {
            ItemVenda item = this.itensVendidos.pegarBloco(i);
            Produto produtoVendido = item.getItem();
            int quantidadeVendida = item.getQuantidade();
            
            int falta = this.catalogo.verificarFalta(produtoVendido, quantidadeVendida);
            
            if(falta > 0){
                
                Pedido novoPedido = new Pedido(produtoVendido, falta);
                pedidosPendentes.insereFinal(novoPedido);
                
                System.out.println(produtoVendido.getNome() + " em falta no catalogo. pedido de compra realizado!");

                //caso não tenha itens para finalizar, ele vai adicionar à lista de pedidos pendentes.
            } 
        }

        boolean teveFalta = pedidosPendentes.tamanhoLista() > 0;

        if (teveFalta) {
            System.out.println("\n--- ENTREGA ACIONADA ---");
        
            for (int i = 0; i < pedidosPendentes.tamanhoLista(); i++) {
            Pedido pedidos = pedidosPendentes.pegarBloco(i);
            entrega.realizarEntrega(this, pedidos);
            }
            //se teve falta, o pedido vai ser realizado, e posteriormente, entregado ao cliente.
        }
        else {
            this.concluirAtendimento(catalogo);
        }
        gerarComprovante(formaPagamento);

        this.itensVendidos.limpaLista(); //limpa a lista para poder usar depois se necessário.
        return true;
    }

    public void concluirAtendimento(Catalogo catalogo) {
        for (int i = 0; i < this.itensVendidos.tamanhoLista(); i++) {
            ItemVenda item = this.itensVendidos.pegarBloco(i);
            
            catalogo.reduzirParaVenda(item.getItem(), item.getQuantidade()); 
        }
        //vai ser chamado para concluir as vendas pendentes.
    }

    public void gerarComprovante(String formaPagamento){
        System.out.println("\n--- COMPROVANTE DE VENDA ---");
        System.out.println("data/hora: " + getDataTransacao());
        System.out.println("--------------------------");
        
        if (this.cliente != null) {
            System.out.println("cliente: " + this.cliente.getNome() + " (" + cliente.getEmail() + ")");
        } else {
            System.out.println("cliente: não tem");
        }
        System.out.println("--------------------------");

        System.out.println("itens:");
        for (int i = 0; i < this.itensVendidos.tamanhoLista(); i++) {
            ItemVenda item = this.itensVendidos.pegarBloco(i);
            System.out.printf("- %s (%d unidades) | R$ %.2f\n", 
                              item.getItem().getNome(), 
                              item.getQuantidade(), 
                              item.getSubtotal());
        }
        
        System.out.println("--------------------------");
        System.out.printf("total: R$ %.2f\n", this.total);
        System.out.println("forma de pagamento: " + formaPagamento);
        System.out.println("--------------------------");
    }

}
