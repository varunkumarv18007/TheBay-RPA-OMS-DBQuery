package utility.email;

public class SendMailPOJO {
    String sendMailTo;
    String sendMailCc;
    String sendMailBcc;
    String sendMailFrom;
    String sendMailUsername;
    String sendMailPassword;
    String smtpHost;
    String sendMailBody;
    String sendMailSubject;
    String[] sendAttachmentFiles;
    int smtpPort;

  public SendMailPOJO() {

    }
    public String getSendMailTo() {

        return sendMailTo;
    }

    public void setSendMailTo(String sendMailTo) {
        this.sendMailTo = sendMailTo;
    }

    public String getSendMailCc() {
        return sendMailCc;
    }

    public void setSendMailCc(String sendMailCc) {
        this.sendMailCc = sendMailCc;
    }

    public String getSendMailBcc() {
        return sendMailBcc;
    }

    public void setSendMailBcc(String sendMailBcc) {
        this.sendMailBcc = sendMailBcc;
    }

    public String getSendMailFrom() {
        return sendMailFrom;
    }

    public void setSendMailFrom(String sendMailFrom) {
        this.sendMailFrom = sendMailFrom;
    }

    public String getSendMailUsername() {
        return sendMailUsername;
    }

    public void setSendMailUsername(String sendMailUsername) {
        this.sendMailUsername = sendMailUsername;
    }

    public String getSendMailPassword() {
        return sendMailPassword;
    }

    public void setSendMailPassword(String sendMailPassword) {
        this.sendMailPassword = sendMailPassword;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String[] getSendAttachmentFiles() {
        return sendAttachmentFiles;
    }

    public void setSendAttachmentFiles(String[] sendAttachmentFiles) {
        this.sendAttachmentFiles = sendAttachmentFiles;
    }

    public int getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(int smtpPort) {
        this.smtpPort = smtpPort;
    }
}
