import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Monitor implements Runnable {
    private Servidor servidor;
    private Socket cliente;

    public Monitor(Servidor servidor, Socket cliente) {
        this.servidor = servidor;
        this.cliente = cliente;
    }

    @Override
    public void run() {
        ObjectInputStream entrada;
        boolean flag = true;
        while(flag){
            try {
                entrada = new ObjectInputStream(this.cliente.getInputStream());
                Cliente cliente = (Cliente) entrada.readObject();
                servidor.clienteArrayList.add(cliente);
                flag = cliente.flag;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        flag = true;
        while (flag) try {
            entrada = new ObjectInputStream(this.cliente.getInputStream());
            Mensagem msg = (Mensagem) entrada.readObject();
            flag = msg.flag;
            System.out.println();
            System.out.println("Remetente: " + msg.getRemetente());
            System.out.println("Destinatario: " + msg.getDestinatario());
            System.out.println("Data: " + msg.getDate());
            System.out.println("Assunto: " + msg.getAssunto());
            System.out.println("Texto: " + msg.getTexto());
            //                System.out.println("Index: "+this.index);
            System.out.println();
            for (int i = 0; i < servidor.clienteArrayList.size(); i++) {
                System.out.println(msg.getName().intern() + "->IPDEST");
                System.out.println(servidor.clienteArrayList.get(i).getName() + "->IPCLIENT");
                if (servidor.clienteArrayList.get(i).getName().intern() ==
                        msg.getName().intern()) {
                    System.out.println("Entrou");
                    ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
                    saida.writeObject(msg);
                    saida.flush();
                    saida.close();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

