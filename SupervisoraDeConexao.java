import java.io.*;
import java.net.Socket;
import java.util.ArrayList;



public class SupervisoraDeConexao extends Thread {

    private Parceiro usuario;
    private Socket conexao;
    private ArrayList<ArrayList<Parceiro>> usuarios;
    private static int qtdUsuarios = 0;
    private static int qtdUsuariosProntos = 0;
    char index;
    char opcao;
    Comunicado comunicado;
    Tracinhos tracinhos2;
    int t =0;
    int i = 0;
    private boolean pare = false;
    private ArrayList<Parceiro> grupode3 = new ArrayList<Parceiro>();

// esperando como parametro o grupo de 3 e os usuarios que armazena meu grupo de 3
    public SupervisoraDeConexao(ArrayList<Parceiro> grupode3, ArrayList<ArrayList<Parceiro>> usuarios) throws Exception {
        if (grupode3 == null) {
            throw new Exception("Conexao ausente");
        }
       else if (usuarios == null) {
            throw new Exception("Usuarios ausentes");
        }

        else{
        this.usuarios = usuarios;
        this.grupode3 = grupode3;
    }}

    public void run() {

        try {

            Palavra palavra = BancoDePalavras.getPalavraSorteada();
            Tracinhos tracinhos = null;
            ControladorDeLetrasJaDigitadas controladorDeLetrasJaDigitadas = new ControladorDeLetrasJaDigitadas();
            try {
                tracinhos = new Tracinhos(palavra.getTamanho());
            } catch (Exception erro) {
            }

            do {
                do {
                    // quando meu index bater 3 eu zero a variavel
                    if (t == 3){
                        t = 0;
                    }
                    // quando meu grupo de 3 fica com apenas 2 pessoas eu zero as duas variaveis
                    if(this.grupode3.size() == 2){
                        t=0;
                        i=0;
                    }
                    // quando tem apenas uma pessoa eu faco ele vencer
                    else if(this.grupode3.size() == 1){
                        t = 0;
                        ((Parceiro) this.grupode3.get(t)).receba(new ComunicadoVitoria());
                        this.grupode3.remove(t);
                    }
                    for (i = 0; i < this.grupode3.size(); i++) {

                        if (i == t) {

                            ((Parceiro) this.grupode3.get(t)).receba(new ComunicadoComecar());
                            ((Parceiro) this.grupode3.get(t)).receba(new ComunicadoPodeJogar());

                        }


                        //usuario.receba(new ComunicadodePalavra(palavra));
                        ((Parceiro) this.grupode3.get(t)).receba(new ComunicadodePalavra(palavra));

                        //usuario.receba(new ComunicadoDeTracinhos(tracinhos.toString()));
                        ((Parceiro) this.grupode3.get(t)).receba(new ComunicadoDeTracinhos(tracinhos.toString()));

                        while (!(comunicado instanceof ComunicadoOpcoes))

                            //ler comunicado
                            comunicado = this.grupode3.get(t).espie();

                        //pergunta opção pro cliente
                        ComunicadoOpcoes comunicadoOpcoes = (ComunicadoOpcoes) ((Parceiro) this.grupode3.get(t)).envie();

                       // logica chute de letra
                        if (comunicadoOpcoes.getOptar() == 'L') {

                            boolean pare = false;
                            // aqui comeÃ§am os chutes
                            try {
                                // da o char letra como um input

                                while (!(comunicado instanceof ComunicadodeLetra))
                                    comunicado = ((Parceiro) this.grupode3.get(t)).espie();
                                ComunicadodeLetra comunicadodeLetra = (ComunicadodeLetra) ((Parceiro) this.grupode3.get(t)).envie();

                                char letra = comunicadodeLetra.getLetra();

                                //comunicado de letra ja digitada
                                if (controladorDeLetrasJaDigitadas.isJaDigitada(letra)) {
                                    String digitada = controladorDeLetrasJaDigitadas.toString();

                                    this.grupode3.get(t).receba(new ComunicadoJogadorAnta());


                                    this.grupode3.get(t).receba(new ComunicadoLetrasJaDigitas(controladorDeLetrasJaDigitadas.toString()));


                                    this.grupode3.get(t).receba(new ComunicadoDeTracinhos(tracinhos.toString()));

                                }
                                //comunicado recebimento da letra


                                //se nao for digitada ele registra a letra

                                else {


                                    int qtd = palavra.getQuantidade(letra);

                                    if (qtd == 0) {
                                        this.grupode3.get(t).receba(new ComunicadoErroLetra());
                                        controladorDeLetrasJaDigitadas.registre(letra);
                                        this.grupode3.get(t).receba(new ComunicadoLetrasJaDigitas(controladorDeLetrasJaDigitadas.toString()));
                                        this.grupode3.get(t).receba(new ComunicadoDeTracinhos(tracinhos.toString()));

                                    }
                                    else {

                                        this.grupode3.get(t).receba(new ComunicadoNaoDigitada());

                                        // A letra é registrada (NAO IMPORTA SE JA FOI OU NAO DIGITADA)
                                        controladorDeLetrasJaDigitadas.registre(letra);

                                        this.grupode3.get(t).receba(new ComunicadoLetrasJaDigitas(controladorDeLetrasJaDigitadas.toString()));


                                        //se na palavra tem a letra que ja foi digitada


                                        // se tiver a letra ele percorre a palavra, ve quantas vezes ela aparece e substitui por tracinhos

                                        if (qtd > 0) {
                                            for (int q = 0; q < qtd; q++) {
                                                int posicao = palavra.getPosicaoDaIezimaOcorrencia(q, letra);
                                                tracinhos.revele(posicao, letra);
                                                // enviar tracinhos atualizados para o cliente.

                                            }
                                            this.grupode3.get(t).receba(new ComunicadoDeTracinhos(tracinhos.toString()));
                                        }

                                    }
                                }

                                do {

                                    comunicado = this.grupode3.get(t).espie();

                                } while (!(comunicado instanceof ComunicadoJaJoguei));
                                ComunicadoJaJoguei comunicadoJaJoguei = (ComunicadoJaJoguei) ((Parceiro) this.grupode3.get(t)).envie();

                                this.grupode3.get(t).receba(new ComunicadoPulaVez());


                            } catch (Exception erro) {
                                System.err.println(erro.getMessage());

                            }

                        }

                        //logica para chutar a palavra
                        if (comunicadoOpcoes.getOptar() == 'P') {

                            String aPalavra;
                            // jogo minha palavra dentro dessa variavel
                            aPalavra = palavra.toString();


                            do {
                                comunicado = (Comunicado) this.grupode3.get(t).espie();
                            }while (!(comunicado instanceof ComunicadoCaregoChute));

                            ComunicadoCaregoChute comunicadoCaregoChute = (ComunicadoCaregoChute) ((Parceiro) this.grupode3.get(t)).envie();

                            // se for a palavra errada

                            if (!aPalavra.equals(comunicadoCaregoChute.getString())) {

                                for(i = 0; i < this.grupode3.size(); ++i) {
                                    i =t;
                                    if (i == t) {
                                        ((Parceiro)this.grupode3.get(t)).receba(new ComunicadoDeDerrota());
                                        this.grupode3.remove(t);
                                    }
                                    break;
                                }
                            }
                            else {

                                for(i = 0; i < this.grupode3.size(); ++i) {
                                    if (i == t) {
                                        ((Parceiro)this.grupode3.get(t)).receba(new ComunicadoAcertoPalavra());
                                    }

                                    else if (i != t) {
                                        ((Parceiro)this.grupode3.get(i)).receba(new ComunicadoDeDerrota());
                                    }
                                }
                            }
                        }
                    // aumento meu indexador para ele mandar o comunicado para o proximo jogado do meu arraylist
                    t++;

                    }
                    continue;
                    }while (!pare);
                } while (true);


        } catch (Exception erro) {
            try {

            } catch (Exception falha)
            {} // so tentando fechar antes de acabar a thread
            return;
        }
    }
}
