/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.Programmer;
import model.Team;
import utils.SoundClass;

/**
 *
 * @author Aviad
 */
public class IfrmManageTeamMembers extends javax.swing.JInternalFrame {

    /**view field*/
    private ViewLogic view;
    /**MyDesktopPane field*/
    private javax.swing.JDesktopPane myDesk;
    
    private Programmer programmer;
    
    private Team team;
    
    private ArrayList<Programmer> prog;
    
    private ArrayList<Team> teams;
    /**
     * Creates new form IfrmManageTeamMembers
     * @param view
     * @param myDesk
     */
    public IfrmManageTeamMembers(ViewLogic view, javax.swing.JDesktopPane myDesk) {
        this.myDesk = myDesk;
        this.view = view;
        initComponents();
        setTitle("Add Team"); //the title of the frame
        Dimension desktopSize = this.myDesk.getSize();   // the size of the desktopPane
        this.setSize(desktopSize);
        this.lbErr.setVisible(false);
        this.lbTeamErr.setVisible(false);
        prog = view.getAllProgrammers();
        DefaultListModel<Programmer> modle = new DefaultListModel<Programmer>(); 
            for(Programmer p : prog)
            {
                if(p.getTeam() == 0){
                modle.addElement(p);
                }
            }
            AvailableProgrammerList.setModel(modle);
            if(modle.isEmpty()){
        this.lbErr.setVisible(true);        
        AvailableProgrammerList.setEnabled(false);
            }
        teams = view.getAllTeams();
        for(Team t :teams)
            {
            cbTeamID.addItem(t.getTeamName()+"");
            }
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbTeamID = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        AvailableProgrammerList = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        TeamMembersList = new javax.swing.JList<>();
        btnInsert = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbErr = new javax.swing.JLabel();
        lbTeamErr = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(102, 102, 102));
        setBorder(null);
        setPreferredSize(new java.awt.Dimension(1071, 618));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbTeamID.setBackground(new java.awt.Color(102, 102, 102));
        cbTeamID.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbTeamID.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTeamIDItemStateChanged(evt);
            }
        });
        getContentPane().add(cbTeamID, new org.netbeans.lib.awtextra.AbsoluteConstraints(751, 11, 197, 26));

        AvailableProgrammerList.setBackground(new java.awt.Color(204, 204, 204));
        AvailableProgrammerList.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        AvailableProgrammerList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AvailableProgrammerListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(AvailableProgrammerList);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 65, 400, 505));

        TeamMembersList.setBackground(new java.awt.Color(204, 204, 204));
        TeamMembersList.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        TeamMembersList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TeamMembersListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TeamMembersList);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(648, 65, 400, 505));

        btnInsert.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnInsert.setText("Add To >>>>");
        btnInsert.setEnabled(false);
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });
        getContentPane().add(btnInsert, new org.netbeans.lib.awtextra.AbsoluteConstraints(464, 219, 141, 31));

        btnRemove.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnRemove.setText("<<<< Remove From");
        btnRemove.setEnabled(false);
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });
        getContentPane().add(btnRemove, new org.netbeans.lib.awtextra.AbsoluteConstraints(464, 334, -1, 31));

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel1.setText("Team Name:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(648, 11, 93, 26));

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel2.setText("Available Programmers:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 11, 164, 26));

        lbErr.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbErr.setForeground(new java.awt.Color(204, 0, 0));
        lbErr.setText("No Available Programmers Found.");
        getContentPane().add(lbErr, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 43, -1, -1));

        lbTeamErr.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbTeamErr.setForeground(new java.awt.Color(204, 0, 0));
        lbTeamErr.setText("No Team Members Found.");
        getContentPane().add(lbTeamErr, new org.netbeans.lib.awtextra.AbsoluteConstraints(648, 43, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/grayNoobwallpaper.jpg"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbTeamIDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTeamIDItemStateChanged
        DefaultListModel<Programmer> modle = new DefaultListModel<Programmer>();
        this.team = teams.get(cbTeamID.getSelectedIndex());
            for(Programmer p : teams.get(cbTeamID.getSelectedIndex()).getTeamMembers())
            {
                if(!p.equals(teams.get(cbTeamID.getSelectedIndex()).getTeamLeader())){
                modle.addElement(p);
                }
            }
             TeamMembersList.setModel(modle);
            if(modle.isEmpty()){
               this.lbTeamErr.setVisible(true);        
               TeamMembersList.setEnabled(false);
            }else{
               
               TeamMembersList.setEnabled(true);
            }
    }//GEN-LAST:event_cbTeamIDItemStateChanged

    private void AvailableProgrammerListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AvailableProgrammerListMouseClicked
        if(AvailableProgrammerList.getSelectedIndex() != -1){
            btnInsert.setEnabled(true);
        }
    }//GEN-LAST:event_AvailableProgrammerListMouseClicked

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        programmer =(Programmer) AvailableProgrammerList.getSelectedValue();
        if(this.team != null){        
        ((DefaultListModel<Programmer>)TeamMembersList.getModel()).addElement(programmer);
        view.ChangeTeamMember(programmer, team.getTeamNumber());
        TeamMembersList.setEnabled(true);
        btnRemove.setEnabled(true);
        this.lbTeamErr.setVisible(false);
         ((DefaultListModel<Programmer>)AvailableProgrammerList.getModel()).removeElement(programmer);
        }else {
            sound("/sounds/errorSound.wav");
            JOptionPane.showMessageDialog(null,  "Sorry you must choose team id first!", "Team Id Required", JOptionPane.PLAIN_MESSAGE, icon("/images/symbolError.png"));
            TeamMembersList.setEnabled(false);
            AvailableProgrammerList.setEnabled(false);
        }
        if(((DefaultListModel<Programmer>)AvailableProgrammerList.getModel()).isEmpty()){
            AvailableProgrammerList.setEnabled(false);
            btnInsert.setEnabled(false);
        }
         
    }//GEN-LAST:event_btnInsertActionPerformed

    private void TeamMembersListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TeamMembersListMouseClicked
        
        if(TeamMembersList.getSelectedIndex() != -1){
            btnRemove.setEnabled(true);
        }
    }//GEN-LAST:event_TeamMembersListMouseClicked

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        programmer = (Programmer)TeamMembersList.getSelectedValue();
        ((DefaultListModel<Programmer>)AvailableProgrammerList.getModel()).addElement(programmer);
        view.ChangeTeamMember(programmer, 0);
        this.lbErr.setVisible(false);
        AvailableProgrammerList.setEnabled(true);
        ((DefaultListModel<Programmer>)TeamMembersList.getModel()).removeElement(programmer);
        if(((DefaultListModel<Programmer>)TeamMembersList.getModel()).isEmpty()){
            TeamMembersList.setEnabled(false);
            btnRemove.setEnabled(false);
        }
    }//GEN-LAST:event_btnRemoveActionPerformed


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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<Programmer> AvailableProgrammerList;
    private javax.swing.JList<Programmer> TeamMembersList;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnRemove;
    private javax.swing.JComboBox<String> cbTeamID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbErr;
    private javax.swing.JLabel lbTeamErr;
    // End of variables declaration//GEN-END:variables
}
