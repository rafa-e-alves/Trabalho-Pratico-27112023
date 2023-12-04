import java.util.Scanner;

public class Loja {
    private Vitrine vitrine;
    private CarrinhoDeCompra carrinhoDeCompra;
    private final String senhaAdministrador = "admin123";

    public Loja() {
        this.vitrine = new Vitrine();
        this.carrinhoDeCompra = new CarrinhoDeCompra();
    }

    public void exibirMenuPrincipal() {
        Scanner entry = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("===== Menu Principal =====");
            System.out.println("1. Ver vitrine da loja.");
            System.out.println("2. Ver carrinho de compra.");
            System.out.println("3. Comprar item.");
            System.out.println("4. Realizar pagamento.");
            System.out.println("5. Acessar opções do administrador.");
            System.out.println("0. Sair.");
            System.out.print("Escolha uma opção: ");

            opcao = entry.nextInt();
            entry.nextLine();

            switch (opcao) {
                case 1:
                    vitrine.exibirItensVitrine();
                    break;
                case 2:
                    carrinhoDeCompra.exibirCarrinho();
                    break;
                case 3:
                    comprarItem();
                    break;
                case 4:
                    realizarPagamento();
                    break;
                case 5:
                    acessarOpcoesDoAdministrador();
                    break;
                case 0:
                    System.out.println("Saindo da loja, obrigado por acessá-la. Espero revê-lo em breve!");
                    break;
                default:
                    System.out.println("Opção inválida, escolha uma entre 0 e 5.");
            }

        } while (opcao != 0);
    }

    private void comprarItem() {
        Scanner entry = new Scanner(System.in);

        System.out.print("Informe o código do produto: ");
        int codigoProduto = entry.nextInt();
        entry.nextLine();

        ItensDaLoja[] itens = vitrine.getItens();
        boolean produtoEncontrado = false;

        for (ItensDaLoja item : itens) {
            if (item.getCodigo() == codigoProduto) {
                System.out.print("Informe a quantidade desejada: ");
                int quantidade = entry.nextInt();
                entry.nextLine();

                ItemDoCarrinho itemDoCarrinho = new ItemDoCarrinho(item, quantidade);
                carrinhoDeCompra.adicionarItemAoCarrinho(itemDoCarrinho);

                System.out.println("Item adicionado ao carrinho com sucesso!");
                produtoEncontrado = true;
                break;
            }
        }

        if (!produtoEncontrado) {
            System.out.println("Código de produto não encontrado na vitrine.");
        }
    }

    private void realizarPagamento() {
        Scanner entry = new Scanner(System.in);

        System.out.println("===== Realizar Pagamento =====");
        carrinhoDeCompra.exibirCarrinho();

        double totalCompra = calcularTotalCompra();
        System.out.println("Total da compra: R$ " + totalCompra);

        System.out.println("Opções de pagamento:");
        System.out.println("1. À vista com 10% de desconto.");
        System.out.println("2. Parcelado em até 5 vezes sem juros.");

        System.out.print("Escolha a opção de pagamento: ");
        int opcaoPagamento = entry.nextInt();
        entry.nextLine();

        switch (opcaoPagamento) {
            case 1:
                double desconto = totalCompra * 0.10;
                double totalComDesconto = totalCompra - desconto;
                System.out.println("Total com desconto: R$ " + totalComDesconto);
                System.out.println("Pagamento à vista realizado com sucesso!");
                break;
            case 2:
                int numParcelas;

                do {
                    System.out.print("Informe o número de vezes que deseja dividir a compra (máximo 5 sem juros): ");
                    numParcelas = entry.nextInt();

                    if (numParcelas > 0 && numParcelas <= 5) {
                        double valorParcela = totalCompra / numParcelas;
                        System.out.println("Pagamento parcelado em " + numParcelas + " vezes de R$ " + valorParcela + " realizado com sucesso!");
                    } else {
                        System.out.println("Número de parcelas inválido. Escolha entre 1 e 5 parcelas.");
                    }
                } while (numParcelas <= 0 || numParcelas > 5);
                break;
            default:
                System.out.println("Método de pagamento inválido.");
        }
        carrinhoDeCompra = new CarrinhoDeCompra();
    }

    private double calcularTotalCompra() {
        ItemDoCarrinho[] itensNoCarrinho = carrinhoDeCompra.getItensNoCarrinho();
        double total = 0;

        for (ItemDoCarrinho item : itensNoCarrinho) {
            total += item.getItem().getValor() * item.getQuantidade();
        }

        return total;
    }

    private void acessarOpcoesDoAdministrador() {
        Scanner entry = new Scanner(System.in);

        System.out.print("Digite a senha do administrador: ");
        String senhaDigitada = entry.nextLine();

        if (senhaDigitada.equals(senhaAdministrador)) {
            int opcaoAdmin;

            do {
                System.out.println("===== Opções do Administrador =====");
                System.out.println("1. Adicionar item à vitrine.");
                System.out.println("2. Remover item da vitrine.");
                System.out.println("3. Atualizar preço de item na vitrine.");
                System.out.println("0. Voltar ao menu principal.");
                System.out.print("Escolha uma opção: ");

                opcaoAdmin = entry.nextInt();
                entry.nextLine();

                switch (opcaoAdmin) {
                    case 1:
                        adicionarItemVitrine();
                        break;
                    case 2:
                        removerItemVitrine();
                        break;
                    case 3:
                        atualizarPrecoVitrine();
                        break;
                    case 0:
                        System.out.println("Retornando ao menu principal da loja.");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }

            } while (opcaoAdmin != 0);
        } else {
            System.out.println("Senha incorreta. Acesso negado.");
        }
    }

    private void adicionarItemVitrine() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Informe o valor do produto: ");
        double valor = scanner.nextDouble();

        System.out.println("Código de produto gerado automaticamente.");

        ItensDaLoja novoItem = new ItensDaLoja(nome, valor);
        vitrine.adicionarItem(novoItem);

        System.out.println("Item adicionado à vitrine com sucesso!");
    }

    private void removerItemVitrine() {
        Scanner entry = new Scanner(System.in);

        System.out.print("Informe o código do produto a ser removido: ");
        int codigo = entry.nextInt();

        if (vitrine.removerItem(codigo)) {
            System.out.println("Item removido da vitrine com sucesso!");
        } else {
            System.out.println("Item não encontrado na vitrine.");
        }
    }

    private void atualizarPrecoVitrine() {
        Scanner entry = new Scanner(System.in);

        System.out.print("Informe o código do produto a ser atualizado: ");
        int codigo = entry.nextInt();

        ItensDaLoja[] itens = vitrine.getItens();
        boolean produtoEncontrado = false;

        for (ItensDaLoja item : itens) {
            if (item.getCodigo() == codigo) {
                System.out.print("Informe o novo valor do produto: ");
                double novoValor = entry.nextDouble();

                item.setValor(novoValor);

                System.out.println("Preço atualizado com sucesso!");
                produtoEncontrado = true;
                break;
            }
        }

        if (!produtoEncontrado) {
            System.out.println("Produto não encontrado na vitrine.");
        }
    }

    public static void main(String[] args) {
        Loja loja = new Loja();
        loja.exibirMenuPrincipal();
    }
}