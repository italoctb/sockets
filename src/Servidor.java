import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {
    /*
    * clienteArrayList = Um array de usuários, associando nomes com a ordem que for entrando no servidor.
    * ipArrayList = um array de sockets, associando as conexões com a ordem que for entrando para um futuro acesso.
    * */
    static public  ArrayList<Cliente> clienteArrayList = new <Cliente>ArrayList();
    static  ArrayList<Socket> ipArrayList = new <Socket>ArrayList();
    private int port;
    public Servidor(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        new Servidor(2130).execute();     //Executando Servidor.
    }

    public void execute(){
        try {
            ServerSocket servidor = new ServerSocket(this.port);     //Abrindo a porta para o servidor.
            System.out.println("Porta 2130 aberta!");
            while(true){
                /*
                * Criamos um laço de repetição indefinida para o servidor sempre buscar novos usuários.
                * E os usuários criados mandamos para uma thread própria, tal que a thread armazena o endereço da conexão e os
                * dados do servidor.
                * */
                Socket user = servidor.accept();
                System.out.println("Conectado com "+user.getInetAddress().getHostAddress());
                this.ipArrayList.add(user);
                Monitor t = new Monitor(this, user);
            }
        }catch (IOException e) {
            System.out.println("Não obteve conexão.");
            e.printStackTrace();
        }
    }
}
