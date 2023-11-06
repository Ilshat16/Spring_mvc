package ru.ilshat.springcrud.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import ru.ilshat.springcrud.models.Person;

@Component
public class PersonDAO {
	
	private final EntityManager entityManager;

	@Autowired
	public PersonDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Transactional
	public void testNPlus1() {
		Session session = entityManager.unwrap(Session.class);
		
//		List<Person> people = session.createQuery("from Person", Person.class)
//				.getResultList();
//		
//		for (Person person : people)
//			System.out.println("Person" + person.getName() + "has" + person.getItems());;
	
		Set<Person> people = new HashSet<Person>(session.createQuery("select p from Person p left join fetch p.items")
				.getResultList());
		
		for (Person person : people)
			System.out.println("Person " + person.getName() + " has: " + person.getItems());
	}
	
	
}
