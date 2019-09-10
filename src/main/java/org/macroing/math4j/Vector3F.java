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

import static org.macroing.math4j.MathF.PI;
import static org.macroing.math4j.MathF.PI_MULTIPLIED_BY_TWO;
import static org.macroing.math4j.MathF.cos;
import static org.macroing.math4j.MathF.pow;
import static org.macroing.math4j.MathF.random;
import static org.macroing.math4j.MathF.sin;
import static org.macroing.math4j.MathF.sqrt;

import java.util.Objects;

/**
 * A {@code Vector3F} denotes a 3-dimensional vector with three components, of type {@code float}, denoted by X, Y and Z.
 * <p>
 * This class is immutable and therefore thread-safe.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class Vector3F {
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
	 * Constructs a new {@code Vector3F} instance given the component values {@code 0.0F}, {@code 0.0F} and {@code 0.0F}.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new Vector3F(0.0F, 0.0F, 0.0F);
	 * }
	 * </pre>
	 */
	public Vector3F() {
		this(0.0F, 0.0F, 0.0F);
	}
	
	/**
	 * Constructs a new {@code Vector3F} instance given the component values {@code p.x}, {@code p.y} and {@code p.z}.
	 * <p>
	 * If {@code p} is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new Vector3F(p.x, p.y, p.z);
	 * }
	 * </pre>
	 * 
	 * @param p a {@link Point3F} instance
	 * @throws NullPointerException thrown if, and only if, {@code p} is {@code null}
	 */
	public Vector3F(final Point3F p) {
		this(p.x, p.y, p.z);
	}
	
	/**
	 * Constructs a new {@code Vector3F} instance given the component values {@code x}, {@code y} and {@code z}.
	 * 
	 * @param x the value of the X-component
	 * @param y the value of the Y-component
	 * @param z the value of the Z-component
	 */
	public Vector3F(final float x, final float y, final float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns a {@code String} representation of this {@code Vector3F} instance.
	 * 
	 * @return a {@code String} representation of this {@code Vector3F} instance
	 */
	@Override
	public String toString() {
		return String.format("new Vector3F(%s, %s, %s)", Float.toString(this.x), Float.toString(this.y), Float.toString(this.z));
	}
	
	/**
	 * Adds the component values of {@code v} to the component values of this {@code Vector3F} instance.
	 * <p>
	 * Returns a new {@code Vector3F} instance with the result of the addition.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * Vector addition is performed componentwise.
	 * 
	 * @param v the {@code Vector3F} to add to this {@code Vector3F} instance
	 * @return a new {@code Vector3F} instance with the result of the addition
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public Vector3F add(final Vector3F v) {
		return new Vector3F(this.x + v.x, this.y + v.y, this.z + v.z);
	}
	
	/**
	 * Computes the cross product of this {@code Vector3F} instance and {@code v}.
	 * <p>
	 * Returns a new {@code Vector3F} instance with the result of the operation.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param v a {@code Vector3F} instance
	 * @return a new {@code Vector3F} instance with the result of the operation
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public Vector3F crossProduct(final Vector3F v) {
		return new Vector3F(this.y * v.z - this.z * v.y, this.z * v.x - this.x * v.z, this.x * v.y - this.y * v.x);
	}
	
	/**
	 * Divides the component values of this {@code Vector3F} instance with {@code s}.
	 * <p>
	 * Returns a new {@code Vector3F} instance with the result of the division.
	 * <p>
	 * Vector division is performed componentwise.
	 * 
	 * @param s the scalar value to divide this {@code Vector3F} instance with
	 * @return a new {@code Vector3F} instance with the result of the division
	 */
	public Vector3F divide(final float s) {
		return new Vector3F(this.x / s, this.y / s, this.z / s);
	}
	
	/**
	 * Multiplies the component values of this {@code Vector3F} instance with {@code s}.
	 * <p>
	 * Returns a new {@code Vector3F} instance with the result of the multiplication.
	 * <p>
	 * Vector multiplication is performed componentwise.
	 * 
	 * @param s the scalar value to multiply this {@code Vector3F} instance with
	 * @return a new {@code Vector3F} instance with the result of the multiplication
	 */
	public Vector3F multiply(final float s) {
		return new Vector3F(this.x * s, this.y * s, this.z * s);
	}
	
	/**
	 * Negates the component values of this {@code Vector3F} instance.
	 * <p>
	 * Returns a new {@code Vector3F} instance with the result of the negation.
	 * 
	 * @return a new {@code Vector3F} instance with the result of the negation
	 */
	public Vector3F negate() {
		return new Vector3F(-this.x, -this.y, -this.z);
	}
	
	/**
	 * Normalizes the component values of this {@code Vector3F} instance.
	 * <p>
	 * Returns a new {@code Vector3F} instance with the result of the normalization.
	 * <p>
	 * To normalize a vector you divide it by its length.
	 * <p>
	 * When a vector is already normalized, normalizing it results in the same vector.
	 * 
	 * @return the component values of this {@code Vector3F} instance
	 */
	public Vector3F normalize() {
		return divide(length());
	}
	
	/**
	 * Subtracts the component values of {@code v} from the component values of this {@code Vector3F} instance.
	 * <p>
	 * Returns a new {@code Vector3F} instance with the result of the subtraction.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * Vector subtraction is performed componentwise.
	 * 
	 * @param v the {@code Vector3F} to subtract from this {@code Vector3F} instance
	 * @return a new {@code Vector3F} instance with the result of the subtraction
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public Vector3F subtract(final Vector3F v) {
		return new Vector3F(this.x - v.x, this.y - v.y, this.z - v.z);
	}
	
	/**
	 * Performs a transformation.
	 * <p>
	 * Returns a new {@code Vector3F} instance with the result of the transformation.
	 * <p>
	 * If {@code m} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param m the {@link Matrix44F} instance to perform the transformation with
	 * @return a new {@code Vector3F} instance with the result of the transformation
	 * @throws NullPointerException thrown if, and only if, {@code m} is {@code null}
	 */
	public Vector3F transform(final Matrix44F m) {
		final float x = m.element11 * this.x + m.element12 * this.y + m.element13 * this.z;
		final float y = m.element21 * this.x + m.element22 * this.y + m.element23 * this.z;
		final float z = m.element31 * this.x + m.element32 * this.y + m.element33 * this.z;
		
		return new Vector3F(x, y, z);
	}
	
	/**
	 * Performs a transformation.
	 * <p>
	 * Returns a new {@code Vector3F} instance with the result of the transformation.
	 * <p>
	 * If {@code orthoNormalBasis} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param orthoNormalBasis the {@link OrthoNormalBasis33F} instance to perform the transformation with
	 * @return a new {@code Vector3F} instance with the result of the transformation
	 * @throws NullPointerException thrown if, and only if, {@code orthoNormalBasis} is {@code null}
	 */
	public Vector3F transform(final OrthoNormalBasis33F orthoNormalBasis) {
		final float x = this.x * orthoNormalBasis.u.x + this.y * orthoNormalBasis.v.x + this.z * orthoNormalBasis.w.x;
		final float y = this.x * orthoNormalBasis.u.y + this.y * orthoNormalBasis.v.y + this.z * orthoNormalBasis.w.y;
		final float z = this.x * orthoNormalBasis.u.z + this.y * orthoNormalBasis.v.z + this.z * orthoNormalBasis.w.z;
		
		return new Vector3F(x, y, z);
	}
	
	/**
	 * Performs a transformation in reverse order.
	 * <p>
	 * Returns a new {@code Vector3F} instance with the result of the transformation.
	 * <p>
	 * If {@code orthoNormalBasis} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param orthoNormalBasis the {@link OrthoNormalBasis33F} instance to perform the transformation with
	 * @return a new {@code Vector3F} instance with the result of the transformation
	 * @throws NullPointerException thrown if, and only if, {@code orthoNormalBasis} is {@code null}
	 */
	public Vector3F transformReverse(final OrthoNormalBasis33F orthoNormalBasis) {
		final float x = dotProduct(orthoNormalBasis.u);
		final float y = dotProduct(orthoNormalBasis.v);
		final float z = dotProduct(orthoNormalBasis.w);
		
		return new Vector3F(x, y, z);
	}
	
	/**
	 * Performs a transformation in transpose order.
	 * <p>
	 * Returns a new {@code Vector3F} instance with the result of the transformation.
	 * <p>
	 * If {@code m} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param m the {@link Matrix44F} instance to perform the transformation with
	 * @return a new {@code Vector3F} instance with the result of the transformation
	 * @throws NullPointerException thrown if, and only if, {@code m} is {@code null}
	 */
	public Vector3F transformTranspose(final Matrix44F m) {
		final float x = m.element11 * this.x + m.element21 * this.y + m.element31 * this.z;
		final float y = m.element12 * this.x + m.element22 * this.y + m.element32 * this.z;
		final float z = m.element13 * this.x + m.element23 * this.y + m.element33 * this.z;
		
		return new Vector3F(x, y, z);
	}
	
	/**
	 * Compares {@code object} to this {@code Vector3F} instance for equality.
	 * <p>
	 * Returns {@code true} if, and only if, {@code object} is an instance of {@code Vector3F}, and their respective values are equal, {@code false} otherwise.
	 * 
	 * @param object the {@code Object} to compare to this {@code Vector3F} instance for equality
	 * @return {@code true} if, and only if, {@code object} is an instance of {@code Vector3F}, and their respective values are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object object) {
		if(object == this) {
			return true;
		} else if(!(object instanceof Vector3F)) {
			return false;
		} else if(!MathF.equals(this.x, Vector3F.class.cast(object).x)) {
			return false;
		} else if(!MathF.equals(this.y, Vector3F.class.cast(object).y)) {
			return false;
		} else if(!MathF.equals(this.z, Vector3F.class.cast(object).z)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returns the dot product of this {@code Vector3F} instance and {@code v}.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param v a {@code Vector3F}
	 * @return the dot product of this {@code Vector3F} instance and {@code v}
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public float dotProduct(final Vector3F v) {
		return this.x * v.x + this.y * v.y + this.z * v.z;
	}
	
	/**
	 * Returns the value of the X-component.
	 * 
	 * @return the value of the X-component
	 */
	public float getX() {
		return this.x;
	}
	
	/**
	 * Returns the value of the Y-component.
	 * 
	 * @return the value of the Y-component
	 */
	public float getY() {
		return this.y;
	}
	
	/**
	 * Returns the value of the Z-component.
	 * 
	 * @return the value of the Z-component
	 */
	public float getZ() {
		return this.z;
	}
	
	/**
	 * Returns the length of this {@code Vector3F} instance.
	 * 
	 * @return the length of this {@code Vector3F} instance
	 */
	public float length() {
		return sqrt(lengthSquared());
	}
	
	/**
	 * Returns the squared length of this {@code Vector3F} instance.
	 * 
	 * @return the squared length of this {@code Vector3F} instance
	 */
	public float lengthSquared() {
		return this.x * this.x + this.y * this.y + this.z * this.z;
	}
	
	/**
	 * Returns a hash code for this {@code Vector3F} instance.
	 * 
	 * @return a hash code for this {@code Vector3F} instance
	 */
	@Override
	public int hashCode() {
		return Objects.hash(Float.valueOf(this.x), Float.valueOf(this.y), Float.valueOf(this.z));
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns a new {@code Vector3F} instance that is pointing in the direction of {@code eye} to {@code lookAt}.
	 * <p>
	 * If either {@code eye} or {@code lookAt} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param eye a {@link Point3F} instance denoting the eye to look from
	 * @param lookAt a {@code Point3F} denoting the target to look at
	 * @return a new {@code Vector3F} instance that is pointing in the direction of {@code eye} to {@code lookAt}
	 * @throws NullPointerException thrown if, and only if, either {@code eye} or {@code lookAt} are {@code null}
	 */
	public static Vector3F direction(final Point3F eye, final Point3F lookAt) {
		return new Vector3F(lookAt.x - eye.x, lookAt.y - eye.y, lookAt.z - eye.z);
	}
	
	/**
	 * Returns a new {@code Vector3F} instance that is pointing in the direction of {@code u} and {@code v}.
	 * 
	 * @param u the U-coordinate
	 * @param v the V-coordinate
	 * @return a new {@code Vector3F} instance that is pointing in the direction of {@code u} and {@code v}
	 */
	public static Vector3F direction(final float u, final float v) {
		final float theta = u * 2.0F * PI;
		final float phi = v * PI;
		final float sinPhi = sin(phi);
		
		final float x = -sinPhi * cos(theta);
		final float y = cos(phi);
		final float z = sinPhi * sin(theta);
		
		return new Vector3F(x, y, z);
	}
	
	/**
	 * Returns a new {@code Vector3F} instance denoting the normal of the plane defined by the {@link Point3F} instances {@code a}, {@code b} and {@code c}.
	 * <p>
	 * If either {@code a}, {@code b} or {@code c} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param a a {@code Point3F} instance, corresponding to one of the three points in the plane
	 * @param b a {@code Point3F} instance, corresponding to one of the three points in the plane
	 * @param c a {@code Point3F} instance, corresponding to one of the three points in the plane
	 * @return a new {@code Vector3F} instance denoting the normal of the plane defined by the {@code Point3F} instances {@code a}, {@code b} and {@code c}
	 * @throws NullPointerException thrown if, and only if, either {@code a}, {@code b} or {@code c} are {@code null}
	 */
	public static Vector3F normal(final Point3F a, final Point3F b, final Point3F c) {
		final Vector3F edgeAB = direction(a, b);
		final Vector3F edgeAC = direction(a, c);
		final Vector3F normal = edgeAB.crossProduct(edgeAC);
		
		return normal;
	}
	
	/**
	 * Computes a normal vector from three other normal vectors via Barycentric interpolation.
	 * <p>
	 * Returns a new {@code Vector3F} instance with the interpolated normal.
	 * <p>
	 * If either {@code normalA}, {@code normalB} or {@code normalC} are {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * <strong>Note:</strong> This method does not normalize {@code normalA}, {@code normalB} or {@code normalC}.
	 * 
	 * @param normalA a {@code Vector3F} instance denoting the normal of vertex {@code A} of a triangle
	 * @param normalB a {@code Vector3F} instance denoting the normal of vertex {@code B} of a triangle
	 * @param normalC a {@code Vector3F} instance denoting the normal of vertex {@code C} of a triangle
	 * @param barycentricU the Barycentric U-coordinate
	 * @param barycentricV the Barycentric V-coordinate
	 * @param barycentricW the Barycentric W-coordinate
	 * @return a new {@code Vector3F} instance with the interpolated normal
	 * @throws NullPointerException thrown if, and only if, either {@code normalA}, {@code normalB} or {@code normalC} are {@code null}
	 */
	public static Vector3F normal(final Vector3F normalA, final Vector3F normalB, final Vector3F normalC, final float barycentricU, final float barycentricV, final float barycentricW) {
		final float x = normalA.x * barycentricW + normalB.x * barycentricU + normalC.x * barycentricV;
		final float y = normalA.y * barycentricW + normalB.y * barycentricU + normalC.y * barycentricV;
		final float z = normalA.z * barycentricW + normalB.z * barycentricU + normalC.z * barycentricV;
		
		return new Vector3F(x, y, z);
	}
	
	/**
	 * Returns a new {@code Vector3F} instance denoting the normalized normal of the plane defined by the {@link Point3F} instances {@code a}, {@code b} and {@code c}.
	 * <p>
	 * If either {@code a}, {@code b} or {@code c} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param a a {@code Point3F} instance, corresponding to one of the three points in the plane
	 * @param b a {@code Point3F} instance, corresponding to one of the three points in the plane
	 * @param c a {@code Point3F} instance, corresponding to one of the three points in the plane
	 * @return a new {@code Vector3F} instance denoting the normalized normal of the plane defined by the {@code Point3F} instances {@code a}, {@code b} and {@code c}
	 * @throws NullPointerException thrown if, and only if, either {@code a}, {@code b} or {@code c} are {@code null}
	 */
	public static Vector3F normalNormalized(final Point3F a, final Point3F b, final Point3F c) {
		return normal(a, b, c).normalize();
	}
	
	/**
	 * Computes a normalized normal vector from three other normalized normal vectors via Barycentric interpolation.
	 * <p>
	 * Returns a new {@code Vector3F} instance with the interpolated and normalized normal.
	 * <p>
	 * If either {@code normalA}, {@code normalB} or {@code normalC} are {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * <strong>Note:</strong> This method does normalize {@code normalA}, {@code normalB} and {@code normalC}. It also normalizes the result, just to make sure.
	 * 
	 * @param normalA a {@code Vector3F} instance denoting the normal of vertex {@code A} of a triangle
	 * @param normalB a {@code Vector3F} instance denoting the normal of vertex {@code B} of a triangle
	 * @param normalC a {@code Vector3F} instance denoting the normal of vertex {@code C} of a triangle
	 * @param barycentricU the Barycentric U-coordinate
	 * @param barycentricV the Barycentric V-coordinate
	 * @param barycentricW the Barycentric W-coordinate
	 * @return a new {@code Vector3F} instance with the interpolated and normalized normal
	 * @throws NullPointerException thrown if, and only if, either {@code normalA}, {@code normalB} or {@code normalC} are {@code null}
	 */
	public static Vector3F normalNormalized(final Vector3F normalA, final Vector3F normalB, final Vector3F normalC, final float barycentricU, final float barycentricV, final float barycentricW) {
		return normal(normalA.normalize(), normalB.normalize(), normalC.normalize(), barycentricU, barycentricV, barycentricW).normalize();
	}
	
	/**
	 * Returns a {@code Vector3F} instance pointing in a random direction based on cosine-weighted hemisphere sampling using a scale of {@code 1.0F}.
	 * <p>
	 * Calling this method is equivalent to the following:
	 * <pre>
	 * {@code
	 * Vector3F.sampleCosineWeightedHemisphere(1.0F);
	 * }
	 * </pre>
	 * 
	 * @return a {@code Vector3F} instance pointing in a random direction based on cosine-weighted hemisphere sampling using a scale of {@code 1.0F}
	 */
	public static Vector3F sampleCosineWeightedHemisphere() {
		return sampleCosineWeightedHemisphere(1.0F);
	}
	
	/**
	 * Returns a {@code Vector3F} instance pointing in a random direction based on cosine-weighted hemisphere sampling using a scale of {@code scale}.
	 * 
	 * @param scale the scale to use
	 * @return a {@code Vector3F} instance pointing in a random direction based on cosine-weighted hemisphere sampling using a scale of {@code scale}
	 */
	public static Vector3F sampleCosineWeightedHemisphere(final float scale) {
		final float phi = random() * PI_MULTIPLIED_BY_TWO;
		final float random = random();
		final float radius = sqrt(1.0F - random) * scale;
		final float x = cos(phi) * radius;
		final float y = sin(phi) * radius;
		final float z = sqrt(random);
		
		return new Vector3F(x, y, z);
	}
	
	/**
	 * Returns a {@code Vector3F} instance pointing in a random direction based on power cosine-weighted hemisphere sampling using an exponent of {@code 20.0F} and a scale of {@code 1.0F}.
	 * <p>
	 * Calling this method is equivalent to the following:
	 * <pre>
	 * {@code
	 * Vector3F.samplePowerCosineWeightedHemisphere(20.0F);
	 * }
	 * </pre>
	 * 
	 * @return a {@code Vector3F} instance pointing in a random direction based on power cosine-weighted hemisphere sampling using an exponent of {@code 20.0F} and a scale of {@code 1.0F}
	 */
	public static Vector3F samplePowerCosineWeightedHemisphere() {
		return samplePowerCosineWeightedHemisphere(20.0F);
	}
	
	/**
	 * Returns a {@code Vector3F} instance pointing in a random direction based on power cosine-weighted hemisphere sampling using an exponent of {@code exponent} and a scale of {@code 1.0F}.
	 * <p>
	 * Calling this method is equivalent to the following:
	 * <pre>
	 * {@code
	 * Vector3F.samplePowerCosineWeightedHemisphere(exponent, 1.0F);
	 * }
	 * </pre>
	 * 
	 * @param exponent the exponent to use
	 * @return a {@code Vector3F} instance pointing in a random direction based on power cosine-weighted hemisphere sampling using an exponent of {@code exponent} and a scale of {@code 1.0F}
	 */
	public static Vector3F samplePowerCosineWeightedHemisphere(final float exponent) {
		return samplePowerCosineWeightedHemisphere(exponent, 1.0F);
	}
	
	/**
	 * Returns a {@code Vector3F} instance pointing in a random direction based on power cosine-weighted hemisphere sampling using an exponent of {@code exponent} and a scale of {@code scale}.
	 * 
	 * @param exponent the exponent to use
	 * @param scale the scale to use
	 * @return a {@code Vector3F} instance pointing in a random direction based on power cosine-weighted hemisphere sampling using an exponent of {@code exponent} and a scale of {@code scale}
	 */
	public static Vector3F samplePowerCosineWeightedHemisphere(final float exponent, final float scale) {
		final float phi = random() * PI_MULTIPLIED_BY_TWO;
		final float random = pow(1.0F - random(), 1.0F / (exponent + 1.0F));
		final float radius = sqrt(1.0F - random * random);
		final float x = cos(phi) * radius;
		final float y = sin(phi) * radius;
		final float z = random;
		
		return new Vector3F(x * scale, y * scale, z * scale);
	}
	
	/**
	 * Returns a {@code Vector3F} instance pointing in a random direction based on uniform sphere sampling using a scale of {@code 1.0F}.
	 * <p>
	 * Calling this method is equivalent to the following:
	 * <pre>
	 * {@code
	 * Vector3F.sampleUniformSphere(1.0F);
	 * }
	 * </pre>
	 * 
	 * @return a {@code Vector3F} instance pointing in a random direction based on uniform sphere sampling using a scale of {@code 1.0F}
	 */
	public static Vector3F sampleUniformSphere() {
		return sampleUniformSphere(1.0F);
	}
	
	/**
	 * Returns a {@code Vector3F} instance pointing in a random direction based on uniform sphere sampling using a scale of {@code scale}.
	 * 
	 * @param scale the scale to use
	 * @return a {@code Vector3F} instance pointing in a random direction based on uniform sphere sampling using a scale of {@code scale}
	 */
	public static Vector3F sampleUniformSphere(final float scale) {
		final float phi = random() * PI_MULTIPLIED_BY_TWO;
		final float random = random() * 2.0F - 1.0F;
		final float radius = sqrt(1.0F - random * random);
		final float x = cos(phi) * radius;
		final float y = sin(phi) * radius;
		final float z = random;
		
		return new Vector3F(x * scale, y * scale, z * scale);
	}
	
	/**
	 * Returns a new {@code Vector3F} instance equivalent to {@code new Vector3F(1.0F, 0.0F, 0.0F)}.
	 * 
	 * @return a new {@code Vector3F} instance equivalent to {@code new Vector3F(1.0F, 0.0F, 0.0F)}
	 */
	public static Vector3F x() {
		return x(1.0F);
	}
	
	/**
	 * Returns a new {@code Vector3F} instance equivalent to {@code new Vector3F(x, 0.0F, 0.0F)}.
	 * 
	 * @param x the value of the X-component
	 * @return a new {@code Vector3F} instance equivalent to {@code new Vector3F(x, 0.0F, 0.0F)}
	 */
	public static Vector3F x(final float x) {
		return new Vector3F(x, 0.0F, 0.0F);
	}
	
	/**
	 * Returns a new {@code Vector3F} instance equivalent to {@code new Vector3F(0.0F, 1.0F, 0.0F)}.
	 * 
	 * @return a new {@code Vector3F} instance equivalent to {@code new Vector3F(0.0F, 1.0F, 0.0F)}
	 */
	public static Vector3F y() {
		return y(1.0F);
	}
	
	/**
	 * Returns a new {@code Vector3F} instance equivalent to {@code new Vector3F(0.0F, y, 0.0F)}.
	 * 
	 * @param y the value of the Y-component
	 * @return a new {@code Vector3F} instance equivalent to {@code new Vector3F(0.0F, y, 0.0F)}
	 */
	public static Vector3F y(final float y) {
		return new Vector3F(0.0F, y, 0.0F);
	}
	
	/**
	 * Returns a new {@code Vector3F} instance equivalent to {@code new Vector3F(0.0F, 0.0F, 1.0F)}.
	 * 
	 * @return a new {@code Vector3F} instance equivalent to {@code new Vector3F(0.0F, 0.0F, 1.0F)}
	 */
	public static Vector3F z() {
		return z(1.0F);
	}
	
	/**
	 * Returns a new {@code Vector3F} instance equivalent to {@code new Vector3F(0.0F, 0.0F, z)}.
	 * 
	 * @param z the value of the Z-component
	 * @return a new {@code Vector3F} instance equivalent to {@code new Vector3F(0.0F, 0.0F, z)}
	 */
	public static Vector3F z(final float z) {
		return new Vector3F(0.0F, 0.0F, z);
	}
}