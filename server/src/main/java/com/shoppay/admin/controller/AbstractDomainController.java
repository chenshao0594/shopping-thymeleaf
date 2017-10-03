package com.shoppay.admin.controller;

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
import com.shoppay.common.constants.ApplicationConstants;
import com.shoppay.common.domain.BusinessDomain;
import com.shoppay.common.exception.BusinessException;
import com.shoppay.common.service.AbstractDomainService;

@Controller("BasicEntityController")
@RequestMapping(ApplicationConstants.ADMIN_PREFIX + "/{sectionKey:.+}")
public abstract class AbstractDomainController<E extends BusinessDomain, K extends Serializable & Comparable<K>> {

	private final Logger log = LoggerFactory.getLogger(AbstractDomainController.class);

	private final AbstractDomainService<E, K> service;
	
	private final Class entityClass;

	private final String sectionKey;

	


	protected void preNew(ModelAndView model) {
	};

	protected void postNew(E entity, Model model) {
	};

	protected void preCreate(E entity) throws BusinessException {
	};

	protected E postCreate(E entity) {
		return entity;
	};

	public AbstractDomainController(AbstractDomainService<E, K> service, Class entityClass) {
		this.service = service;
		this.entityClass = entityClass;
		this.sectionKey = entityClass.getSimpleName().toLowerCase();
	}

	@Timed
	@GetMapping()
	public String listEntities(Model model, Pageable pageable) {
		Page<E> page = this.service.findAll(pageable);
		model.addAttribute("page", page);
		return this.sectionKey + "/list";
	}

	@Timed
	@GetMapping(value = "/{id}")
	public String findOne(@PathVariable("id") K id, Model model) {
		E entity = this.service.findOne(id);
		model.addAttribute("item", entity);
		return this.sectionKey + "/detail";
	}

	@Timed
	@GetMapping(value = "/new")
	public ModelAndView initCreationForm(ModelAndView model) {
		this.preNew(model);
		try {
			model.addObject("item", this.entityClass.newInstance());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.setViewName(this.sectionKey + "/dialog");
		return model;
	}

	@Timed
	@PostMapping()
	public ModelAndView createEntity(@Valid E entity, BindingResult result, RedirectAttributes redirect)
			throws BusinessException {
		log.debug("REST request to save entity : {}", entity);
		ModelAndView mv = new ModelAndView();
		if (result.hasErrors()) {
			log.error("save entity has error {}", result.getAllErrors());
			mv.addObject("formErrors", result.getAllErrors());
			mv.setViewName(this.sectionKey + "/dialog");
			mv.addObject("item", entity);
			this.preNew(mv);
			return mv;
		} else {
			this.preCreate(entity);
			this.service.save(entity);
			redirect.addFlashAttribute("statusMessage", "Successfully created!");
			return new ModelAndView(
					"redirect:" + ApplicationConstants.ADMIN_PREFIX + "/" + this.sectionKey + "/{entity.id}", "entity.id",
					entity.getId());
		}
	}

	@Timed
	@GetMapping(value = "{id}/edit")
	public ModelAndView editEntity(@PathVariable("id") K id, ModelAndView model) {
		E entity = this.service.findOne(id);
		model.addObject("item", entity);
		this.preNew(model);
		model.setViewName(this.sectionKey + "/dialog");
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
			return this.sectionKey + "/dialog";
		} else {
			E e = this.service.findOne(id);
			this.service.save(entity);
			model.addAttribute("item", entity);
			redirect.addFlashAttribute("statusMessage", "Update Successfully !");
			return "redirect:" + ApplicationConstants.ADMIN_PREFIX + "/" + this.sectionKey + "/" + id + "/edit";
		}
	}

}
