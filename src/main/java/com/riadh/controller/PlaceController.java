package com.riadh.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.riadh.entity.Place;
import com.riadh.service.PlaceService;

@Controller
@RequestMapping("/place")
public class PlaceController {
    protected static final int DEFAULT_PAGE_NUM = 0;
    protected static final int DEFAULT_PAGE_SIZE = 5;

    @Inject
    protected PlaceService placeService;

    protected static final Logger LOGGER = LoggerFactory
            .getLogger(PlaceController.class);

    @RequestMapping(value = "/list")
    public String list(
            @RequestParam(value = "page", required = false) Integer page,
            Model model) {
        int pageNum = page != null ? page : DEFAULT_PAGE_NUM;
        Page<Place> paging = placeService.findAll(pageNum, DEFAULT_PAGE_SIZE);
    	//List<Place> paging = placeService.findByName(name);
        model.addAttribute("page", paging);
        return "/place/list";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public @ModelAttribute
    Place create(Model model) {
        Place place = new Place();
        return place;
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String createOnSubmit(@Valid Place place,
            BindingResult bindingResult, Model model) {
        LOGGER.debug("create place={}", place);
        if (bindingResult.hasErrors()) {
            LOGGER.warn("validation error={}", bindingResult.getModel());
            model.addAllAttributes(bindingResult.getModel());
            return "/place/form";
        }
        placeService.insert(place);
        return "redirect:/place/list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Model model) {
        Place place = placeService.findById(id);
        model.addAttribute(place);
        return "/place/form";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editOnSubmit(@Valid Place place,
            BindingResult bindingResult, Model model) {
        LOGGER.debug("edit place={}", place);
        if (bindingResult.hasErrors()) {
            LOGGER.warn("validation error={}", bindingResult.getModel());
            model.addAllAttributes(bindingResult.getModel());
            return "/place/form";
        }
        placeService.update(place);
        return "redirect:/place/list";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(
            @RequestParam(value = "page", required = false) Integer page,
            @PathVariable("id") Integer id) {
        LOGGER.debug("delete id={}", id);
        placeService.deleteById(id);

        return "redirect:/place/list";
    }

}
