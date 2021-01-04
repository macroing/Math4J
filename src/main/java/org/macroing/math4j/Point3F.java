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

import static org.macroing.math4j.MathF.max;
import static org.macroing.math4j.MathF.min;
import static org.macroing.math4j.MathF.sqrt;

import java.util.Objects;

/**
 * A {@code Point3F} denotes a 3-dimensional point with three coordinates, of type {@code float}, denoted by X, Y and Z.
 * <p>
 * This class is immutable and therefore thread-safe.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class Point3F {
	/**
	 * The value of the X-coordinate.
	 */
	public final float x;
	
	/**
	 * The value of the Y-coordinate.
	 */
	public final float y;
	
	/**
	 * The value of the Z-coordinate.
	 */
	public final float z;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructs a new {@code Point3F} instance given the coordinates {@code 0.0F}, {@code 0.0F} and {@code 0.0F}.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new Point3F(0.0F, 0.0F, 0.0F);
	 * }
	 * </pre>
	 */
	public Point3F() {
		this(0.0F, 0.0F, 0.0F);
	}
	
	/**
	 * Constructs a new {@code Point3F} instance given the coordinates {@code v.x}, {@code v.y} and {@code v.z}.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new Point3F(v.x, v.y, v.z);
	 * }
	 * </pre>
	 * 
	 * @param v a {@link Vector3F} instance
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public Point3F(final Vector3F v) {
		this(v.x, v.y, v.z);
	}
	
	/**
	 * Constructs a new {@code Point3F} instance given the coordinates {@code x}, {@code y} and {@code z}.
	 * 
	 * @param x the value of the X-coordinate
	 * @param y the value of the Y-coordinate
	 * @param z the value of the Z-coordinate
	 */
	public Point3F(final float x, final float y, final float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Adds the component values of {@code v} to the coordinate values of this {@code Point3F} instance.
	 * <p>
	 * Returns a new {@code Point3F} instance that is offset from this {@code Point3F} instance in the direction of {@code v} with the distance of {@code v}.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param v a {@link Vector3F} instance with the direction of the {@code Point3F} to return
	 * @return a new {@code Point3F} instance that is offset from this {@code Point3F} instance in the direction of {@code v} with the distance of {@code v}
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public Point3F add(final Vector3F v) {
		return new Point3F(this.x + v.x, this.y + v.y, this.z + v.z);
	}
	
	/**
	 * Adds the component values of {@code v} multiplied by {@code t} to the coordinate values of this {@code Point3F} instance.
	 * <p>
	 * Returns a new {@code Point3F} instance that is offset from this {@code Point3F} instance in the direction of {@code v} with the distance of {@code v.multiply(t)}.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param v a {@link Vector3F} instance with the direction of the {@code Point3F} to return
	 * @param t the distance multiplier
	 * @return a new {@code Point3F} instance that is offset from this {@code Point3F} instance in the direction of {@code v} with the distance of {@code v.multiply(t)}
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public Point3F add(final Vector3F v, final float t) {
		return new Point3F(this.x + v.x * t, this.y + v.y * t, this.z + v.z * t);
	}
	
	/**
	 * Performs a transformation.
	 * <p>
	 * Returns a new {@code Point3F} instance with the result of the transformation.
	 * <p>
	 * If {@code m} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param m the {@link Matrix44F} to perform the transformation with
	 * @return a new {@code Point3F} instance with the result of the transformation
	 * @throws NullPointerException thrown if, and only if, {@code m} is {@code null}
	 */
	public Point3F transform(final Matrix44F m) {
		final float x = m.element11 * this.x + m.element12 * this.y + m.element13 * this.z + m.element14;
		final float y = m.element21 * this.x + m.element22 * this.y + m.element23 * this.z + m.element24;
		final float z = m.element31 * this.x + m.element32 * this.y + m.element33 * this.z + m.element34;
		
		return new Point3F(x, y, z);
	}
	
	/**
	 * Performs a transformation and a divide.
	 * <p>
	 * Returns a new {@code Point3F} instance with the result of the transformation and division.
	 * <p>
	 * If {@code m} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param m the {@link Matrix44F} to perform the transformation with
	 * @return a new {@code Point3F} instance with the result of the transformation and division
	 * @throws NullPointerException thrown if, and only if, {@code m} is {@code null}
	 */
	public Point3F transformAndDivide(final Matrix44F m) {
		final float x = m.element11 * this.x + m.element12 * this.y + m.element13 * this.z + m.element14;
		final float y = m.element21 * this.x + m.element22 * this.y + m.element23 * this.z + m.element24;
		final float z = m.element31 * this.x + m.element32 * this.y + m.element33 * this.z + m.element34;
		final float w = m.element41 * this.x + m.element42 * this.y + m.element43 * this.z + m.element44;
		
		return MathF.equals(w, 1.0F) ? new Point3F(x, y, z) : new Point3F(x / w, y / w, z / w);
	}
	
	/**
	 * Returns a {@code String} representation of this {@code Point3F} instance.
	 * 
	 * @return a {@code String} representation of this {@code Point3F} instance
	 */
	@Override
	public String toString() {
		return String.format("new Point3F(%s, %s, %s)", Float.toString(this.x), Float.toString(this.y), Float.toString(this.z));
	}
	
	/**
	 * Compares {@code object} to this {@code Point3F} instance for equality.
	 * <p>
	 * Returns {@code true} if, and only if, {@code object} is an instance of {@code Point3F}, and their respective values are equal, {@code false} otherwise.
	 * 
	 * @param object the {@code Object} to compare to this {@code Point3F} instance for equality
	 * @return {@code true} if, and only if, {@code object} is an instance of {@code Point3F}, and their respective values are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object object) {
		if(object == this) {
			return true;
		} else if(!(object instanceof Point3F)) {
			return false;
		} else if(!MathF.equals(this.x, Point3F.class.cast(object).x)) {
			return false;
		} else if(!MathF.equals(this.y, Point3F.class.cast(object).y)) {
			return false;
		} else if(!MathF.equals(this.z, Point3F.class.cast(object).z)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returns the distance from this {@code Point3F} instance to {@code p}.
	 * <p>
	 * If {@code p} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param p a {@code Point3F} instance
	 * @return the distance from this {@code Point3F} instance to {@code p}
	 * @throws NullPointerException thrown if, and only if, {@code p} is {@code null}
	 */
	public float distanceTo(final Point3F p) {
		return sqrt(distanceToSquared(p));
	}
	
	/**
	 * Returns the squared distance from this {@code Point3F} instance to {@code p}.
	 * <p>
	 * If {@code p} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param p a {@code Point3F} instance
	 * @return the squared distance from this {@code Point3F} instance to {@code p}
	 * @throws NullPointerException thrown if, and only if, {@code p} is {@code null}
	 */
	public float distanceToSquared(final Point3F p) {
		final float x = p.x - this.x;
		final float y = p.y - this.y;
		final float z = p.z - this.z;
		
		return x * x + y * y + z * z;
	}
	
	/**
	 * Returns the value of the X-coordinate.
	 * 
	 * @return the value of the X-coordinate
	 */
	public float getX() {
		return this.x;
	}
	
	/**
	 * Returns the value of the Y-coordinate.
	 * 
	 * @return the value of the Y-coordinate
	 */
	public float getY() {
		return this.y;
	}
	
	/**
	 * Returns the value of the Z-coordinate.
	 * 
	 * @return the value of the Z-coordinate
	 */
	public float getZ() {
		return this.z;
	}
	
	/**
	 * Returns a hash code for this {@code Point3F} instance.
	 * 
	 * @return a hash code for this {@code Point3F} instance
	 */
	@Override
	public int hashCode() {
		return Objects.hash(Float.valueOf(this.x), Float.valueOf(this.y), Float.valueOf(this.z));
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns a new {@code Point3F} instance with the greatest X-, Y- and Z-coordinate values.
	 * 
	 * @return a new {@code Point3F} instance with the greatest X-, Y- and Z-coordinate values
	 */
	public static Point3F maximum() {
		return new Point3F(Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE);
	}
	
	/**
	 * Returns a new {@code Point3F} instance with the greater X-, Y- and Z-coordinate values of {@code a} and {@code b}.
	 * <p>
	 * If either {@code a} or {@code b} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param a a {@code Point3F} instance
	 * @param b a {@code Point3F} instance
	 * @return a new {@code Point3F} instance with the greater X-, Y- and Z-coordinate values of {@code a} and {@code b}
	 * @throws NullPointerException thrown if, and only if, either {@code a} or {@code b} are {@code null}
	 */
	public static Point3F maximum(final Point3F a, final Point3F b) {
		return new Point3F(max(a.x, b.x), max(a.y, b.y), max(a.z, b.z));
	}
	
	/**
	 * Returns a new {@code Point3F} instance with the greater X-, Y- and Z-coordinate values of {@code a}, {@code b} and {@code c}.
	 * <p>
	 * If either {@code a}, {@code b} or {@code c} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param a a {@code Point3F} instance
	 * @param b a {@code Point3F} instance
	 * @param c a {@code Point3F} instance
	 * @return a new {@code Point3F} instance with the greater X-, Y- and Z-coordinate values of {@code a}, {@code b} and {@code c}
	 * @throws NullPointerException thrown if, and only if, either {@code a}, {@code b} or {@code c} are {@code null}
	 */
	public static Point3F maximum(final Point3F a, final Point3F b, final Point3F c) {
		return new Point3F(max(a.x, b.x, c.x), max(a.y, b.y, c.y), max(a.z, b.z, c.z));
	}
	
	/**
	 * Returns a new {@code Point3F} instance located in the middle of {@code a} and {@code b}.
	 * <p>
	 * If either {@code a} or {@code b} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param a a {@code Point3F} instance that represents one of two end points of a line segment
	 * @param b a {@code Point3F} instance that represents one of two end points of a line segment
	 * @return a new {@code Point3F} instance located in the middle of {@code a} and {@code b}
	 * @throws NullPointerException thrown if, and only if, either {@code a} or {@code b} are {@code null}
	 */
	public static Point3F midpoint(final Point3F a, final Point3F b) {
		return new Point3F((a.x + b.x) * 0.5F, (a.y + b.y) * 0.5F, (a.z + b.z) * 0.5F);
	}
	
	/**
	 * Returns a new {@code Point3F} instance with the smallest X-, Y- and Z-coordinate values.
	 * 
	 * @return a new {@code Point3F} instance with the smallest X-, Y- and Z-coordinate values
	 */
	public static Point3F minimum() {
		return new Point3F(Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE);
	}
	
	/**
	 * Returns a new {@code Point3F} instance with the smaller X-, Y- and Z-coordinate values of {@code a} and {@code b}.
	 * <p>
	 * If either {@code a} or {@code b} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param a a {@code Point3F} instance
	 * @param b a {@code Point3F} instance
	 * @return a new {@code Point3F} instance with the smaller X-, Y- and Z-coordinate values of {@code a} and {@code b}
	 * @throws NullPointerException thrown if, and only if, either {@code a} or {@code b} are {@code null}
	 */
	public static Point3F minimum(final Point3F a, final Point3F b) {
		return new Point3F(min(a.x, b.x), min(a.y, b.y), min(a.z, b.z));
	}
	
	/**
	 * Returns a new {@code Point3F} instance with the smaller X-, Y- and Z-coordinate values of {@code a}, {@code b} and {@code c}.
	 * <p>
	 * If either {@code a}, {@code b} or {@code c} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param a a {@code Point3F} instance
	 * @param b a {@code Point3F} instance
	 * @param c a {@code Point3F} instance
	 * @return a new {@code Point3F} instance with the smaller X-, Y- and Z-coordinate values of {@code a}, {@code b} and {@code c}
	 * @throws NullPointerException thrown if, and only if, either {@code a}, {@code b} or {@code c} are {@code null}
	 */
	public static Point3F minimum(final Point3F a, final Point3F b, final Point3F c) {
		return new Point3F(min(a.x, b.x, c.x), min(a.y, b.y, c.y), min(a.z, b.z, c.z));
	}
}