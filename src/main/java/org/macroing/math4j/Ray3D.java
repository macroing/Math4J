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

import java.util.Objects;

/**
 * A {@code Ray3D} denotes a 3-dimensional ray with a point called its origin and a direction vector, both of which are using {@code double} as their type.
 * <p>
 * The 3-dimensional point used by this class is represented by {@link Point3D}. The 3-dimensional vector used by this class is represented by {@link Vector3D}.
 * <p>
 * This class is immutable and therefore thread-safe.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class Ray3D {
	/**
	 * The {@link Point3D} instance used by this {@code Ray3D} instance to represent its origin.
	 */
	public final Point3D origin;
	
	/**
	 * The {@link Vector3D} instance used by this {@code Ray3D} instance to represent its direction.
	 */
	public final Vector3D direction;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructs a new {@code Ray3D} instance given {@code origin} and {@code direction}.
	 * <p>
	 * If either {@code origin} or {@code direction} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param origin a {@link Point3D} instance to represent the origin
	 * @param direction a {@link Vector3D} instance to represent the direction
	 */
	public Ray3D(final Point3D origin, final Vector3D direction) {
		this.origin = Objects.requireNonNull(origin, "origin == null");
		this.direction = Objects.requireNonNull(direction, "direction == null");
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns the {@link Point3D} instance used by this {@code Ray3D} instance to represent its origin.
	 * 
	 * @return the {@code Point3D} instance used by this {@code Ray3D} instance to represent its origin
	 */
	public Point3D getOrigin() {
		return this.origin;
	}
	
	/**
	 * Performs a transformation.
	 * <p>
	 * Returns a new {@code Ray3D} instance with the result of the transformation.
	 * <p>
	 * If {@code m} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param m the {@link Matrix44D} to perform the transformation with
	 * @return a new {@code Ray3D} instance with the result of the transformation
	 * @throws NullPointerException thrown if, and only if, {@code m} is {@code null}
	 */
	public Ray3D transform(final Matrix44D m) {
		return new Ray3D(this.origin.transform(m), this.direction.transform(m));
	}
	
	/**
	 * Returns a {@code String} representation of this {@code Ray3D} instance.
	 * 
	 * @return a {@code String} representation of this {@code Ray3D} instance
	 */
	@Override
	public String toString() {
		return String.format("new Ray3D(%s, %s)", this.origin, this.direction);
	}
	
	/**
	 * Returns the {@link Vector3D} instance used by this {@code Ray3D} instance to represent its direction.
	 * 
	 * @return the {@code Vector3D} instance used by this {@code Ray3D} instance to represent its direction
	 */
	public Vector3D getDirection() {
		return this.direction;
	}
	
	/**
	 * Compares {@code object} to this {@code Ray3D} instance for equality.
	 * <p>
	 * Returns {@code true} if, and only if, {@code object} is an instance of {@code Ray3D}, and their respective values are equal, {@code false} otherwise.
	 * 
	 * @param object the {@code Object} to compare to this {@code Ray3D} instance for equality
	 * @return {@code true} if, and only if, {@code object} is an instance of {@code Ray3D}, and their respective values are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object object) {
		if(object == this) {
			return true;
		} else if(!(object instanceof Ray3D)) {
			return false;
		} else if(!Objects.equals(this.origin, Ray3D.class.cast(object).origin)) {
			return false;
		} else if(!Objects.equals(this.direction, Ray3D.class.cast(object).direction)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returns a hash code for this {@code Ray3D} instance.
	 * 
	 * @return a hash code for this {@code Ray3D} instance
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.origin, this.direction);
	}
}