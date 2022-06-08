public class Tracinhos extends Comunicado implements Cloneable {
    private char texto[];

    public Tracinhos(int qtd) throws Exception {
        // verifica se qtd não é positiva, lançando uma exceção.
        if (qtd <= 0)
            throw new Exception("Quantidade inválida.");
        // instancia this.texto com um vetor com tamanho igual qtd.
        // preenche this.texto com underlines (_).
        this.texto = new char[qtd];
        for (int i = 0; i < qtd; i++) {
            this.texto[i] = '_';
        }

    }

    public void revele(int posicao, char letra) throws Exception {

        // verifica se posicao é negativa ou então igual ou maior
        // do que this.texto.length, lançando uma exceção.

        if (posicao < 0 || posicao >= this.texto.length) {
            throw new Exception("Posição inválida");
        }

        // armazena a letra fornecida na posicao tambem fornecida
        // do vetor this.texto
        this.texto[posicao] = letra;

    }

    public boolean isAindaComTracinhos() {
        // percorre o vetor de char this.texto e verifica
        // se o mesmo ainda contem algum underline ou se ja
        // foram todos substituidos por letras; retornar true
        for (int posicao = 0; posicao < this.texto.length; posicao++) {
            if (this.texto[posicao] == '_') {
                return true;
            }
        }
        return false;
        // if (this.texto < letras) return true;

        // caso ainda reste algum underline, ou false caso
        // contrario
    }

    @Override
    public String toString() {
        String letras = "";
        for (int posicao = 0; posicao < this.texto.length; posicao++) {
            letras += this.texto[posicao] + " ";
        }
        return letras;
        // retorna um String com TODOS os caracteres que há
        // no vetor this.texto, intercalados com espaços em
        // branco
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true; // verificar se this e obj possuem o mesmo conteúdo

        if (obj == null)
            return false;

        if (obj.getClass() != Tracinhos.class)
            return false;

        Tracinhos tracinho = (Tracinhos) obj;

        // sempre esse padrão
        if (!tracinho.texto.equals(this.texto))

            return false;

        return true;

    }

    public int hashCode() {
        // calcular e retornar o hashcode de this
        int hash = 1;
        for (int posicao = 0; posicao < this.texto.length; posicao++) {
            hash = 7 * hash + Character.valueOf(this.texto[posicao]); // Character.valueOf() retorna um object Character
        }
        if (hash < 0) {
            hash = -hash; // inverte o valor
        }
        return hash;
    }

    public Tracinhos(Tracinhos t) throws Exception // construtor de cópia
    {
        if (t == null) {
            throw new Exception("Valor de cópia vazio");
        }
        // instanciar this.texto um vetor com o mesmo tamanho de t.texto
        this.texto = new char[t.texto.length];
        // e copilar o conteúdo de t.texto para this.texto
        for (int posicao = 0; posicao < t.texto.length; posicao++) {
            this.texto[posicao] = t.texto[posicao];
        }

    }

    public Object clone() {
        // retornar uma copia de this
        Tracinhos t = null;
        try {
            t = new Tracinhos(this);
        } catch (Exception erro) {
        }
        return t;
    }
}