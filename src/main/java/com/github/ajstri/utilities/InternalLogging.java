/*
 *  Copyright 2020 EchoedAJ
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.github.ajstri.utilities;

import java.io.PrintStream;

/**
 * InternalLogging class of the EchoedCalculator project
 *
 * @author EchoedAJ
 * @since April 2020
 */
public class InternalLogging {

    // PrintStreams
    private static final PrintStream err = System.err;
    private static final PrintStream other = System.out;

    // Logging Specific
    private static String title = "[EchoedCore";
    private static boolean log = true;

    // Colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public InternalLogging() { }

    public InternalLogging (String newTitle) {
        title = "[" + newTitle;
    }

    public void setTitle(String newTitle) {
        title = "[" + newTitle;
    }

    /**
     * Toggles if internal logging should be used
     *
     * @param logging true if yes, false if no
     */
    public void setLogging(boolean logging) {
        log = logging;
    }

    public static boolean isLogging() {
        return log;
    }

    /**
     * Logs an informational message
     *
     * @param message Message to log
     */
    public void info(String message) {
        if (isLogging()) {
            other.println(ANSI_BLUE + title + "/INFO] " + message + ANSI_RESET);
        }
    }

    /**
     * Logs a warning message
     *
     * @param message Message to log
     */
    public void warning(String message) {
        if (isLogging()) {
            other.println(ANSI_YELLOW + title + "/WARNING] " + message + ANSI_RESET);
        }
    }

    /**
     * Logs an error message
     *
     * @param message Message to log
     * @param e Exception encountered
     */
    public void error(String message, Exception e) {
        if (isLogging()) {
            err.println(ANSI_RED + title + "/ERROR] " + message + ANSI_RESET);

            e.getMessage();
            e.printStackTrace();
        }
    }

    /**
     * Logs a debug message
     *
     * @param message Message to log
     * @param stage Stage of log
     */
    public void debug(String message, String stage) {
        if (isLogging()) {
            other.println(ANSI_PURPLE + title + "/DEBUG/" + stage + "] " + message + ANSI_RESET);
        }
    }

    /**
     * Logs a custom message
     *
     * @param message message to log
     * @param header header of message, eg. "[EchoedBot/Utils]"
     * @param color color of message
     */
    public void blank(String header, String color, String message) {
        if (isLogging()) {
            other.println(color + header + message + ANSI_RESET);
        }
    }
}