/***********************************************************************************************************************
 *
 * blueShades - a Java UI for Argyll
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
 * WWW: http://blueshades.tidalwave.it
 * SCM: https://bitbucket.org/tidalwave/blueshades-src
 *
 **********************************************************************************************************************/
package it.tidalwave.argyll.impl;

import org.openide.util.lookup.ServiceProvider;
import it.tidalwave.actor.spi.ActorGroupActivator;
import static it.tidalwave.actor.spi.ActorActivator.*;

/***********************************************************************************************************************
 * 
 * @author  Fabrizio Giudici
 * @version $Id$
 *
 **********************************************************************************************************************/
@ServiceProvider(service=ArgyllActivator.class)
public class ArgyllActivator extends ActorGroupActivator
  {
    public ArgyllActivator() 
      {
        add(activatorFor(DispwinActor.class).withPoolSize(1));
        
        if (!Boolean.getBoolean("it.tidalwave.blueshades.mockSensor"))
          {
            add(activatorFor(SpotReadActor.class).withPoolSize(1));
          }
      }
  }