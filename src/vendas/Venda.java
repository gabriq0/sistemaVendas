package vendas;

import java.time.*;
import java.time.format.DateTimeFormatter;
import catalogo.*;
import clientes.*;
import listas.*;
import logistica.Catalogo;
import logistica.ServicoReposicao;

public class Venda {
    private String idVenda;
    private String dataTransacao;
    private String formaPagamento;
    private Cliente cliente;
    private double total;
    private Catalogo catalogoVendedor;
    private Lista<ItemVenda> itensVendidos;
    private ServicoReposicao servicoReposicao;

    public Venda(Cliente cliente, Catalogo catalogoVendedor, Lista<ItemVenda> itensVendidos, ServicoReposicao servicoReposicao){
        this.idVenda = "V-" + LocalDateTime.now();
        this.dataTransacao = getDataTransacao();
        this.cliente = cliente;
        this.itensVendidos = new Lista<ItemVenda>();
        this.total = 0.0;
        this.catalogoVendedor = catalogoVendedor;
        this.itensVendidos = itensVendidos;
        this.servicoReposicao = servicoReposicao;
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

    public boolean finalizarVenda(String formaPagamento){
        if(this.itensVendidos.listaVazia()){
            System.out.println("nenhum item! venda cancelada.");
            return false;
        }

        System.out.println("finalizando a venda: " + getIdVenda());
        System.out.println("\n\n--- CHECANDO ESTOQUE ---");

        boolean precisaReposicao = false;

        for (int i = 0; i < this.itensVendidos.tamanhoLista(); i++) {
            
            ItemVenda item = this.itensVendidos.pegarBloco(i);
            Produto produtoVendido = item.getItem();
            int quantidadeVendida = item.getQuantidade();
            
            int falta = this.catalogoVendedor.verificarFalta(produtoVendido, quantidadeVendida);
            
            if(falta > 0){
                System.out.println(produtoVendido.getNome() + " em falta no catalogo. pedido de compra realizado!");
                precisaReposicao = true;
                break;
            } 
        }

        if (precisaReposicao) {
            System.out.println("\n--- pedido de reposição automática realizado ---");
            boolean reposicaoSucesso = true;

            for (int i = 0; i < this.itensVendidos.tamanhoLista(); i++) {
            
                ItemVenda item = this.itensVendidos.pegarBloco(i);
                Produto produtoParaRepor = item.getItem();
                int quantidadeVendida = item.getQuantidade();
                int falta = this.catalogoVendedor.verificarFalta(produtoParaRepor, quantidadeVendida);
            
                this.servicoReposicao.comprardoProdutor(produtoParaRepor, (falta+5));

                if(falta > 0){
                    
                    if(!this.servicoReposicao.comprardoProdutor(produtoParaRepor, (falta+5))){
                        reposicaoSucesso = false;
                        System.out.println("venda cancelada: não foi possível repor o catalogo do vendedor.");
                        this.itensVendidos.limpaLista();
                        return false;
                    }                 
                } 
            }
        }

        else {
            this.concluirAtendimento(catalogoVendedor);
        }
        
        gerarComprovante(formaPagamento);
        this.itensVendidos.limpaLista();
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
