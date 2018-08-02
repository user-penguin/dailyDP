package reserve.control.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import reserve.control.domain.*;

@Controller
public class MainController {
	@Autowired
	private EmployeeRepository employeeRepository;
	private CandidateRepository candidateRepository;
	
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }		
	
	// Show all todos
    @RequestMapping(value="/employees")
    public String empList(Model model) {
        model.addAttribute("empList", employeeRepository.findAll());
        return "employees";
    }

    // Add new todo
    @RequestMapping(value = "/addEmp")
    public String addEmp(Model model){
    	model.addAttribute("emp", new Employee());
        return "addEmp";
    }     
    
    // Save new todo
    @RequestMapping(value = "/saveEmp", method = RequestMethod.POST)
    public String save(Employee employee){
        employeeRepository.save(employee);
        return "redirect:employees";
    }


}
