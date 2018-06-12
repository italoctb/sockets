import java.io.InputStream;
import java.util.Scanner;

public class Receiver implements Runnable{
    private InputStream servidor;

    public Receiver(InputStream servidor) {
        this.servidor = servidor;
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        Scanner s = new Scanner(this.servidor);
        while(s.hasNextLine()){
            System.out.println(s.nextLine());
        }
    }
}
