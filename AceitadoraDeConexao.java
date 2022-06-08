import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

// Essa classe e responsavel por controlar
public class AceitadoraDeConexao extends Thread
{
    private static final int PORTA_PADRAO = 3000;
    private ServerSocket pedido;
    // Um objeto amarzenando o outro do mesmo tipo.
    private ArrayList<ArrayList<Parceiro>> usuarios;
    private ArrayList<Parceiro> grupode3 = new ArrayList();
    Parceiro usuario = null;


    public AceitadoraDeConexao(String escolha, ArrayList<ArrayList<Parceiro>> usuarios) throws Exception
    {
        // Porta padrao que e 3000
        int porta = AceitadoraDeConexao.PORTA_PADRAO;

        if (escolha != null)
        {
            porta = Integer.parseInt(escolha);
        }

        try
        {
            this.pedido = new ServerSocket(porta);
        }
        catch (Exception  erro)
        {
            throw new Exception ("Porta invalida");
        }

        if (usuarios==null)
            throw new Exception ("Usuarios ausentes");

        else {
            this.usuarios = usuarios;
        }


    }

//run para is fazendo a conexao com o usario e o servidor
    public void run ()
    {
        // dentro desse loop espero a conexao dos usuarios para la em baixo eu adiciona-los ao grupo de 3
        while(true)
        {
            //rodo esse while para fazer as conxoes com os transmissores
            Socket conexao=null;
            try
            {
                conexao = this.pedido.accept(); //conectou-se
            }
            catch (Exception erro)
            {
                continue;
            }
            Parceiro usuario = null;

            // Crio meus transmissores e receptores a partir de cada conexao de clientes

            ObjectOutputStream transmissor;
            try {
                transmissor = new ObjectOutputStream(conexao.getOutputStream());
            } catch (Exception err) {
                return;
            }

            ObjectInputStream receptor = null;
            try {
                receptor = new ObjectInputStream(conexao.getInputStream());
            } catch (Exception err) {
                try {
                    transmissor.close();
                } catch (Exception er) {
                }

                return;
            }
            try {
                usuario = new Parceiro(conexao, receptor, transmissor);
            } catch (Exception err) {
            }

            //se o usuario nao for null ele sera adicionado ao grupo de tres
            if (usuario != null) {
                this.grupode3.add(usuario);
            }

            // Entrego como parametro para a minha supervisora a conexao e o grupo de usuarios.

            SupervisoraDeConexao supervisoraDeConexao=null;

            try {
                // Aqui pego meu grupo de 3 e jogo no arraylist usuarios
                if (this.grupode3.size() == 3) {
                    synchronized(this.usuarios) {
                        this.usuarios.add(this.grupode3);
                    }
                    // apos as adicoes entrego para minha supervisora todos os meus usuarios e o grupo de 3 para ela gerenciar os games

                    supervisoraDeConexao = new SupervisoraDeConexao(this.grupode3, this.usuarios);
                    // ativo o run da thread que inicia um game com 3 usuarios que chegaram respectivamente
                    supervisoraDeConexao.start();
                    // quando acabo o procedimento com um grupo, instacio um novo para ele subir de volta ao topo do loop esperando mais conexoes
                    this.grupode3 = new ArrayList();
                }
            } catch (Exception err) {
            }
        }
    }



}