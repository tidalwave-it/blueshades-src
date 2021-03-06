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
package it.tidalwave.argyll.impl;

import java.util.Arrays;
import it.tidalwave.actor.Collaboration;
import it.tidalwave.actor.spi.ActorGroupActivator;
import it.tidalwave.colorimetry.message.DisplayDiscoveryMessage;
import it.tidalwave.colorimetry.message.DisplayDiscoveryQueryMessage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.mockito.internal.matchers.Equals;
import it.tidalwave.actor.test.MessageVerifier;

/***********************************************************************************************************************
 * 
 * @author  Fabrizio Giudici
 * @version $Id$
 *
 **********************************************************************************************************************/
public class DispwinActorTest 
  {
    private MessageVerifier messages;
    
    private ActorGroupActivator activator;
    
    @BeforeMethod
    public void setupFixture()
      {
        activator = new ArgyllActivator();
        activator.activate();   
        messages = new MessageVerifier();
        messages.initialize();
      }
    
    @AfterMethod
    public void disposeFixture()
      {
        activator.deactivate();
        activator = null;
        messages.dispose();
        messages = null;
      }
    
    @Test
    public void must_discover_displays() 
      throws InterruptedException
      {
        final Collaboration collaboration = new DisplayDiscoveryQueryMessage().send();
        collaboration.waitForCompletion();
        Thread.sleep(1000); // FIXME: to receive CollaborationCompleted
        
        messages.verifyCollaborationStarted();
        messages.verify(DisplayDiscoveryQueryMessage.class);
        messages.verify(DisplayDiscoveryMessage.class).with("displayNames", new Equals(Arrays.asList("SwitchResX4 - Color LCD")));
        messages.verifyCollaborationCompleted();
      }
  }
