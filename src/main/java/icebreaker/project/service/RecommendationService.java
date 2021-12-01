package icebreaker.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import icebreaker.project.entity.Recommendation;
import icebreaker.project.repository.RecommendationRepository;

@Service
public class RecommendationService {

	@Autowired
	RecommendationRepository recommendationRepository;
	
	public static final int RAND_NUM = 3;
	
	public String[] getRandomTags() {
		List<String> tagList = recommendationRepository.selectAllTags();
		List<Integer> randomNumberList = getRandomNumbers(tagList.size());
		String[] tags = new String[RAND_NUM];
		
		for (int i = 0; i < RAND_NUM; i++) {
			tags[i] = tagList.get(randomNumberList.get(i));
		}
		return tags;
	}
	
	public String getRandomQuestion(String tag) {
		List<Recommendation> recommendList = recommendationRepository.findByTag(tag);
		int randomNumber = getRandomNumber(recommendList.size());
		return recommendList.get(randomNumber).getQuestion();
	}
	
	public List<Integer> getRandomNumbers(int length) {
		Random rd = new Random();
		List<Integer> numberList = new ArrayList<Integer>();
		
		for(int i = 0; i < RAND_NUM; i++) {
			int newNum = rd.nextInt(length);
			if (numberList.contains(newNum)) {
				i--;
				continue;
			} else {
				numberList.add(newNum);
			}
		}
		return numberList;
	}
	
	public int getRandomNumber(int length) {
		Random rd = new Random();
		return rd.nextInt(length);
	}
	
}