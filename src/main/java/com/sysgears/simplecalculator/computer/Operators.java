package com.sysgears.simplecalculator.computer;

import java.util.regex.Pattern;

/**
 * Contains possible operators and their math logic. Priority of
 * operators is their order in this class. Minus must be before
 * plus according to algorithms' logic.
 */
public enum Operators {
    POWER("^") {
        @Override
        public Double calculate(final double v1, final double v2) {
            return convertNegativeZero((v1 < 0 ? -1 : 1) * Math.pow(v1, v2));
        }
    },
    DIVIDE("/") {
        @Override
        public Double calculate(final double v1, final double v2) {
            if (Double.compare(convertNegativeZero(v2), 0) == 0) {
                throw new ArithmeticException();
            }

            return convertNegativeZero(v1 / v2);
        }
    },
    MULTIPLY("*") {
        @Override
        public Double calculate(final double v1, final double v2) {
            return convertNegativeZero(v1 * v2);
        }
    },
    SUBTRACT("-") {
        @Override
        public Double calculate(final double v1, final double v2) {
            return convertNegativeZero(v1 - v2);
        }
    },
    ADD("+") {
        @Override
        public Double calculate(final double v1, final double v2) {
            return convertNegativeZero(v1 + v2);
        }
    };

    /**
     * The string representation of the operator
     */
    private String representation;

    /**
     * Constructs an object
     *
     * @param representation the string representation of the operator
     */
    Operators(String representation) {
        this.representation = representation;
    }

    /**
     * Builds a RegExp string contains all the operators
     *
     * @return a RegExp string contains all the operators
     */
    static String getRegExp() {
        StringBuilder builder = new StringBuilder("[");

        for (Operators operator : values()) {
            builder.append(operator.getRegExpRepresentation());
        }

        return builder.append("]{1}").toString();
    }

    /**
     * This function convert <code>value</code> to +0.0, if it is equal to -0.0 so as to
     * obtain a predictable behaviour of compare functions
     *
     * @param value a value to convert
     * @return converted or the same value
     */
    static double convertNegativeZero(double value) {
        if (value == 0.0) {
            value = 0.0;  // convert -0.0 to +0.0
        }

        return value;
    }

    /**
     * Contains the math logic of the operator
     *
     * @param v1 the left operand
     * @param v2 the right operand
     * @return computed value
     */
    public abstract Double calculate(final double v1, final double v2) throws ArithmeticException;

    /**
     * Returns the string representation of the operator
     *
     * @return the string representation of the operator
     */
    public String getRepresentation() {
        return representation;
    }

    /**
     * Returns the RegExp string representation of the operator
     *
     * @return the string representation of the operator
     */
    public String getRegExpRepresentation() {
        return Pattern.quote(representation);
    }
}