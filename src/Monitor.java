import java.io.*;
import java.net.Socket;

public class Monitor implements Runnable {
    private Servidor servidor;
    private Socket cliente;

    public Monitor(Servidor servidor, Socket cliente) {
        this.servidor = servidor;
        this.cliente = cliente;
        Thread t = new Thread(this);
        t.start();
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
            System.out.println();
            if(servidor.clienteArrayList.isEmpty()){
                System.out.println("Sem clientes!");
            }else{
                for (int i = 0; i < servidor.clienteArrayList.size(); i++) {
                    if (servidor.clienteArrayList.get(i).getName().intern() ==
                            msg.getDestinatario().intern()) {
                        ObjectOutputStream saida = new ObjectOutputStream(servidor.ipArrayList.get(i).getOutputStream());
                        saida.writeObject(msg);
                        saida.flush();
                    }
                    if(!flag && servidor.clienteArrayList.get(i).getName().intern()== msg.getRemetente().intern()){
                        servidor.clienteArrayList.remove(i);
                        servidor.ipArrayList.remove(i);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

