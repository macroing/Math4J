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

import static org.macroing.math4j.MathD.abs;
import static org.macroing.math4j.MathD.atan2;
import static org.macroing.math4j.MathD.cos;
import static org.macroing.math4j.MathD.sin;
import static org.macroing.math4j.MathD.sqrt;

import java.util.Objects;

/**
 * A {@code QuaternionD} denotes a quaternion with four components, of type {@code double}, denoted by X, Y, Z and W.
 * <p>
 * This class is immutable and therefore thread-safe.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class QuaternionD {
	private static final double EPSILON = 1000.0D;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
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
	 * Constructs a new {@code QuaternionD} instance given the component values {@code 0.0D}, {@code 0.0D}, {@code 0.0D} and {@code 1.0D}.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new QuaternionD(0.0D, 0.0D, 0.0D, 1.0D);
	 * }
	 * </pre>
	 */
	public QuaternionD() {
		this(0.0D, 0.0D, 0.0D, 1.0D);
	}
	
	/**
	 * Constructs a new {@code QuaternionD} instance given the component values {@code v.x}, {@code v.y}, {@code v.z} and {@code 1.0D}.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new QuaternionD(v.x, v.y, v.z, 1.0D);
	 * }
	 * </pre>
	 * 
	 * @param v a {@link Vector3D} instance
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public QuaternionD(final Vector3D v) {
		this(v.x, v.y, v.z, 1.0D);
	}
	
	/**
	 * Constructs a new {@code QuaternionD} instance given the component values {@code v.x}, {@code v.y}, {@code v.z} and {@code v.w}.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new QuaternionD(v.x, v.y, v.z, v.w);
	 * }
	 * </pre>
	 * 
	 * @param v a {@link Vector4D} instance
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public QuaternionD(final Vector4D v) {
		this(v.x, v.y, v.z, v.w);
	}
	
	/**
	 * Constructs a new {@code QuaternionD} instance given the component values {@code x}, {@code y}, {@code z} and {@code 1.0D}.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new QuaternionD(x, y, z, 1.0D);
	 * }
	 * </pre>
	 * 
	 * @param x the value of the X-component
	 * @param y the value of the Y-component
	 * @param z the value of the Z-component
	 */
	public QuaternionD(final double x, final double y, final double z) {
		this(x, y, z, 1.0D);
	}
	
	/**
	 * Constructs a new {@code QuaternionD} instance given the component values {@code x}, {@code y}, {@code z} and {@code w}.
	 * 
	 * @param x the value of the X-component
	 * @param y the value of the Y-component
	 * @param z the value of the Z-component
	 * @param w the value of the W-component
	 */
	public QuaternionD(final double x, final double y, final double z, final double w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Adds the component values of {@code q} to the component values of this {@code QuaternionD} instance.
	 * <p>
	 * Returns a new {@code QuaternionD} instance with the result of the addition.
	 * <p>
	 * If {@code q} is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * Quaternion addition is performed componentwise.
	 * 
	 * @param q the {@code QuaternionD} to add to this {@code QuaternionD} instance
	 * @return a new {@code QuaternionD} instance with the result of the addition
	 * @throws NullPointerException thrown if, and only if, {@code q} is {@code null}
	 */
	public QuaternionD add(final QuaternionD q) {
		return new QuaternionD(this.x + q.x, this.y + q.y, this.z + q.z, this.w + q.w);
	}
	
	/**
	 * Conjugates the component values of this {@code QuaternionD} instance.
	 * <p>
	 * Returns a new {@code QuaternionD} instance with the result of the conjugation.
	 * 
	 * @return a new {@code QuaternionD} instance with the result of the conjugation
	 */
	public QuaternionD conjugate() {
		return new QuaternionD(-this.x, -this.y, -this.z, this.w);
	}
	
	/**
	 * Divides the component values of this {@code QuaternionD} instance with {@code s}.
	 * <p>
	 * Returns a new {@code QuaternionD} instance with the result of the division.
	 * <p>
	 * Quaternion division is performed componentwise.
	 * 
	 * @param s the scalar value to divide this {@code QuaternionD} instance with
	 * @return a new {@code QuaternionD} instance with the result of the division
	 */
	public QuaternionD divide(final double s) {
		return new QuaternionD(this.x / s, this.y / s, this.z / s, this.w / s);
	}
	
//	TODO: Add Javadocs!
	public QuaternionD linearInterpolationNormalized(final QuaternionD q, final double fraction, final boolean isInterpolatingShortest) {
		final QuaternionD q0 = isInterpolatingShortest && dotProduct(q) < 0.0D ? q.negate() : q;
		final QuaternionD q1 = q0.subtract(this);
		final QuaternionD q2 = q1.multiply(fraction);
		final QuaternionD q3 = q2.add(this);
		final QuaternionD q4 = q3.normalize();
		
		return q4;
	}
	
//	TODO: Add Javadocs!
	public QuaternionD linearInterpolationSpherical(final QuaternionD q, final double fraction, final boolean isInterpolatingShortest) {
		final double dotProduct = dotProduct(q);
		
		final double x = isInterpolatingShortest && dotProduct < 0.0D ? -dotProduct : dotProduct;
		final double y = sqrt(1.0D - x * x);
		
		final QuaternionD q0 = MathD.equals(dotProduct, x) ? q : q.negate();
		
		if(abs(x) >= 1.0D - EPSILON) {
			return linearInterpolationNormalized(q0, fraction, false);
		}
		
		final double theta = atan2(y, x);
		
		final double s0 = sin((1.0D - fraction) * theta) / y;
		final double s1 = sin(fraction * theta) / y;
		
		final QuaternionD q1 = multiply(s0);
		final QuaternionD q2 = q0.multiply(s1);
		final QuaternionD q3 = q1.add(q2);
		
		return q3;
	}
	
	/**
	 * Multiplies the component values of this {@code QuaternionD} instance with the component values of {@code q}.
	 * <p>
	 * Returns a new {@code QuaternionD} instance with the result of the multiplication.
	 * <p>
	 * If {@code q} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param q the {@code QuaternionD} to multiply this {@code QuaternionD} instance with
	 * @return a new {@code QuaternionD} instance with the result of the multiplication
	 * @throws NullPointerException thrown if, and only if, {@code q} is {@code null}
	 */
	public QuaternionD multiply(final QuaternionD q) {
		final double x = this.x * q.w + this.w * q.x + this.y * q.z - this.z * q.y;
		final double y = this.y * q.w + this.w * q.y + this.z * q.x - this.x * q.z;
		final double z = this.z * q.w + this.w * q.z + this.x * q.y - this.y * q.x;
		final double w = this.w * q.w - this.x * q.x - this.y * q.y - this.z * q.z;
		
		return new QuaternionD(x, y, z, w);
	}
	
	/**
	 * Multiplies the component values of this {@code QuaternionD} instance with the component values of {@code v}.
	 * <p>
	 * Returns a new {@code QuaternionD} instance with the result of the multiplication.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param v the {@code Vector3D} to multiply this {@code QuaternionD} instance with
	 * @return a new {@code QuaternionD} instance with the result of the multiplication
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public QuaternionD multiply(final Vector3D v) {
		final double x = this.w * v.x + this.y * v.z - this.z * v.y;
		final double y = this.w * v.y + this.z * v.x - this.x * v.z;
		final double z = this.w * v.z + this.x * v.y - this.y * v.x;
		final double w = -this.x * v.x - this.y * v.y - this.z * v.z;
		
		return new QuaternionD(x, y, z, w);
	}
	
	/**
	 * Multiplies the component values of this {@code QuaternionD} instance with the component values of {@code v}.
	 * <p>
	 * Returns a new {@code QuaternionD} instance with the result of the multiplication.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param v the {@code Vector4D} to multiply this {@code QuaternionD} instance with
	 * @return a new {@code QuaternionD} instance with the result of the multiplication
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public QuaternionD multiply(final Vector4D v) {
		final double x = this.w * v.x + this.y * v.z - this.z * v.y;
		final double y = this.w * v.y + this.z * v.x - this.x * v.z;
		final double z = this.w * v.z + this.x * v.y - this.y * v.x;
		final double w = -this.x * v.x - this.y * v.y - this.z * v.z;
		
		return new QuaternionD(x, y, z, w);
	}
	
	/**
	 * Multiplies the component values of this {@code QuaternionD} instance with {@code s}.
	 * <p>
	 * Returns a new {@code QuaternionD} instance with the result of the multiplication.
	 * <p>
	 * Quaternion multiplication is performed componentwise.
	 * 
	 * @param s the scalar value to multiply this {@code QuaternionD} instance with
	 * @return a new {@code QuaternionD} instance with the result of the multiplication
	 */
	public QuaternionD multiply(final double s) {
		return new QuaternionD(this.x * s, this.y * s, this.z * s, this.w * s);
	}
	
	/**
	 * Negates the component values of this {@code QuaternionD} instance.
	 * <p>
	 * Returns a new {@code QuaternionD} instance with the result of the negation.
	 * 
	 * @return a new {@code QuaternionD} instance with the result of the negation
	 */
	public QuaternionD negate() {
		return new QuaternionD(-this.x, -this.y, -this.z, -this.w);
	}
	
	/**
	 * Normalizes the component values of this {@code QuaternionD} instance.
	 * <p>
	 * Returns a new {@code QuaternionD} instance with the result of the normalization.
	 * <p>
	 * To normalize a quaternion you divide it by its length.
	 * <p>
	 * When a quaternion is already normalized, normalizing it results in the same quaternion.
	 * 
	 * @return a new {@code QuaternionD} instance with the result of the normalization
	 */
	public QuaternionD normalize() {
		return divide(length());
	}
	
	/**
	 * Subtracts the component values of {@code q} from the component values of this {@code QuaternionD} instance.
	 * <p>
	 * Returns a new {@code QuaternionD} instance with the result of the subtraction.
	 * <p>
	 * If {@code q} is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * Quaternion subtraction is performed componentwise.
	 * 
	 * @param q the {@code QuaternionD} to subtract from this {@code QuaternionD} instance
	 * @return a new {@code QuaternionD} instance with the result of the subtraction
	 * @throws NullPointerException thrown if, and only if, {@code q} is {@code null}
	 */
	public QuaternionD subtract(final QuaternionD q) {
		return new QuaternionD(this.x - q.x, this.y - q.y, this.z - q.z, this.w - q.w);
	}
	
	/**
	 * Returns a {@code String} representation of this {@code QuaternionD} instance.
	 * 
	 * @return a {@code String} representation of this {@code QuaternionD} instance
	 */
	@Override
	public String toString() {
		return String.format("new QuaternionD(%s, %s, %s, %s)", Double.toString(this.x), Double.toString(this.y), Double.toString(this.z), Double.toString(this.w));
	}
	
	/**
	 * Compares {@code object} to this {@code QuaternionD} instance for equality.
	 * <p>
	 * Returns {@code true} if, and only if, {@code object} is an instance of {@code QuaternionD}, and their respective values are equal, {@code false} otherwise.
	 * 
	 * @param object the {@code Object} to compare to this {@code QuaternionD} instance for equality
	 * @return {@code true} if, and only if, {@code object} is an instance of {@code QuaternionD}, and their respective values are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object object) {
		if(object == this) {
			return true;
		} else if(!(object instanceof QuaternionD)) {
			return false;
		} else if(!MathD.equals(this.w, QuaternionD.class.cast(object).w)) {
			return false;
		} else if(!MathD.equals(this.x, QuaternionD.class.cast(object).x)) {
			return false;
		} else if(!MathD.equals(this.y, QuaternionD.class.cast(object).y)) {
			return false;
		} else if(!MathD.equals(this.z, QuaternionD.class.cast(object).z)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returns the dot product of this {@code QuaternionD} instance and {@code q}.
	 * <p>
	 * If {@code q} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param q a {@code QuaternionD}
	 * @return the dot product of this {@code QuaternionD} instance and {@code q}
	 * @throws NullPointerException thrown if, and only if, {@code q} is {@code null}
	 */
	public double dotProduct(final QuaternionD q) {
		return this.x * q.x + this.y * q.y + this.z * q.z + this.w * q.w;
	}
	
	/**
	 * Returns the length of this {@code QuaternionD} instance.
	 * 
	 * @return the length of this {@code QuaternionD} instance
	 */
	public double length() {
		return sqrt(lengthSquared());
	}
	
	/**
	 * Returns the squared length of this {@code QuaternionD} instance.
	 * 
	 * @return the squared length of this {@code QuaternionD} instance
	 */
	public double lengthSquared() {
		return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
	}
	
	/**
	 * Returns a hash code for this {@code QuaternionD} instance.
	 * 
	 * @return a hash code for this {@code QuaternionD} instance
	 */
	@Override
	public int hashCode() {
		return Objects.hash(Double.valueOf(this.w), Double.valueOf(this.x), Double.valueOf(this.y), Double.valueOf(this.z));
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns a new {@code QuaternionD} instance from a {@link Matrix44D} instance.
	 * <p>
	 * If {@code m} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param m a {@code Matrix44D} instance
	 * @return a new {@code QuaternionD} instance from a {@code Matrix44D} instance
	 * @throws NullPointerException thrown if, and only if, {@code m} is {@code null}
	 */
	public static QuaternionD fromMatrix(final Matrix44D m) {
		return new QuaternionD(doCalculateVector4D(m));
	}
	
	/**
	 * Returns a new {@code QuaternionD} instance from a {@link Vector3D} and an {@link AngleD}.
	 * <p>
	 * If either {@code v} or {@code angle} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param v a {@code Vector3D} instance
	 * @param angle an {@code AngleD} instance
	 * @return a new {@code QuaternionD} instance from a {@code Vector3D} and an {@code AngleD}
	 * @throws NullPointerException thrown if, and only if, either {@code v} or {@code angle} are {@code null}
	 */
	public static QuaternionD fromVector(final Vector3D v, final AngleD angle) {
		final AngleD angleHalf = angle.half();
		
		final double sinAngleHalf = sin(angleHalf.radians);
		final double cosAngleHalf = cos(angleHalf.radians);
		
		final double x = v.x * sinAngleHalf;
		final double y = v.y * sinAngleHalf;
		final double z = v.z * sinAngleHalf;
		final double w = cosAngleHalf;
		
		return new QuaternionD(x, y, z, w);
	}
	
	/**
	 * Returns a new {@code QuaternionD} instance from a {@link Vector4D} and an {@link AngleD}.
	 * <p>
	 * If either {@code v} or {@code angle} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param v a {@code Vector4D} instance
	 * @param angle an {@code AngleD} instance
	 * @return a new {@code QuaternionD} instance from a {@code Vector4D} and an {@code AngleD}
	 * @throws NullPointerException thrown if, and only if, either {@code v} or {@code angle} are {@code null}
	 */
	public static QuaternionD fromVector(final Vector4D v, final AngleD angle) {
		final AngleD angleHalf = angle.half();
		
		final double sinAngleHalf = sin(angleHalf.radians);
		final double cosAngleHalf = cos(angleHalf.radians);
		
		final double x = v.x * sinAngleHalf;
		final double y = v.y * sinAngleHalf;
		final double z = v.z * sinAngleHalf;
		final double w = cosAngleHalf;
		
		return new QuaternionD(x, y, z, w);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private static Vector4D doCalculateVector4D(final Matrix44D m) {
		if(m.element11 + m.element22 + m.element33 > 0.0F) {
			final double s = 0.5D / sqrt(m.element11 + m.element22 + m.element33 + 1.0D);
			
			final double x = (m.element23 - m.element32) * s;
			final double y = (m.element31 - m.element13) * s;
			final double z = (m.element12 - m.element21) * s;
			final double w = 0.25D / s;
			
			return new Vector4D(x, y, z, w).normalize();
		} else if(m.element11 > m.element22 && m.element11 > m.element33) {
			final double s = 2.0D * sqrt(1.0D + m.element11 - m.element22 - m.element33);
			final double sReciprocal = 1.0D / s;
			
			final double x = 0.25D * s;
			final double y = (m.element21 + m.element12) * sReciprocal;
			final double z = (m.element31 + m.element13) * sReciprocal;
			final double w = (m.element23 - m.element32) * sReciprocal;
			
			return new Vector4D(x, y, z, w).normalize();
		} else if(m.element22 > m.element33) {
			final double s = 2.0D * sqrt(1.0D + m.element22 - m.element11 - m.element33);
			final double sReciprocal = 1.0D / s;
			
			final double x = (m.element21 + m.element12) * sReciprocal;
			final double y = 0.25D * s;
			final double z = (m.element32 + m.element23) * sReciprocal;
			final double w = (m.element31 - m.element13) * sReciprocal;
			
			return new Vector4D(x, y, z, w).normalize();
		} else {
			final double s = 2.0D * sqrt(1.0D + m.element33 - m.element11 - m.element22);
			final double sReciprocal = 1.0D / s;
			
			final double x = (m.element31 + m.element13) * sReciprocal;
			final double y = (m.element23 + m.element32) * sReciprocal;
			final double z = 0.25D * s;
			final double w = (m.element12 - m.element21) * sReciprocal;
			
			return new Vector4D(x, y, z, w).normalize();
		}
	}
}