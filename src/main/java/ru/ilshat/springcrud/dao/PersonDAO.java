package ru.ilshat.springcrud.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.ilshat.springcrud.models.Person;

@Component
public class PersonDAO {
	
	private final SessionFactory sessionFactory;
	
	@Autowired
	public PersonDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Transactional(readOnly = true)
	public List<Person> index() {
		Session session = sessionFactory.getCurrentSession();

		List<Person> people = session.createQuery("FROM Person", Person.class)
				.getResultList();

		return people;
	}
	
	@Transactional(readOnly = true)
	public Person show(String email) {
		Session session = sessionFactory.getCurrentSession();
		return (Person) session.createQuery("FROM Person where email = :personEmail", Person.class)
				.setParameter("personEmail", email).uniqueResult();
	}
	
	@Transactional(readOnly = true)
	public Person show(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Person.class, id);
	}
	
	@Transactional
	public void save(Person person) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(person);
	}
	 
	@Transactional
	public void update(int id, Person updatedPerson) {
		Session session = sessionFactory.getCurrentSession();
		Person person = session.get(Person.class, id);
		
		person.setName(updatedPerson.getName());
		person.setAge(updatedPerson.getAge());
		person.setEmail(updatedPerson.getEmail());
		person.setAddress(updatedPerson.getAddress());
	}
	
	@Transactional
	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		Person person = session.get(Person.class, id);
		session.remove(person);
	}
}
