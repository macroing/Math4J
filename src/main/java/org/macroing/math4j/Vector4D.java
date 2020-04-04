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
 * A {@code Vector4D} denotes a 4-dimensional vector with four components, of type {@code double}, denoted by X, Y, Z and W.
 * <p>
 * This class is immutable and therefore thread-safe.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class Vector4D {
	/**
	 * The value of the W-component.
	 */
	public final double w;
	
	/**
	 * The value of the X-component.
	 */
	public final double x;
	
	/**
	 * The value of the Y-component.
	 */
	public final double y;
	
	/**
	 * The value of the Z-component.
	 */
	public final double z;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructs a new {@code Vector4D} instance given the component values {@code 0.0D}, {@code 0.0D}, {@code 0.0D} and {@code 1.0D}.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new Vector4D(0.0D, 0.0D, 0.0D);
	 * }
	 * </pre>
	 */
	public Vector4D() {
		this(0.0D, 0.0D, 0.0D, 0.0D);
	}
	
	/**
	 * Constructs a new {@code Vector4D} instance given the component values {@code v.x}, {@code v.y}, {@code v.z} and {@code 1.0D}.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new Vector4D(v.x, v.y, v.z);
	 * }
	 * </pre>
	 * 
	 * @param v a {@link Vector3D} instance
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public Vector4D(final Vector3D v) {
		this(v.x, v.y, v.z);
	}
	
	/**
	 * Constructs a new {@code Vector4D} instance given the component values {@code x}, {@code y}, {@code z} and {@code 1.0D}.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new Vector4D(x, y, z, 1.0D);
	 * }
	 * </pre>
	 * 
	 * @param x the value of the X-component
	 * @param y the value of the Y-component
	 * @param z the value of the Z-component
	 */
	public Vector4D(final double x, final double y, final double z) {
		this(x, y, z, 1.0D);
	}
	
	/**
	 * Constructs a new {@code Vector4D} instance given the component values {@code x}, {@code y}, {@code z} and {@code w}.
	 * 
	 * @param x the value of the X-component
	 * @param y the value of the Y-component
	 * @param z the value of the Z-component
	 * @param w the value of the W-component
	 */
	public Vector4D(final double x, final double y, final double z, final double w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns a {@code String} representation of this {@code Vector4D} instance.
	 * 
	 * @return a {@code String} representation of this {@code Vector4D} instance
	 */
	@Override
	public String toString() {
		return String.format("new Vector4D(%s, %s, %s, %s)", Double.toString(this.x), Double.toString(this.y), Double.toString(this.z), Double.toString(this.w));
	}
	
	/**
	 * Adds the component values of {@code v} to the component values of this {@code Vector4D} instance.
	 * <p>
	 * Returns a new {@code Vector4D} instance with the result of the addition.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * Vector addition is performed componentwise.
	 * 
	 * @param v the {@code Vector4D} to add to this {@code Vector4D} instance
	 * @return a new {@code Vector4D} instance with the result of the addition
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public Vector4D add(final Vector4D v) {
		return new Vector4D(this.x + v.x, this.y + v.y, this.z + v.z, this.w + v.w);
	}
	
	/**
	 * Computes the cross product of this {@code Vector4D} instance and {@code v}.
	 * <p>
	 * Returns a new {@code Vector4D} instance with the result of the operation.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param v a {@code Vector4D} instance
	 * @return a new {@code Vector4D} instance with the result of the operation
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public Vector4D crossProduct(final Vector4D v) {
		return new Vector4D(this.y * v.z - this.z * v.y, this.z * v.x - this.x * v.z, this.x * v.y - this.y * v.x, 0.0D);
	}
	
	/**
	 * Divides the component values of this {@code Vector4D} instance with {@code s}.
	 * <p>
	 * Returns a new {@code Vector4D} instance with the result of the division.
	 * <p>
	 * Vector division is performed componentwise.
	 * 
	 * @param s the scalar value to divide this {@code Vector4D} instance with
	 * @return a new {@code Vector4D} instance with the result of the division
	 */
	public Vector4D divide(final double s) {
		return new Vector4D(this.x / s, this.y / s, this.z / s, this.w / s);
	}
	
	/**
	 * Multiplies the component values of this {@code Vector4D} instance with {@code s}.
	 * <p>
	 * Returns a new {@code Vector4D} instance with the result of the multiplication.
	 * <p>
	 * Vector multiplication is performed componentwise.
	 * 
	 * @param s the scalar value to multiply this {@code Vector4D} instance with
	 * @return a new {@code Vector4D} instance with the result of the multiplication
	 */
	public Vector4D multiply(final double s) {
		return new Vector4D(this.x * s, this.y * s, this.z * s, this.w * s);
	}
	
	/**
	 * Normalizes the component values of this {@code Vector4D} instance.
	 * <p>
	 * Returns a new {@code Vector4D} instance with the result of the normalization.
	 * <p>
	 * To normalize a vector you divide it by its length.
	 * <p>
	 * When a vector is already normalized, normalizing it results in the same vector.
	 * 
	 * @return a new {@code Vector4D} instance with the result of the normalization
	 */
	public Vector4D normalize() {
		return divide(length());
	}
	
	/**
	 * Subtracts the component values of {@code v} from the component values of this {@code Vector4D} instance.
	 * <p>
	 * Returns a new {@code Vector4D} instance with the result of the subtraction.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * Vector subtraction is performed componentwise.
	 * 
	 * @param v the {@code Vector4D} to subtract from this {@code Vector4D} instance
	 * @return a new {@code Vector4D} instance with the result of the subtraction
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public Vector4D subtract(final Vector4D v) {
		return new Vector4D(this.x - v.x, this.y - v.y, this.z - v.z, this.w - v.w);
	}
	
	/**
	 * Compares {@code object} to this {@code Vector4D} instance for equality.
	 * <p>
	 * Returns {@code true} if, and only if, {@code object} is an instance of {@code Vector4D}, and their respective values are equal, {@code false} otherwise.
	 * 
	 * @param object the {@code Object} to compare to this {@code Vector4D} instance for equality
	 * @return {@code true} if, and only if, {@code object} is an instance of {@code Vector4D}, and their respective values are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object object) {
		if(object == this) {
			return true;
		} else if(!(object instanceof Vector4D)) {
			return false;
		} else if(!MathD.equals(this.w, Vector4D.class.cast(object).w)) {
			return false;
		} else if(!MathD.equals(this.x, Vector4D.class.cast(object).x)) {
			return false;
		} else if(!MathD.equals(this.y, Vector4D.class.cast(object).y)) {
			return false;
		} else if(!MathD.equals(this.z, Vector4D.class.cast(object).z)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returns the dot product of this {@code Vector4D} instance and {@code v}.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param v a {@code Vector4D}
	 * @return the dot product of this {@code Vector4D} instance and {@code v}
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public double dotProduct(final Vector4D v) {
		return this.x * v.x + this.y * v.y + this.z * v.z + this.w * v.w;
	}
	
	/**
	 * Returns the length of this {@code Vector4D} instance.
	 * 
	 * @return the length of this {@code Vector4D} instance
	 */
	public double length() {
		return sqrt(lengthSquared());
	}
	
	/**
	 * Returns the squared length of this {@code Vector4D} instance.
	 * 
	 * @return the squared length of this {@code Vector4D} instance
	 */
	public double lengthSquared() {
		return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
	}
	
	/**
	 * Returns a hash code for this {@code Vector4D} instance.
	 * 
	 * @return a hash code for this {@code Vector4D} instance
	 */
	@Override
	public int hashCode() {
		return Objects.hash(Double.valueOf(this.w), Double.valueOf(this.x), Double.valueOf(this.y), Double.valueOf(this.z));
	}
}