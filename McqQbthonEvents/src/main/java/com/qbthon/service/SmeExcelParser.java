package com.qbthon.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.qbthon.model.AssociateDetails;
import com.qbthon.model.EventDetails;

public class SmeExcelParser implements ExcelParser{

	@Override
	public List<String> parseExcel(EventDetails eventDetails) throws IOException {
		// TODO Auto-generated method stub
		Workbook workbook = new XSSFWorkbook(eventDetails.getUploadFilesmes().getInputStream());
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.iterator();
		List<String> associateslist = new ArrayList<String>();
		rows.next();	
		while(rows.hasNext()) {
			Row row = rows.next();

			AssociateDetails associateDetails = new AssociateDetails();
			
			associateDetails.setAssociateId(String.valueOf(row.getCell(0).getNumericCellValue()));
			associateDetails.setAssociateName(row.getCell(1).getStringCellValue());
			associateDetails.setBuName(row.getCell(2).getStringCellValue());
			associateDetails.setAccountName(row.getCell(3).getStringCellValue());
			String [] skills = row.getCell(4).getStringCellValue().split(",");

			ArrayList<String> skillist = new ArrayList<String>();
			Collections.addAll(skillist, skills);
			associateDetails.setSkillList(skillist);
			associateDetails.setRole(row.getCell(5).getStringCellValue());
			associateDetails.setEmail(row.getCell(6).getStringCellValue());
			associateslist.add(associateDetails.toString());


		}
		workbook.close();
		return associateslist;
		
	}

}
