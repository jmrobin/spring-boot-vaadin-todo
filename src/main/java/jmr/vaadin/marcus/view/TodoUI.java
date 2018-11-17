package jmr.vaadin.marcus.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import jmr.vaadin.marcus.entity.Todo;

@SpringUI( path = "/todo" )
@Title( "Vaadin to do list" )
@Theme( "valo" )
public class TodoUI extends UI
{
	private static final long serialVersionUID = 1L;
	private VerticalLayout rootLayout;

	@Autowired
	TodoLayout todoLayout;

	@Override
	protected void init( VaadinRequest request )
	{
		setupLayout();
		addHeader();
		addForm();
		addTodoList();
		addDeleteButton();
	}

	private void setupLayout()
	{
		rootLayout = new VerticalLayout();
		rootLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		setContent(rootLayout);
	}

	private void addHeader()
	{
		Label header = new Label("TODOs");
		header.addStyleName(ValoTheme.LABEL_H1);
		rootLayout.addComponent(header);
	}

	private void addForm()
	{
		HorizontalLayout formLayout = new HorizontalLayout();
		formLayout.setWidth("80%");

		TextField task = new TextField();
		Button add = new Button();
		add.setIcon(VaadinIcons.PLUS);
		add.addStyleName(ValoTheme.BUTTON_PRIMARY);
		formLayout.addComponentsAndExpand(task);
		formLayout.addComponent(add);

		add.addClickListener(e -> {
			Todo todo = new Todo(task.getValue());
			todoLayout.add(todo);
			task.clear();
			task.focus();
		});
		task.focus();
		add.setClickShortcut(ShortcutAction.KeyCode.ENTER);

		rootLayout.addComponent(formLayout);
	}

	private void addTodoList()
	{
		todoLayout.setWidth("80%");
		rootLayout.addComponent(todoLayout);
	}

	private void addDeleteButton()
	{
		Button delete = new Button("Delete completed");
		delete.addClickListener(e -> {
			todoLayout.deleteCompleted();
		});
		rootLayout.addComponent(delete);
	}

}
