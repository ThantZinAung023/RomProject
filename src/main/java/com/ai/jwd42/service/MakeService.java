package com.ai.jwd42.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ai.jwd42.dto.Make;
import com.ai.jwd42.repo.MakeRepository;

@Service
public class MakeService {

	@Autowired
	private MakeRepository makeRepository;

	
	public List<Make> findAllMake() {

		return makeRepository.findAllMake() ;
	}

}
