package icebreaker.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TeamCodes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long teamId;
	
	@Column(length = 10)
	private String leaderCode;
	
	@Column(length = 10)
	private String memberCode;
	
}