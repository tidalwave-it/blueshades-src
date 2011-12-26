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
package it.tidalwave.uniformity.ui.impl;

import javax.annotation.Nonnull;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.Action;
import it.tidalwave.argyll.impl.MessageVerifier;
import it.tidalwave.netbeans.util.test.MockLookup;
import it.tidalwave.uniformity.ui.UniformityCheckPresentation;
import it.tidalwave.uniformity.ui.UniformityCheckPresentation.Position;
import it.tidalwave.uniformity.ui.UniformityCheckPresentationProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import static org.mockito.Mockito.*;

/***********************************************************************************************************************
 * 
 * @author  Fabrizio Giudici
 * @version $Id$
 *
 **********************************************************************************************************************/
@Slf4j
public class UniformityCheckControllerActorTest extends UniformityCheckControllerActorTestSupport
  {
    private Action continueAction;
    
    /*******************************************************************************************************************
     * 
     *
     ******************************************************************************************************************/
    private final Answer<Void> clickContinue = new Answer<Void>()
      {
        @Override
        public Void answer (final @Nonnull InvocationOnMock invocation) 
          {
            new Timer().schedule(new TimerTask() 
              {
                @Override
                public void run() 
                  {
                    log.info("Clicking on 'Continue'...");
                    continueAction.actionPerformed(null);
                  }
              }, 1000);

            return null;
          }
      };

    /*******************************************************************************************************************
     * 
     *
     ******************************************************************************************************************/
    private final Answer<Void> storeActionReferences = new Answer<Void>()
      {
        @Override
        public Void answer (final @Nonnull InvocationOnMock invocation) 
          {
            continueAction = (Action)invocation.getArguments()[0];
            return null;
          }
      };

    /*******************************************************************************************************************
     * 
     *
     ******************************************************************************************************************/
    @BeforeMethod
    public void setupFixture()
      {
        messageVerifier = new MessageVerifier();
        messageVerifier.initialize();
        
        createPresentation();
        MockLookup.setInstances(presentationBuilder);
        
        inOrder = inOrder(presentation);
        
        testActivator = new TestActivator();
        testActivator.activate();
      }
    
    /*******************************************************************************************************************
     * 
     *
     ******************************************************************************************************************/
    @Override
    protected void createPresentation()
      {
        presentation = mock(UniformityCheckPresentation.class);
        doAnswer(storeActionReferences).when(presentation).bind(any(Action.class), any(Action.class));
        doAnswer(clickContinue).when(presentation).renderSensorPlacementInvitationCellAt(any(Position.class));
        presentationBuilder = mock(UniformityCheckPresentationProvider.class);
        doReturn(presentation).when(presentationBuilder).getPresentation();
      }
    
    /*******************************************************************************************************************
     * 
     *
     ******************************************************************************************************************/
    @AfterMethod
    public void cleanup()
      {
        messageVerifier.dispose();
        testActivator.deactivate();
        messageVerifier = null;
        presentation = null;
        testActivator = null;
        continueAction = null;
        MockLookup.reset();
      }
    
    /*******************************************************************************************************************
     * 
     *
     ******************************************************************************************************************/
    @Test
    public void must_follow_the_proper_sequence_3x3() 
      throws InterruptedException
      {
        xxx();
      }
  }
