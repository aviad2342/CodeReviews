/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Point;
import java.io.File;

/**
 *
 * @author Aviad
 */
public class frmCodeEditor extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

     /**view field*/
    private ViewLogic view;
    
    IfrmEditor ifrmEditor;
    
    IfrmDescription description;
    
    File editFile;
    
    String textDescription;
    
    String language;
    
    UserMain user;
    /**
     * Creates new form frmCodeEditor
     * @param view
     * @param location
     * @param file
     */
    public frmCodeEditor(ViewLogic view, Point location, int width , File file,UserMain user) {
        this.view = view;
        initComponents();
        this.setTitle("Review Editor"); //the title of the frame
        this.setLocation((int)location.getX() + (width+3), (int)location.getY()-1);
        ifrmEditor = new IfrmEditor(this.view, EditorPane, file);
        EditorPane.add(ifrmEditor);
        ifrmEditor.show();
        this.user=user;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCancel = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        EditorPane = new javax.swing.JDesktopPane();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        btnCancel.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnNext.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Button-Next-icon.png"))); // NOI18N
        btnNext.setText("Next  ");
        btnNext.setFocusable(false);
        btnNext.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNext.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout EditorPaneLayout = new javax.swing.GroupLayout(EditorPane);
        EditorPane.setLayout(EditorPaneLayout);
        EditorPaneLayout.setHorizontalGroup(
            EditorPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        EditorPaneLayout.setVerticalGroup(
            EditorPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 672, Short.MAX_VALUE)
        );

        btnBack.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Button-Back-icon.png"))); // NOI18N
        btnBack.setText(" Back");
        btnBack.setEnabled(false);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 321, Short.MAX_VALUE)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(250, 250, 250)
                .addComponent(btnNext)
                .addGap(48, 48, 48))
            .addComponent(EditorPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(EditorPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnNext)
                    .addComponent(btnBack))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        if(ifrmEditor.isShowing()){
        btnNext.setEnabled(true);
        editFile = ifrmEditor.getEditFile();
        language = ifrmEditor.getLanguage();
        btnBack.setEnabled(true);
        ifrmEditor.dispose();
        btnNext.setEnabled(false);
        description = new IfrmDescription(view, EditorPane, this);
        EditorPane.add(description);
        description.show();
        }else{
            textDescription=description.getDescription();
           view.addCodeFile(editFile, textDescription, language, ifrmEditor);
            this.user.RefreshScore();
           dispose();
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed

        description.dispose();
        btnBack.setEnabled(false);
        ifrmEditor = new IfrmEditor(this.view, EditorPane, editFile);
        EditorPane.add(ifrmEditor);
        ifrmEditor.show();
        btnNext.setEnabled(true);
    }//GEN-LAST:event_btnBackActionPerformed

    public void enabledNext(boolean enabled){
        btnNext.setEnabled(enabled);
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
//            java.util.logging.Logger.getLogger(frmCodeEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(frmCodeEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(frmCodeEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(frmCodeEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new frmCodeEditor().setVisible(trubtnNext          }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane EditorPane;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnNext;
    // End of variables declaration//GEN-END:variables
}
