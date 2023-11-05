package ru.ilshat.springcrud.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.ilshat.springcrud.models.Person;
import ru.ilshat.springcrud.services.PeopleService;

@Component
public class PersonValidator implements Validator{

	private final PeopleService peopleService;
	
	@Autowired
	public PersonValidator(PeopleService peopleService) {
		this.peopleService = peopleService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Person.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Person person = (Person) target;
		
		if(peopleService.findOne(person.getEmail()) != null)
			errors.rejectValue("email", "", "This email is already taken");
	}

}
