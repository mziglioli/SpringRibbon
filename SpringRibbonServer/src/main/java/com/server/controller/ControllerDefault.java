package com.server.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.server.model.EntityJpaClass;
import com.server.service.ServiceDefault;
import com.server.util.Catalago;

import lombok.Getter;

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class ControllerDefault<E extends EntityJpaClass, T extends ServiceDefault> {

	@Autowired
	@Getter
	protected T service;

	@RequestMapping(value = Catalago.URL_BASE, method = RequestMethod.POST)
	public final ModelAndView save(ModelAndView model, @Valid E entity) {
		service.save(model, entity);
		return model;
	}

	@RequestMapping(value = Catalago.URL_BASE, method = RequestMethod.PUT)
	public final ModelAndView update(ModelAndView model, @Valid E entity) {
		service.update(model, entity);
		return model;
	}

	@RequestMapping(value = Catalago.URL_ID, method = RequestMethod.DELETE)
	public final ModelAndView delete(ModelAndView model, @PathVariable Long id) {
		service.delete(model, id);
		return model;
	}

	@RequestMapping(value = Catalago.URL_ID, method = RequestMethod.GET)
	public ModelAndView findById(ModelAndView model, @PathVariable Long id) {
		model.addObject("entity", service.findById(id));
		return model;
	}

	@RequestMapping(value = Catalago.URL_BASE, method = RequestMethod.GET)
	public ModelAndView findAll(ModelAndView model) {
		model.addObject("entityList", service.findAll());
		return model;
	}
}