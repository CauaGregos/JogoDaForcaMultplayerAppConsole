public class Palavra extends Comunicado implements Comparable<Palavra>
{
    private String texto;

    public Palavra (String texto) throws Exception
    {
        // verifica se o texto recebido � nulo ou ent�o vazio,
        // ou seja, sem nenhum caractere, lan�ando exce��o.
        // armazena o texto recebido em this.texto.

        if (texto == null || texto == "")
            throw new Exception ("Texto Inv�lido");

        else
        {
            this.texto = texto;
        }
    }




    public int getQuantidade (char letra)
    {
        // percorre o String this.texto, conta e retorna
        // quantas letras existem nele iguais a letra fornecida

        int qtd = 0;

        for (int index = 0; index < texto.length(); index ++ )
        {
            if (texto.charAt(index) == letra)
            {
                qtd ++;
            }
        }

        return qtd;

    }

    public int getPosicaoDaIezimaOcorrencia (int i, char letra) throws Exception
    {
        try
        {
            int numLetras = 0;

            if(i < 0 || i > texto.length()-1)
                throw new Exception("Posi��o inv�lida");

            for(int pos = 0; pos < this.texto.length(); pos++)
            {
                if(this.texto.charAt(pos) == (letra))
                {
                    if(numLetras == i )
                        return pos;

                    numLetras ++;
                }
            }
        }

        catch(Exception erro)
        {
            System.out.println("erro");
        }

        return letra;

        // se i==0, retorna a posicao em que ocorre a primeira
        // aparicao de letra fornecida em this.texto;
        // se i==1, retorna a posicao em que ocorre a segunda
        // aparicao de letra fornecida em this.texto;
        // se i==2, retorna a posicao em que ocorre a terceira
        // aparicao de letra fornecida em this.texto;
        // e assim por diante.
        // lan�ar excecao caso nao encontre em this.texto
        // a I�zima apari��o da letra fornecida.

    }


    public int getTamanho ()
    {
        return this.texto.length();
    }

    @Override
    public String toString ()
    {
        return this.texto;
    }

    @Override
    public boolean equals (Object obj)
    {
        if (this==obj) return true;

        if (obj==null) return false;

        if (obj.getClass() != Palavra.class) return false;

        Palavra palavra = (Palavra)obj;

        //sempre esse padr�o
        if (!palavra.texto.equals(this.texto)) // || !palavra.texto == this.texto (se for tipo primitivo)
            return false;

        return true;

        // verificar se this e obj possuem o mesmo conte�do, retornando
        // true no caso afirmativo ou false no caso negativo
    }
    @Override
    public int hashCode ()
    {
        int ret = 2;
        ret = ret * 2 + texto.hashCode(); //converte qualquer tipo s� colocar ".hashcode" TEM Q SER OBJETO
        if(ret < 0)
            ret = -ret;
        return ret;

        // calcular usando qualquer numero primo
        // calcular e retornar o hashcode de this
    }

    @Override
    public int compareTo (Palavra palavra)
    {
        return this.texto.compareTo(palavra.texto);
    }
}
