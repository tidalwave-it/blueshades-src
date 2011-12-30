/***********************************************************************************************************************
 *
 * blueArgyle - a Java UI for Argyll
 * Copyright (C) 2011-2011 by Tidalwave s.a.s. (http://www.tidalwave.it)
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
package it.tidalwave.uniformity.ui.impl.main.netbeans;

import javax.annotation.Nonnull;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.Action;
import javax.swing.JPanel;
import org.openide.nodes.Node;
import org.openide.explorer.view.ListView;
import it.tidalwave.role.ui.PresentationModel;
import it.tidalwave.swing.ActionAdapter;
import it.tidalwave.swing.RadioButtonsSelector;
import it.tidalwave.netbeans.SimpleExplorerPanel;
import it.tidalwave.uniformity.ui.main.UniformityCheckMainPresentation;
import it.tidalwave.blueargyle.util.MutableProperty;

/***********************************************************************************************************************
 * 
 * @stereotype Presentation
 * 
 * @author  Fabrizio Giudici
 * @version $Id$
 *
 **********************************************************************************************************************/
public class UniformityCheckMainPanel extends JPanel implements UniformityCheckMainPresentation
  {
    public static final String PROP_SELECTED_MEASURE = "selectedMeasure";
    
    private final UniformityMeasurementsPanel measurementsPanel = new UniformityMeasurementsPanel();
 
    private final RadioButtonsSelector radioButtonsSelector;
    
    private final ActionAdapter startAction = new ActionAdapter();
    
    private final SimpleExplorerPanel epDisplays = new SimpleExplorerPanel(new ListView());
    
    private final SimpleExplorerPanel epMeasurementsArchive = new SimpleExplorerPanel(new ListView());
    
    public UniformityCheckMainPanel() 
      {
        assert EventQueue.isDispatchThread();
        initComponents();
        radioButtonsSelector = new RadioButtonsSelector(rbLuminance, rbTemperature);
        pnInnerMeasurements.add(measurementsPanel, BorderLayout.CENTER);
        pnDisplays.add(epDisplays, BorderLayout.CENTER);
        pnArchive.add(epMeasurementsArchive, BorderLayout.CENTER);
      }

    @Override
    public void bind (final @Nonnull Action startAction, final @Nonnull MutableProperty<Integer> selectedMeasurement)
      {
        assert EventQueue.isDispatchThread();
        this.startAction.bind(startAction);
        radioButtonsSelector.bind(selectedMeasurement);
      }
    
    @Override
    public void showUp() 
      {
        assert EventQueue.isDispatchThread();
      }

    @Override
    public void dismiss()
      {
        assert EventQueue.isDispatchThread();
      }
    
    @Override
    public void renderMeasurements (final @Nonnull String[][] measurements)
      {
        assert EventQueue.isDispatchThread();
        measurementsPanel.renderMeasurements(measurements);  
      }
    
    @Override
    public void populateDisplays (final @Nonnull PresentationModel presentationModel)
      {
        assert EventQueue.isDispatchThread();
        epDisplays.getExplorerManager().setRootContext((Node)presentationModel);
      }
    
    @Override
    public void populateMeasurementsArchive (final @Nonnull PresentationModel presentationModel)
      {  
        assert EventQueue.isDispatchThread();
        epMeasurementsArchive.getExplorerManager().setRootContext((Node)presentationModel);
      }
    
    @Override
    public void showWaitingOnDisplayList() 
      {
        assert EventQueue.isDispatchThread();
        // FIXME: TODO
      }

    @Override
    public void showWaitingOnMeasurementsArchive() 
      {
        // FIXME: TODO
      } 

    @Override
    public void hideWaitingOnDisplayList() 
      {
        // FIXME: TODO
      }

    @Override
    public void hideWaitingOnMeasurementsArchive() 
      {
        // FIXME: TODO
      }
    
    @Override
    public void removeNotify()
      {
        assert EventQueue.isDispatchThread();
        startAction.unbind();
        radioButtonsSelector.unbind();
        super.removeNotify();
      } 
    
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgMeasurement = new javax.swing.ButtonGroup();
        pnMeasurements = new javax.swing.JPanel();
        pnInnerMeasurements = new javax.swing.JPanel();
        rbLuminance = new javax.swing.JRadioButton();
        rbTemperature = new javax.swing.JRadioButton();
        pnDisplays = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btStart = new javax.swing.JButton();
        pnArchive = new javax.swing.JPanel();

        setName(org.openide.util.NbBundle.getMessage(UniformityCheckMainPanel.class, "UniformityCheckMainPanel.name")); // NOI18N

        pnMeasurements.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(UniformityCheckMainPanel.class, "UniformityCheckMainPanel.pnMeasurements.border.outsideBorder.title")), javax.swing.BorderFactory.createEmptyBorder(8, 8, 8, 8))); // NOI18N
        pnMeasurements.setName(org.openide.util.NbBundle.getMessage(UniformityCheckMainPanel.class, "UniformityCheckMainPanel.pnMeasurements.name")); // NOI18N

        pnInnerMeasurements.setName(org.openide.util.NbBundle.getMessage(UniformityCheckMainPanel.class, "UniformityCheckMainPanel.pnInnerMeasurements.name")); // NOI18N
        pnInnerMeasurements.setLayout(new java.awt.BorderLayout());

        bgMeasurement.add(rbLuminance);
        rbLuminance.setText(org.openide.util.NbBundle.getMessage(UniformityCheckMainPanel.class, "UniformityCheckMainPanel.rbLuminance.text")); // NOI18N
        rbLuminance.setName(org.openide.util.NbBundle.getMessage(UniformityCheckMainPanel.class, "UniformityCheckMainPanel.rbLuminance.name")); // NOI18N

        bgMeasurement.add(rbTemperature);
        rbTemperature.setText(org.openide.util.NbBundle.getMessage(UniformityCheckMainPanel.class, "UniformityCheckMainPanel.rbTemperature.text")); // NOI18N
        rbTemperature.setName(org.openide.util.NbBundle.getMessage(UniformityCheckMainPanel.class, "UniformityCheckMainPanel.rbTemperature.name")); // NOI18N

        javax.swing.GroupLayout pnMeasurementsLayout = new javax.swing.GroupLayout(pnMeasurements);
        pnMeasurements.setLayout(pnMeasurementsLayout);
        pnMeasurementsLayout.setHorizontalGroup(
            pnMeasurementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnMeasurementsLayout.createSequentialGroup()
                .addGap(0, 282, Short.MAX_VALUE)
                .addComponent(rbLuminance)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbTemperature))
            .addGroup(pnMeasurementsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnInnerMeasurements, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnMeasurementsLayout.setVerticalGroup(
            pnMeasurementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnMeasurementsLayout.createSequentialGroup()
                .addGroup(pnMeasurementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbTemperature)
                    .addComponent(rbLuminance))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnInnerMeasurements, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnDisplays.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(UniformityCheckMainPanel.class, "UniformityCheckMainPanel.pnDisplays.border.title"))); // NOI18N
        pnDisplays.setName(org.openide.util.NbBundle.getMessage(UniformityCheckMainPanel.class, "UniformityCheckMainPanel.pnDisplays.name")); // NOI18N
        pnDisplays.setLayout(new java.awt.BorderLayout());

        jPanel3.setName(org.openide.util.NbBundle.getMessage(UniformityCheckMainPanel.class, "UniformityCheckMainPanel.jPanel3.name")); // NOI18N

        btStart.setAction(startAction);
        btStart.setText(org.openide.util.NbBundle.getMessage(UniformityCheckMainPanel.class, "UniformityCheckMainPanel.btStart.text")); // NOI18N
        btStart.setName(org.openide.util.NbBundle.getMessage(UniformityCheckMainPanel.class, "UniformityCheckMainPanel.btStart.name")); // NOI18N
        jPanel3.add(btStart);

        pnArchive.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(UniformityCheckMainPanel.class, "UniformityCheckMainPanel.pnArchive.border.title"))); // NOI18N
        pnArchive.setName(org.openide.util.NbBundle.getMessage(UniformityCheckMainPanel.class, "UniformityCheckMainPanel.pnArchive.name")); // NOI18N
        pnArchive.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnArchive, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnDisplays, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnMeasurements, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {pnArchive, pnDisplays});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnMeasurements, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnDisplays, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnArchive, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgMeasurement;
    private javax.swing.JButton btStart;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel pnArchive;
    private javax.swing.JPanel pnDisplays;
    private javax.swing.JPanel pnInnerMeasurements;
    private javax.swing.JPanel pnMeasurements;
    private javax.swing.JRadioButton rbLuminance;
    private javax.swing.JRadioButton rbTemperature;
    // End of variables declaration//GEN-END:variables
  }
