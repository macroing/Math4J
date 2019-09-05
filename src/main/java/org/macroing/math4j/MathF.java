/**
 * Copyright 2019 J&#246;rgen Lundgren
 * 
 * This file is part of org.macroing.math4j.
 * 
 * org.macroing.math4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * org.macroing.math4j is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with org.macroing.math4j. If not, see <http://www.gnu.org/licenses/>.
 */
package org.macroing.math4j;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The class {@code MathF} contains methods for performing basic numeric operations such as the elementary exponential, logarithm, square root and trigonometric functions.
 * <p>
 * You can think of this class as an extension of {@code Math}. It does what {@code Math} does for the {@code double} and {@code float} types, but for the {@code float} type. In addition to this it also adds new methods.
 * <p>
 * This class does not contain all methods from {@code Math}. The methods in {@code Math} that deals with the {@code double} type can be found in the class {@link MathD}. The methods in {@code Math} that deals with the {@code int} type can be found in
 * the class {@link MathI}.
 * <p>
 * The documentation in this class should be comprehensive. But some of the details covered in the documentation of the {@code Math} class may be missing. To get the full documentation for a particular method, you may want to look at the documentation
 * of the corresponding method in the {@code Math} class. This is, of course, only true if the method you're looking at exists in the {@code Math} class.
 * <p>
 * Not all methods in the {@code Math} class that should be added to this class, may have been added yet.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class MathF {
	/**
	 * The {@code float} value that is closer than any other to pi, the ratio of the circumference of a circle to its diameter.
	 */
	public static final float PI = doToFloat(Math.PI);
	
	/**
	 * The {@code float} value that is closer than any other to pi, the ratio of the circumference of a circle to its diameter, multiplied by 2.0.
	 */
	public static final float PI_MULTIPLIED_BY_TWO = PI * 2.0F;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private MathF() {
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns {@code true} if, and only if, {@code a} is equal to {@code b}, {@code false} otherwise.
	 * 
	 * @param a a {@code float} value
	 * @param b a {@code float} value
	 * @return {@code true} if, and only if, {@code a} is equal to {@code b}, {@code false} otherwise
	 */
	public static boolean equals(final float a, final float b) {
		return Float.compare(a, b) == 0;
	}
	
	/**
	 * Returns the absolute version of {@code value}.
	 * <p>
	 * If the argument is not negative, the argument is returned. If the argument is negative, the negation of the argument is returned.
	 * <p>
	 * Special cases:
	 * <ul>
	 * <li>If the argument is positive zero or negative zero, the result is positive zero.</li>
	 * <li>If the argument is infinite, the result is positive infinity.</li>
	 * <li>If the argument is NaN, the result is NaN.</li>
	 * </ul>
	 * <p>
	 * In other words, the result is the same as the value of the expression:
	 * <pre>
	 * {@code
	 * Float.intBitsToFloat(0x7fffffff & Float.floatToIntBits(value))
	 * }
	 * </pre>
	 * 
	 * @param value a {@code float} value
	 * @return the absolute version of {@code value}
	 * @see Math#abs(float)
	 */
	public static float abs(final float value) {
		return Math.abs(value);
	}
	
	/**
	 * Returns the arc cosine of {@code value}.
	 * <p>
	 * The returned angle is in the range 0.0 through pi.
	 * <p>
	 * Special case:
	 * <ul>
	 * <li>If the argument is NaN or its absolute value is greater than 1, then the result is NaN.</li>
	 * </ul>
	 * <p>
	 * The computed result must be within 1 ulp of the exact result. Results must be semi-monotonic.
	 * 
	 * @param value the value whose arc cosine is to be returned
	 * @return the arc cosine of {@code value}
	 * @see Math#acos(double)
	 */
	public static float acos(final float value) {
		return doToFloat(Math.acos(value));
	}
	
	/**
	 * Returns the arc sine of {@code value}.
	 * <p>
	 * The returned angle is in the range -pi / 2 through pi / 2.
	 * <p>
	 * Special cases:
	 * <ul>
	 * <li>If the argument is NaN or its absolute value is greater than 1, then the result is NaN.</li>
	 * <li>If the argument is zero, then the result is a zero with the same sign as the argument.</li>
	 * </ul>
	 * <p>
	 * The computed result must be within 1 ulp of the exact result. Results must be semi-monotonic.
	 * 
	 * @param value the value whose arc sine is to be returned
	 * @return the arc sine of {@code value}
	 * @see Math#asin(double)
	 */
	public static float asin(final float value) {
		return doToFloat(Math.asin(value));
	}
	
	/**
	 * Returns the result of {@code asin(value)} divided by pi.
	 * 
	 * @param value the value whose arc sine divided by pi is to be returned
	 * @return the result of {@code asin(value)} divided by pi
	 * @see #asin(float)
	 */
	public static float asinpi(final float value) {
		return asin(value) / PI;
	}
	
	/**
	 * Returns the arc tangent of {@code value}.
	 * <p>
	 * The returned angle is in the range -pi / 2 through pi / 2.
	 * <p>
	 * Special cases:
	 * <ul>
	 * <li>If the argument is NaN, then the result is NaN.</li>
	 * <li>If the argument is zero, then the result is a zero with the same sign as the argument.</li>
	 * </ul>
	 * <p>
	 * The computed result must be within 1 ulp of the exact result. Results must be semi-monotonic.
	 * 
	 * @param value the value whose arc tangent is to be returned
	 * @return the arc tangent of {@code value}
	 * @see Math#atan(double)
	 */
	public static float atan(final float value) {
		return doToFloat(Math.atan(value));
	}
	
	/**
	 * Returns the angle <i>theta</i> from the conversion of rectangular coordinates (x, y) to polar coordinates (r, <i>theta</i>).
	 * <p>
	 * This method computes the phase <i>theta</i> by computing an arc tangent of y / x in the range of <i>-pi</i> to <i>pi</i>.
	 * <p>
	 * Special cases:
	 * <ul>
	 * <li>If either argument is NaN, then the result is NaN.</li>
	 * <li>If the first argument is positive zero and the second argument is positive, or the first argument is positive and finite and the second argument is positive infinity, then the result is positive zero.</li>
	 * <li>If the first argument is negative zero and the second argument is positive, or the first argument is negative and finite and the second argument is positive infinity, then the result is negative zero.</li>
	 * <li>If the first argument is positive zero and the second argument is negative, or the first argument is positive and finite and the second argument is negative infinity, then the result is the {@code float} value closest to pi.</li>
	 * <li>If the first argument is negative zero and the second argument is negative, or the first argument is negative and finite and the second argument is negative infinity, then the result is the {@code float} value closest to -pi.</li>
	 * <li>If the first argument is positive and the second argument is positive zero or negative zero, or the first argument is positive infinity and the second argument is finite, then the result is the {@code float} value closest to pi / 2.</li>
	 * <li>If the first argument is negative and the second argument is positive zero or negative zero, or the first argument is negative infinity and the second argument is finite, then the result is the {@code float} value closest to -pi / 2.</li>
	 * <li>If both arguments are positive infinity, then the result is the {@code float} value closest to pi / 4.</li>
	 * <li>If the first argument is positive infinity and the second argument is negative infinity, then the result is the {@code float} value closest to 3 * pi / 4.</li>
	 * <li>If the first argument is negative infinity and the second argument is positive infinity, then the result is the {@code float} value closest to -pi / 4.</li>
	 * <li>If both arguments are negative infinity, then the result is the {@code float} value closest to -3 * pi / 4.</li>
	 * </ul>
	 * <p>
	 * The computed result must be within 2 ulps of the exact result. Results must be semi-monotonic.
	 * 
	 * @param y the ordinate coordinate
	 * @param x the abscissa coordinate
	 * @return the angle <i>theta</i> from the conversion of rectangular coordinates (x, y) to polar coordinates (r, <i>theta</i>)
	 * @see Math#atan2(double, double)
	 */
	public static float atan2(final float y, final float x) {
		return doToFloat(Math.atan2(y, x));
	}
	
	/**
	 * Returns the result of {@code atan2(y, x)} divided by (pi * 2).
	 * 
	 * @param y the ordinate coordinate
	 * @param x the abscissa coordinate
	 * @return the result of {@code atan2(y, x)} divided by (pi * 2)
	 * @see #atan2(float, float)
	 */
	public static float atan2pi2(final float y, final float x) {
		return atan2(y, x) / PI_MULTIPLIED_BY_TWO;
	}
	
	/**
	 * Performs a bilinear interpolation operation on the supplied values.
	 * <p>
	 * Returns the result of the bilinear interpolation operation.
	 * <p>
	 * Calling this method is equivalent to the following:
	 * <pre>
	 * {@code
	 * MathF.lerp(MathF.lerp(value00, value01, tX), MathF.lerp(value10, value11, tX), tY);
	 * }
	 * </pre>
	 * 
	 * @param value00 a {@code float} value
	 * @param value01 a {@code float} value
	 * @param value10 a {@code float} value
	 * @param value11 a {@code float} value
	 * @param tX the X-axis factor
	 * @param tY the Y-axis factor
	 * @return the result of the bilinear interpolation operation
	 * @see #lerp(float, float, float)
	 */
	public static float blerp(final float value00, final float value01, final float value10, final float value11, final float tX, final float tY) {
		return lerp(lerp(value00, value01, tX), lerp(value10, value11, tX), tY);
	}
	
	/**
	 * Returns the smallest (closest to negative infinity) {@code float} value that is greater than or equal to {@code value} and is equal to a mathematical integer.
	 * <p>
	 * Special cases:
	 * <ul>
	 * <li>If the argument value is already equal to a mathematical integer, then the result is the same as the argument.</li>
	 * <li>If the argument is NaN or an infinity or positive zero or negative zero, then the result is the same as the argument.</li>
	 * <li>If the argument value is less than zero but greater than -1.0, then the result is negative zero.</li>
	 * </ul>
	 * <p>
	 * The computed result must be within 1 ulp of the exact result. Results must be semi-monotonic.
	 * 
	 * @param value a value
	 * @return the smallest (closest to negative infinity) {@code float} value that is greater than or equal to {@code value} and is equal to a mathematical integer
	 * @see Math#ceil(double)
	 */
	public static float ceil(final float value) {
		return doToFloat(Math.ceil(value));
	}
	
	/**
	 * Returns the trigonometric cosine of {@code angle}.
	 * <p>
	 * Special case:
	 * <ul>
	 * <li>If the argument is NaN or an infinity, then the result is NaN.</li>
	 * </ul>
	 * <p>
	 * The computed result must be within 1 ulp of the exact result. Results must be semi-monotonic.
	 * 
	 * @param angle an angle, in radians
	 * @return the trigonometric cosine of {@code angle}
	 * @see Math#cos(double)
	 */
	public static float cos(final float angle) {
		return doToFloat(Math.cos(angle));
	}
	
	/**
	 * Returns Euler's number {@code e} raised to the power of {@code exponent}.
	 * <p>
	 * Special cases:
	 * <ul>
	 * <li>If the argument is NaN, the result is NaN.</li>
	 * <li>If the argument is positive infinity, then the result is positive infinity.</li>
	 * <li>If the argument is negative infinity, then the result is positive zero.</li>
	 * </ul>
	 * <p>
	 * The computed result must be within 1 ulp of the exact result. Results must be semi-monotonic.
	 * 
	 * @param exponent the exponent to raise {@code e} to
	 * @return Euler's number {@code e} raised to the power of {@code exponent}
	 * @see Math#exp(double)
	 */
	public static float exp(final float exponent) {
		return doToFloat(Math.exp(exponent));
	}
	
	/**
	 * Returns the largest (closest to positive infinity) {@code float} value that is less than or equal to {@code value} and is equal to a mathematical integer.
	 * <p>
	 * Special cases:
	 * <ul>
	 * <li>If the argument value is already equal to a mathematical integer, then the result is the same as the argument.</li>
	 * <li>If the argument is NaN or an infinity or positive zero or negative zero, then the result is the same as the argument.</li>
	 * </ul>
	 * 
	 * @param value a value
	 * @return the largest (closest to positive infinity) {@code float} value that is less than or equal to {@code value} and is equal to a mathematical integer
	 * @see Math#floor(double)
	 */
	public static float floor(final float value) {
		return doToFloat(Math.floor(value));
	}
	
	/**
	 * Performs a linear interpolation operation on the supplied values.
	 * <p>
	 * Returns the result of the linear interpolation operation.
	 * 
	 * @param value0 a {@code float} value
	 * @param value1 a {@code float} value
	 * @param t the factor
	 * @return the result of the linear interpolation operation
	 */
	public static float lerp(final float value0, final float value1, final float t) {
		return (1.0F - t) * value0 + t * value1;
	}
	
	/**
	 * Returns the natural logarithm (base {@code e}) of the {@code float} value {@code value}.
	 * <p>
	 * Special cases:
	 * <ul>
	 * <li>If the argument is NaN or less than zero, then the result is NaN.</li>
	 * <li>If the argument is positive infinity, then the result is positive infinity.</li>
	 * <li>If the argument is positive zero or negative zero, then the result is negative infinity.</li>
	 * </ul>
	 * <p>
	 * The computed result must be within 1 ulp of the exact result. Results must be semi-monotonic.
	 * 
	 * @param value a value
	 * @return the natural logarithm (base {@code e}) of the {@code float} value {@code value}
	 * @see Math#log(double)
	 */
	public static float log(final float value) {
		return doToFloat(Math.log(value));
	}
	
	/**
	 * Returns the greater value of {@code a} and {@code b}.
	 * <p>
	 * The result is the argument closer to positive infinity.
	 * <p>
	 * If the arguments have the same value, the result is that same value. If either value is NaN, then the result is NaN. Unlike the numerical comparison operators, this method considers negative zero to be strictly smaller than positive zero. If one
	 * argument is positive zero and the other negative zero, the result is positive zero.
	 * 
	 * @param a a value
	 * @param b a value
	 * @return the greater value of {@code a} and {@code b}
	 * @see Math#max(float, float)
	 */
	public static float max(final float a, final float b) {
		return Math.max(a, b);
	}
	
	/**
	 * Returns the greater value of {@code a}, {@code b} and {@code c}.
	 * <p>
	 * The result is the argument closer to positive infinity.
	 * <p>
	 * If the arguments have the same value, the result is that same value. If either value is NaN, then the result is NaN. Unlike the numerical comparison operators, this method considers negative zero to be strictly smaller than positive zero. If one
	 * argument is positive zero and the two others negative zero, the result is positive zero.
	 * 
	 * @param a a value
	 * @param b a value
	 * @param c a value
	 * @return the greater value of {@code a}, {@code b} and {@code c}
	 */
	public static float max(final float a, final float b, final float c) {
		return max(max(a, b), c);
	}
	
	/**
	 * Returns the smaller value of {@code a} and {@code b}.
	 * <p>
	 * The result is the value closer to negative infinity.
	 * <p>
	 * If the arguments have the same value, the result is that same value. If either value is NaN, then the result is NaN. Unlike the numerical comparison operators, this method considers negative zero to be strictly smaller than positive zero. If one
	 * argument is positive zero and the other is negative zero, the result is negative zero.
	 * 
	 * @param a a value
	 * @param b a value
	 * @return the smaller value of {@code a} and {@code b}
	 * @see Math#min(float, float)
	 */
	public static float min(final float a, final float b) {
		return Math.min(a, b);
	}
	
	/**
	 * Returns the smaller value of {@code a}, {@code b} and {@code c}.
	 * <p>
	 * The result is the value closer to negative infinity.
	 * <p>
	 * If the arguments have the same value, the result is that same value. If either value is NaN, then the result is NaN. Unlike the numerical comparison operators, this method considers negative zero to be strictly smaller than positive zero. If one
	 * argument is positive zero and the others negative zero, the result is negative zero.
	 * 
	 * @param a a value
	 * @param b a value
	 * @param c a value
	 * @return the smaller value of {@code a}, {@code b} and {@code c}
	 */
	public static float min(final float a, final float b, final float c) {
		return min(min(a, b), c);
	}
	
	/**
	 * Returns {@code base} raised to the power of {@code exponent}.
	 * <p>
	 * For the full documentation of this method, see {@link Math#pow(double, double)}.
	 * 
	 * @param base the base
	 * @param exponent the exponent
	 * @return {@code base} raised to the power of {@code exponent}
	 * @see Math#pow(double, double)
	 */
	public static float pow(final float base, final float exponent) {
		return doToFloat(Math.pow(base, exponent));
	}
	
	/**
	 * Returns a pseudorandom {@code float} value between {@code 0.0F} (inclusive) and {@code 1.0F} (exclusive).
	 * 
	 * @return a pseudorandom {@code float} value between {@code 0.0F} (inclusive) and {@code 1.0F} (exclusive)
	 */
	public static float random() {
		return ThreadLocalRandom.current().nextFloat();
	}
	
	/**
	 * Returns the remainder of {@code x} and {@code y}.
	 * 
	 * @param x the left hand side of the remainder operation
	 * @param y the right hand side of the remainder operation
	 * @return the remainder of {@code x} and {@code y}
	 */
	public static float remainder(final float x, final float y) {
		return x - (int)(x / y) * y;
	}
	
	/**
	 * Returns a saturated (or clamped) value based on {@code value}.
	 * <p>
	 * Calling this method is equivalent to the following:
	 * <pre>
	 * {@code
	 * MathF.saturate(value, 0.0F, 1.0F);
	 * }
	 * </pre>
	 * 
	 * @param value the value to saturate (or clamp)
	 * @return a saturated (or clamped) value based on {@code value}
	 */
	public static float saturate(final float value) {
		return saturate(value, 0.0F, 1.0F);
	}
	
	/**
	 * Returns a saturated (or clamped) value based on {@code value}.
	 * <p>
	 * If {@code value} is less than {@code min(edgeA, edgeB)}, {@code min(edgeA, edgeB)} will be returned. If {@code value} is greater than {@code max(edgeA, edgeB)}, {@code max(edgeA, edgeB)} will be returned. Otherwise {@code value} will be
	 * returned.
	 * 
	 * @param value the value to saturate (or clamp)
	 * @param edgeA the minimum or maximum value
	 * @param edgeB the maximum or minimum value
	 * @return a saturated (or clamped) value based on {@code value}
	 */
	public static float saturate(final float value, final float edgeA, final float edgeB) {
		final float minimumValue = min(edgeA, edgeB);
		final float maximumValue = max(edgeA, edgeB);
		
		return value < minimumValue ? minimumValue : value > maximumValue ? maximumValue : value;
	}
	
	/**
	 * Returns the trigonometric sine of {@code angle}.
	 * <p>
	 * Special cases:
	 * <ul>
	 * <li>If the argument is NaN or an infinity, then the result is NaN.</li>
	 * <li>If the argument is zero, then the result is a zero with the same sign as the argument.</li>
	 * </ul>
	 * <p>
	 * The computed result must be within 1 ulp of the exact result. Results must be semi-monotonic.
	 * 
	 * @param angle an angle, in radians
	 * @return the trigonometric sine of {@code angle}
	 * @see Math#sin(double)
	 */
	public static float sin(final float angle) {
		return doToFloat(Math.sin(angle));
	}
	
	/**
	 * Performs a smoothstep operation on {@code value} and the edges {@code edgeA} and {@code edgeB}.
	 * <p>
	 * Returns a {@code float} value.
	 * 
	 * @param value a {@code float} value
	 * @param edgeA one of the edges
	 * @param edgeB one of the edges
	 * @return a {@code float} value
	 */
	public static float smoothstep(final float value, final float edgeA, final float edgeB) {
		final float minimumValue = min(edgeA, edgeB);
		final float maximumValue = max(edgeA, edgeB);
		
		final float x = saturate((value - minimumValue) / (maximumValue - minimumValue), 0.0F, 1.0F);
		
		return x * x * (3.0F - 2.0F * x);
	}
	
	/**
	 * Returns the correctly rounded positive square root of {@code value}.
	 * <p>
	 * Special cases:
	 * <ul>
	 * <li>If the argument is NaN or less than zero, then the result is NaN.</li>
	 * <li>If the argument is positive infinity, then the result is positive infinity.</li>
	 * <li>If the argument is positive zero or negative zero, then the result is the same as the argument.</li>
	 * </ul>
	 * <p>
	 * Otherwise, the result is the {@code float} value closest to the true mathematical square root of the argument value.
	 * 
	 * @param value a value
	 * @return the correctly rounded positive square root of {@code value}
	 * @see Math#sqrt(double)
	 */
	public static float sqrt(final float value) {
		return doToFloat(Math.sqrt(value));
	}
	
	/**
	 * Returns the trigonometric tangent of {@code angle}.
	 * <p>
	 * Special cases:
	 * <ul>
	 * <li>If the argument is NaN or an infinity, then the result is NaN.</li>
	 * <li>If the argument is zero, then the result is a zero with the same sign as the argument.</li>
	 * </ul>
	 * <p>
	 * The computed result must be within 1 ulp of the exact result. Results must be semi-monotonic.
	 * 
	 * @param angle an angle, in radians
	 * @return the trigonometric tangent of {@code angle}
	 * @see Math#tan(double)
	 */
	public static float tan(final float angle) {
		return doToFloat(Math.tan(angle));
	}
	
	/**
	 * Returns an approximately equivalent angle measured in degrees from an angle measured in radians.
	 * <p>
	 * The conversion from radians to degrees is generally inexact.
	 * 
	 * @param angleInRadians an angle, in radians
	 * @return an approximately equivalent angle measured in degrees from an angle measured in radians
	 * @see Math#toDegrees(double)
	 */
	public static float toDegrees(final float angleInRadians) {
		return doToFloat(Math.toDegrees(angleInRadians));
	}
	
	/**
	 * Returns an approximately equivalent angle measured in radians from an angle measured in degrees.
	 * <p>
	 * The conversion from degrees to radians is generally inexact.
	 * 
	 * @param angleInDegrees an angle, in degrees
	 * @return an approximately equivalent angle measured in radians from an angle measured in degrees
	 * @see Math#toRadians(double)
	 */
	public static float toRadians(final float angleInDegrees) {
		return doToFloat(Math.toRadians(angleInDegrees));
	}
	
	/**
	 * Returns {@code value} or its wrapped around representation.
	 * <p>
	 * If {@code value} is greater than or equal to {@code min(a, b)} and less than or equal to {@code max(a, b)}, {@code value} will be returned. Otherwise it will wrap around on either side until it is contained in the interval
	 * {@code [min(a, b), max(a, b)]}.
	 * 
	 * @param value the value to potentially wrap around
	 * @param a one of the values in the interval to wrap around
	 * @param b one of the values in the interval to wrap around
	 * @return {@code value} or its wrapped around representation
	 */
	public static float wrapAround(final float value, final float a, final float b) {
		final float minimumValue = min(a, b);
		final float maximumValue = max(a, b);
		
		float currentValue = value;
		
		while(currentValue < minimumValue || currentValue > maximumValue) {
			if(currentValue < minimumValue) {
				currentValue = maximumValue - (minimumValue - currentValue);
			} else if(currentValue > maximumValue) {
				currentValue = minimumValue + (currentValue - maximumValue);
			}
		}
		
		return currentValue;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private static float doToFloat(final double value) {
		return (float)(value);
	}
}