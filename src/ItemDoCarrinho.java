public class ItemDoCarrinho {
    private ItensDaLoja item;
    private int quantidade;

    public ItemDoCarrinho(ItensDaLoja item, int quantidade) {
        this.item = item;
        this.quantidade = quantidade;
    }

    public void exibirItemDoCarrinho() {
        item.exibirInformacoes();
        System.out.println("Quantidade: " + quantidade);
    }

    public ItensDaLoja getItem() {
        return item;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
