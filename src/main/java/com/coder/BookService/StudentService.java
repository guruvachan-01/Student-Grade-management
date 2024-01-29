package com.coder.BookService;

import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.coder.BookRepository.StudentRepository;
import com.coder.entity.Student;


import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class StudentService {
	@Autowired
	private StudentRepository bRepo;
	
	public void save(Student b) {
		bRepo.save(b);
	}
	public List<Student> getAllBook(){
		return bRepo.findAll();
	}
	public Student getBookById(int id) {
		return bRepo.findById(id).get();
	}
	public void deleteById(int id) {
		bRepo.deleteById(id);
	}
	
	//export data excel file
	public void generateExcel(HttpServletResponse response) throws IOException {
		 
		List<Student> stinfo = bRepo.findAll();
		HSSFWorkbook  workbook = new HSSFWorkbook();
	HSSFSheet sheet = workbook.createSheet("Student_Result_Details");
	 HSSFRow row  = sheet.createRow(0);
	 row.createCell(0).setCellValue("id");
	 row.createCell(1).setCellValue("rollno");
	 row.createCell(2).setCellValue("name");
	 row.createCell(3).setCellValue("hindi"); 
	 row.createCell(4).setCellValue("english");
	 row.createCell(5).setCellValue("math"); 
	 row.createCell(6).setCellValue("phy");
	 row.createCell(7).setCellValue("chem");
	 row.createCell(8).setCellValue("percentag");
	 row.createCell(9).setCellValue("grade");
	 
	 int dataRowIndex = 1;
	 for( Student studentinfo :stinfo) {
		HSSFRow dataRow = sheet.createRow(dataRowIndex);
		dataRow.createCell(0).setCellValue(studentinfo.getId());
		dataRow.createCell(1).setCellValue(studentinfo.getRollno());
		dataRow.createCell(2).setCellValue(studentinfo.getName());
		dataRow.createCell(3).setCellValue(studentinfo.getHindi());
		dataRow.createCell(4).setCellValue(studentinfo.getEnglish());
		dataRow.createCell(5).setCellValue(studentinfo.getMath());
		dataRow.createCell(6).setCellValue(studentinfo.getPhy());
		dataRow.createCell(7).setCellValue(studentinfo.getChem());
		dataRow.createCell(8).setCellValue(studentinfo.getPercentag());
		dataRow.createCell(9).setCellValue(studentinfo.getGrade());
		
		dataRowIndex ++;
	 }
	
	 ServletOutputStream ops = response.getOutputStream();
	 workbook.write(ops);
	 workbook.close();
	 ops.close();
		
		
	}
	
}
