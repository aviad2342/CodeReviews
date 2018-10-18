package model;

import java.io.File;
import java.util.Date;


public class CodeFile {
	
	//-------------------------------Class Members------------------------------
	/**
	 * File serial number - the key
	 */
	private int serialNumber;
	
	/**
	 * File name
	 */
	private String fileName;
	
	/**
	 * File author
	 */
	private Programmer author;
	
        /**
	 * File date
	 */
	private Date date;
        
	/**
	 * File functionality Description
	 */
	private String functionalityDescription;
	
	/**
	 * File Attached
	 */
	private File fileAttached;
		
	/**
	 * File Programming language
	 */
	private String language;
	
	
	//-------------------------------Constructors----------------------------------
	
	/**
	 * Full Constructor ~ use for initial all fields
	 * 
	 * @param serialNumber
	 * @param fileName
	 * @param author
         * @param date
	 * @param functionalityDescription
	 * @param fileAttached
	 * @param language
	 */
	public CodeFile(int serialNumber, String fileName, Programmer author, Date date, String functionalityDescription, File fileAttached, String language) {
		super();
		setSerialNumber(serialNumber);
		setFileName(fileName);
		setAuthor(author);
                setDate(date);
		setFunctionalityDescription(functionalityDescription);
		setFileAttached(fileAttached);
		setLanguage(language);
	}

	//-------------------------------Getters And Setters------------------------------

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Programmer getAuthor() {
		return author;
	}

	public void setAuthor(Programmer author) {
		this.author = author;
	}

	public String getFunctionalityDescription() {
		return functionalityDescription;
	}

	public void setFunctionalityDescription(String functionalityDescription) {
		this.functionalityDescription = functionalityDescription;
	}

	public File getFileAttached() {
		return fileAttached;
	}

	public void setFileAttached(File fileAttached) {
		this.fileAttached = fileAttached;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

        public Date getDate() {
                return date;
        }

        public void setDate(Date date) {
                this.date = date;
        }

        
	//-------------------------------hashCode equals & toString------------------------------
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + serialNumber;
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
		CodeFile other = (CodeFile) obj;
		return serialNumber == other.serialNumber;
	}

    @Override
    public String toString() {
        return "CodeFile{" + "serialNumber=" + serialNumber + ", fileName=" + fileName + ", author=" + author + ", date=" + date + ", functionalityDescription=" + functionalityDescription + ", fileAttached=" + fileAttached + ", language=" + language + '}';
    }

}
