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
package it.tidalwave.swing;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import lombok.RequiredArgsConstructor;

/***********************************************************************************************************************
 * 
 * @author  Fabrizio Giudici
 * @version $Id$
 *
 **********************************************************************************************************************/
@RequiredArgsConstructor
public class FixedAspectRatioLayout implements LayoutManager 
  {
    @Nonnegative
    private final double aspectRatio;
    
    @Override
    public void addLayoutComponent (final @Nonnull String string, final @Nonnull Component component)
      {
      }

    @Override
    public void removeLayoutComponent (final @Nonnull Component component) 
      {
      }

    @Override @Nonnull
    public Dimension preferredLayoutSize (final @Nonnull Container container) 
      {
        final int width = container.getWidth();
        final int height = container.getHeight();
        final double containerAspectRatio = (double)width / height;
        int componentWidth, componentHeight;
        
        if (containerAspectRatio < aspectRatio)
          {
            componentWidth = width;
            componentHeight = (int)(width / aspectRatio);
          }
        else
          {
            componentHeight = height;
            componentWidth = (int)(height * aspectRatio);
          }
        
        return new Dimension(componentWidth, componentHeight);
      }

    @Override @Nonnull
    public Dimension minimumLayoutSize (final @Nonnull Container container)
      {
        return new Dimension(0, 0);
      }

    @Override
    public void layoutContainer (final @Nonnull Container container) 
      {
        final Component component = container.getComponent(0);
        
        if (component != null)
          {
            final int width = container.getWidth();
            final int height = container.getHeight();
            final Dimension size = preferredLayoutSize(container);
            component.setLocation((width - size.width) / 2, (height - size.height) / 2);
            component.setSize(size.width, size.height);
          }
      }
  }