package icebreaker.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TeamCodes {

	@Id
	private Long teamId;
	
	@NotNull
	@Column(length = 10, unique = true)
	private String leaderCode;
	
	@NotNull
	@Column(length = 10, unique = true)
	private String memberCode;
	
	public TeamCodes(String leaderCode, String memberCode) {
		this.setLeaderCode(leaderCode);
		this.setMemberCode(memberCode);
	}
	
}