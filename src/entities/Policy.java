package entities;

public class Policy {

    private int policyID;
    private String policyName;

    public Policy(){}

    public Policy(int policyID, String policyName) {
        this.policyID = policyID;
        this.policyName = policyName;
    }

    public int getPolicyID() {
        return policyID;
    }

    public void setPolicyID(int policyID) {
        this.policyID = policyID;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    @Override
    public String toString() {
        return "Policy{" +
                "policyID=" + policyID +
                ", policyName='" + policyName + '\'' +
                '}';
    }
}
