/***********************************************************************************************************************
 *
 * blueArgyle - a Java UI for Argyll
 * Copyright (C) 2011-2012 by Tidalwave s.a.s. (http://www.tidalwave.it)
 *
 ***********************************************************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 *
 ***********************************************************************************************************************
 *
 * WWW: http://blueargyle.java.net
 * SCM: https://bitbucket.org/tidalwave/blueargyle-src
 *
 **********************************************************************************************************************/
package it.tidalwave.blueargyle.profileevaluation.ui.impl.main.netbeans;

import javax.swing.JPanel;

/***********************************************************************************************************************
 *
 * @author  fritz
 * @version $Id$
 *
 **********************************************************************************************************************/
public class ProfileEvaluationMainPanel extends JPanel 
  {
    public ProfileEvaluationMainPanel() 
      {
        initComponents();
        setOpaque(true);
      }

    /*******************************************************************************************************************
     * 
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     * 
     ******************************************************************************************************************/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        btStart = new javax.swing.JButton();
        lbTitle = new javax.swing.JLabel();
        lbDescription = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jPanel1 = new javax.swing.JPanel();
        lbDisplaySelection = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));

        setBackground(new java.awt.Color(80, 80, 80));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 8, 16));
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0};
        layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0};
        setLayout(layout);

        btStart.setText(org.openide.util.NbBundle.getMessage(ProfileEvaluationMainPanel.class, "ProfileEvaluationMainPanel.btStart.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        add(btStart, gridBagConstraints);

        lbTitle.setFont(lbTitle.getFont().deriveFont(lbTitle.getFont().getStyle() | java.awt.Font.BOLD, lbTitle.getFont().getSize()+3));
        lbTitle.setForeground(new java.awt.Color(255, 255, 255));
        lbTitle.setText(org.openide.util.NbBundle.getMessage(ProfileEvaluationMainPanel.class, "ProfileEvaluationMainPanel.lbTitle.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(lbTitle, gridBagConstraints);

        lbDescription.setForeground(new java.awt.Color(255, 255, 255));
        lbDescription.setText(org.openide.util.NbBundle.getMessage(ProfileEvaluationMainPanel.class, "ProfileEvaluationMainPanel.lbDescription.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(32, 0, 0, 0);
        add(lbDescription, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(filler1, gridBagConstraints);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(70, 70, 70), 1, true));
        jPanel1.setOpaque(false);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 242, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 131, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        add(jPanel1, gridBagConstraints);

        lbDisplaySelection.setFont(lbDisplaySelection.getFont().deriveFont(lbDisplaySelection.getFont().getSize()+1f));
        lbDisplaySelection.setForeground(new java.awt.Color(255, 255, 255));
        lbDisplaySelection.setText(org.openide.util.NbBundle.getMessage(ProfileEvaluationMainPanel.class, "ProfileEvaluationMainPanel.lbDisplaySelection.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 0, 0);
        add(lbDisplaySelection, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.weightx = 0.3;
        add(filler2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btStart;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbDescription;
    private javax.swing.JLabel lbDisplaySelection;
    private javax.swing.JLabel lbTitle;
    // End of variables declaration//GEN-END:variables
  }
