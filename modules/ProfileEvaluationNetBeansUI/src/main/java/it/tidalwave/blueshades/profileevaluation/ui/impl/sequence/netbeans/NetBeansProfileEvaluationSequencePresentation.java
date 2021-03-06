/***********************************************************************************************************************
 *
 * blueShades - a Java UI for Argyll
 * Copyright (C) 2011-2016 by Tidalwave s.a.s. (http://www.tidalwave.it)
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
 * WWW: http://blueshades.tidalwave.it
 * SCM: https://bitbucket.org/tidalwave/blueshades-src
 *
 **********************************************************************************************************************/
package it.tidalwave.blueshades.profileevaluation.ui.impl.sequence.netbeans;

import javax.annotation.Nonnull;
import java.awt.EventQueue;
import javax.swing.JFrame;
import it.tidalwave.colorimetry.ProfiledDisplay;
import it.tidalwave.blueshades.profileevaluation.ui.sequence.ProfileEvaluationSequencePresentation;
import lombok.Delegate;

/***********************************************************************************************************************
 *
 * @author  Fabrizio Giudici
 * @version $Id$
 *
 **********************************************************************************************************************/
public class NetBeansProfileEvaluationSequencePresentation implements ProfileEvaluationSequencePresentation
  {
    private static interface DelegateExclusions
      {
        public void showUp (ProfiledDisplay profiledDisplay);
        public void dismiss();    
      }
    
    protected final JFrame frame = new JFrame();
    
    @Delegate(types=ProfileEvaluationSequencePresentation.class, excludes=DelegateExclusions.class)
    protected final ProfileEvaluationSequencePanel panel = new ProfileEvaluationSequencePanel();
    
    /*******************************************************************************************************************
     * 
     *
     ******************************************************************************************************************/
    public NetBeansProfileEvaluationSequencePresentation()
      {
        assert EventQueue.isDispatchThread();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true); 
        frame.add(panel);
      }
    
    /*******************************************************************************************************************
     * 
     *
     ******************************************************************************************************************/
    @Override
    public void showUp (final @Nonnull ProfiledDisplay profiledDisplay)
      {
        assert EventQueue.isDispatchThread();
        panel.showUp(profiledDisplay);
        profiledDisplay.getDisplay().getGraphicsDevice().setFullScreenWindow(frame);
        frame.setVisible(true);
      }
    
    /*******************************************************************************************************************
     * 
     *
     ******************************************************************************************************************/
    @Override
    public void dismiss()
      {
        assert EventQueue.isDispatchThread();
        frame.setVisible(false);
        frame.dispose();
        panel.dismiss();
      }
  }
