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
package it.tidalwave.colorimetry;

import javax.annotation.Nonnull;
import it.tidalwave.actor.MessageSupport;
import it.tidalwave.actor.annotation.Message;
import lombok.RequiredArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import static lombok.AccessLevel.PUBLIC;

/***********************************************************************************************************************
 * 
 * A message notifying a failure with Argyll.
 * 
 * @stereotype Message
 * 
 * @author  Fabrizio Giudici
 * @version $Id$
 *
 **********************************************************************************************************************/
@Message @Getter @RequiredArgsConstructor(access=PUBLIC) @ToString
public class ColorimeterFailureMessage extends MessageSupport
  {
    @Nonnull
    private final Throwable cause;
  }
