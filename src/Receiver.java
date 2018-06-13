import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Receiver implements Runnable{
    private Socket emissor;
    private static ArrayList<Mensagem> msgList = new <Mensagem>ArrayList();

    public Receiver() {

    }

    public Receiver(Socket emissor) {
        this.emissor = emissor;
        Thread t = new Thread(this);
        t.start();
    }

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
            while (true){
//                System.out.println("ola");
                ObjectInputStream entrada = new ObjectInputStream(this.emissor.getInputStream());
                Mensagem msg = (Mensagem) entrada.readObject();
                System.out.println(msg.getRemetente());
                this.msgList.add(msg);
//                Scanner entrada = new Scanner(this.emissor.getInputStream());
//                while (entrada.hasNextLine()) {
//                    System.out.println(entrada.nextLine());
//                }
//                System.out.println("add!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
