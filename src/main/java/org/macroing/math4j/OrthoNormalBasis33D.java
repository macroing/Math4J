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

import static org.macroing.math4j.MathD.abs;

import java.util.Objects;

/**
 * An {@code OrthoNormalBasis33D} denotes an orthonormal basis constructed by three 3-dimensional vectors of type {@code double}.
 * <p>
 * The 3-dimensional vectors used by this class are represented by {@link Vector3D}.
 * <p>
 * This class is immutable and therefore thread-safe.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class OrthoNormalBasis33D {
	/**
	 * A {@link Vector3D} pointing in the U-direction.
	 */
	public final Vector3D u;
	
	/**
	 * A {@link Vector3D} pointing in the V-direction.
	 */
	public final Vector3D v;
	
	/**
	 * A {@link Vector3D} pointing in the W-direction.
	 */
	public final Vector3D w;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructs a new {@code OrthoNormalBasis33D} instance.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new OrthoNormalBasis33D(Vector3D.z(), Vector3D.y(), Vector3D.x());
	 * }
	 * </pre>
	 */
	public OrthoNormalBasis33D() {
		this(Vector3D.z(), Vector3D.y(), Vector3D.x());
	}
	
	/**
	 * Constructs a new {@code OrthoNormalBasis33D} instance given {@code eye}, {@code lookAt} and {@code Vector3D.y()}.
	 * <p>
	 * The W-direction is computed by {@code Vector3D.direction(lookAt, eye)}, which is the opposite direction.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new OrthoNormalBasis33D(eye, lookAt, Vector3D.y());
	 * }
	 * </pre>
	 * If either {@code eye} or {@code lookAt} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param eye a {@link Point3D} instance denoting the eye to look from
	 * @param lookAt a {@code Point3D} denoting the target to look at
	 * @throws NullPointerException thrown if, and only if, either {@code eye} or {@code lookAt} are {@code null}
	 */
	public OrthoNormalBasis33D(final Point3D eye, final Point3D lookAt) {
		this(eye, lookAt, Vector3D.y());
	}
	
	/**
	 * Constructs a new {@code OrthoNormalBasis33D} instance given {@code eye}, {@code lookAt} and {@code v}.
	 * <p>
	 * The W-direction is computed by {@code Vector3D.direction(lookAt, eye)}, which is the opposite direction.
	 * <p>
	 * If either {@code eye}, {@code lookAt} or {@code v} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param eye a {@link Point3D} instance denoting the eye to look from
	 * @param lookAt a {@code Point3D} denoting the target to look at
	 * @param v a {@link Vector3D} denoting the V-direction
	 * @throws NullPointerException thrown if, and only if, either {@code eye}, {@code lookAt} or {@code v} are {@code null}
	 */
	public OrthoNormalBasis33D(final Point3D eye, final Point3D lookAt, final Vector3D v) {
		this(Vector3D.direction(lookAt, eye), v);
	}
	
	/**
	 * Constructs a new {@code OrthoNormalBasis33D} instance given {@code w}.
	 * <p>
	 * If {@code w} is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * <strong>Note:</strong> This constructor does normalize {@code w}.
	 * 
	 * @param w a {@link Vector3D} pointing in the W-direction
	 * @throws NullPointerException thrown if, and only if, {@code w} is {@code null}
	 */
	public OrthoNormalBasis33D(final Vector3D w) {
		this.w = w.normalize();
		this.v = doCalculateV(this.w);
		this.u = this.v.crossProduct(this.w);
	}
	
	/**
	 * Constructs a new {@code OrthoNormalBasis33D} instance given {@code w} and {@code v}.
	 * <p>
	 * If either {@code w} or {@code v} are {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * <strong>Note:</strong> This constructor does normalize both {@code w} and {@code v}.
	 * 
	 * @param w a {@link Vector3D} pointing in the W-direction
	 * @param v a {@code Vector3D} pointing in the V-direction to some degree
	 * @throws NullPointerException thrown if, and only if, either {@code w} or {@code v} are {@code null}
	 */
	public OrthoNormalBasis33D(final Vector3D w, final Vector3D v) {
		this.w = w.normalize();
		this.u = v.crossProduct(this.w).normalize();
		this.v = this.w.crossProduct(this.u);
	}
	
	/**
	 * Constructs a new {@code OrthoNormalBasis33D} instance given {@code w}, {@code v} and {@code u}.
	 * <p>
	 * If either {@code w}, {@code v} or {@code u} are {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * <strong>Note:</strong> This constructor does not normalize {@code w}, {@code v} or {@code u}.
	 * 
	 * @param w a {@link Vector3D} pointing in the W-direction
	 * @param v a {@code Vector3D} pointing in the V-direction
	 * @param u a {@code Vector3D} pointing in the U-direction
	 * @throws NullPointerException thrown if, and only if, either {@code w}, {@code v} or {@code u} are {@code null}
	 */
	public OrthoNormalBasis33D(final Vector3D w, final Vector3D v, final Vector3D u) {
		this.w = Objects.requireNonNull(w, "w == null");
		this.v = Objects.requireNonNull(v, "v == null");
		this.u = Objects.requireNonNull(u, "u == null");
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Flips the U-direction.
	 * <p>
	 * Returns a new {@code OrthoNormalBasis33D} with the U-direction flipped.
	 * 
	 * @return a new {@code OrthoNormalBasis33D} with the U-direction flipped
	 */
	public OrthoNormalBasis33D flipU() {
		return new OrthoNormalBasis33D(this.w, this.v, this.u.negate());
	}
	
	/**
	 * Flips the V-direction.
	 * <p>
	 * Returns a new {@code OrthoNormalBasis33D} with the V-direction flipped.
	 * 
	 * @return a new {@code OrthoNormalBasis33D} with the V-direction flipped
	 */
	public OrthoNormalBasis33D flipV() {
		return new OrthoNormalBasis33D(this.w, this.v.negate(), this.u);
	}
	
	/**
	 * Flips the W-direction.
	 * <p>
	 * Returns a new {@code OrthoNormalBasis33D} with the W-direction flipped.
	 * 
	 * @return a new {@code OrthoNormalBasis33D} with the W-direction flipped
	 */
	public OrthoNormalBasis33D flipW() {
		return new OrthoNormalBasis33D(this.w.negate(), this.v, this.u);
	}
	
	/**
	 * Swaps the U- and V-directions.
	 * <p>
	 * Returns a new {@code OrthoNormalBasis33D} with the U- and V-directions swapped.
	 * <p>
	 * This method does the same thing as {@link #swapVU()}.
	 * 
	 * @return a new {@code OrthoNormalBasis33D} with the U- and V-directions swapped
	 */
	public OrthoNormalBasis33D swapUV() {
		return new OrthoNormalBasis33D(this.w, this.u, this.v);
	}
	
	/**
	 * Swaps the U- and W-directions.
	 * <p>
	 * Returns a new {@code OrthoNormalBasis33D} with the U- and W-directions swapped.
	 * <p>
	 * This method does the same thing as {@link #swapWU()}.
	 * 
	 * @return a new {@code OrthoNormalBasis33D} with the U- and W-directions swapped
	 */
	public OrthoNormalBasis33D swapUW() {
		return new OrthoNormalBasis33D(this.u, this.v, this.w);
	}
	
	/**
	 * Swaps the V- and U-directions.
	 * <p>
	 * Returns a new {@code OrthoNormalBasis33D} with the V- and U-directions swapped.
	 * <p>
	 * This method does the same thing as {@link #swapUV()}.
	 * 
	 * @return a new {@code OrthoNormalBasis33D} with the V- and U-directions swapped
	 */
	public OrthoNormalBasis33D swapVU() {
		return new OrthoNormalBasis33D(this.w, this.u, this.v);
	}
	
	/**
	 * Swaps the V- and W-directions.
	 * <p>
	 * Returns a new {@code OrthoNormalBasis33D} with the V- and W-directions swapped.
	 * <p>
	 * This method does the same thing as {@link #swapWV()}.
	 * 
	 * @return a new {@code OrthoNormalBasis33D} with the V- and W-directions swapped
	 */
	public OrthoNormalBasis33D swapVW() {
		return new OrthoNormalBasis33D(this.v, this.w, this.u);
	}
	
	/**
	 * Swaps the W- and U-directions.
	 * <p>
	 * Returns a new {@code OrthoNormalBasis33D} with the W- and U-directions swapped.
	 * <p>
	 * This method does the same thing as {@link #swapUW()}.
	 * 
	 * @return a new {@code OrthoNormalBasis33D} with the W- and U-directions swapped
	 */
	public OrthoNormalBasis33D swapWU() {
		return new OrthoNormalBasis33D(this.u, this.v, this.w);
	}
	
	/**
	 * Swaps the W- and V-directions.
	 * <p>
	 * Returns a new {@code OrthoNormalBasis33D} with the W- and V-directions swapped.
	 * <p>
	 * This method does the same thing as {@link #swapVW()}.
	 * 
	 * @return a new {@code OrthoNormalBasis33D} with the W- and V-directions swapped
	 */
	public OrthoNormalBasis33D swapWV() {
		return new OrthoNormalBasis33D(this.v, this.w, this.u);
	}
	
	/**
	 * Performs a transformation.
	 * <p>
	 * Returns a new {@code OrthoNormalBasis33D} instance with the result of the transformation.
	 * <p>
	 * If {@code m} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param m the {@link Matrix44D} instance to perform the transformation with
	 * @return a new {@code OrthoNormalBasis33D} instance with the result of the transformation
	 * @throws NullPointerException thrown if, and only if, {@code m} is {@code null}
	 */
	public OrthoNormalBasis33D transform(final Matrix44D m) {
		return new OrthoNormalBasis33D(this.w.transform(m), this.v.transform(m), this.u.transform(m));
	}
	
	/**
	 * Performs a transformation in transpose order.
	 * <p>
	 * Returns a new {@code OrthoNormalBasis33D} instance with the result of the transformation.
	 * <p>
	 * If {@code m} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param m the {@link Matrix44D} instance to perform the transformation with
	 * @return a new {@code OrthoNormalBasis33D} instance with the result of the transformation
	 * @throws NullPointerException thrown if, and only if, {@code m} is {@code null}
	 */
	public OrthoNormalBasis33D transformTranspose(final Matrix44D m) {
		return new OrthoNormalBasis33D(this.w.transformTranspose(m), this.v.transformTranspose(m), this.u.transformTranspose(m));
	}
	
	/**
	 * Returns a {@code String} representation of this {@code OrthoNormalBasis33D} instance.
	 * 
	 * @return a {@code String} representation of this {@code OrthoNormalBasis33D} instance
	 */
	@Override
	public String toString() {
		return String.format("new OrthoNormalBasis33D(%s, %s, %s)", this.w, this.v, this.u);
	}
	
	/**
	 * Returns the {@link Vector3D} pointing in the U-direction.
	 * 
	 * @return the {@code Vector3D} pointing in the U-direction
	 */
	public Vector3D getU() {
		return this.u;
	}
	
	/**
	 * Returns the {@link Vector3D} pointing in the V-direction.
	 * 
	 * @return the {@code Vector3D} pointing in the V-direction
	 */
	public Vector3D getV() {
		return this.v;
	}
	
	/**
	 * Returns the {@link Vector3D} pointing in the W-direction.
	 * 
	 * @return the {@code Vector3D} pointing in the W-direction
	 */
	public Vector3D getW() {
		return this.w;
	}
	
	/**
	 * Compares {@code object} to this {@code OrthoNormalBasis33D} instance for equality.
	 * <p>
	 * Returns {@code true} if, and only if, {@code object} is an instance of {@code OrthoNormalBasis33D}, and their respective values are equal, {@code false} otherwise.
	 * 
	 * @param object the {@code Object} to compare to this {@code OrthoNormalBasis33D} instance for equality
	 * @return {@code true} if, and only if, {@code object} is an instance of {@code OrthoNormalBasis33D}, and their respective values are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object object) {
		if(object == this) {
			return true;
		} else if(!(object instanceof OrthoNormalBasis33D)) {
			return false;
		} else if(!Objects.equals(this.u, OrthoNormalBasis33D.class.cast(object).u)) {
			return false;
		} else if(!Objects.equals(this.v, OrthoNormalBasis33D.class.cast(object).v)) {
			return false;
		} else if(!Objects.equals(this.w, OrthoNormalBasis33D.class.cast(object).w)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returns a hash code for this {@code OrthoNormalBasis33D} instance.
	 * 
	 * @return a hash code for this {@code OrthoNormalBasis33D} instance
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.u, this.v, this.w);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private static Vector3D doCalculateV(final Vector3D w) {
		final double absWX = abs(w.x);
		final double absWY = abs(w.y);
		final double absWZ = abs(w.z);
		
		final double vX = absWX < absWY && absWX < absWZ ? 0.0D : absWY < absWZ ? +w.z : +w.y;
		final double vY = absWX < absWY && absWX < absWZ ? +w.z : absWY < absWZ ? 0.0D : -w.x;
		final double vZ = absWX < absWY && absWX < absWZ ? -w.y : absWY < absWZ ? -w.x : 0.0D;
		
		return new Vector3D(vX, vY, vZ).normalize();
	}
}