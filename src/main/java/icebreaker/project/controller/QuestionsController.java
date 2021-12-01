package icebreaker.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import icebreaker.project.service.QuestionsService;
import icebreaker.project.service.TeamCodesService;

@CrossOrigin
@RestController
@RequestMapping("api/questions")
public class QuestionsController {
	
	@Autowired
	QuestionsService questionsService;
	@Autowired
	TeamCodesService teamCodesService;
	
	@GetMapping("/all")
	public String[] getAllQuestions(@RequestParam String memberCode) {
		boolean isValid = teamCodesService.verifyMemberCode(memberCode);
		
		if (isValid) {
			return questionsService.getAllQuestions(memberCode);
		} else {
			return null;
		}
	}

}