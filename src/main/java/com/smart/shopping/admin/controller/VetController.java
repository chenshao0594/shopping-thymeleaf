/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.smart.shopping.admin.controller;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/vets")
public class VetController {


	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showVets(Model model) {
		return new ModelAndView("redirect:/vets/list.html", model.asMap());
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String showVetList(Model model) {
		// Here we are returning an object of type 'Vets' rather than a
		// collection of Vet objects so it is simpler for Object-Xml mapping
		model.addAttribute("vets", CollectionUtils.EMPTY_COLLECTION);

		//log.info("In showVetList: " + model);

		return "vets/vetList";
	}
}
