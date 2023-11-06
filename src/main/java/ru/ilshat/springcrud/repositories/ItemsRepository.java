package ru.ilshat.springcrud.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.ilshat.springcrud.models.Item;
import ru.ilshat.springcrud.models.Person;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Integer>{
	List<Item> findByOwner(Person owner);
	List<Item> findByItemName(String itemName);
	
}
