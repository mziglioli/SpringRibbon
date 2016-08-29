package com.server.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.core.model.EntityJpaClass;
import com.server.service.ServiceDefault;

import lombok.Getter;

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class ControllerDefault<E extends EntityJpaClass, T extends ServiceDefault> {

	@Autowired
	@Getter
	protected T service;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public final ModelAndView save(ModelAndView model, @Valid E entity) {
		getService().save(model, entity);
		return model;
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public final ModelAndView update(ModelAndView model, @Valid E entity) {
		getService().update(model, entity);
		return model;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public final ModelAndView delete(ModelAndView model, @PathVariable Long id) {
		getService().delete(model, id);
		return model;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView findById(ModelAndView model, @PathVariable Long id) {
		model.addObject("entity", getService().findById(id));
		return model;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView findAll(ModelAndView model) {
		model.addObject("entityList", getService().findAll());
		return model;
	}

}