public class ComunicadodeLetra extends Comunicado{
    
    private char letra;
    
    public  ComunicadodeLetra(char letra){

        this.letra = letra;

    }

    public char getLetra() {
        return letra;
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }



    @Override
    public int hashCode() {
        int ret = 666;
        ret = ret * 3 + ((Character)letra).hashCode();
        if(ret < 0)
            ret = -ret;
        return ret;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComunicadodeLetra c = (ComunicadodeLetra) o;
        return letra == c.letra;
    }

    @Override
    public Object clone ()
    {
        // returnar uma copia de this
        ComunicadodeLetra ret = null;
        try
        { ret = new ComunicadodeLetra (this.letra);

        }
        catch (Exception ignored) {
        }
        return ret;
    }

    @Override
    public String toString() {
        return "" + letra;
    }
}
