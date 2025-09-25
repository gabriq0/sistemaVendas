package vendas;

import catalogo.*;

public class Entrega {
    private Catalogo catalogo;

    public Entrega(Catalogo catalogo){
        this.catalogo = catalogo;
    }

    public void realizarEntrega(Venda venda, Pedido pedidoPendente){
        pedidoPendente.concluirPedido(catalogo);
        venda.concluirAtendimento(this.catalogo);

        System.out.println("venda finalizada! " + venda.getIdVenda() + " foi entregue ao cliente.");
        System.out.println("-----------------------");
    }
}
