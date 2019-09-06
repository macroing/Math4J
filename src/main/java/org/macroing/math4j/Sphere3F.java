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
import static org.macroing.math4j.MathF.sqrt;

import java.util.Objects;

/**
 * A {@code Sphere3F} denotes a sphere that uses the data type {@code float}.
 * <p>
 * This class is immutable and therefore thread-safe.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class Sphere3F implements Shape3F {
	private static final float EPSILON = 0.0001F;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final Point3F center;
	private final float radius;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructs a new {@code Sphere3F} instance given its center and radius.
	 * <p>
	 * If {@code center} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param center the center of the {@code Sphere3F} instance
	 * @param radius the radius of the {@code Sphere3F} instance
	 * @throws NullPointerException thrown if, and only if, {@code center} is {@code null}
	 */
	public Sphere3F(final Point3F center, final float radius) {
		this.center = Objects.requireNonNull(center, "center == null");
		this.radius = radius;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns an {@link OrthoNormalBasis33F} instance denoting the OrthoNormal Basis (ONB) of the surface of this {@code Sphere3F} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} instance that was used in a call to {@link #intersection(Ray3F)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Sphere3F} instance that was returned by {@code intersection(Ray3F)}
	 * @return an {@code OrthoNormalBasis33F} instance denoting the OrthoNormal Basis (ONB) of the surface of this {@code Sphere3F} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public OrthoNormalBasis33F calculateOrthoNormalBasis(final Ray3F ray, final float t) {
		return new OrthoNormalBasis33F(calculateSurfaceNormal(ray, t));
	}
	
	/**
	 * Returns a {@link Point2F} instance denoting the texture coordinates (or UV-coordinates) of the surface of this {@code Sphere3F} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} instance that was used in a call to {@link #intersection(Ray3F)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Sphere3F} instance that was returned by {@code intersection(Ray3F)}
	 * @return a {@code Point2F} instance denoting the texture coordinates (or UV-coordinates) of the surface of this {@code Sphere3F} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Point2F calculateTextureCoordinates(final Ray3F ray, final float t) {
		return Point2F.direction(ray.direction);
	}
	
	/**
	 * Returns a {@link Point3F} instance denoting the surface intersection point of the surface of this {@code Sphere3F} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} instance that was used in a call to {@link #intersection(Ray3F)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Sphere3F} instance that was returned by {@code intersection(Ray3F)}
	 * @return a {@code Point3F} instance denoting the surface intersection point of the surface of this {@code Sphere3F} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Point3F calculateSurfaceIntersectionPoint(final Ray3F ray, final float t) {
		return ray.origin.add(ray.direction, t);
	}
	
	/**
	 * Returns the center of this {@code Sphere3F} instance.
	 * 
	 * @return the center of this {@code Sphere3F} instance
	 */
	public Point3F getCenter() {
		return this.center;
	}
	
	/**
	 * Returns a {@code String} representation of this {@code Sphere3F} instance.
	 * 
	 * @return a {@code String} representation of this {@code Sphere3F} instance
	 */
	@Override
	public String toString() {
		return String.format("new Sphere3F(%s, %s)", this.center, Float.toString(this.radius));
	}
	
	/**
	 * Returns a {@link Vector3F} instance denoting the surface normal of the surface of this {@code Sphere3F} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} instance that was used in a call to {@link #intersection(Ray3F)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Sphere3F} instance that was returned by {@code intersection(Ray3F)}
	 * @return a {@code Vector3F} instance denoting the surface normal of the surface of this {@code Sphere3F} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Vector3F calculateSurfaceNormal(final Ray3F ray, final float t) {
		return Vector3F.direction(this.center, calculateSurfaceIntersectionPoint(ray, t)).normalize();
	}
	
	/**
	 * Compares {@code object} to this {@code Sphere3F} instance for equality.
	 * <p>
	 * Returns {@code true} if, and only if, {@code object} is an instance of {@code Sphere3F}, and their respective values are equal, {@code false} otherwise.
	 * 
	 * @param object the {@code Object} to compare to this {@code Sphere3F} instance for equality
	 * @return {@code true} if, and only if, {@code object} is an instance of {@code Sphere3F}, and their respective values are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object object) {
		if(object == this) {
			return true;
		} else if(!(object instanceof Sphere3F)) {
			return false;
		} else if(!Objects.equals(this.center, Sphere3F.class.cast(object).center)) {
			return false;
		} else if(!MathF.equals(this.radius, Sphere3F.class.cast(object).radius)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returns the diameter of this {@code Sphere3F} instance.
	 * 
	 * @return the diameter of this {@code Sphere3F} instance
	 */
	public float getDiameter() {
		return this.radius * 2.0F;
	}
	
	/**
	 * Returns the radius of this {@code Sphere3F} instance.
	 * 
	 * @return the radius of this {@code Sphere3F} instance
	 */
	public float getRadius() {
		return this.radius;
	}
	
	/**
	 * Returns the squared radius of this {@code Sphere3F} instance.
	 * 
	 * @return the squared radius of this {@code Sphere3F} instance
	 */
	public float getRadiusSquared() {
		return this.radius * this.radius;
	}
	
	/**
	 * Returns the surface area of this {@code Sphere3F} instance.
	 * 
	 * @return the surface area of this {@code Sphere3F} instance
	 */
	public float getSurfaceArea() {
		return 4.0F * PI * getRadiusSquared();
	}
	
	/**
	 * Performs an intersection test between {@code ray} and this {@code Sphere3F} instance.
	 * <p>
	 * Returns {@code t}, the parametric distance from {@code ray} to this {@code Sphere3F} instance, or {@code Float.NaN} if no intersection exists.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} to perform an intersection test against this {@code Sphere3F} instance
	 * @return {@code t}, the parametric distance from {@code ray} to this {@code Sphere3F} instance, or {@code Float.NaN} if no intersection exists
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public float intersection(final Ray3F ray) {
		final Point3F origin = ray.origin;
		final Point3F center = this.center;
		
		final Vector3F direction = ray.direction;
		final Vector3F originToCenter = Vector3F.direction(origin, center);
		
		final float originToCenterDotDirection = originToCenter.dotProduct(direction);
		final float originToCenterDotDirectionSquared = originToCenterDotDirection * originToCenterDotDirection;
		final float originToCenterLengthSquared = originToCenter.lengthSquared();
		final float radius = this.radius;
		final float radiusSquared = radius * radius;
		final float determinantSquared = originToCenterDotDirectionSquared - originToCenterLengthSquared + radiusSquared;
		
		if(determinantSquared >= 0.0F) {
			final float determinant = sqrt(determinantSquared);
			
			final float t0 = originToCenterDotDirection - determinant;
			final float t1 = originToCenterDotDirection + determinant;
			
			if(t0 > EPSILON) {
				return t0;
			}
			
			if(t1 > EPSILON) {
				return t1;
			}
		}
		
		return Float.NaN;
	}
	
	/**
	 * Returns a hash code for this {@code Sphere3F} instance.
	 * 
	 * @return a hash code for this {@code Sphere3F} instance
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.center, Float.valueOf(this.radius));
	}
}