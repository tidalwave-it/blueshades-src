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

import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle;
import it.tidalwave.actor.netbeans.ActorTopComponent;
import it.tidalwave.blueargyle.profileevaluation.ui.ProfileEvaluationActorActivator;
import static org.openide.windows.TopComponent.*;

/***********************************************************************************************************************
 * 
 * This {@link TopComponent} acts as both a Presentation, holding the display estate, and as an Adapter, activating
 * and deactivating the Controller Actor when the NetBeans Platform opens and closes it.
 * 
 * @stereotype Presentation
 * @stereotype Adapter
 * 
 * @author  Fabrizio Giudici
 * @version $Id$
 *
 **********************************************************************************************************************/
@TopComponent.Description(preferredID = "ProfileEvaluationMainTopComponent", 
                          iconBase="it/tidalwave/blueargyle/profileevaluation/ui/impl/main/netbeans/Evaluation.png",
                          persistenceType = PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Tools", id = "it.tidalwave.blueargyle.profileevaluation.ui.impl.main.netbeans.ProfileEvaluationMainTopComponent")
@ActionReferences(value =
  {
    @ActionReference(path = "Menu/Tools", position = 2500),
    @ActionReference(path = "Toolbars/Standard", position = 2500)
  })
@TopComponent.OpenActionRegistration(displayName = "#CTL_ProfileEvaluationAction", preferredID = "ProfileEvaluationMainTopComponent")
public final class ProfileEvaluationMainTopComponent extends ActorTopComponent<ProfileEvaluationMainPanel>
  {
    public ProfileEvaluationMainTopComponent() 
      {
        super(ProfileEvaluationActorActivator.class, ProfileEvaluationMainPanel.class);
        setName(NbBundle.getMessage(ProfileEvaluationMainTopComponent.class, "CTL_ProfileEvaluationMainTopComponent"));
        putClientProperty(PROP_DRAGGING_DISABLED, true);
        putClientProperty(PROP_MAXIMIZATION_DISABLED, true);
        putClientProperty(PROP_UNDOCKING_DISABLED, true);
      }
  }