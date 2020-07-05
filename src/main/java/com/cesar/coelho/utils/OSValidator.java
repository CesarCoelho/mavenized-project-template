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

/**
 *
 * @author Cesar Coelho
 */
public class OSValidator {

    private static final String OS_NAME_MAC = "Mac";
    private static final String OS_NAME_WIN = "Windows";
    private static final String OS_NAME_CHROME_OS = "Chrome OS";
    private static final String OS_NAME_LIN = "Linux";

    private static final String PROP_DIST = "DISTRIB_ID";
    private static final String CMD_LINUX_VERSION = "cat /etc/*-release";
    private static final String PROP_CHROME_OS = "CHROMEOS";
    private static final String PROP_CHROME_OS_VERSION = "CHROMEOS_RELEASE_VERSION";
    private final String OS_Name = System.getProperty("os.name");
    private final String OS = System.getProperty("os.name").toLowerCase();

    public OSValidator() {
    }

    public boolean isWindows() {
        return (OS.contains("win"));
    }

    public boolean isMac() {
        return (OS.contains("mac"));
    }

    public boolean isLinux() {
        return (OS.contains("nux"));
    }

    public boolean isSolaris() {
        return (OS.contains("sunos"));
    }
    
    public boolean isChromeOS(){
        if(!isLinux()){
            return false;
        }
        
        return "Chrome OS".equals(this.getOSSimpleName());
    }

    public String getOSComplexName() {
        // If it is Linux, then we need additional magic...
        if (this.isLinux()) {
            ShellCommander shell = new ShellCommander();
            String msg = shell.runCommandAndGetOutputMessage(CMD_LINUX_VERSION);
            /*
            JOptionPane.showMessageDialog(null, "The returned message is:\n" + msg,
                    "The returned message is:", JOptionPane.INFORMATION_MESSAGE);
             */

            if (msg.contains(PROP_DIST)) {
                int indexFrom = msg.indexOf(PROP_DIST);
                int indexTo = msg.indexOf("\n", indexFrom);
                String line = msg.substring(indexFrom, indexTo);
                String distro = line.substring(msg.indexOf("=") + 1); // Name
                //JOptionPane.showMessageDialog(null, "The distro is: \n" + distro, "Distro", JOptionPane.INFORMATION_MESSAGE);

                return distro;
            }

            if (msg.contains(PROP_CHROME_OS)) {
                int indexFrom = msg.indexOf(PROP_CHROME_OS_VERSION);
                int indexTo = msg.indexOf("\n", indexFrom);
                String line = msg.substring(indexFrom, indexTo);
                String distro = line.substring(msg.indexOf("=") + 1); // Name
                return distro;
                
                /*
                (termina) chronos@localhost ~ $ cat /etc/lsb-release 
                CHROMEOS_RELEASE_BUILDER_PATH=tatl-full-tryjob/R69-10739.0.0-b2622948
                GOOGLE_RELEASE=10739.0.2018_05_31_1127
                CHROMEOS_DEVSERVER=http://swarm-cros-54.c.chromeos-bot.internal:8080
                CHROMEOS_RELEASE_BOARD=tatl
                CHROMEOS_RELEASE_BUILD_NUMBER=10739
                CHROMEOS_RELEASE_BRANCH_NUMBER=0
                CHROMEOS_RELEASE_CHROME_MILESTONE=69
                CHROMEOS_RELEASE_PATCH_NUMBER=2018_05_31_1127
                CHROMEOS_RELEASE_TRACK=buildbot-build
                CHROMEOS_RELEASE_DESCRIPTION=10739.0.2018_05_31_1127 (Continuous Builder - Builder: N/A) tatl
                CHROMEOS_RELEASE_NAME=Chromium OS
                CHROMEOS_RELEASE_BUILD_TYPE=Continuous Builder - Builder: N/A
                CHROMEOS_RELEASE_VERSION=10739.0.2018_05_31_1127
                CHROMEOS_AUSERVER=http://swarm-cros-54.c.chromeos-bot.internal:8080/update
                 */
                
            }

            return "Unknown Linux distro";
        }

        return this.OS_Name;
    }

    public String getOSSimpleName() {
        if (this.isMac()) {
            return OS_NAME_MAC;
        }

        if (this.isWindows()) {
            return OS_NAME_WIN;
        }

        if (this.isLinux()) {
            // It still might be ChromeOS
            ShellCommander shell = new ShellCommander();
            String msg = shell.runCommandAndGetOutputMessage(CMD_LINUX_VERSION);

            if (msg.contains(PROP_CHROME_OS)) {
                return OS_NAME_CHROME_OS;
            }

            return OS_NAME_LIN;
        }

        return this.OS_Name; // Not a popular OS... we don't know, return it directly!
    }

}
