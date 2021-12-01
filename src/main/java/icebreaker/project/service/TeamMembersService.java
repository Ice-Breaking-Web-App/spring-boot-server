package icebreaker.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import icebreaker.project.entity.TeamInfo;
import icebreaker.project.entity.TeamMembers;
import icebreaker.project.entity.TeamMembers.MemberStatus;
import icebreaker.project.repository.TeamInfoRepository;
import icebreaker.project.repository.TeamMembersRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
public class TeamMembersService {

	@Autowired
	TeamMembersRepository teamMembersRepository;
	@Autowired
	TeamInfoRepository teamInfoRepository;
	@Autowired
	TeamCodesService teamCodesService;
	
	public void createMembers(String leaderName, String[] members, Long teamId) {
		List<TeamMembers> teamMemberList = new ArrayList<TeamMembers>();
		
		for (int i = 0; i < members.length; i++) {
			TeamMembers teamMember = new TeamMembers(teamId, members[i]);
			if (members[i].equals(leaderName)) {
				teamMember.setLeader(true);
			}
			teamMemberList.add(teamMember);
		}
		teamMembersRepository.saveAllAndFlush(teamMemberList);
	}
	
	public List<TeamMembers> getMembersInfo(String memberCode) {
		Long teamId = teamCodesService.getTeamId(memberCode);
		return teamMembersRepository.findByTeamId(teamId);
	}
	
	public StatusSet getTeamStatus(String memberCode) {
		Long teamId = teamCodesService.getTeamId(memberCode);
		List<TeamMembers> teamMemberList = teamMembersRepository.findByTeamId(teamId);
		List<MemberStatusSet> memberStatusSetList = new ArrayList<MemberStatusSet>();
		
		for (TeamMembers member : teamMemberList) {
			MemberStatusSet newSet = new MemberStatusSet(member.getMemberName(), member.getStatus());
			memberStatusSetList.add(newSet);
		}
		
		TeamInfo teamInfo = teamInfoRepository.findById(teamId).get();		
		StatusSet statusSet = new StatusSet(memberStatusSetList, teamInfo.isBoardAvailable());
		return statusSet;
	}
	
	public void updateMemberStatus(String memberCode, String memberName, String status) {
		Long teamId = teamCodesService.getTeamId(memberCode);
		TeamMembers teamMember = teamMembersRepository.findByTeamIdAndMemberName(teamId, memberName);
		
		if (status.equals("joining")) {
			teamMember.setStatus(MemberStatus.joining);
		} else if (status.equals("done")) {
			teamMember.setStatus(MemberStatus.done);
		}
		teamMembersRepository.flush();
	}
	
	public void setMemberScore(String memberCode, String memberName, int score) {
		Long teamId = teamCodesService.getTeamId(memberCode);
		TeamMembers member = teamMembersRepository.findByTeamIdAndMemberName(teamId, memberName);
		member.setScore(score);
		teamMembersRepository.saveAndFlush(member);
	}
	
	public List<ScoreSet> getAllScores(String memberCode) {
		Long teamId = teamCodesService.getTeamId(memberCode);
		List<TeamMembers> teamMemberList = teamMembersRepository.findByTeamId(teamId);
		List<ScoreSet> scoreSetList = new ArrayList<ScoreSet>();
		
		for (TeamMembers member : teamMemberList) {
			ScoreSet newSet = new ScoreSet(member.getMemberName(), member.getScore());
			scoreSetList.add(newSet);
		}
		return scoreSetList;
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	public static class StatusSet {
		public List<MemberStatusSet> statusList;
		public boolean isBoardAvailable;
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	public static class MemberStatusSet {
		public String memberName;
		public MemberStatus status;	
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ScoreSet {
		public String memberName;
		public int score;
	}
	
}