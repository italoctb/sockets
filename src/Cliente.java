import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
    private String Name;
    private String ip;
    private int port;
    public Socket client;

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
        BufferedReader t_terminal = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Digite seu nome: ");
        Nome = t_terminal.readLine();
        System.out.print("Digite seu ip: ");
        Ip = t_terminal.readLine();
        Cliente cliente = new Cliente(Nome, Ip, 2130);
        Receiver rec = new Receiver(cliente.client.getInputStream());
    }
}
