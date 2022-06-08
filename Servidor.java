import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;


public class Servidor
{
    public static void main(String[] args)
    {

        boolean t = false;

        System.out.print("Indique a porta desejada: ");
        String porta = null;
        try
        {
            porta = Teclado.getUmString();
        }
        catch(Exception e)
        {
            System.err.println("Valor invalido");
            return;
        }
        ArrayList<ArrayList<Parceiro>> usuarios = new ArrayList();
        AceitadoraDeConexao aceitadoraDeConexao = null;
        try
        {
            aceitadoraDeConexao = new AceitadoraDeConexao(porta, usuarios);
            try
            {
                System.out.println("\nIp para jogar: " + InetAddress.getLocalHost().getHostAddress() + "\n"); //Para mostrar o IP do computador para outros poderem acessar o jogo
            }
            catch(Exception e)
            {}

            aceitadoraDeConexao.start();
        }
        catch(Exception ex)
        {
            System.err.println("Escolha outra porta!\n");
            return;
        }

        for(;;)
        {
            System.out.println("O servidor esta ativo! Para desativa-lo, use o comando \"desativar\"\n");
            System.out.print("> ");

            String comando = null;
            try
            {
                comando = Teclado.getUmString();
            }
            catch(Exception ex)
            {}

            // leio o comando desativar
            if(comando.toLowerCase().equals("desativar"))
            {
                // sincronizo com todos os usuarios logados no servidor
                synchronized(usuarios)
                {
                    ComunicadoDeDesligamento comunicadoDeDesligamento = new ComunicadoDeDesligamento();

                    // Coloco todos meus usuarios dentro do percorre, para depois eu percorre-los
                    Iterator percorre = usuarios.iterator();

                    while(t == false) {
                        // verifico se ha um proximo item dentro da minha iteracao (nao precisarei do for
                        // do tipo "for(Jogador jogador : this.usuarios)"(foreach)) pois estou usando o iterator que interar minha colecao
                        if (!percorre.hasNext()) {
                            break; // quebro caso acabe os elementos da minha iteracao
                        } else {
                            // adiciono todos os elementos do percorre no Arraylist para eu poder usar os metodos .size, pegando o seu tamanho
                            // e percorrendo ele com um for
                            ArrayList Allgroup = (ArrayList) percorre.next();

                            try {
                                // percorro meu arraylist e envio um comunicado de desligamento a todos dentro do ArrayList
                                for (int i = 0; i < Allgroup.size(); ++i) {
                                    ((Parceiro) Allgroup.get(i)).receba(comunicadoDeDesligamento);
                                    // removo todos antes do servidor desligar
                                    ((Parceiro) Allgroup.get(i)).adeus();
                                }
                            } catch (Exception err) {
                            }
                        }
                    }
                }
                System.out.println("O servidor foi desativado!\n");
                System.exit(0);
            }
            else
                System.out.println("Comando invalido!\n");
        }
    }
}