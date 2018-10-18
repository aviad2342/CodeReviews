package model;

import java.util.ArrayList;

public class Team {
	
	//-------------------------------Class Members------------------------------
	/**
	 * Team id number - the key
	 */
	private int teamNumber;
	
	/**
	 * Team name
	 */
	private String teamName;
	
	/**
	 * Team Leader id
	 */
	private Programmer teamLeader;
	
	/**
	 * Team Members
	 */
	private ArrayList<Programmer> teamMembers;
	
	/**
	 * Team score
	 */
	private int score;
		
	/**
	 * Team Symbol
	 */
	private String teamSymbol;
	
	
	//-------------------------------Constructors------------------------------
	
	/**
	 * Full Constructor ~ use for initial all fields
	 * 
	 * @param teamNumber
	 * @param teamName
	 * @param teamLeader
	 * @param teamMembers
	 * @param teamSymbol
	 */
	public Team(int teamNumber, String teamName, Programmer teamLeader, ArrayList<Programmer> teamMembers, String teamSymbol) {
		super();
		setTeamNumber(teamNumber);      
		setTeamName(teamName);
		setTeamLeader(teamLeader);
		setTeamMembers(teamMembers);
		setTeamSymbol(teamSymbol);
		
	}
	        /**
	 * Full Constructor ~ use for initial all fields
	 * 
	 * @param teamNumber
	 * @param teamName
	 * @param teamLeader
	 * @param teamMembers
	 * @param teamSymbol
         * @param score
	 */
	public Team(int teamNumber, String teamName, Programmer teamLeader, ArrayList<Programmer> teamMembers, String teamSymbol, int score) {
		super();
                setScore(score);
		setTeamNumber(teamNumber);      
		setTeamName(teamName);
		setTeamLeader(teamLeader);
		setTeamMembers(teamMembers);
		setTeamSymbol(teamSymbol);
		
	}
        /**
	 * Partial Constructor ~ use for initial fields
	 * 
	 * @param teamNumber
	 */
	public Team(int teamNumber) {
		super();
		setTeamNumber(teamNumber);
	
	}
        
	//-------------------------------Getters And Setters------------------------------

	public int getTeamNumber() {
		return teamNumber;
	}

	public void setTeamNumber(int teamNumber) {
		this.teamNumber = teamNumber;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Programmer getTeamLeader() {
		return teamLeader;
	}

	public void setTeamLeader(Programmer teamLeader) {
		this.teamLeader = teamLeader;
	}

	public ArrayList<Programmer> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(ArrayList<Programmer> teamMembers) {
		this.teamMembers = teamMembers;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getTeamSymbol() {
		return teamSymbol;
	}

	public void setTeamSymbol(String teamSymbol) {
		this.teamSymbol = teamSymbol;
	}
	
	//-------------------------------Methods------------------------------------------
        
        public void UpdateScore(int score) {
		this.score += score;
	}
        
	/**
	 * this method adds a new Team Member to the Team Members arrayList
	 * @param member
	 * @return true if the member added successfully or false otherwise
	 */
	public boolean addTeamMember(Programmer member){
		if(member != null)
			if(!teamMembers.contains(member))
				return teamMembers.add(member);
		return false;
	}// ~ END OF Method addTeamMember
	
	/**
	 * this method removes a Team Member from the Team Members arrayList
	 * @param member
	 * @return true if the member removed successfully or false otherwise
	 */
	public boolean removeTeamMember(Programmer member){
		if(member != null)
			if(teamMembers.contains(member))
				return teamMembers.remove(member);
		return false;
	}// ~ END OF Method removeTeamMember
	

	//-------------------------------hashCode equals & toString------------------------------
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + teamNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		if (teamNumber != other.teamNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Team [teamNumber=" + teamNumber + ", teamName="
				+ teamName + ", teamLeader=" + teamLeader
				+ ", teamMembers=" + teamMembers + ", score=" + score
				+ ", teamSymbol=" + teamSymbol + "]";
	}
	

}
