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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * A {@code TriangleMesh3F} denotes a 3-dimensional triangle mesh that uses the data type {@code float}.
 * <p>
 * This class is immutable and therefore thread-safe.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class TriangleMesh3F implements Shape3F {
	private final BoundingVolume3F boundingVolume;
	private final List<Triangle3F> triangles;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructs a new {@code TriangleMesh3F} instance.
	 * <p>
	 * If either {@code triangles} or any of its elements are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param triangles the {@code Triangle3F} instances associated with this {@code TriangleMesh3F} instance
	 * @throws NullPointerException thrown if, and only if, either {@code triangles} or any of its elements are {@code null}
	 */
	public TriangleMesh3F(final List<Triangle3F> triangles) {
		this.triangles = new ArrayList<>(doRequireNonNullList(triangles));
		this.boundingVolume = doCreateBoundingVolume(triangles);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns a {@link BoundingVolume3F} instance that contains this {@code TriangleMesh3F} instance.
	 * 
	 * @return a {@code BoundingVolume3F} instance that contains this {@code TriangleMesh3F} instance
	 */
	@Override
	public BoundingVolume3F getBoundingVolume() {
		return this.boundingVolume;
	}
	
	/**
	 * Returns a {@code List} with the {@link Triangle3F} instances associated with this {@code TriangleMesh3F} instance.
	 * <p>
	 * Modifying the returned {@code List} will not affect this {@code TriangleMesh3F} instance.
	 * 
	 * @return a {@code List} with the {@code Triangle3F} instances associated with this {@code TriangleMesh3F} instance
	 */
	public List<Triangle3F> getTriangles() {
		return new ArrayList<>(this.triangles);
	}
	
	/**
	 * Samples this {@code TriangleMesh3F} instance.
	 * <p>
	 * Returns an optional {@link SurfaceSample3F} with the surface sample.
	 * <p>
	 * If either {@code referencePoint} or {@code referenceSurfaceNormal} are {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * Note: This method has not been implemented yet.
	 * 
	 * @param referencePoint the reference point on this {@code TriangleMesh3F} instance
	 * @param referenceSurfaceNormal the reference surface normal on this {@code TriangleMesh3F} instance
	 * @param u a random {@code float} with a uniform distribution between {@code 0.0F} and {@code 1.0F}
	 * @param v a random {@code float} with a uniform distribution between {@code 0.0F} and {@code 1.0F}
	 * @return an optional {@code SurfaceSample3F} with the surface sample
	 * @throws NullPointerException thrown if, and only if, either {@code referencePoint} or {@code referenceSurfaceNormal} are {@code null}
	 */
	@Override
	public Optional<SurfaceSample3F> sample(final Point3F referencePoint, final Vector3F referenceSurfaceNormal, final float u, final float v) {
		Objects.requireNonNull(referencePoint, "referencePoint == null");
		Objects.requireNonNull(referenceSurfaceNormal, "referenceSurfaceNormal == null");
		
		return Optional.empty();//TODO: Implement!
	}
	
	/**
	 * Performs an intersection test between {@code ray} and this {@code TriangleMesh3F} instance.
	 * <p>
	 * Returns an {@code Optional} with an optional {@link Intersection3F} instance that contains information about the intersection, if it was found.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} to perform an intersection test against this {@code TriangleMesh3F} instance
	 * @return an {@code Optional} with an optional {@code Intersection3F} instance that contains information about the intersection, if it was found
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Optional<Intersection3F> intersection(final Ray3F ray) {
		Objects.requireNonNull(ray, "ray == null");
		
		Intersection3F intersection = null;
		
		if(getBoundingVolume().intersects(ray)) {
			for(final Triangle3F triangle : this.triangles) {
				final Optional<Intersection3F> optionalIntersection = triangle.intersection(ray);
				
				if(optionalIntersection.isPresent() && (intersection == null || intersection.getT() > optionalIntersection.get().getT())) {
					intersection = optionalIntersection.get();
				}
			}
		}
		
		return Optional.ofNullable(intersection);
	}
	
	/**
	 * Returns a {@link Point2F} instance denoting the texture coordinates (or UV-coordinates) of the surface of this {@code TriangleMesh3F} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} instance that was used in a call to {@link #intersectionT(Ray3F)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code TriangleMesh3F} instance that was returned by {@code intersectionT(Ray3F)}
	 * @return a {@code Point2F} instance denoting the texture coordinates (or UV-coordinates) of the surface of this {@code TriangleMesh3F} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Point2F calculateTextureCoordinates(final Ray3F ray, final float t) {
		Objects.requireNonNull(ray, "ray == null");
		
		if(getBoundingVolume().intersects(ray)) {
			for(final Triangle3F triangle : this.triangles) {
				final float currentT = triangle.intersectionT(ray);
				
				if(!Float.isNaN(currentT) && MathF.equals(currentT, t)) {
					return triangle.calculateTextureCoordinates(ray, t);
				}
			}
		}
		
		return new Point2F(0.5F, 0.5F);
	}
	
	/**
	 * Returns a {@code String} representation of this {@code TriangleMesh3F} instance.
	 * 
	 * @return a {@code String} representation of this {@code TriangleMesh3F} instance
	 */
	@Override
	public String toString() {
		return "new TriangleMesh3F(...)";
	}
	
	/**
	 * Returns a {@link Vector3F} instance denoting the surface normal of the surface of this {@code TriangleMesh3F} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} instance that was used in a call to {@link #intersectionT(Ray3F)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code TriangleMesh3F} instance that was returned by {@code intersectionT(Ray3F)}
	 * @return a {@code Vector3F} instance denoting the surface normal of the surface of this {@code TriangleMesh3F} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Vector3F calculateSurfaceNormal(final Ray3F ray, final float t) {
		Objects.requireNonNull(ray, "ray == null");
		
		if(getBoundingVolume().intersects(ray)) {
			for(final Triangle3F triangle : this.triangles) {
				final float currentT = triangle.intersectionT(ray);
				
				if(!Float.isNaN(currentT) && MathF.equals(currentT, t)) {
					return triangle.calculateSurfaceNormal(ray, t);
				}
			}
		}
		
		return Vector3F.ZERO;
	}
	
	/**
	 * Compares {@code object} to this {@code TriangleMesh3F} instance for equality.
	 * <p>
	 * Returns {@code true} if, and only if, {@code object} is an instance of {@code TriangleMesh3F}, and their respective values are equal, {@code false} otherwise.
	 * 
	 * @param object the {@code Object} to compare to this {@code TriangleMesh3F} instance for equality
	 * @return {@code true} if, and only if, {@code object} is an instance of {@code TriangleMesh3F}, and their respective values are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object object) {
		if(object == this) {
			return true;
		} else if(!(object instanceof TriangleMesh3F)) {
			return false;
		} else if(!Objects.equals(this.triangles, TriangleMesh3F.class.cast(object).triangles)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returns the probability density function (PDF) value for solid angle.
	 * <p>
	 * If either {@code referencePoint}, {@code referenceSurfaceNormal}, {@code point} or {@code surfaceNormal} are {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * Note: This method has not been implemented yet.
	 * 
	 * @param referencePoint the reference point on this {@code TriangleMesh3F} instance
	 * @param referenceSurfaceNormal the reference surface normal on this {@code TriangleMesh3F} instance
	 * @param point the point on this {@code TriangleMesh3F} instance
	 * @param surfaceNormal the surface normal on this {@code TriangleMesh3F} instance
	 * @return the probability density function (PDF) value for solid angle
	 * @throws NullPointerException thrown if, and only if, either {@code referencePoint}, {@code referenceSurfaceNormal}, {@code point} or {@code surfaceNormal} are {@code null}
	 */
	@Override
	public float calculateProbabilityDensityFunctionValueForSolidAngle(final Point3F referencePoint, final Vector3F referenceSurfaceNormal, final Point3F point, final Vector3F surfaceNormal) {
		Objects.requireNonNull(referencePoint, "referencePoint == null");
		Objects.requireNonNull(referenceSurfaceNormal, "referenceSurfaceNormal == null");
		Objects.requireNonNull(point, "point == null");
		Objects.requireNonNull(surfaceNormal, "surfaceNormal == null");
		
		return 0.0F;//TODO: Implement!
	}
	
	/**
	 * Returns the surface area of this {@code TriangleMesh3F} instance.
	 * 
	 * @return the surface area of this {@code TriangleMesh3F} instance
	 */
	@Override
	public float getSurfaceArea() {
		float surfaceArea = 0.0F;
		
		for(final Triangle3F triangle : this.triangles) {
			surfaceArea += triangle.getSurfaceArea();
		}
		
		return surfaceArea;
	}
	
	/**
	 * Returns the surface area probability density function (PDF) value of this {@code TriangleMesh3F} instance.
	 * <p>
	 * Note: This method has not been implemented yet.
	 * 
	 * @return the surface area probability density function (PDF) value of this {@code TriangleMesh3F} instance
	 */
	@Override
	public float getSurfaceAreaProbabilityDensityFunctionValue() {
		return 0.0F;//TODO: Implement!
	}
	
	/**
	 * Returns the volume of this {@code TriangleMesh3F} instance.
	 * <p>
	 * Note: This method has not been implemented yet.
	 * 
	 * @return the volume of this {@code TriangleMesh3F} instance
	 */
	@Override
	public float getVolume() {
		return 0.0F;//TODO: Implement!
	}
	
	/**
	 * Performs an intersection test between {@code ray} and this {@code TriangleMesh3F} instance.
	 * <p>
	 * Returns {@code t}, the parametric distance from {@code ray} to this {@code TriangleMesh3F} instance, or {@code Float.NaN} if no intersection exists.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} to perform an intersection test against this {@code TriangleMesh3F} instance
	 * @return {@code t}, the parametric distance from {@code ray} to this {@code TriangleMesh3F} instance, or {@code Float.NaN} if no intersection exists
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public float intersectionT(final Ray3F ray) {
		Objects.requireNonNull(ray, "ray == null");
		
		float t = Float.NaN;
		
		if(getBoundingVolume().intersects(ray)) {
			for(final Triangle3F triangle : this.triangles) {
				final float currentT = triangle.intersectionT(ray);
				
				if(!Float.isNaN(currentT) && (Float.isNaN(t) || currentT < t)) {
					t = currentT;
				}
			}
		}
		
		return t;
	}
	
	/**
	 * Returns a hash code for this {@code TriangleMesh3F} instance.
	 * 
	 * @return a hash code for this {@code TriangleMesh3F} instance
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.triangles);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private static BoundingVolume3F doCreateBoundingVolume(final List<Triangle3F> triangles) {
		Point3F maximum = Point3F.minimum();
		Point3F minimum = Point3F.maximum();
		
		for(final Triangle3F triangle : triangles) {
			maximum = Point3F.maximum(triangle.getBoundingVolume().getMaximum(), maximum);
			minimum = Point3F.minimum(triangle.getBoundingVolume().getMinimum(), minimum);
		}
		
		return new RectangularCuboid3F(maximum, minimum);
	}
	
	private static List<Triangle3F> doRequireNonNullList(final List<Triangle3F> triangles) {
		Objects.requireNonNull(triangles, "triangles == null");
		
		for(int i = 0; i < triangles.size(); i++) {
			Objects.requireNonNull(triangles.get(i), String.format("triangles.get(%s) == null", Integer.toString(i)));
		}
		
		return triangles;
	}
}