public class ComunicadoOpcoes extends Comunicado{


    private ComunicadoOpcoes comunicadoOpcoes;

    private char optar;


    public ComunicadoOpcoes (char optar)
    {
        this.optar = optar;
    }

    public char getOptar(){
        return this.optar;
    }


    public String toString (){
        return (""+this.optar);
    }

    @Override
    public int hashCode() {
        int ret = 666;
        ret = ret * 3 + ((Character)optar).hashCode();
        if(ret < 0)
            ret = -ret;
        return ret;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComunicadoOpcoes c = (ComunicadoOpcoes) o;
        return optar == c.optar;
    }

    @Override
    public Object clone ()
    {
        // returnar uma copia de this
        ComunicadoOpcoes ret = null;
        try
        { ret = new ComunicadoOpcoes (this.optar);

        }
        catch (Exception ignored) {
        }
        return ret;
    }


}
