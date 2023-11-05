package ru.ilshat.springcrud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import ru.ilshat.springcrud.models.Person;
import ru.ilshat.springcrud.services.PeopleService;
import ru.ilshat.springcrud.util.PersonValidator;

@Controller
@RequestMapping("/people")
public class PeopleController {
	
	private PersonValidator personValidator;
	
	private final PeopleService peopleService;
	
	@Autowired
	public PeopleController(PeopleService peopleService, PersonValidator personValidator) {
		this.peopleService = peopleService;
		this.personValidator = personValidator;
	}

	@GetMapping()
	public String index(Model model) {
		model.addAttribute("people", peopleService.findAll());
		return "people/index";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		model.addAttribute("person", peopleService.findOne(id));
		return "people/show";
	}
	
	@GetMapping("/new")
	public String newPerson(@ModelAttribute("person") Person person) {
		return "people/new";
	}
	
	@PostMapping()
	public String create(@ModelAttribute("person") @Valid Person person, 
						BindingResult bindingResulg) {
		
		personValidator.validate(person, bindingResulg);
		
		if(bindingResulg.hasErrors())
			return "people/new";
		
		peopleService.save(person);
		return "redirect:/people";
	}
	
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") int id, Model model) {
		model.addAttribute("person", peopleService.findOne(id));
		return "people/edit";
	}
	
	@PatchMapping("/{id}")
	public String update(@ModelAttribute("person") @Valid Person person, 
						BindingResult bindingResulg, @PathVariable("id") int id) {
		
		personValidator.validate(person, bindingResulg);
		
		if(bindingResulg.hasErrors())
			return "people/edit";
		
		peopleService.update(id, person);
		return "redirect:/people";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) { 
		peopleService.delete(id);
		return "redirect:/people";
	}
}
