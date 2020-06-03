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
package com.github.ajstri;

import com.github.ajstri.echoedcalculator.CalculatorCore;
import com.github.ajstri.echoedcalculator.MathUtilities;

/**
 * Tester Class of the Echoed Calculator project
 *
 * @author EchoedAJ
 * @since June 2020
 */
public class Tester {
    public static void main (String[] args) {
        MathUtilities math = CalculatorCore.getMathUtils();
        double degreeVal = -180;

        String[] expressions = new String[] {
                "((4 - 2^3 + 1) * -sqrt(3*3+4*4)) / 2",
                "2 + 2",
                "sin(" + degreeVal + ")"
        };

        math.getTrigInstance().setDegrees();

        for (String expression : expressions) {
            System.out.println("Evaluating: " + expression);
            System.out.println(math.evaluateExpression(expression) + "");
            System.out.println();
        }
    }
}
