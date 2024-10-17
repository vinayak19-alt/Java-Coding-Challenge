package mainmod;

import dao.InsuranceServiceImplementation;
import entities.Policy;

import java.util.Scanner;

public class MainModule {
    public static void main(String[] args) {
        InsuranceServiceImplementation insuranceServiceImplementation = new InsuranceServiceImplementation();

        System.out.println("Enter choice for what you want to do:");
        System.out.println("1. Create a new Policy");
        System.out.println("2. Get a Policy by PolicyID");
        System.out.println("3. Get all Policies");
        System.out.println("4. Update a policy");
        System.out.println("5. Delete a policy");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice){
            case 1:
                addPolicy();
                break;
            case 2:
                fetchPolicy();
                break;
            case 3:
                fetchAllPolicies();
                break;
            case 4:
                updatePolicy();
                break;
            case 5:
                deletePolicy();
        }
    }



    //for creating a new policy
    private static void addPolicy() {
        InsuranceServiceImplementation insuranceServiceImplementation = new InsuranceServiceImplementation();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter policy ID:");
        int id = sc.nextInt();
        System.out.println("Enter the Policy Name: ");
        String str = sc.next();
        Policy policy = new Policy(id, str);
        insuranceServiceImplementation.createPolicy(policy);
    }
    //for getting a policy
    private static void fetchPolicy() {
        InsuranceServiceImplementation insuranceServiceImplementation = new InsuranceServiceImplementation();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter policy ID to fetch: ");
        int id = sc.nextInt();
        System.out.println(insuranceServiceImplementation.getPolicy(33));
    }
    //for fetching all policies
    private static void fetchAllPolicies(){
        InsuranceServiceImplementation insuranceServiceImplementation = new InsuranceServiceImplementation();
        System.out.println(insuranceServiceImplementation.getAllPolicies());
    }
    //for updating the policy
    private static void updatePolicy(){
        InsuranceServiceImplementation insuranceServiceImplementation = new InsuranceServiceImplementation();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter policy ID:");
        int id = sc.nextInt();
        System.out.println("Enter the new value: ");
        String str = sc.next();
        Policy policy = new Policy(id, str);
        insuranceServiceImplementation.updatePolicy(policy);
    }

    private static void deletePolicy(){
        InsuranceServiceImplementation insuranceServiceImplementation = new InsuranceServiceImplementation();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the policy id to be removed");
        int id = sc.nextInt();
        System.out.println(insuranceServiceImplementation.deletePolicy(22));
    }
}