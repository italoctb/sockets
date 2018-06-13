import java.io.Serializable;
import java.util.Date;

public class Mensagem implements Serializable {
    /*
    * Estrutura da mensagem com a flag com a situação de logOff.
    * */
    public boolean flag = true;
    Date d = new Date();
    private String assunto = null;
    private String texto = null;
    private String remetente;
    private String destinatario;
    private String date = d.toString();

    public Mensagem(String remetente) {
        this.remetente = remetente;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getRemetente() {
        return remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getDate() {
        return date;
    }


}