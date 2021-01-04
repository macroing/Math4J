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

import static org.macroing.math4j.MathF.sqrt;

import java.util.Objects;

/**
 * A {@code Vector4F} denotes a 4-dimensional vector with four components, of type {@code float}, denoted by X, Y, Z and W.
 * <p>
 * This class is immutable and therefore thread-safe.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class Vector4F {
	/**
	 * The value of the W-component.
	 */
	public final float w;
	
	/**
	 * The value of the X-component.
	 */
	public final float x;
	
	/**
	 * The value of the Y-component.
	 */
	public final float y;
	
	/**
	 * The value of the Z-component.
	 */
	public final float z;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructs a new {@code Vector4F} instance given the component values {@code 0.0F}, {@code 0.0F}, {@code 0.0F} and {@code 1.0F}.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new Vector4F(0.0F, 0.0F, 0.0F);
	 * }
	 * </pre>
	 */
	public Vector4F() {
		this(0.0F, 0.0F, 0.0F, 0.0F);
	}
	
	/**
	 * Constructs a new {@code Vector4F} instance given the component values {@code v.x}, {@code v.y}, {@code v.z} and {@code 1.0F}.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new Vector4F(v.x, v.y, v.z);
	 * }
	 * </pre>
	 * 
	 * @param v a {@link Vector3F} instance
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public Vector4F(final Vector3F v) {
		this(v.x, v.y, v.z);
	}
	
	/**
	 * Constructs a new {@code Vector4F} instance given the component values {@code x}, {@code y}, {@code z} and {@code 1.0F}.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new Vector4F(x, y, z, 1.0F);
	 * }
	 * </pre>
	 * 
	 * @param x the value of the X-component
	 * @param y the value of the Y-component
	 * @param z the value of the Z-component
	 */
	public Vector4F(final float x, final float y, final float z) {
		this(x, y, z, 1.0F);
	}
	
	/**
	 * Constructs a new {@code Vector4F} instance given the component values {@code x}, {@code y}, {@code z} and {@code w}.
	 * 
	 * @param x the value of the X-component
	 * @param y the value of the Y-component
	 * @param z the value of the Z-component
	 * @param w the value of the W-component
	 */
	public Vector4F(final float x, final float y, final float z, final float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns a {@code String} representation of this {@code Vector4F} instance.
	 * 
	 * @return a {@code String} representation of this {@code Vector4F} instance
	 */
	@Override
	public String toString() {
		return String.format("new Vector4F(%s, %s, %s, %s)", Float.toString(this.x), Float.toString(this.y), Float.toString(this.z), Float.toString(this.w));
	}
	
	/**
	 * Adds the component values of {@code v} to the component values of this {@code Vector4F} instance.
	 * <p>
	 * Returns a new {@code Vector4F} instance with the result of the addition.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * Vector addition is performed componentwise.
	 * 
	 * @param v the {@code Vector4F} to add to this {@code Vector4F} instance
	 * @return a new {@code Vector4F} instance with the result of the addition
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public Vector4F add(final Vector4F v) {
		return new Vector4F(this.x + v.x, this.y + v.y, this.z + v.z, this.w + v.w);
	}
	
	/**
	 * Computes the cross product of this {@code Vector4F} instance and {@code v}.
	 * <p>
	 * Returns a new {@code Vector4F} instance with the result of the operation.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param v a {@code Vector4F} instance
	 * @return a new {@code Vector4F} instance with the result of the operation
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public Vector4F crossProduct(final Vector4F v) {
		return new Vector4F(this.y * v.z - this.z * v.y, this.z * v.x - this.x * v.z, this.x * v.y - this.y * v.x, 0.0F);
	}
	
	/**
	 * Divides the component values of this {@code Vector4F} instance with {@code s}.
	 * <p>
	 * Returns a new {@code Vector4F} instance with the result of the division.
	 * <p>
	 * Vector division is performed componentwise.
	 * 
	 * @param s the scalar value to divide this {@code Vector4F} instance with
	 * @return a new {@code Vector4F} instance with the result of the division
	 */
	public Vector4F divide(final float s) {
		return new Vector4F(this.x / s, this.y / s, this.z / s, this.w / s);
	}
	
	/**
	 * Multiplies the component values of this {@code Vector4F} instance with {@code s}.
	 * <p>
	 * Returns a new {@code Vector4F} instance with the result of the multiplication.
	 * <p>
	 * Vector multiplication is performed componentwise.
	 * 
	 * @param s the scalar value to multiply this {@code Vector4F} instance with
	 * @return a new {@code Vector4F} instance with the result of the multiplication
	 */
	public Vector4F multiply(final float s) {
		return new Vector4F(this.x * s, this.y * s, this.z * s, this.w * s);
	}
	
	/**
	 * Normalizes the component values of this {@code Vector4F} instance.
	 * <p>
	 * Returns a new {@code Vector4F} instance with the result of the normalization.
	 * <p>
	 * To normalize a vector you divide it by its length.
	 * <p>
	 * When a vector is already normalized, normalizing it results in the same vector.
	 * 
	 * @return a new {@code Vector4F} instance with the result of the normalization
	 */
	public Vector4F normalize() {
		return divide(length());
	}
	
	/**
	 * Subtracts the component values of {@code v} from the component values of this {@code Vector4F} instance.
	 * <p>
	 * Returns a new {@code Vector4F} instance with the result of the subtraction.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * Vector subtraction is performed componentwise.
	 * 
	 * @param v the {@code Vector4F} to subtract from this {@code Vector4F} instance
	 * @return a new {@code Vector4F} instance with the result of the subtraction
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public Vector4F subtract(final Vector4F v) {
		return new Vector4F(this.x - v.x, this.y - v.y, this.z - v.z, this.w - v.w);
	}
	
	/**
	 * Compares {@code object} to this {@code Vector4F} instance for equality.
	 * <p>
	 * Returns {@code true} if, and only if, {@code object} is an instance of {@code Vector4F}, and their respective values are equal, {@code false} otherwise.
	 * 
	 * @param object the {@code Object} to compare to this {@code Vector4F} instance for equality
	 * @return {@code true} if, and only if, {@code object} is an instance of {@code Vector4F}, and their respective values are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object object) {
		if(object == this) {
			return true;
		} else if(!(object instanceof Vector4F)) {
			return false;
		} else if(!MathF.equals(this.w, Vector4F.class.cast(object).w)) {
			return false;
		} else if(!MathF.equals(this.x, Vector4F.class.cast(object).x)) {
			return false;
		} else if(!MathF.equals(this.y, Vector4F.class.cast(object).y)) {
			return false;
		} else if(!MathF.equals(this.z, Vector4F.class.cast(object).z)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returns the dot product of this {@code Vector4F} instance and {@code v}.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param v a {@code Vector4F}
	 * @return the dot product of this {@code Vector4F} instance and {@code v}
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public float dotProduct(final Vector4F v) {
		return this.x * v.x + this.y * v.y + this.z * v.z + this.w * v.w;
	}
	
	/**
	 * Returns the length of this {@code Vector4F} instance.
	 * 
	 * @return the length of this {@code Vector4F} instance
	 */
	public float length() {
		return sqrt(lengthSquared());
	}
	
	/**
	 * Returns the squared length of this {@code Vector4F} instance.
	 * 
	 * @return the squared length of this {@code Vector4F} instance
	 */
	public float lengthSquared() {
		return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
	}
	
	/**
	 * Returns a hash code for this {@code Vector4F} instance.
	 * 
	 * @return a hash code for this {@code Vector4F} instance
	 */
	@Override
	public int hashCode() {
		return Objects.hash(Float.valueOf(this.w), Float.valueOf(this.x), Float.valueOf(this.y), Float.valueOf(this.z));
	}
}