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
import static org.macroing.math4j.MathD.sqrt;

import java.util.Objects;

/**
 * A {@code Sphere3D} denotes a sphere that uses the data type {@code double}.
 * <p>
 * This class is immutable and therefore thread-safe.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class Sphere3D implements Shape3D {
	private static final double EPSILON = 0.0001D;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final Point3D center;
	private final double radius;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructs a new {@code Sphere3D} instance given its center and radius.
	 * <p>
	 * If {@code center} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param center the center of the {@code Sphere3D} instance
	 * @param radius the radius of the {@code Sphere3D} instance
	 * @throws NullPointerException thrown if, and only if, {@code center} is {@code null}
	 */
	public Sphere3D(final Point3D center, final double radius) {
		this.center = Objects.requireNonNull(center, "center == null");
		this.radius = radius;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns an {@link OrthoNormalBasis33D} instance denoting the OrthoNormal Basis (ONB) of the surface of this {@code Sphere3D} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3D} instance that was used in a call to {@link #intersection(Ray3D)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Sphere3D} instance that was returned by {@code intersection(Ray3D)}
	 * @return an {@code OrthoNormalBasis33D} instance denoting the OrthoNormal Basis (ONB) of the surface of this {@code Sphere3D} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public OrthoNormalBasis33D calculateOrthoNormalBasis(final Ray3D ray, final double t) {
		return new OrthoNormalBasis33D(calculateSurfaceNormal(ray, t));
	}
	
	/**
	 * Returns a {@link Point2D} instance denoting the texture coordinates (or UV-coordinates) of the surface of this {@code Sphere3D} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3D} instance that was used in a call to {@link #intersection(Ray3D)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Sphere3D} instance that was returned by {@code intersection(Ray3D)}
	 * @return a {@code Point2D} instance denoting the texture coordinates (or UV-coordinates) of the surface of this {@code Sphere3D} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Point2D calculateTextureCoordinates(final Ray3D ray, final double t) {
		return Point2D.direction(ray.direction);
	}
	
	/**
	 * Returns a {@link Point3D} instance denoting the surface intersection point of the surface of this {@code Sphere3D} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3D} instance that was used in a call to {@link #intersection(Ray3D)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Sphere3D} instance that was returned by {@code intersection(Ray3D)}
	 * @return a {@code Point3D} instance denoting the surface intersection point of the surface of this {@code Sphere3D} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Point3D calculateSurfaceIntersectionPoint(final Ray3D ray, final double t) {
		return ray.origin.add(ray.direction, t);
	}
	
	/**
	 * Returns the center of this {@code Sphere3D} instance.
	 * 
	 * @return the center of this {@code Sphere3D} instance
	 */
	public Point3D getCenter() {
		return this.center;
	}
	
	/**
	 * Returns a {@code String} representation of this {@code Sphere3D} instance.
	 * 
	 * @return a {@code String} representation of this {@code Sphere3D} instance
	 */
	@Override
	public String toString() {
		return String.format("new Sphere3D(%s, %s)", this.center, Double.toString(this.radius));
	}
	
	/**
	 * Returns a {@link Vector3D} instance denoting the surface normal of the surface of this {@code Sphere3D} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3D} instance that was used in a call to {@link #intersection(Ray3D)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Sphere3D} instance that was returned by {@code intersection(Ray3D)}
	 * @return a {@code Vector3D} instance denoting the surface normal of the surface of this {@code Sphere3D} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Vector3D calculateSurfaceNormal(final Ray3D ray, final double t) {
		return Vector3D.direction(this.center, calculateSurfaceIntersectionPoint(ray, t)).normalize();
	}
	
	/**
	 * Compares {@code object} to this {@code Sphere3D} instance for equality.
	 * <p>
	 * Returns {@code true} if, and only if, {@code object} is an instance of {@code Sphere3D}, and their respective values are equal, {@code false} otherwise.
	 * 
	 * @param object the {@code Object} to compare to this {@code Sphere3D} instance for equality
	 * @return {@code true} if, and only if, {@code object} is an instance of {@code Sphere3D}, and their respective values are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object object) {
		if(object == this) {
			return true;
		} else if(!(object instanceof Sphere3D)) {
			return false;
		} else if(!Objects.equals(this.center, Sphere3D.class.cast(object).center)) {
			return false;
		} else if(!MathD.equals(this.radius, Sphere3D.class.cast(object).radius)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returns the diameter of this {@code Sphere3D} instance.
	 * 
	 * @return the diameter of this {@code Sphere3D} instance
	 */
	public double getDiameter() {
		return this.radius * 2.0D;
	}
	
	/**
	 * Returns the radius of this {@code Sphere3D} instance.
	 * 
	 * @return the radius of this {@code Sphere3D} instance
	 */
	public double getRadius() {
		return this.radius;
	}
	
	/**
	 * Returns the squared radius of this {@code Sphere3D} instance.
	 * 
	 * @return the squared radius of this {@code Sphere3D} instance
	 */
	public double getRadiusSquared() {
		return this.radius * this.radius;
	}
	
	/**
	 * Returns the surface area of this {@code Sphere3D} instance.
	 * 
	 * @return the surface area of this {@code Sphere3D} instance
	 */
	public double getSurfaceArea() {
		return 4.0D * PI * (this.radius * this.radius);
	}
	
	/**
	 * Performs an intersection test between {@code ray} and this {@code Sphere3D} instance.
	 * <p>
	 * Returns {@code t}, the parametric distance from {@code ray} to this {@code Sphere3D} instance, or {@code Double.NaN} if no intersection exists.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3D} to perform an intersection test against this {@code Sphere3D} instance
	 * @return {@code t}, the parametric distance from {@code ray} to this {@code Sphere3D} instance, or {@code Double.NaN} if no intersection exists
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public double intersection(final Ray3D ray) {
		final Point3D origin = ray.origin;
		final Point3D center = this.center;
		
		final Vector3D direction = ray.direction;
		final Vector3D originToCenter = Vector3D.direction(origin, center);
		
		final double originToCenterDotDirection = originToCenter.dotProduct(direction);
		final double originToCenterDotDirectionSquared = originToCenterDotDirection * originToCenterDotDirection;
		final double originToCenterLengthSquared = originToCenter.lengthSquared();
		final double radius = this.radius;
		final double radiusSquared = radius * radius;
		final double determinantSquared = originToCenterDotDirectionSquared - originToCenterLengthSquared + radiusSquared;
		
		if(determinantSquared >= 0.0D) {
			final double determinant = sqrt(determinantSquared);
			
			final double t0 = originToCenterDotDirection - determinant;
			final double t1 = originToCenterDotDirection + determinant;
			
			if(t0 > EPSILON) {
				return t0;
			}
			
			if(t1 > EPSILON) {
				return t1;
			}
		}
		
		return Double.NaN;
	}
	
	/**
	 * Returns a hash code for this {@code Sphere3D} instance.
	 * 
	 * @return a hash code for this {@code Sphere3D} instance
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.center, Double.valueOf(this.radius));
	}
}