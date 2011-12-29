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

import javax.annotation.Nonnull;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import static it.tidalwave.blueargyle.util.SwingSafeRunner.*;

/***********************************************************************************************************************
 * 
 * @author  Fabrizio Giudici
 * @version $Id$
 *
 **********************************************************************************************************************/
public class ActionAdapter extends AbstractAction
  {
    private Action delegate;
    
    private final PropertyChangeListener pcl = new PropertyChangeListener()
      {
        @Override
        public void propertyChange (final @Nonnull PropertyChangeEvent event) 
          {
            runSafely(new Runnable() 
              {
                @Override
                public void run() 
                  {
                    putValue(event.getPropertyName(), event.getNewValue());
                  }
              });
          }
      };
    
    @Override
    public void actionPerformed (final @Nonnull ActionEvent event) 
      {
        if (delegate != null)
          {
            delegate.actionPerformed(event);  
          }
      }
    
    public void bind (final @Nonnull Action delegate)
      {
        unbind();
        this.delegate = delegate;
        delegate.addPropertyChangeListener(pcl);
        
        runSafely(new Runnable() 
          {
            @Override
            public void run() 
              {
                putValue(NAME, delegate.getValue(NAME));
                // Mnemonic etc... are left as they are, since they are a Presentation responsibility
              }
          });
      }
    
    public void unbind()
      {
        if (delegate != null)
          { 
            delegate.removePropertyChangeListener(pcl);
            delegate = null;  
          }
      }
  }  
