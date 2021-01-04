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

/**
 * A {@code BoundingVolume3D} denotes a 3-dimensional bounding volume that uses the data type {@code double}.
 * <p>
 * All official implementations of this interface are immutable and therefore thread-safe. But this cannot be guaranteed for all implementations.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public interface BoundingVolume3D {
	/**
	 * Performs a transformation.
	 * <p>
	 * Returns a new {@code BoundingVolume3D} instance with the result of the transformation.
	 * <p>
	 * If {@code m} is {@code null}, a {@code NullPointerException} may be thrown. But no guarantees can be made.
	 * 
	 * @param m the {@link Matrix44D} instance to perform the transformation with
	 * @return a new {@code BoundingVolume3D} instance with the result of the transformation
	 * @throws NullPointerException thrown if, and only if, {@code m} is {@code null}
	 */
	BoundingVolume3D transform(final Matrix44D m);
	
	/**
	 * Returns a {@link Point3D} instance that represents the closest point to {@code p} and is contained inside this {@code BoundingVolume3D} instance.
	 * <p>
	 * If {@code p} is {@code null}, a {@code NullPointerException} may be thrown. But no guarantees can be made.
	 * 
	 * @param p a {@code Point3D} instance
	 * @return a {@code Point3D} instance that represents the closest point to {@code p} and is contained inside this {@code BoundingVolume3D} instance
	 * @throws NullPointerException thrown if, and only if, {@code p} is {@code null}
	 */
	Point3D getClosestPointTo(final Point3D p);
	
	/**
	 * Returns a {@link Point3D} with the greatest X-, Y- and Z-coordinates that is contained in this {@code BoundingVolume3D} instance.
	 * 
	 * @return a {@code Point3D} with the greatest X-, Y- and Z-coordinates that is contained in this {@code BoundingVolume3D} instance
	 */
	Point3D getMaximum();
	
	/**
	 * Returns a {@link Point3D} with the smallest X-, Y- and Z-coordinates that is contained in this {@code BoundingVolume3D} instance.
	 * 
	 * @return a {@code Point3D} with the smallest X-, Y- and Z-coordinates that is contained in this {@code BoundingVolume3D} instance
	 */
	Point3D getMinimum();
	
	/**
	 * Returns {@code true} if, and only if, {@code p} is contained inside this {@code BoundingVolume3D} instance, {@code false} otherwise.
	 * <p>
	 * If {@code p} is {@code null}, a {@code NullPointerException} may be thrown. But no guarantees can be made.
	 * 
	 * @param p a {@link Point3D} instance
	 * @return {@code true} if, and only if, {@code p} is contained inside this {@code BoundingVolume3D} instance, {@code false} otherwise
	 * @throws NullPointerException thrown if, and only if, {@code p} is {@code null}
	 */
	boolean contains(final Point3D p);
	
	/**
	 * Returns the surface area of this {@code BoundingVolume3D} instance.
	 * 
	 * @return the surface area of this {@code BoundingVolume3D} instance
	 */
	double getSurfaceArea();
	
	/**
	 * Returns the volume of this {@code BoundingVolume3D} instance.
	 * 
	 * @return the volume of this {@code BoundingVolume3D} instance
	 */
	double getVolume();
	
	/**
	 * Performs an intersection test between {@code ray} and this {@code BoundingVolume3D} instance.
	 * <p>
	 * Returns {@code t}, the parametric distance from {@code ray} to this {@code BoundingVolume3D} instance, or {@code Double.NaN} if no intersection exists.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} may be thrown. But no guarantees can be made.
	 * 
	 * @param ray the {@link Ray3D} to perform an intersection test against this {@code BoundingVolume3D} instance
	 * @return {@code t}, the parametric distance from {@code ray} to this {@code BoundingVolume3D} instance, or {@code Double.NaN} if no intersection exists
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	double intersectionT(final Ray3D ray);
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns a {@link Point3D} with the X-, Y- and Z-coordinates in the middle of this {@code BoundingVolume3D} instance.
	 * 
	 * @return a {@code Point3D} with the X-, Y- and Z-coordinates in the middle of this {@code BoundingVolume3D} instance
	 */
	default Point3D getMidpoint() {
		return Point3D.midpoint(getMaximum(), getMinimum());
	}
	
	/**
	 * Performs an intersection test between {@code boundingVolume} and this {@code BoundingVolume3D} instance.
	 * <p>
	 * Returns {@code true} if, and only if, {@code boundingVolume} intersects this {@code BoundingVolume3D} instance, {@code false} otherwise.
	 * <p>
	 * If {@code boundingVolume} is {@code null}, a {@code NullPointerException} may be thrown. But no guarantees can be made.
	 * 
	 * @param boundingVolume the {@code BoundingVolume3D} to perform an intersection test against this {@code BoundingVolume3D} instance
	 * @return {@code true} if, and only if, {@code boundingVolume} intersects this {@code BoundingVolume3D} instance, {@code false} otherwise
	 * @throws NullPointerException thrown if, and only if, {@code boundingVolume} is {@code null}
	 */
	default boolean intersects(final BoundingVolume3D boundingVolume) {
		return contains(boundingVolume.getClosestPointTo(getMidpoint()));
	}
	
	/**
	 * Performs an intersection test between {@code ray} and this {@code BoundingVolume3D} instance.
	 * <p>
	 * Returns {@code true} if, and only if, {@code ray} intersects this {@code BoundingVolume3D} instance, {@code false} otherwise.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} may be thrown. But no guarantees can be made.
	 * 
	 * @param ray the {@link Ray3D} to perform an intersection test against this {@code BoundingVolume3D} instance
	 * @return {@code true} if, and only if, {@code ray} intersects this {@code BoundingVolume3D} instance, {@code false} otherwise
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	default boolean intersects(final Ray3D ray) {
		return !Double.isNaN(intersectionT(ray));
	}
}