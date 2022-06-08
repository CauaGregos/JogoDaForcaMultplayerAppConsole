import java.util.Objects;

public class ComunicadodePalavra extends Comunicado{

    Palavra palavra;

    public ComunicadodePalavra(Palavra palavra) {
        this.palavra = palavra;
    }


    public Palavra getPalavra() {
        return this.palavra;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComunicadodePalavra c = (ComunicadodePalavra) o;
        return Objects.equals(palavra, c.palavra);
    }

    @Override
    public int hashCode() {
        int ret = 666;
        ret = ret * 3 + palavra.hashCode();
        if(ret < 0)
            ret = -ret;
        return ret;

    }

    @Override
    public Object clone ()
    {
        // returnar uma copia de this
        ComunicadoPalavra ret = null;
        try
        { ret = new ComunicadoPalavra (palavra.toString());

        }
        catch (Exception ignored) {
        }
        return ret;
    }

    @Override
    public String toString() {
        return "" + palavra;
    }
}
