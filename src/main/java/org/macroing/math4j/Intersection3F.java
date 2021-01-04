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

import java.util.Objects;
import java.util.function.Supplier;

/**
 * An {@code Intersection3F} denotes an intersection between a {@link Ray3F} instance and a {@link Shape3F} instance.
 * <p>
 * This class can be considered immutable and thread-safe if, and only if, its associated {@code Shape3F} instance is.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class Intersection3F {
	private final LazyReference<OrthoNormalBasis33F> orthoNormalBasis;
	private final LazyReference<Point2F> textureCoordinates;
	private final LazyReference<Point3F> surfaceIntersectionPoint;
	private final LazyReference<Vector3F> surfaceNormal;
	private final Ray3F ray;
	private final Shape3F shape;
	private final float t;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructs a new {@code Intersection3F} instance.
	 * <p>
	 * If either {@code ray}, {@code shape}, {@code orthoNormalBasisSupplier}, {@code textureCoordinatesSupplier}, {@code surfaceIntersectionPointSupplier} or {@code surfaceNormalSupplier} are {@code null}, a {@code NullPointerException} will be
	 * thrown.
	 * 
	 * @param ray the {@link Ray3F} instance that is associated with this {@code Intersection3F} instance
	 * @param shape the {@link Shape3F} instance that is associated with this {@code Intersection3F} instance
	 * @param t the parametric T value that is associated with this {@code Intersection3F} instance
	 * @param orthoNormalBasisSupplier a {@code Supplier} of the {@link OrthoNormalBasis33F} instance that is associated with this {@code Intersection3F} instance
	 * @param textureCoordinatesSupplier a {@code Supplier} of the {@link Point2F} instance that represents the texture coordinates and is associated with this {@code Intersection3F} instance
	 * @param surfaceIntersectionPointSupplier a {@code Supplier} of the {@link Point3F} instance that represents the surface intersection point and is associated with this {@code Intersection3F} instance
	 * @param surfaceNormalSupplier a {@code Supplier} of the {@link Vector3F} instance that represents the surface normal and is associated with this {@code Intersection3F} instance
	 * @throws NullPointerException thrown if, and only if, either {@code ray}, {@code shape}, {@code orthoNormalBasisSupplier}, {@code textureCoordinatesSupplier}, {@code surfaceIntersectionPointSupplier} or {@code surfaceNormalSupplier} are
	 *                              {@code null}
	 */
	public Intersection3F(final Ray3F ray, final Shape3F shape, final float t, final Supplier<OrthoNormalBasis33F> orthoNormalBasisSupplier, final Supplier<Point2F> textureCoordinatesSupplier, final Supplier<Point3F> surfaceIntersectionPointSupplier, final Supplier<Vector3F> surfaceNormalSupplier) {
		this.ray = Objects.requireNonNull(ray, "ray == null");
		this.shape = Objects.requireNonNull(shape, "shape == null");
		this.t = t;
		this.orthoNormalBasis = new LazyReference<>(Objects.requireNonNull(orthoNormalBasisSupplier, "orthoNormalBasisSupplier == null"));
		this.textureCoordinates = new LazyReference<>(Objects.requireNonNull(textureCoordinatesSupplier, "textureCoordinatesSupplier == null"));
		this.surfaceIntersectionPoint = new LazyReference<>(Objects.requireNonNull(surfaceIntersectionPointSupplier, "surfaceIntersectionPointSupplier == null"));
		this.surfaceNormal = new LazyReference<>(Objects.requireNonNull(surfaceNormalSupplier, "surfaceNormalSupplier == null"));
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns the {@link OrthoNormalBasis33F} instance that is associated with this {@code Intersection3F} instance.
	 * 
	 * @return the {@code OrthoNormalBasis33F} instance that is associated with this {@code Intersection3F} instance
	 */
	public OrthoNormalBasis33F getOrthoNormalBasis() {
		return this.orthoNormalBasis.get();
	}
	
	/**
	 * Returns the {@link Point2F} instance that represents the texture coordinates that are associated with this {@code Intersection3F} instance.
	 * 
	 * @return the {@code Point2F} instance that represents the texture coordinates that are associated with this {@code Intersection3F} instance
	 */
	public Point2F getTextureCoordinates() {
		return this.textureCoordinates.get();
	}
	
	/**
	 * Returns the {@link Point3F} instance that represents the surface intersection point that is associated with this {@code Intersection3F} instance.
	 * 
	 * @return the {@code Point3F} instance that represents the surface intersection point that is associated with this {@code Intersection3F} instance
	 */
	public Point3F getSurfaceIntersectionPoint() {
		return this.surfaceIntersectionPoint.get();
	}
	
	/**
	 * Returns the {@link Ray3F} instance that is associated with this {@code Intersection3F} instance.
	 * 
	 * @return the {@code Ray3F} instance that is associated with this {@code Intersection3F} instance
	 */
	public Ray3F getRay() {
		return this.ray;
	}
	
	/**
	 * Returns the {@link Shape3F} instance that is associated with this {@code Intersection3F} instance.
	 * 
	 * @return the {@code Shape3F} instance that is associated with this {@code Intersection3F} instance
	 */
	public Shape3F getShape() {
		return this.shape;
	}
	
	/**
	 * Returns the {@link Vector3F} instance that represents the surface normal that is associated with this {@code Intersection3F} instance.
	 * 
	 * @return the {@code Vector3F} instance that represents the surface normal that is associated with this {@code Intersection3F} instance
	 */
	public Vector3F getSurfaceNormal() {
		return this.surfaceNormal.get();
	}
	
	/**
	 * Returns the parametric T that is associated with this {@code Intersection3F} instance.
	 * 
	 * @return the parametric T that is associated with this {@code Intersection3F} instance
	 */
	public float getT() {
		return this.t;
	}
}