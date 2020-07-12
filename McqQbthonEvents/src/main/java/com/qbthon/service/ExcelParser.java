package com.qbthon.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.qbthon.model.EventDetails;
import com.qbthon.repositories.EventAssociatesRepository;


public interface ExcelParser {
	
	
	public List<String> parseExcel(EventDetails eventDetails) throws IOException;
    
}
