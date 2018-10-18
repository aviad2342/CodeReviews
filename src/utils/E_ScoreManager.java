/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Aviad
 */
public enum E_ScoreManager {

        INITIAL_SCORE(20,0), 
	//WRITE_CODE(5,0),
        ASK_REVIEW(10,2), 
	WRITE_REVIEW(5,2), 
        SEND_REVIEW(8,3), 
        SHARE_REVIEW(8,3),
        SHARE_KNOWLEDGE(5,5),
	SHARE_TIPS(5,3), 
        USE_REVIEW_KNOWLEDGE(4,2),
	USE_TIPS(3,1);

	/**
	 * Programmer's Score
	 */
	private final int programmerScore;

	/**
	 * Team's Score
	 */
	private final int teamScore;

	//-------------------------------------------------------------Constructor------------------------------------------------------------------
	E_ScoreManager(int programmerScore, int teamScore){
		this.programmerScore = programmerScore;
		this.teamScore = teamScore;
	}

	//-------------------------------------------------------------Methods----------------------------------------------------------------------
	public int getProgrammerScore() { 
		return programmerScore; 
	}
        public int addToProgrammerScore(int num) { 
		return programmerScore + num; 
	}
	public int getTeamScore() { 
		return teamScore; 
	}
    
}
