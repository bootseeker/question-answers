package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.exception.ResourceExistsException;
import com.example.exception.SpringBootException;
import com.example.model.Questions;
import com.example.model.QuestionsDto;
import com.example.model.ResponseMessage;
import com.example.repository.QuestionsRepository;
@RestController
@RequestMapping("/api/questions")
public class QuestionController 
{

	@Autowired
	private QuestionsRepository questionsRepository;

	
	@GetMapping("/testseries")
	public ResponseEntity<QuestionsDto> getAllUsers(@RequestParam String testSeries,@RequestParam int pageNo,@RequestParam int pageSize) {
		Page<Questions> localPageQuestions = null;
		QuestionsDto localQuestionsDto = null;
		
		localPageQuestions = questionsRepository.findByTestSeries(testSeries,PageRequest.of(pageNo-1, pageSize));
		localQuestionsDto = new QuestionsDto();
		localQuestionsDto.setQuestions(localPageQuestions.getContent());
		System.out.println(localPageQuestions.getTotalPages());
		localQuestionsDto.setPageNo(pageNo+"");
		localQuestionsDto.setPageSize(pageSize+"");
		localQuestionsDto.setTotalPages(localPageQuestions.getTotalPages()+"");
		localQuestionsDto.setTotalRecords(localPageQuestions.getTotalElements()+"");
//		System.out.println(users.getTotalElements());
		return ResponseEntity.ok(localQuestionsDto);
	}
	
	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadQuestions(@RequestParam("file") MultipartFile formFile)
			throws ResourceExistsException,IOException 
	{
		List<Questions> localQuestionsList = new ArrayList<Questions>();
		String localStrFileName = formFile.getOriginalFilename();
		XSSFRow row = null;
		XSSFWorkbook workbook = null;
		XSSFSheet worksheet = null;
		Questions localQuestions = null;
		File localFile = null;
		int QuestionColNo = 0;
		int choice1ColNo = 0;
		int choice2ColNo = 0;
		int choice3ColNo = 0;
		int choice4ColNo = 0;
		int answerColNo = 0;
		int marksColNo = 0;
		int testColNo = 0;
		int hintColNo = 0;

		if (!localStrFileName.endsWith(".xlsx")) 
		{
			throw new ResourceExistsException("File should be in xlsx format");
		}

		localFile = new File(localStrFileName);

//		if (!localFile.exists()) 
//		{
//			throw new ResourceExistsException("Specified File does not exits.");
//		}

		try 
		{
			
			workbook = new XSSFWorkbook(formFile.getInputStream());

			worksheet = workbook.getSheetAt(0);
			row = worksheet.getRow(0);
			int colCount = row.getLastCellNum();

			for (int j = 0; j < colCount; j++) 
			{

				
				
				if (row.getCell(j).getCellType() != Cell.CELL_TYPE_STRING) {

					throw new SpringBootException("Specified File is corrputed or inValid");

				} 
				else 
				{

					String localStrColunmValue = row.getCell(j).getStringCellValue();
					boolean localFlag = false;

					if (localStrColunmValue.equalsIgnoreCase("Question")) {
						QuestionColNo = j;
						localFlag = true;
					} else if (localStrColunmValue.equalsIgnoreCase("Choice1")) {
						choice1ColNo = j;
						localFlag = true;
					} else if (localStrColunmValue.equalsIgnoreCase("Choice2")) {
						choice2ColNo = j;
						localFlag = true;
					} else if (localStrColunmValue.equalsIgnoreCase("Choice3")) {
						choice3ColNo = j;
						localFlag = true;
					} else if (localStrColunmValue.equalsIgnoreCase("Choice4")) {
						choice4ColNo = j;
						localFlag = true;
					} else if (localStrColunmValue.equalsIgnoreCase("answer")) {
						answerColNo = j;
						localFlag = true;
					} else if (localStrColunmValue.equalsIgnoreCase("marks")) {
						marksColNo = j;
						localFlag = true;
					} else if (localStrColunmValue.equalsIgnoreCase("test")) {
						testColNo = j;
						localFlag = true;
					} else if (localStrColunmValue.equalsIgnoreCase("hint")) {
						hintColNo = j;
						localFlag = true;
					}

					if (!localFlag) 
					{
						throw new ResourceExistsException("Sepecified file is not proper or corrputed.");
					}
				}
			}

			for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++)
			{
				localQuestions = new Questions();
				row = worksheet.getRow(i);
				String localStrCellValue = null;
				localStrCellValue = getCellValue(row.getCell(QuestionColNo));
				localQuestions.setQuestion(localStrCellValue);
				localStrCellValue = getCellValue(row.getCell(choice1ColNo));
				localQuestions.setChoice1(localStrCellValue);
				localStrCellValue = getCellValue(row.getCell(choice2ColNo));
				localQuestions.setChoice2(localStrCellValue);
				localStrCellValue = getCellValue(row.getCell(choice3ColNo));
				localQuestions.setChoice3(localStrCellValue);
				localStrCellValue = getCellValue(row.getCell(choice4ColNo));
				localQuestions.setChoice4(localStrCellValue);
				localStrCellValue = getCellValue(row.getCell(answerColNo));
				localQuestions.setCorrectAnswer(localStrCellValue);
				localStrCellValue = getCellValue(row.getCell(marksColNo));
				localQuestions.setMarks(Long.parseLong(localStrCellValue));
				localStrCellValue = getCellValue(row.getCell(testColNo));
				localQuestions.setTestSeries(localStrCellValue);
				localStrCellValue = getCellValue(row.getCell(hintColNo));
				localQuestions.setHint(localStrCellValue);
				
				localQuestionsList.add(localQuestions);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			if (workbook != null) {
				workbook.close();
			}
		}
		if (localQuestionsList.size() > 0) 
		{
			questionsRepository.saveAll(localQuestionsList);
		}
		ResponseMessage message = new ResponseMessage("Questions uploaded Successfully.");
		return ResponseEntity.ok().body(message);
	}

private String getCellValue(XSSFCell cell) 
{
	String localStrValue = null;
	
	if(cell!=null)
	{
		if(cell.getCellType() == Cell.CELL_TYPE_STRING)
		{
			localStrValue = cell.getStringCellValue();
		}
		else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
		{
			localStrValue = cell.getRawValue();
		}
		else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN)
		{
			localStrValue = cell.getBooleanCellValue()+"";
		}
	}
	return localStrValue;
}
	
}
