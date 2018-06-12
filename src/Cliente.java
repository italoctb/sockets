import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
    private String Name;
    private String ip;
    private int port;
    public Socket client;
    public boolean flag = false;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Cliente(String Name, String ip, int port) {
        this.Name = Name;
        this.ip = ip;
        this.port = port;
        try (Socket socket = this.client = new Socket(this.ip, this.port)) {
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        String Nome;
        String Ip;
        boolean flag = true;
        BufferedReader t_terminal = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Digite seu nome: ");
        Nome = t_terminal.readLine();
        System.out.print("Digite seu ip: ");
        Ip = t_terminal.readLine();
        Cliente cliente = new Cliente(Nome, Ip, 2130);
        Receiver rec = new Receiver(cliente.client);
        ObjectOutputStream objSaida = new ObjectOutputStream(cliente.client.getOutputStream());
        objSaida.writeObject(cliente);
        objSaida.flush();
        objSaida.close();
        while(flag){
            objSaida = new ObjectOutputStream(cliente.client.getOutputStream());
            Mensagem msg = new Mensagem();
            System.out.print("Digite o nome do Destinatário: ");
            msg.setDestinatario(t_terminal.readLine());
            System.out.print("Digite o assunto: ");
            msg.setAssunto(t_terminal.readLine());
            System.out.println("Digite o texto: ");
            msg.setTexto(t_terminal.readLine());
            System.out.print("Deseja continuar?(Y/N) ");
            if (t_terminal.readLine().intern() == "N"){
                msg.flag = false;
                flag = false;
                objSaida.writeObject(msg);
                System.out.println();
                System.out.print("Você deseja visualizar suas mensangens recebidas ?(Y/N) ");
                if (t_terminal.readLine().intern() == "Y"){
                    rec.getPilha();
                }
                objSaida.close();
            }else{
                objSaida.writeObject(msg);
                objSaida.flush();
            }
        }
        t_terminal.close();
    }
}

