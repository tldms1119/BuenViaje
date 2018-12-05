package edu.iot.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iot.common.exception.PasswordMissmatchException;
import edu.iot.common.model.Route;
import edu.iot.common.model.Search;
import edu.iot.common.model.TourSpot;
import edu.iot.common.service.RouteService;

@Controller
@RequestMapping("/route")
public class RouteController {

	@Autowired
	RouteService service;

	@Autowired
	ServletContext context;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "orderBy", defaultValue = "route_id") String orderBy, Model model) throws Exception {
		model.addAllAttributes(service.getPage(page, orderBy));
		model.addAttribute("orderBy", orderBy);
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public void createForm(Route route) {

	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createSubmit(@Valid Route route, BindingResult result, 
			@RequestParam("spot") List<String> spots,
			@RequestParam("brief") List<String> briefs,
			Model model) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("spots", spots);
			model.addAttribute("briefs", briefs);
			return "route/create";
		}

		List<TourSpot> tourSpot = new ArrayList<>();
		for (String n : spots) { // 입력 받은 List<String>을
			tourSpot.add(new TourSpot(n)); // List<TourSpot>으로
		}
		
		for (int i = 0; i < tourSpot.size(); i++) {
			tourSpot.get(i).setBrief(briefs.get(i));
		}

		route.setList(tourSpot); // tourSpot list setting

		service.create(route);
		return "redirect:list";
	}

	@RequestMapping(value = "/view/{routeId}", method = RequestMethod.GET)
	public String view(@PathVariable long routeId, Model model) throws Exception {
		service.increaseReadCnt(routeId);
		Route route = service.findById(routeId);
		model.addAttribute("route", route);
		return "route/view";
	}

	@RequestMapping(value = "/edit/{routeId}", method = RequestMethod.GET)
	public String editForm(@PathVariable long routeId, Model model) throws Exception {
		Route route = service.findById(routeId);
		model.addAttribute("route", route);
		return "route/edit";
	}

	@RequestMapping(value = "/edit/{routeId}", method = RequestMethod.POST)
	public String editSubmit(@Valid Route route, BindingResult result, 
			@RequestParam("spot") List<String> spots,
			@RequestParam("brief") List<String> briefs,
			@RequestParam("page") int page, Model model) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("spots", spots);
			model.addAttribute("briefs", briefs);
			return "route/edit";
		}

		List<TourSpot> tourSpot = new ArrayList<>();
		for (String n : spots) { // 입력 받은 List<String>을
			tourSpot.add(new TourSpot(n)); // List<TourSpot>으로
		}
		
		for (int i = 0; i < tourSpot.size(); i++) {
			tourSpot.get(i).setBrief(briefs.get(i));
		}

		route.setList(tourSpot);
		try {
			service.update(route);
		} catch (PasswordMissmatchException e) {
			result.reject("updateFail", e.getMessage());
			model.addAttribute("spotList", tourSpot);
			return "route/edit";
		}
		return "redirect:/route/view/" + route.getRouteId() + "?page=" + page;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET, produces = "text/plain; charset=utf8")
	@ResponseBody
	public String delete(Route route) {
		try {
			service.delete(route);
			return "ok";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public void search(Search search, 
			@RequestParam(value = "page", defaultValue = "1") int page,
			Model model,  HttpServletRequest request) throws Exception{
		String keyword = search.getKeyword();
		String secondKeyword = search.getSecondKeyword();
		if(secondKeyword != null) {
			model.addAttribute("secondKeyword", secondKeyword.substring(1, secondKeyword.length()-1));
		}
		model.addAttribute("search", search);
		model.addAttribute("keyword", keyword.substring(1, keyword.length()-1));
		model.addAllAttributes(service.search(search, page));
	}

}
