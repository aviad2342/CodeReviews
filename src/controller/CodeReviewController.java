package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import model.Bug;
import model.CodeFile;
import model.CodeReview;
import model.ExpertProgrammer;
import model.ModelLogic;
import model.Programmer;
import model.Team;
import utils.Constants;
import utils.E_ScoreManager;


public class CodeReviewController {
	
	//***************************************** Variables *********************************************

    /**
     * Singleton instance of this class, loaded on the first execution of ControllerLogic.getInstance()
     */
    private static CodeReviewController instance;

    /**
     * ModelLogic reference pointer
     */
    private static ModelLogic model; //assuming we've only one.
        
    //***************************************** Constructors ******************************************
    /**
     * Full C'tor, for singleton support.
     */
    private CodeReviewController() {
            model = ModelLogic.getInstance();
        new Constants();
    }
    //***************************************** Methods ***********************************************

    /**
     * The method creates this class's instance & provides access to it, by returning a reference (singleton).
     * @return reference to this class's only instance, or null if reference was already returned (singleton).
     */
    public static CodeReviewController getInstance() {
        if (instance == null) {     // if controller is null create new viewLogic object
               instance = new CodeReviewController();
              return instance;
           }
         return instance;
    }					
	
  //*****************************************Get Methods *************************************************

         public ArrayList<String> getAllLanguages()
         {    
            return model.getAllLanguages();
         }
         
         public ArrayList<Programmer> getAllProgrammers(){
             return model.getAllProgrammers();
         }
    
         public Programmer getAProgrammerById(String id){
             return model.getAProgrammerById(id);
         }
         public ArrayList<ExpertProgrammer> getAllExpertProgrammers(){
             return model.getAllExpertProgrammers();
         }
     
         public ArrayList<Team> getAllTeams(){
             return model.getAllTeams();
         }
         
         public Team getTeamById(int teamId){
             return model.getTeamById(teamId);
         }
         
        public ArrayList<CodeReview> getAllCompletedReviews(ExpertProgrammer logEuser) {
        return model.getAllCompeletedReviews(logEuser);
         }
                   
         public ArrayList<CodeReview> getAllCodeReviews(){
             return model.getAllCodeReviews();
         }

         public ArrayList<CodeFile> getAllUpcomingReviews(ExpertProgrammer expertProgrammer){
             return model.getAllUpcomingReviews(expertProgrammer);
         }
         
         public ArrayList<CodeReview> getAllReturnedReviews(Programmer programmer){
              return model.getAllReturnedReviews(programmer);
         }
         
         public ArrayList<CodeReview> getAllCompeletedReviews(ExpertProgrammer expertProgrammer){
              return model.getAllCompeletedReviews(expertProgrammer);
         }
         
         public ArrayList<CodeReview> getAllSharedReviews(){
              return model.getAllSharedReviews();
         }
  //***************************************** Add Methods ******************************************
	
	/**
	 * this method add new Code File object to database IIF the Code File not already
	 * exists and the details are valid.
	 * 
	 * @param serialNumber
	 * @param fileName
	 * @param author
         * @param date
	 * @param functionalityDescription
	 * @param fileAttached
	 * @param language
	 * @return true if the Code File added successfully, false otherwise
	 */
	public boolean addCodeFile(int serialNumber, String fileName, Programmer author, Date date, String functionalityDescription,
			File fileAttached, String language) {
                CodeFile codeFile = new CodeFile(serialNumber, fileName, author, date, functionalityDescription, fileAttached, language);
		return model.addCodeFile(codeFile);
	}
    
	/**
	 * this method add new Code Review object to database IIF the Code Review not already
	 * exists and the details are valid.
	 * 
	 * @param reviewNumber
	 * @param file
	 * @param reviewer
	 * @param codeRating
     * @param bugs
         * @param date
	 * @param suggestionTips
         * @param Status
	 * @return true if the Code Review added successfully, false otherwise
	 */
	public boolean addCodeReview(int reviewNumber, CodeFile file, ExpertProgrammer reviewer, int codeRating, ArrayList<Bug> bugs, Date date, String suggestionTips, Boolean Status) {
                CodeReview codeReview = new CodeReview(reviewNumber, file, reviewer, codeRating, bugs, date, suggestionTips, Status);
		return model.addCodeReview(codeReview);
	}

	/**
	 * this method add new Programmer object to database IIF the Programmer not already
	 * exists and the details are valid.
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param password
	 * @param email
             * @param icon
	 * @return true if the Programmer added successfully, false otherwise
	 */
	public boolean addProgrammer(String id, String firstName, String lastName, String password, String email, String icon) {
                Programmer programmer = new Programmer(id, firstName, lastName, password, email, icon);
	    return model.addProgrammer(programmer);
                    
	}

	/**
	 * this method add new Expert Programmer object to database IIF the Expert Programmer not already
	 * exists and the details are valid.
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param password
	 * @param email
             * @param icon
	 * @param languages
	 * @return true if the Expert Programmer added successfully, false otherwise
	 */
	public boolean addExpertProgrammer(String id, String firstName, String lastName, String password, String email, String icon,
			ArrayList<String> languages) {
                ExpertProgrammer expertProgrammer = new ExpertProgrammer(id, firstName, lastName, password, email, icon, languages);
		return model.addExpertProgrammer(expertProgrammer);
		
	}

	/**
	 * this method add new Team object to database IIF the Team not already
	 * exists and the details are valid.
	 * 
	 * @param teamNumber
	 * @param teamName
	 * @param teamLeader
	 * @param teamMembers
	 * @param teamSymbol
	 * @return true if the Team added successfully, false otherwise
	 */
	public boolean addTeam(int teamNumber, String teamName, Programmer teamLeader, ArrayList<Programmer> teamMembers, String teamSymbol) {
                Team team = new Team(teamNumber, teamName, teamLeader, teamMembers, teamSymbol);
		return model.addTeam(team);
	}

        
        public boolean ChangeTeamMember(Programmer p,int teamID) {
            return model.ChangeTeamMember(p, teamID);
        }
        
        public ArrayList<Team> getContributedTeams(Team t){
           return model.getContributedTeams(t);
        }
	//***************************************** Remove Methods ******************************************
	
	/**
	 * this method remove Code File object from database IIF the Code File already exists.
	 * 
	 * @param serialNumber
	 * @return true if the Code File removed successfully, false otherwise
	 */
	public boolean removeCodeFile(int serialNumber) {
		return model.removeCodeFile(serialNumber);
	}

	/**
     * this method Code Review File object from database IIF the Code Review already exists.
	 *
     * @param reviewNumber
     * @return true if the Code Review removed successfully, false otherwise
     */
	public boolean removeCodeReview(int reviewNumber) {
		return model.removeCodeReview(reviewNumber);
	}

	/**
	 * this method remove Programmer object from database IIF the Programmer already exists.
	 *
	 * @param id
	 * @return true if the Programmer removed successfully, false otherwise
	 */
	public boolean removeProgrammer(String id) {
		return model.removeProgrammer(id);
	}

	/**
	 * this method remove Expert Programmer object from database IIF the Expert Programmer already exists.
	 *
	 * @param id
	 * @return true if the Expert Programmer removed successfully, false otherwise
	 */
	public boolean removeExpertProgrammer(String id) {
		return model.removeExpertProgrammer(id);
	}

	/**
	 * this method remove Team object from database IIF the Team already exists.
	 *
	 * @param teamNumber
	 * @return true if the Team removed successfully, false otherwise
	 */
	public boolean removeTeam(int teamNumber) {
		return model.removeTeam(teamNumber);
	}

	//***************************************** Update Methods ******************************************
	
	/**
	 * this method Update Code File object in database IIF the Code File already exists
	 * and the details are valid.
	 *
	 * @param serialNumber
	 * @param fileName
	 * @param author
         * @param date
	 * @param functionalityDescription
	 * @param fileAttached
	 * @param language
	 * @return true if the Code File was successfully update, false otherwise
	 */
	public boolean updateCodeFile(int serialNumber, String fileName, Programmer author, Date date, String functionalityDescription,
			File fileAttached, String language) {
                CodeFile codeFile = new CodeFile(serialNumber, fileName, author, date, functionalityDescription, fileAttached, language);
		return model.updateCodeFile(codeFile);
	}

    /**
     * this method Update Code Review object in database IIF the Code Review already exists
     * and the details are valid.
     *
     * @param reviewNumber
     * @param file
     * @param reviewer
     * @param codeRating
     * @param bugs
     * @param date
     * @param suggestionTips
     * @param Status
     * @return true if the Code Review was successfully update, false otherwise
     */
	public boolean updateCodeReview(int reviewNumber, CodeFile file, ExpertProgrammer reviewer, int codeRating, ArrayList<Bug> bugs, Date date, String suggestionTips, Boolean Status) {
                CodeReview codeReview = new CodeReview(reviewNumber, file, reviewer, codeRating, bugs, date, suggestionTips, Status);
		return model.updateCodeReview(codeReview);
	}

	/**
	 * this method Update Programmer object in database IIF the Programmer already exists
	 * and the details are valid.
	 *
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param password
	 * @param email
             * @param icon
	 * @return true if the Programmer was successfully update, false otherwise
	 */
	public boolean updateProgrammer(String id, String firstName, String lastName, String password, String email, String icon) {
                Programmer programmer = new Programmer(id, firstName, lastName, password, email, icon);
		return model.updateProgrammer(programmer);
	}

	/**
	 * this method Update Expert Programmer object in database IIF the Expert Programmer already exists
	 * and the details are valid.
	 *
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param password
	 * @param email
         * @param icon
	 * @param languages
	 * @return true if the Expert Programmer was successfully update, false otherwise
	 */
	public boolean updateExpertProgrammer(String id, String firstName, String lastName, String password, String email, String icon,
			ArrayList<String> languages) {
                ExpertProgrammer expertProgrammer = new ExpertProgrammer(id, firstName, lastName, password, email, icon, languages);
		return model.updateProgrammer(expertProgrammer);
	}

	/**
	 * this method Update Team object in database IIF the Team already exists
	 * and the details are valid.
	 *
	 * @param teamNumber
	 * @param teamName
	 * @param teamLeader
	 * @param teamMembers
	 * @param teamSymbol
	 * @return true if the Team was successfully update, false otherwise
	 */
	public boolean updateTeam(int teamNumber, String teamName, Programmer teamLeader, ArrayList<Programmer> teamMembers, String teamSymbol) {
                Team team = new Team(teamNumber, teamName, teamLeader, teamMembers, teamSymbol);
		return model.updateTeam(team);
	}
        
        
        public boolean UpdateScore(Programmer programmer, Team team , E_ScoreManager scoreManager){
                return model.UpdateScore(programmer, team, scoreManager);
        }
        
        public boolean UpdateScoreWithBugs(Programmer programmer, Team team , E_ScoreManager scoreManager, int num){
                return model.UpdateScoreWithBugs(programmer, team, scoreManager, num);
        }

}
