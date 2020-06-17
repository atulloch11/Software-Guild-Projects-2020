/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corbos.fieldagent.controller;

import corbos.fieldagent.entities.Agency;
import corbos.fieldagent.entities.Agent;
import corbos.fieldagent.entities.Assignment;
import corbos.fieldagent.entities.Country;
import corbos.fieldagent.entities.SecurityClearance;
import corbos.fieldagent.service.LookupService;
import corbos.fieldagent.service.ValidationService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author ashleytulloch
 */
@Controller
public class MainController {
    
    Set<ConstraintViolation<Agent>> errors = new HashSet<>();
    Set<String> violations = new HashSet<>();
    
    @Autowired
    LookupService service;
    
    @Autowired
    ValidationService validation;
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("agents", service.findAllAgents());
        return "index";
    }
    
    @GetMapping("/agent")
    public String addAgent(Model model) {
        List<Agency> agencyList = service.findAllAgencies();
        List<SecurityClearance> clearList = service.findAllSecurityClearances();
        model.addAttribute("agencyList", agencyList);
        model.addAttribute("clearList", clearList);
        model.addAttribute("errors", errors);
        model.addAttribute("violations", violations);
        return "agent";
}
    @PostMapping("/addAgent")
    public String addTheAgent(Agent agent, HttpServletRequest request) {
        agent.setAgency(service.findAgencyById(Integer.parseInt(request.getParameter("agency"))));
        agent.setSecurityClearance(service.findSecurityClearanceById(Integer.parseInt(request.getParameter("securityClearanceId"))));
        
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        errors = validate.validate(agent);
        violations = validation.validateAgent(agent);
        
        

        if(!errors.isEmpty() || !violations.isEmpty()) {
            return "redirect:/agent";
        }
        service.saveAgent(agent);
        
        return "redirect:/";
        
    }
    
    @GetMapping("/confirmDeleteAgent{id}")
    public String deleteAgentPage(String id, Model model) {
        Agent agent = service.findAgentByIdentifier(id);
        List<Assignment> assignmentList = service.findAssignmentByIdentifier(id);
        
        model.addAttribute("assignmentNumber", assignmentList.size());
        model.addAttribute("agent", agent);
        return "confirmDeleteAgent";
    }
    
    
    @GetMapping("/deleteAgent{id}")
    public String deleteAgent(String id) {
        service.deleteAgent(id);
        return "redirect:/";
    }
    
    @GetMapping("/editAgent{id}")
    public String getEditAgentPage(String id, Model model) {
        Agent agent = service.findAgentByIdentifier(id);
        List<Agency> agencyList = service.findAllAgencies();
        List<SecurityClearance> clearList = service.findAllSecurityClearances();
        List<Assignment> assignmentList = service.findAssignmentByIdentifier(id);
        model.addAttribute("assignmentList", assignmentList);
        model.addAttribute("agencyList", agencyList);
        model.addAttribute("clearList", clearList);
        
        model.addAttribute("agent", agent);
        return "editAgent";
    }
    
    @PostMapping("/confirmEditAgent")
    public String performEditAgent(Agent agent, HttpServletRequest request) {
        agent.setAgency(service.findAgencyById(Integer.parseInt(request.getParameter("agency"))));
        agent.setSecurityClearance(service.findSecurityClearanceById(Integer.parseInt(request.getParameter("securityClearanceId"))));
        agent.setActive(Boolean.parseBoolean(request.getParameter("isActive")));
        
        service.updateAgent(agent);
        return "redirect:/";
    }

    @GetMapping("/assignment{id}")
    public String getAssignmentPage(String id, Model model) {
        Agent agent = service.findAgentByIdentifier(id);
        List<Agent> agentList = service.findAllAgents();
        List<Country> countryList = service.findAllCountries();
        
        
        model.addAttribute("countryList", countryList);
        model.addAttribute("agentList", agentList);
        model.addAttribute("agent", agent);
        return "assignment";
    }
    
    @PostMapping("/addAssignment")
    public String addAssignment(Assignment assignment, HttpServletRequest request) {
        assignment.setAgent(service.findAgentByIdentifier(request.getParameter("identifier")));
        assignment.setCountry(service.findCountryByCode(request.getParameter("countryCode")));
        
        service.addAssignment(assignment);
        return "redirect:/";
    }
    
    @GetMapping("/editAssignment{identifier}{id}")
    public String displayEditAssignmentPage(String identifier, String id, Model model) {
        Agent agent = service.findAgentByIdentifier(identifier);
        List<Agent> agentList = service.findAllAgents();
        List<Country> countryList = service.findAllCountries();
        Assignment assignment = service.findAssignmentById(Integer.parseInt(id));
        model.addAttribute("assignment", assignment);
        model.addAttribute("agent", agent);
        model.addAttribute("countryList", countryList);
        model.addAttribute("agentList", agentList);
        return "editAssignment";
    }
    
    @PostMapping("/editAssignment")
    public String editAssignment(Assignment assignment, HttpServletRequest request) {
        assignment.setAgent(service.findAgentByIdentifier(request.getParameter("identifier")));
        assignment.setCountry(service.findCountryByCode(request.getParameter("countryCode")));
        
        service.addAssignment(assignment);
        return "redirect:/editAgent?id=" + assignment.getAgent().getIdentifier();
    }
    
    @GetMapping("/deleteAssignment{id}")
    public String deleteAssignment(Integer id) {
        service.deleteAssignment(id);
        return "redirect:/";
    }
}
