package com.server.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.servlet.ModelAndView;

import com.core.model.EntityJpaClass;

import lombok.Getter;

public abstract class ServiceDefault<T extends EntityJpaClass, R extends JpaRepository<T, Long>> {

	@Autowired
	@Getter
	protected R repository;

	public void save(ModelAndView model, T entity) {
		try {
			save(entity);
			model.addObject("success", "saved succesfully");
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("error", e.getMessage());
		}
	}

	public void save(T entity) {
		try {
			if (entity.getId() == null) {
				beforeInsert(entity);
				repository.save(entity);
				afterInsert(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(ModelAndView model, T entity) {
		try {
			update(entity);
			model.addObject("success", "updated successfully");
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("error", e.getMessage());
		}
	}

	public void update(T entity) {
		try {
			beforeUpdate(entity);
			repository.save(entity);
			afterUpdate(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(T entity) {
		try {
			beforeDelete(entity);
			repository.delete(entity);
			afterDelete(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(ModelAndView model, T entity) {
		try {
			delete(entity);
			model.addObject("success", "deleted successfully");
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("error", e.getMessage());
		}
	}

	public void delete(Long id) {
		T entity = repository.findOne(id);
		delete(entity);
	}

	public void delete(ModelAndView model, Long id) {
		T entity = repository.findOne(id);
		delete(model, entity);
	}

	public Collection<T> findAll() {
		return repository.findAll();
	}

	public T findById(Long id) {
		return repository.findOne(id);
	}

	protected void afterInsert(T entity) {

	}

	protected void afterUpdate(T entity) {
	}

	protected void afterDelete(T entity) {
	}

	protected void beforeInsert(T entity) {
	}

	protected void beforeUpdate(T entity) {
	}

	protected void beforeDelete(T entity) {

	}

	protected boolean isUnique(T entity) {
		return true;
	}

}