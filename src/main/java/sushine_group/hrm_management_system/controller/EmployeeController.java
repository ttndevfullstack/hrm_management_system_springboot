package sushine_group.hrm_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sushine_group.hrm_management_system.model.Employee;
import sushine_group.hrm_management_system.model.NhanVien;
import sushine_group.hrm_management_system.repository.EmployeeRepository;
import sushine_group.hrm_management_system.repository.NhanVienRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Selim Horri
 */
@Controller
@RequestMapping(value = {"/employees"})
public class EmployeeController {
	@Autowired
	private NhanVienRepository employeeRepository;

	@GetMapping(value = {"/list"})
	public String displayEmployeesList(final Model model) {
		List<NhanVien> employees = this.employeeRepository.findAll();
		model.addAttribute("size", employees.size() + " " + this.getClass().getSimpleName().replace("Controller", "") + "s");
		model.addAttribute("employees", employees);
		return "employees/employees-list";
	}

	@GetMapping(value = {"/add"})
	public String displayEmployeeAdd(final Model model) {
		model.addAttribute("employee", new Employee());
		return "employees/employees-add";
	}

	@PostMapping(value = {"/add"})
	public String handleEmployeeAdd(@ModelAttribute("employee") final NhanVien employee, final BindingResult error, final Model model) {
		
		if (error.hasErrors()) {
			System.err.println(error);
			return "redirect:/app/employees/employees-list";
		}
		
		NhanVien emp = this.employeeRepository.save(employee);
		System.err.println(emp);
		model.addAttribute("msgColour", "success");
		model.addAttribute("msg", "Employee saved successfully");
		return "employees/employees-add";
	}

	@GetMapping(value = {"/edit"})
	public String displayEmployeesEdit(@RequestParam("id") final String id, final Model model) {
		Optional<NhanVien> employee = this.employeeRepository.findById(id);
		model.addAttribute("employee", employee);
		return "employees/employees-edit";
	}

	@PostMapping(value = {"/edit"})
	public String handleEmployeesEdit(@ModelAttribute("employee") final NhanVien employee, final BindingResult error, final Model model) {
		
		if (error.hasErrors()) {
			System.err.println(error);
			return "redirect:/app/employees/employees-list";
		}
		
		final NhanVien emp = this.employeeRepository.save(employee);
		 System.err.println(emp);
		model.addAttribute("msgColour", "success");
		model.addAttribute("msg", "Employee updated successfully!");
		return "employees/employees-edit";
	}

	@GetMapping(value = {"/delete"})
	public String handleEmployeeDelete(@RequestParam("id") final String id) {
		this.employeeRepository.deleteById(id);
		return "redirect:/app/employees/employees-list";
	}
}









