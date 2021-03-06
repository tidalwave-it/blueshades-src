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
import javax.annotation.concurrent.Immutable;
import it.tidalwave.role.spi.DefaultDisplayable;
import it.tidalwave.netbeans.util.AsLookupSupport;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/***********************************************************************************************************************
 *
 * @author  Fabrizio Giudici
 * @version $Id$
 *
 **********************************************************************************************************************/
@Immutable @Getter @EqualsAndHashCode(callSuper=false) @ToString(callSuper=false) 
public class ProfiledDisplay extends AsLookupSupport
  {
    @Nonnull
    private final Display display;
    
    @Nonnull
    private final Profile profile;

    public ProfiledDisplay (final @Nonnull Display display, final @Nonnull Profile profile) 
      {
        super(new Object[] { new DefaultDisplayable(display.getDisplayName(), display.getDisplayName()) });  
        this.display = display;
        this.profile = profile;
      }
  }
