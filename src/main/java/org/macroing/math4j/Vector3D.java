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

import static org.macroing.math4j.MathD.PI;
import static org.macroing.math4j.MathD.cos;
import static org.macroing.math4j.MathD.sin;
import static org.macroing.math4j.MathD.sqrt;

import java.util.Objects;

/**
 * A {@code Vector3D} denotes a 3-dimensional vector with three components, of type {@code double}, denoted by X, Y and Z.
 * <p>
 * This class is immutable and therefore thread-safe.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class Vector3D {
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
	 * Constructs a new {@code Vector3D} instance given the component values {@code 0.0D}, {@code 0.0D} and {@code 0.0D}.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new Vector3D(0.0D, 0.0D, 0.0D);
	 * }
	 * </pre>
	 */
	public Vector3D() {
		this(0.0D, 0.0D, 0.0D);
	}
	
	/**
	 * Constructs a new {@code Vector3D} instance given the component values {@code p.x}, {@code p.y} and {@code p.z}.
	 * <p>
	 * If {@code p} is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new Vector3D(p.x, p.y, p.z);
	 * }
	 * </pre>
	 * 
	 * @param p a {@link Point3D} instance
	 * @throws NullPointerException thrown if, and only if, {@code p} is {@code null}
	 */
	public Vector3D(final Point3D p) {
		this(p.x, p.y, p.z);
	}
	
	/**
	 * Constructs a new {@code Vector3D} instance given the component values {@code x}, {@code y} and {@code z}.
	 * 
	 * @param x the value of the X-component
	 * @param y the value of the Y-component
	 * @param z the value of the Z-component
	 */
	public Vector3D(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns a {@code String} representation of this {@code Vector3D} instance.
	 * 
	 * @return a {@code String} representation of this {@code Vector3D} instance
	 */
	@Override
	public String toString() {
		return String.format("new Vector3D(%s, %s, %s)", Double.toString(this.x), Double.toString(this.y), Double.toString(this.z));
	}
	
	/**
	 * Adds the component values of {@code v} to the component values of this {@code Vector3D} instance.
	 * <p>
	 * Returns a new {@code Vector3D} instance with the result of the addition.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * Vector addition is performed componentwise.
	 * 
	 * @param v the {@code Vector3D} to add to this {@code Vector3D} instance
	 * @return a new {@code Vector3D} instance with the result of the addition
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public Vector3D add(final Vector3D v) {
		return new Vector3D(this.x + v.x, this.y + v.y, this.z + v.z);
	}
	
	/**
	 * Computes the cross product of this {@code Vector3D} instance and {@code v}.
	 * <p>
	 * Returns a new {@code Vector3D} instance with the result of the operation.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param v a {@code Vector3D} instance
	 * @return a new {@code Vector3D} instance with the result of the operation
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public Vector3D crossProduct(final Vector3D v) {
		return new Vector3D(this.y * v.z - this.z * v.y, this.z * v.x - this.x * v.z, this.x * v.y - this.y * v.x);
	}
	
	/**
	 * Divides the component values of this {@code Vector3D} instance with {@code s}.
	 * <p>
	 * Returns a new {@code Vector3D} instance with the result of the division.
	 * <p>
	 * Vector division is performed componentwise.
	 * 
	 * @param s the scalar value to divide this {@code Vector3D} instance with
	 * @return a new {@code Vector3D} instance with the result of the division
	 */
	public Vector3D divide(final double s) {
		return new Vector3D(this.x / s, this.y / s, this.z / s);
	}
	
	/**
	 * Multiplies the component values of this {@code Vector3D} instance with {@code s}.
	 * <p>
	 * Returns a new {@code Vector3D} instance with the result of the multiplication.
	 * <p>
	 * Vector multiplication is performed componentwise.
	 * 
	 * @param s the scalar value to multiply this {@code Vector3D} instance with
	 * @return a new {@code Vector3D} instance with the result of the multiplication
	 */
	public Vector3D multiply(final double s) {
		return new Vector3D(this.x * s, this.y * s, this.z * s);
	}
	
	/**
	 * Negates the component values of this {@code Vector3D} instance.
	 * <p>
	 * Returns a new {@code Vector3D} instance with the result of the negation.
	 * 
	 * @return a new {@code Vector3D} instance with the result of the negation
	 */
	public Vector3D negate() {
		return new Vector3D(-this.x, -this.y, -this.z);
	}
	
	/**
	 * Normalizes the component values of this {@code Vector3D} instance.
	 * <p>
	 * Returns a new {@code Vector3D} instance with the result of the normalization.
	 * <p>
	 * To normalize a vector you divide it by its length.
	 * <p>
	 * When a vector is already normalized, normalizing it results in the same vector.
	 * 
	 * @return the component values of this {@code Vector3D} instance
	 */
	public Vector3D normalize() {
		return divide(length());
	}
	
	/**
	 * Subtracts the component values of {@code v} from the component values of this {@code Vector3D} instance.
	 * <p>
	 * Returns a new {@code Vector3D} instance with the result of the subtraction.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * Vector subtraction is performed componentwise.
	 * 
	 * @param v the {@code Vector3D} to subtract from this {@code Vector3D} instance
	 * @return a new {@code Vector3D} instance with the result of the subtraction
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public Vector3D subtract(final Vector3D v) {
		return new Vector3D(this.x - v.x, this.y - v.y, this.z - v.z);
	}
	
	/**
	 * Performs a transformation.
	 * <p>
	 * Returns a new {@code Vector3D} instance with the result of the transformation.
	 * <p>
	 * If {@code m} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param m the {@link Matrix44D} instance to perform the transformation with
	 * @return a new {@code Vector3D} instance with the result of the transformation
	 * @throws NullPointerException thrown if, and only if, {@code m} is {@code null}
	 */
	public Vector3D transform(final Matrix44D m) {
		final double x = m.element11 * this.x + m.element12 * this.y + m.element13 * this.z;
		final double y = m.element21 * this.x + m.element22 * this.y + m.element23 * this.z;
		final double z = m.element31 * this.x + m.element32 * this.y + m.element33 * this.z;
		
		return new Vector3D(x, y, z);
	}
	
	/**
	 * Performs a transformation.
	 * <p>
	 * Returns a new {@code Vector3D} instance with the result of the transformation.
	 * <p>
	 * If {@code orthoNormalBasis} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param orthoNormalBasis the {@link OrthoNormalBasis33D} instance to perform the transformation with
	 * @return a new {@code Vector3D} instance with the result of the transformation
	 * @throws NullPointerException thrown if, and only if, {@code orthoNormalBasis} is {@code null}
	 */
	public Vector3D transform(final OrthoNormalBasis33D orthoNormalBasis) {
		final double x = this.x * orthoNormalBasis.u.x + this.y * orthoNormalBasis.v.x + this.z * orthoNormalBasis.w.x;
		final double y = this.x * orthoNormalBasis.u.y + this.y * orthoNormalBasis.v.y + this.z * orthoNormalBasis.w.y;
		final double z = this.x * orthoNormalBasis.u.z + this.y * orthoNormalBasis.v.z + this.z * orthoNormalBasis.w.z;
		
		return new Vector3D(x, y, z);
	}
	
	/**
	 * Performs a transformation in reverse order.
	 * <p>
	 * Returns a new {@code Vector3D} instance with the result of the transformation.
	 * <p>
	 * If {@code orthoNormalBasis} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param orthoNormalBasis the {@link OrthoNormalBasis33D} instance to perform the transformation with
	 * @return a new {@code Vector3D} instance with the result of the transformation
	 * @throws NullPointerException thrown if, and only if, {@code orthoNormalBasis} is {@code null}
	 */
	public Vector3D transformReverse(final OrthoNormalBasis33D orthoNormalBasis) {
		final double x = dotProduct(orthoNormalBasis.u);
		final double y = dotProduct(orthoNormalBasis.v);
		final double z = dotProduct(orthoNormalBasis.w);
		
		return new Vector3D(x, y, z);
	}
	
	/**
	 * Performs a transformation in transpose order.
	 * <p>
	 * Returns a new {@code Vector3D} instance with the result of the transformation.
	 * <p>
	 * If {@code m} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param m the {@link Matrix44D} instance to perform the transformation with
	 * @return a new {@code Vector3D} instance with the result of the transformation
	 * @throws NullPointerException thrown if, and only if, {@code m} is {@code null}
	 */
	public Vector3D transformTranspose(final Matrix44D m) {
		final double x = m.element11 * this.x + m.element21 * this.y + m.element31 * this.z;
		final double y = m.element12 * this.x + m.element22 * this.y + m.element32 * this.z;
		final double z = m.element13 * this.x + m.element23 * this.y + m.element33 * this.z;
		
		return new Vector3D(x, y, z);
	}
	
	/**
	 * Compares {@code object} to this {@code Vector3D} instance for equality.
	 * <p>
	 * Returns {@code true} if, and only if, {@code object} is an instance of {@code Vector3D}, and their respective values are equal, {@code false} otherwise.
	 * 
	 * @param object the {@code Object} to compare to this {@code Vector3D} instance for equality
	 * @return {@code true} if, and only if, {@code object} is an instance of {@code Vector3D}, and their respective values are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object object) {
		if(object == this) {
			return true;
		} else if(!(object instanceof Vector3D)) {
			return false;
		} else if(!MathD.equals(this.x, Vector3D.class.cast(object).x)) {
			return false;
		} else if(!MathD.equals(this.y, Vector3D.class.cast(object).y)) {
			return false;
		} else if(!MathD.equals(this.z, Vector3D.class.cast(object).z)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returns the dot product of this {@code Vector3D} instance and {@code v}.
	 * <p>
	 * If {@code v} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param v a {@code Vector3D}
	 * @return the dot product of this {@code Vector3D} instance and {@code v}
	 * @throws NullPointerException thrown if, and only if, {@code v} is {@code null}
	 */
	public double dotProduct(final Vector3D v) {
		return this.x * v.x + this.y * v.y + this.z * v.z;
	}
	
	/**
	 * Returns the value of the X-component.
	 * 
	 * @return the value of the X-component
	 */
	public double getX() {
		return this.x;
	}
	
	/**
	 * Returns the value of the Y-component.
	 * 
	 * @return the value of the Y-component
	 */
	public double getY() {
		return this.y;
	}
	
	/**
	 * Returns the value of the Z-component.
	 * 
	 * @return the value of the Z-component
	 */
	public double getZ() {
		return this.z;
	}
	
	/**
	 * Returns the length of this {@code Vector3D} instance.
	 * 
	 * @return the length of this {@code Vector3D} instance
	 */
	public double length() {
		return sqrt(lengthSquared());
	}
	
	/**
	 * Returns the squared length of this {@code Vector3D} instance.
	 * 
	 * @return the squared length of this {@code Vector3D} instance
	 */
	public double lengthSquared() {
		return this.x * this.x + this.y * this.y + this.z * this.z;
	}
	
	/**
	 * Returns a hash code for this {@code Vector3D} instance.
	 * 
	 * @return a hash code for this {@code Vector3D} instance
	 */
	@Override
	public int hashCode() {
		return Objects.hash(Double.valueOf(this.x), Double.valueOf(this.y), Double.valueOf(this.z));
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns a new {@code Vector3D} instance that is pointing in the direction of {@code eye} to {@code lookAt}.
	 * <p>
	 * If either {@code eye} or {@code lookAt} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param eye a {@link Point3D} instance denoting the eye to look from
	 * @param lookAt a {@code Point3D} denoting the target to look at
	 * @return a new {@code Vector3D} instance that is pointing in the direction of {@code eye} to {@code lookAt}
	 * @throws NullPointerException thrown if, and only if, either {@code eye} or {@code lookAt} are {@code null}
	 */
	public static Vector3D direction(final Point3D eye, final Point3D lookAt) {
		return new Vector3D(lookAt.x - eye.x, lookAt.y - eye.y, lookAt.z - eye.z);
	}
	
	/**
	 * Returns a new {@code Vector3D} instance that is pointing in the direction of {@code u} and {@code v}.
	 * 
	 * @param u the U-coordinate
	 * @param v the V-coordinate
	 * @return a new {@code Vector3D} instance that is pointing in the direction of {@code u} and {@code v}
	 */
	public static Vector3D direction(final double u, final double v) {
		final double theta = u * 2.0D * PI;
		final double phi = v * PI;
		final double sinPhi = sin(phi);
		
		final double x = -sinPhi * cos(theta);
		final double y = cos(phi);
		final double z = sinPhi * sin(theta);
		
		return new Vector3D(x, y, z);
	}
	
	/**
	 * Returns a new {@code Vector3D} instance denoting the normal of the plane defined by the {@link Point3D} instances {@code a}, {@code b} and {@code c}.
	 * <p>
	 * If either {@code a}, {@code b} or {@code c} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param a a {@code Point3D} instance, corresponding to one of the three points in the plane
	 * @param b a {@code Point3D} instance, corresponding to one of the three points in the plane
	 * @param c a {@code Point3D} instance, corresponding to one of the three points in the plane
	 * @return a new {@code Vector3D} instance denoting the normal of the plane defined by the {@code Point3D} instances {@code a}, {@code b} and {@code c}
	 * @throws NullPointerException thrown if, and only if, either {@code a}, {@code b} or {@code c} are {@code null}
	 */
	public static Vector3D normal(final Point3D a, final Point3D b, final Point3D c) {
		final Vector3D edgeAB = direction(a, b);
		final Vector3D edgeAC = direction(a, c);
		final Vector3D normal = edgeAB.crossProduct(edgeAC);
		
		return normal;
	}
	
	/**
	 * Computes a normal vector from three other normal vectors via Barycentric interpolation.
	 * <p>
	 * Returns a new {@code Vector3D} instance with the interpolated normal.
	 * <p>
	 * If either {@code normalA}, {@code normalB} or {@code normalC} are {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * <strong>Note:</strong> This method does not normalize {@code normalA}, {@code normalB} or {@code normalC}.
	 * 
	 * @param normalA a {@code Vector3D} instance denoting the normal of vertex {@code A} of a triangle
	 * @param normalB a {@code Vector3D} instance denoting the normal of vertex {@code B} of a triangle
	 * @param normalC a {@code Vector3D} instance denoting the normal of vertex {@code C} of a triangle
	 * @param barycentricU the Barycentric U-coordinate
	 * @param barycentricV the Barycentric V-coordinate
	 * @param barycentricW the Barycentric W-coordinate
	 * @return a new {@code Vector3D} instance with the interpolated normal
	 * @throws NullPointerException thrown if, and only if, either {@code normalA}, {@code normalB} or {@code normalC} are {@code null}
	 */
	public static Vector3D normal(final Vector3D normalA, final Vector3D normalB, final Vector3D normalC, final double barycentricU, final double barycentricV, final double barycentricW) {
		final double x = normalA.x * barycentricW + normalB.x * barycentricU + normalC.x * barycentricV;
		final double y = normalA.y * barycentricW + normalB.y * barycentricU + normalC.y * barycentricV;
		final double z = normalA.z * barycentricW + normalB.z * barycentricU + normalC.z * barycentricV;
		
		return new Vector3D(x, y, z);
	}
	
	/**
	 * Returns a new {@code Vector3D} instance denoting the normalized normal of the plane defined by the {@link Point3D} instances {@code a}, {@code b} and {@code c}.
	 * <p>
	 * If either {@code a}, {@code b} or {@code c} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param a a {@code Point3D} instance, corresponding to one of the three points in the plane
	 * @param b a {@code Point3D} instance, corresponding to one of the three points in the plane
	 * @param c a {@code Point3D} instance, corresponding to one of the three points in the plane
	 * @return a new {@code Vector3D} instance denoting the normalized normal of the plane defined by the {@code Point3D} instances {@code a}, {@code b} and {@code c}
	 * @throws NullPointerException thrown if, and only if, either {@code a}, {@code b} or {@code c} are {@code null}
	 */
	public static Vector3D normalNormalized(final Point3D a, final Point3D b, final Point3D c) {
		return normal(a, b, c).normalize();
	}
	
	/**
	 * Computes a normalized normal vector from three other normalized normal vectors via Barycentric interpolation.
	 * <p>
	 * Returns a new {@code Vector3D} instance with the interpolated and normalized normal.
	 * <p>
	 * If either {@code normalA}, {@code normalB} or {@code normalC} are {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * <strong>Note:</strong> This method does normalize {@code normalA}, {@code normalB} and {@code normalC}. It also normalizes the result, just to make sure.
	 * 
	 * @param normalA a {@code Vector3D} instance denoting the normal of vertex {@code A} of a triangle
	 * @param normalB a {@code Vector3D} instance denoting the normal of vertex {@code B} of a triangle
	 * @param normalC a {@code Vector3D} instance denoting the normal of vertex {@code C} of a triangle
	 * @param barycentricU the Barycentric U-coordinate
	 * @param barycentricV the Barycentric V-coordinate
	 * @param barycentricW the Barycentric W-coordinate
	 * @return a new {@code Vector3D} instance with the interpolated and normalized normal
	 * @throws NullPointerException thrown if, and only if, either {@code normalA}, {@code normalB} or {@code normalC} are {@code null}
	 */
	public static Vector3D normalNormalized(final Vector3D normalA, final Vector3D normalB, final Vector3D normalC, final double barycentricU, final double barycentricV, final double barycentricW) {
		return normal(normalA.normalize(), normalB.normalize(), normalC.normalize(), barycentricU, barycentricV, barycentricW).normalize();
	}
	
	/**
	 * Returns a new {@code Vector3D} instance equivalent to {@code new Vector3D(1.0D, 0.0D, 0.0D)}.
	 * 
	 * @return a new {@code Vector3D} instance equivalent to {@code new Vector3D(1.0D, 0.0D, 0.0D)}
	 */
	public static Vector3D x() {
		return x(1.0D);
	}
	
	/**
	 * Returns a new {@code Vector3D} instance equivalent to {@code new Vector3D(x, 0.0D, 0.0D)}.
	 * 
	 * @param x the value of the X-component
	 * @return a new {@code Vector3D} instance equivalent to {@code new Vector3D(x, 0.0D, 0.0D)}
	 */
	public static Vector3D x(final double x) {
		return new Vector3D(x, 0.0D, 0.0D);
	}
	
	/**
	 * Returns a new {@code Vector3D} instance equivalent to {@code new Vector3D(0.0D, 1.0D, 0.0D)}.
	 * 
	 * @return a new {@code Vector3D} instance equivalent to {@code new Vector3D(0.0D, 1.0D, 0.0D)}
	 */
	public static Vector3D y() {
		return y(1.0D);
	}
	
	/**
	 * Returns a new {@code Vector3D} instance equivalent to {@code new Vector3D(0.0D, y, 0.0D)}.
	 * 
	 * @param y the value of the Y-component
	 * @return a new {@code Vector3D} instance equivalent to {@code new Vector3D(0.0D, y, 0.0D)}
	 */
	public static Vector3D y(final double y) {
		return new Vector3D(0.0D, y, 0.0D);
	}
	
	/**
	 * Returns a new {@code Vector3D} instance equivalent to {@code new Vector3D(0.0D, 0.0D, 1.0D)}.
	 * 
	 * @return a new {@code Vector3D} instance equivalent to {@code new Vector3D(0.0D, 0.0D, 1.0D)}
	 */
	public static Vector3D z() {
		return z(1.0D);
	}
	
	/**
	 * Returns a new {@code Vector3D} instance equivalent to {@code new Vector3D(0.0D, 0.0D, z)}.
	 * 
	 * @param z the value of the Z-component
	 * @return a new {@code Vector3D} instance equivalent to {@code new Vector3D(0.0D, 0.0D, z)}
	 */
	public static Vector3D z(final double z) {
		return new Vector3D(0.0D, 0.0D, z);
	}
}