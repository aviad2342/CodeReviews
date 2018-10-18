package view;


import exceptions.DigitException;
import exceptions.IntegersTestException;
import exceptions.LettersTestException;
import exceptions.MissingDataException;
import exceptions.SpaceStartException;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.*;
import controller.*;
import java.awt.Color;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import utils.E_ScoreManager;
import utils.InputTestsStaticClass;
import utils.SoundClass;


public final class ViewLogic {
	
	//*************************************************** Variables *****************************************************// 
    /**
     * Singleton instance of this class, loaded on the first execution of ViewLogic.getInstance()
     */
    private static ViewLogic instance;
    /**
     * the currently logged in Programmer
     */
    private static Programmer logUser;
    /**
     * the currently logged in Expert Programmer
     */
    private static ExpertProgrammer logEuser;
    /**
     * the currently logged in Programmer's Team
     */
    private static Team userTeam;
    /**
     * whether the currently logged is Expert Programmer
     */
    private static Boolean isExpert;
    /**
     * ControllerLogic reference pointer
     */
    private static CodeReviewController CRcontroller;
    
    private static  JDialog frame;

//*************************************************** Constructors *************************************************
    /**
     * constructor.
     */
    private ViewLogic() {
        CRcontroller = CodeReviewController.getInstance();
        
    }
//*************************************************** Methods *************************************************

    /**
     * The method creates this class's instance & provides access to it, by returning a reference (singleton).
     * @return reference to this class's only instance, or null if reference was already returned (singleton).
     */
    public static ViewLogic getInstance() {
        if (instance == null) {     // if controller is null create new viewLogic object
            instance = new ViewLogic();
            return instance;
        }
        return instance;
    }
    
        public JDialog getAnimation(JFrame curr)
    {
        frame=new JDialog(curr);
        
        java.net.URL imgURL = getClass().getResource("/images/ajax-loader.gif");
        ImageIcon loading = new ImageIcon(imgURL);
        frame.setUndecorated(true);
        
        frame.add(new JLabel(loading));
        frame.setSize(200, 200);
       frame.setLocationRelativeTo(curr);
        
       
        frame.setVisible(true);
        frame.getRootPane().setOpaque(false);
        frame.getContentPane ().setBackground (new Color (0, 0, 0, 0));
        frame.setBackground (new Color (0, 0, 0, 0));
        return frame;
    }		    
    
//*************************************************** Programmer / ExpertProgrammer ************************************************* 
    
    /**
     * 
     * @param txtID
     * @param txtFirstName
     * @param txtLastName
     * @param txtMail
     * @param txtPassWord
     * @param IsExpertCheckBox
     * @param fileName
     * @param LanguagesJList
     * @param frame 
     */
    public void addProgrammer(JTextField txtID, JTextField txtFirstName, JTextField txtLastName, JTextField txtMail, JPasswordField txtPassWord, JCheckBox  IsExpertCheckBox,
            String fileName, JList<String> LanguagesJList, JInternalFrame frame ){
        try{    
            //check if any of textFields are empty.           
            InputTestsStaticClass.checkEmptyTextFields(txtID.getText(), txtFirstName.getText(), txtLastName.getText(), txtMail.getText());
            //check if the text starts with space.
            InputTestsStaticClass.digitTest(txtID.getText(), 9);
            //check if the text starts with space.
            InputTestsStaticClass.spaceInStartTest(txtID.getText(), "Programmer Id Number");         
            InputTestsStaticClass.spaceInStartTest(txtFirstName.getText(), "First Name");
            InputTestsStaticClass.spaceInStartTest(txtLastName.getText(), "Last Name");
            InputTestsStaticClass.spaceInStartTest(txtMail.getText(), "Mail");           
            //check if the text contains spaces.           
            InputTestsStaticClass.spaceInStartTest(txtID.getText(), "Programmer Id Number");         
            InputTestsStaticClass.spaceInStartTest(txtFirstName.getText(), "First Name");
            InputTestsStaticClass.spaceInStartTest(txtLastName.getText(), "Last Name");
            InputTestsStaticClass.spaceInStartTest(txtMail.getText(), "Mail");           

            //check if the text contains letters.
            InputTestsStaticClass.lettersTest(txtID.getText(), "Programmer Id Number");     
            //check if the text contains numbers.
            InputTestsStaticClass.integerTest(txtFirstName.getText(), "First Name");
            InputTestsStaticClass.integerTest(txtLastName.getText(), "Last Name");

            String EMAIL_REGEX = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
            if(!txtMail.getText().matches(EMAIL_REGEX)){
                sound("/sounds/errorSound.wav");
                JOptionPane.showMessageDialog(null, "Invalid E-Mail Address!", "Invalid E-Mail", JOptionPane.PLAIN_MESSAGE, icon("/images/symbolError.png"));
                txtMail.selectAll();
            }else{
                ArrayList<String> Languages = null;
                if(IsExpertCheckBox.isSelected()){
                   if(LanguagesJList.getSelectedValuesList().size() > 0){
                        Languages = new ArrayList<>(LanguagesJList.getSelectedValuesList());
                        if(CRcontroller.addExpertProgrammer(txtID.getText(), txtFirstName.getText(), txtLastName.getText(), String.valueOf(txtPassWord.getPassword()), txtMail.getText(), fileName, Languages)){
                            sound("/sounds/okSound.wav");
                            JOptionPane.showMessageDialog(null, "Successfully added Programmer "+txtID.getText()+" to DB", "Added Message", JOptionPane.PLAIN_MESSAGE, icon("/images/succeedIcon.png"));
                            frame.dispose();
                        }else{
                            sound("/sounds/errorSound.wav");
                            JOptionPane.showMessageDialog(null, "Failed to Add Programmer "+txtID.getText()+" Please Try Again Later ", "Failed To Add", JOptionPane.PLAIN_MESSAGE, icon("/images/symbolError.png"));
                            frame.dispose();
                        }
                    }else{                   
                        sound("/sounds/errorSound.wav");
                        JOptionPane.showMessageDialog(null, "You must choose at least one Language! ", "Language Required", JOptionPane.PLAIN_MESSAGE, icon("/images/symbolError.png"));   
                    }  
             }else{
                if(CRcontroller.addProgrammer(txtID.getText(), txtFirstName.getText(), txtLastName.getText(), String.valueOf(txtPassWord.getPassword()), txtMail.getText(), fileName)){
                    sound("/sounds/okSound.wav");
                    JOptionPane.showMessageDialog(null, "Successfully added Programmer "+txtID.getText()+" to DB", "Added Message", JOptionPane.PLAIN_MESSAGE, icon("/images/succeedIcon.png"));
                    frame.dispose();
                }else{                   
                    sound("/sounds/errorSound.wav");
                    JOptionPane.showMessageDialog(null, "Failed to Add Programmer "+txtID.getText()+" Please Try Again Later ", "Failed To Add", JOptionPane.PLAIN_MESSAGE, icon("/images/symbolError.png"));   
                    frame.dispose();
                    }
                }
            }
       }catch (MissingDataException | SpaceStartException | LettersTestException | IntegersTestException | DigitException ex) {
            ex.getStackTrace();
       }catch (NullPointerException e){
            e.printStackTrace();                      
       }
    } //End Of addProgrammer  
    
    /**
     * 
     * @param cbID
     * @param frame 
     */
    public void removeProgrammer(JComboBox<?> cbID, JInternalFrame frame ){
        try{    
            //check if any of textFields are empty.           
            InputTestsStaticClass.checkEmptyTextFields(cbID.getSelectedItem().toString());                        
            if(CRcontroller.removeProgrammer(cbID.getSelectedItem().toString())){ 
                sound("/sounds/okSound.wav");
                JOptionPane.showMessageDialog(null, "Successfully removed Programmer "+cbID.getSelectedItem().toString()+" from DB", "Removed  Message", JOptionPane.PLAIN_MESSAGE, icon("/images/succeedIcon.png"));
                frame.dispose();
            }else{
                sound("/sounds/errorSound.wav");
                JOptionPane.showMessageDialog(null, "Failed to removed Programmer Please Try Again Later ", "Failed To Remove", JOptionPane.PLAIN_MESSAGE, icon("/images/symbolError.png"));    
                frame.dispose();
            }
       }catch (MissingDataException ex) {
            ex.getStackTrace();
       } catch (NullPointerException e){
            e.printStackTrace();                     
       }
    } //End Of removeProgrammer 

    /**
     * 
     * @param cbID
     * @param txtFirstName
     * @param txtLastName
     * @param txtMail
     * @param txtPassWord
     * @param IsExpertCheckBox
     * @param fileName
     * @param LanguagesJList
     * @param frame 
     */
    public void updateProgrammer(JComboBox<?> cbID, JTextField txtFirstName, JTextField txtLastName, JTextField txtMail, JPasswordField txtPassWord, JCheckBox  IsExpertCheckBox,
            String fileName, JList<String> LanguagesJList, JInternalFrame frame ){
        try{    
            //check if any of textFields are empty.           
            InputTestsStaticClass.checkEmptyTextFields(txtFirstName.getText(), txtLastName.getText(), txtMail.getText());
            //check if the text starts with space.         
            InputTestsStaticClass.spaceInStartTest(txtFirstName.getText(), "First Name");
            InputTestsStaticClass.spaceInStartTest(txtLastName.getText(), "Last Name");
            InputTestsStaticClass.spaceInStartTest(txtMail.getText(), "Mail");           
            //check if the text contains spaces.                    
            InputTestsStaticClass.spaceInStartTest(txtFirstName.getText(), "First Name");
            InputTestsStaticClass.spaceInStartTest(txtLastName.getText(), "Last Name");
            InputTestsStaticClass.spaceInStartTest(txtMail.getText(), "Mail");                
            //check if the text contains numbers.
            InputTestsStaticClass.integerTest(txtFirstName.getText(), "First Name");
            InputTestsStaticClass.integerTest(txtLastName.getText(), "Last Name");

            String EMAIL_REGEX = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
            if(!txtMail.getText().matches(EMAIL_REGEX)){
                sound("/sounds/errorSound.wav");
                JOptionPane.showMessageDialog(null, "Invalid E-Mail Address!", "Invalid E-Mail", JOptionPane.PLAIN_MESSAGE, icon("/images/symbolError.png"));
                txtMail.selectAll();
            }else{
                ArrayList<String> Languages = null;
                if(IsExpertCheckBox.isSelected()){
                    if(LanguagesJList.getSelectedValuesList().size() > 0){
                        Languages = new ArrayList<>(LanguagesJList.getSelectedValuesList());
                        if(CRcontroller.updateExpertProgrammer(cbID.getSelectedItem().toString(), txtFirstName.getText(), txtLastName.getText(), String.valueOf(txtPassWord.getPassword()), txtMail.getText(), fileName, Languages)){
                            sound("/sounds/okSound.wav");
                            JOptionPane.showMessageDialog(null, "Successfully Update Programmer "+cbID.getSelectedItem()+" in DB", "Update Message", JOptionPane.PLAIN_MESSAGE, icon("/images/succeedIcon.png"));
                            frame.dispose();
                        }else{
                            sound("/sounds/errorSound.wav");
                            JOptionPane.showMessageDialog(null, "Failed to Update Programmer Please Try Again Later ", "Failed To Update", JOptionPane.PLAIN_MESSAGE, icon("/images/symbolError.png"));    
                            frame.dispose();
                        }
                     }else{                   
                         sound("/sounds/errorSound.wav");
                         JOptionPane.showMessageDialog(null, "You must choose at least one Language! ", "Language Required", JOptionPane.PLAIN_MESSAGE, icon("/images/symbolError.png"));   
                        }  
               }else{
                     if(CRcontroller.updateProgrammer(cbID.getSelectedItem().toString(), txtFirstName.getText(), txtLastName.getText(), String.valueOf(txtPassWord.getPassword()), txtMail.getText(), fileName)){
                        sound("/sounds/okSound.wav");
                        JOptionPane.showMessageDialog(null, "Successfully Update Programmer "+cbID.getSelectedItem()+" in DB", "Update Message", JOptionPane.PLAIN_MESSAGE, icon("/images/succeedIcon.png"));
                        frame.dispose();
                    }else{
                        sound("/sounds/errorSound.wav");
                        JOptionPane.showMessageDialog(null, "Failed to Update Programmer Please Try Again Later ", "Failed To Update", JOptionPane.PLAIN_MESSAGE, icon("/images/symbolError.png"));    
                        frame.dispose();
                        }
                     }
                }
            } catch (MissingDataException | SpaceStartException | IntegersTestException ex) {
                ex.getStackTrace();
            } catch (NullPointerException e){
                e.printStackTrace();                    
            }
    }  //End Of updateProgrammer 

//*************************************************** Team *************************************************  
    
    /**
     * 
     * @param txtTeamName
     * @param programmer
     * @param fileName
     * @param frame 
     */
    public void addTeam(JTextField txtTeamName ,Programmer programmer ,String fileName ,JInternalFrame frame ){
        try{    
            //check if any of textFields are empty.           
            InputTestsStaticClass.checkEmptyTextFields(txtTeamName.getText());
            //check if the text starts with space.
            InputTestsStaticClass.spaceInStartTest(txtTeamName.getText(), "Team Name");         
            //check if the text contains spaces.           
            InputTestsStaticClass.spaceInStartTest(txtTeamName.getText(), "Team Name");             
            //check if the text contains numbers.
            InputTestsStaticClass.integerTest(txtTeamName.getText(), "Team Name");


            if(CRcontroller.addTeam(0, txtTeamName.getText(), programmer, null, fileName)){
                sound("/sounds/okSound.wav");
                JOptionPane.showMessageDialog(null, "Successfully added Team "+txtTeamName.getText()+" to DB", "Added Message", JOptionPane.PLAIN_MESSAGE, icon("/images/succeedIcon.png"));
                frame.dispose();
            }else{                   
                sound("/sounds/errorSound.wav");
                JOptionPane.showMessageDialog(null, "Failed to Add Team "+txtTeamName.getText()+" Please Try Again Later ", "Failed To Add", JOptionPane.PLAIN_MESSAGE, icon("/images/symbolError.png"));   
                frame.dispose();
            }  
       } catch (MissingDataException | SpaceStartException | IntegersTestException ex) {
            ex.getStackTrace();
       } catch (NullPointerException e){
              e.printStackTrace();
                              
       }
    }//End Of addTeam 
    
    /**
     * 
     * @param cbTeamID
     * @param txtTeamName
     * @param frame 
     */
    public void removeTeam(JComboBox<?> cbTeamID, JTextField txtTeamName, JInternalFrame frame ){
        if(cbTeamID.getSelectedItem() != null){
            int teamId = Integer.parseInt(cbTeamID.getSelectedItem().toString());
            if(CRcontroller.removeTeam(teamId)){
                sound("/sounds/okSound.wav");
                JOptionPane.showMessageDialog(null, "Successfully removed Team "+txtTeamName.getText()+" from DB", "removed Message", JOptionPane.PLAIN_MESSAGE, icon("/images/succeedIcon.png"));
                frame.dispose();
            }else{
                sound("/sounds/errorSound.wav");
                JOptionPane.showMessageDialog(null, "Failed to removed Team "+txtTeamName.getText()+" Please Try Again Later ", "Failed To Remove", JOptionPane.PLAIN_MESSAGE, icon("/images/symbolError.png"));    
                frame.dispose();
            }
       }else{
            sound("/sounds/errorSound.wav");
            JOptionPane.showMessageDialog(null,  "Sorry you must choose team id", "Team Id Required", JOptionPane.PLAIN_MESSAGE, icon("/images/symbolError.png"));
             cbTeamID.requestFocusInWindow();
            }
    }//End Of removeTeam 
    
    /**
     * 
     * @param cbTeamID
     * @param txtTeamName
     * @param programmer
     * @param fileName
     * @param frame 
     */
    public void updateTeam(JComboBox<?> cbTeamID, JTextField txtTeamName ,Programmer programmer ,String fileName ,JInternalFrame frame ){
        try{    
            //check if any of textFields are empty.           
            InputTestsStaticClass.checkEmptyTextFields(txtTeamName.getText());
            //check if the text starts with space.
            InputTestsStaticClass.spaceInStartTest(txtTeamName.getText(), "Team Name");         
            //check if the text contains spaces.           
            InputTestsStaticClass.spaceInStartTest(txtTeamName.getText(), "Team Name");             
            //check if the text contains numbers.
            InputTestsStaticClass.integerTest(txtTeamName.getText(), "Team Name");

            if(CRcontroller.updateTeam(Integer.parseInt(cbTeamID.getSelectedItem().toString()), txtTeamName.getText(), programmer, null, fileName)){
                sound("/sounds/okSound.wav");
                JOptionPane.showMessageDialog(null, "Successfully Update Team "+txtTeamName.getText()+" in DB", "Update Message", JOptionPane.PLAIN_MESSAGE, icon("/images/succeedIcon.png"));
                frame.dispose();
            }else{
                sound("/sounds/errorSound.wav");
                JOptionPane.showMessageDialog(null, "Failed to Update Team "+txtTeamName.getText()+" Please Try Again Later ", "Failed To Update", JOptionPane.PLAIN_MESSAGE, icon("/images/symbolError.png"));    
                frame.dispose();
            }
       } catch (MissingDataException | SpaceStartException | IntegersTestException ex) {
            ex.getStackTrace();
       } catch (NullPointerException e){
              e.printStackTrace();
                              
       }
    }//End Of updateTeam 

//*************************************************** CodeFile ************************************************* 
    
    /**
     * 
     * @param editFile
     * @param textDescription
     * @param language
     * @param frame 
     */
    public void addCodeFile(File editFile, String textDescription, String language, JInternalFrame frame ){ 
        if(isExpert){
            if(CRcontroller.addCodeFile(0, editFile.getName(), logEuser, new Date(), textDescription, editFile, language)){
                UpdateScore(E_ScoreManager.ASK_REVIEW);
                 sound("/sounds/okSound.wav");
                JOptionPane.showMessageDialog(null, "Successfully added Code File to DB", "Added Message", JOptionPane.PLAIN_MESSAGE, icon("/images/succeedIcon.png"));
                frame.dispose();
           }else{
                sound("/sounds/errorSound.wav");
                JOptionPane.showMessageDialog(null, "Failed to Add Code File Please Try Again Later ", "Failed To Add", JOptionPane.PLAIN_MESSAGE, icon("/images/symbolError.png"));
                frame.dispose();
            }
        }else{
             if(CRcontroller.addCodeFile(0, editFile.getName(), logUser, new Date(), textDescription, editFile, language)){
                UpdateScore(E_ScoreManager.ASK_REVIEW);
                sound("/sounds/okSound.wav");
                JOptionPane.showMessageDialog(null, "Successfully added Code File to DB", "Added Message", JOptionPane.PLAIN_MESSAGE, icon("/images/succeedIcon.png"));
                frame.dispose();
            }else{
                sound("/sounds/errorSound.wav");
                JOptionPane.showMessageDialog(null, "Failed to Add Code File Please Try Again Later ", "Failed To Add", JOptionPane.PLAIN_MESSAGE, icon("/images/symbolError.png"));
                frame.dispose();
            }    
       
        }
    }  //End Of addCodeFile 

    //*************************************************** CodeReview ************************************************* 
    
    /**
     * 
     * @param ID
     * @param codeFile
     * @param date
     * @param ratring
     * @param bugs
     * @param suggestions
     * @param IsShareCheckBox
     * @param frame 
     */
    public void addCodeReview(int ID, CodeFile codeFile, Date date , int ratring, ArrayList <Bug> bugs, JTextArea suggestions, JCheckBox  IsShareCheckBox, JFrame frame ){
        if(CRcontroller.addCodeReview(ID, codeFile, logEuser, ratring, bugs, date, suggestions.getText(), IsShareCheckBox.isSelected())){
            if(bugs.size() > 0){
                UpdateScoreWithBugs(E_ScoreManager.SEND_REVIEW, bugs.size());
            }else{
                UpdateScore(E_ScoreManager.SEND_REVIEW);
            }
            if(IsShareCheckBox.isSelected()){
                UpdateScore(E_ScoreManager.SHARE_REVIEW);
            }
            sound("/sounds/okSound.wav");
            JOptionPane.showMessageDialog(null, "Successfully added Code Review to DB", "Added Message", JOptionPane.PLAIN_MESSAGE, icon("/images/succeedIcon.png"));
            frame.dispose();
        }else{
            sound("/sounds/errorSound.wav");
            JOptionPane.showMessageDialog(null, "Failed to Add Code Review Please Try Again Later ", "Failed To Add", JOptionPane.PLAIN_MESSAGE, icon("/images/symbolError.png"));
            frame.dispose();
        } 
    } //End Of addCodeReview 
    
    /**
     * 
     * @param programmer
     * @param teamID 
     */ 
    public void ChangeTeamMember(Programmer programmer,int teamID) {
           CRcontroller.ChangeTeamMember(programmer, teamID);
    }//End Of ChangeTeamMember 
    
  //*****************************************Get Methods *************************************************
    
    /**
     * get all the existing Languages
     * @return ArrayList Languages
     */
    public ArrayList<String> getAllLanguages(){
        return CRcontroller.getAllLanguages();
    }
    
    /**
     * get all the existing Programmers
     * @return ArrayList Programmer
     */
    public ArrayList<Programmer> getAllProgrammers(){
        return CRcontroller.getAllProgrammers();
    }
    
    /**
     * get specified Programmer
     * @param id
     * @return Programmer
     */
    public Programmer getAProgrammerById(String id){
        return CRcontroller.getAProgrammerById(id);
    }
    
    /**
     * get all the existing Expert Programmers
     * @return ArrayList Languages
     */    
    public ArrayList<ExpertProgrammer> getAllExpertProgrammers(){
        return CRcontroller.getAllExpertProgrammers();
    }
    
    /**
     * get all the existing Teams
     * @return ArrayList Team
     */     
    public ArrayList<Team> getAllTeams(){
        return CRcontroller.getAllTeams();
    }
    
    /**
     * get specified Team
     * @param teamId
     * @return Team
     */ 
    public Team getTeamById(int teamId){
        return CRcontroller.getTeamById(teamId);
    }
    
    /**
     * get all the existing Code Reviews
     * @return ArrayList Code Reviews
     */
    public ArrayList<CodeReview> getAllCodeReviews(){
        return CRcontroller.getAllCodeReviews();
    }
    
    /**
     * get all the incoming Code Reviews
     * @return ArrayList Code Reviews
     */
    public ArrayList<CodeFile> getAllUpcomingReviews(){
        return CRcontroller.getAllUpcomingReviews(logEuser);
    }
    
    /**
     * get all the completed Code Reviews
     * @return ArrayList Code Reviews
     */
    public ArrayList<CodeReview> getAllCompletedReviews(){
        return CRcontroller.getAllCompletedReviews(logEuser);
    }
    
    /**
     * get the specified Team's Contributed Teams 
     * @param team
     * @return ArrayList Team
     */
    public ArrayList<Team> getContributedTeams(Team team){
        return CRcontroller.getContributedTeams(team);
    }
    
    /**
     * get the specified programmer's Review results
     * @param programmer
     * @return ArrayList Code Reviews
     */    
    public ArrayList<CodeReview> getAllReturnedReviews(Programmer programmer){
        return CRcontroller.getAllReturnedReviews(programmer);
    }
    
    /**
     * get all the Reviews written by this Expert programmer
     * @param expertProgrammer
     * @return ArrayList Code Reviews
     */
    public ArrayList<CodeReview> getAllCompeletedReviews(ExpertProgrammer expertProgrammer){
        return CRcontroller.getAllCompeletedReviews(expertProgrammer);
    }
    
    /**
     * get all public Code Reviews
     * @return ArrayList Code Reviews
     */
    public ArrayList<CodeReview> getAllSharedReviews(){
        return CRcontroller.getAllSharedReviews();
    }
    
    /**
     * get the currently logged in Programmer 
     * @return Programmer
     */
    public static Programmer getLogUser() {
        return logUser;
    }
    
    /**
     * set the currently logged in Programmer 
     * @param logUser
     */
    public static void setLogUser(Programmer logUser) {
        isExpert = false;
        ViewLogic.logUser = logUser;
    }
    
    /**
     * get the currently logged in Expert Programmer 
     * @return Expert Programmer
     */
    public static ExpertProgrammer getLogEuser() {
        return logEuser;
    }
    
    /**
     * set the currently logged in Expert Programmer 
     * @param logEuser
     */
    public static void setLogEuser(ExpertProgrammer logEuser) {
        isExpert = true;
        ViewLogic.logEuser = logEuser;
    }
    
    /**
     * get whether the currently logged is Expert Programmer 
     * @return True/False
     */
    public static Boolean getIsExpert() {
        return isExpert;
    }
    
    /**
     * get the currently logged in Programmer's Team 
     * @return Team
     */
    public static Team getUserTeam() {
        return userTeam;
    }
    
    /**
     * sets the currently logged in Programmer's Team 
     * @param userTeam
     */
    public static void setUserTeam(Team userTeam) {
        ViewLogic.userTeam = userTeam;
    }
    
//***************************************** Other Utils Methods ************************************************* 
    
    public void UpdateScore(E_ScoreManager scoreManager){
        if(isExpert){
            if(CRcontroller.UpdateScore(logEuser, userTeam, scoreManager)){
                logEuser.UpdateScore(scoreManager.getProgrammerScore());
                if(userTeam!=null)
                {
                userTeam.UpdateScore(scoreManager.getTeamScore());
                }
                
                }
        }else{
            if(CRcontroller.UpdateScore(logUser, userTeam, scoreManager)){
                logUser.UpdateScore(scoreManager.getProgrammerScore());
                if(userTeam!=null){
                userTeam.UpdateScore(scoreManager.getTeamScore());
                }
                
                }
        }
    }
    
    
    
     public void UpdateScore(ExpertProgrammer p,E_ScoreManager scoreManager){
       
            Team t=CRcontroller.getTeamById(p.getTeam());
            if(CRcontroller.UpdateScore(p, t, scoreManager)){
               
                if(t!=null)
                {
                t.UpdateScore(scoreManager.getTeamScore());
                }
               
                }
        
        }
    
    
    public void UpdateScoreWithBugs(E_ScoreManager scoreManager, int num){
        if(isExpert){
            if(CRcontroller.UpdateScoreWithBugs(logEuser, userTeam, scoreManager, num)){
                logEuser.UpdateScore(scoreManager.getProgrammerScore());
                 if(userTeam!=null)
                {
                userTeam.UpdateScore(scoreManager.getTeamScore());
                }
               
                }
        }
    }
    
    /**
     * the method gets address of icon file and return the icon for display.
     * @param iconAddress
     * @return the icon
     */
    public Icon icon(String iconAddress) {          ///////// icon method
        Icon succeedIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(iconAddress)));
        return succeedIcon;
    }
        /**
     * the method gets address of sound file and return the sound for play.
     * @param soundAddress
     * @return sound
     */
    public SoundClass sound(String soundAddress) {          ///////// sound method
        SoundClass sound = new SoundClass(soundAddress);
        return sound;
    }
    /**
     * the methods clear all fields that she get
     * using ellipsis
     * @param text 
     */
    public void clearFields(JTextField... text) {            // ellipsis method to clear fields
        for (JTextField t : text) {
            t.setText("");
        }
    }
    
   public void setImag(JLabel label, String path ) {            
        try {
        File f = new File(path);
        BufferedImage img= ImageIO.read(f);
        int height;
        int width;
        if(label.getWidth()==0&&label.getHeight()==0)
        {
        	height=160;
        	width=160;
        }
        else
        {
        	height=label.getHeight();
        	width=label.getWidth();
        }
        BufferedImage imd=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g=imd.createGraphics();
        g.drawImage(img,0,0,width,height,null);
        label.setIcon(new ImageIcon(imd));
        } catch (IOException ex) {
            Logger.getLogger(ViewLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
