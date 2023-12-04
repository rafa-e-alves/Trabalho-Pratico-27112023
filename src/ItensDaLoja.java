public class ItensDaLoja {
    private static int contadorCodigo = 1;
    private String nome;
    private double valor;
    private int codigo;

    public ItensDaLoja(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
        this.codigo = contadorCodigo++;
    }

    public void exibirInformacoes() {
        System.out.println("Código: " + codigo);
        System.out.println("Nome: " + nome);
        System.out.println("Valor: R$ " + valor);
    }

    public int getCodigo() {
        return codigo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double novoValor) {
        this.valor = novoValor;
    }
}
