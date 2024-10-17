package dao;

import entities.Policy;

import java.util.List;

public interface IPolicyService {

    public boolean createPolicy(Policy policy);

    public Policy getPolicy(int policyID);

    public List<Policy> getAllPolicies();

    public boolean updatePolicy(Policy policy);

    public boolean deletePolicy(int policyID);
}
