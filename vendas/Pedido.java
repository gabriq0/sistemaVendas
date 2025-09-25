package vendas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import catalogo.Catalogo;
import catalogo.Produto;

public class Pedido {
    private Produto produto;
    private String dataPedido;
    private int quantidadePedida;
    private boolean concluido;

    public Pedido(Produto produto, int quantidadePedida){
        this.produto = produto;
        this.dataPedido = getDataPedido();
        this.quantidadePedida = quantidadePedida;
        this.concluido = false;
    }

    public String getDataPedido() {
        LocalDateTime horadeVenda = LocalDateTime.now();
        DateTimeFormatter estiloBrasil = DateTimeFormatter.ofPattern("hh:mm - dd/MM/yy");
        dataPedido = horadeVenda.format(estiloBrasil);
        return dataPedido; //essa formatação está aí, pois o formato original é bem estranho/confuso.
    }

    public void concluirPedido(Catalogo catalogo) {
        if (!this.concluido) {
            catalogo.adicionarProduto(this.produto, this.quantidadePedida);
            this.concluido = true;
            System.out.println("pedido concluído! " + produto.getNome() + "(" + quantidadePedida + " unidades adicionadas ao estoque)");
            System.out.println("-----------------------");
        }
    }
}
