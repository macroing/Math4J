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

import static org.macroing.math4j.MathF.abs;

import java.util.Objects;
import java.util.Optional;

/**
 * A {@code Plane3F} denotes a plane that uses the data type {@code float}.
 * <p>
 * This class is immutable and therefore thread-safe.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class Plane3F implements Shape3F {
	private static final float EPSILON = 0.0001F;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final Point3F a;
	private final Point3F b;
	private final Point3F c;
	private final Vector3F surfaceNormal;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructs a new {@code Plane3F} instance given the three {@link Point3F}s {@code a}, {@code b} and {@code c}.
	 * <p>
	 * If either {@code a}, {@code b} or {@code c} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param a a {@code Point3F} instance that represents a point denoted by {@code A}
	 * @param b a {@code Point3F} instance that represents a point denoted by {@code B}
	 * @param c a {@code Point3F} instance that represents a point denoted by {@code C}
	 * @throws NullPointerException thrown if, and only if, either {@code a}, {@code b} or {@code c} are {@code null}
	 */
	public Plane3F(final Point3F a, final Point3F b, final Point3F c) {
		this.a = Objects.requireNonNull(a, "a == null");
		this.b = Objects.requireNonNull(b, "b == null");
		this.c = Objects.requireNonNull(c, "c == null");
		this.surfaceNormal = Vector3F.normalNormalized(a, b, c);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Samples this {@code Plane3F} instance.
	 * <p>
	 * Returns an optional {@link SurfaceSample3F} with the surface sample.
	 * <p>
	 * If either {@code referencePoint} or {@code referenceSurfaceNormal} are {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * This {@code Plane3F} class cannot be sampled. An empty {@code Optional} will be returned.
	 * 
	 * @param referencePoint the reference point on this {@code Plane3F} instance
	 * @param referenceSurfaceNormal the reference surface normal on this {@code Plane3F} instance
	 * @param u a random {@code float} with a uniform distribution between {@code 0.0F} and {@code 1.0F}
	 * @param v a random {@code float} with a uniform distribution between {@code 0.0F} and {@code 1.0F}
	 * @return an optional {@code SurfaceSample3F} with the surface sample
	 * @throws NullPointerException thrown if, and only if, either {@code referencePoint} or {@code referenceSurfaceNormal} are {@code null}
	 */
	@Override
	public Optional<SurfaceSample3F> sample(final Point3F referencePoint, final Vector3F referenceSurfaceNormal, final float u, final float v) {
		Objects.requireNonNull(referencePoint, "referencePoint == null");
		Objects.requireNonNull(referenceSurfaceNormal, "referenceSurfaceNormal == null");
		
		return Optional.empty();
	}
	
	/**
	 * Returns an {@link OrthoNormalBasis33F} instance denoting the OrthoNormal Basis (ONB) of the surface of this {@code Plane3F} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} instance that was used in a call to {@link #intersection(Ray3F)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Plane3F} instance that was returned by {@code intersection(Ray3F)}
	 * @param isCorrectlyOriented {@code true} if, and only if, the {@code OrthoNormalBasis33F} must lie in the same hemisphere as {@code ray}, {@code false} otherwise
	 * @return an {@code OrthoNormalBasis33F} instance denoting the OrthoNormal Basis (ONB) of the surface of this {@code Plane3F} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public OrthoNormalBasis33F calculateOrthoNormalBasis(final Ray3F ray, final float t, final boolean isCorrectlyOriented) {
		return new OrthoNormalBasis33F(calculateSurfaceNormal(ray, t, isCorrectlyOriented));
	}
	
	/**
	 * Returns a {@link Point2F} instance denoting the texture coordinates (or UV-coordinates) of the surface of this {@code Plane3F} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} instance that was used in a call to {@link #intersection(Ray3F)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Plane3F} instance that was returned by {@code intersection(Ray3F)}
	 * @return a {@code Point2F} instance denoting the texture coordinates (or UV-coordinates) of the surface of this {@code Plane3F} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Point2F calculateTextureCoordinates(final Ray3F ray, final float t) {
		final Point3F a = this.a;
		final Point3F b = this.b;
		final Point3F c = this.c;
		final Point3F surfaceIntersectionPoint = calculateSurfaceIntersectionPoint(ray, t);
		
		final Vector3F surfaceNormal = this.surfaceNormal;
		
		final float x = abs(surfaceNormal.x);
		final float y = abs(surfaceNormal.y);
		final float z = abs(surfaceNormal.z);
		
		final boolean isX = x > y && x > z;
		final boolean isY = y > z;
		
		final float aX = isX ? a.y      : isY ? a.z      : a.x;
		final float aY = isX ? a.z      : isY ? a.x      : a.y;
		final float bX = isX ? c.y - aX : isY ? c.z - aX : c.x - aX;
		final float bY = isX ? c.z - aY : isY ? c.x - aY : c.y - aY;
		final float cX = isX ? b.y - aX : isY ? b.z - aX : b.x - aX;
		final float cY = isX ? b.z - aY : isY ? b.x - aY : b.y - aY;
		
		final float determinant = bX * cY - bY * cX;
		final float determinantReciprocal = 1.0F / determinant;
		
		final float bNU = -bY * determinantReciprocal;
		final float bNV =  bX * determinantReciprocal;
		final float bND = (bY * aX - bX * aY) * determinantReciprocal;
		final float cNU =  cY * determinantReciprocal;
		final float cNV = -cX * determinantReciprocal;
		final float cND = (cX * aY - cY * aX) * determinantReciprocal;
		
		final float hU = isX ? surfaceIntersectionPoint.y : isY ? surfaceIntersectionPoint.z : surfaceIntersectionPoint.x;
		final float hV = isX ? surfaceIntersectionPoint.z : isY ? surfaceIntersectionPoint.x : surfaceIntersectionPoint.y;
		
		final float u = hU * bNU + hV * bNV + bND;
		final float v = hU * cNU + hV * cNV + cND;
		
		return new Point2F(u, v);
	}
	
	/**
	 * Returns a {@link Point3F} instance denoting the surface intersection point of the surface of this {@code Plane3F} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} instance that was used in a call to {@link #intersection(Ray3F)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Plane3F} instance that was returned by {@code intersection(Ray3F)}
	 * @return a {@code Point3F} instance denoting the surface intersection point of the surface of this {@code Plane3F} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Point3F calculateSurfaceIntersectionPoint(final Ray3F ray, final float t) {
		return ray.origin.add(ray.direction, t);
	}
	
	/**
	 * Returns the {@link Point3F} instance that represents the point denoted by {@code A}.
	 * 
	 * @return the {@code Point3F} instance that represents the point denoted by {@code A}
	 */
	public Point3F getA() {
		return this.a;
	}
	
	/**
	 * Returns the {@link Point3F} instance that represents the point denoted by {@code B}.
	 * 
	 * @return the {@code Point3F} instance that represents the point denoted by {@code B}
	 */
	public Point3F getB() {
		return this.b;
	}
	
	/**
	 * Returns the {@link Point3F} instance that represents the point denoted by {@code C}.
	 * 
	 * @return the {@code Point3F} instance that represents the point denoted by {@code C}
	 */
	public Point3F getC() {
		return this.c;
	}
	
	/**
	 * Returns a {@code String} representation of this {@code Plane3F} instance.
	 * 
	 * @return a {@code String} representation of this {@code Plane3F} instance
	 */
	@Override
	public String toString() {
		return String.format("new Plane3F(%s, %s, %s)", this.a, this.b, this.c);
	}
	
	/**
	 * Returns a {@link Vector3F} instance denoting the surface normal of the surface of this {@code Plane3F} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} instance that was used in a call to {@link #intersection(Ray3F)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Plane3F} instance that was returned by {@code intersection(Ray3F)}
	 * @param isCorrectlyOriented {@code true} if, and only if, the {@code Vector3F} must lie in the same hemisphere as {@code ray}, {@code false} otherwise
	 * @return a {@code Vector3F} instance denoting the surface normal of the surface of this {@code Plane3F} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Vector3F calculateSurfaceNormal(final Ray3F ray, final float t, final boolean isCorrectlyOriented) {
		Objects.requireNonNull(ray, "ray == null");
		
		final Vector3F surfaceNormal0 = this.surfaceNormal;
		final Vector3F surfaceNormal1 = isCorrectlyOriented && surfaceNormal0.dotProduct(ray.direction) >= 0.0F ? surfaceNormal0.negate() : surfaceNormal0;
		
		return surfaceNormal1;
	}
	
	/**
	 * Returns the {@link Vector3F} instance that represents the surface normal of this {@code Plane3F} instance.
	 * 
	 * @return the {@code Vector3F} instance that represents the surface normal of this {@code Plane3F} instance
	 */
	public Vector3F getSurfaceNormal() {
		return this.surfaceNormal;
	}
	
	/**
	 * Compares {@code object} to this {@code Plane3F} instance for equality.
	 * <p>
	 * Returns {@code true} if, and only if, {@code object} is an instance of {@code Plane3F}, and their respective values are equal, {@code false} otherwise.
	 * 
	 * @param object the {@code Object} to compare to this {@code Plane3F} instance for equality
	 * @return {@code true} if, and only if, {@code object} is an instance of {@code Plane3F}, and their respective values are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object object) {
		if(object == this) {
			return true;
		} else if(!(object instanceof Plane3F)) {
			return false;
		} else if(!Objects.equals(this.a, Plane3F.class.cast(object).a)) {
			return false;
		} else if(!Objects.equals(this.b, Plane3F.class.cast(object).b)) {
			return false;
		} else if(!Objects.equals(this.c, Plane3F.class.cast(object).c)) {
			return false;
		} else if(!Objects.equals(this.surfaceNormal, Plane3F.class.cast(object).surfaceNormal)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returns the probability density function (PDF) value for solid angle.
	 * <p>
	 * If either {@code referencePoint}, {@code referenceSurfaceNormal}, {@code point} or {@code surfaceNormal} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param referencePoint the reference point on this {@code Plane3F} instance
	 * @param referenceSurfaceNormal the reference surface normal on this {@code Plane3F} instance
	 * @param point the point on this {@code Plane3F} instance
	 * @param surfaceNormal the surface normal on this {@code Plane3F} instance
	 * @return the probability density function (PDF) value for solid angle
	 * @throws NullPointerException thrown if, and only if, either {@code referencePoint}, {@code referenceSurfaceNormal}, {@code point} or {@code surfaceNormal} are {@code null}
	 */
	@Override
	public float calculateProbabilityDensityFunctionValueForSolidAngle(final Point3F referencePoint, final Vector3F referenceSurfaceNormal, final Point3F point, final Vector3F surfaceNormal) {
		Objects.requireNonNull(referencePoint, "referencePoint == null");
		Objects.requireNonNull(referenceSurfaceNormal, "referenceSurfaceNormal == null");
		Objects.requireNonNull(point, "point == null");
		Objects.requireNonNull(surfaceNormal, "surfaceNormal == null");
		
		return 0.0F;
	}
	
	/**
	 * Returns the surface area of this {@code Plane3F} instance.
	 * <p>
	 * This method returns {@code Float.POSITIVE_INFINITY}.
	 * 
	 * @return the surface area of this {@code Plane3F} instance
	 */
	@Override
	public float getSurfaceArea() {
		return Float.POSITIVE_INFINITY;
	}
	
	/**
	 * Returns the surface area probability density function (PDF) value of this {@code Plane3F} instance.
	 * <p>
	 * This method returns {@code 0.0F}.
	 * 
	 * @return the surface area probability density function (PDF) value of this {@code Plane3F} instance
	 */
	@Override
	public float getSurfaceAreaProbabilityDensityFunctionValue() {
		return 0.0F;
	}
	
	/**
	 * Returns the volume of this {@code Plane3F} instance.
	 * <p>
	 * This method returns {@code Float.POSITIVE_INFINITY}.
	 * 
	 * @return the volume of this {@code Plane3F} instance
	 */
	@Override
	public float getVolume() {
		return Float.POSITIVE_INFINITY;
	}
	
	/**
	 * Performs an intersection test between {@code ray} and this {@code Plane3F} instance.
	 * <p>
	 * Returns {@code t}, the parametric distance from {@code ray} to this {@code Plane3F} instance, or {@code Float.NaN} if no intersection exists.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} to perform an intersection test against this {@code Plane3F} instance
	 * @return {@code t}, the parametric distance from {@code ray} to this {@code Plane3F} instance, or {@code Float.NaN} if no intersection exists
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public float intersection(final Ray3F ray) {
		final Vector3F direction = ray.direction;
		final Vector3F surfaceNormal = this.surfaceNormal;
		
		final float dotProduct = surfaceNormal.dotProduct(direction);
		
		if(!MathF.equals(dotProduct, 0.0F)) {
			final Point3F a = this.a;
			final Point3F origin = ray.origin;
			
			final Vector3F originToA = Vector3F.direction(origin, a);
			
			final float t = originToA.dotProduct(surfaceNormal) / dotProduct;
			
			if(t > EPSILON) {
				return t;
			}
		}
		
		return Float.NaN;
	}
	
	/**
	 * Returns a hash code for this {@code Plane3F} instance.
	 * 
	 * @return a hash code for this {@code Plane3F} instance
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.a, this.b, this.c, this.surfaceNormal);
	}
}