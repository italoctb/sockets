import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {
    public static void main(String[] args) {
        new Servidor(2130).execute();
    }
    private int port;
    static private ArrayList ipArrayList = new ArrayList();

    public Servidor(int port) {
        this.port = port;
    }

    public void execute(){
        try {
            ServerSocket servidor = new ServerSocket(this.port);
            System.out.println("Porta 2130 aberta!");
            while(true){
                Socket user = servidor.accept();
                System.out.println("Conectado com "+user.getInetAddress().getHostAddress());
                this.ipArrayList.add(user.getInetAddress().getHostAddress());
            }
        }catch (IOException e) {
            System.out.println("Não obteve conexão.");
            e.printStackTrace();
        }
    }
}
