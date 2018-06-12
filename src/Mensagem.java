import java.io.Serializable;
import java.util.Date;

public class Mensagem implements Serializable {
    private String assunto = null;
    private String texto = null;
    private String remetente;
    private String destinatario;
    private String ip_destinatario;
    public boolean flag = true;
    Date d = new Date();
    private String date = d.toString();

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

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getIp_destinatario() {
        return ip_destinatario;
    }

    public void setIp_destinatario(String ip_destinatario) {
        this.ip_destinatario = ip_destinatario;
    }

    public String getDate() {
        return date;
    }


}