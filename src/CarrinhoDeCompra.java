public class CarrinhoDeCompra {
    private ItemDoCarrinho[] itensNoCarrinho;

    public CarrinhoDeCompra() {
        this.itensNoCarrinho = new ItemDoCarrinho[0];
    }

    public void adicionarItemAoCarrinho(ItemDoCarrinho item) {
        ItemDoCarrinho[] novoArray = new ItemDoCarrinho[itensNoCarrinho.length + 1];
        System.arraycopy(itensNoCarrinho, 0, novoArray, 0, itensNoCarrinho.length);
        novoArray[itensNoCarrinho.length] = item;
        itensNoCarrinho = novoArray;
    }

    public ItemDoCarrinho[] getItensNoCarrinho() {
        return itensNoCarrinho;
    }

    public void exibirCarrinho() {
        System.out.println("Itens no Carrinho:");
        for (ItemDoCarrinho item : itensNoCarrinho) {
            item.exibirItemDoCarrinho();
            System.out.println("-----------------------");
        }
    }
}
