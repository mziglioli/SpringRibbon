package com.server.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.server.model.EntityJpaClass;
import com.server.service.ServiceDefault;
import com.server.util.Catalago;

import lombok.Getter;

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class RestControllerDefault<E extends EntityJpaClass, T extends ServiceDefault> {

	@Autowired
	@Getter
	protected T service;

	@RequestMapping(value = Catalago.URL_BASE, method = RequestMethod.POST)
	@ResponseStatus(code = HttpStatus.OK)
	public final void save(@Valid E entity) {
		service.save(entity);
	}

	@RequestMapping(value = Catalago.URL_BASE, method = RequestMethod.PUT)
	@ResponseStatus(code = HttpStatus.OK)
	public final void update(@Valid E entity) {
		service.update(entity);
	}

	@RequestMapping(value = Catalago.URL_ID, method = RequestMethod.DELETE)
	@ResponseStatus(code = HttpStatus.OK)
	public final void delete(@PathVariable Long id) {
		service.delete(id);
	}

	@RequestMapping(value = Catalago.URL_ID, method = RequestMethod.GET)
	public @ResponseBody E findById(@PathVariable Long id) {
		return (E) service.findById(id);
	}

	@RequestMapping(value = Catalago.URL_BASE, method = RequestMethod.GET)
	public @ResponseBody Collection<E> findAll() {
		return service.findAll();
	}
}