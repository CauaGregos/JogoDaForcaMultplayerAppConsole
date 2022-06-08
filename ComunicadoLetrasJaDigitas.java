public class ComunicadoLetrasJaDigitas extends Comunicado{

   private String letras = "";

    public ComunicadoLetrasJaDigitas(String letras) {
        this.letras = letras;
    }

    public String getLetra(){
        return letras;
    }

    public String toString() {
        return this.letras;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComunicadoLetrasJaDigitas c = (ComunicadoLetrasJaDigitas) o;
        return letras == c.letras;
    }

    @Override
    public int hashCode() {
        int ret = 666;
        ret = ret * 3 + letras.hashCode();
        if(ret < 0)
            ret = -ret;
        return ret;

    }

    @Override
    public Object clone ()
    {
        // returnar uma copia de this
        ComunicadoLetrasJaDigitas ret = null;
        try
        { ret = new ComunicadoLetrasJaDigitas (this.letras);

        }
        catch (Exception ignored) {
        }
        return ret;
    }



}
