public class ControladorDeErros extends Comunicado implements Cloneable
{
    private int qtdMax, qtdErr=0;

    public ControladorDeErros (int qtdMax) throws Exception
    {
        // verifica se qtdMax fornecida n�o � positiva, lan�ando
        // uma exce��o.
        // armazena qtdMax fornecida em this.qtdMax.
        if (qtdMax < 0) throw new Exception ("Quantidade maxima negativa");
        this.qtdMax = qtdMax;
    }

    public void registreUmErro () throws Exception
    {
        // verifica se this.qtdErr ja � igual a this.qtdMax,
        // lan�ando excecao em caso positivo ou
        // incrementando this.qtdErr em caso negativo
        if (this.qtdErr==this.qtdMax) throw new Exception ("Quantidade maxima de erros atingida");
        this.qtdErr++;

    }

    public boolean isAtingidoMaximoDeErros  ()
    {
        // returna true se this.qtdErr for igual a this.qtdMax,
        // ou ent�o false, caso contrario.
        if (this.qtdErr==this.qtdMax) return true;
        return false;
        // return this.qtdErr==this.qtdMax;
    }

    public String toString ()
    {
        return this.qtdErr + "/" + this.qtdMax;
    }

    public boolean equals (Object obj)
    {
        // verificar se this e obj possuem o mesmo conte�do, retornando
        // true no caso afirmativo ou false no caso negativo
        if (this==obj) return true;

        if (obj==null) return false;

        if (obj.getClass() != ControladorDeErros.class)
        {
            return false;
        }
        if (this.qtdMax!=((ControladorDeErros)obj).qtdMax)
        {
            return false;
        }
        if (this.qtdErr!=((ControladorDeErros)obj).qtdErr)
        {
            return false;
        }
        return true;


    }

    @Override
    public int hashCode ()
    {
        // calcular e retornar o hashcode de this
        int ret = 666;

        ret = 13*ret + new Integer (this.qtdMax).hashCode();
        ret = 13*ret + new Integer (this.qtdErr).hashCode();

        if (ret < 0) {
            ret = -ret;
        }
        return ret;
    }

    public ControladorDeErros (ControladorDeErros c) throws Exception // construtor de c�pia
    {
        // copiar c.qtdMax e c.qtdErr, respectivamente em, this.qtdMax e this.qtdErr
        if (c==null) throw new Exception ("Modelo Ausente");

        this.qtdErr = c.qtdErr;
        this.qtdMax = c.qtdMax;

    }

    public Object clone ()
    {
        // returnar uma c�pia de this
        ControladorDeErros ret = null;
        try
        { ret = new ControladorDeErros (this);

        }
        catch (Exception ignored) {
        }
        return ret;
    }
}