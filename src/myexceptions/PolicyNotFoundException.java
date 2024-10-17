package myexceptions;

public class PolicyNotFoundException extends Exception{

    public PolicyNotFoundException(){
        super("Policy Not Found");
    }
    public PolicyNotFoundException(String message){
        super(message);
    }
}
