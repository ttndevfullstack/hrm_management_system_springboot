package sushine_group.hrm_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sushine_group.hrm_management_system.model.Department;
import sushine_group.hrm_management_system.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Selim Horri
 */
@Controller
@RequestMapping("/departments")
public class DepartmentController {

	@Autowired
	private DepartmentRepository departmentRepository;

	@GetMapping(value = {"/list"})
	public String displayDepartmentsList(final Model model) {
		List<Department> departments = this.departmentRepository.findAll();
		model.addAttribute("size", departments.size() + " " + this.getClass().getSimpleName().replace("Controller", "") + "s");
		model.addAttribute("departments", departments);
		return "departments/departments-list";
	}

	@GetMapping(value = {"/add"})
	public String displayDepartmentAdd(final Model model) {
		model.addAttribute("department", new Department());
		return "departments/departments-add";
	}

	@PostMapping(value = {"/add"})
	public String handleDepartmentAdd(@ModelAttribute("department") final Department department, final BindingResult error, final Model model) {
		
		if (error.hasErrors()) {
			System.err.println(error);
			return "redirect:/departments/list";
		}
		
		Department dept = this.departmentRepository.save(department);
		System.err.println(dept);
		model.addAttribute("msgColour", "success");
		model.addAttribute("msg", "Department saved successfully");
		return "departments/departments-add";
	}

	@GetMapping(value = {"/edit"})
	public String displayDepartmentsEdit(@RequestParam("id") final String id, final Model model) {
		
		Optional<Department> department = this.departmentRepository.findById(Integer.parseInt(id));
		model.addAttribute("department", department);
		return "departments/departments-edit";
	}

	@PostMapping(value = {"/edit"})
	public String handleDepartmentsEdit(@ModelAttribute("department") final Department department, final BindingResult error, final Model model) {
		
		if (error.hasErrors()) {
			System.err.println(error);
			return "redirect:/departments/list";
		}
		
		Department dept = this.departmentRepository.save(department);
		System.err.println(dept);
		model.addAttribute("msgColour", "success");
		model.addAttribute("msg", "Department updated successfully!");
		return "departments/departments-edit";
	}

	@GetMapping(value = {"/delete"})
	public String handleDepartmentDelete(@RequestParam("id") final String id) {
		this.departmentRepository.deleteById(Integer.parseInt(id));
		return "redirect:/departments/list";
	}
}









