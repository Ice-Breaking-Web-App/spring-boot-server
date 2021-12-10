package icebreaker.project.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import icebreaker.project.entity.Answers;
import icebreaker.project.repository.AnswersRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
public class AnswersService {

	@Autowired
	AnswersRepository answersRepository;
	@Autowired
	TeamCodesService teamCodesService;
	@Autowired
	TeamMembersService teamMembersService;
	@Autowired
	TeamInfoService teamInfoService;

	public List<AnswersNameSet> getAllAnswers(String memberCode, int qNumber) {		
		Long teamId = teamCodesService.getTeamId(memberCode);
		List<Answers> allAnswers =  answersRepository.findByTeamIdAndQNumber(teamId, qNumber);
		List<AnswersNameSet> answers = new ArrayList<AnswersNameSet>();
		
		for (int i = 0; i < allAnswers.size(); i++) {
			Answers answer = allAnswers.get(i);
			answers.add(new AnswersNameSet(answer.getMemberName(), answer.getAText()));
		}
		return answers;
	}
	
	public String[] getMemberAnswers(String memberCode, String memberName) {
		Long teamId = teamCodesService.getTeamId(memberCode);
		List<Answers> answerList = answersRepository.findByTeamIdAndMemberName(teamId, memberName);	
		String[] answers = new String[answerList.size()];
		
		for (int i = 0; i < answerList.size(); i++) {
			answers[i] = answerList.get(i).getAText();
		}
		return answers;
	}
	
	public void updateMemberAnswer(String memberCode, String memberName, AnswersQNumberSet data) { // qNumber, aText
		Long teamId = teamCodesService.getTeamId(memberCode);
		Answers answer =  answersRepository.findByTeamIdAndQNumberAndMemberName(teamId, data.qNumber, memberName);
		answer.setAText(data.aText);
		answersRepository.saveAndFlush(answer);
	}
	
	public void createMemberAnswer(String memberCode, String memberName, AnswersQNumberSet data) { // qNumber, aText
		Long teamId = teamCodesService.getTeamId(memberCode);
		Answers answer = new Answers(teamId, data.qNumber, memberName, data.aText);
		answersRepository.saveAndFlush(answer);
		teamMembersService.updateALast(teamId, memberName, data.qNumber);
	}
	
	public MemberProgressSet getProgress(String memberCode, String memberName) {
		Long teamId = teamCodesService.getTeamId(memberCode);
		int qCount = teamInfoService.getQCount(teamId);
		int aLast = teamMembersService.getALast(teamId, memberName);
		return new MemberProgressSet(qCount, aLast);
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	public static class AnswersNameSet {
		public String memberName;
		public String aText;	
	}
	
	@AllArgsConstructor
	@NoArgsConstructor
	public static class AnswersQNumberSet {
		public int qNumber;
		public String aText;
	}
	
	@AllArgsConstructor
	@NoArgsConstructor
	public static class MemberProgressSet {
		public int qCount;
		public int aLast;
	}

}