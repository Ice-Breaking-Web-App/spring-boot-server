package icebreaker.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import icebreaker.project.entity.Questions;
import icebreaker.project.repository.QuestionsRepository;

@Service
public class QuestionsService {

	@Autowired
	QuestionsRepository questionsRepository;
	@Autowired
	TeamCodesService teamCodesService;
	
	public String[] getAllQuestions(String memberCode) {
		Long teamId = teamCodesService.getTeamId(memberCode);
		List<Questions> questionList = questionsRepository.findByTeamId(teamId);
		
		String[] questions = new String[questionList.size()];
		for (int i = 0; i < questionList.size(); i++) {
			questions[i] = questionList.get(i).getQText();
		}
		return questions;
	}
	
	public void setAllQuestions(String[] questions, Long teamId) {
		List<Questions> questionList = new ArrayList<Questions>();
		
		for (int i = 0; i < questions.length; i++) {
			Questions newQuestion = new Questions(teamId, i + 1, questions[i]);
			questionList.add(newQuestion);
		}
		questionsRepository.saveAllAndFlush(questionList);
	}
	
}