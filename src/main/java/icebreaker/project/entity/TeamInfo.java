package icebreaker.project.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class TeamInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long teamId;
	
	@Column(length = 10)
	private String teamName; // default `ÆÀ ${teamId}`·Î ¼³Á¤
	
	@Column(length = 7)
	@ColumnDefault("'gray'")
	@Enumerated(EnumType.STRING)
	private TeamColors teamColor;
	
	@Column(length = 7)
	@ColumnDefault("'leader'")
	private String leaderName;
	
	@Column
	@ColumnDefault("1")
	private int memberCount;
	
	@Column
	@ColumnDefault("1")
	private int qCount;
	
	@Column
	@ColumnDefault("false")
	private boolean isBoardAvailable;
	
	@Column
	@ColumnDefault("false")
	private boolean isPaid;
	
	@CreationTimestamp
	private Timestamp createdAt;
	
	public enum TeamColors { red, orange, yellow, green, skyblue, blue, purple, pink, brown, gray };
	
	public TeamInfo(String teamName, String leaderName, TeamColors teamColor, int qCount, int memberCount) {
		this.setTeamName(teamName);
		this.setLeaderName(leaderName);
		this.setTeamColor(teamColor);
		this.setQCount(qCount);
		this.setMemberCount(memberCount);
	}
	
}