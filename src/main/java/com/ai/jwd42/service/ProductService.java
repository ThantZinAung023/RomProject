package com.ai.jwd42.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ai.jwd42.dto.Product;
import com.ai.jwd42.repo.ProductRepository;


@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public boolean checkProductAlreadyExist(String model , int maker_id) {

		List<Product> product= productRepository.findProductWithModelandMaker_Id(model,maker_id);
		if (product == null) {
			return false;
		} else {
			return true;
		}
	}
	

	
	public void insertProduct(Product product) {

		productRepository.insertProduct(product);
	}
	
	
	
	public List<Product> findAllProduct() {

		return productRepository.findAllProduct() ;
	}


	public void updateProduct(Product product) {

		productRepository.updateProduct(product);
	}
	
	public void deleteProduct(int id) {

		productRepository.deleteProduct(id);
		
	}

}
