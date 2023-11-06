package ru.ilshat.springcrud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.ilshat.springcrud.models.Item;
import ru.ilshat.springcrud.models.Person;
import ru.ilshat.springcrud.repositories.ItemsRepository;

@Service
public class ItemService {
	
	private final ItemsRepository itemsRepository;

	@Autowired
	public ItemService(ItemsRepository itemsRepository) {
		this.itemsRepository = itemsRepository;
	}
	
	public List<Item> findByItemName(String itemName) {
		return itemsRepository.findByItemName(itemName);
	}
	
	public List<Item> findByOwner(Person owner) {
		return itemsRepository.findByOwner(owner);
	}
	
	public void test() {
		System.out.println("Testing here with debug. Inside Hibernate Transaction");
	}
}
