package icebreaker.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import icebreaker.project.entity.Recommendation;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
	
	@Query(value = "SELECT DISTINCT tag FROM Recommendation", nativeQuery = true)
	public List<String> selectAllTags();
	
	public List<Recommendation> findByTag(String tag);
	
}