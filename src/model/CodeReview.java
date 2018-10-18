package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CodeReview {
	
	//-------------------------------Class Members------------------------------
	/**
	 * Review number - the key
	 */
	private int reviewNumber;
	
	/**
	 * Review CodeFile
	 */
	private CodeFile file;
	
	/**
	 * Review author
	 */
	private ExpertProgrammer reviewer;
	
	/**
	 * Code Quality Rating
	 */
	private int codeRating;
	
	/**
	 * Bugs Found
	 */
	private ArrayList<Bug> bugs;
	
        /**
	 * Review date
	 */
	private Date date;
	/**
	 * Suggestion And Tips To Improve
	 */
	private String suggestionTips;
		
	/**
	 * Review Share Status
	 */
	private Boolean Status;
	
	
	//-------------------------------Constructors----------------------------------
	
        /**
	 * Full Constructor ~ use for initial all fields
	 * 
	 * @param reviewNumber
	 * @param file
	 * @param reviewer
	 * @param codeRating
         * @param bugs
         * @param date
	 * @param suggestionTips
         * @param Status
	 */
        public CodeReview(int reviewNumber, CodeFile file, ExpertProgrammer reviewer, int codeRating, ArrayList<Bug> bugs, Date date, String suggestionTips, Boolean Status) {
                super();
		setReviewNumber(reviewNumber);
		setFile(file);
		setReviewer(reviewer);
		setCodeRating(codeRating);
                setBugs(bugs);
                setDate(date);
		setSuggestionTips(suggestionTips);
		setStatus(Status);
    }

            
            /**
	 * Partial Constructor ~ use for initial fields
	 * 
	 * @param reviewNumber
	 */
	public CodeReview(int reviewNumber) {
		super();
		setReviewNumber(reviewNumber);
	}
        
	//-------------------------------Getters And Setters------------------------------

	public int getReviewNumber() {
		return reviewNumber;
	}

	public void setReviewNumber(int reviewNumber) {
		this.reviewNumber = reviewNumber;
	}

	public CodeFile getFile() {
		return file;
	}

	public void setFile(CodeFile file) {
		this.file = file;
	}

	public ExpertProgrammer getReviewer() {
		return reviewer;
	}

	public void setReviewer(ExpertProgrammer reviewer) {
		this.reviewer = reviewer;
	}

	public int getCodeRating() {
		return codeRating;
	}

	public void setCodeRating(int codeRating) {
		this.codeRating = codeRating;
	}

	public String getSuggestionTips() {
		return suggestionTips;
	}

	public void setSuggestionTips(String suggestionTips) {
		this.suggestionTips = suggestionTips;
	}

	public Boolean getStatus() {
		return Status;
	}

	public void setStatus(Boolean status) {
		Status = status;
	}

        public ArrayList<Bug> getBugs() {
               return bugs;
        }

        public void setBugs(ArrayList<Bug> bags) {
               this.bugs = bags;
        }

        public Date getDate() {
               return date;
        }

        public void setDate(Date date) {
               this.date = date;
        }
	
        public boolean AddBugs(Bug bag) {
            if(bag != null && !bugs.contains(bag)){
                return bugs.add(bag);
            }
            return false;
        }
        
        public boolean RemoveBugs(Bug bag) {
            if(bag != null && bugs.contains(bag)){
                return bugs.remove(bag);
            }
            return false;
        }
	//-------------------------------hashCode equals & toString------------------------------

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.reviewNumber;
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
        final CodeReview other = (CodeReview) obj;
        return this.reviewNumber == other.reviewNumber;
    }
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    public String toString() {
        return  reviewNumber + ".Published In:<"+ formatter.format(date) +"> By: "+ reviewer.getFirstName()+" "+reviewer.getLastName()+" For<"+file.getFileName()+">";
    }    

}
