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
package com.github.ajstri.echoedcalculator;

import com.github.ajstri.utilities.InternalLogging;

/**
 * Main Class of the EchoedCalculator project
 * Core of the Calculator, where everything is run & built.
 *
 * @author EchoedAJ
 * @since May 2020
 *
 * @version 1.0-SNAPSHOT
 */
public class CalculatorCore {

    private static final InternalLogging logging = new InternalLogging();
    private static final MathUtilities mathUtils = new MathUtilities();

    private static final CalculatorGUI GUI = new CalculatorGUI();

    public static void main(String[] args) {
        // There's nothing here...
        GUI.initialize();
    }

    /**
     * Retrieves the Internal Logging instance
     * @return InternalLogging
     */
    public static InternalLogging getLogging() {
        return logging;
    }

    /**
     * Retrieves the Math Utilities instance
     * @return MathUtilities
     */
    public static MathUtilities getMathUtils() {
        return mathUtils;
    }

}
