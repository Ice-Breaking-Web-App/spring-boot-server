package icebreaker.project.service;

import java.util.ArrayList;
import java.util.Collections;
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
	@Autowired
	TeamInfoService teamInfoService;
	
	public void createMembers(String leaderName, String[] members, Long teamId) {
		List<TeamMembers> teamMemberList = new ArrayList<TeamMembers>();
		
		for (int i = 0; i < members.length; i++) {
			TeamMembers teamMember = new TeamMembers(teamId, members[i]);
			if (members[i].equals(leaderName)) {
				teamMember.setLeader(true);
				teamMember.setStatus(MemberStatus.joining);
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
	
	public void updateMemberStatus(String memberCode, String memberName, MemberStatus status) {
		Long teamId = teamCodesService.getTeamId(memberCode);
		TeamMembers teamMember = teamMembersRepository.findByTeamIdAndMemberName(teamId, memberName);
		teamMember.setStatus(status);
		teamMembersRepository.saveAndFlush(teamMember);
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
		Collections.sort(scoreSetList, Collections.reverseOrder());
		return scoreSetList;
	}
	
	public void updateALast(Long teamId, String memberName, int qNumber) {
		TeamMembers member = teamMembersRepository.findByTeamIdAndMemberName(teamId, memberName);
		int qCount = teamInfoService.getQCount(teamId);
		if (qCount == qNumber) {
			member.setStatus(MemberStatus.done);
		}
		member.setALast(qNumber);
		teamMembersRepository.saveAndFlush(member);
		updateBoardAvailability(teamId);
	}
	
	public int getALast(Long teamId, String memberName) {
		TeamMembers member = teamMembersRepository.findByTeamIdAndMemberName(teamId, memberName);
		return member.getALast();
	}
	
	public void updateBoardAvailability(Long teamId) {
		List<TeamMembers> memberList = teamMembersRepository.findByTeamId(teamId);
		int count = 0;
		for (int i = 0; i < memberList.size(); i++) {
			if (memberList.get(i).getStatus().equals(MemberStatus.done)) {
				count++;
				continue;
			} else {
				break;
			}
		}
		if (count == memberList.size()) {
			TeamInfo infoData = teamInfoRepository.findById(teamId).get();
			infoData.setBoardAvailable(true);
			teamInfoRepository.saveAndFlush(infoData);
		}
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
	public static class ScoreSet implements Comparable<ScoreSet> {
		public String memberName;
		public int score;
		
		@Override
		public int compareTo(ScoreSet scoreSet) {
			if (scoreSet.score < score) {
				return 1;
			} else if (scoreSet.score > score) {
				return -1;
			} else {
				return 0;
			}
		}
	}
	
}