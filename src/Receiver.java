import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Receiver implements Runnable{
    private static ArrayList<Mensagem> msgList = new <Mensagem>ArrayList(); //Array onde armazena as mensagens para cada usuário.
    public boolean flag = true;
    private Socket emissor;

    public Receiver(Socket emissor){
        this.emissor = emissor; //Servidor.
        Thread t = new Thread(this);
        t.start();
    }

    /*
    * Da Lista de Mensagens recebidas, função mostra a lista em formato de pilha na tela.
    * */

    public void getPilha() {
        if (this.msgList.isEmpty()){
            System.out.println("Você não possui mensagens.");
        }else{
            for(int i = this.msgList.size()-1; i>=0;i--){
                System.out.println();
                System.out.println("Remetente: "+this.msgList.get(i).getRemetente());
                System.out.println("Destinatario: "+this.msgList.get(i).getDestinatario());
                System.out.println("Data: "+this.msgList.get(i).getDate());
                System.out.println("Assunto: "+this.msgList.get(i).getAssunto());
                System.out.println("Texto: "+this.msgList.get(i).getTexto());
                System.out.println();
            }
        }
    }

    @Override
    public void run() {

        try {
            ObjectInputStream entrada = null;
            while (flag) {
                /*
                * Thread para cada usuário ficar recebendo mensagem em qualquer momento.
                * */
                entrada = new ObjectInputStream(this.emissor.getInputStream());
                Mensagem msg = (Mensagem) entrada.readObject();
                /*
                * Adiciona as mensagens nem uma lista.
                * */
                this.msgList.add(msg);
                System.out.println();
                System.out.println("***Nova Mensagem***");
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
