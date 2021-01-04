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

import static org.macroing.math4j.MathF.abs;
import static org.macroing.math4j.MathF.atan2;
import static org.macroing.math4j.MathF.cos;
import static org.macroing.math4j.MathF.sin;
import static org.macroing.math4j.MathF.sqrt;

import java.util.Objects;

/**
 * A {@code QuaternionF} denotes a quaternion with four components, of type {@code float}, denoted by X, Y, Z and W.
 * <p>
 * This class is immutable and therefore thread-safe.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class QuaternionF {
	private static final float EPSILON = 1000.0F;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
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
	 * Constructs a new {@code QuaternionF} instance given the component values {@code 0.0F}, {@code 0.0F}, {@code 0.0F} and {@code 1.0F}.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new QuaternionF(0.0F, 0.0F, 0.0F, 1.0F);
	 * }
	 * </pre>
	 */
	public QuaternionF() {
		this(0.0F, 0.0F, 0.0F, 1.0F);
	}
	
	/**
	 * Constructs a new {@code QuaternionF} instance given the component values {@code v.x}, {@code v.y}, {@code v.z} and {@code 1.0F}.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new QuaternionF(v.x, v.y, v.z, 1.0F);
	 * }
	 * </pre>
	 * 
	 * @param v a {@link Vector3F} instance
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public QuaternionF(final Vector3F v) {
		this(v.x, v.y, v.z, 1.0F);
	}
	
	/**
	 * Constructs a new {@code QuaternionF} instance given the component values {@code v.x}, {@code v.y}, {@code v.z} and {@code v.w}.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new QuaternionF(v.x, v.y, v.z, v.w);
	 * }
	 * </pre>
	 * 
	 * @param v a {@link Vector4F} instance
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public QuaternionF(final Vector4F v) {
		this(v.x, v.y, v.z, v.w);
	}
	
	/**
	 * Constructs a new {@code QuaternionF} instance given the component values {@code x}, {@code y}, {@code z} and {@code 1.0F}.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new QuaternionF(x, y, z, 1.0F);
	 * }
	 * </pre>
	 * 
	 * @param x the value of the X-component
	 * @param y the value of the Y-component
	 * @param z the value of the Z-component
	 */
	public QuaternionF(final float x, final float y, final float z) {
		this(x, y, z, 1.0F);
	}
	
	/**
	 * Constructs a new {@code QuaternionF} instance given the component values {@code x}, {@code y}, {@code z} and {@code w}.
	 * 
	 * @param x the value of the X-component
	 * @param y the value of the Y-component
	 * @param z the value of the Z-component
	 * @param w the value of the W-component
	 */
	public QuaternionF(final float x, final float y, final float z, final float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Adds the component values of {@code q} to the component values of this {@code QuaternionF} instance.
	 * <p>
	 * Returns a new {@code QuaternionF} instance with the result of the addition.
	 * <p>
	 * If {@code q} is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * Quaternion addition is performed componentwise.
	 * 
	 * @param q the {@code QuaternionF} to add to this {@code QuaternionF} instance
	 * @return a new {@code QuaternionF} instance with the result of the addition
	 * @throws NullPointerException thrown if, and only if, {@code q} is {@code null}
	 */
	public QuaternionF add(final QuaternionF q) {
		return new QuaternionF(this.x + q.x, this.y + q.y, this.z + q.z, this.w + q.w);
	}
	
	/**
	 * Conjugates the component values of this {@code QuaternionF} instance.
	 * <p>
	 * Returns a new {@code QuaternionF} instance with the result of the conjugation.
	 * 
	 * @return a new {@code QuaternionF} instance with the result of the conjugation
	 */
	public QuaternionF conjugate() {
		return new QuaternionF(-this.x, -this.y, -this.z, this.w);
	}
	
	/**
	 * Divides the component values of this {@code QuaternionF} instance with {@code s}.
	 * <p>
	 * Returns a new {@code QuaternionF} instance with the result of the division.
	 * <p>
	 * Quaternion division is performed componentwise.
	 * 
	 * @param s the scalar value to divide this {@code QuaternionF} instance with
	 * @return a new {@code QuaternionF} instance with the result of the division
	 */
	public QuaternionF divide(final float s) {
		return new QuaternionF(this.x / s, this.y / s, this.z / s, this.w / s);
	}
	
//	TODO: Add Javadocs!
	public QuaternionF linearInterpolationNormalized(final QuaternionF q, final float fraction, final boolean isInterpolatingShortest) {
		final QuaternionF q0 = isInterpolatingShortest && dotProduct(q) < 0.0F ? q.negate() : q;
		final QuaternionF q1 = q0.subtract(this);
		final QuaternionF q2 = q1.multiply(fraction);
		final QuaternionF q3 = q2.add(this);
		final QuaternionF q4 = q3.normalize();
		
		return q4;
	}
	
//	TODO: Add Javadocs!
	public QuaternionF linearInterpolationSpherical(final QuaternionF q, final float fraction, final boolean isInterpolatingShortest) {
		final float dotProduct = dotProduct(q);
		
		final float x = isInterpolatingShortest && dotProduct < 0.0F ? -dotProduct : dotProduct;
		final float y = sqrt(1.0F - x * x);
		
		final QuaternionF q0 = MathF.equals(dotProduct, x) ? q : q.negate();
		
		if(abs(x) >= 1.0F - EPSILON) {
			return linearInterpolationNormalized(q0, fraction, false);
		}
		
		final float theta = atan2(y, x);
		
		final float s0 = sin((1.0F - fraction) * theta) / y;
		final float s1 = sin(fraction * theta) / y;
		
		final QuaternionF q1 = multiply(s0);
		final QuaternionF q2 = q0.multiply(s1);
		final QuaternionF q3 = q1.add(q2);
		
		return q3;
	}
	
	/**
	 * Multiplies the component values of this {@code QuaternionF} instance with the component values of {@code q}.
	 * <p>
	 * Returns a new {@code QuaternionF} instance with the result of the multiplication.
	 * <p>
	 * If {@code q} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param q the {@code QuaternionF} to multiply this {@code QuaternionF} instance with
	 * @return a new {@code QuaternionF} instance with the result of the multiplication
	 * @throws NullPointerException thrown if, and only if, {@code q} is {@code null}
	 */
	public QuaternionF multiply(final QuaternionF q) {
		final float x = this.x * q.w + this.w * q.x + this.y * q.z - this.z * q.y;
		final float y = this.y * q.w + this.w * q.y + this.z * q.x - this.x * q.z;
		final float z = this.z * q.w + this.w * q.z + this.x * q.y - this.y * q.x;
		final float w = this.w * q.w - this.x * q.x - this.y * q.y - this.z * q.z;
		
		return new QuaternionF(x, y, z, w);
	}
	
	/**
	 * Multiplies the component values of this {@code QuaternionF} instance with the component values of {@code v}.
	 * <p>
	 * Returns a new {@code QuaternionF} instance with the result of the multiplication.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param v the {@code Vector3F} to multiply this {@code QuaternionF} instance with
	 * @return a new {@code QuaternionF} instance with the result of the multiplication
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public QuaternionF multiply(final Vector3F v) {
		final float x = this.w * v.x + this.y * v.z - this.z * v.y;
		final float y = this.w * v.y + this.z * v.x - this.x * v.z;
		final float z = this.w * v.z + this.x * v.y - this.y * v.x;
		final float w = -this.x * v.x - this.y * v.y - this.z * v.z;
		
		return new QuaternionF(x, y, z, w);
	}
	
	/**
	 * Multiplies the component values of this {@code QuaternionF} instance with the component values of {@code v}.
	 * <p>
	 * Returns a new {@code QuaternionF} instance with the result of the multiplication.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param v the {@code Vector4F} to multiply this {@code QuaternionF} instance with
	 * @return a new {@code QuaternionF} instance with the result of the multiplication
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public QuaternionF multiply(final Vector4F v) {
		final float x = this.w * v.x + this.y * v.z - this.z * v.y;
		final float y = this.w * v.y + this.z * v.x - this.x * v.z;
		final float z = this.w * v.z + this.x * v.y - this.y * v.x;
		final float w = -this.x * v.x - this.y * v.y - this.z * v.z;
		
		return new QuaternionF(x, y, z, w);
	}
	
	/**
	 * Multiplies the component values of this {@code QuaternionF} instance with {@code s}.
	 * <p>
	 * Returns a new {@code QuaternionF} instance with the result of the multiplication.
	 * <p>
	 * Quaternion multiplication is performed componentwise.
	 * 
	 * @param s the scalar value to multiply this {@code QuaternionF} instance with
	 * @return a new {@code QuaternionF} instance with the result of the multiplication
	 */
	public QuaternionF multiply(final float s) {
		return new QuaternionF(this.x * s, this.y * s, this.z * s, this.w * s);
	}
	
	/**
	 * Negates the component values of this {@code QuaternionF} instance.
	 * <p>
	 * Returns a new {@code QuaternionF} instance with the result of the negation.
	 * 
	 * @return a new {@code QuaternionF} instance with the result of the negation
	 */
	public QuaternionF negate() {
		return new QuaternionF(-this.x, -this.y, -this.z, -this.w);
	}
	
	/**
	 * Normalizes the component values of this {@code QuaternionF} instance.
	 * <p>
	 * Returns a new {@code QuaternionF} instance with the result of the normalization.
	 * <p>
	 * To normalize a quaternion you divide it by its length.
	 * <p>
	 * When a quaternion is already normalized, normalizing it results in the same quaternion.
	 * 
	 * @return a new {@code QuaternionF} instance with the result of the normalization
	 */
	public QuaternionF normalize() {
		return divide(length());
	}
	
	/**
	 * Subtracts the component values of {@code q} from the component values of this {@code QuaternionF} instance.
	 * <p>
	 * Returns a new {@code QuaternionF} instance with the result of the subtraction.
	 * <p>
	 * If {@code q} is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * Quaternion subtraction is performed componentwise.
	 * 
	 * @param q the {@code QuaternionF} to subtract from this {@code QuaternionF} instance
	 * @return a new {@code QuaternionF} instance with the result of the subtraction
	 * @throws NullPointerException thrown if, and only if, {@code q} is {@code null}
	 */
	public QuaternionF subtract(final QuaternionF q) {
		return new QuaternionF(this.x - q.x, this.y - q.y, this.z - q.z, this.w - q.w);
	}
	
	/**
	 * Returns a {@code String} representation of this {@code QuaternionF} instance.
	 * 
	 * @return a {@code String} representation of this {@code QuaternionF} instance
	 */
	@Override
	public String toString() {
		return String.format("new QuaternionF(%s, %s, %s, %s)", Float.toString(this.x), Float.toString(this.y), Float.toString(this.z), Float.toString(this.w));
	}
	
	/**
	 * Compares {@code object} to this {@code QuaternionF} instance for equality.
	 * <p>
	 * Returns {@code true} if, and only if, {@code object} is an instance of {@code QuaternionF}, and their respective values are equal, {@code false} otherwise.
	 * 
	 * @param object the {@code Object} to compare to this {@code QuaternionF} instance for equality
	 * @return {@code true} if, and only if, {@code object} is an instance of {@code QuaternionF}, and their respective values are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object object) {
		if(object == this) {
			return true;
		} else if(!(object instanceof QuaternionF)) {
			return false;
		} else if(!MathF.equals(this.w, QuaternionF.class.cast(object).w)) {
			return false;
		} else if(!MathF.equals(this.x, QuaternionF.class.cast(object).x)) {
			return false;
		} else if(!MathF.equals(this.y, QuaternionF.class.cast(object).y)) {
			return false;
		} else if(!MathF.equals(this.z, QuaternionF.class.cast(object).z)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returns the dot product of this {@code QuaternionF} instance and {@code q}.
	 * <p>
	 * If {@code q} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param q a {@code QuaternionF}
	 * @return the dot product of this {@code QuaternionF} instance and {@code q}
	 * @throws NullPointerException thrown if, and only if, {@code q} is {@code null}
	 */
	public float dotProduct(final QuaternionF q) {
		return this.x * q.x + this.y * q.y + this.z * q.z + this.w * q.w;
	}
	
	/**
	 * Returns the length of this {@code QuaternionF} instance.
	 * 
	 * @return the length of this {@code QuaternionF} instance
	 */
	public float length() {
		return sqrt(lengthSquared());
	}
	
	/**
	 * Returns the squared length of this {@code QuaternionF} instance.
	 * 
	 * @return the squared length of this {@code QuaternionF} instance
	 */
	public float lengthSquared() {
		return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
	}
	
	/**
	 * Returns a hash code for this {@code QuaternionF} instance.
	 * 
	 * @return a hash code for this {@code QuaternionF} instance
	 */
	@Override
	public int hashCode() {
		return Objects.hash(Float.valueOf(this.w), Float.valueOf(this.x), Float.valueOf(this.y), Float.valueOf(this.z));
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns a new {@code QuaternionF} instance from a {@link Matrix44F} instance.
	 * <p>
	 * If {@code m} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param m a {@code Matrix44F} instance
	 * @return a new {@code QuaternionF} instance from a {@code Matrix44F} instance
	 * @throws NullPointerException thrown if, and only if, {@code m} is {@code null}
	 */
	public static QuaternionF fromMatrix(final Matrix44F m) {
		return new QuaternionF(doCalculateVector4F(m));
	}
	
	/**
	 * Returns a new {@code QuaternionF} instance from a {@link Vector3F} and an {@link AngleF}.
	 * <p>
	 * If either {@code v} or {@code angle} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param v a {@code Vector3F} instance
	 * @param angle an {@code AngleF} instance
	 * @return a new {@code QuaternionF} instance from a {@code Vector3F} and an {@code AngleF}
	 * @throws NullPointerException thrown if, and only if, either {@code v} or {@code angle} are {@code null}
	 */
	public static QuaternionF fromVector(final Vector3F v, final AngleF angle) {
		final AngleF angleHalf = angle.half();
		
		final float sinAngleHalf = sin(angleHalf.radians);
		final float cosAngleHalf = cos(angleHalf.radians);
		
		final float x = v.x * sinAngleHalf;
		final float y = v.y * sinAngleHalf;
		final float z = v.z * sinAngleHalf;
		final float w = cosAngleHalf;
		
		return new QuaternionF(x, y, z, w);
	}
	
	/**
	 * Returns a new {@code QuaternionF} instance from a {@link Vector4F} and an {@link AngleF}.
	 * <p>
	 * If either {@code v} or {@code angle} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param v a {@code Vector4F} instance
	 * @param angle an {@code AngleF} instance
	 * @return a new {@code QuaternionF} instance from a {@code Vector4F} and an {@code AngleF}
	 * @throws NullPointerException thrown if, and only if, either {@code v} or {@code angle} are {@code null}
	 */
	public static QuaternionF fromVector(final Vector4F v, final AngleF angle) {
		final AngleF angleHalf = angle.half();
		
		final float sinAngleHalf = sin(angleHalf.radians);
		final float cosAngleHalf = cos(angleHalf.radians);
		
		final float x = v.x * sinAngleHalf;
		final float y = v.y * sinAngleHalf;
		final float z = v.z * sinAngleHalf;
		final float w = cosAngleHalf;
		
		return new QuaternionF(x, y, z, w);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private static Vector4F doCalculateVector4F(final Matrix44F m) {
		if(m.element11 + m.element22 + m.element33 > 0.0F) {
			final float s = 0.5F / sqrt(m.element11 + m.element22 + m.element33 + 1.0F);
			
			final float x = (m.element23 - m.element32) * s;
			final float y = (m.element31 - m.element13) * s;
			final float z = (m.element12 - m.element21) * s;
			final float w = 0.25F / s;
			
			return new Vector4F(x, y, z, w).normalize();
		} else if(m.element11 > m.element22 && m.element11 > m.element33) {
			final float s = 2.0F * sqrt(1.0F + m.element11 - m.element22 - m.element33);
			final float sReciprocal = 1.0F / s;
			
			final float x = 0.25F * s;
			final float y = (m.element21 + m.element12) * sReciprocal;
			final float z = (m.element31 + m.element13) * sReciprocal;
			final float w = (m.element23 - m.element32) * sReciprocal;
			
			return new Vector4F(x, y, z, w).normalize();
		} else if(m.element22 > m.element33) {
			final float s = 2.0F * sqrt(1.0F + m.element22 - m.element11 - m.element33);
			final float sReciprocal = 1.0F / s;
			
			final float x = (m.element21 + m.element12) * sReciprocal;
			final float y = 0.25F * s;
			final float z = (m.element32 + m.element23) * sReciprocal;
			final float w = (m.element31 - m.element13) * sReciprocal;
			
			return new Vector4F(x, y, z, w).normalize();
		} else {
			final float s = 2.0F * sqrt(1.0F + m.element33 - m.element11 - m.element22);
			final float sReciprocal = 1.0F / s;
			
			final float x = (m.element31 + m.element13) * sReciprocal;
			final float y = (m.element23 + m.element32) * sReciprocal;
			final float z = 0.25F * s;
			final float w = (m.element12 - m.element21) * sReciprocal;
			
			return new Vector4F(x, y, z, w).normalize();
		}
	}
}