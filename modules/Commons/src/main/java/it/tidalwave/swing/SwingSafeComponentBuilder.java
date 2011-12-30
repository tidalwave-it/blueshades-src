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
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.concurrent.atomic.AtomicReference;
import java.awt.EventQueue;
import it.tidalwave.swing.impl.SwingSafeProxy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import static lombok.AccessLevel.PRIVATE;

/***********************************************************************************************************************
 * 
 * @stereotype Factory
 * 
 * @author  Fabrizio Giudici
 * @version $Id$
 *
 **********************************************************************************************************************/
@RequiredArgsConstructor(access=PRIVATE) @Slf4j
public class SwingSafeComponentBuilder<T, I>
  {
    @Nonnull
    private final Class<T> componentClass;
    
    @Nonnull
    private final Class<I> interfaceClass;
    
    private WeakReference<T> presentationRef = new WeakReference<T>(null);
    
    @Nonnull
    public static <X, J> SwingSafeComponentBuilder<X, J> builderFor (final @Nonnull Class<X> componentClass,    
                                                                     final @Nonnull Class<J> interfaceClass)
      {
        return new SwingSafeComponentBuilder<X, J>(componentClass, interfaceClass);  
      }
    
    @Nonnull
    public synchronized T getInstance()
      {
        log.info("getInstance()");
        T presentation = presentationRef.get();
        
        if (presentation == null)
          {
            presentation = EventQueue.isDispatchThread() ? createComponentInstance() : createComponentInstanceInEDT();            
            presentation = (T)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), 
                                                     new Class[] { interfaceClass }, 
                                                     new SwingSafeProxy<T>(presentation));
            presentationRef = new WeakReference<T>(presentation);
          }
        
        return presentation;
      }
    
    @Nonnull
    protected T createComponentInstance()
      {  
        try 
          {
            return componentClass.newInstance();
          }
        catch (InstantiationException e)
          {
            log.error("", e);
            throw new RuntimeException(e);
          }
        catch (IllegalAccessException e) 
          {
            log.error("", e);
            throw new RuntimeException(e);
          }
      }
              
    @Nonnull
    private T createComponentInstanceInEDT()
      {
        final AtomicReference<T> reference = new AtomicReference<T>();
        
        try  
          {
            EventQueue.invokeAndWait(new Runnable() 
              {
                @Override
                public void run() 
                  {
                    reference.set(createComponentInstance());
                  }
              });
          } 
        catch (InterruptedException e)
          {
            log.error("", e);
            throw new RuntimeException(e);
          }
        catch (InvocationTargetException e) 
          {
            log.error("", e);
            throw new RuntimeException(e);
          }
        
        return reference.get();
      }
  }