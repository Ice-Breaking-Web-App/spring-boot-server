package icebreaker.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class TeamMembers {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column
	private Long teamId;
	
	@NotNull
	@Column(length = 7)
	private String memberName;
	
	@Column
	@ColumnDefault("false")
	private boolean isLeader;
	
	@Column(length = 7)
	@ColumnDefault("'pending'")
	@Enumerated(EnumType.STRING)
	private MemberStatus status;
	
	@Column
	@ColumnDefault("0")
	private int aLast;
	
	@Column
	@ColumnDefault("0")
	private int score;
	
	public TeamMembers(Long teamId, String memberName) {
		this.setTeamId(teamId);
		this.setMemberName(memberName);
	}
	
	public enum MemberStatus { pending, joining, done };
	
}