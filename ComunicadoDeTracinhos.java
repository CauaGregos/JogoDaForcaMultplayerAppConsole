public class ComunicadoDeTracinhos extends Comunicado{


    private String tracinhos = "";

    public ComunicadoDeTracinhos(String tracinhos){
        this.tracinhos = tracinhos;
    }

    public String getTracinhos() {
        return tracinhos;
    }

    public void setTracinhos(String tracinhos) {
        this.tracinhos = tracinhos;
    }


    @Override
    public String toString() {
        return "" + tracinhos;
    }

    @Override
    public int hashCode() {
        int ret = 666;
        ret = ret * 3 + tracinhos.hashCode();
        if(ret < 0)
            ret = -ret;
        return ret;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComunicadoDeTracinhos c = (ComunicadoDeTracinhos) o;
        return tracinhos == c.tracinhos;
    }

    @Override
    public Object clone ()
    {
        // returnar uma copia de this
        ComunicadoDeTracinhos ret = null;
        try
        { ret = new ComunicadoDeTracinhos (this.tracinhos);

        }
        catch (Exception ignored) {
        }
        return ret;
    }


}
