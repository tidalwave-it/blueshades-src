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
package it.tidalwave.blueshades.profileevaluation.ui.impl.sequence;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import javax.swing.Action;
import it.tidalwave.messagebus.annotation.ListensTo;
import it.tidalwave.actor.MessageSupport;
import it.tidalwave.actor.annotation.Actor;
import it.tidalwave.actor.annotation.Message;
import it.tidalwave.netbeans.util.Locator;
import it.tidalwave.swing.ActionMessageAdapter;
import it.tidalwave.blueshades.profileevaluation.ProfileEvaluationRequest;
import it.tidalwave.blueshades.profileevaluation.ui.sequence.SequenceStepDescriptor;
import it.tidalwave.blueshades.profileevaluation.ui.sequence.HiKeyDescriptor;
import it.tidalwave.blueshades.profileevaluation.ui.sequence.LoKeyDescriptor;
import it.tidalwave.blueshades.profileevaluation.ui.sequence.GrangerRainbowDescriptor;
import it.tidalwave.blueshades.profileevaluation.ui.sequence.ProfileEvaluationSequencePresentation;
import it.tidalwave.blueshades.profileevaluation.ui.sequence.ProfileEvaluationSequencePresentationProvider;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/***********************************************************************************************************************
 * 
 * @stereotype Controller
 * @stereotype Actor
 * 
 * @author  Fabrizio Giudici
 * @version $Id$
 *
 **********************************************************************************************************************/
@Actor(threadSafe=false) @NotThreadSafe @Slf4j
public class ProfileEvaluationSequenceControllerActor
  {
    @Message(outOfBand=true) @ToString
    private static class NextMessage extends MessageSupport
      {
      }
    
    @Message(outOfBand=true) @ToString
    private static class PreviousMessage extends MessageSupport
      {
      }
    
    // FIXME: use @Inject
    private final ProfileEvaluationSequencePresentationProvider presentationProvider = Locator.find(ProfileEvaluationSequencePresentationProvider.class);
    
    /** The presentation controlled by this class */
    private ProfileEvaluationSequencePresentation presentation;
    
    private final Action nextAction = new ActionMessageAdapter("Next", new NextMessage()); 
    
    private final Action previousAction = new ActionMessageAdapter("Previous", new PreviousMessage()); 
    
    private final List<? extends SequenceStepDescriptor> stepSequence = Collections.unmodifiableList(Arrays.asList
      (
        new HiKeyDescriptor(),
        new LoKeyDescriptor(),
        new GrangerRainbowDescriptor()
      ));
    
    private ListIterator<? extends SequenceStepDescriptor> currentStep;
    
    /*******************************************************************************************************************
     * 
     *
     ******************************************************************************************************************/
    @PostConstruct
    public void initialize()
      {
        log.info("initialize()");
        presentation = presentationProvider.getPresentation();
        presentation.bind(nextAction, previousAction);    
      }
    
    /*******************************************************************************************************************
     * 
     *
     ******************************************************************************************************************/
    public void onProfileEvaluationRequest (final @Nonnull @ListensTo ProfileEvaluationRequest message)
      {
        log.info("onProfileEvaluationRequest({})", message);
        presentation.showUp(message.getDisplay());
        currentStep = stepSequence.listIterator();
        presentation.renderEvaluationStep(currentStep.next());
        presentation.renderProfileName(message.getDisplay().getProfile().getName());
      }
    
    /*******************************************************************************************************************
     * 
     *
     ******************************************************************************************************************/
    private void onNextMessage (final @Nonnull @ListensTo NextMessage message)
      {
        log.info("onNextMessage({})", message);
        
        if (currentStep.hasNext())
          {
            presentation.renderEvaluationStep(currentStep.next());  
          }
        else
          {
            presentation.dismiss();  
          }
      }
    
    /*******************************************************************************************************************
     * 
     *
     ******************************************************************************************************************/
    private void onPreviousMessage (final @Nonnull @ListensTo PreviousMessage message)
      {
        log.info("onPreviousMessage({})", message);
        
        if (currentStep.hasPrevious())
          {
            presentation.renderEvaluationStep(currentStep.previous());  
          }
        else
          {
            presentation.dismiss();  
          }
      }
  }
