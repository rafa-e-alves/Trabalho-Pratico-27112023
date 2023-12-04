import java.util.ArrayList;
import java.util.List;

public class Vitrine {
    private List<ItensDaLoja> itens;

    public Vitrine() {
        this.itens = new ArrayList<>();
    }

    public ItensDaLoja[] getItens() {
        return itens.toArray(new ItensDaLoja[0]);
    }

    public void adicionarItem(ItensDaLoja item) {
        itens.add(item);
    }

    public boolean removerItem(int codigo) {
        for (ItensDaLoja item : itens) {
            if (item.getCodigo() == codigo) {
                itens.remove(item);
                return true;
            }
        }
        return false;
    }

    public void atualizarPreco(int codigo, double novoValor) {
        for (ItensDaLoja item : itens) {
            if (item.getCodigo() == codigo) {
                item.setValor(novoValor);
                break;
            }
        }
    }

    public void exibirItensVitrine() {
        if (itens.isEmpty()) {
            System.out.println("Vitrine vazia. Nenhum item cadastrado.");
        } else {
            System.out.println("Itens na Vitrine:");
            for (ItensDaLoja item : itens) {
                item.exibirInformacoes();
                System.out.println("-----------------------");
            }
        }
    }
}
