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

import java.lang.reflect.Field;//TODO: Add Javadocs!
import java.util.Objects;
import java.util.function.Supplier;

//TODO: Add Javadocs!
public final class Intersection3F {
	private final LazyReference<OrthoNormalBasis33F> orthoNormalBasis;
	private final LazyReference<Point2F> textureCoordinates;
	private final LazyReference<Point3F> surfaceIntersectionPoint;
	private final LazyReference<Vector3F> surfaceNormal;
	private final Ray3F ray;
	private final Shape3F shape;
	private final float t;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
//	TODO: Add Javadocs!
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
	
//	TODO: Add Javadocs!
	public OrthoNormalBasis33F getOrthoNormalBasis() {
		return this.orthoNormalBasis.get();
	}
	
//	TODO: Add Javadocs!
	public Point2F getTextureCoordinates() {
		return this.textureCoordinates.get();
	}
	
//	TODO: Add Javadocs!
	public Point3F getSurfaceIntersectionPoint() {
		return this.surfaceIntersectionPoint.get();
	}
	
//	TODO: Add Javadocs!
	public Ray3F getRay() {
		return this.ray;
	}
	
//	TODO: Add Javadocs!
	public Shape3F getShape() {
		return this.shape;
	}
	
//	TODO: Add Javadocs!
	public Vector3F getSurfaceNormal() {
		return this.surfaceNormal.get();
	}
	
//	TODO: Add Javadocs!
	public float getT() {
		return this.t;
	}
}