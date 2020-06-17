package corbos.fieldagent.service;

import corbos.fieldagent.data.AgencyRepository;
import corbos.fieldagent.data.AgentRepository;
import corbos.fieldagent.data.AssignmentRepository;
import corbos.fieldagent.data.CountryRepository;
import corbos.fieldagent.data.SecurityClearanceRepository;
import corbos.fieldagent.entities.Agency;
import corbos.fieldagent.entities.Agent;
import corbos.fieldagent.entities.Assignment;
import corbos.fieldagent.entities.Country;
import corbos.fieldagent.entities.SecurityClearance;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LookupService {

    private final AgencyRepository agencyRepo;
    private final CountryRepository countryRepo;
    private final SecurityClearanceRepository securityRepo;
    private final AgentRepository agentRepo;
    private final AssignmentRepository assignmentRepo;

    public LookupService(AgencyRepository agencyRepo,
            CountryRepository countryRepo,
            SecurityClearanceRepository securityRepo,
            AgentRepository agentRepo,
            AssignmentRepository assignmentRepo) {
        this.agencyRepo = agencyRepo;
        this.countryRepo = countryRepo;
        this.securityRepo = securityRepo;
        this.agentRepo = agentRepo;
        this.assignmentRepo = assignmentRepo;
    }
    
    public List<Agent> findAllAgents() {
        return agentRepo.findAll();
    }
    
    public Agent findAgentByIdentifier(String identifier) {
        return agentRepo.findById(identifier).orElse(null);
    }
    
    public void deleteAgent(String id) {
        List<Assignment> assignments = assignmentRepo.findByAgentIdentifier(id);
        for (Assignment assignment : assignments) {
            assignmentRepo.delete(assignment);
        }
        
        agentRepo.deleteById(id);
    }
    
    public void deleteAssignment(Integer id) {
        Assignment assignment = findAssignmentById(id);
        assignmentRepo.delete(assignment);
    }

    public List<Agency> findAllAgencies() {
        return agencyRepo.findAll();
    }

    public Agency findAgencyById(int agencyId) {
        return agencyRepo.findById(agencyId)
                .orElse(null);
    }

    public List<Country> findAllCountries() {
        return countryRepo.findAll();
    }

    public Country findCountryByCode(String countryCode) {
        return countryRepo.findById(countryCode)
                .orElse(null);
    }

    public List<SecurityClearance> findAllSecurityClearances() {
        return securityRepo.findAll();
    }

    public SecurityClearance findSecurityClearanceById(int securityClearanceId) {
        return securityRepo.findById(securityClearanceId)
                .orElse(null);
    }
    
    public List<Assignment> findAssignmentByIdentifier(String identifier) {
        return assignmentRepo.findByAgentIdentifier(identifier);
    }
    
    public Agent saveAgent(Agent agent) {
        return agentRepo.save(agent);
    }
    
    public Agent updateAgent(Agent agent) {
        return agentRepo.save(agent);
    }
    
    public Assignment addAssignment(Assignment assignment) {
        return assignmentRepo.save(assignment);
    }
    
    public Assignment findAssignmentById(Integer id) {
        return assignmentRepo.findById(id).orElse(null);
    }

    
}
