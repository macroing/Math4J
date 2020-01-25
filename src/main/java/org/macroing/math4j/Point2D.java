/**
 * Copyright 2019 - 2020 J&#246;rgen Lundgren
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

import static org.macroing.math4j.MathD.sqrt;

import java.util.Objects;

/**
 * A {@code Point2D} denotes a 2-dimensional point with two coordinates, of type {@code double}, denoted by X and Y.
 * <p>
 * This class is immutable and therefore thread-safe.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class Point2D {
	/**
	 * The value of the X-coordinate.
	 */
	public final double x;
	
	/**
	 * The value of the Y-coordinate.
	 */
	public final double y;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructs a new {@code Point2D} instance given the coordinates {@code 0.0D} and {@code 0.0D}.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new Point2D(0.0D, 0.0D);
	 * }
	 * </pre>
	 */
	public Point2D() {
		this(0.0D, 0.0D);
	}
	
	/**
	 * Constructs a new {@code Point2D} instance given the coordinates {@code x} and {@code y}.
	 * 
	 * @param x the value of the X-coordinate
	 * @param y the value of the Y-coordinate
	 */
	public Point2D(final double x, final double y) {
		this.x = x;
		this.y = y;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns a {@code String} representation of this {@code Point2D} instance.
	 * 
	 * @return a {@code String} representation of this {@code Point2D} instance
	 */
	@Override
	public String toString() {
		return String.format("new Point2D(%s, %s)", Double.toString(this.x), Double.toString(this.y));
	}
	
	/**
	 * Compares {@code object} to this {@code Point2D} instance for equality.
	 * <p>
	 * Returns {@code true} if, and only if, {@code object} is an instance of {@code Point2D}, and their respective values are equal, {@code false} otherwise.
	 * 
	 * @param object the {@code Object} to compare to this {@code Point2D} instance for equality
	 * @return {@code true} if, and only if, {@code object} is an instance of {@code Point2D}, and their respective values are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object object) {
		if(object == this) {
			return true;
		} else if(!(object instanceof Point2D)) {
			return false;
		} else if(!MathD.equals(this.x, Point2D.class.cast(object).x)) {
			return false;
		} else if(!MathD.equals(this.y, Point2D.class.cast(object).y)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returns the distance from this {@code Point2D} instance to {@code p}.
	 * <p>
	 * If {@code p} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param p a {@code Point2D} instance
	 * @return the distance from this {@code Point2D} instance to {@code p}
	 * @throws NullPointerException thrown if, and only if, {@code p} is {@code null}
	 */
	public double distanceTo(final Point2D p) {
		return sqrt(distanceToSquared(p));
	}
	
	/**
	 * Returns the squared distance from this {@code Point2D} instance to {@code p}.
	 * <p>
	 * If {@code p} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param p a {@code Point2D} instance
	 * @return the squared distance from this {@code Point2D} instance to {@code p}
	 * @throws NullPointerException thrown if, and only if, {@code p} is {@code null}
	 */
	public double distanceToSquared(final Point2D p) {
		final double x = p.x - this.x;
		final double y = p.y - this.y;
		
		return x * x + y * y;
	}
	
	/**
	 * Returns the value of the X-coordinate.
	 * 
	 * @return the value of the X-coordinate
	 */
	public double getX() {
		return this.x;
	}
	
	/**
	 * Returns the value of the Y-coordinate.
	 * 
	 * @return the value of the Y-coordinate
	 */
	public double getY() {
		return this.y;
	}
	
	/**
	 * Returns a hash code for this {@code Point2D} instance.
	 * 
	 * @return a hash code for this {@code Point2D} instance
	 */
	@Override
	public int hashCode() {
		return Objects.hash(Double.valueOf(this.x), Double.valueOf(this.y));
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns a {@code Point2D} instance denoting the UV-coordinates of a sphere.
	 * <p>
	 * If {@code direction} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param direction a {@link Vector3D} instance pointing in the direction of the UV-coordinates
	 * @return a {@code Point2D} instance denoting the UV-coordinates of a sphere
	 * @throws NullPointerException thrown if, and only if, {@code direction} is {@code null}
	 */
	public static Point2D direction(final Vector3D direction) {
		final double x = 0.5D + MathD.atan2(direction.z, direction.x) / MathD.PI_MULTIPLIED_BY_TWO;
		final double y = 0.5D - MathD.asinpi(direction.y);
		
		return new Point2D(x, y);
	}
}