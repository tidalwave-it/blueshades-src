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
package it.tidalwave.uniformity.archive.impl.io;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.EOFException;
import java.io.InputStream;
import java.io.IOException;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import it.tidalwave.role.Unmarshallable;
import it.tidalwave.colorimetry.ColorTemperature;
import it.tidalwave.colorimetry.Display;
import it.tidalwave.colorimetry.Profile;
import it.tidalwave.colorimetry.ProfiledDisplay;
import it.tidalwave.uniformity.Position;
import it.tidalwave.uniformity.UniformityMeasurement;
import it.tidalwave.uniformity.UniformityMeasurements;
import static java.lang.Integer.parseInt;
import static it.tidalwave.colorimetry.ColorTemperature.kelvin;
import static it.tidalwave.uniformity.Position.xy;

/***********************************************************************************************************************
 * 
 * @author  Fabrizio Giudici
 * @version $Id$
 *
 **********************************************************************************************************************/
public class UniformityMeasurementsUnmarshallable implements Unmarshallable
  {
    private static final Pattern PATTERN_DISPLAY_NAME = Pattern.compile("D='([^']*)'");
    private static final Pattern PATTERN_PROFILE_NAME = Pattern.compile("P='([^']*)'");
    private static final Pattern PATTERN_LUMINANCE = Pattern.compile("L\\[([0-9]*),([0-9]*)\\]= *([0-9]*)");
    private static final Pattern PATTERN_TEMPERATURE = Pattern.compile("T\\[([0-9]*),([0-9]*)\\]= *([0-9]*)");
    
    /*******************************************************************************************************************
     * 
     *
     * 
     ******************************************************************************************************************/
    @Override
    public UniformityMeasurements unmarshal (final @Nonnull InputStream is) 
      throws IOException
      {  
        final String s = readLine(is);
        
        if ("".equals(s))
          {
            throw new EOFException();  
          }
        
        final Scanner scanner = new Scanner(s.trim()).useDelimiter(";");
        final DateTime dateTime = ISODateTimeFormat.dateTimeNoMillis().parseDateTime(scanner.next().trim());
        
        String displayName = "";
        String profileName = "";
        int luminance = 0;
        final Map<Position, UniformityMeasurement> map = new TreeMap<Position, UniformityMeasurement>();
        
        while (scanner.hasNext())
          {  
            final String t = scanner.next().trim();
            
            final Matcher displayNameMatcher = PATTERN_DISPLAY_NAME.matcher(t);
            
            if (displayNameMatcher.matches())
              {
                displayName = displayNameMatcher.group(1);
                continue;
              }
            
            final Matcher profileNameMatcher = PATTERN_PROFILE_NAME.matcher(t);
            
            if (profileNameMatcher.matches())
              {
                profileName = profileNameMatcher.group(1);
                continue;
              }
            
            final Matcher luminanceMatcher = PATTERN_LUMINANCE.matcher(t);
            
            if (luminanceMatcher.matches())
              {
//                final Position xy = xy(parseInt(luminanceMatcher.group(1)), parseInt(luminanceMatcher.group(2)));
                luminance = Integer.parseInt(luminanceMatcher.group(3));
                continue;
              }
            
            final Matcher temperatureMatcher = PATTERN_TEMPERATURE.matcher(t);
            
            if (temperatureMatcher.matches())
              {
                final Position xy = xy(parseInt(temperatureMatcher.group(1)), parseInt(temperatureMatcher.group(2)));
                final ColorTemperature temperature = kelvin(parseInt(temperatureMatcher.group(3)));
                map.put(xy, new UniformityMeasurement(temperature, luminance));
                continue;
              }
          }

        return new UniformityMeasurements(new ProfiledDisplay(new Display(displayName, -1), new Profile(profileName)), dateTime, map);
      }   
    
    /*******************************************************************************************************************
     * 
     *
     * 
     ******************************************************************************************************************/
    @Nonnull
    private String readLine (final @Nonnull InputStream is) 
      throws IOException
      {
        final StringBuilder buffer = new StringBuilder();
        
        for (;;)
          {
            final int c = is.read();
            
            if (c < 0)
              {
                break;    
              }     
            
            if (c == '\n')
              {
                break;    
              }     
            
            buffer.append((char)c);
          }
        
        return buffer.toString();
      }
  } 
