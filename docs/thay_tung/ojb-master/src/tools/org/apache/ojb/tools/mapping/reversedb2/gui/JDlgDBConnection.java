package org.apache.ojb.tools.mapping.reversedb2.gui;


import java.util.Vector;
import org.apache.ojb.tools.mapping.reversedb2.Main;
import javax.swing.MutableComboBoxModel;
/* Copyright 2002-2005 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */





/**
 * This dialog collects JDBC connection data and opens the connection. The JDBC
 * driver class and URL are stored in the properties of Main, so the user only
 * has to enter this data once.
 *
 * The method showAndReturnConnection() performs a show(), blocks until the 
 * dialog is disposed and returns the connection. If no valid connection is
 * established, null is returned.
 * 
 * @author <a href="mailto:bfl@florianbruckner.com">Florian Bruckner</a>
 * @version $Id: JDlgDBConnection.java,v 1.1 2007-08-24 22:17:41 ewestfal Exp $
 */
public class JDlgDBConnection extends javax.swing.JDialog
{
    
    private Vector vJDBCDrivers = new Vector();
    private Vector vJDBCUrls = new Vector();
    
    private java.sql.Connection theConnection = null;
    
    private boolean isDisposed = false;
    /** Creates new form JDlgDBConnection.
     * @param parent parent frame as specified by javax.swing.JDialog
     * @param isModal
     */
    public JDlgDBConnection(java.awt.Frame parent, boolean isModal)
    {
        super(parent, isModal);
        initComponents();
        
        synchronized (Main.getProperties())
        {
            // Read all stored JDBCDrivers from properties
            int i = 0;
            String s = null;
            do
            {
                s = Main.getProperties().getProperty(Main.PROPERTY_JDBCDRIVER + i);
                if (s != null) vJDBCDrivers.add(s);
                i++;
            } while (s != null);


            // Read all stored JDBCUrls from properties
            i = 0;
            s = null;
            do
            {
                s = Main.getProperties().getProperty(Main.PROPERTY_JDBCURL + i);
                if (s != null) vJDBCUrls.add(s);
                i++;
            } while (s != null);


            // Read all stored JDBC usernames from properties
            s = Main.getProperties().getProperty("JDBCUser" + i);



            this.cmbJDBCDriver.setModel(new javax.swing.DefaultComboBoxModel(vJDBCDrivers));
            this.cmbJDBCURL.setModel(new javax.swing.DefaultComboBoxModel(vJDBCUrls));


            this.cmbJDBCDriver.setEditable(true);
            this.cmbJDBCURL.setEditable(true);


            this.tfUsername.setText(Main.getProperties().getProperty(Main.PROPERTY_JDBCUSER, ""));
            this.tfPassword.setText("");


            this.lblResult.setText("");
            pack();
        }
        if (this.cmbJDBCDriver.getSelectedItem() != null &&
            this.cmbJDBCURL.getSelectedItem() != null)
        {
            this.pbTest.setEnabled(true);
            this.pbOpen.setEnabled(true);
        }
        else
        {
            this.pbTest.setEnabled(false);
            this.pbOpen.setEnabled(false);        
        }
    }
    
    /** Performs a show(), blocks until the
     * dialog is disposed and returns the connection. If no valid connection is
     * established, null is returned.
     */    
    public java.sql.Connection showAndReturnConnection()
    {
        show();
        while (!this.isDisposed)
        {
            try
            {
                synchronized (this)
                {
                    wait();
                }
            }
            catch (Throwable t)
            {
            }
        }
        return theConnection;
    }
    
    /** dispose() of javax.swing.JDialog overridden to be able to wait until the dialog is
     * disposed even if it is not modal in showAndReturnConnection().
     *
     * Warning: This method should not be called from the Eventdispatch-Thread if the dialog is non-modal, because no events can be dispatched while this method blocks.
     */    
    public void dispose()
    {
        super.dispose();
        this.isDisposed = true;
        synchronized(this)
        {
            this.notifyAll();
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents()//GEN-BEGIN:initComponents
    {
        java.awt.GridBagConstraints gridBagConstraints;

        lblJDBCDriver = new javax.swing.JLabel();
        cmbJDBCDriver = new javax.swing.JComboBox();
        lblJDBCURL = new javax.swing.JLabel();
        cmbJDBCURL = new javax.swing.JComboBox();
        lblUsername = new javax.swing.JLabel();
        tfUsername = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        tfPassword = new javax.swing.JPasswordField();
        lblResult = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        pbCancel = new javax.swing.JButton();
        pbTest = new javax.swing.JButton();
        pbOpen = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                closeDialog(evt);
            }
        });

        lblJDBCDriver.setText("JDBC Driver Class:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(lblJDBCDriver, gridBagConstraints);

        cmbJDBCDriver.setFont(new java.awt.Font("Dialog", 0, 12));
        cmbJDBCDriver.setMinimumSize(new java.awt.Dimension(31, 20));
        cmbJDBCDriver.setPreferredSize(new java.awt.Dimension(31, 20));
        cmbJDBCDriver.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                cmbJDBCDriverItemStateChanged(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(cmbJDBCDriver, gridBagConstraints);

        lblJDBCURL.setText("JDBC URL:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(lblJDBCURL, gridBagConstraints);

        cmbJDBCURL.setFont(new java.awt.Font("Dialog", 0, 12));
        cmbJDBCURL.setMinimumSize(new java.awt.Dimension(31, 20));
        cmbJDBCURL.setPreferredSize(new java.awt.Dimension(31, 20));
        cmbJDBCURL.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                cmbJDBCURLItemStateChanged(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(cmbJDBCURL, gridBagConstraints);

        lblUsername.setText("Username:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(lblUsername, gridBagConstraints);

        tfUsername.setColumns(30);
        tfUsername.setText("jTextField3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(tfUsername, gridBagConstraints);

        lblPassword.setText("Password:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(lblPassword, gridBagConstraints);

        tfPassword.setColumns(30);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(tfPassword, gridBagConstraints);

        lblResult.setBackground((java.awt.Color) javax.swing.UIManager.getDefaults().get("Label.background"));
        lblResult.setEditable(false);
        lblResult.setText("jTextField1");
        lblResult.setBorder(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(lblResult, gridBagConstraints);

        pbCancel.setMnemonic('c');
        pbCancel.setText("Cancel");
        pbCancel.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                pbCancelActionPerformed(evt);
            }
        });

        jPanel1.add(pbCancel);

        pbTest.setMnemonic('t');
        pbTest.setText("Test");
        pbTest.setEnabled(false);
        pbTest.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                pbTestActionPerformed(evt);
            }
        });

        jPanel1.add(pbTest);

        pbOpen.setMnemonic('o');
        pbOpen.setText("Open");
        pbOpen.setEnabled(false);
        pbOpen.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                pbOpenActionPerformed(evt);
            }
        });

        jPanel1.add(pbOpen);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }//GEN-END:initComponents

    private void pbOpenActionPerformed (java.awt.event.ActionEvent evt)//GEN-FIRST:event_pbOpenActionPerformed
    {//GEN-HEADEREND:event_pbOpenActionPerformed
        // Add your handling code here:
      pbTestActionPerformed(evt);
      if (theConnection != null)
      {
          synchronized(Main.getProperties())
          {
              int i = 0;
              while (Main.getProperties().getProperty(Main.PROPERTY_JDBCDRIVER + i) != null)
              {
                  Main.getProperties().remove(Main.PROPERTY_JDBCDRIVER + i);
                  i++;
              }
              while (Main.getProperties().getProperty(Main.PROPERTY_JDBCURL + i) != null)
              {
                  Main.getProperties().remove(Main.PROPERTY_JDBCURL + i);
                  i++;
              }
              for (i = 0; i < cmbJDBCDriver.getModel().getSize(); i++)
                  Main.getProperties().setProperty(Main.PROPERTY_JDBCDRIVER + i, cmbJDBCDriver.getModel().getElementAt(i).toString());
              for (i = 0; i < cmbJDBCURL.getModel().getSize(); i++)
                  Main.getProperties().setProperty(Main.PROPERTY_JDBCURL + i, cmbJDBCURL.getModel().getElementAt(i).toString());
              Main.getProperties().setProperty(Main.PROPERTY_JDBCUSER, tfUsername.getText());
              Main.getProperties().storeProperties("");
          }
          dispose();        
      }
    }//GEN-LAST:event_pbOpenActionPerformed


    private void cmbJDBCURLItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_cmbJDBCURLItemStateChanged
    {//GEN-HEADEREND:event_cmbJDBCURLItemStateChanged
        if (this.cmbJDBCDriver.getSelectedItem() != null &&
            this.cmbJDBCURL.getSelectedItem() != null)
        {
            this.pbTest.setEnabled(true);
            this.pbOpen.setEnabled(true);
        }
        else
        {
            this.pbTest.setEnabled(false);
            this.pbOpen.setEnabled(false);        
        }            
    }//GEN-LAST:event_cmbJDBCURLItemStateChanged


    private void cmbJDBCDriverItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_cmbJDBCDriverItemStateChanged
    {//GEN-HEADEREND:event_cmbJDBCDriverItemStateChanged
        // Add your handling code here:
        if (this.cmbJDBCDriver.getSelectedItem() != null &&
            this.cmbJDBCURL.getSelectedItem() != null)
        {
            this.pbTest.setEnabled(true);
            this.pbOpen.setEnabled(true);
        }
        else
        {
            this.pbTest.setEnabled(false);
            this.pbOpen.setEnabled(false);        
        }
    }//GEN-LAST:event_cmbJDBCDriverItemStateChanged
    
  private void pbCancelActionPerformed (java.awt.event.ActionEvent evt)//GEN-FIRST:event_pbCancelActionPerformed
  {//GEN-HEADEREND:event_pbCancelActionPerformed
      // Add your handling code here:
      theConnection = null;
      dispose();
  }//GEN-LAST:event_pbCancelActionPerformed
  
  private void pbTestActionPerformed (java.awt.event.ActionEvent evt)//GEN-FIRST:event_pbTestActionPerformed
  {//GEN-HEADEREND:event_pbTestActionPerformed
        // Add your handling code here:
        theConnection = null;
        try
        {
            theConnection = this.connectToDB(cmbJDBCDriver.getSelectedItem().toString(), 
                                 cmbJDBCURL.getSelectedItem().toString(), 
                                 tfUsername.getText(), 
                                 new String(tfPassword.getPassword()));
            this.lblResult.setText("Connection successful");
            this.lblResult.setForeground(java.awt.Color.green);
            if (this.cmbJDBCDriver.getModel() instanceof MutableComboBoxModel)
            {
                Object selectedItem = cmbJDBCDriver.getSelectedItem();
                ((MutableComboBoxModel)this.cmbJDBCDriver.getModel()).removeElement(selectedItem);
                ((MutableComboBoxModel)this.cmbJDBCDriver.getModel()).insertElementAt(selectedItem,0);
                cmbJDBCDriver.setSelectedItem(selectedItem);
            }
            if (this.cmbJDBCURL.getModel() instanceof MutableComboBoxModel)
            {
                Object selectedItem = cmbJDBCURL.getSelectedItem();
                ((MutableComboBoxModel)this.cmbJDBCURL.getModel()).removeElement(selectedItem);
                ((MutableComboBoxModel)this.cmbJDBCURL.getModel()).insertElementAt(selectedItem, 0);
                cmbJDBCURL.setSelectedItem(selectedItem);
            }
        }
        catch (java.sql.SQLException sqlEx)
        {
            StringBuffer strBufMessages = new StringBuffer();
            java.sql.SQLException currentSqlEx = sqlEx;
            do
            {
                strBufMessages.append("\n" + sqlEx.getErrorCode() + ":" + sqlEx.getMessage());
                currentSqlEx = currentSqlEx.getNextException();
            } while (currentSqlEx != null);
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Error connecting to database:" + strBufMessages.toString(), 
                "Error connecting to database",                 
                javax.swing.JOptionPane.ERROR_MESSAGE);
            this.lblResult.setText("Connection failed: JDBC Error");
            this.lblResult.setForeground(java.awt.Color.red);            
        }
        catch (java.lang.ClassNotFoundException clsNFEx)
        {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Could not load driver because the specified class could not be found", 
                "Error connecting to database", 
                javax.swing.JOptionPane.ERROR_MESSAGE);            
            this.lblResult.setText("Connection failed: Driver class not found");
            this.lblResult.setForeground(java.awt.Color.red);                        
        }
  }//GEN-LAST:event_pbTestActionPerformed
    
    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog
    
    private java.sql.Connection connectToDB(String strJDBCDriver, String strJDBCURL,
        String strUsername, String strPassword)
        throws java.sql.SQLException, java.lang.ClassNotFoundException
    {
        Class.forName(strJDBCDriver);
        java.sql.Connection conn =
        java.sql.DriverManager.getConnection(strJDBCURL,
                strUsername, strPassword);  
        return conn;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        System.out.println(new JDlgDBConnection(new javax.swing.JFrame(), true).showAndReturnConnection());
        System.out.println(new JDlgDBConnection(new javax.swing.JFrame(), false).showAndReturnConnection());
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblJDBCURL;
    private javax.swing.JComboBox cmbJDBCURL;
    private javax.swing.JComboBox cmbJDBCDriver;
    private javax.swing.JLabel lblJDBCDriver;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField lblResult;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JPasswordField tfPassword;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTextField tfUsername;
    private javax.swing.JButton pbTest;
    private javax.swing.JButton pbOpen;
    private javax.swing.JButton pbCancel;
    // End of variables declaration//GEN-END:variables
    
}


/***************************** Changelog *****************************
 * // $Log: not supported by cvs2svn $
 * // Revision 1.1.2.1  2005/12/21 22:32:41  tomdz
 * // Updated license
 * //
 * // Revision 1.1  2004/05/05 16:45:09  arminw
 * // fix fault
 * // wrong package structure used:
 * // org.apache.ojb.tools.reversdb
 * // org.apache.ojb.tools.reversdb2
 * //
 * // instead of
 * // org.apache.ojb.tools.mapping.reversdb
 * // org.apache.ojb.tools.mapping.reversdb2
 * //
 * // Revision 1.1  2004/05/04 13:45:04  arminw
 * // move reverseDB stuff
 * //
 * // Revision 1.3  2004/04/04 23:53:43  brianm
 * // Fixed initial copyright dates to match cvs repository
 * //
 * // Revision 1.2  2004/03/11 18:16:23  brianm
 * // ASL 2.0
 * //
 * // Revision 1.1  2002/06/18 12:37:46  florianbruckner
 * // adding a complete redesign of a reverse engineering tool. This is still work in progress.
 * //
 * // Revision 1.4  2002/06/10 21:30:53  Administrator
 * // more intuitive handling
 * //
 * // Revision 1.3  2002/06/08 00:51:30  Administrator
 * // no message
 * //
 * // Revision 1.2  2002/06/07 10:11:25  Administrator
 * // *** empty log message ***
 * //
 * // Revision 1.1.1.1  2002/06/06 10:54:41  Administrator
 * // initial import
 * //
 * // Revision 1.2  2002/05/16 11:47:09  florianbruckner
 * // fix CR/LF issue, change license to ASL
 * //
 * // Revision 1.1  2002/04/18 11:44:16  mpoeschl
 * //
 * // move files to new location
 * //
 * // Revision 1.3  2002/04/07 09:05:17  thma
 * // *** empty log message ***
 * //
 * // Revision 1.2  2002/03/11 17:36:26  florianbruckner
 * // fix line break issue for these files that were previously checked in with -kb
 * //
 * // Revision 1.1  2002/03/04 17:19:32  thma
 * // initial checking for Florians Reverse engineering tool
 * //
 * // Revision 1.1.1.1  2002/02/20 13:35:25  Administrator
 * // initial import
 * //
 * /***************************** Changelog *****************************/
