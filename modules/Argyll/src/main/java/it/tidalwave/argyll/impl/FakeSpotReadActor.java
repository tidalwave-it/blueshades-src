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
package it.tidalwave.argyll.impl;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.Random;
import it.tidalwave.actor.annotation.Actor;
import it.tidalwave.actor.annotation.MessageListener;
import it.tidalwave.colorimetry.ColorPoints;
import it.tidalwave.colorimetry.ColorTemperature;
import it.tidalwave.colorimetry.MeasureWithPrecision;
import it.tidalwave.argyll.MeasurementMessage;
import it.tidalwave.argyll.MeasurementRequest;
import lombok.extern.slf4j.Slf4j;
import static it.tidalwave.colorimetry.ColorPoint.*;
import static it.tidalwave.colorimetry.ColorTemperature.*;
import static it.tidalwave.colorimetry.MeasureWithPrecision.*;

/***********************************************************************************************************************
 * 
 * @stereotype Actor
 * 
 * @author  Fabrizio Giudici
 * @version $Id$
 *
 **********************************************************************************************************************/
@Actor(threadSafe=false) @NotThreadSafe @Slf4j
public class FakeSpotReadActor 
  {
    // TODO: implement a message that allows to change the seed, for testing.
    private final Random r = new Random(423526923857L);
    
    /*******************************************************************************************************************
     * 
     * Answers to the request for a measurement.
     * 
     ******************************************************************************************************************/
    @MessageListener
    public void spotRead (final @Nonnull MeasurementRequest message)
      throws InterruptedException
      {
        log.info("spotRead({})", message);

        Thread.sleep(500);
        
        final ColorPoints colorPoints = new ColorPoints(colorLab(r.nextDouble() * 100, r.nextDouble() * 100, r.nextDouble() * 100), 
                                                        colorXYZ(r.nextDouble() * 100, r.nextDouble() * 100, r.nextDouble() * 100));
        
        final MeasureWithPrecision<ColorTemperature> ccTemp        = measureWithPrecision(kelvin(2000 + r.nextInt(6000)), r.nextDouble() * 10);
        final MeasureWithPrecision<ColorTemperature> planckianTemp = measureWithPrecision(kelvin(2000 + r.nextInt(6000)), r.nextDouble() * 10);
        final MeasureWithPrecision<ColorTemperature> daylightTemp  = measureWithPrecision(kelvin(2000 + r.nextInt(6000)), r.nextDouble() * 10);
        
        new MeasurementMessage(colorPoints, ccTemp, planckianTemp, daylightTemp).send();
      }
  }