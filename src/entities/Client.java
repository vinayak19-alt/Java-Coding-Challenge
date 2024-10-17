package entities;

public class Client {

    private int clientID;
    private String clientName;
    private String contactInfo;
    private Policy policy;

    public Client(){

    }

    public Client(int clientID, String clientName, String contactInfo, Policy policy) {
        this.clientID = clientID;
        this.clientName = clientName;
        this.contactInfo = contactInfo;
        this.policy = policy;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }


}
