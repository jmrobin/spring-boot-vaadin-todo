package jmr.vaadin.marcus.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import jmr.vaadin.marcus.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>
{
	@Transactional
	void deleteByDone( boolean done );
}
