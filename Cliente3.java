import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Locale;


public class Cliente3
{

    public static void main (String[] args)
    {
        String nome = "";
        char optar;
        char letra;
        String palavra;
        String tracinhos;
        String tracinhos2;
        Comunicado comunicado = null;
        boolean perdeu = false;
        int teste = 0;


        System.out.println("Digite o ip e a porta de quem voce quer jogar com!");
        System.out.println("Dica: Para iniciar o jogo no seu computador, digite localhost.\n\n");

        String host = null;
        int porta = 0;
        try
        {
            System.out.print("Ip: ");
            host = Teclado.getUmString();
            System.out.print("\nPorta: ");
            porta = Teclado.getUmInt();
        }
        catch(Exception e)
        {
            System.err.println("Valores invalidos");
            return;
        }

        Socket conexao=null;
        try
        {
            conexao = new Socket (host, porta);
        }
        catch (Exception erro)
        {
            System.err.println ("Indique o servidor e a porta corretos!\n");
            return;
        }

        ObjectOutputStream transmissor=null;
        try
        {
            transmissor =
                    new ObjectOutputStream(
                            conexao.getOutputStream());
        }
        catch (Exception erro)
        {
            System.err.println ("Indique o servidor e a porta corretos!\n");
            return;
        }

        ObjectInputStream receptor=null;
        try
        {
            receptor =
                    new ObjectInputStream(
                            conexao.getInputStream());
        }
        catch (Exception erro)
        {
            System.err.println ("Indique o servidor e a porta corretos!\n");
            return;
        }


        Parceiro servidor=null;
        try
        {
            servidor = new Parceiro(conexao, receptor, transmissor);
        }
        catch (Exception erro)
        {
            System.err.println ("Indique o servidor e a porta corretos!\n");
            return;
        }

        TratadoraDeComunicadoDeDesligamento tratadoraDeComunicadoDeDesligamento = null;
        try
        {
            tratadoraDeComunicadoDeDesligamento = new TratadoraDeComunicadoDeDesligamento (servidor);
        }
        catch (Exception erro)
        {} // sei que servidor foi instanciado
        tratadoraDeComunicadoDeDesligamento.start();


        try
        {
            System.out.println("\nPor favor, aguarde por 3 jogadores...");

        }catch(Exception erro) {}

        do {
            do {
                try {

                    System.out.println("Espere sua vez de jogar.");
                    System.out.println("Lembre-se, se alguem chutar a palavra certa primeiro voce perde");

                    // Espero algum comunicado desses aqui e trato em baixo.
                    do {
                        comunicado = (Comunicado) servidor.espie();
                    }
                    while (!(comunicado instanceof ComunicadoComecar)&& (!(comunicado instanceof ComunicadoDeDerrota)) && (!(comunicado instanceof ComunicadoVitoria)));

                    if (comunicado instanceof ComunicadoDeDerrota){
                        System.out.println("Perdeu!!!, Alguem acertou a palavra");
                        ComunicadoDeDerrota comunicadoDeDerrota = (ComunicadoDeDerrota) servidor.envie();
                        System.exit(0);
                    }

                    if(comunicado instanceof ComunicadoVitoria){
                        System.out.println("Parabens, o ultimo jogador chutou a palavra errada, voce venceu!!!");
                        System.exit(0);
                    }



                    ComunicadoComecar comunicadoComecar = (ComunicadoComecar) servidor.envie();


                    do {
                        comunicado = (Comunicado) servidor.espie();
                    } while (!(comunicado instanceof ComunicadoPodeJogar));
                    ComunicadoPodeJogar comunicadoPodeJogar = (ComunicadoPodeJogar) servidor.envie();




                } catch (Exception e) {
                    e.printStackTrace();
                }


                try {

                    do {
                        comunicado = (Comunicado) servidor.espie();
                    }
                    while (!(comunicado instanceof ComunicadodePalavra));

                    ComunicadodePalavra comunicadodePalavra = (ComunicadodePalavra) servidor.envie();


                    do {
                        comunicado = (Comunicado) servidor.espie();
                    }
                    while (!(comunicado instanceof ComunicadoDeTracinhos));

                    ComunicadoDeTracinhos comunicadoDeTracinhos = (ComunicadoDeTracinhos) servidor.envie();

                    tracinhos = comunicadoDeTracinhos.getTracinhos();


                    // chuta letra
                    try {
                        boolean t;
                        do {

                            System.out.println("Se quiser chutar a letra digite 'L' : ");
                            System.out.println("Se quiser chutar a Palavra digite 'P' : ");
                            System.out.println("Digite apenas as opcoes que sao instruidos acima");
                            letra = Character.toUpperCase(Teclado.getUmChar());

                            if (letra == 'L') {
                                break;
                            }
                            else if  (letra == 'P'){
                                break;
                            }
                        } while (t = true);

                        servidor.receba(new ComunicadoOpcoes(letra));
                        if (letra == 'L') {

                            System.out.println("Palavra: " + tracinhos);
                            System.out.println("Digite uma letra: ");
                            letra = Character.toUpperCase(Teclado.getUmChar());
                            servidor.receba(new ComunicadodeLetra(letra));


                            // Confere se a letra ja foi digitada!!

                            do {
                                comunicado = (Comunicado) servidor.espie();
                            } while (!(comunicado instanceof ComunicadoLetrasJaDigitas) && !(comunicado instanceof ComunicadoNaoDigitada) && !((comunicado instanceof ComunicadoJogadorAnta)) && (!(comunicado instanceof ComunicadoErroLetra)));

                            if(comunicado instanceof ComunicadoJogadorAnta){
                                System.out.println("Voce digitou uma letra que ja estava digitada.");
                            }

                            // Erro da letra

                            if(comunicado instanceof ComunicadoErroLetra){

                                System.out.println("Você errou a letra e perdeu a vez");

                                //comunicado = (Comunicado) servidor.envie();
                            }

                            comunicado = (Comunicado) servidor.envie();



                            // Logica de letra ja digitada (Carrega um string com as letras ja digitadas)
                            do {
                                comunicado = (Comunicado) servidor.espie();
                            }
                            while (!(comunicado instanceof ComunicadoLetrasJaDigitas));
                            ComunicadoLetrasJaDigitas comunicadoLetrasJaDigitas = (ComunicadoLetrasJaDigitas) servidor.envie();


                            // Recebimento do tracinhos tratado e revelado.
                            do {
                                comunicado = (Comunicado) servidor.espie();
                            }
                            while (!(comunicado instanceof ComunicadoDeTracinhos));

                            comunicadoDeTracinhos = (ComunicadoDeTracinhos) servidor.envie();

                            tracinhos = comunicadoDeTracinhos.getTracinhos();

                            System.out.println("Palavra: " + tracinhos);
                            System.out.println("Digitadas: " + comunicadoLetrasJaDigitas);

                            servidor.receba(new ComunicadoJaJoguei());



                        }// Enviando palavra chutada para o servidor atráves do comunicadoCaregoChute
                        else if(letra == 'P'){


                            System.out.println("Chute a palavra, lembre-se voce so tem UMA chance!!");
                            palavra = (Teclado.getUmString().toUpperCase());
                            servidor.receba(new ComunicadoCaregoChute(palavra));


                            do {
                                comunicado = (Comunicado) servidor.espie();
                            }
                            while (!(comunicado instanceof ComunicadoErroAPalavra) && (!(comunicado instanceof ComunicadoAcertoPalavra))&& (!(comunicado instanceof ComunicadoDeDerrota)));


                            if(comunicado instanceof ComunicadoAcertoPalavra){
                                System.out.println("Voce acertou a palavra parabens!, VITORIA!, a palavra era realmente\n" + palavra);
                                ComunicadoAcertoPalavra comunicadoAcertoPalavra = (ComunicadoAcertoPalavra) servidor.envie();
                                System.exit(0);
                            }

                            if(comunicado instanceof ComunicadoDeDerrota){
                                System.out.println("Voce errou, infelizmente tera que deixar o jogo, ate a proxima");
                                ComunicadoDeDerrota comunicadoDeDerrota = (ComunicadoDeDerrota) servidor.envie();
                                System.exit(0);
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } catch (Exception erro) {
                    System.err.println("Erro de comunicacao com o servidor;");
                    System.err.println("Tente novamente!");
                    System.err.println("Caso o erro persista, termine o programa");
                    System.err.println("e volte a tentar mais tarde!");
                }
                try {
                    do {
                        comunicado = (Comunicado) servidor.espie();
                    } while (!(comunicado instanceof ComunicadoPulaVez));
                    ComunicadoPulaVez comunicadoPulaVez = (ComunicadoPulaVez) servidor.envie();
                }catch(Exception e){}

            }
            while (!(comunicado instanceof ComunicadoPulaVez));

        }while (teste == 0);

        try
        {
            System.out.println("\nVoce saiu do jogo.\n");
            servidor.receba(new ComunicadoDeDesligamento());
        }
        catch(Exception ex)
        {}
    }
}