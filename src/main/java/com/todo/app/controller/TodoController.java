package com.todo.app.controller;

import com.todo.app.model.Todo;
import com.todo.app.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
public class TodoController {

	@Autowired
	private TodoRepository repository;

	public TodoController(TodoRepository repository) {
		this.repository = repository;
	}

	@GetMapping("")
	public Collection<Todo> getAll() {
		return repository.findAll().stream().collect(Collectors.toList());
	}

	@PostMapping(value = "")
	public Todo add(@RequestBody Todo todo) {
		repository.save(todo);
		return todo;
	}

	@PutMapping(value = "/{id}")
	public Todo update(@PathVariable("id") String id, @RequestBody Todo todo) {
		repository.save(todo);
		return todo;
	}

	@GetMapping("/complete")
	public Collection<Todo> getComplete() {
		return repository.findAll().stream().filter(todo -> todo.getCompleted()).collect(Collectors.toList());
	}

	@GetMapping("/pending")
	public Collection<Todo> getPending() {
		return repository.findAll().stream().filter(todo -> !todo.getCompleted()).collect(Collectors.toList());
	}

	@GetMapping(value = "/{id}")
	public Optional<Todo> findById(@PathVariable("id") Long id) {
		Optional<Todo> todo = repository.findById(id);
		return todo;
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
