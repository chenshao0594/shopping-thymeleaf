package com.smart.shopping.admin.controller;

import java.io.Serializable;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.codahale.metrics.annotation.Timed;
import com.smart.shopping.core.catalog.Category;
import com.smart.shopping.domain.common.BusinessDomain;
import com.smart.shopping.service.AbstractDomainService;

@Controller("BasicEntityController")
@RequestMapping("/{sectionKey:.+}")
public abstract class AbstractDomainController<E extends BusinessDomain, K extends Serializable & Comparable<K>> {

	private final Logger log = LoggerFactory.getLogger(AbstractDomainController.class);

	private final AbstractDomainService<E, K> service;

	protected abstract String getSectionKey();

	protected abstract String getEntityName();

	protected void preCreate(E entity) {
	};

	protected E postCreate(E entity) {
		return entity;
	};

	public AbstractDomainController(AbstractDomainService<E, K> service) {
		this.service = service;
	}

	@Timed
	@GetMapping()
	public String listEntities(Model model, Pageable pageable) {
		Page<E> page = this.service.findAll(pageable);
		model.addAttribute("items", page.getContent());
		model.addAttribute("page", page);
		return this.getSectionKey() + "/list";
	}

	@Timed
	@GetMapping(value = "/{id}")
	public String findOne(@PathVariable("id") K id, Model model) {
		E entity = this.service.findOne(id);
		model.addAttribute("item", entity);
		return this.getSectionKey() + "/detail";
	}

	@Timed
	@GetMapping(value = "/new")
	public String initCreationForm(Model model) {
		Category entity = new Category();
		model.addAttribute(entity);
		return this.getSectionKey() + "/dialog";
	}

	@Timed
	@PostMapping()
	public String createEntity(@Valid E entity, BindingResult result, SessionStatus status, Model model) {
		log.debug("REST request to save entity : {}", entity);
		if (result.hasErrors()) {
			return this.getSectionKey() + "/dialog";
		} else {
			this.service.save(entity);
			// status.setComplete();
			PageRequest defaultPageable = new PageRequest(0, 10);
			model.addAttribute("items", this.service.findAll(defaultPageable));
			return "redirect:/" + this.getSectionKey();
		}
	}

	@Timed
	@GetMapping(value = "{id}/edit")
	public String editEntity(@PathVariable("id") K id, Model model) {
		E entity = this.service.findOne(id);
		model.addAttribute(entity);
		return this.getSectionKey() + "/dialog";
	}

}
