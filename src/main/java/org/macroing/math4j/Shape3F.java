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

import java.util.Optional;

/**
 * A {@code Shape3F} denotes a 3-dimensional shape that uses the data type {@code float}.
 * <p>
 * All official implementations of this interface are immutable and therefore thread-safe. But this cannot be guaranteed for all implementations.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public interface Shape3F {
	/**
	 * Samples this {@code Shape3F} instance.
	 * <p>
	 * Returns an optional {@link SurfaceSample3F} with the surface sample.
	 * <p>
	 * If either {@code referencePoint} or {@code referenceSurfaceNormal} are {@code null}, a {@code NullPointerException} may be thrown. But no guarantees can be made.
	 * 
	 * @param referencePoint the reference point on this {@code Shape3F} instance
	 * @param referenceSurfaceNormal the reference surface normal on this {@code Shape3F} instance
	 * @param u a random {@code float} with a uniform distribution between {@code 0.0F} and {@code 1.0F}
	 * @param v a random {@code float} with a uniform distribution between {@code 0.0F} and {@code 1.0F}
	 * @return an optional {@code SurfaceSample3F} with the surface sample
	 * @throws NullPointerException thrown if, and only if, either {@code referencePoint} or {@code referenceSurfaceNormal} are {@code null}
	 */
	Optional<SurfaceSample3F> sample(final Point3F referencePoint, final Vector3F referenceSurfaceNormal, final float u, final float v);
	
	/**
	 * Returns an {@link OrthoNormalBasis33F} instance denoting the OrthoNormal Basis (ONB) of the surface of this {@code Shape3F} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} may be thrown. But no guarantees can be made.
	 * 
	 * @param ray the {@link Ray3F} instance that was used in a call to {@link #intersection(Ray3F)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Shape3F} instance that was returned by {@code intersection(Ray3F)}
	 * @param isCorrectlyOriented {@code true} if, and only if, the {@code OrthoNormalBasis33F} must lie in the same hemisphere as {@code ray}, {@code false} otherwise
	 * @return an {@code OrthoNormalBasis33F} instance denoting the OrthoNormal Basis (ONB) of the surface of this {@code Shape3F} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	OrthoNormalBasis33F calculateOrthoNormalBasis(final Ray3F ray, final float t, final boolean isCorrectlyOriented);
	
	/**
	 * Returns a {@link Point2F} instance denoting the texture coordinates (or UV-coordinates) of the surface of this {@code Shape3F} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} may be thrown. But no guarantees can be made.
	 * 
	 * @param ray the {@link Ray3F} instance that was used in a call to {@link #intersection(Ray3F)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Shape3F} instance that was returned by {@code intersection(Ray3F)}
	 * @return a {@code Point2F} instance denoting the texture coordinates (or UV-coordinates) of the surface of this {@code Shape3F} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	Point2F calculateTextureCoordinates(final Ray3F ray, final float t);
	
	/**
	 * Returns a {@link Point3F} instance denoting the surface intersection point of the surface of this {@code Shape3F} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} may be thrown. But no guarantees can be made.
	 * 
	 * @param ray the {@link Ray3F} instance that was used in a call to {@link #intersection(Ray3F)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Shape3F} instance that was returned by {@code intersection(Ray3F)}
	 * @return a {@code Point3F} instance denoting the surface intersection point of the surface of this {@code Shape3F} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	Point3F calculateSurfaceIntersectionPoint(final Ray3F ray, final float t);
	
	/**
	 * Returns a {@link Vector3F} instance denoting the surface normal of the surface of this {@code Shape3F} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} may be thrown. But no guarantees can be made.
	 * 
	 * @param ray the {@link Ray3F} instance that was used in a call to {@link #intersection(Ray3F)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Shape3F} instance that was returned by {@code intersection(Ray3F)}
	 * @param isCorrectlyOriented {@code true} if, and only if, the {@code Vector3F} must lie in the same hemisphere as {@code ray}, {@code false} otherwise
	 * @return a {@code Vector3F} instance denoting the surface normal of the surface of this {@code Shape3F} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	Vector3F calculateSurfaceNormal(final Ray3F ray, final float t, final boolean isCorrectlyOriented);
	
	/**
	 * Returns the probability density function (PDF) value for solid angle.
	 * <p>
	 * If either {@code referencePoint}, {@code referenceSurfaceNormal}, {@code point} or {@code surfaceNormal} are {@code null}, a {@code NullPointerException} may be thrown. But no guarantees can be made.
	 * 
	 * @param referencePoint the reference point on this {@code Shape3F} instance
	 * @param referenceSurfaceNormal the reference surface normal on this {@code Shape3F} instance
	 * @param point the point on this {@code Shape3F} instance
	 * @param surfaceNormal the surface normal on this {@code Shape3F} instance
	 * @return the probability density function (PDF) value for solid angle
	 * @throws NullPointerException thrown if, and only if, either {@code referencePoint}, {@code referenceSurfaceNormal}, {@code point} or {@code surfaceNormal} are {@code null}
	 */
	float calculateProbabilityDensityFunctionValueForSolidAngle(final Point3F referencePoint, final Vector3F referenceSurfaceNormal, final Point3F point, final Vector3F surfaceNormal);
	
	/**
	 * Returns the surface area of this {@code Shape3F} instance.
	 * 
	 * @return the surface area of this {@code Shape3F} instance
	 */
	float getSurfaceArea();
	
	/**
	 * Returns the surface area probability density function (PDF) value of this {@code Shape3F} instance.
	 * 
	 * @return the surface area probability density function (PDF) value of this {@code Shape3F} instance
	 */
	float getSurfaceAreaProbabilityDensityFunctionValue();
	
	/**
	 * Returns the volume of this {@code Shape3F} instance.
	 * 
	 * @return the volume of this {@code Shape3F} instance
	 */
	float getVolume();
	
	/**
	 * Performs an intersection test between {@code ray} and this {@code Shape3F} instance.
	 * <p>
	 * Returns {@code t}, the parametric distance from {@code ray} to this {@code Shape3F} instance, or {@code Float.NaN} if no intersection exists.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} may be thrown. But no guarantees can be made.
	 * 
	 * @param ray the {@link Ray3F} to perform an intersection test against this {@code Shape3F} instance
	 * @return {@code t}, the parametric distance from {@code ray} to this {@code Shape3F} instance, or {@code Float.NaN} if no intersection exists
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	float intersection(final Ray3F ray);
}