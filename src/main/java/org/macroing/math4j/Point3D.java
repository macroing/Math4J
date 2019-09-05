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

import static org.macroing.math4j.MathD.max;
import static org.macroing.math4j.MathD.min;
import static org.macroing.math4j.MathD.sqrt;

import java.util.Objects;

/**
 * A {@code Point3D} denotes a 3-dimensional point with three coordinates, of type {@code double}, denoted by X, Y and Z.
 * <p>
 * This class is immutable and therefore thread-safe.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class Point3D {
	/**
	 * The value of the X-coordinate.
	 */
	public final double x;
	
	/**
	 * The value of the Y-coordinate.
	 */
	public final double y;
	
	/**
	 * The value of the Z-coordinate.
	 */
	public final double z;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructs a new {@code Point3D} instance given the coordinates {@code 0.0D}, {@code 0.0D} and {@code 0.0D}.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new Point3D(0.0D, 0.0D, 0.0D);
	 * }
	 * </pre>
	 */
	public Point3D() {
		this(0.0D, 0.0D, 0.0D);
	}
	
	/**
	 * Constructs a new {@code Point3D} instance given the coordinates {@code v.x}, {@code v.y} and {@code v.z}.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new Point3D(v.x, v.y, v.z);
	 * }
	 * </pre>
	 * 
	 * @param v a {@link Vector3D} instance
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public Point3D(final Vector3D v) {
		this(v.x, v.y, v.z);
	}
	
	/**
	 * Constructs a new {@code Point3D} instance given the coordinates {@code x}, {@code y} and {@code z}.
	 * 
	 * @param x the value of the X-coordinate
	 * @param y the value of the Y-coordinate
	 * @param z the value of the Z-coordinate
	 */
	public Point3D(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Adds the component values of {@code v} to the coordinate values of this {@code Point3D} instance.
	 * <p>
	 * Returns a new {@code Point3D} instance that is offset from this {@code Point3D} instance in the direction of {@code v} with the distance of {@code v}.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param v a {@link Vector3D} instance with the direction of the {@code Point3D} to return
	 * @return a new {@code Point3D} instance that is offset from this {@code Point3D} instance in the direction of {@code v} with the distance of {@code v}
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public Point3D add(final Vector3D v) {
		return new Point3D(this.x + v.x, this.y + v.y, this.z + v.z);
	}
	
	/**
	 * Adds the component values of {@code v} multiplied by {@code t} to the coordinate values of this {@code Point3D} instance.
	 * <p>
	 * Returns a new {@code Point3D} instance that is offset from this {@code Point3D} instance in the direction of {@code v} with the distance of {@code v.multiply(t)}.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param v a {@link Vector3D} instance with the direction of the {@code Point3D} to return
	 * @param t the distance multiplier
	 * @return a new {@code Point3D} instance that is offset from this {@code Point3D} instance in the direction of {@code v} with the distance of {@code v.multiply(t)}
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public Point3D add(final Vector3D v, final double t) {
		return new Point3D(this.x + v.x * t, this.y + v.y * t, this.z + v.z * t);
	}
	
	/**
	 * Performs a transformation.
	 * <p>
	 * Returns a new {@code Point3D} instance with the result of the transformation.
	 * <p>
	 * If {@code m} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param m the {@link Matrix44D} to perform the transformation with
	 * @return a new {@code Point3D} instance with the result of the transformation
	 * @throws NullPointerException thrown if, and only if, {@code m} is {@code null}
	 */
	public Point3D transform(final Matrix44D m) {
		final double x = m.element11 * this.x + m.element12 * this.y + m.element13 * this.z + m.element14;
		final double y = m.element21 * this.x + m.element22 * this.y + m.element23 * this.z + m.element24;
		final double z = m.element31 * this.x + m.element32 * this.y + m.element33 * this.z + m.element34;
		
		return new Point3D(x, y, z);
	}
	
	/**
	 * Performs a transformation and a divide.
	 * <p>
	 * Returns a new {@code Point3D} instance with the result of the transformation and division.
	 * <p>
	 * If {@code m} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param m the {@link Matrix44D} to perform the transformation with
	 * @return a new {@code Point3D} instance with the result of the transformation and division
	 * @throws NullPointerException thrown if, and only if, {@code m} is {@code null}
	 */
	public Point3D transformAndDivide(final Matrix44D m) {
		final double x = m.element11 * this.x + m.element12 * this.y + m.element13 * this.z + m.element14;
		final double y = m.element21 * this.x + m.element22 * this.y + m.element23 * this.z + m.element24;
		final double z = m.element31 * this.x + m.element32 * this.y + m.element33 * this.z + m.element34;
		final double w = m.element41 * this.x + m.element42 * this.y + m.element43 * this.z + m.element44;
		
		return MathD.equals(w, 1.0D) ? new Point3D(x / w, y / w, z / w) : new Point3D(x, y, z);
	}
	
	/**
	 * Returns a {@code String} representation of this {@code Point3D} instance.
	 * 
	 * @return a {@code String} representation of this {@code Point3D} instance
	 */
	@Override
	public String toString() {
		return String.format("new Point3D(%s, %s, %s)", Double.toString(this.x), Double.toString(this.y), Double.toString(this.z));
	}
	
	/**
	 * Compares {@code object} to this {@code Point3D} instance for equality.
	 * <p>
	 * Returns {@code true} if, and only if, {@code object} is an instance of {@code Point3D}, and their respective values are equal, {@code false} otherwise.
	 * 
	 * @param object the {@code Object} to compare to this {@code Point3D} instance for equality
	 * @return {@code true} if, and only if, {@code object} is an instance of {@code Point3D}, and their respective values are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object object) {
		if(object == this) {
			return true;
		} else if(!(object instanceof Point3D)) {
			return false;
		} else if(!MathD.equals(this.x, Point3D.class.cast(object).x)) {
			return false;
		} else if(!MathD.equals(this.y, Point3D.class.cast(object).y)) {
			return false;
		} else if(!MathD.equals(this.z, Point3D.class.cast(object).z)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returns the distance from this {@code Point3D} instance to {@code p}.
	 * <p>
	 * If {@code p} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param p a {@code Point3D} instance
	 * @return the distance from this {@code Point3D} instance to {@code p}
	 * @throws NullPointerException thrown if, and only if, {@code p} is {@code null}
	 */
	public double distanceTo(final Point3D p) {
		return sqrt(distanceToSquared(p));
	}
	
	/**
	 * Returns the squared distance from this {@code Point3D} instance to {@code p}.
	 * <p>
	 * If {@code p} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param p a {@code Point3D} instance
	 * @return the squared distance from this {@code Point3D} instance to {@code p}
	 * @throws NullPointerException thrown if, and only if, {@code p} is {@code null}
	 */
	public double distanceToSquared(final Point3D p) {
		final double x = p.x - this.x;
		final double y = p.y - this.y;
		final double z = p.z - this.z;
		
		return x * x + y * y + z * z;
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
	 * Returns the value of the Z-coordinate.
	 * 
	 * @return the value of the Z-coordinate
	 */
	public double getZ() {
		return this.z;
	}
	
	/**
	 * Returns a hash code for this {@code Point3D} instance.
	 * 
	 * @return a hash code for this {@code Point3D} instance
	 */
	@Override
	public int hashCode() {
		return Objects.hash(Double.valueOf(this.x), Double.valueOf(this.y), Double.valueOf(this.z));
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns a new {@code Point3D} instance with the greater X-, Y- and Z-coordinate values of {@code a} and {@code b}.
	 * <p>
	 * If either {@code a} or {@code b} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param a a {@code Point3D} instance
	 * @param b a {@code Point3D} instance
	 * @return a new {@code Point3D} instance with the greater X-, Y- and Z-coordinate values of {@code a} and {@code b}
	 * @throws NullPointerException thrown if, and only if, either {@code a} or {@code b} are {@code null}
	 */
	public static Point3D maximum(final Point3D a, final Point3D b) {
		return new Point3D(max(a.x, b.x), max(a.y, b.y), max(a.z, b.z));
	}
	
	/**
	 * Returns a new {@code Point3D} instance with the greater X-, Y- and Z-coordinate values of {@code a}, {@code b} and {@code c}.
	 * <p>
	 * If either {@code a}, {@code b} or {@code c} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param a a {@code Point3D} instance
	 * @param b a {@code Point3D} instance
	 * @param c a {@code Point3D} instance
	 * @return a new {@code Point3D} instance with the greater X-, Y- and Z-coordinate values of {@code a}, {@code b} and {@code c}
	 * @throws NullPointerException thrown if, and only if, either {@code a}, {@code b} or {@code c} are {@code null}
	 */
	public static Point3D maximum(final Point3D a, final Point3D b, final Point3D c) {
		return new Point3D(max(a.x, b.x, c.x), max(a.y, b.y, c.y), max(a.z, b.z, c.z));
	}
	
	/**
	 * Returns a new {@code Point3D} instance located in the middle of {@code a} and {@code b}.
	 * <p>
	 * If either {@code a} or {@code b} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param a a {@code Point3D} instance that represents one of two end points of a line segment
	 * @param b a {@code Point3D} instance that represents one of two end points of a line segment
	 * @return a new {@code Point3D} instance located in the middle of {@code a} and {@code b}
	 * @throws NullPointerException thrown if, and only if, either {@code a} or {@code b} are {@code null}
	 */
	public static Point3D midpoint(final Point3D a, final Point3D b) {
		return new Point3D((a.x + b.x) * 0.5D, (a.y + b.y) * 0.5D, (a.z + b.z) * 0.5D);
	}
	
	/**
	 * Returns a new {@code Point3D} instance with the smaller X-, Y- and Z-coordinate values of {@code a} and {@code b}.
	 * <p>
	 * If either {@code a} or {@code b} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param a a {@code Point3D} instance
	 * @param b a {@code Point3D} instance
	 * @return a new {@code Point3D} instance with the smaller X-, Y- and Z-coordinate values of {@code a} and {@code b}
	 * @throws NullPointerException thrown if, and only if, either {@code a} or {@code b} are {@code null}
	 */
	public static Point3D minimum(final Point3D a, final Point3D b) {
		return new Point3D(min(a.x, b.x), min(a.y, b.y), min(a.z, b.z));
	}
	
	/**
	 * Returns a new {@code Point3D} instance with the smaller X-, Y- and Z-coordinate values of {@code a}, {@code b} and {@code c}.
	 * <p>
	 * If either {@code a}, {@code b} or {@code c} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param a a {@code Point3D} instance
	 * @param b a {@code Point3D} instance
	 * @param c a {@code Point3D} instance
	 * @return a new {@code Point3D} instance with the smaller X-, Y- and Z-coordinate values of {@code a}, {@code b} and {@code c}
	 * @throws NullPointerException thrown if, and only if, either {@code a}, {@code b} or {@code c} are {@code null}
	 */
	public static Point3D minimum(final Point3D a, final Point3D b, final Point3D c) {
		return new Point3D(min(a.x, b.x, c.x), min(a.y, b.y, c.y), min(a.z, b.z, c.z));
	}
}