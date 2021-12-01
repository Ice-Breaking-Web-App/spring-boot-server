package icebreaker.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import icebreaker.project.service.RecommendationService;

@CrossOrigin
@RestController
@RequestMapping("api/recommend")
public class RecommendationController {
	
	@Autowired
	RecommendationService recommendationService;
	
	@GetMapping("/tags")
	public String[] getRandomTags() {
		return recommendationService.getRandomTags();
	}
	
	@GetMapping("/question")
	public String getRandomQuestion(@RequestParam String tag) {
		return recommendationService.getRandomQuestion(tag);
	}

}