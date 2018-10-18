package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import utils.Constants;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import model.CodeFile;
import model.CodeReview;
import utils.E_ScoreManager;



/**
 *
 * @author Aviad
 */
public class UserMain extends javax.swing.JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private ViewLogic view;
    
    private ArrayList<CodeFile> codeFiles;
    
    private ArrayList<CodeReview> codeReviews;
    
    private ArrayList<CodeReview> sharedReviews;
    
    private ArrayList<CodeReview> completedReviews;
    
    private static int score;
    
    /**
     * Creates new form UserMain
     * 
     */
    public UserMain() {
        view = ViewLogic.getInstance();
        initComponents();
        setLocation();
        this.setTitle("User Main"); //the title of the frame
        RefreshReturnedReviews();
        RefreshSharedReviews();
        if(ViewLogic.getIsExpert()){
            RefreshUpcomingReviews();
            codeFiles = view.getAllUpcomingReviews();
            RefreshCompletedReviews();
            score = ViewLogic.getLogEuser().getScore();
           lbScore.setText("Score: " + score);
           lbUserName.setText(ViewLogic.getLogEuser().getFirstName()+" "+ViewLogic.getLogEuser().getLastName());
            if(ViewLogic.getLogEuser().getTeam() != 0){
                ViewLogic.setUserTeam(view.getTeamById(ViewLogic.getLogEuser().getTeam()));            
            }else
            {   
            	btnTeamProfile.setEnabled(false);
            }
            view.setImag(this.userIcon, ViewLogic.getLogEuser().getIcon());
        if(ViewLogic.getLogEuser().getScore()<51){
          this.UserRank.setIcon(icon("/images/NoobTag.jpg"));
          lbTeamScoe1.setText("Rank: "+Constants.RANK1);
        }
        else if(ViewLogic.getLogEuser().getScore()<101){
         this.UserRank.setIcon(icon("/images/ExpTag.jpg"));
         lbTeamScoe1.setText("Rank: "+Constants.RANK2);
        }  
        else{
        this.UserRank.setIcon(icon("/images/MasterNoob.jpg"));
        lbTeamScoe1.setText("Rank: "+Constants.RANK3);
        }
        }
        else{
            score = ViewLogic.getLogUser().getScore();
            lbScore.setText("Score: " + score); 
            btnReviewCompleted.setVisible(false);
            btnRefresh.setVisible(false);
            btnReviewRequests.setVisible(false);
            lbUserName.setText(ViewLogic.getLogUser().getFirstName()+" "+ViewLogic.getLogUser().getLastName());
           if(ViewLogic.getLogUser().getTeam() != 0){
                ViewLogic.setUserTeam(view.getTeamById(ViewLogic.getLogUser().getTeam()));             
            }else
            {   
            	btnTeamProfile.setEnabled(false);
            }
            view.setImag(this.userIcon, ViewLogic.getLogUser().getIcon());
         if(ViewLogic.getLogUser().getScore()<51){
          this.UserRank.setIcon(icon("/images/NoobTag.jpg"));
          lbTeamScoe1.setText("Rank: "+Constants.RANK1);
        }
        else if(ViewLogic.getLogUser().getScore()<101){
            this.UserRank.setIcon(icon("/images/ExpTag.jpg"));
         lbTeamScoe1.setText("Rank: "+Constants.RANK2);
        }  
        else{
            this.UserRank.setIcon(icon("/images/MasterNoob.jpg"));
        lbTeamScoe1.setText("Rank: "+Constants.RANK3);
        }    
        }
        
    }
    
    public void RefreshOutside()
    {
    	 final JDialog frame=view.getAnimation(this);
    	 final JFrame curr=this;
         new Thread(new Runnable(){
         	
         @Override
         public void run(){
            //time consuming algorithm.
        	 enableComponents(curr,false);
         	 if(ViewLogic.getIsExpert())
         {
             RefreshUpcomingReviews();
             RefreshCompletedReviews();
         }
         RefreshReturnedReviews();
         RefreshSharedReviews();
            
              SwingUtilities.invokeLater(new Runnable(){
                  @Override public void run(){
                	  enableComponents(curr,true);
                 	 frame.dispose(); 
                 	 if(codeReviews.isEmpty())
                     {
                    	 btnReviewResults.setEnabled(false); 
                     }
                     if(completedReviews.isEmpty())
                     {
                    	 btnReviewCompleted.setEnabled(false);
                     }
                     if(sharedReviews.isEmpty())
                     {
                    	btnSharedReviews.setEnabled(false);
                     }
                    if(codeFiles.isEmpty())
                    {
                    	btnReviewRequests.setEnabled(false);
                    }
                    if(ViewLogic.getLogEuser().getTeam() == 0){
                        
                        	btnTeamProfile.setEnabled(false);
                        }
                 	
                 	
                }
               });
            
         }
         }).start();
         
    }

    public  void RefreshScore()
    {
        if(ViewLogic.getIsExpert())
        score = ViewLogic.getLogEuser().getScore();
        else
        {
            score=ViewLogic.getLogUser().getScore();
        }
       
    }
    private void setLocation(){
       setLocationRelativeTo(null);
    }
    private void RefreshUpcomingReviews(){
        try{
            codeFiles = view.getAllUpcomingReviews();
            if(!codeFiles.isEmpty()){
               this.btnReviewRequests.setEnabled(true);
               this.btnReviewRequests.setText("Review Requests ("+codeFiles.size()+")");
            }
            else
            {
            	 this.btnReviewRequests.setText("Review Requests (0)");
                 this.btnReviewRequests.setEnabled(false);
            }
        }catch(Exception e){
            this.btnReviewRequests.setText("Review Requests (0)");
            this.btnReviewRequests.setEnabled(false);
        }
    }
    
    private void RefreshCompletedReviews(){
        try{
            this.completedReviews = view.getAllCompletedReviews();
            if(!completedReviews.isEmpty()){
               this.btnReviewCompleted.setEnabled(true);
              
            }
            else
            {
                 this.btnReviewCompleted.setEnabled(false);
            }
        }catch(Exception e){
            
            this.btnReviewCompleted.setEnabled(false);
        }
    }
    private void RefreshReturnedReviews(){
        try{
            if(ViewLogic.getIsExpert())
            codeReviews = view.getAllReturnedReviews(ViewLogic.getLogEuser());
            else
                 codeReviews = view.getAllReturnedReviews(ViewLogic.getLogUser());
            if(!codeReviews.isEmpty()){
               this.btnReviewResults.setEnabled(true);
               this.btnReviewResults.setText("Review Results ("+codeReviews.size()+")");
            }
            else
            {
                 this.btnReviewResults.setText("Review Results (0)");
            this.btnReviewResults.setEnabled(false); 
            }
        }catch(Exception e){
            this.btnReviewResults.setText("Review Results (0)");
            this.btnReviewResults.setEnabled(false);
        }
    }
    private void RefreshSharedReviews(){
        try{
            sharedReviews = view.getAllSharedReviews();
            if(!sharedReviews.isEmpty()){
               this.btnSharedReviews.setEnabled(true);
            }
               
               else
               {
            	   this.btnSharedReviews.setEnabled(false);
               }
            
        }catch(Exception e){
            this.btnSharedReviews.setEnabled(false);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbUserName = new javax.swing.JLabel();
        lbScore = new javax.swing.JLabel();
        userIcon = new javax.swing.JLabel();
        btnSendToReview = new javax.swing.JButton();
        btnTeamProfile = new javax.swing.JButton();
        btnReviewResults = new javax.swing.JButton();
        btnReviewRequests = new javax.swing.JButton();
        btnSharedReviews = new javax.swing.JButton();
        lbTeamScoe1 = new javax.swing.JLabel();
        UserRank = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        btnReviewCompleted = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(403, 728));
        setPreferredSize(new java.awt.Dimension(403, 728));
        setResizable(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        getContentPane().setLayout(null);

        lbUserName.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lbUserName.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lbUserName);
        lbUserName.setBounds(10, 30, 220, 27);

        lbScore.setBackground(new java.awt.Color(255, 255, 255));
        lbScore.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lbScore.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lbScore);
        lbScore.setBounds(10, 70, 220, 30);

        userIcon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        getContentPane().add(userIcon);
        userIcon.setBounds(240, 10, 154, 154);
        userIcon.getAccessibleContext().setAccessibleName("userIcon");

        btnSendToReview.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnSendToReview.setText("Send Code To Review");
        btnSendToReview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendToReviewActionPerformed(evt);
            }
        });
        getContentPane().add(btnSendToReview);
        btnSendToReview.setBounds(72, 385, 263, 40);
        btnSendToReview.getAccessibleContext().setAccessibleName("btnSendToReview");

        btnTeamProfile.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnTeamProfile.setText("View Team Profile");
        btnTeamProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTeamProfileActionPerformed(evt);
            }
        });
        getContentPane().add(btnTeamProfile);
        btnTeamProfile.setBounds(72, 474, 263, 37);
        btnTeamProfile.getAccessibleContext().setAccessibleName("btnTeamProfile");

        btnReviewResults.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnReviewResults.setText("Review Results");
        btnReviewResults.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReviewResultsActionPerformed(evt);
            }
        });
        getContentPane().add(btnReviewResults);
        btnReviewResults.setBounds(72, 431, 263, 37);
        btnReviewResults.getAccessibleContext().setAccessibleName("btnReviewResults");

        btnReviewRequests.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnReviewRequests.setText("Review Requests");
        btnReviewRequests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReviewRequestsActionPerformed(evt);
            }
        });
        getContentPane().add(btnReviewRequests);
        btnReviewRequests.setBounds(116, 605, 219, 39);
        btnReviewRequests.getAccessibleContext().setAccessibleName("btnReviewRequests");

        btnSharedReviews.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnSharedReviews.setText("Shared Reviews");
        btnSharedReviews.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSharedReviewsActionPerformed(evt);
            }
        });
        getContentPane().add(btnSharedReviews);
        btnSharedReviews.setBounds(72, 517, 263, 37);
        btnSharedReviews.getAccessibleContext().setAccessibleName("btnSharedReviews");

        lbTeamScoe1.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lbTeamScoe1.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lbTeamScoe1);
        lbTeamScoe1.setBounds(10, 200, 260, 23);

        UserRank.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        getContentPane().add(UserRank);
        UserRank.setBounds(290, 200, 100, 90);

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refreshericon.png"))); // NOI18N
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        getContentPane().add(btnRefresh);
        btnRefresh.setBounds(71, 605, 39, 39);

        btnReviewCompleted.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnReviewCompleted.setText("Previous Reviews");
        btnReviewCompleted.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReviewCompletedActionPerformed(evt);
            }
        });
        getContentPane().add(btnReviewCompleted);
        btnReviewCompleted.setBounds(72, 560, 263, 39);

        jButton1.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/loggout.jpg"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, new java.awt.Color(0, 0, 0), java.awt.Color.gray, java.awt.Color.black));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(0, 650, 50, 50);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Grey-Background.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 400, 700);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendToReviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendToReviewActionPerformed
        JFileChooser ch= new JFileChooser();
            ch.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    }
                    final String name = f.getName();
                    return name.endsWith(".java") || name.endsWith(".c")|| name.endsWith(".cpp")|| name.endsWith(".js")|| name.endsWith(".ABAP")
                            || name.endsWith(".clojure")|| name.endsWith(".groovy")|| name.endsWith(".gvy")|| name.endsWith(".lua")|| name.endsWith(".python")|| name.endsWith(".ruby");
                }
                @Override
                public String getDescription() {
                    return "*.java,*.c,*.cpp,*.js,*.ABAP,*.clojure,*.groovy,*.gvy,*.lua,*.python,*.ruby";
                }//123mycodereview.com
            });
            ch.showOpenDialog(null);
            File file = ch.getSelectedFile();
            if(file!=null){
                
        frmCodeEditor codeEditor = new frmCodeEditor(this.view, this.getLocationOnScreen(),this.getWidth()+5, file,this);
        codeEditor.setVisible(true);
            }
    }//GEN-LAST:event_btnSendToReviewActionPerformed

    private void btnTeamProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTeamProfileActionPerformed
        IfrmTeamProfile teamProfile = new IfrmTeamProfile(this.view);
        teamProfile.setVisible(true);
    }//GEN-LAST:event_btnTeamProfileActionPerformed

    private void btnReviewRequestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReviewRequestsActionPerformed
        frmNewReview newReview = new frmNewReview(this.view,this, codeFiles);
        newReview.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnReviewRequestsActionPerformed

    private void btnSharedReviewsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSharedReviewsActionPerformed
        dlgReviews reviewsDialog = new dlgReviews(this, sharedReviews, false , this.view);
        reviewsDialog.setVisible(true);
    }//GEN-LAST:event_btnSharedReviewsActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        final JDialog frame=view.getAnimation(this);
    	 final JFrame curr=this;
         new Thread(new Runnable(){
         	
         @Override
         public void run(){
            //time consuming algorithm.
        	 enableComponents(curr,false);
         	 if(ViewLogic.getIsExpert())
         {
             RefreshUpcomingReviews();
             RefreshCompletedReviews();
         }
         RefreshReturnedReviews();
         RefreshSharedReviews();
            
              SwingUtilities.invokeLater(new Runnable(){
                  @Override public void run(){
                	  enableComponents(curr,true);
                 	 frame.dispose(); 
                 	 if(codeReviews.isEmpty())
                     {
                    	 btnReviewResults.setEnabled(false); 
                     }
                     if(completedReviews.isEmpty())
                     {
                    	 btnReviewCompleted.setEnabled(false);
                     }
                     if(sharedReviews.isEmpty())
                     {
                    	btnSharedReviews.setEnabled(false);
                     }
                    if(codeFiles.isEmpty())
                    {
                    	btnReviewRequests.setEnabled(false);
                    }
                    if(ViewLogic.getLogEuser().getTeam() == 0)
                        {
                        	btnTeamProfile.setEnabled(false);
                        }
                 	
                 	
                }
               });
            
         }
         }).start();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnReviewResultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReviewResultsActionPerformed
        dlgReviews reviewsDialog = new dlgReviews(this, this.codeReviews, false , this.view);
        reviewsDialog.setVisible(true);
    }//GEN-LAST:event_btnReviewResultsActionPerformed

    private void btnReviewCompletedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReviewCompletedActionPerformed
        dlgReviews reviewsDialog = new dlgReviews(this, completedReviews, false , this.view);
        reviewsDialog.setVisible(true);
    }//GEN-LAST:event_btnReviewCompletedActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        LoginFrame loginFrame = new LoginFrame(ViewLogic.getInstance());
       dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
       if(ViewLogic.getIsExpert())
       {
             lbScore.setText("Score: " + ViewLogic.getLogEuser().getScore());
       }
       else
       {
           lbScore.setText("Score: " + ViewLogic.getLogUser().getScore());
       }
        
    }//GEN-LAST:event_formMouseMoved

        public void enableComponents(Container container, boolean enable) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            component.setEnabled(enable);
            if (component instanceof Container) {
                enableComponents((Container)component, enable);
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
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(UserMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(UserMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(UserMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(UserMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new UserMain().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel UserRank;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnReviewCompleted;
    private javax.swing.JButton btnReviewRequests;
    private javax.swing.JButton btnReviewResults;
    private javax.swing.JButton btnSendToReview;
    private javax.swing.JButton btnSharedReviews;
    private javax.swing.JButton btnTeamProfile;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbScore;
    private javax.swing.JLabel lbTeamScoe1;
    private javax.swing.JLabel lbUserName;
    private javax.swing.JLabel userIcon;
    // End of variables declaration//GEN-END:variables
}
