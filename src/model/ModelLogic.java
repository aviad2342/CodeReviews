package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import controller.CodeReviewController;
import controller.ConnectionToDB;
import java.text.ParseException;
import utils.E_ScoreManager;


public class ModelLogic implements I_ModelLogic {
	
	//***************************************** Variables *********************************************

    /**
     * Singleton instance of this class, loaded on the first execution of ModelLogic.getInstance()
     */
    private static ModelLogic instance;

    //***************************************** Constructors ******************************************

    /**
     * Full C'tor, for singleton support.
     */
    private ModelLogic() {
    }
  //***************************************** Methods ***********************************************

    /**
     * The method creates this class's instance & provides access to it, by returning a reference (singleton).
     * @return reference to this class's only instance, or null if reference was already returned (singleton).
     */
    public static ModelLogic getInstance() {
            if (instance == null) {
                instance = new ModelLogic();
                return instance;
            }
        return instance;
    }
    
  //-----------------------------------------Getters--------------------------------------
    public ArrayList<String> getAllLanguages()
    {
        try {
            ArrayList<String> lang = new ArrayList<String>();
            Connection conn= ConnectionToDB.getConect();     
            Statement stmt = conn.createStatement();
            String sql;  
            sql ="SELECT * FROM `tblLanguages`";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                lang.add(rs.getString("langName"));
            }
          return lang;
      } catch (SQLException ex) {
            Logger.getLogger(CodeReviewController.class.getName()).log(Level.SEVERE, null, ex);
        }   
         return null;
    }
    
    @Override
    public ArrayList<Programmer> getAllProgrammers(){
        try {
            ArrayList<Programmer> programmer = new ArrayList<Programmer>();
             Connection conn= ConnectionToDB.getConect();     
            Statement stmt = conn.createStatement();
            String sql;  
            sql ="SELECT * FROM `tblProgrammer` as t";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                sql ="SELECT * FROM `tblExpertProgrammer` as tx where tx.id='"+rs.getString("id")+"'";
                 Statement stmt1 = conn.createStatement();
                 ResultSet rs1 = stmt1.executeQuery(sql);
                 
                 if(rs1.next())
                 {
                      Statement stmt2 = conn.createStatement();
                      sql="SELECT `lang_name` FROM `tblLanguage_Expert` as le WHERE le.Expert_id='"+rs.getString("id")+"'";
                     ResultSet rs2 = stmt2.executeQuery(sql);
                     ArrayList<String> languages= new ArrayList<String>();
                     while(rs2.next())
                     {
                         languages.add(rs2.getString("lang_name"));
                     }
                   programmer.add(new ExpertProgrammer(rs.getString("id"), rs.getString("firstName"), rs.getString("lastname"), rs.getInt("score"),rs.getString("password"), rs.getString("email"), getPicFromData(rs.getBytes("icon"),rs.getString("id")), rs.getInt("Team_ID"),languages));  
                 }
                 else
                 {
                programmer.add(new Programmer(rs.getString("id"), rs.getString("firstName"), rs.getString("lastname"), rs.getInt("score"),rs.getString("password"), rs.getString("email"), getPicFromData(rs.getBytes("icon"),rs.getString("id")), rs.getInt("Team_ID")));
                 }
            }
          return programmer;
      } catch (SQLException ex) {
            Logger.getLogger(CodeReviewController.class.getName()).log(Level.SEVERE, null, ex);
        }  
         return null;   
    }
            
    public Programmer getAProgrammerById(String id){
        try {
            Programmer programmer = null;
            Connection conn= ConnectionToDB.getConect();     
            Statement stmt = conn.createStatement();
            String sql;  
            sql ="SELECT * FROM `tblProgrammer` as t WHERE t.id='"+id+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next())
            {
                sql ="SELECT * FROM `tblExpertProgrammer` as tx where tx.id='"+rs.getString("id")+"'";
                Statement stmt1 = conn.createStatement();
                ResultSet rs1 = stmt1.executeQuery(sql);
                 
                 if(rs1.next())
                 {
                Statement stmt2 = conn.createStatement();
                sql="SELECT `lang_name` FROM `tblLanguage_Expert` as le WHERE le.Expert_id='"+rs.getString("id")+"'";
                ResultSet rs2 = stmt2.executeQuery(sql);
                ArrayList<String> languages= new ArrayList<String>();
                while(rs2.next())
                     {
                         languages.add(rs2.getString("lang_name"));
                     }
                   programmer = new ExpertProgrammer(rs.getString("id"), rs.getString("firstName"), rs.getString("lastname"), rs.getInt("score"),rs.getString("password"), rs.getString("email"), getPicFromData(rs.getBytes("icon"),rs.getString("id")), rs.getInt("Team_ID"),languages);  
                 }
                 else
                 {
                programmer = new Programmer(rs.getString("id"), rs.getString("firstName"), rs.getString("lastname"), rs.getInt("score"),rs.getString("password"), rs.getString("email"), getPicFromData(rs.getBytes("icon"),rs.getString("id")), rs.getInt("Team_ID"));
                 }
               
               return programmer;
            } 
      } catch (SQLException ex) {
            Logger.getLogger(CodeReviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return null;   
    }
        
    @Override
    public ArrayList<ExpertProgrammer> getAllExpertProgrammers(){
        try {
            ArrayList<ExpertProgrammer> expertProgrammer = new ArrayList<ExpertProgrammer>();
            Connection conn= ConnectionToDB.getConect();     
            Statement stmt = conn.createStatement();
            String sql;  
            sql ="SELECT * FROM `tblLanguages`";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                expertProgrammer.add(new ExpertProgrammer(sql, sql, sql, sql, sql, sql,getAllLanguages()));
            }
          return expertProgrammer;
      } catch (SQLException ex) {
            Logger.getLogger(CodeReviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;      
    }
         
  @Override
    public ArrayList<Team> getAllTeams(){
        try {
            ArrayList<Team> team = new ArrayList<Team>();
            Connection conn= ConnectionToDB.getConect();     
            Statement stmt = conn.createStatement();
            String sql;  
            
            sql ="SELECT * FROM tblTeam";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                  ArrayList<Programmer> mates= new ArrayList<Programmer>();
                Statement stmt1 = conn.createStatement();
                sql="SELECT t.id FROM tblProgrammer as t where t.Team_ID="+rs.getInt("ID")+" and t.id !='"+rs.getString("leader")+"'";
                ResultSet rs1 = stmt1.executeQuery(sql);
                while(rs1.next())
                {
                    mates.add(getAProgrammerById(rs1.getString("id")));
                }
                team.add(new Team(rs.getInt("ID"),rs.getString("team_name"),getAProgrammerById(rs.getString("leader")),mates,getPicFromData(rs.getBytes("icon"),rs.getString("id")+"t"),rs.getInt("score") ));
            }
          return team;
      } catch (SQLException ex) {
            Logger.getLogger(CodeReviewController.class.getName()).log(Level.SEVERE, null, ex);
        }      
         return null;     
    }
    
    public Team getTeamById(int teamId){
        try {
            Team team = null;
            Connection conn= ConnectionToDB.getConect();     
            Statement stmt = conn.createStatement();
            String sql;  
            
            sql ="SELECT * FROM tblTeam as t where t.ID="+teamId;
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next())
            { 
                ArrayList<Programmer> mates= new ArrayList<Programmer>();
                Statement stmt1 = conn.createStatement();
                sql="SELECT t.id FROM tblProgrammer as t where t.Team_ID="+rs.getInt("ID")+" and t.id !='"+rs.getString("leader")+"'";
                ResultSet rs1 = stmt1.executeQuery(sql);
                while(rs1.next())
                {
                    mates.add(getAProgrammerById(rs1.getString("id")));
                }
                team = new Team(rs.getInt("ID"),rs.getString("team_name"),getAProgrammerById(rs.getString("leader")),mates,getPicFromData(rs.getBytes("icon"),rs.getString("id")+"t"),rs.getInt("score") );
            }
          return team;
      } catch (SQLException ex) {
            Logger.getLogger(CodeReviewController.class.getName()).log(Level.SEVERE, null, ex);
        }       
         return null;     
    }
    
    public ArrayList<CodeReview> getAllSharedReviews()
    {      
    	try {     
            Connection conn= ConnectionToDB.getConect();
            Statement stmt = conn.createStatement();
            String sql;
            SimpleDateFormat smFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ArrayList<CodeReview> reviews= new ArrayList<CodeReview>();
            sql ="SELECT c.*,r.* FROM tblCodeFile as c inner join tblReviewForCode as r on c.serialNumber=r.serialNumberCode where r.shared=1";
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
               Programmer p=(getAProgrammerById(rs.getString("prog_ID")));
               ExpertProgrammer ex=(ExpertProgrammer)(getAProgrammerById(rs.getString("revie_ID")));
               
                CodeFile c=new CodeFile(rs.getInt("serialNumber") , rs.getString("fileName"),p,smFormat.parse(rs.getString("createdDate")), rs.getString("functionNalityDescription"),new File(getFileFromData(rs.getBytes("content"),rs.getString("fileName"),((rs.getString("lang_name").equals("javaScript"))?"js":rs.getString("lang_name")))),rs.getString("lang_name"));  
                 Statement stmt2 = conn.createStatement();
                      sql="SELECT *  FROM tblBugs as b WHERE b.serialCode ="+rs.getInt("serialNumber")+" and b.dateCodeBug ='"+rs.getString("createdDate")+"'and b.revie_ID= '"+rs.getString("revie_ID")+"'";
                     ResultSet rs2 = stmt2.executeQuery(sql);
                     ArrayList<Bug> bugs= new ArrayList<Bug>();
                     while(rs2.next())
                     {
                    	 bugs.add(new Bug(rs2.getInt("bugID"),rs2.getString("bugDescription")));
                     }
                     CodeReview r=new CodeReview(rs.getInt("serialNumber"), c, ex, rs.getInt("codeRating"),bugs, smFormat.parse(rs.getString("createdDate")), rs.getString("tips"), ((rs.getInt("shared")==1)?true:false));
                     reviews.add(r);
            }
            return reviews;
        } catch (SQLException ex1) {
            
            Logger.getLogger(ModelLogic.class.getName()).log(Level.SEVERE, null, ex1);
            return null;
        } catch (ParseException e) {
	    e.printStackTrace();
	     return null;
	}
    }
       
    public ArrayList<CodeReview> getAllCompeletedReviews(ExpertProgrammer ex)
    {        
    	try {     
            Connection conn= ConnectionToDB.getConect();
            Statement stmt = conn.createStatement();
            String sql;
            SimpleDateFormat smFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ArrayList<CodeReview> reviews= new ArrayList<CodeReview>();
            sql ="SELECT c.*,r.* FROM tblCodeFile as c inner join tblReviewForCode as r on c.serialNumber=r.serialNumberCode where r.revie_ID='"+ex.getId()+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
               Programmer p=(getAProgrammerById(rs.getString("prog_ID")));
 
                CodeFile c=new CodeFile(rs.getInt("serialNumber") , rs.getString("fileName"),p,smFormat.parse(rs.getString("createdDate")), rs.getString("functionNalityDescription"),new File(getFileFromData(rs.getBytes("content"),rs.getString("fileName"),((rs.getString("lang_name").equals("javaScript"))?"js":rs.getString("lang_name")))),rs.getString("lang_name"));  
                 Statement stmt2 = conn.createStatement();
                      sql="SELECT *  FROM tblBugs as b WHERE b.serialCode ="+rs.getInt("serialNumber")+" and b.dateCodeBug ='"+rs.getString("createdDate")+"'and b.revie_ID= '"+rs.getString("revie_ID")+"'";
                     ResultSet rs2 = stmt2.executeQuery(sql);
                     ArrayList<Bug> bugs= new ArrayList<Bug>();
                     while(rs2.next())
                     {
                    	 bugs.add(new Bug(rs2.getInt("bugID"),rs2.getString("bugDescription")));
                     }
                     CodeReview r=new CodeReview(rs.getInt("serialNumber"), c, ex, rs.getInt("codeRating"),bugs, smFormat.parse(rs.getString("createdDate")), rs.getString("tips"), ((rs.getInt("shared")==1)?true:false));
                     reviews.add(r);
            }
            return reviews;
        } catch (SQLException ex1) {
            Logger.getLogger(ModelLogic.class.getName()).log(Level.SEVERE, null, ex1);
            return null;
        } catch (ParseException e) {
		e.printStackTrace();
		 return null;
	}
    }
      
      public ArrayList<CodeReview> getAllReturnedReviews(Programmer ex)
    {  
        try {     
            Connection conn= ConnectionToDB.getConect();
            Statement stmt = conn.createStatement();
            String sql;
            SimpleDateFormat smFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ArrayList<CodeReview> reviews= new ArrayList<CodeReview>();
            sql ="SELECT c.*,r.* FROM tblCodeFile as c inner join tblReviewForCode as r on c.serialNumber=r.serialNumberCode where c.prog_ID='"+ex.getId()+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
               Programmer p=(getAProgrammerById(rs.getString("revie_ID")));
                CodeFile c = new CodeFile(rs.getInt("serialNumber") , rs.getString("fileName"),ex,smFormat.parse(rs.getString("createdDate")), rs.getString("functionNalityDescription"),new File(getFileFromData(rs.getBytes("content"),rs.getString("fileName"),((rs.getString("lang_name").equals("javaScript"))?"js":rs.getString("lang_name")))),rs.getString("lang_name"));  
                 Statement stmt2 = conn.createStatement();
                     sql="SELECT *  FROM tblBugs as b WHERE b.serialCode ="+rs.getInt("serialNumber")+" and b.dateCodeBug ='"+rs.getString("createdDate")+"'and b.revie_ID= '"+rs.getString("revie_ID")+"'";
                     ResultSet rs2 = stmt2.executeQuery(sql);
                     ArrayList<Bug> bugs= new ArrayList<Bug>();
                     while(rs2.next())
                     {
                    	 bugs.add(new Bug(rs2.getInt("bugID"),rs2.getString("bugDescription")));
                     }
                     CodeReview r=new CodeReview(rs.getInt("serialNumber"), c, (ExpertProgrammer)p, rs.getInt("codeRating"),bugs, smFormat.parse(rs.getString("createdDate")), rs.getString("tips"), ((rs.getInt("shared")==1)?true:false));
                     reviews.add(r);
            }
            return reviews;
        } catch (SQLException ex1) {
            
            Logger.getLogger(ModelLogic.class.getName()).log(Level.SEVERE, null, ex1);
            return null;
        } catch (ParseException e) {
		e.printStackTrace();
		return null;
	}
    }
    
      
    public ArrayList<CodeFile> getAllUpcomingReviews(ExpertProgrammer ex)
    {
        
        try {     
            Connection conn= ConnectionToDB.getConect();
            Statement stmt = conn.createStatement();
            String sql;
            SimpleDateFormat smFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ArrayList<CodeFile> codes= new ArrayList<CodeFile>();
            sql ="SELECT c.* FROM tblCodeFile as c left outer join tblReviewForCode as r on c.serialNumber=r.serialNumberCode where c.revie_ID='"+ex.getId()+"' and r.serialNumberCode is null";
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
               Programmer p=(getAProgrammerById(rs.getString("prog_ID")));
                codes.add(new CodeFile(rs.getInt("serialNumber") , rs.getString("fileName"),p,smFormat.parse(rs.getString("createdDate")), rs.getString("functionNalityDescription"),new File(getFileFromData(rs.getBytes("content"),rs.getString("fileName"),((rs.getString("lang_name").equals("javaScript")))?"js":rs.getString("lang_name"))),rs.getString("lang_name")));  
            }
            return codes;
        } catch (SQLException ex2) {  
            Logger.getLogger(ModelLogic.class.getName()).log(Level.SEVERE, null, ex2);
        } catch (ParseException ex1) {
            Logger.getLogger(ModelLogic.class.getName()).log(Level.SEVERE, null, ex1);
        }
        return null;
    }
       
    @Override
    public ArrayList<CodeReview> getAllCodeReviews(){
        try {
            ArrayList<CodeReview> codeReview = new ArrayList<CodeReview>();
            Connection conn= ConnectionToDB.getConect();     
            Statement stmt = conn.createStatement();
            String sql;  
            sql ="SELECT * FROM `tblLanguages`";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                codeReview.add(new CodeReview(33));
            }
          return codeReview;
      } catch (SQLException ex) {
            Logger.getLogger(CodeReviewController.class.getName()).log(Level.SEVERE, null, ex);
        }       
         return null;
            
    }
       
    public boolean ChangeTeamMember(Programmer p,int teamID) {
		if(p != null){  
                  String pID=p.getId();
                  Connection conn= controller.ConnectionToDB.getConect();
                 
             try {
            	 Statement stmt1 = conn.createStatement();
                 String sql="Update tblProgrammer Set Team_ID='"+teamID+"' where ID="+pID;
                 stmt1.executeUpdate(sql);
                
           }catch (SQLException e)
           {
            return false;
           }
              finally{
                }   
                 return true;
                }
              return false;
	}
 //********************************** Layer's closure procedure (implementation) methods ****************************
	
	//***************************************** Add Methods ******************************************
	
	/**
	 * this method add new Code File object to database IIF the Code File not already
	 * exists and the details are valid.
	 * 
	 * @param codeFile 
	 * @return true if the Code File added successfully, false otherwise
	 */
	@Override
	public boolean addCodeFile(CodeFile codeFile) {
            
	if(codeFile != null){  
                  Connection conn= ConnectionToDB.getConect();
                  SimpleDateFormat smFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                  String formatted=smFormat.format(codeFile.getDate());
                  String INSERT_prog = "insert into tblCodeFile(createdDate,fileName,functionNalityDescription,content,prog_ID,prog_fullName,revie_ID,Revie_FullName,lang_name)"
                          + "values (?, ?, ?, ?, ?, ?, ?, ?,?)";
           FileInputStream fis = null;
                
                  PreparedStatement ps = null;
             try {
                 conn.setAutoCommit(false);
                   File file =codeFile.getFileAttached();
                 fis = new FileInputStream(file);
                 ps = conn.prepareStatement(INSERT_prog);
                 ps.setString(1, formatted);
                 ps.setString(2, codeFile.getFileName());
                 ps.setString(3, codeFile.getFunctionalityDescription());
                 ps.setBinaryStream(4, fis, (int) file.length());
                 ps.setString(5, codeFile.getAuthor().getId());
                 ps.setString(6,  codeFile.getAuthor().getFirstName()+" "+codeFile.getAuthor().getLastName());
                  Statement stmt = conn.createStatement();
            String sql;
           sql= "SELECT lp.langI AS id, CONCAT( lp.f,  ' ', lp.l ) AS fullName, COUNT( c.revie_ID ) AS reviewsPending\n" +
	    		 "FROM (\n" +
	    		 "tblCodeFile AS c\n" +
	    		 "LEFT OUTER JOIN tblReviewForCode AS r ON r.revie_ID is null\n" +
	    		 ")\n" +
	    		 "RIGHT OUTER JOIN (\n" +
	    		 "SELECT tx.id AS langI, tx.firstName AS f, tx.lastName AS l\n" +
	    		 "FROM tblProgrammer AS tx\n" +
	    		 "INNER JOIN tblLanguage_Expert AS k ON tx.id = k.Expert_ID\n" +
	    		 "WHERE k.lang_name = '"+codeFile.getLanguage()+"'\n" +
	    		 ") AS lp ON c.revie_ID = lp.langI\n" +
	    		 "WHERE lp.langI!='"+codeFile.getAuthor().getId()+"' \n" +
	    		 "GROUP BY c.revie_ID\n" +
	    		 "ORDER BY reviewsPending ASC";
	     ResultSet rs=stmt.executeQuery(sql);
	     if(rs.next())
	     {
	          ps.setString(7, rs.getString("id"));
	            ps.setString(8, rs.getString("fullName"));
	     }
	     else
	     
	       { 
	           sql="SELECT patsy.langI as id,CONCAT( patsy.f,  ' ', patsy.l ) as allName\n" +
"from (\n" +
"SELECT tx.id AS langI, tx.firstName AS f, tx.lastName AS l\n" +
"FROM tblProgrammer AS tx\n" +
"INNER JOIN tblLanguage_Expert AS k ON tx.id = k.Expert_ID\n" +
"WHERE k.lang_name = 'java'\n" +
") as patsy \n" +
"WHERE  patsy.langI!='"+codeFile.getAuthor().getId()+"'";
                 rs=stmt.executeQuery(sql);
	     if(rs.next())
	     {
	          ps.setString(7, rs.getString("id"));
	            ps.setString(8, rs.getString("allName"));
	     }
             else
             {
                 return false;
             }
	       }
                 ps.setString(9,codeFile.getLanguage() );
                 ps.executeUpdate();
                 conn.commit();
           }catch (SQLException | FileNotFoundException e)
           {
            e.printStackTrace();
            return false;
           }
              finally{
                 try {
                   ps.close();
                   fis.close();
                }catch (SQLException | IOException ex) {
                    return false;
                }
                }   
                 return true;
                }
              return false;
	}
    
	/**
	 * this method add new Code Review object to database IIF the Code Review not already
	 * exists and the details are valid.
	 * 
	 * @param codeReview 
	 * @return true if the Code Review added successfully, false otherwise
	 */
	@Override
	public boolean addCodeReview(CodeReview codeReview) {
		if(codeReview != null){  
                    SimpleDateFormat smFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formatted=smFormat.format(codeReview.getDate());
                  Connection conn= controller.ConnectionToDB.getConect();
                  String INSERT_prog = "insert into tblReviewForCode(serialNumberCode, dateCode, codeRating,revie_ID, Revie_FullName,tips,shared)"
                          + "values (?, ?, ?, ?, ?, ?, ?)";
                 
                  PreparedStatement ps = null;
             try {
            	 conn.setAutoCommit(false);
                 
                 ps = conn.prepareStatement(INSERT_prog);
                 ps.setInt(1, codeReview.getFile().getSerialNumber());
                 ps.setString(2, formatted);
                 ps.setInt(3, codeReview.getCodeRating());
                 ps.setString(4, codeReview.getReviewer().getId());
                 ps.setString(5,  codeReview.getReviewer().getFirstName()+" " +codeReview.getReviewer().getLastName());
                 ps.setString(6, codeReview.getSuggestionTips());
                 ps.setInt(7, (codeReview.getStatus())?1:0);
                 ps.executeUpdate();
                 INSERT_prog = "insert into tblBugs(serialCode, dateCodeBug,revie_ID,bugID,bugDescription)"
                         + "values (?, ?, ?, ?, ?)";
                 for(int i=0;i<codeReview.getBugs().size();i++)
                 {
                     ps= conn.prepareStatement(INSERT_prog);
                     ps.setInt(1, codeReview.getFile().getSerialNumber());
                     ps.setString(2, formatted);
                     ps.setString(3, codeReview.getReviewer().getId());
                     ps.setInt(4,i+1);
                     ps.setString(5, codeReview.getBugs().get(i).getBugsDescription());
                     ps.executeUpdate();
                 }
                 
                 conn.commit();
           }catch (SQLException e)
           {
            e.printStackTrace();
            return false;
           }
              finally{
                 try {
                   ps.close();
                   
                }catch (SQLException ex) {
                    ex.printStackTrace();
                }
                }   
                 return true;
                }
              return false;
	}
	
	
	
	public boolean ChangeReview(CodeReview codeReview) {
		if(codeReview != null){  
                    SimpleDateFormat smFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formatted=smFormat.format(codeReview.getDate());
                  Connection conn= controller.ConnectionToDB.getConect();
                  SimpleDateFormat smRFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   	           String formattedR=smRFormat.format(codeReview.getDate());
             String Update_Rev = "update tblReviewForCode set codeRating=?, tips=?, shared=? where serialNumberCode=? and dateCode=? and revie_ID=?";
             FileInputStream fis = null;
             PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);
            
            ps = conn.prepareStatement(Update_Rev);
            ps.setInt(1, codeReview.getCodeRating());
            ps.setString(2, codeReview.getSuggestionTips());
            ps.setInt(3, ((codeReview.getStatus())?1:0));
            ps.setInt(4, codeReview.getReviewNumber());
            ps.setString(5,  formattedR);
            ps.setString(6,  codeReview.getReviewer().getId());
            ps.executeUpdate();
            Update_Rev="DELETE FROM tblBugs WHERE tblBugs.serialCode ="+codeReview.getReviewNumber()+" and dateCodeBug ='"+formattedR+"'and revie_ID= '"+codeReview.getReviewer().getId()+"'";
            Statement st=conn.createStatement();
            st.execute(Update_Rev);
            String INSERT_prog = "insert into tblBugs(serialCode, dateCodeBug,revie_ID,bugID,bugDescription)"
                    + "values (?, ?, ?, ?, ?)";
            for(int i=0;i<codeReview.getBugs().size();i++)
            {
            	ps= conn.prepareStatement(INSERT_prog);
           	 ps.setInt(1, codeReview.getFile().getSerialNumber());
                ps.setString(2, formatted);
                ps.setString(3, codeReview.getReviewer().getId());
                ps.setInt(4,i+1);
                ps.setString(5, codeReview.getBugs().get(i).getBugsDescription());
                ps.executeUpdate();
            }
            
            conn.commit();
           }catch (SQLException e)
           {
            return false;
           }
              finally{
                 try {
                   ps.close();
                   fis.close(); 
                }catch (SQLException | IOException ex) {
                    ex.printStackTrace();
                }
                }   
                 return true;
                }
              return false;
	}

	/**
	 * this method add new Programmer object to database IIF the Programmer not already
	 * exists and the details are valid.
	 * 
         * @param programmer
	 * @return true if the Programmer added successfully, false otherwise
	 */
	@Override
	public boolean addProgrammer(Programmer programmer) {
		if(programmer != null){  
                  Connection conn= ConnectionToDB.getConect();
                  String INSERT_prog = "insert into tblProgrammer(id, firstName, lastname,score , password,email,icon,Team_ID) values (?, ?, ?, ?, ?, ?, ?, ?)";
                  FileInputStream fis = null;
                  PreparedStatement ps = null;
             try {
                 conn.setAutoCommit(false);
                 File file = new File(programmer.getIcon());
                 fis = new FileInputStream(file);
                 ps = conn.prepareStatement(INSERT_prog);
                 ps.setString(1, programmer.getId());
                 ps.setString(2, programmer.getFirstName());
                 ps.setString(3, programmer.getLastName());
                 ps.setInt(4, programmer.getScore());
                 ps.setString(5, programmer.getPassword());
                 ps.setString(6, programmer.getEmail());
                 ps.setInt(8, 0);
                 ps.setBinaryStream(7, fis, (int) file.length());
                 ps.executeUpdate();
                 conn.commit();
           }catch (SQLException | FileNotFoundException e)    
           {
               e.printStackTrace();
            return false;
           }
              finally{
                 try {
                   ps.close();
                   fis.close();  
                }catch (SQLException | IOException ex) {
                   Logger.getLogger(Programmer.class.getName()).log(Level.SEVERE, null, ex);
                }
                }   
                 return true;
                }
              return false;
	}

	/**
	 * this method add new Expert Programmer object to database IIF the Expert Programmer not already
	 * exists and the details are valid.
	 * 
         * @param expertProgrammer
	 * @return true if the Expert Programmer added successfully, false otherwise
	 */
	@Override
	public boolean addExpertProgrammer(ExpertProgrammer expertProgrammer) {
	    if(expertProgrammer != null){  
                Connection conn= ConnectionToDB.getConect();
                String INSERT_prog = "insert into tblProgrammer(id, firstName, lastname,score, password,email,icon,Team_ID) values (?, ?, ?, ?, ?, ?, ?, ?)";
                FileInputStream fis = null;
                PreparedStatement ps = null;
            try {
                conn.setAutoCommit(false);
                File file = new File(expertProgrammer.getIcon());
                fis = new FileInputStream(file);
                ps = conn.prepareStatement(INSERT_prog);
                ps.setString(1, expertProgrammer.getId());
                ps.setString(2, expertProgrammer.getFirstName());
                ps.setString(3, expertProgrammer.getLastName());
                ps.setInt(4, 20);
                ps.setString(5, expertProgrammer.getPassword());
                ps.setString(6, expertProgrammer.getEmail());
                ps.setBinaryStream(7, fis, (int) file.length());
                ps.setInt(8, 0);
                ps.executeUpdate();
                conn.commit();
                ps.close();
                INSERT_prog="insert into tblExpertProgrammer(id) values (?)";
                ps = conn.prepareStatement(INSERT_prog);
                ps.setString(1, expertProgrammer.getId()+"");
                ps.executeUpdate();
                conn.commit();
                
            for(String l: expertProgrammer.getLanguages())
            {
                INSERT_prog="insert into tblLanguage_Expert(lang_name,Expert_id) values (?,?)";
                ps = conn.prepareStatement(INSERT_prog);
                ps.setString(1, l);
                ps.setString(2, expertProgrammer.getId()+"");
                ps.executeUpdate();
                conn.commit(); 
            }

            }catch (SQLException | FileNotFoundException e)
            {
                e.printStackTrace();
             return false;
            }
                finally{
                   try {
                        ps.close();
                        fis.close();
                       
                  }catch (SQLException | IOException ex) {
                        Logger.getLogger(Programmer.class.getName()).log(Level.SEVERE, null, ex); 
                    }   
                    }
                  return true;
            }
            return false;
	}

	/**
	 * this method add new Team object to database IIF the Team not already
	 * exists and the details are valid.
	 * 
	 * @param team 
	 * @return true if the Team added successfully, false otherwise
	 */
	@Override
	public boolean addTeam(Team team) {
	    if(team != null){  
                  Connection conn= ConnectionToDB.getConect();
                  String INSERT_prog = "insert into tblTeam(team_name,icon,leader,score) values (?,?,?,?)";
                  FileInputStream fis = null;
                  PreparedStatement ps = null;
             try {
                 
                 conn.setAutoCommit(false);
                 File file = new File(team.getTeamSymbol());
                 fis = new FileInputStream(file);
                 
                 ps = conn.prepareStatement(INSERT_prog);
                 
                 ps.setString(1, team.getTeamName());
                 ps.setBinaryStream(2, fis, (int) file.length());
                 ps.setString(3, team.getTeamLeader().getId());
                 ps.setInt(4, team.getScore());
               
                 ps.executeUpdate();
                  INSERT_prog ="SELECT t.ID FROM tblTeam as t ORDER BY t.ID DESC LIMIT 1";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(INSERT_prog);
                int id=0;
                if(rs.next())
                {
                    id=rs.getInt("ID");
                }
                 INSERT_prog="Update tblProgrammer Set Team_ID="+id+" where id="+team.getTeamLeader().getId();
                  stmt.executeUpdate(INSERT_prog);
                 
                 conn.commit();
           }catch (SQLException | FileNotFoundException e)
           {
               e.printStackTrace();
            return false;
           }
              finally{
                 try {
                   ps.close();
                   fis.close();  
                }catch (SQLException | IOException ex) {
                   Logger.getLogger(Programmer.class.getName()).log(Level.SEVERE, null, ex);
                }
                }   
                 return true;
                }
              return false;
	}

        public ArrayList<Team> getContributedTeams(Team t){
	try{
		 ArrayList<Team> team = new ArrayList<Team>();
         Connection conn= ConnectionToDB.getConect();     
         Statement stmt = conn.createStatement();
         String sql;  
         
         sql ="select t.*\n" +
        		 "from (tblTeam as t inner join tblProgrammer as p on t.ID=p.Team_ID) inner join\n" +
        		 "(select c.prog_ID as pID, c.revie_ID as rID\n" +
        		 "from tblReviewForCode as r inner join tblCodeFile as c on c.serialNumber=r.serialNumberCode\n" +
        		 ") as fin on fin.pID=p.id inner join (tblTeam as tmain inner join tblProgrammer as pmain on tmain.ID=pmain.Team_ID) on pmain.id=rID\n" +
        		 "where tmain.ID="+t.getTeamNumber();
         ResultSet rs = stmt.executeQuery(sql);
         while(rs.next())
         {
               ArrayList<Programmer> mates= new ArrayList<Programmer>();
             Statement stmt1 = conn.createStatement();
             sql="SELECT t.id FROM tblProgrammer as t where t.Team_ID="+rs.getInt("ID")+" and t.id !='"+rs.getString("leader")+"'";
             ResultSet rs1 = stmt1.executeQuery(sql);
             while(rs1.next())
             {
                 mates.add(getAProgrammerById(rs1.getString("id")));
             }
             team.add(new Team(rs.getInt("ID"),rs.getString("team_name"),getAProgrammerById(rs.getString("leader")),mates,getPicFromData(rs.getBytes("icon"),rs.getString("id")+"t"),rs.getInt("score") ));
         }
       return team;
   } catch (SQLException ex) {
         Logger.getLogger(CodeReviewController.class.getName()).log(Level.SEVERE, null, ex);
         
     }       
      return null;    
	}
	//***************************************** Remove Methods ******************************************
	
	/**
	 * this method remove Code File object from database IIF the Code File already exists.
	 * 
	 * @param serialNumber
	 * @return true if the Code File removed successfully, false otherwise
	 */
	@Override
	public boolean removeCodeFile(int serialNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
     * this method Code Review File object from database IIF the Code Review already exists.
	 *
     * @param reviewNumber
     * @return true if the Code Review removed successfully, false otherwise
     */
	@Override
	public boolean removeCodeReview(int reviewNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * this method remove Programmer object from database IIF the Programmer already exists.
	 *
	 * @param id
	 * @return true if the Programmer removed successfully, false otherwise
	 */
	@Override
	public boolean removeProgrammer(String id) {
          try {
            Connection t=ConnectionToDB.getConect();
            Statement stmt = t.createStatement();
            String sql;
            sql="select tx.id fromtblExpertProgrammer as tx where tx.id='"+id+"'";
            ResultSet rs=stmt.executeQuery(sql);
            if(rs.next())
            {
              sql ="DELETE FROM tblLanguage_Expert WHERE tblLanguage_Expert.Expert_id ='"+id+"'";
              stmt.execute(sql);
              sql ="DELETE FROM tblExpertProgrammer WHERE tblExpertProgrammer.id ='"+id+"'";
              stmt.execute(sql);
            }
            sql="SELECT t.ID FROM tblTeam as t WHERE t.leader='"+id+"'";
            rs=stmt.executeQuery(sql);
            if(rs.next())
            {
              int team_id=rs.getInt("ID");
              Statement stmt1 = t.createStatement();
              sql="SELECT p.id,p.score FROM tblProgrammer as p WHERE p.Team_ID="+team_id+"\n" + "order by p.score Desc";
              ResultSet rs1=stmt1.executeQuery(sql);
              if(rs1.next())
                {
                    String leader_id=rs1.getString("id");
                    Statement stmt2 = t.createStatement();
                    sql="Update tblTeam Set leader='"+leader_id+"' where ID="+team_id;
                    stmt2.executeUpdate(sql);
                }
              else
                {
                    Statement stmt2 = t.createStatement();
                    sql ="DELETE FROM tblTeam WHERE tblTeam.id ='"+team_id+"'" ;
                    stmt2.execute(sql);
                }
            }
            sql ="DELETE FROM tblProgrammer WHERE tblProgrammer.id ='"+id+"'";
            if(stmt.execute(sql)){
                return false;
            }
         return true;
        } catch (SQLException ex) {
            Logger.getLogger(ModelLogic.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
	}
	

	/**
	 * this method remove Expert Programmer object from database IIF the Expert Programmer already exists.
	 *
	 * @param id
	 * @return true if the Expert Programmer removed successfully, false otherwise
	 */
	@Override
	public boolean removeExpertProgrammer(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * this method remove Team object from database IIF the Team already exists.
	 *
	 * @param teamNumber
	 * @return true if the Team removed successfully, false otherwise
	 */
	@Override
	public boolean removeTeam(int teamNumber) {
	 try {
            Connection t=ConnectionToDB.getConect();
            
            Statement stmt = t.createStatement();
            String sql;         
            
            sql ="DELETE FROM tblTeam WHERE ID="+teamNumber;
            stmt.execute(sql);
              sql="Update tblProgrammer Set Team_ID=0 where Team_ID="+teamNumber;
              stmt.executeUpdate(sql);
           
         return true;
        } catch (SQLException ex) {
            Logger.getLogger(ModelLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
	}

	//***************************************** Update Methods ******************************************
	
	/**
	 * this method Update Code File object in database IIF the Code File already exists
	 * and the details are valid.
	 *
	 * @param codeFile 
	 * @return true if the Code File was successfully update, false otherwise
	 */
	@Override
	public boolean updateCodeFile(CodeFile codeFile) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
         * this method Update Code Review object in database IIF the Code Review already exists
	 * and the details are valid.
	 *
         * @param codeReview 
         * @return true if the Code Review was successfully update, false otherwise
         */
	@Override
	public boolean updateCodeReview(CodeReview codeReview) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * this method Update Programmer object in database IIF the Programmer already exists
	 * and the details are valid.
	 *
	 * @param programmer 
	 * @return true if the Programmer was successfully update, false otherwise
	 */
	@Override
        public boolean updateProgrammer(Programmer programmer) {
		if(programmer != null){  
                  Connection conn= controller.ConnectionToDB.getConect();
                   FileInputStream fis = null;
                  PreparedStatement ps = null;
                  try {
                  Statement stmt2= conn.createStatement();
                  String INSERT_prog = "update tblProgrammer set firstName=?, lastname=?, password=?,email=?,icon=? where id=?";
                  
             
                 conn.setAutoCommit(false);
                 File file = new File(programmer.getIcon());
                 fis = new FileInputStream(file);
                 ps = conn.prepareStatement(INSERT_prog);
               
                 ps.setString(1, programmer.getFirstName());
                 ps.setString(2, programmer.getLastName());
                
                 ps.setString(3, programmer.getPassword());
                 ps.setString(4, programmer.getEmail());
                 ps.setBinaryStream(5, fis, (int) file.length());
                 ps.setString(6,programmer.getId());
                 ps.executeUpdate();
                 conn.commit();
                 
                 if(programmer instanceof ExpertProgrammer)
                 {
                     String sql="SELECT ex.id FROM `tblExpertProgrammer` as ex WHERE ex.id='"+programmer.getId()+"'";
                     ResultSet rs2 = stmt2.executeQuery(sql);
                     if(!rs2.next())
                     {
                    	 INSERT_prog="insert into tblExpertProgrammer(id) values (?)";
                         ps = conn.prepareStatement(INSERT_prog);
                         ps.setString(1, programmer.getId()+"");
                         ps.executeUpdate();
                     }
                     sql="Delete from tblLanguage_Expert where tblLanguage_Expert.Expert_id='"+programmer.getId()+"'";
                     stmt2.executeUpdate(sql);
                     ExpertProgrammer ep=(ExpertProgrammer)programmer;
                     for(String l: ep.getLanguages())
                     {
                         INSERT_prog="insert into tblLanguage_Expert(lang_name,Expert_id) values (?,?)";
                         ps = conn.prepareStatement(INSERT_prog);
                         ps.setString(1, l);
                         ps.setString(2, ep.getId()+"");
                         ps.executeUpdate();
                         conn.commit(); 
                     }
                 }
                
           }catch (SQLException | FileNotFoundException e)    
           {
               e.printStackTrace();
            return false;
           }
              finally{
                 try {
                   ps.close();
                   fis.close();  
                }catch (SQLException | IOException ex) {
                   Logger.getLogger(Programmer.class.getName()).log(Level.SEVERE, null, ex);
                }
                }   
                 return true;
                }
              return false;
	}
	/**
	 * this method Update Expert Programmer object in database IIF the Expert Programmer already exists
	 * and the details are valid.
	 *
	 * @param expertProgrammer 
	 * @return true if the Expert Programmer was successfully update, false otherwise
	 */
	@Override
	public boolean updateExpertProgrammer(ExpertProgrammer expertProgrammer) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * this method Update Team object in database IIF the Team already exists
	 * and the details are valid.
	 *
	 * @param team 
	 * @return true if the Team was successfully update, false otherwise
	 */
	@Override
	public boolean updateTeam(Team team) {
	    if(team != null){  
                  Connection conn= controller.ConnectionToDB.getConect();
                  String INSERT_prog = "update tblTeam set team_name=?,icon=? where ID=?";
                  FileInputStream fis = null;
                  PreparedStatement ps = null;
             try {
                 conn.setAutoCommit(false);
                 File file = new File(team.getTeamSymbol());
                 fis = new FileInputStream(file);
                 ps = conn.prepareStatement(INSERT_prog);
                 
                 ps.setString(1, team.getTeamName());
                 ps.setBinaryStream(2, fis, (int) file.length());
               
                 ps.setInt(3, team.getTeamNumber());
                
                 ps.executeUpdate();
                 conn.commit();
           }catch (SQLException | FileNotFoundException e)
           {
               e.printStackTrace();
            return false;
           }
              finally{
                 try {
                   ps.close();
                   fis.close();  
                }catch (SQLException | IOException ex) {
                   Logger.getLogger(Programmer.class.getName()).log(Level.SEVERE, null, ex);
                }
                }   
                 return true;
                }
              return false;
	}
        
        public boolean UpdateScore(Programmer programmer, Team team , E_ScoreManager scoreManager){
            if(programmer != null  && scoreManager != null){  
                  String programmerId = programmer.getId();
                  int programmerScore = programmer.getScore();
                  programmerScore+=scoreManager.getProgrammerScore();
                  int teamId=0;
                  int teamScore=0;
                  if(team!=null){
                   teamId = team.getTeamNumber();
                   teamScore=team.getScore();
                  teamScore+=scoreManager.getTeamScore();
                  }
                  Connection conn = ConnectionToDB.getConect();
             try {
            	 Statement stmt = conn.createStatement();
                 Statement stmt1 = conn.createStatement();
                 String sql = "Update tblProgrammer as t Set t.score="+programmerScore  +" where t.id='"+programmerId+"';";
                int result= stmt.executeUpdate(sql);
                
                 if(team!=null&&scoreManager.getTeamScore() != 0){
                    sql = "Update tblTeam as s Set s.score="+teamScore +" where s.ID="+teamId+";";
                    stmt1.executeUpdate(sql);   
                 }
                 conn.commit();
           }catch (SQLException e){
            return false;
           }
            return true;
           }
         return false;    
        }
        
        public boolean UpdateScoreWithBugs(Programmer programmer, Team team , E_ScoreManager scoreManager, int num){
            if(programmer != null && team != null && scoreManager != null){  
                  String programmerId = programmer.getId();
                  int programmerScore = programmer.getScore()+ num;
                  int teamId = team.getTeamNumber();
                  Connection conn = ConnectionToDB.getConect();
             try {
            	 Statement stmt = conn.createStatement();
                 String sql = "Update tblProgrammer Set score='"+(programmerScore +scoreManager.getProgrammerScore()) +"' where ID='"+programmerId+"'";
                 stmt.executeUpdate(sql);
                 
                 if(scoreManager.getTeamScore() != 0){
                    sql = "Update tblTeam Set score='"+(team.getScore() + scoreManager.getTeamScore())+"' where ID="+teamId;
                    stmt.executeUpdate(sql);   
                 }
                 
                conn.commit();
                
           }catch (SQLException e){
            return false;
           }
            return true;
           }
         return false;    
        }
        //***************************************** Other Methods ******************************************
        
        private static String getPicFromData(byte[] fileBytes,String id) 
         {  
         OutputStream targetFile = null;
       try {
           (new File("C:\\ProjectData")).mkdir();
    	   File f=new File("C:\\ProjectData");
           String path=f.getAbsolutePath();
           path+="\\"+id+".JPG";
           targetFile = new FileOutputStream(path);
           targetFile.write(fileBytes);
           targetFile.close();
           return path;
       } catch (FileNotFoundException ex) {
           Logger.getLogger(ModelLogic.class.getName()).log(Level.SEVERE, null, ex);
            return null;
       } catch (IOException ex) {
          Logger.getLogger(ModelLogic.class.getName()).log(Level.SEVERE, null, ex);
           return null;
       } finally {
           try {
               targetFile.close();
           } catch (IOException ex) {
               Logger.getLogger(ModelLogic.class.getName()).log(Level.SEVERE, null, ex);
               return null;
           }
       }

         }
        
        private static String getFileFromData(byte[] fileBytes,String filename,String lang) 
         {
              
         OutputStream targetFile = null;
       try {
           
           (new File("C:\\ProjectData")).mkdir();
    	   File f=new File("C:\\ProjectData");
           String path=f.getAbsolutePath();
           
           path+="\\"+filename+"."+lang;
          
           targetFile = new FileOutputStream(path);
          
           targetFile.write(fileBytes);
          
           targetFile.close();
           return path;

       } catch (FileNotFoundException ex) {
           ex.printStackTrace();
            return null;
       } catch (IOException ex) {
          ex.printStackTrace();
           return null;
       } finally {
           try {
               targetFile.close();
           } catch (IOException ex) {
            ex.printStackTrace();
               return null;
           }
       }
      
    
         }

}
