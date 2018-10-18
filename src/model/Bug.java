/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Aviad
 */
public class Bug {
    
    //-------------------------------Class Members------------------------------
    /**
     * Bugs id
     */
    private int bugId;

    /**
     * Bugs Description
     */
    private String bugsDescription;
    
    //-------------------------------Constructors----------------------------------
    
    public Bug(int bugId, String bugsDescription) {
        setBugId(bugId);
        setBugsDescription(bugsDescription);
    }
    
    public Bug(String bugsDescription) {
        setBugsDescription(bugsDescription);
    }
    //-------------------------------Getters And Setters------------------------------
    
    public int getBugId() {
        return bugId;
    }

    public void setBugId(int bugId) {
        this.bugId = bugId;
    }

    public String getBugsDescription() {
        return bugsDescription;
    }

    public void setBugsDescription(String bugsDescription) {
        this.bugsDescription = bugsDescription;
    }
    
    
    //-------------------------------hashCode equals & toString------------------------------

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.bugId;
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
        final Bug other = (Bug) obj;
        if (this.bugId != other.bugId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bug{" + "bugId=" + bugId + ", bugsDescription=" + bugsDescription + '}';
    }
    
}
