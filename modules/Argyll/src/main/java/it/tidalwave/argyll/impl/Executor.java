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

import java.io.*;
import java.util.Map.Entry;
import java.util.*;
import java.util.concurrent.Executors;
import javax.annotation.Nonnull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.concurrent.ThreadSafe;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/***********************************************************************************************************************
 * 
 * @author  Fabrizio Giudici
 * @version $Id$
 *
 **********************************************************************************************************************/
@ThreadSafe @NoArgsConstructor(access=AccessLevel.PRIVATE) @Slf4j
public class Executor 
  {
    @RequiredArgsConstructor(access=AccessLevel.PACKAGE)
    public class ConsoleOutput
      {
        @Nonnull
        private final InputStream input;
        
        @Getter
        private final List<String> content = new ArrayList<String>();
        
        @Nonnull
        public ConsoleOutput start()
          {
            Executors.newSingleThreadExecutor().submit(runnable); 
            return this;
          }
        
        private final Runnable runnable = new Runnable() 
          {
            @Override
            public void run() 
              {
                try
                  {
                    read();  
                  }
                catch (IOException e)
                  {
                    e.printStackTrace(); // FIXME  
                  }
              }
          };
        
        @Nonnull
        public List<String> filteredBy (final @Nonnull String filter)
          {
            final Pattern p = Pattern.compile(filter);
            final List<String> result = new ArrayList<String>();

            for (final String s : content)
              {
                final Matcher m = p.matcher(s);
                
                if (m.matches())
                  {
                    result.add(m.group(1));  
                  }
              }
            
            return result;
          }
    
        private void read()
          throws IOException
          {
            final @Cleanup BufferedReader br = new BufferedReader(new InputStreamReader(input));

            for (;;)
              {
                final String s = br.readLine();

                if (s == null)
                  { 
                    break;   
                  }  

                log.info(">>>>>>>> {}", s);
                content.add(s);  
              }

            br.close();
          }
      }
    
    private final static String argyllPath = "/Users/fritz/Applications/Argyll_V1.3.5/bin";
    
    private final List<String> arguments = new ArrayList<String>();

    private Process process;
    
    @Getter
    private ConsoleOutput stdout;
    
    @Getter
    private ConsoleOutput stderr;
    
    private PrintWriter stdin;
                
    @Nonnull
    public static Executor forExecutable (final @Nonnull String executable)
      {
        final Executor executor = new Executor();
        executor.arguments.add(argyllPath + File.separator + executable);
        return executor;
      }
    
    @Nonnull
    public Executor withArgument (final @Nonnull String argument)
      {
        arguments.add(argument);
        return this;
      }
    
    @Nonnull
    public Executor execute()
      throws IOException
      {
        log.info(">>>> executing {} ...", arguments);
        
        final List<String> environment = new ArrayList<String>();
        
//        for (final Entry<String, String> e : System.getenv().entrySet())
//          {
//            environment.add(String.format("%s=%s", e.getKey(), e.getValue()));  
//          }
        
        environment.add("ARGYLL_NOT_INTERACTIVE=true");
        log.info(">>>> environment: {}", environment);
        process = Runtime.getRuntime().exec(arguments.toArray(new String[0]),
                                            environment.toArray(new String[0]));
        stdout = new ConsoleOutput(process.getInputStream()).start();
        stderr = new ConsoleOutput(process.getErrorStream()).start();
        stdin  = new PrintWriter(process.getOutputStream(), true);
        
        return this;
      }

    @Nonnull
    public Executor waitFor()
      throws IOException, InterruptedException
      {
        process.waitFor();
        return this;
      }

    @Nonnull
    public Executor send (final @Nonnull String string) 
      throws IOException 
      {
        stdin.print(string);
        return this;
      }
  }

