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
import com.ai.jwd42.dto.Printer;
import com.ai.jwd42.dto.Product;
import com.ai.jwd42.service.PrinterService;
import com.ai.jwd42.service.ProductService;

@Controller
public class PrinterController {

	@Autowired
	private PrinterService printerService;

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/printer", method = RequestMethod.GET)
	public ModelAndView getPrinter(ModelMap model, HttpServletRequest request) throws IOException {

		if (request.getSession().getAttribute("username") == null) {
			return new ModelAndView("redirect:/login");
		} else {

			Printer printer = new Printer();
			model.addAttribute("printers",printerService.findAllPrinter());
			return new ModelAndView("printer.html", "printer", printer);
		}
	}

	@RequestMapping(value = { "/addPrinter" }, method = RequestMethod.POST)
	public ModelAndView addPrinter(@ModelAttribute("SpringWeb") Printer printer,ModelMap model, HttpServletRequest request)
			throws IOException {

		if (request.getSession().getAttribute("username") == null) {
			return new ModelAndView("redirect:/login");
		} else {

			if (printerService.checkPrinterAlreadyExist(printer.getProduct_id(), printer.getColor(),printer.getPrice())) {

				model.addAttribute("message", "This Printer is Already Exist!");
				model.addAttribute("printers",printerService.findAllPrinter());
				return new ModelAndView("printer.html", "printer",new Printer());
			} else {

				printerService.insertPrinter(printer);
				model.addAttribute("message", "Printer Adding Process is Success!");
				model.addAttribute("printers",printerService.findAllPrinter());
				return new ModelAndView("printer.html", "printer", new Printer());

			}
		}
	}

	@RequestMapping(value = { "/updatePrinter" }, method = RequestMethod.POST)
	public ModelAndView updatePrinter(@ModelAttribute("SpringWeb") Printer printer,ModelMap model, HttpServletRequest request)
			throws IOException {

		if (request.getSession().getAttribute("username") == null) {
			return new ModelAndView("redirect:/login");
		} else {
			if (printerService.checkPrinterAlreadyExist(printer.getProduct_id(), printer.getColor(),
					printer.getPrice())) {
				// return error page
				model.addAttribute("message", "This printer is already exist!!");
				model.addAttribute("printers",printerService.findAllPrinter());
				return new ModelAndView("printer.html", "printer", new Printer());
			} else {

				printerService.updatePrinter(printer);
				model.addAttribute("message", "Success Printer Update !");
				model.addAttribute("printers",printerService.findAllPrinter());
				return new ModelAndView("printer.html", "printer",new Printer());

			}
		}
	}
	
	@RequestMapping(value = { "/deletePrinter" }, method = RequestMethod.POST)
	public ModelAndView deletePrinter(@ModelAttribute("SpringWeb") Printer printer, HttpServletRequest request,ModelMap model) throws IOException {
		
		if (request.getSession().getAttribute("username") == null) {
			return new ModelAndView("redirect:/login");
		} else {
			printerService.deletePrinter(printer.getId());
			model.addAttribute("message", "Delete Printer Success!");
			model.addAttribute("printers",printerService.findAllPrinter());
			return new ModelAndView("printer.html", "printer", new Printer());
		}
	}
	
	
	@ModelAttribute("productList")
	public Map<Integer, String> getProductList() {

		List<Product> products = productService.findAllProduct();
		Map<Integer, String> productList = new HashMap<>();

		for (Product product : products) {
			productList.put(product.getId(), product.getModel());
		}

		return productList;

	}

}
