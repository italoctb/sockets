import java.io.*;
import java.net.Socket;
import java.io.Serializable;

public class Cliente implements Serializable {
    private String Name;
    private String ip;
    public boolean flag = false;

    public String getName() {
        return Name;
    }

    public Cliente(String Name, String ip) {
        this.Name = Name;
        this.ip = ip;
    }

    public static void main(String[] args) throws IOException{
        String Nome;
        String Ip;
        boolean flag = true;
        BufferedReader t_terminal = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Digite seu nome: ");
        Nome = t_terminal.readLine();
        System.out.print("Digite seu ip: ");
        Ip = t_terminal.readLine();
        Socket cliente_socket = new Socket(Ip, 2130);
        Cliente cliente = new Cliente(Nome, Ip);
        Receiver rec = new Receiver(cliente_socket);
        ObjectOutputStream objSaida = new ObjectOutputStream(cliente_socket.getOutputStream());
        objSaida.writeObject(cliente);
        objSaida.flush();
        while(flag){
            objSaida = new ObjectOutputStream(cliente_socket.getOutputStream());
            Mensagem msg = new Mensagem(cliente.getName());
            System.out.print("Digite o nome do Destinatário: ");
            msg.setDestinatario(t_terminal.readLine());
            System.out.print("Digite o assunto: ");
            msg.setAssunto(t_terminal.readLine());
            System.out.println("Digite o texto: ");
            msg.setTexto(t_terminal.readLine());
            System.out.print("Deseja continuar?(Y/N) ");
            if (t_terminal.readLine().intern() == "N"){
                flag = false;
                rec.flag = false;
                msg.flag = false;
                objSaida.writeObject(msg);
                System.out.println();
                System.out.print("Você deseja visualizar suas mensangens recebidas ?(Y/N) ");
                if (t_terminal.readLine().intern() == "Y"){
                    rec.getPilha();
                }
            }else{
                objSaida.writeObject(msg);
                objSaida.flush();
            }
        }
        objSaida.flush();
        objSaida.close();
        t_terminal.close();
    }
}

