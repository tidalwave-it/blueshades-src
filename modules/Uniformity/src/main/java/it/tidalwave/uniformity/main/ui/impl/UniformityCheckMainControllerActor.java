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
package it.tidalwave.uniformity.main.ui.impl;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import it.tidalwave.actor.annotation.Actor;
import it.tidalwave.netbeans.util.Locator;
import it.tidalwave.uniformity.UniformityCheckRequest;
import it.tidalwave.uniformity.main.ui.UniformityCheckMainPresentation;
import it.tidalwave.uniformity.main.ui.UniformityCheckMainPresentationProvider;

/***********************************************************************************************************************
 * 
 * @stereotype Controller
 * @stereotype Actor
 * 
 * @author  Fabrizio Giudici
 * @version $Id$
 *
 **********************************************************************************************************************/
@Actor(threadSafe=false)
public class UniformityCheckMainControllerActor
  {
    private final UniformityCheckMainPresentationProvider presentationBuilder = Locator.find(UniformityCheckMainPresentationProvider.class);
    
    private UniformityCheckMainPresentation presentation;
    
    /*******************************************************************************************************************
     * 
     *
     ******************************************************************************************************************/
    /* package */ final Action startAction = new AbstractAction("Start measurement") 
      {
        @Override
        public void actionPerformed (final @Nonnull ActionEvent event) 
          {
            setEnabled(false);
            new UniformityCheckRequest().send();
          }
      };
    
    /*******************************************************************************************************************
     * 
     *
     ******************************************************************************************************************/
    @PostConstruct
    public void initialize()
      {
        presentation = presentationBuilder.getPresentation();
        presentation.bind(startAction);  
      }
  }