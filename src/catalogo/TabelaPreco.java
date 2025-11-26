package catalogo;
import listas.*;
import java.time.LocalDate;

public class TabelaPreco {
    private Lista<ItemTabelaPreco> tabela = new Lista<ItemTabelaPreco>();

    private LocalDate inicioV;
    private LocalDate fimV;

    public void definirVigencia(LocalDate inicio, LocalDate fim){
        this.inicioV = inicio;
        this.fimV = fim;
    }

    public void definirPreco(Produto produto, double precoProduto) {
        ItemTabelaPreco novoItem = new ItemTabelaPreco(produto, precoProduto);
        ItemTabelaPreco itemExistente = tabela.compararItens(novoItem);

        if (itemExistente != null) itemExistente.setPrecoProduto(precoProduto);
        else tabela.insereFinal(novoItem);
    }

    public boolean isVigente(){
        if(inicioV == null || fimV == null) return true; 
        // ^ isso quer dizer que caso a vigência da tabela não seja definida, ele ainda vai retornar válida (a vigência não deve ser obrigatória).

        LocalDate agora = LocalDate.now();
        boolean depoisInicio = agora.isEqual(inicioV) || agora.isAfter(inicioV);
        boolean antesFim = agora.isEqual(fimV) || agora.isBefore(fimV);

        return depoisInicio && antesFim; 
        // se os dois retornarem true, isso quer dizer que está dentro do período de vigência da tabela.
    }

    public void removerPreco(Produto produto) {
        ItemTabelaPreco itemBusca = new ItemTabelaPreco(produto, 0);
        this.tabela.excluirItem(itemBusca);
    }

    public Double getPreco(Produto produto) {
        ItemTabelaPreco itemBusca = new ItemTabelaPreco(produto, 0); 
        ItemTabelaPreco itemEncontrado = tabela.compararItens(itemBusca);

        if (itemEncontrado != null) return itemEncontrado.getPrecoProduto();
        else return null;
    }

    public void mostrarTabela(){
        if(this.tabela.listaVazia()) System.out.println("não tem produtos, não tem preços?!!");
        System.out.println(this.tabela.toString());
    }

    @Override
    public String toString() {
        return this.tabela.toString();
    }
}
