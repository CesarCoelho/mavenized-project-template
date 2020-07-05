/*
 * Copyright (c) 2020 Cesar Coelho
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 */
package com.cesar.coelho.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cesar Coelho
 */
public class ShellCommander {

    private final static int DEATH_TIMEOUT = 2000;
    
    public ShellCommander(){
    }

    public Process runCommand(String cmd) {
        return this.runCommand(cmd, null);
    }

    public String runCommandAndGetOutputMessage(String cmd) {
        try {
            Process proc = this.runCommand(cmd, null);
            StreamWrapper error = new StreamWrapper(proc.getErrorStream(), "ERROR");
            StreamWrapper output = new StreamWrapper(proc.getInputStream(), "OUTPUT");
            error.start();
            output.start();
            
            error.join(DEATH_TIMEOUT);
            output.join(DEATH_TIMEOUT);
            proc.destroy();
//            String out = "Output:\n" + output.getMessage() + "\nError:\n" + error.getMessage();
            
            return output.getMessage();
        } catch (InterruptedException ex) {
            Logger.getLogger(ShellCommander.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "";
    }
   
    public Process runCommand(String cmd, File dirPath) {
        try {
            OSValidator osValidator = new OSValidator();
            Process proc;

            if (osValidator.isLinux()) {
                proc = Runtime.getRuntime().exec(new String[]{"bash", "-c", cmd}, null, dirPath);
            } else if (osValidator.isWindows()) {
                proc = Runtime.getRuntime().exec(new String[]{"cmd", "/c", cmd}, null, dirPath);
            } else {
                Logger.getLogger(ShellCommander.class.getName()).log(Level.SEVERE, "Unknown OS");
                return null;
            }

            return proc;
        } catch (IOException ex) {
            Logger.getLogger(ShellCommander.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private class StreamWrapper extends Thread {

        private InputStream is = null;
        private String type = null;
        private String message = "<Nothing>";

        public String getMessage() {
            return message;
        }

        StreamWrapper(InputStream is, String type) {
            this.is = is;
            this.type = type;
        }

        private String getOutput() {
            return message;
        }

        @Override
        public void run() {
            this.setName("ShellCommander_StreamWrapper");
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder buffer = new StringBuilder();
                String line = null;
                while ((line = br.readLine()) != null) {
                    buffer.append(line);
                    buffer.append("\n");
                }
                message = buffer.toString();
            } catch (IOException ioe) {
                Logger.getLogger(ShellCommander.class.getName()).log(Level.INFO, "Error: " + ioe);
            }
        }
    }

}