public class ControladorDeLetrasJaDigitadas extends Comunicado implements Cloneable
{
    // variáveis
    private String letrasJaDigitadas;

    public ControladorDeLetrasJaDigitadas ()
    {
        // torna this.letrasJaDigitadas igual ao String vazio
        letrasJaDigitadas =  "";
    }

    public boolean isJaDigitada (char letra)
    {
        // percorrer o String this.letrasJaDigitadas e verificar se ele
        // possui a letra fornecida, retornando true em caso afirmativo
        // ou false em caso negativ

        char[] ch=letrasJaDigitadas.toCharArray();
        for(int i=0;i<ch.length;i++){
            if (ch[i] == letra){
                return true;
            }
        }
        return false;
    // tentei percorrer pela string vendo se a letra que digitei como novo charm ja foi digitada.
    }

    public void registre (char letra) throws Exception
    {
		// verifica se a letra fornecida ja foi digitada (pode usar
		// o metodo this.isJaDigitada, para isso), lancando uma exceção
		// em caso afirmativo.
		// concatena a letra fornecida a this.letrasJaDigitadas.
        if (!isJaDigitada(letra)){
            letrasJaDigitadas += letra;
        }

        else if (isJaDigitada(letra)) {
            throw new Exception("Letra ja foi digitada");
        }



    }
@Override
   public String toString ()
    {
		// retorna um String com TODAS as letras presentes em
		// this.letrasJaDigitadas separadas por v�rgula (,).

        String ret = "";
        for(int i=0;i<letrasJaDigitadas.length();i++){
            ret += letrasJaDigitadas.charAt(i);
            if (i != letrasJaDigitadas.length()-1){
                ret += ", ";
            }
        }
        // Acima esta oque seria a separação dos chars de dentro da string.
        return ret;
    }

    @Override
    public boolean equals (Object var1)
    {
        // verifica se metodo chamante é igual a obj
        if(this==var1) return true;

        if(var1==null) return false;

        if(this.getClass()!=var1.getClass())
            return false;

        ControladorDeLetrasJaDigitadas x= (ControladorDeLetrasJaDigitadas) var1;

        if(!this.letrasJaDigitadas.equals(x.letrasJaDigitadas))
            return false;

        return true;
    }



    public int hashCode ()
    {
        //calcular e retornar o hashcode de this
        int ret = 666;

        ret = 3 * ret + new String (this.letrasJaDigitadas).hashCode();

        if (ret < 0){
            ret = -ret ;
        }
        return ret ;

    }

    // Aqui é o construtor de cópia
    public ControladorDeLetrasJaDigitadas(ControladorDeLetrasJaDigitadas modelo) // meu parameto modelo, para criar a cíopia
    throws Exception // construtor de cópia
    {
        // copiar c.letrasJaDigitadas em this.letrasJaDigitadas

        if (modelo == null){ // se for null
            throw new Exception("Modelo ausente");
        }
        this.letrasJaDigitadas = modelo.letrasJaDigitadas; // letras digitadas rececbe ela mesmo, ou seja, sai uma cópia daqui
        // para outro lugar da memoria.
    }

    public Object clone (){
        // criar uma cópia do this com o construtor de cópia e retornar
        ControladorDeLetrasJaDigitadas ret = null; // Aqui uso o construtor de cópia
        try {
            ret = new ControladorDeLetrasJaDigitadas(this); // aqui recebe a cópia e á transforma em um clone.
        }// Exessão ignorada
        catch (Exception ignored){}
        return ret; // retornei o clone
    }
}
