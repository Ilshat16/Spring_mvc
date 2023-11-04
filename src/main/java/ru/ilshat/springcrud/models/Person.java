package ru.ilshat.springcrud.models;

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
	
	public Person() {
	}
	
	public Person(String name, int age, String email, String address) {
		this.name = name;
		this.age = age;
		this.email = email;
		this.address = address;
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

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + ", email=" + email + ", address=" + address
				+ "]";
	}
}
