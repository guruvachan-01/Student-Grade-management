package com.coder.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.coder.BookRepository.StudentRepository;
import com.coder.BookService.StudentService;
import com.coder.entity.Student;

import jakarta.servlet.http.HttpServletResponse;



@Controller
public class MyController {
	@Autowired
	private StudentService service;
	
	@Autowired
	private StudentRepository studentRepository;
	
	
	@GetMapping("/page")
    public String page() {
		return "page";
    	
    }
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/bookList")
	public ModelAndView getAllBook() {
		List<Student> list=service.getAllBook();
		return new ModelAndView("bookList","book",list);
	}
	@PostMapping("/save")
	public String addBook(@ModelAttribute Student b) {
		int hindi=b.getHindi();
		int eng=b.getEnglish();
		int math=b.getMath();
		int phy=b.getPhy();
		int chem=b.getChem();
		float percentage=((hindi+eng+math+phy+chem)*100)/500;
		b.setPercentag(percentage);
		if(percentage >= 90 && percentage <= 100) {
       	 b.setGrade("A1");
        }
        else if(percentage >= 80 && percentage <= 89) {
       	 b.setGrade("A2");
        }
        else if(percentage >= 70 && percentage <= 79) {
       	 b.setGrade("B1");
        }
        else  if(percentage >= 60 && percentage <= 69) {
       	 b.setGrade("B2");
        }
        else if(percentage >= 50 && percentage <= 59) {
       	 b.setGrade("C1");
        }
        else if(percentage >= 40 && percentage <= 49) {
       	 b.setGrade("C2");
        }
        else if(percentage >= 33 && percentage <= 39) {
       	 b.setGrade("P");
        }
        else {
       	 b.setGrade("F");
        }
		service.save(b);
		return"redirect:/bookList";
	}
	
	
	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id,Model model) {
		Student b=service.getBookById(id);
		model.addAttribute("book",b);
		return "editBook";
	}
	@PostMapping("/update/{id}")
	public String Update(@ModelAttribute Student b) {
		int hindi=b.getHindi();
		int eng=b.getEnglish();
		int math=b.getMath();
		int phy=b.getPhy();
		int chem=b.getChem();
		float percentage=((hindi+eng+math+phy+chem)*100)/500;
		b.setPercentag(percentage);
		if(percentage >= 90 && percentage <= 100) {
       	 b.setGrade("A1");
        }
        else if(percentage >= 80 && percentage <= 89) {
       	 b.setGrade("A2");
        }
        else if(percentage >= 70 && percentage <= 79) {
       	 b.setGrade("B1");
        }
        else  if(percentage >= 60 && percentage <= 69) {
       	 b.setGrade("B2");
        }
        else if(percentage >= 50 && percentage <= 59) {
       	 b.setGrade("C1");
        }
        else if(percentage >= 40 && percentage <= 49) {
       	 b.setGrade("C2");
        }
        else if(percentage >= 33 && percentage <= 39) {
       	 b.setGrade("P");
        }
        else {
       	 b.setGrade("F");
        }
		service.save(b);
		return"redirect:/bookList";
	}
	
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id")int id) {
		service.deleteById(id);
		return "redirect:/bookList";
	}
	@RequestMapping("/view/{id}")
	public String openShowResume13(@PathVariable("id") int id, Model m) {
		
		m.addAttribute("title", "Show result");
		m.addAttribute("res", new Student());
		
		Optional<Student> stpOptional = this.studentRepository.findById(id);
		Student stpinfo = stpOptional.get();
		
		m.addAttribute("res",stpinfo);
		

		return "view";
	}
	
	/// generate formeta excel file
		@GetMapping("/generate-template")
		public void generateTemplate(HttpServletResponse response) throws IOException {
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.setHeader("Content-Disposition", "attachment; filename=Students_Result_Details.xls");

			Workbook workbook = new XSSFWorkbook();
			org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Data Template");

	// Create header row
			Row header = sheet.createRow(0);
			header.createCell(0).setCellValue("id");
			header.createCell(1).setCellValue("rollno");
			header.createCell(2).setCellValue("name");
			header.createCell(3).setCellValue("hindi");
			header.createCell(4).setCellValue("english");
			header.createCell(5).setCellValue("math");
			header.createCell(6).setCellValue("phy");
			header.createCell(7).setCellValue("chem");
			header.createCell(8).setCellValue("percentag");
			header.createCell(9).setCellValue("grade");
			
			workbook.write(response.getOutputStream());
			workbook.close();
		}
		@GetMapping("/exportStudentsDetails")
		public void generateExcelReport(HttpServletResponse response) throws Exception {
			response.setContentType("application/octet-stream");
			String headerKey = "Content-Disposition";
			String headerValue = "attachment;filename=Students_Result_Details.xls";
			response.setHeader(headerKey, headerValue);

			service.generateExcel(response);
		}
}
