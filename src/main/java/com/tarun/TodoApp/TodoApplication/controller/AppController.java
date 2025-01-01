package com.tarun.TodoApp.TodoApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tarun.TodoApp.TodoApplication.model.Todo;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class AppController {
	
	
	@Autowired
	private TodoRepository todoRepository;
	
	private String getLoggedInUsername() {
		String Username = SecurityContextHolder.getContext().getAuthentication().getName();
		return Username;
	}
	
	// 1. Maps to the todo page, display the todos for the Specific username
	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		String username = getLoggedInUsername();
		List<Todo> todos = todoRepository.findByUsername(username);
		model.put("list", todos);
		return "listTodos";
	}
	
	
	// 2. Calling the add new todo list
	@RequestMapping(value = "add-todo", method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
			String username = getLoggedInUsername();
			Todo todo = new Todo(0, username, "", LocalDate.now(), false);
			model.put("todo", todo);
			return "todo_update";
	}
	
	//3. sending the data to the form using the post method
	@RequestMapping(value = "add-todo", method = RequestMethod.POST)
	public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "todo_update";
		}
		String username = getLoggedInUsername();
		todo.setUsername(username);
		todoRepository.save(todo);
		return "redirect:list-todos";
	}
	
	//4. function to deleting todo
	@RequestMapping("delete-todo")
	public String DeleteTodo(@RequestParam int id) {
		todoRepository.deleteById(id);
		return "redirect:list-todos";
	}
	
	// 5. function to update values, this will return the values as it is.
	@RequestMapping(value = "update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = todoRepository.findById(id).get();
		model.addAttribute("todo", todo);
		return "todo_update";
	}
	
	@RequestMapping(value = "update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "todo_update";
		}
		String username = getLoggedInUsername();
		todo.setUsername(username); 
		todoRepository.save(todo);
		return "redirect:list-todos";
	}
	
	//6. welcome page
	@RequestMapping("/")
	public String gotoWelcomePage(ModelMap model) {
		model.put("name", getLoggedInUsername());
		return "home";
	}

	
	
}
