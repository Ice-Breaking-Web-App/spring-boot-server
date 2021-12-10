package icebreaker.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Questions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column
	private Long teamId;

	@NotNull
	@Column
	private int questionNumber;
	
	@NotNull
	@Column(length = 30)
	private String qText;
	
	public Questions(Long teamId, int qNumber, String qText) {
		this.setTeamId(teamId);
		this.setQuestionNumber(qNumber);
		this.setQText(qText);
	}
	
}