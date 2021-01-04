/**
 * Copyright 2019 - 2021 J&#246;rgen Lundgren
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

import static org.macroing.math4j.MathI.max;
import static org.macroing.math4j.MathI.min;

import java.util.Objects;

/**
 * A {@code Point2I} denotes a 2-dimensional point with two coordinates, of type {@code int}, denoted by X and Y.
 * <p>
 * This class is immutable and therefore thread-safe.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class Point2I {
	/**
	 * The value of the X-coordinate.
	 */
	public final int x;
	
	/**
	 * The value of the Y-coordinate.
	 */
	public final int y;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructs a new {@code Point2I} instance given the coordinates {@code 0} and {@code 0}.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new Point2I(0, 0);
	 * }
	 * </pre>
	 */
	public Point2I() {
		this(0, 0);
	}
	
	/**
	 * Constructs a new {@code Point2I} instance given the coordinates {@code x} and {@code y}.
	 * 
	 * @param x the value of the X-coordinate
	 * @param y the value of the Y-coordinate
	 */
	public Point2I(final int x, final int y) {
		this.x = x;
		this.y = y;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns a {@code String} representation of this {@code Point2I} instance.
	 * 
	 * @return a {@code String} representation of this {@code Point2I} instance
	 */
	@Override
	public String toString() {
		return String.format("new Point2I(%s, %s)", Integer.toString(this.x), Integer.toString(this.y));
	}
	
	/**
	 * Compares {@code object} to this {@code Point2I} instance for equality.
	 * <p>
	 * Returns {@code true} if, and only if, {@code object} is an instance of {@code Point2I}, and their respective values are equal, {@code false} otherwise.
	 * 
	 * @param object the {@code Object} to compare to this {@code Point2I} instance for equality
	 * @return {@code true} if, and only if, {@code object} is an instance of {@code Point2I}, and their respective values are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object object) {
		if(object == this) {
			return true;
		} else if(!(object instanceof Point2I)) {
			return false;
		} else if(this.x != Point2I.class.cast(object).x) {
			return false;
		} else if(this.y != Point2I.class.cast(object).y) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returns the value of the X-coordinate.
	 * 
	 * @return the value of the X-coordinate
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Returns the value of the Y-coordinate.
	 * 
	 * @return the value of the Y-coordinate
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Returns a hash code for this {@code Point2I} instance.
	 * 
	 * @return a hash code for this {@code Point2I} instance
	 */
	@Override
	public int hashCode() {
		return Objects.hash(Integer.valueOf(this.x), Integer.valueOf(this.y));
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns a new {@code Point2I} instance with the greater X- and Y-coordinate values of {@code a} and {@code b}.
	 * <p>
	 * If either {@code a} or {@code b} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param a a {@code Point2I} instance
	 * @param b a {@code Point2I} instance
	 * @return a new {@code Point2I} instance with the greater X- and Y-coordinate values of {@code a} and {@code b}
	 * @throws NullPointerException thrown if, and only if, either {@code a} or {@code b} are {@code null}
	 */
	public static Point2I maximum(final Point2I a, final Point2I b) {
		return new Point2I(max(a.x, b.x), max(a.y, b.y));
	}
	
	/**
	 * Returns a new {@code Point2I} instance with the smaller X- and Y-coordinate values of {@code a} and {@code b}.
	 * <p>
	 * If either {@code a} or {@code b} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param a a {@code Point2I} instance
	 * @param b a {@code Point2I} instance
	 * @return a new {@code Point2I} instance with the smaller X- and Y-coordinate values of {@code a} and {@code b}
	 * @throws NullPointerException thrown if, and only if, either {@code a} or {@code b} are {@code null}
	 */
	public static Point2I minimum(final Point2I a, final Point2I b) {
		return new Point2I(min(a.x, b.x), min(a.y, b.y));
	}
}