import java.util.Objects;

public class ComunicadoCaregoChute  extends Comunicado{

    private String string;

    public ComunicadoCaregoChute(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComunicadoCaregoChute that = (ComunicadoCaregoChute) o;
        return Objects.equals(string, that.string);
    }

    @Override
    public int hashCode() {
        int ret = 666;
        ret = ret * 3 + string.hashCode();
        if(ret < 0)
            ret = -ret;
        return ret;

    }

    @Override
    public Object clone ()
    {
        // returnar uma copia de this
        ComunicadoCaregoChute ret = null;
        try
        { ret = new ComunicadoCaregoChute (this.string);

        }
        catch (Exception ignored) {
        }
        return ret;
    }

}

