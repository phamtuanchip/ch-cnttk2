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

package org.apache.ojb.tools.mapping.reversedb2.gui;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceListener;

import org.apache.ojb.tools.mapping.reversedb2.dbmetatreemodel.DBMetaCatalogNode;
import org.apache.ojb.tools.mapping.reversedb2.dbmetatreemodel.DBMetaRootNode;
import org.apache.ojb.tools.mapping.reversedb2.dbmetatreemodel.DBMetaSchemaNode;
import org.apache.ojb.tools.mapping.reversedb2.dbmetatreemodel.DBMetaTableNode;
import org.apache.ojb.tools.mapping.reversedb2.dbmetatreemodel.DatabaseMetaDataTreeModel;
import org.apache.ojb.tools.mapping.reversedb2.dnd2.DragHelper;
import org.apache.ojb.tools.mapping.reversedb2.dnd2.DropTargetHelper;
import org.apache.ojb.tools.mapping.reversedb2.dnd2.ReverseDbNodesDragWorker;
import org.apache.ojb.tools.mapping.reversedb2.dnd2.ReverseDbNodesDropWorker;
import org.apache.ojb.tools.mapping.reversedb2.propertyEditors.PropertyEditor;
import org.apache.ojb.tools.mapping.reversedb2.propertyEditors.PropertyEditorTarget;

/**
 *
 * @author  Administrator
 */
public class JIFrmDatabase extends javax.swing.JInternalFrame 
{
    private java.sql.Connection jdbcConnection;
    
    private DragSource dragSource;
    private DragGestureListener dgListener;
    private DragSourceListener dsListener;
    
    private java.util.HashMap hmPropertyEditors = new java.util.HashMap();
    
    /** Creates new form JIFrmDatabaseMetadata */
    public JIFrmDatabase(java.sql.Connection pjdbcConnection) 
    {
        this.jdbcConnection = pjdbcConnection;
        initComponents();
        try
        {
            this.setTitle("Database: " + jdbcConnection.getMetaData().getURL());
            DatabaseMetaDataTreeModel model = new DatabaseMetaDataTreeModel(this.jdbcConnection.getMetaData());
            model.addStatusMessageListener(new org.apache.ojb.tools.mapping.reversedb2.events.StatusMessageAdapter()
            {
                public void statusMessageReceived(final String s)
                {
                    javax.swing.SwingUtilities.invokeLater(new Runnable()
                    {
                        public void run()
                        {
                            jTextField1.setText(s);
                        }
                    });
                }
            });
            this.jTreeDatabase.setModel(model);            
        }
        catch (java.sql.SQLException sqlEx)
        {
            this.setTitle("Database: unknown");
            this.jTreeDatabase.setModel(new javax.swing.tree.DefaultTreeModel(new javax.swing.tree.DefaultMutableTreeNode("Error...")));
        }
        
        // new DatabaseTransferHandler().register(jTreeDatabase);
        // enable dragging for the tree.
        new DragHelper(new ReverseDbNodesDragWorker()).registerComponent(jTreeDatabase);
        
        // enable dropping for the tree.
        DropTargetHelper helper = new DropTargetHelper();
        helper.registerDropPasteWorker (new ReverseDbNodesDropWorker());
        jTreeDatabase.setDropTarget(helper.getDropTarget ());

    }
    
    private java.util.Collection getTableNodes(Object o)
    {
        if (o instanceof DBMetaRootNode    ||
            o instanceof DBMetaCatalogNode ||
            o instanceof DBMetaSchemaNode)
        {
            java.util.ArrayList alTables = new java.util.ArrayList();
            java.util.Enumeration e = ((javax.swing.tree.TreeNode)o).children();
            while (e.hasMoreElements())
            {
                alTables.addAll(getTableNodes(e.nextElement()));
            }
            return alTables;
        }
        else if (o instanceof DBMetaTableNode)
        {
            return java.util.Collections.singleton(o);
        }
        else
        {
            return null;
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents()//GEN-BEGIN:initComponents
    {
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrlTree = new javax.swing.JScrollPane();
        jTreeDatabase = new javax.swing.JTree();
        jScrlProperty = new javax.swing.JScrollPane();
        jToolBar1 = new javax.swing.JToolBar();
        jTextField1 = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Database");
        setPreferredSize(new java.awt.Dimension(300, 300));
        jTreeDatabase.setRootVisible(false);
        jTreeDatabase.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener()
        {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt)
            {
                jTreeDatabaseValueChanged(evt);
            }
        });

        jScrlTree.setViewportView(jTreeDatabase);

        jSplitPane1.setLeftComponent(jScrlTree);

        jSplitPane1.setRightComponent(jScrlProperty);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        jTextField1.setEditable(false);
        jTextField1.setText("jTextField1");
        jTextField1.setBorder(null);
        jTextField1.setOpaque(false);
        jToolBar1.add(jTextField1);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.SOUTH);

        pack();
    }//GEN-END:initComponents

    private void jTreeDatabaseValueChanged (javax.swing.event.TreeSelectionEvent evt)//GEN-FIRST:event_jTreeDatabaseValueChanged
    {//GEN-HEADEREND:event_jTreeDatabaseValueChanged
        // Add your handling code here:
        Object selectedObject = evt.getPath().getLastPathComponent();
        if (selectedObject instanceof PropertyEditorTarget)
        {
            PropertyEditorTarget p = (PropertyEditorTarget)selectedObject;
            if (p.getPropertyEditorClass() != null)
            {
                try
                {
                    if(!this.hmPropertyEditors.containsKey(p.getPropertyEditorClass()))
                    {
                        this.hmPropertyEditors.put(p.getPropertyEditorClass(), p.getPropertyEditorClass().newInstance());
                    }
                    PropertyEditor propertyEditor = (PropertyEditor)hmPropertyEditors.get(p.getPropertyEditorClass());
                    this.jScrlProperty.setViewportView(propertyEditor);
                    propertyEditor.setEditorTarget(p);
                }
                catch (Throwable t)
                {
                    t.printStackTrace();
                    this.jScrlProperty.setViewportView(null);
                }
            }
            else
            {
                this.jScrlProperty.setViewportView(null);
            }
        }
        else
        {
            this.jScrlProperty.setViewportView(null);
        }
    }//GEN-LAST:event_jTreeDatabaseValueChanged
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JScrollPane jScrlTree;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JScrollPane jScrlProperty;
    private javax.swing.JTree jTreeDatabase;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
    
}
