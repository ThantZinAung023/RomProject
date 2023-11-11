package com.ai.jwd42.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ai.jwd42.dto.Printer;
import com.ai.jwd42.repo.PrinterRepository;


@Service
public class PrinterService {
	
	@Autowired
	private PrinterRepository printerRepository;
	
	public List<Printer> findAllPrinter() {

		return printerRepository.findAllPrinter() ;
	}
	
	
	public boolean checkPrinterAlreadyExist(int productId ,String color , double price) {

		List<Printer> printer= printerRepository.findPrinterWithAllSpec(productId, color, price);
		if (printer == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public void insertPrinter(Printer printer) {
		
		printerRepository.insertPrinter(printer);
	}
	
	public void updatePrinter(Printer printer) {
		printerRepository.updatePrinter(printer);
		
	}
	
	public void deletePrinter(int id) {
		
		printerRepository.deletePrinter(id);	
	}


	

}
