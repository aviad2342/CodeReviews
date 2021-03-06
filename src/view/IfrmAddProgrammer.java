/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;
import model.ExpertProgrammer;

import utils.SoundClass;
/**
 *
 * @author Aviad
 */
public class IfrmAddProgrammer extends javax.swing.JInternalFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**view field*/
    private ViewLogic view;
    /**MyDesktopPane field*/
    private javax.swing.JDesktopPane myDesk;
    
    private String fileName;
    /**
     * Creates new form IfrmAddProgrammer
     * @param view
     * @param myDesk
     */
    public IfrmAddProgrammer(ViewLogic view, javax.swing.JDesktopPane myDesk) {
        
            this.myDesk = myDesk;
            this.view = view;
            initComponents();
            //this.iconFrame.setVisible(false);
            this.plExpert.setVisible(false);
            this.passErr.setVisible(false);
            this.txtID.requestFocusInWindow();
            Dimension desktopSize = this.myDesk.getSize();   // the size of the desktopPane
            this.setSize(desktopSize);
            DefaultListModel<String> modle = new DefaultListModel<String>();
            for(String lang :view.getAllLanguages())
            {
            modle.addElement(lang);
            }
            LanguagesJList.setModel(modle);
            LanguagesJList.setSelectionModel(new DefaultListSelectionModel() {
	    private static final long serialVersionUID = 1L;
	    boolean gestureStarted = false;

                @Override
                public void setSelectionInterval(int index0, int index1) {
                    if(!gestureStarted){
                        if (isSelectedIndex(index0)) {
                            super.removeSelectionInterval(index0, index1);
                        } else {
                            super.addSelectionInterval(index0, index1);
                        }
                    }
                    gestureStarted = true;
                }
                @Override
                public void setValueIsAdjusting(boolean isAdjusting) {
                    if (isAdjusting == false) {
                        gestureStarted = false;
                    }
                }
            });
            URL url=getClass().getResource("/images/user.png");
            File f= new File(url.getPath());
            fileName=f.getAbsolutePath();
            ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null); 
    }
                

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSave = new javax.swing.JButton();
        txtLastName = new javax.swing.JTextField();
        txtID = new javax.swing.JTextField();
        txtFirstName = new javax.swing.JTextField();
        txtMail = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        lbIcon = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        iconFrame = new javax.swing.JLabel();
        IsExpertCheckBox = new javax.swing.JCheckBox();
        plExpert = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        LanguagesJList = new javax.swing.JList<>();
        ProgrammingLanguages = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        txtPasswordValidation = new javax.swing.JPasswordField();
        passErr = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 204));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setPreferredSize(new java.awt.Dimension(1071, 618));
        setVisible(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSave.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        getContentPane().add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 530, 100, 31));

        txtLastName.setBackground(new java.awt.Color(102, 102, 102));
        txtLastName.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtLastName.setForeground(new java.awt.Color(255, 255, 255));
        txtLastName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        getContentPane().add(txtLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(553, 137, 180, 26));

        txtID.setBackground(new java.awt.Color(102, 102, 102));
        txtID.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtID.setForeground(new java.awt.Color(255, 255, 255));
        txtID.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        getContentPane().add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(553, 71, 180, 28));

        txtFirstName.setBackground(new java.awt.Color(102, 102, 102));
        txtFirstName.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtFirstName.setForeground(new java.awt.Color(255, 255, 255));
        txtFirstName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        getContentPane().add(txtFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(553, 105, 180, 26));

        txtMail.setBackground(new java.awt.Color(102, 102, 102));
        txtMail.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtMail.setForeground(new java.awt.Color(255, 255, 255));
        txtMail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        txtMail.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        getContentPane().add(txtMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(553, 169, 180, 26));

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel1.setText("ID:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(323, 78, -1, -1));

        jLabel3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel3.setText("First Name:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(323, 106, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel4.setText("Last Name:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(323, 138, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel5.setText("E-Mail:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(323, 170, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel6.setText("Password:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(323, 202, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel7.setText("Password Validation:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(323, 238, 169, -1));

        btnCancel.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 530, 100, 31));

        lbIcon.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lbIcon.setText("Icon:");
        getContentPane().add(lbIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(323, 270, -1, -1));

        jButton3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton3.setText("Choose...");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(553, 270, 100, -1));

        iconFrame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        getContentPane().add(iconFrame, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 30, 160, 160));

        IsExpertCheckBox.setBackground(new java.awt.Color(204, 204, 204));
        IsExpertCheckBox.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        IsExpertCheckBox.setText("Is Expert:  ");
        IsExpertCheckBox.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        IsExpertCheckBox.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        IsExpertCheckBox.setOpaque(false);
        IsExpertCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IsExpertCheckBoxActionPerformed(evt);
            }
        });
        getContentPane().add(IsExpertCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(323, 301, -1, -1));

        plExpert.setBackground(new java.awt.Color(204, 204, 204));
        plExpert.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        plExpert.setOpaque(false);

        LanguagesJList.setBackground(new java.awt.Color(102, 102, 102));
        LanguagesJList.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        LanguagesJList.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(LanguagesJList);

        ProgrammingLanguages.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        ProgrammingLanguages.setText("Programming Languages:");

        javax.swing.GroupLayout plExpertLayout = new javax.swing.GroupLayout(plExpert);
        plExpert.setLayout(plExpertLayout);
        plExpertLayout.setHorizontalGroup(
            plExpertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plExpertLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ProgrammingLanguages)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        plExpertLayout.setVerticalGroup(
            plExpertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plExpertLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(plExpertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProgrammingLanguages))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        getContentPane().add(plExpert, new org.netbeans.lib.awtextra.AbsoluteConstraints(323, 348, 423, -1));

        txtPassword.setBackground(new java.awt.Color(102, 102, 102));
        txtPassword.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(255, 255, 255));
        txtPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        txtPassword.setEchoChar('\u2022');
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(553, 201, 180, 26));

        txtPasswordValidation.setBackground(new java.awt.Color(102, 102, 102));
        txtPasswordValidation.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        txtPasswordValidation.setForeground(new java.awt.Color(255, 255, 255));
        txtPasswordValidation.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        txtPasswordValidation.setEchoChar('\u2022');
        txtPasswordValidation.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPasswordValidationFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPasswordValidationFocusLost(evt);
            }
        });
        getContentPane().add(txtPasswordValidation, new org.netbeans.lib.awtextra.AbsoluteConstraints(553, 233, 180, 26));

        passErr.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        passErr.setForeground(new java.awt.Color(255, 51, 51));
        getContentPane().add(passErr, new org.netbeans.lib.awtextra.AbsoluteConstraints(764, 233, 170, 26));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/grayNoobwallpaper.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
      if(Arrays.equals(txtPassword.getPassword(), txtPasswordValidation.getPassword()) ){
        final JFrame pa=(JFrame)myDesk.getTopLevelAncestor();
           final JDialog frame=view.getAnimation(pa);
            	  final JInternalFrame curr=this;
                 
                  new Thread(new Runnable(){
                  	
                  @Override
                  public void run(){
                     //time consuming algorithm.
                     enableComponents(pa,false);
                      view.addProgrammer(txtID, txtFirstName, txtLastName, txtMail, txtPassword, IsExpertCheckBox, fileName, LanguagesJList, curr);
                       SwingUtilities.invokeLater(new Runnable(){
                           @Override public void run(){
                        	   enableComponents(pa,true);
                          	 frame.dispose(); 
                          
         	
                         }
                        });
                     
                  }
                  }).start();
        
      }else{
          sound("/sounds/errorSound.wav");
          JOptionPane.showMessageDialog(null, "Passwords Does Not Match!", "Password Mismatch", JOptionPane.PLAIN_MESSAGE, icon("/images/symbolError.png"));
      }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            JFileChooser ch= new JFileChooser();
            ch.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    }
                    final String name = f.getName();
                    return name.endsWith(".png") || name.endsWith(".jpg")|| name.endsWith(".gif")|| name.endsWith(".jpeg")|| name.endsWith(".bmp")|| name.endsWith(".JPG");
                }
                @Override
                public String getDescription() {
                    return "*.png,*.jpg,*.gif,*.jpeg,*.bmp,*.JPG";
                }

            });
            ch.showOpenDialog(null);
            File f = ch.getSelectedFile();
            if(f!=null)
            {
                fileName=f.getAbsolutePath();
                 
                BufferedImage img= ImageIO.read(f);
                BufferedImage imd=new BufferedImage(iconFrame.getWidth(),iconFrame.getHeight(),BufferedImage.TYPE_INT_RGB);
                Graphics2D g=imd.createGraphics();
                g.drawImage(img,0,0,iconFrame.getWidth(),iconFrame.getHeight(),null);
                 this.iconFrame.setVisible(true);
                 this.iconFrame.setIcon(new ImageIcon(imd));
                
        
            }
        } catch (IOException ex) {
            Logger.getLogger(IfrmAddProgrammer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void IsExpertCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IsExpertCheckBoxActionPerformed

           this.plExpert.setVisible(IsExpertCheckBox.isSelected());
       
    }//GEN-LAST:event_IsExpertCheckBoxActionPerformed

    private void txtPasswordValidationFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPasswordValidationFocusLost
        if(Arrays.equals(txtPassword.getPassword(), txtPasswordValidation.getPassword()) ){
            Border border = BorderFactory.createLineBorder(Color.getHSBColor(102, 102, 102));
            txtPasswordValidation.setBorder(border);
      }else{
          Border border = BorderFactory.createLineBorder(Color.red);
          txtPasswordValidation.setBorder(border);
        if(txtPasswordValidation.getPassword().length != 0){  
          this.passErr.setVisible(true);
          passErr.setText("Password Mismatch!");
        }
      }
    }//GEN-LAST:event_txtPasswordValidationFocusLost

    private void txtPasswordValidationFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPasswordValidationFocusGained
        Border border = BorderFactory.createLineBorder(Color.getHSBColor(102, 102, 102));
            txtPasswordValidation.setBorder(border);
            this.passErr.setVisible(false);
            passErr.setText("");
            txtPasswordValidation.selectAll();
    }//GEN-LAST:event_txtPasswordValidationFocusGained
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
     * the method gets address of icon file and return the icon for display.
     * @param iconAddress
     * @return the icon
     */
    public Icon icon(String iconAddress) {          ///////// icon method
        Icon succeedIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(iconAddress)));
        return succeedIcon;
    }
    
    
      public void enableComponents(Container container, boolean enable) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            component.setEnabled(enable);
            if (component instanceof Container) {
                enableComponents((Container)component, enable);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox IsExpertCheckBox;
    private javax.swing.JList<String> LanguagesJList;
    private javax.swing.JLabel ProgrammingLanguages;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel iconFrame;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel passErr;
    private javax.swing.JPanel plExpert;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtMail;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JPasswordField txtPasswordValidation;
    // End of variables declaration//GEN-END:variables
}
