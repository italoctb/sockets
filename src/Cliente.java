import java.io.*;
import java.net.Socket;
import java.io.Serializable;

public class Cliente implements Serializable {
    public boolean flag = false;
    private String Name;
    private String ip;

    public Cliente(String Name, String ip) {
        this.Name = Name;
        this.ip = ip;
    }

    public static void main(String[] args) throws IOException{
        String Nome;
        String Ip;
        boolean flag = true;
        BufferedReader t_terminal = new BufferedReader(new InputStreamReader(System.in));
        /*
        * Resebe dados do usuário e inicia uma conexão com o servidor na porta 2130.
        * */
        System.out.print("Digite seu nome: ");
        Nome = t_terminal.readLine();
        System.out.print("Digite seu ip: ");
        Ip = t_terminal.readLine();
        Socket cliente_socket = new Socket(Ip, 2130);
        Cliente cliente = new Cliente(Nome, Ip);
        /*
        * Cria uma thread para, simultaneamente, ficar apto a receber mensagens.
        * Envia o objeto cliente para o servidor.
        * */
        Receiver rec = new Receiver(cliente_socket);
        ObjectOutputStream objSaida = new ObjectOutputStream(cliente_socket.getOutputStream());
        objSaida.writeObject(cliente);
        objSaida.flush();
        while(flag){
            objSaida = new ObjectOutputStream(cliente_socket.getOutputStream());
            /*
            * Colhe dados para a contrução da mensagem e manda para o servidor.
            * */
            Mensagem msg = new Mensagem(cliente.getName());
            System.out.print("Digite o nome do Destinatário: ");
            msg.setDestinatario(t_terminal.readLine());
            System.out.print("Digite o assunto: ");
            msg.setAssunto(t_terminal.readLine());
            System.out.println("Digite o texto: ");
            msg.setTexto(t_terminal.readLine());
            System.out.print("Deseja continuar?(Y/N) ");
            if (t_terminal.readLine().intern() == "N"){ //Acionamento da flag e interrompre a criação de Mensagens.
                flag = false;
                msg.flag = false;
                objSaida.writeObject(msg);
                System.out.println();
                System.out.print("Você deseja visualizar suas mensangens recebidas ?(Y/N) ");
                if (t_terminal.readLine().intern() == "Y"){
                    rec.getPilha(); //Mostra a pilha de mensagens recebidas.
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

    public String getName() {
        return Name;
    }
}

