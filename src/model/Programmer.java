package model;

import java.util.Objects;

public class Programmer {
	
	//-------------------------------Class Members------------------------------
	/**
	 * Programmer's id number - the key
	 */
	private String id;
	
	/**
	 * Programmer's first name
	 */
	private String firstName;
	
	/**
	 * Programmer's sure name
	 */
	private String lastName;
	
	/**
	 * Programmer's score
	 */
	private int score;
	
	/**
	 * Programmer's password for enter the system
	 */
	private String password;
	
	/**
	 * Programmer's email
	 */
	private String email;
	
    /**
	 * Programmer's icon
	 */
	private String icon;
	
	/**
	 * Programmer's team
	 */
	private int team;
	
	
	//-------------------------------Constructors------------------------------
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
	 */
	public Programmer(String id, String firstName, String lastName, int score, String password, String email, String icon, int team) {
		super();
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
                setScore(score);
		setEmail(email);
		setPassword(password);
                setIcon(icon);
		setTeam(team);
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
	 */
	public Programmer(String id, String firstName, String lastName, String password, String email, String icon, int team) {
		super();
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPassword(password);
                setIcon(icon);
		setTeam(team);
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
	 */
	public Programmer(String id, String firstName, String lastName, String password, String email, String icon) {
		super();
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPassword(password);
                setIcon(icon);
	}
            
        /**
	 * Partial Constructor ~ use for initial fields
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param password
	 * @param email
	 */
	public Programmer(String id, String firstName, String lastName, String password, String email) {
		super();
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPassword(password);
	}
            
        /**
	 * Partial Constructor ~ use for initial fields
	 * 
	 * @param id
	 */
	public Programmer(String id) {
		super();
		setId(id);
	}
	//-------------------------------Getters And Setters------------------------------

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	      
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}
	//******************************* Other Methods *****************************************
        
        public void UpdateScore(int score) {
		this.score += score;
	}
        
        
	//-------------------------------hashCode equals & toString------------------------------

        @Override
        public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
        }

        @Override
        public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Programmer other = (Programmer) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
        }
	
	

        @Override
        public String toString() {
        return "Programmer  ID: " + id + " Name: " + firstName+ " " + lastName + "";
        }

}
