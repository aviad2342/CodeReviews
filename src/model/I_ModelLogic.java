package model;

import java.util.ArrayList;

public interface I_ModelLogic {
	
	//***************************************** CRUD Methods ******************************************
	
	//***************************************** Add Methods ******************************************
	/**
	 * this method add new Code File object to database IIF the Code File not already
	 * exists and the details are valid.
	 * 
         * @param codeFile
	 * @return true if the Code File added successfully, false otherwise
	 */
	public boolean addCodeFile(CodeFile codeFile);
	
	/**
	 * this method add new Code Review object to database IIF the Code Review not already
	 * exists and the details are valid.
	 * 
         * @param codeReview
	 * @return true if the Code Review added successfully, false otherwise
	 */
	public boolean addCodeReview(CodeReview codeReview);
	
	/**
	 * this method add new Programmer object to database IIF the Programmer not already
	 * exists and the details are valid.
	 * 
         * @param programmer
	 * @return true if the Programmer added successfully, false otherwise
	 */
	public boolean addProgrammer(Programmer programmer);
	
	/**
	 * this method add new Expert Programmer object to database IIF the Expert Programmer not already
	 * exists and the details are valid.
	 * 
         * @param expertProgrammer
	 * @return true if the Expert Programmer added successfully, false otherwise
	 */
	public boolean addExpertProgrammer(ExpertProgrammer expertProgrammer);
	
	/**
	 * this method add new Team object to database IIF the Team not already
	 * exists and the details are valid.
	 * 
         * @param team
	 * @return true if the Team added successfully, false otherwise
	 */
	public boolean addTeam(Team team);
	
	//***************************************** Remove Methods ******************************************
	
	/**
	 * this method remove Code File object from database IIF the Code File already exists.
	 * 
	 * @param serialNumber
	 * @return true if the Code File removed successfully, false otherwise
	 */
        public boolean removeCodeFile(int serialNumber);
	
        /**
         * this method Code Review File object from database IIF the Code Review already exists.
	 *
         * @param reviewNumber
         * @return true if the Code Review removed successfully, false otherwise
         */
	public boolean removeCodeReview(int reviewNumber);
	
	/**
	 * this method remove Programmer object from database IIF the Programmer already exists.
	 *
	 * @param id
	 * @return true if the Programmer removed successfully, false otherwise
	 */
	public boolean removeProgrammer(String id);
	
	/**
	 * this method remove Expert Programmer object from database IIF the Expert Programmer already exists.
	 *
	 * @param id
	 * @return true if the Expert Programmer removed successfully, false otherwise
	 */
	public boolean removeExpertProgrammer(String id);
	
	/**
	 * this method remove Team object from database IIF the Team already exists.
	 *
	 * @param teamNumber
	 * @return true if the Team removed successfully, false otherwise
	 */
	public boolean removeTeam(int teamNumber);
	
	
	//***************************************** Update Methods ******************************************
	
	/**
	 * this method Update Code File object in database IIF the Code File already exists
	 * and the details are valid.
	 *
         * @param codeFile
	 * @return true if the Code File was successfully update, false otherwise
	 */
         public boolean updateCodeFile(CodeFile codeFile);
	
        /**
         * this method Update Code Review object in database IIF the Code Review already exists
         * and the details are valid.
         *
         * @param codeReview     
         * @return true if the Code Review was successfully update, false otherwise
         */
	public boolean updateCodeReview(CodeReview codeReview);
	
	/**
	 * this method Update Programmer object in database IIF the Programmer already exists
	 * and the details are valid.
	 *
         * @param programmer
	 * @return true if the Programmer was successfully update, false otherwise
	 */
	public boolean updateProgrammer(Programmer programmer);
	
	/**
	 * this method Update Expert Programmer object in database IIF the Expert Programmer already exists
	 * and the details are valid.
	 *
         * @param expertProgrammer
	 * @return true if the Expert Programmer was successfully update, false otherwise
	 */
	public boolean updateExpertProgrammer(ExpertProgrammer expertProgrammer);
	
	/**
	 * this method Update Team object in database IIF the Team already exists
	 * and the details are valid.
	 *
         * @param team
	 * @return true if the Team was successfully update, false otherwise
	 */
	public boolean updateTeam(Team team);
	
	//***************************************** Other Methods ******************************************

	public ArrayList<Programmer> getAllProgrammers();
        
        public ArrayList<ExpertProgrammer> getAllExpertProgrammers();
         
        public ArrayList<Team> getAllTeams();
        
        public ArrayList<CodeReview> getAllCodeReviews();
 

}
