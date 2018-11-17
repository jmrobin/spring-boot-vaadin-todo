package jmr.vaadin.marcus.view;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.VerticalLayout;

import jmr.vaadin.marcus.entity.Todo;
import jmr.vaadin.marcus.repository.TodoRepository;

@SpringComponent
public class TodoLayout extends VerticalLayout implements TodoChangeListener
{
	private static final long serialVersionUID = 1L;

	@Autowired
	TodoRepository todoRepository;

	@PostConstruct
	void init()
	{
		update();
	}

	private void update()
	{
		List<Todo> todos = todoRepository.findAll();
		removeAllComponents();
		todos.forEach(todo -> addComponent(new TodoItemLayout(todo, this)));
	}

	public void add( Todo todo )
	{
		todoRepository.save(todo);
		update();
	}

	public void deleteCompleted()
	{
		todoRepository.deleteByDone(true);
		update();
	}

	@Override
	public void todoChanged( Todo todo )
	{
		add(todo);
	}

}
