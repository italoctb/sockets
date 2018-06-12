import java.io.InputStream;
import java.util.Scanner;

public class Receiver implements Runnable{
    private InputStream destinatario;

    public Receiver(InputStream destinatario) {
        this.destinatario = destinatario;
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        Scanner s = new Scanner(this.destinatario);
        while(s.hasNextLine()){
            System.out.println(s.nextLine());
        }
    }
}
