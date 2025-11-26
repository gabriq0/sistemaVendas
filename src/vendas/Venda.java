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
    private Catalogo catalogoVendedor;
    private Lista<ItemVenda> itensVendidos;
    private TabelaPreco tabelaVendedor;

    public Venda(Cliente cliente, Catalogo catalogoVendedor, TabelaPreco tabelaVendedor){
        this.idVenda = "V-" + LocalDateTime.now();
        this.dataTransacao = getDataTransacao();
        this.cliente = cliente;
        this.itensVendidos = new Lista<ItemVenda>();
        this.total = 0.0;
        this.catalogoVendedor = catalogoVendedor;
        this.tabelaVendedor = tabelaVendedor;
    }

    public void adicionarItem(Produto produto, int quantidade){
            if(!this.tabelaVendedor.isVigente()){
                System.out.println("erro: a tabela que se refere a este item está vencida.");
                return; // sim isso poderia ser uma excessão...
            }

            double precoVenda = this.tabelaVendedor.getPreco(produto);
            ItemVenda item = new ItemVenda(produto, quantidade, precoVenda);
        
            this.itensVendidos.insereFinal(item);
            this.total += item.getSubtotal();

    }

    public void retirarItem(Produto produtoAlvo, int quantidadeParaReduzir){
        ItemVenda auxiliar = new ItemVenda(produtoAlvo, 0, 0);
        ItemVenda itemExiste = this.itensVendidos.compararItens(auxiliar);

        if(itemExiste != null){
            int quantidadeAnterior = itemExiste.getQuantidade();
            int reducao = quantidadeAnterior - quantidadeParaReduzir;
            if(reducao < 0) reducao = 0;
            itemExiste.setQuantidade(reducao);
        }
        //isso é para caso seja necessário retirar um item da lista de compras. o equivalente de tirar um item do carrinho de compras.
    }

    public boolean finalizarVenda(String formaPagamento) {
        if(this.itensVendidos.listaVazia()){
            System.out.println("nenhum item! venda cancelada.");
            return false;
        }
        
        for(int i = 0; i < this.itensVendidos.tamanhoLista(); i++) {
            ItemVenda item = this.itensVendidos.pegarBloco(i);
            this.catalogoVendedor.reduzirParaVenda(item.getItem(), item.getQuantidade());
        }
        gerarComprovante(formaPagamento);
        this.itensVendidos.limpaLista();
        return true;
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


    public String getIdVenda(){ return this.idVenda; }
    
    public String getDataTransacao(){
        LocalDateTime horadeVenda = LocalDateTime.now();
        DateTimeFormatter estiloBrasil = DateTimeFormatter.ofPattern("HH:mm - dd/MM/yy");
        dataTransacao = horadeVenda.format(estiloBrasil);
        return dataTransacao; //essa formatação está aí, pois o formato original é bem estranho/confuso.
    }
    
    public String getFormaPagamento(){ return formaPagamento; }
    public double getTotal(){ return this.total; }
}
