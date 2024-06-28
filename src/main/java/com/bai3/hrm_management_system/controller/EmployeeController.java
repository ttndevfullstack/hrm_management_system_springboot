package com.bai3.hrm_management_system.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import com.bai3.hrm_management_system.model.Employee;
import com.bai3.hrm_management_system.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Selim Horri
 */
@Controller
@RequestMapping(value = {"/employees"})
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping(value = {"/list"})
	public String displayEmployeesList(final Model model) {
		List<Employee> employees = this.employeeRepository.findAll();
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
	public String handleEmployeeAdd(@ModelAttribute("employee") final Employee employee, final BindingResult error, final Model model) {
		
		if (error.hasErrors()) {
			System.err.println(error);
			return "redirect:/app/employees/employees-list";
		}
		
		Employee emp = this.employeeRepository.save(employee);
		System.err.println(emp);
		model.addAttribute("msgColour", "success");
		model.addAttribute("msg", "Employee saved successfully");
		return "employees/employees-add";
	}

	@GetMapping(value = {"/edit"})
	public String displayEmployeesEdit(@RequestParam("id") final String id, final Model model) {
		Optional<Employee> employee = this.employeeRepository.findById(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		return "employees/employees-edit";
	}

	@PostMapping(value = {"/edit"})
	public String handleEmployeesEdit(@ModelAttribute("employee") final Employee employee, final BindingResult error, final Model model) {
		
		if (error.hasErrors()) {
			System.err.println(error);
			return "redirect:/app/employees/employees-list";
		}
		
		final Employee emp = this.employeeRepository.save(employee);
		 System.err.println(emp);
		model.addAttribute("msgColour", "success");
		model.addAttribute("msg", "Employee updated successfully!");
		return "employees/employees-edit";
	}

	@GetMapping(value = {"/delete"})
	public String handleEmployeeDelete(@RequestParam("id") final String id) {
		this.employeeRepository.deleteById(Integer.parseInt(id));
		return "redirect:/app/employees/employees-list";
	}
}









