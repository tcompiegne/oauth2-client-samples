/*******************************************************************************
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Titouan COMPIEGNE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *******************************************************************************/
package com.tce.oauth2.spring.client.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tce.oauth2.spring.client.models.Todo;
import com.tce.oauth2.spring.client.services.TodoService;

/**
 * 
 * @author Titouan COMPIEGNE
 *
 */
@Controller
public class TodoController {
	
	@Autowired
	private TodoService todoService;

	@RequestMapping("/todos")
	public ModelAndView index(HttpServletRequest request) {
		List<Todo> todos = todoService.findByUsername((String) request.getSession().getAttribute("access_token"), (String) request.getSession().getAttribute("username"));
		ModelAndView model = new ModelAndView();
		model.addObject("todo", new Todo());
		model.addObject("todos", todos);
		model.setViewName("todolist");
		return model;
	}
	
	@RequestMapping(value = "/todos/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("todo") Todo todo, HttpServletRequest request) {
		todo.setUsername((String) request.getSession().getAttribute("username"));
		todoService.add((String) request.getSession().getAttribute("access_token"), todo);
		return "redirect:/todos";
	}
	
	@RequestMapping(value = "/todos/edit", method = RequestMethod.POST)
	public @ResponseBody Todo edit(@RequestParam("pk") Long pk, @RequestParam("value") String value, HttpServletRequest request) {
		Todo todo = new Todo();
		todo.setId(pk);
		todo.setDescription(value);
		todo.setUsername((String) request.getSession().getAttribute("username"));
		return todoService.edit((String) request.getSession().getAttribute("access_token"), todo);
	}
	
	@RequestMapping(value = "/todos/{id}/delete", method = RequestMethod.POST)
	public String delete(@PathVariable("id") Long id, HttpServletRequest request) {
		todoService.delete((String) request.getSession().getAttribute("access_token"), id);
		return "redirect:/todos";
	}
	
}
