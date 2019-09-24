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

import java.util.Objects;

/**
 * A {@code SurfaceSample3D} denotes a 3-dimensional surface sample that uses the data type {@code double}.
 * <p>
 * This class is immutable and therefore thread-safe.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class SurfaceSample3D {
	private final Point3D point;
	private final Vector3D surfaceNormal;
	private final double probabilityDensityFunctionValue;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructs a new {@code SurfaceSample3D} instance.
	 * <p>
	 * If either {@code point} or {@code surfaceNormal} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param point the sampled point
	 * @param surfaceNormal the sampled surface normal
	 * @param probabilityDensityFunctionValue the sampled probability density function (PDF) value
	 * @throws NullPointerException thrown if, and only if, either {@code point} or {@code surfaceNormal} are {@code null}
	 */
	public SurfaceSample3D(final Point3D point, final Vector3D surfaceNormal, final double probabilityDensityFunctionValue) {
		this.point = Objects.requireNonNull(point, "point == null");
		this.surfaceNormal = Objects.requireNonNull(surfaceNormal, "surfaceNormal == null");
		this.probabilityDensityFunctionValue = probabilityDensityFunctionValue;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns the sampled point.
	 * 
	 * @return the sampled point
	 */
	public Point3D getPoint() {
		return this.point;
	}
	
	/**
	 * Returns a {@code String} representation of this {@code SurfaceSample3D} instance.
	 * 
	 * @return a {@code String} representation of this {@code SurfaceSample3D} instance
	 */
	@Override
	public String toString() {
		return String.format("new SurfaceSample3D(%s, %s, %s)", this.point, this.surfaceNormal, Double.toString(this.probabilityDensityFunctionValue));
	}
	
	/**
	 * Transforms this {@code SurfaceSample3D} instance from world space to object space.
	 * <p>
	 * Returns a new {@code SurfaceSample3D} instance with the result of the transformation.
	 * <p>
	 * If either {@code objectToWorld} or {@code worldToObject} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param objectToWorld a {@link Matrix44D} instance that transforms from object space to world space
	 * @param worldToObject a {@code Matrix44D} instance that transforms from world space to object space
	 * @return a new {@code SurfaceSample3D} instance with the result of the transformation
	 * @throws NullPointerException thrown if, and only if, either {@code objectToWorld} or {@code worldToObject} are {@code null}
	 */
	public SurfaceSample3D transformToObjectSpace(final Matrix44D objectToWorld, final Matrix44D worldToObject) {
		final Point3D point = this.point.transform(worldToObject);
		
		final Vector3D surfaceNormal = this.surfaceNormal.transformTranspose(objectToWorld);
		
		final double probabilityDensityFunctionValue = this.probabilityDensityFunctionValue;
		
		return new SurfaceSample3D(point, surfaceNormal, probabilityDensityFunctionValue);
	}
	
	/**
	 * Transforms this {@code SurfaceSample3D} instance from object space to world space.
	 * <p>
	 * Returns a new {@code SurfaceSample3D} instance with the result of the transformation.
	 * <p>
	 * If either {@code objectToWorld} or {@code worldToObject} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param objectToWorld a {@link Matrix44D} instance that transforms from object space to world space
	 * @param worldToObject a {@code Matrix44D} instance that transforms from world space to object space
	 * @return a new {@code SurfaceSample3D} instance with the result of the transformation
	 * @throws NullPointerException thrown if, and only if, either {@code objectToWorld} or {@code worldToObject} are {@code null}
	 */
	public SurfaceSample3D transformToWorldSpace(final Matrix44D objectToWorld, final Matrix44D worldToObject) {
		final Point3D point = this.point.transform(objectToWorld);
		
		final Vector3D surfaceNormal = this.surfaceNormal.transformTranspose(worldToObject);
		
		final double probabilityDensityFunctionValue = this.probabilityDensityFunctionValue;
		
		return new SurfaceSample3D(point, surfaceNormal, probabilityDensityFunctionValue);
	}
	
	/**
	 * Returns the sampled surface normal.
	 * 
	 * @return the sampled surface normal
	 */
	public Vector3D getSurfaceNormal() {
		return this.surfaceNormal;
	}
	
	/**
	 * Compares {@code object} to this {@code SurfaceSample3F} instance for equality.
	 * <p>
	 * Returns {@code true} if, and only if, {@code object} is an instance of {@code SurfaceSample3F}, and their respective values are equal, {@code false} otherwise.
	 * 
	 * @param object the {@code Object} to compare to this {@code SurfaceSample3F} instance for equality
	 * @return {@code true} if, and only if, {@code object} is an instance of {@code SurfaceSample3F}, and their respective values are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object object) {
		if(object == this) {
			return true;
		} else if(!(object instanceof SurfaceSample3D)) {
			return false;
		} else if(!(Objects.equals(this.point, SurfaceSample3D.class.cast(object).point))) {
			return false;
		} else if(!(Objects.equals(this.surfaceNormal, SurfaceSample3D.class.cast(object).surfaceNormal))) {
			return false;
		} else if(!MathD.equals(this.probabilityDensityFunctionValue, SurfaceSample3D.class.cast(object).probabilityDensityFunctionValue)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returns the sampled probability density function (PDF) value.
	 * 
	 * @return the sampled probability density function (PDF) value
	 */
	public double getProbabilityDensityFunctionValue() {
		return this.probabilityDensityFunctionValue;
	}
	
	/**
	 * Returns a hash code for this {@code SurfaceSample3D} instance.
	 * 
	 * @return a hash code for this {@code SurfaceSample3D} instance
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.point, this.surfaceNormal, Double.valueOf(this.probabilityDensityFunctionValue));
	}
}