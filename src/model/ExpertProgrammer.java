package model;

import java.util.ArrayList;

public class ExpertProgrammer extends Programmer {
	
	//-------------------------------Class Members----------------------------------
	/**
	 * Expert Programmer's Languages
	 */
	private ArrayList<String> languages;
	
	
	
	//-------------------------------Constructors----------------------------------
        
        /**
	 * Full Constructor ~ use for initial all fields
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
     * @param score
	 * @param password
	 * @param email
	 * @param icon
     * @param team
     * @param languages
	 */
	public ExpertProgrammer(String id, String firstName, String lastName, int score, String password, String email, String icon, int team, ArrayList<String> languages) {
		super(id, firstName, lastName,score, password, email, icon, team);
		setLanguages(languages);
	}
        
	/**
	 * Partial Constructor ~ use for initial fields
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param password
	 * @param email
	 * @param icon
     * @param team
     * @param languages
	 */
	public ExpertProgrammer(String id, String firstName, String lastName, String password, String email, String icon, int team, ArrayList<String> languages) {
		super(id, firstName, lastName, password, email, icon, team);
		setLanguages(languages);
	}

    /**
	 * Partial Constructor ~ use for initial fields
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param password
	 * @param email
	 * @param icon
     * @param languages
	 */
	public ExpertProgrammer(String id, String firstName, String lastName, String password, String email, String icon, ArrayList<String> languages) {
		super(id, firstName, lastName, password, email, icon);
		setLanguages(languages);
	}
        
    /**
	 * Partial Constructor ~ use for initial fields
	 * 
	 * @param id
	 */
	public ExpertProgrammer(String id) {
		super(id);
	}
	//-------------------------------Getters And Setters------------------------------
	
	public ArrayList<String> getLanguages() {
		return languages;
	}

	public void setLanguages(ArrayList<String> languages) {
		this.languages = languages;
	}
	
	//-------------------------------Methods------------------------------------------

	/**
	* this method adds a new language to the languages arrayList
	* @param language
    * @return true if the language added successfully or false otherwise
	*/
	public boolean addLanguage(String language){
	    if(language != null){
		if(!languages.contains(language))
		    return languages.add(language);
            }
            return false;
	}// ~ END OF Method addLanguages
		
   /**
	* this method removes a language from the languages arrayList
	* @param language
	* @return true if the language removed successfully or false otherwise
	*/
	public boolean removeLanguage(String language){
	    if(language != null){
		if(languages.contains(language))
		    return languages.remove(language);
            }
	    return false;
	}// ~ END OF Method removeOrder

	//-------------------------------toString--------------------------------------
		
	@Override
	public String toString() {
	    return "Expert " + super.toString();
	}

}
