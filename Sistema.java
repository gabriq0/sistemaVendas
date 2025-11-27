import java.time.LocalDate;

import catalogo.Catalogo;
import catalogo.Produto;
import catalogo.TabelaPreco;
import clientes.Cliente;
import clientes.ListaClientes;
import listas.Lista;
import vendas.Venda;

public class Sistema {
    private static Sistema instancia1;

    //todas as instâncias necessárias para o funcionamento do sistema:
    private Catalogo catalogo;
    private ListaClientes listaClientes;
    private Lista<TabelaPreco> tabelas;
    
    private Venda vendaAtual;

    private Sistema(){
        catalogo = new Catalogo();
        listaClientes = new ListaClientes();
        tabelas = new Lista<TabelaPreco>();
    }

    public static Sistema getInstance() {
        if (instancia1 == null) instancia1 = new Sistema();
        return instancia1;
    }

    public Catalogo getCatalogo() { return catalogo; }
    public ListaClientes getListaClientes() { return listaClientes; }
    public Lista<TabelaPreco> getTabelas() { return tabelas; }
    
    // ----------------------------------------------------------------------------------

    // seção que trata das lógicas de CATALOGO / PRODUTO:
    public void cadastrarProduto(String nome, String id, int qtd){
        if(id == null) return;
        Produto novo = new Produto(nome, id);

        boolean addCatalogo = this.catalogo.adicionarProduto(novo, qtd);
        
        if(addCatalogo) System.out.println(nome + " adicionado ao catalogo.");
        else System.out.println("erro ao cadastrar produto.");
    }

    public void adicionarEstoque(String id, int qtd){
        Produto p = this.catalogo.buscaProduto(id);
        if(p != null) this.catalogo.adicionarProduto(p, qtd);
    }

    // ----------------------------------------------------------------------------------

    // seção que trata das lógicas de CLIENTES:
    public void cadastrarCliente(String nome, String email, String endereco){
        if(email == null) return;

        this.listaClientes.adicionar(nome, email, endereco);
        System.out.println(nome + " adicionado(a) no sistema!");
    }

    // ----------------------------------------------------------------------------------

    // seção que trata das lógicas de TABELAS DE PREÇO:
    public void criarTabela(LocalDate inicio, LocalDate fim){
        TabelaPreco tab = new TabelaPreco();
        tab.definirVigencia(inicio, fim);
        this.tabelas.insereFinal(tab);
    }
    
    public void definirPreco(String id, double preco){
        Produto p = this.catalogo.buscaProduto(id);
        if(p == null) return;

        TabelaPreco tab = getVigente();
        tab.definirPreco(p, preco);
    }

    private TabelaPreco getVigente(){
        for(int i=0;i<this.tabelas.tamanhoLista();i++){
            TabelaPreco tab = this.tabelas.pegarBloco(i);
            if(tab.isVigente()) return tab;
        }
        return null;
    }

    // ----------------------------------------------------------------------------------
    
    // parte que trata das lógicas de VENDA: 
    public void iniciarVenda(String email){
        Cliente cliente = this.listaClientes.buscar(email);
        TabelaPreco tabelaVigente = getVigente();

        if(cliente != null && tabelaVigente != null) this.vendaAtual = new Venda(cliente, catalogo, tabelaVigente);
        else System.out.println("erro: cliente ou tabela de preços não encontrados.");
    }

    public void addItemVenda(String id, int qtd){
        if(this.vendaAtual != null){
            Produto p = this.catalogo.buscaProduto(id);
            if(p != null) this.vendaAtual.adicionarItem(p, qtd);
        }
    }

    public void fecharVenda(String pag){
        if(this.vendaAtual != null){
            this.vendaAtual.finalizarVenda(pag);
            this.vendaAtual = null;
        }
    }
}
