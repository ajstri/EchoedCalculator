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

import com.github.ajstri.exceptions.UnhandledMathException;
import com.github.ajstri.math.Logarithmic;
import com.github.ajstri.math.Trigonometry;

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
     * NOTE: THIS CLASS IS NOT FINAL & MAY NOT STAY
     *  HOW THESE FUNCTIONS WILL WORK ARE NOT FINAL
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


    /**
     * A super overkill Math function
     *
     * This code is altered from a StackOverflow answer
     * and is used from public domain.
     * You can find the original code here:
     * https://stackoverflow.com/a/26227947
     *
     * It has been adapted to work for this project and
     * expanded to include more mathematical functions.
     * It is a fully functional, dynamic-length math evaluation
     * method.
     *
     * @param str mathematical expression to parse.
     * @return the answer to the expression.
     */
    public double evaluateExpression(String str) {
        CalculatorCore.getLogging().info("Parsing expression: " + str);
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) {
                    CalculatorCore.getLogging().error("Unexpected: " + (char)ch + "\n", new RuntimeException("Unexpected: " + (char)ch));
                    return -9999.99;
                }
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+')) {
                        x += parseTerm(); // addition
                    }
                    else if (eat('-')) {
                        x -= parseTerm(); // subtraction
                    }
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('*')) {
                        x *= parseFactor(); // multiplication
                    }
                    else if (eat('/')) {
                        x /= parseFactor(); // division
                    }
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    if (!eat(')')) {
                        CalculatorCore.getLogging().error("Missing closing parenthesis\n", new UnhandledMathException("Missing closing parenthesis"));
                        return 0;
                    }
                }
                else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') {
                        nextChar();
                    }
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                }
                else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    x = parseFunction(x, func);
                }
                else {
                    CalculatorCore.getLogging().error("Unexpected: " + (char)ch + "\n", new UnhandledMathException("Unexpected: " + (char)ch));
                    return -9999.99;
                }

                if (eat('^')) {
                    x = Math.pow(x, parseFactor()); // exponentiation
                }

                return x;
            }
        }.parse();
    }

    /**
     * Parses an input function from a string and applies it to
     * a given variable.
     *
     * @param x given variable
     * @param func function to parse
     * @return x after applied function
     */
    private double parseFunction(double x, String func) {
        switch (func) {
            case "sqrt":
                x = Math.sqrt(x);
                break;
            case "cbrt":
                x = Math.cbrt(x);
                break;
            case "sin":
                x = getTrigInstance().sin(x);
                break;
            case "cos":
                x = getTrigInstance().cos(x);
                break;
            case "tan":
                x = getTrigInstance().tan(x);
                break;
            case "sinh":
                x = getTrigInstance().sinh(x);
                break;
            case "cosh":
                x = getTrigInstance().cosh(x);
                break;
            case "tanh":
                x = getTrigInstance().tanh(x);
                break;
            case "asin":
                x = getTrigInstance().asin(x);
                break;
            case "acos":
                x = getTrigInstance().acos(x);
                break;
            case "atan":
                x = getTrigInstance().atan(x);
                break;
            case "abs":
                x = Math.abs(x);
                break;
            case "ln":
                x = Logarithmic.ln(x);
                break;
            case "log":
                x = Logarithmic.log(x);
                break;
            case "cot":
                x = getTrigInstance().cot(x);
                break;
            case "csc":
                x = getTrigInstance().csc(x);
                break;
            case "sec":
                x = getTrigInstance().sec(x);
                break;
            case "acot":
                x = getTrigInstance().acot(x);
                break;
            case "acsc":
                x = getTrigInstance().acsc(x);
                break;
            case "asec":
                x = getTrigInstance().asec(x);
                break;
            case "coth":
                x = getTrigInstance().coth(x);
                break;
            case "csch":
                x = getTrigInstance().csch(x);
                break;
            case "sech":
                x = getTrigInstance().sech(x);
                break;
            case "asinh":
                x = getTrigInstance().asinh(x);
                break;
            case "acosh":
                x = getTrigInstance().acosh(x);
                break;
            case "atanh":
                x = getTrigInstance().atanh(x);
                break;
            case "acoth":
                x = getTrigInstance().acoth(x);
                break;
            case "acsch":
                x = getTrigInstance().acsch(x);
                break;
            case "asech":
                x = getTrigInstance().asech(x);
                break;

            default:
                CalculatorCore.getLogging().error("Unexpected function: " + func + "\n", new UnhandledMathException("Unexpected function: " + func));
                return -9999.99;
        }
        return x;
    }

}
