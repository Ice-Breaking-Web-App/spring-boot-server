package icebreaker.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import icebreaker.project.service.AnswersService;
import icebreaker.project.service.AnswersService.AnswersNameSet;
import icebreaker.project.service.AnswersService.AnswersQNumberSet;
import icebreaker.project.service.AnswersService.MemberProgressSet;
import icebreaker.project.service.TeamCodesService;

@CrossOrigin
@RestController
@RequestMapping("api/answers")
public class AnswersController {
	
	@Autowired
	AnswersService answersService;
	@Autowired
	TeamCodesService teamCodesService;

	@GetMapping("/all")
	public List<AnswersNameSet> getAllAnswers(@RequestParam String memberCode, int qNumber){
		boolean isValid = teamCodesService.verifyMemberCode(memberCode);
		
		if (isValid) {
			return answersService.getAllAnswers(memberCode, qNumber);
		} else {
			return null;
		}
	}
	
	@GetMapping("/member")
	public String[] getMemberAnswers(@RequestParam String memberCode, String memberName) {
		boolean isValid = teamCodesService.verifyMemberCode(memberCode);
		
		if (isValid) {
			return answersService.getMemberAnswers(memberCode, memberName);
		} else {
			return null;
		}
	}
	
	@PatchMapping("/member")
	public void updateMemberAnswer(@RequestParam String memberCode, String memberName, @RequestBody AnswersQNumberSet data) {
		boolean isValid = teamCodesService.verifyMemberCode(memberCode);
		
		if (isValid) {
			answersService.updateMemberAnswer(memberCode, memberName, data);
		}
		
	}
	
	@PutMapping("/member")
	public void createMemberAnswer(@RequestParam String memberCode, String memberName, @RequestBody AnswersQNumberSet data) {
		boolean isValid = teamCodesService.verifyMemberCode(memberCode);
		
		if (isValid) {
			answersService.createMemberAnswer(memberCode, memberName, data);
		}
	}
	
	@GetMapping("/progress")
	public MemberProgressSet getProgress(@RequestParam String memberCode, String memberName) {
		boolean isValid = teamCodesService.verifyMemberCode(memberCode);
		
		if (isValid) {
			return answersService.getProgress(memberCode, memberName);
		} else {
			return null;
		}
	}

}