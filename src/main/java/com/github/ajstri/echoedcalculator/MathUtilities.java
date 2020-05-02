/*
 * Copyright 2020 EchoedAJ
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.github.ajstri.echoedcalculator;

import com.github.ajstri.echoedmath.Trigonometry;

/**
 * MathUtilities Class of the EchoedCalculator project
 * Core of the Mathematics
 *
 * @author EchoedAJ
 * @since May 2020
 *
 * @version 1.0-SNAPSHOT
 */
public class MathUtilities {
    /*
        TODO input parse

        NOTE: THIS CLASS IS NOT FINAL & MAY NOT STAY
        HOW THESE FUNCTIONS WILL WORK ARE NOT FINAL
     */

    private static Trigonometry trig;

    /**
     * Constructor of the MathUtilities class
     * Use defined isDegrees value
     *
     * @param isDegrees isDegrees
     */
    public MathUtilities (boolean isDegrees) {
        trig = new Trigonometry(isDegrees);
    }

    /**
     * Constructor of the MathUtilities class
     * Default to radians
     */
    public MathUtilities () {
        trig = new Trigonometry();
    }

    // --- getter & setter ---

    /**
     * Retrieve the Trigonometry instance.
     * This set of math functions has an object in order to keep
     * the distinction of degrees/radians constant.
     *
     * @return Trigonometry instance
     */
    public Trigonometry getTrigInstance() {
        return trig;
    }

}
