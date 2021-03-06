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
package it.tidalwave.uniformity.ui.impl.main;

import javax.annotation.Nonnull;
import java.util.Locale;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import it.tidalwave.role.Displayable;
import it.tidalwave.uniformity.UniformityMeasurements;
import lombok.RequiredArgsConstructor;

/***********************************************************************************************************************
 * 
 * @author  Fabrizio Giudici
 * @version $Id$
 *
 **********************************************************************************************************************/
@RequiredArgsConstructor
class DateTimeDisplayable implements Displayable
  {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormat.shortDateTime().withLocale(Locale.ITALY);

    @Nonnull
    private final UniformityMeasurements measurements;
    
    @Override @Nonnull
    public String getDisplayName() 
      {
        return DATE_FORMAT.print(measurements.getDateTime());
      }
  }

