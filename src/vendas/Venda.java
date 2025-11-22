package vendas;

import java.time.*;
import java.time.format.DateTimeFormatter;
import catalogo.*;
import clientes.*;
import excecoes.CarrinhoVazioException;
import excecoes.EstoqueInsuficienteException;
import excecoes.ProdutoSemPrecoException;
import excecoes.QuantidadeInvalidaException;
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

    public Venda(Cliente cliente, Catalogo catalogoVendedor, Lista<ItemVenda> itensVendidos, TabelaPreco tabelaVendedor){
        this.idVenda = "V-" + LocalDateTime.now();
        this.dataTransacao = getDataTransacao();
        this.cliente = cliente;
        this.itensVendidos = new Lista<ItemVenda>();
        this.total = 0.0;
        this.catalogoVendedor = catalogoVendedor;
        this.itensVendidos = itensVendidos;
        this.tabelaVendedor = tabelaVendedor;
    }

    public void adicionarItem(Produto produto, int quantidade) throws ProdutoSemPrecoException, QuantidadeInvalidaException {
            double precoVenda = this.tabelaVendedor.getPreco(produto);
            ItemVenda item = new ItemVenda(produto, quantidade, precoVenda);
        
            this.itensVendidos.insereFinal(item);
            this.total += item.getSubtotal();

    }

    public void retirarItem(Produto produtoAlvo, int quantidadeParaReduzir) throws QuantidadeInvalidaException{
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

    public void finalizarVenda(String formaPagamento) throws EstoqueInsuficienteException, CarrinhoVazioException {
        if(this.itensVendidos.listaVazia()) throw new CarrinhoVazioException();
        
        for(int i = 0; i < this.itensVendidos.tamanhoLista(); i++) {
            ItemVenda item = this.itensVendidos.pegarBloco(i);
            this.catalogoVendedor.reduzirParaVenda(item.getItem(), item.getQuantidade());
        }
        gerarComprovante(formaPagamento);
        this.itensVendidos.limpaLista();
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


    public String getIdVenda(){
        return this.idVenda;
    }
    public String getDataTransacao(){
        LocalDateTime horadeVenda = LocalDateTime.now();
        DateTimeFormatter estiloBrasil = DateTimeFormatter.ofPattern("HH:mm - dd/MM/yy");
        dataTransacao = horadeVenda.format(estiloBrasil);
        return dataTransacao; //essa formatação está aí, pois o formato original é bem estranho/confuso.
    }
    public String getFormaPagamento(){
        return formaPagamento;
    }
    public double getTotal(){
        return this.total;
    }
}
