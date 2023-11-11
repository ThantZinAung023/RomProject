package com.ai.jwd42.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ai.jwd42.dto.Make;
import com.ai.jwd42.dto.Product;
import com.ai.jwd42.service.MakeService;
import com.ai.jwd42.service.ProductService;



@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private MakeService makeService;

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public ModelAndView getProduct(ModelMap model, HttpServletRequest request) throws IOException {

		if (request.getSession().getAttribute("username") == null) {
			return new ModelAndView("redirect:/login");
		} else {
			Product product = new Product();
			model.addAttribute("products", productService.findAllProduct());
			return new ModelAndView("product.html", "product", product);
		}
	}

	@RequestMapping(value = { "/addProduct" }, method = RequestMethod.POST)
	public ModelAndView addProduct(@ModelAttribute("SpringWeb") Product product, HttpServletRequest request,ModelMap model)
			throws IOException {
		
		if (request.getSession().getAttribute("username") == null) {
			return new ModelAndView("redirect:/login");
//			return "redirect:/login";
		} else {

			if (productService.checkProductAlreadyExist(product.getModel(), product.getMaker_id())) {

				model.addAttribute("message", "This Product is Already Exist!");
				model.addAttribute("products", productService.findAllProduct());
				return new ModelAndView("product.html", "product", new Product());
			} else {

				productService.insertProduct(product);
				model.addAttribute("message", "Product Adding Process is Success!");
				model.addAttribute("products", productService.findAllProduct());
				return new ModelAndView("product.html", "product", new Product());
			}

		}
	}

	@RequestMapping(value = { "/updateProduct" }, method = RequestMethod.POST)
	public ModelAndView updateProduct(@ModelAttribute("SpringWeb") Product product, HttpServletRequest request,ModelMap model)
			throws IOException {

		if (request.getSession().getAttribute("username") == null) {
			return new ModelAndView("redirect:/login");
		} else {
			
			if (productService.checkProductAlreadyExist(product.getModel(), product.getMaker_id())) {
				// return error page
				model.addAttribute("message", "This product is already exist!!");
				model.addAttribute("products", productService.findAllProduct());
				return new ModelAndView("product.html", "product",new Product());
			} else {

				productService.updateProduct(product);
				model.addAttribute("message", "Success Product Update !");
				model.addAttribute("products", productService.findAllProduct());
				return new ModelAndView("product.html", "product", new Product());
			}
		}
	}
	
	@RequestMapping(value = { "/deleteProduct" }, method = RequestMethod.POST)
	public ModelAndView deleteProduct(@ModelAttribute("SpringWeb") Product product, HttpServletRequest request,ModelMap model) throws IOException {
		
		if (request.getSession().getAttribute("username") == null) {
			return new ModelAndView("redirect:/login");
		} else {
			productService.deleteProduct(product.getId());
			model.addAttribute("message", "Delete Product Success!");
			model.addAttribute("products", productService.findAllProduct());
			return new ModelAndView("product.html", "product",new Product());

		}
	}

	@ModelAttribute("makeList")
	public Map<Integer, String> getmakeList() {

		List<Make> makes = makeService.findAllMake();
		Map<Integer, String> makeList = new HashMap<>();

		for (Make make : makes) {
			makeList.put(make.getId(), make.getName());
		}
		return makeList;

	}

}
