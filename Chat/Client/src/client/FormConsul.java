/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mario
 */
public class FormConsul extends javax.swing.JFrame implements Runnable {

    /**
     * Creates new form FormConsul
     */
    int idPenyewa;
    String fullName;
    String username;
    DataOutputStream out;
    BufferedReader inp;
    Thread t;
    public FormConsul() {
        initComponents();
    }
    
    public FormConsul(String pIdPenyewa, String pFullName, String pUsername, Socket s) {
            initComponents();
            username = pUsername;
            idPenyewa = Integer.parseInt(pIdPenyewa);
            fullName = pFullName;
            labelNama.setText("Hi, " + fullName);
        try {
            out = new DataOutputStream(s.getOutputStream());
            inp = new BufferedReader(new InputStreamReader(s.getInputStream()));
            if(t == null)
            {
                t = new Thread(this, "Client Side");
                t.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(FormConsul.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void MintaDataRumah(){
        try {
            out.writeBytes("getRumah"+"\n" );
            String dataRumah = inp.readLine();
            String msgSplit[] = dataRumah.split(":");
            showChat("List Rumah : ");
            for (int i = 0; i < msgSplit.length; i++) {
                showChat(msgSplit[i]);
            }
        } catch (IOException ex) {
            Logger.getLogger(FormConsul.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showChat(String tmp){

        if (tmp.contains("//")) {
            String msgSplit[] = tmp.split("//");
            txtArea.append(msgSplit[1] + "\n");            
        }
        else{
            txtArea.append(tmp + "\n");          
        }
    }
    
    public void sendChat(String tmp){
        try {
            out.writeBytes(tmp + "\n");
        } catch (IOException ex) {
            Logger.getLogger(FormConsul.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void getHistory(){
        try {
            out.writeBytes("[HISTORY]:-:" + username+ "\n");    
            DisplayChat();
            
        } catch (IOException ex) {
            Logger.getLogger(FormConsul.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void DisplayChat(){
        try {
            System.out.println("menunggu display");
            String msg = inp.readLine();
            System.out.println(msg);
            String msgSplit[] = msg.split("--");
            System.out.println(msgSplit.length);
            if (msgSplit.length > 1) {                
                for (String str : msgSplit) {
                    if (str.equals("[HISRORY]")) {
                        System.out.println("masuk if");
                    }
                    else{
                        System.out.println("masuk else");
                        txtArea.append(str+"\n");
                    }
                }
            }
    } catch (IOException ex) {
            Logger.getLogger(FormConsul.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Connected(){
        try {
            out.writeBytes("[CONNECT]" + "\n");
        } catch (IOException ex) {
            Logger.getLogger(FormConsul.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        btnEnter = new javax.swing.JButton();
        txtBox = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labelNama = new javax.swing.JLabel();
        btnCall = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        btnEnter.setText("Enter");
        btnEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnterActionPerformed(evt);
            }
        });

        jLabel1.setText("Format Booking : [BOOK]/\"Nama Villa\"/\"Tanggal\"");

        jLabel2.setText("Contoh Format : [BOOK]/Eindhoven/2020-07-30");

        labelNama.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelNama.setText("HI, DUMMY");

        btnCall.setText("Call admin");
        btnCall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCallActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(btnEnter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBox)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelNama)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(btnCall)
                        .addGap(18, 18, 18)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNama)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnCall))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEnter, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnterActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            String message = txtBox.getText();
            if (message.contains("[BOOK]")) {
                sendChat(message+"/"+idPenyewa);
            }
            else{
                sendChat("admin<:>"+message+"<:>"+fullName+"<:>"+username);
            }
                showChat(fullName + " : " + message);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_btnEnterActionPerformed

    private void btnCallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCallActionPerformed
        // TODO add your handling code here:
        FormCall fc = new FormCall(username);
        fc.setVisible(true);
    }//GEN-LAST:event_btnCallActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormConsul.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormConsul.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormConsul.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormConsul.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormConsul().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCall;
    private javax.swing.JButton btnEnter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelNama;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JTextField txtBox;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        Connected();
        MintaDataRumah();
        System.out.println("minta data rumah");
        getHistory();
        System.out.println("display chat");
        while(true)
        {
            try {
                String msg = inp.readLine();
                showChat(msg);
            } catch (IOException ex) {
                Logger.getLogger(FormConsul.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
