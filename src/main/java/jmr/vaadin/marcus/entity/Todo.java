package jmr.vaadin.marcus.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "todos" )
public class Todo
{
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private long id;

	private String text;

	private boolean done;

	public Todo()
	{
		// empty constructor for JPA
	}

	public Todo( String text )
	{
		this.text = text;
	}

	public Todo( String text, boolean done )
	{
		this.text = text;
		this.done = done;
	}

	public long getId()
	{
		return id;
	}

	public void setId( long id )
	{
		this.id = id;
	}

	public String getText()
	{
		return text;
	}

	public void setText( String text )
	{
		this.text = text;
	}

	public boolean isDone()
	{
		return done;
	}

	public void setDone( boolean done )
	{
		this.done = done;
	}

}
