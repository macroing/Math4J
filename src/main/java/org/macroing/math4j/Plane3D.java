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

import static org.macroing.math4j.MathD.abs;

import java.util.Objects;

/**
 * A {@code Plane3D} denotes a plane that uses the data type {@code double}.
 * <p>
 * This class is immutable and therefore thread-safe.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class Plane3D implements Shape3D {
	private static final double EPSILON = 0.0001D;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final Point3D a;
	private final Point3D b;
	private final Point3D c;
	private final Vector3D surfaceNormal;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructs a new {@code Plane3D} instance given the three {@link Point3D}s {@code a}, {@code b} and {@code c}.
	 * <p>
	 * If either {@code a}, {@code b} or {@code c} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param a a {@code Point3D} instance that represents a point denoted by {@code A}
	 * @param b a {@code Point3D} instance that represents a point denoted by {@code B}
	 * @param c a {@code Point3D} instance that represents a point denoted by {@code C}
	 * @throws NullPointerException thrown if, and only if, either {@code a}, {@code b} or {@code c} are {@code null}
	 */
	public Plane3D(final Point3D a, final Point3D b, final Point3D c) {
		this.a = Objects.requireNonNull(a, "a == null");
		this.b = Objects.requireNonNull(b, "b == null");
		this.c = Objects.requireNonNull(c, "c == null");
		this.surfaceNormal = Vector3D.normalNormalized(a, b, c);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns an {@link OrthoNormalBasis33D} instance denoting the OrthoNormal Basis (ONB) of the surface of this {@code Plane3D} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3D} instance that was used in a call to {@link #intersection(Ray3D)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Plane3D} instance that was returned by {@code intersection(Ray3D)}
	 * @return an {@code OrthoNormalBasis33D} instance denoting the OrthoNormal Basis (ONB) of the surface of this {@code Plane3D} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public OrthoNormalBasis33D calculateOrthoNormalBasis(final Ray3D ray, final double t) {
		return new OrthoNormalBasis33D(calculateSurfaceNormal(ray, t));
	}
	
	/**
	 * Returns a {@link Point2D} instance denoting the texture coordinates (or UV-coordinates) of the surface of this {@code Plane3D} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3D} instance that was used in a call to {@link #intersection(Ray3D)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Plane3D} instance that was returned by {@code intersection(Ray3D)}
	 * @return a {@code Point2D} instance denoting the texture coordinates (or UV-coordinates) of the surface of this {@code Plane3D} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Point2D calculateTextureCoordinates(final Ray3D ray, final double t) {
		final Point3D a = this.a;
		final Point3D b = this.b;
		final Point3D c = this.c;
		final Point3D surfaceIntersectionPoint = calculateSurfaceIntersectionPoint(ray, t);
		
		final Vector3D surfaceNormal = this.surfaceNormal;
		
		final double x = abs(surfaceNormal.x);
		final double y = abs(surfaceNormal.y);
		final double z = abs(surfaceNormal.z);
		
		final boolean isX = x > y && x > z;
		final boolean isY = y > z;
		
		final double aX = isX ? a.y      : isY ? a.z      : a.x;
		final double aY = isX ? a.z      : isY ? a.x      : a.y;
		final double bX = isX ? c.y - aX : isY ? c.z - aX : c.x - aX;
		final double bY = isX ? c.z - aY : isY ? c.x - aY : c.y - aY;
		final double cX = isX ? b.y - aX : isY ? b.z - aX : b.x - aX;
		final double cY = isX ? b.z - aY : isY ? b.x - aY : b.y - aY;
		
		final double determinant = bX * cY - bY * cX;
		final double determinantReciprocal = 1.0D / determinant;
		
		final double bNU = -bY * determinantReciprocal;
		final double bNV =  bX * determinantReciprocal;
		final double bND = (bY * aX - bX * aY) * determinantReciprocal;
		final double cNU =  cY * determinantReciprocal;
		final double cNV = -cX * determinantReciprocal;
		final double cND = (cX * aY - cY * aX) * determinantReciprocal;
		
		final double hU = isX ? surfaceIntersectionPoint.y : isY ? surfaceIntersectionPoint.z : surfaceIntersectionPoint.x;
		final double hV = isX ? surfaceIntersectionPoint.z : isY ? surfaceIntersectionPoint.x : surfaceIntersectionPoint.y;
		
		final double u = hU * bNU + hV * bNV + bND;
		final double v = hU * cNU + hV * cNV + cND;
		
		return new Point2D(u, v);
	}
	
	/**
	 * Returns a {@link Point3D} instance denoting the surface intersection point of the surface of this {@code Plane3D} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3D} instance that was used in a call to {@link #intersection(Ray3D)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Plane3D} instance that was returned by {@code intersection(Ray3D)}
	 * @return a {@code Point3D} instance denoting the surface intersection point of the surface of this {@code Plane3D} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Point3D calculateSurfaceIntersectionPoint(final Ray3D ray, final double t) {
		return ray.origin.add(ray.direction, t);
	}
	
	/**
	 * Returns the {@link Point3D} instance that represents the point denoted by {@code A}.
	 * 
	 * @return the {@code Point3D} instance that represents the point denoted by {@code A}
	 */
	public Point3D getA() {
		return this.a;
	}
	
	/**
	 * Returns the {@link Point3D} instance that represents the point denoted by {@code B}.
	 * 
	 * @return the {@code Point3D} instance that represents the point denoted by {@code B}
	 */
	public Point3D getB() {
		return this.b;
	}
	
	/**
	 * Returns the {@link Point3D} instance that represents the point denoted by {@code C}.
	 * 
	 * @return the {@code Point3D} instance that represents the point denoted by {@code C}
	 */
	public Point3D getC() {
		return this.c;
	}
	
	/**
	 * Returns a {@code String} representation of this {@code Plane3D} instance.
	 * 
	 * @return a {@code String} representation of this {@code Plane3D} instance
	 */
	@Override
	public String toString() {
		return String.format("new Plane3D(%s, %s, %s)", this.a, this.b, this.c);
	}
	
	/**
	 * Returns a {@link Vector3D} instance denoting the surface normal of the surface of this {@code Plane3D} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3D} instance that was used in a call to {@link #intersection(Ray3D)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Plane3D} instance that was returned by {@code intersection(Ray3D)}
	 * @return a {@code Vector3D} instance denoting the surface normal of the surface of this {@code Plane3D} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Vector3D calculateSurfaceNormal(final Ray3D ray, final double t) {
		Objects.requireNonNull(ray, "ray == null");
		
		return this.surfaceNormal;
	}
	
	/**
	 * Returns the {@link Vector3D} instance that represents the surface normal of this {@code Plane3D} instance.
	 * 
	 * @return the {@code Vector3D} instance that represents the surface normal of this {@code Plane3D} instance
	 */
	public Vector3D getSurfaceNormal() {
		return this.surfaceNormal;
	}
	
	/**
	 * Compares {@code object} to this {@code Plane3D} instance for equality.
	 * <p>
	 * Returns {@code true} if, and only if, {@code object} is an instance of {@code Plane3D}, and their respective values are equal, {@code false} otherwise.
	 * 
	 * @param object the {@code Object} to compare to this {@code Plane3D} instance for equality
	 * @return {@code true} if, and only if, {@code object} is an instance of {@code Plane3D}, and their respective values are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object object) {
		if(object == this) {
			return true;
		} else if(!(object instanceof Plane3D)) {
			return false;
		} else if(!Objects.equals(this.a, Plane3D.class.cast(object).a)) {
			return false;
		} else if(!Objects.equals(this.b, Plane3D.class.cast(object).b)) {
			return false;
		} else if(!Objects.equals(this.c, Plane3D.class.cast(object).c)) {
			return false;
		} else if(!Objects.equals(this.surfaceNormal, Plane3D.class.cast(object).surfaceNormal)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Performs an intersection test between {@code ray} and this {@code Plane3D} instance.
	 * <p>
	 * Returns {@code t}, the parametric distance from {@code ray} to this {@code Plane3D} instance, or {@code Double.NaN} if no intersection exists.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3D} to perform an intersection test against this {@code Plane3D} instance
	 * @return {@code t}, the parametric distance from {@code ray} to this {@code Plane3D} instance, or {@code Double.NaN} if no intersection exists
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public double intersection(final Ray3D ray) {
		final Vector3D direction = ray.direction;
		final Vector3D surfaceNormal = this.surfaceNormal;
		
		final double dotProduct = surfaceNormal.dotProduct(direction);
		
		if(!MathD.equals(dotProduct, 0.0D)) {
			final Point3D a = this.a;
			final Point3D origin = ray.origin;
			
			final Vector3D originToA = Vector3D.direction(origin, a);
			
			final double t = originToA.dotProduct(surfaceNormal) / dotProduct;
			
			if(t > EPSILON) {
				return t;
			}
		}
		
		return Double.NaN;
	}
	
	/**
	 * Returns a hash code for this {@code Plane3D} instance.
	 * 
	 * @return a hash code for this {@code Plane3D} instance
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.a, this.b, this.c, this.surfaceNormal);
	}
}