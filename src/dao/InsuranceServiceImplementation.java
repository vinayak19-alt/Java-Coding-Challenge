package dao;

import utils.DBConnection;
import entities.Policy;
import myexceptions.PolicyNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InsuranceServiceImplementation implements IPolicyService{
    @Override
    public boolean createPolicy(Policy policy) {
        String sql = "INSERT INTO Policy (policyID, policyName) VALUES (?, ?)";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1,policy.getPolicyID());
            stmt.setString(2, policy.getPolicyName());
            int rowsUpdated = stmt.executeUpdate();
            if(rowsUpdated > 0){
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error Occurred 1: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Policy getPolicy(int policyID) {
        String sql = "SELECT * FROM Policy WHERE policyID = ?";
        Policy policy = null;
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, policyID);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                policy=  new Policy(rs.getInt("policyID"), rs.getString("policyName"));
            }
            if(policy == null){
                throw new PolicyNotFoundException("Policy with Policy ID: " + policyID + " not found");
            }
            return policy;
        } catch (SQLException e) {
            System.out.println("Error Occurred 2: " + e.getMessage());
        } catch (PolicyNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Policy> getAllPolicies() {
        String sql = "SELECT * FROM Policy";

        List<Policy> list = new ArrayList<>();

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                list.add(new Policy(rs.getInt("policyID"), rs.getString("policyName")));
            }
        } catch (SQLException e) {
            System.out.println("Error Occurred 3: " + e.getMessage());
        }
        return list;
    }

    @Override
    public boolean updatePolicy(Policy policy) {
        String sql = "UPDATE Policy SET policyName = ? WHERE policyID = ?";

        try(Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, policy.getPolicyName());
            stmt.setInt(2, policy.getPolicyID());
            int rowsUpdated = stmt.executeUpdate();
            if(rowsUpdated > 0){
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error Occurred 4: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deletePolicy(int policyID) {
        // SQL queries to delete associated records in the correct order
        String deletePayments = "DELETE FROM Payment WHERE clientID IN (SELECT clientID FROM Client WHERE policyID = ?)";
        String deleteClaims = "DELETE FROM Claim WHERE policyID = ?";
        String deleteClients = "DELETE FROM Client WHERE policyID = ?";
        String deletePolicy = "DELETE FROM Policy WHERE policyID = ?";


        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt1 = conn.prepareStatement(deletePayments);
             PreparedStatement stmt2 = conn.prepareStatement(deleteClaims);
             PreparedStatement stmt3 = conn.prepareStatement(deleteClients);
             PreparedStatement stmt4 = conn.prepareStatement(deletePolicy)) {

            stmt1.setInt(1, policyID);
            stmt2.setInt(1, policyID);
            stmt3.setInt(1, policyID);
            stmt4.setInt(1, policyID);

            stmt1.executeUpdate();
            stmt2.executeUpdate();
            stmt3.executeUpdate();

            int rowsAffected = stmt4.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Policy with ID " + policyID + " and associated records deleted successfully.");
                return true;
            } else {
                System.out.println("Policy with ID " + policyID + " not found.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error occurred 5: " + e.getMessage());
            return false;
        }
    }
}
