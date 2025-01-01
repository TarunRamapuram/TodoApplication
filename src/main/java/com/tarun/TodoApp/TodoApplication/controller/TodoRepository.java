package com.tarun.TodoApp.TodoApplication.controller;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tarun.TodoApp.TodoApplication.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer>{

	List<Todo> findByUsername(String username);
	

}
