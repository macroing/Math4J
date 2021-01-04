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

import java.util.Optional;

/**
 * A {@code Shape3D} denotes a 3-dimensional shape that uses the data type {@code double}.
 * <p>
 * All official implementations of this interface are immutable and therefore thread-safe. But this cannot be guaranteed for all implementations.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public interface Shape3D {
	/**
	 * Samples this {@code Shape3D} instance.
	 * <p>
	 * Returns an optional {@link SurfaceSample3D} with the surface sample.
	 * <p>
	 * If either {@code referencePoint} or {@code referenceSurfaceNormal} are {@code null}, a {@code NullPointerException} may be thrown. But no guarantees can be made.
	 * 
	 * @param referencePoint the reference point on this {@code Shape3D} instance
	 * @param referenceSurfaceNormal the reference surface normal on this {@code Shape3D} instance
	 * @param u a random {@code double} with a uniform distribution between {@code 0.0D} and {@code 1.0D}
	 * @param v a random {@code double} with a uniform distribution between {@code 0.0D} and {@code 1.0D}
	 * @return an optional {@code SurfaceSample3D} with the surface sample
	 * @throws NullPointerException thrown if, and only if, either {@code referencePoint} or {@code referenceSurfaceNormal} are {@code null}
	 */
	Optional<SurfaceSample3D> sample(final Point3D referencePoint, final Vector3D referenceSurfaceNormal, final double u, final double v);
	
	/**
	 * Returns a {@link Point2D} instance denoting the texture coordinates (or UV-coordinates) of the surface of this {@code Shape3D} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} may be thrown. But no guarantees can be made.
	 * 
	 * @param ray the {@link Ray3D} instance that was used in a call to {@link #intersectionT(Ray3D)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Shape3D} instance that was returned by {@code intersectionT(Ray3D)}
	 * @return a {@code Point2D} instance denoting the texture coordinates (or UV-coordinates) of the surface of this {@code Shape3D} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	Point2D calculateTextureCoordinates(final Ray3D ray, final double t);
	
	/**
	 * Returns a {@link Vector3D} instance denoting the surface normal of the surface of this {@code Shape3D} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} may be thrown. But no guarantees can be made.
	 * 
	 * @param ray the {@link Ray3D} instance that was used in a call to {@link #intersectionT(Ray3D)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Shape3D} instance that was returned by {@code intersectionT(Ray3D)}
	 * @return a {@code Vector3D} instance denoting the surface normal of the surface of this {@code Shape3D} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	Vector3D calculateSurfaceNormal(final Ray3D ray, final double t);
	
	/**
	 * Returns the probability density function (PDF) value for solid angle.
	 * <p>
	 * If either {@code referencePoint}, {@code referenceSurfaceNormal}, {@code point} or {@code surfaceNormal} are {@code null}, a {@code NullPointerException} may be thrown. But no guarantees can be made.
	 * 
	 * @param referencePoint the reference point on this {@code Shape3D} instance
	 * @param referenceSurfaceNormal the reference surface normal on this {@code Shape3D} instance
	 * @param point the point on this {@code Shape3D} instance
	 * @param surfaceNormal the surface normal on this {@code Shape3D} instance
	 * @return the probability density function (PDF) value for solid angle
	 * @throws NullPointerException thrown if, and only if, either {@code referencePoint}, {@code referenceSurfaceNormal}, {@code point} or {@code surfaceNormal} are {@code null}
	 */
	double calculateProbabilityDensityFunctionValueForSolidAngle(final Point3D referencePoint, final Vector3D referenceSurfaceNormal, final Point3D point, final Vector3D surfaceNormal);
	
	/**
	 * Returns the surface area of this {@code Shape3D} instance.
	 * 
	 * @return the surface area of this {@code Shape3D} instance
	 */
	double getSurfaceArea();
	
	/**
	 * Returns the surface area probability density function (PDF) value of this {@code Shape3D} instance.
	 * 
	 * @return the surface area probability density function (PDF) value of this {@code Shape3D} instance
	 */
	double getSurfaceAreaProbabilityDensityFunctionValue();
	
	/**
	 * Returns the volume of this {@code Shape3D} instance.
	 * 
	 * @return the volume of this {@code Shape3D} instance
	 */
	double getVolume();
	
	/**
	 * Performs an intersection test between {@code ray} and this {@code Shape3D} instance.
	 * <p>
	 * Returns {@code t}, the parametric distance from {@code ray} to this {@code Shape3D} instance, or {@code Double.NaN} if no intersection exists.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} may be thrown. But no guarantees can be made.
	 * 
	 * @param ray the {@link Ray3D} to perform an intersection test against this {@code Shape3D} instance
	 * @return {@code t}, the parametric distance from {@code ray} to this {@code Shape3D} instance, or {@code Double.NaN} if no intersection exists
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	double intersectionT(final Ray3D ray);
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns an {@link OrthoNormalBasis33D} instance denoting the OrthoNormal Basis (ONB) of the surface of this {@code Shape3D} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} may be thrown. But no guarantees can be made.
	 * 
	 * @param ray the {@link Ray3D} instance that was used in a call to {@link #intersectionT(Ray3D)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Shape3D} instance that was returned by {@code intersectionT(Ray3D)}
	 * @return an {@code OrthoNormalBasis33D} instance denoting the OrthoNormal Basis (ONB) of the surface of this {@code Shape3D} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	default OrthoNormalBasis33D calculateOrthoNormalBasis(final Ray3D ray, final double t) {
		return new OrthoNormalBasis33D(calculateSurfaceNormal(ray, t));
	}
	
	/**
	 * Returns a {@link Point3D} instance denoting the surface intersection point of the surface of this {@code Shape3D} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3D} instance that was used in a call to {@link #intersectionT(Ray3D)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Shape3D} instance that was returned by {@code intersectionT(Ray3D)}
	 * @return a {@code Point3D} instance denoting the surface intersection point of the surface of this {@code Shape3D} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	default Point3D calculateSurfaceIntersectionPoint(final Ray3D ray, final double t) {
		return ray.origin.add(ray.direction, t);
	}
}