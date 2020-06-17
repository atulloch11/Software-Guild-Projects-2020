/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corbos.fieldagent.service;

import corbos.fieldagent.data.AgencyRepository;
import corbos.fieldagent.data.AgentRepository;
import corbos.fieldagent.data.AssignmentRepository;
import corbos.fieldagent.data.CountryRepository;
import corbos.fieldagent.data.SecurityClearanceRepository;
import corbos.fieldagent.entities.Agent;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author ashleytulloch
 */
@Service
public class ValidationService {
    
    private final AgencyRepository agencyRepo;
    private final CountryRepository countryRepo;
    private final SecurityClearanceRepository securityRepo;
    private final AgentRepository agentRepo;
    private final AssignmentRepository assignmentRepo;

    public ValidationService(AgencyRepository agencyRepo,
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
    
    public Set<String> validateAgent(Agent agent) {
        Set<String> violations = new HashSet();
        int height = agent.getHeight();
        
        if (height == 0 || height < 32 || height > 96) {
            violations.add("Please enter a height between 32 and 96");
        }
        
        LocalDate birthDate = agent.getBirthDate();
        
        if (birthDate == null) {
            violations.add("Please enter a birthdate!");
        }
        
        String identifier = agent.getIdentifier();
        
        if (identifier == null) {
            violations.add("Please enter an Identification Number for Agent");
        }
        
        LocalDate activationDate = agent.getActivationDate();
        
        if (agent.getAgency() == null) {
            violations.add("Please select an Agency");
        }
        
        if (agent.getSecurityClearance() == null) {
            violations.add("Please select a Security Clearance");
        }
        
        
        return violations;
    }
    
    
}
