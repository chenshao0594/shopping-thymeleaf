package com.smart.shopping.admin.controller;

import java.io.Serializable;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codahale.metrics.annotation.Timed;
import com.smart.shopping.config.AppConstants;
import com.smart.shopping.domain.common.BusinessDomain;
import com.smart.shopping.service.AbstractDomainService;

@Controller("BasicEntityController")
@RequestMapping("/" + AppConstants.ADMIN_PREFIX + "/{sectionKey:.+}")
public abstract class AbstractDomainController<E extends BusinessDomain, K extends Serializable & Comparable<K>> {

	private final Logger log = LoggerFactory.getLogger(AbstractDomainController.class);

	private final AbstractDomainService<E, K> service;

	protected abstract String getSectionKey();

	protected abstract Class getEntityClass();

	protected void preNew(ModelAndView model) {
	};

	protected void postNew(E entity, Model model) {
	};

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
	public ModelAndView initCreationForm(ModelAndView model) {
		this.preNew(model);
		try {
			model.addObject("item", this.getEntityClass().newInstance());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.setViewName(this.getSectionKey() + "/dialog");
		return model;
	}

	@Timed
	@PostMapping()
	public ModelAndView createEntity(@Valid E entity, BindingResult result, RedirectAttributes redirect) {
		log.debug("REST request to save entity : {}", entity);
		ModelAndView mv = new ModelAndView();
		if (result.hasErrors()) {
			log.error("save entity has error {}", result.getAllErrors());
			mv.addObject("formErrors", result.getAllErrors());
			mv.setViewName(this.getSectionKey() + "/dialog");
			mv.addObject("item", entity);
			this.preNew(mv);
			return mv;
		} else {
			this.service.save(entity);
			redirect.addFlashAttribute("statusMessage", "Successfully created!");
			return new ModelAndView(
					"redirect:/" + AppConstants.ADMIN_PREFIX + "/" + this.getSectionKey() + "/{entity.id}", "entity.id",
					entity.getId());
		}
	}

	@Timed
	@GetMapping(value = "{id}/edit")
	public ModelAndView editEntity(@PathVariable("id") K id, ModelAndView model) {
		E entity = this.service.findOne(id);
		model.addObject("item", entity);
		this.preNew(model);
		model.setViewName(this.getSectionKey() + "/dialog");
		return model;
	}

	@Timed
	@PostMapping(value = "{id}/edit")
	public String updateEntity(@PathVariable("id") K id, @Valid E entity, BindingResult result, Model model,
			RedirectAttributes redirect) {
		if (result.hasErrors()) {
			log.error("error info {}", result.getAllErrors());
			model.addAttribute("formErrors", result.getAllErrors());
			model.addAttribute("item", entity);
			return this.getSectionKey() + "/dialog";
		} else {
			this.service.save(entity);
			model.addAttribute("item", entity);
			redirect.addFlashAttribute("statusMessage", "Update Successfully !");
			return "redirect:/" + AppConstants.ADMIN_PREFIX + "/" + this.getSectionKey() + "/" + id + "/edit";
		}
	}

}
