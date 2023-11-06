package ru.ilshat.springcrud.models;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "person")
public class Person {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Name should not be empty")
	@Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
	@Column(name = "name")
	private String name;
	
	@Min(value = 0, message = "Age should be greater than 0")
	@Column(name = "age")
	private int age;
	
	@NotEmpty(message = "Email should be not empty")
	@Email(message = "Email should be valid")
	@Column(name = "email")
	private String email;
	
	@Pattern(regexp="[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message="Address should be this format: Country, City, Postal code (6 digits)")
	@Column(name = "address")
	private String address;
	
	@OneToMany(mappedBy = "owner")
	private List<Item> items;
	
	@Column(name = "date_birth")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar dateBirth;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createAt;
	
	@Enumerated(EnumType.ORDINAL)
	private Mood mood;
	
	public Person() {
	}
	
	public Person(String name, int age, String email, String address) {
		this.name = name;
		this.age = age;
		this.email = email;
		this.address = address;
	}

	public Mood getMood() {
		return mood;
	}

	public void setMood(Mood mood) {
		this.mood = mood;
	}

	public Calendar getDateBirth() {
		return dateBirth;
	}
	
	public String getDateBirthToString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(dateBirth.getTime());
	}

	public void setDateBirth(Calendar dateBirth) {
		this.dateBirth = dateBirth;
	}

	public Calendar getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Calendar createAt) {
		this.createAt = createAt;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + ", email=" + email + ", address=" + address
				+ "]";
	}
}
