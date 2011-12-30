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
package it.tidalwave.uniformity.archive.impl;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;
import it.tidalwave.uniformity.UniformityMeasurements;
import java.util.HashSet;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/***********************************************************************************************************************
 * 
 * @author  Fabrizio Giudici
 * @version $Id$
 *
 **********************************************************************************************************************/
@EqualsAndHashCode @ToString
public class UniformityArchive 
  {
    private final Set<UniformityMeasurements> archive = new HashSet<UniformityMeasurements>();

    public void add (final @Nonnull UniformityMeasurements measurements) 
      {
        archive.add(measurements);
      }

    @Nonnull
    public List<UniformityMeasurements> getAll() 
      {
        return new CopyOnWriteArrayList<UniformityMeasurements>(archive);   
      }
    
    public void clear() 
      {
        archive.clear();
      }
    
    @Nonnegative
    public int getSize()
      {
        return archive.size();  
      }
  }
