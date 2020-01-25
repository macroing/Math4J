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
import java.util.Optional;

/**
 * A {@code Triangle3D} denotes a triangle that uses the data type {@code double}.
 * <p>
 * This class is immutable and therefore thread-safe.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class Triangle3D implements Shape3D {
	private static final double EPSILON = 0.0001D;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final Vector3D surfaceNormal;
	private final Vertex3D a;
	private final Vertex3D b;
	private final Vertex3D c;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructs a new {@code Triangle3D} instance given its vertices {@code a}, {@code b} and {@code c}.
	 * <p>
	 * If either {@code a}, {@code b} or {@code c} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param a one of the vertices of this {@code Triangle3D} instance
	 * @param b one of the vertices of this {@code Triangle3D} instance
	 * @param c one of the vertices of this {@code Triangle3D} instance
	 * @throws NullPointerException thrown if, and only if, either {@code a}, {@code b} or {@code c} are {@code null}
	 */
	public Triangle3D(final Vertex3D a, final Vertex3D b, final Vertex3D c) {
		this.a = Objects.requireNonNull(a, "a == null");
		this.b = Objects.requireNonNull(b, "b == null");
		this.c = Objects.requireNonNull(c, "c == null");
		this.surfaceNormal = Vector3D.normalNormalized(a.getPosition(), b.getPosition(), c.getPosition());
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Samples this {@code Triangle3D} instance.
	 * <p>
	 * Returns an optional {@link SurfaceSample3D} with the surface sample.
	 * <p>
	 * If either {@code referencePoint} or {@code referenceSurfaceNormal} are {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * Note: This method has not been implemented yet.
	 * 
	 * @param referencePoint the reference point on this {@code Triangle3D} instance
	 * @param referenceSurfaceNormal the reference surface normal on this {@code Triangle3D} instance
	 * @param u a random {@code double} with a uniform distribution between {@code 0.0D} and {@code 1.0D}
	 * @param v a random {@code double} with a uniform distribution between {@code 0.0D} and {@code 1.0D}
	 * @return an optional {@code SurfaceSample3D} with the surface sample
	 * @throws NullPointerException thrown if, and only if, either {@code referencePoint} or {@code referenceSurfaceNormal} are {@code null}
	 */
	@Override
	public Optional<SurfaceSample3D> sample(final Point3D referencePoint, final Vector3D referenceSurfaceNormal, final double u, final double v) {
		return Optional.empty();//TODO: Implement!
	}
	
	/**
	 * Returns an {@link OrthoNormalBasis33D} instance denoting the OrthoNormal Basis (ONB) of the surface of this {@code Triangle3D} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3D} instance that was used in a call to {@link #intersection(Ray3D)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Triangle3D} instance that was returned by {@code intersection(Ray3D)}
	 * @param isCorrectlyOriented {@code true} if, and only if, the {@code OrthoNormalBasis33D} must lie in the same hemisphere as {@code ray}, {@code false} otherwise
	 * @return an {@code OrthoNormalBasis33D} instance denoting the OrthoNormal Basis (ONB) of the surface of this {@code Triangle3D} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public OrthoNormalBasis33D calculateOrthoNormalBasis(final Ray3D ray, final double t, final boolean isCorrectlyOriented) {
		return new OrthoNormalBasis33D(calculateSurfaceNormal(ray, t, isCorrectlyOriented));
	}
	
	/**
	 * Returns a {@link Point2D} instance denoting the texture coordinates (or UV-coordinates) of the surface of this {@code Triangle3D} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3D} instance that was used in a call to {@link #intersection(Ray3D)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Triangle3D} instance that was returned by {@code intersection(Ray3D)}
	 * @return a {@code Point2D} instance denoting the texture coordinates (or UV-coordinates) of the surface of this {@code Triangle3D} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Point2D calculateTextureCoordinates(final Ray3D ray, final double t) {
		final Point2D textureCoordinatesA = this.a.getTextureCoordinates();
		final Point2D textureCoordinatesB = this.b.getTextureCoordinates();
		final Point2D textureCoordinatesC = this.c.getTextureCoordinates();
		
		final Point3D barycentricCoordinates = calculateBarycentricCoordinates(ray, t);
		
		final double barycentricU = barycentricCoordinates.x;
		final double barycentricV = barycentricCoordinates.y;
		final double barycentricW = barycentricCoordinates.z;
		
		final double u = barycentricU * textureCoordinatesA.x + barycentricV * textureCoordinatesB.x + barycentricW * textureCoordinatesC.x;
		final double v = barycentricU * textureCoordinatesA.y + barycentricV * textureCoordinatesB.y + barycentricW * textureCoordinatesC.y;
		
		return new Point2D(u, v);
	}
	
	/**
	 * Returns a {@link Point3D} instance denoting the Barycentric coordinates of the surface of this {@code Triangle3D} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3D} instance that was used in a call to {@link #intersection(Ray3D)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Triangle3D} instance that was returned by {@code intersection(Ray3D)}
	 * @return a {@code Point3D} instance denoting the Barycentric coordinates of the surface of this {@code Triangle3D} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	public Point3D calculateBarycentricCoordinates(final Ray3D ray, final double t) {
		final Point3D origin = ray.origin;
		
		final Point3D a = this.a.getPosition();
		final Point3D b = this.b.getPosition();
		final Point3D c = this.c.getPosition();
		
		final Vector3D direction = ray.direction;
		
		final Vector3D edgeAB = Vector3D.direction(a, b);
		final Vector3D edgeAC = Vector3D.direction(a, c);
		
		final Vector3D v0 = direction.crossProduct(edgeAC);
		final Vector3D v1 = Vector3D.direction(a, origin);
		final Vector3D v2 = v1.crossProduct(edgeAB);
		
		final double determinant = edgeAB.dotProduct(v0);
		final double determinantReciprocal = 1.0D / determinant;
		
		final double u = v1.dotProduct(v0) * determinantReciprocal;
		final double v = direction.dotProduct(v2) * determinantReciprocal;
		final double w = 1.0D - u - v;
		
		return new Point3D(u, v, w);
	}
	
	/**
	 * Returns a {@link Point3D} instance denoting the surface intersection point of the surface of this {@code Triangle3D} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3D} instance that was used in a call to {@link #intersection(Ray3D)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Triangle3D} instance that was returned by {@code intersection(Ray3D)}
	 * @return a {@code Point3D} instance denoting the surface intersection point of the surface of this {@code Triangle3D} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Point3D calculateSurfaceIntersectionPoint(final Ray3D ray, final double t) {
		return ray.origin.add(ray.direction, t);
	}
	
	/**
	 * Returns a {@code String} representation of this {@code Triangle3D} instance.
	 * 
	 * @return a {@code String} representation of this {@code Triangle3D} instance
	 */
	@Override
	public String toString() {
		return String.format("new Triangle3D(%s, %s, %s)", this.a, this.b, this.c);
	}
	
	/**
	 * Returns a {@link Vector3D} instance denoting the surface normal of the surface of this {@code Triangle3D} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3D} instance that was used in a call to {@link #intersection(Ray3D)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Triangle3D} instance that was returned by {@code intersection(Ray3D)}
	 * @param isCorrectlyOriented {@code true} if, and only if, the {@code Vector3D} must lie in the same hemisphere as {@code ray}, {@code false} otherwise
	 * @return a {@code Vector3D} instance denoting the surface normal of the surface of this {@code Triangle3D} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Vector3D calculateSurfaceNormal(final Ray3D ray, final double t, final boolean isCorrectlyOriented) {
		final Vector3D normalA = this.a.getNormal();
		final Vector3D normalB = this.b.getNormal();
		final Vector3D normalC = this.c.getNormal();
		
		final Point3D barycentricCoordinates = calculateBarycentricCoordinates(ray, t);
		
		final double barycentricU = barycentricCoordinates.x;
		final double barycentricV = barycentricCoordinates.y;
		final double barycentricW = barycentricCoordinates.z;
		
		final Vector3D surfaceNormal = Vector3D.normalNormalized(normalA, normalB, normalC, barycentricU, barycentricV, barycentricW);
		final Vector3D surfaceNormalCorrectlyOriented = isCorrectlyOriented && surfaceNormal.dotProduct(ray.direction) >= 0.0D ? surfaceNormal.negate() : surfaceNormal;
		
		return surfaceNormalCorrectlyOriented;
	}
	
	/**
	 * Returns the {@link Vertex3D} instance that denotes the vertex {@code A}.
	 * 
	 * @return the {@code Vertex3D} instance that denotes the vertex {@code A}
	 */
	public Vertex3D getA() {
		return this.a;
	}
	
	/**
	 * Returns the {@link Vertex3D} instance that denotes the vertex {@code B}.
	 * 
	 * @return the {@code Vertex3D} instance that denotes the vertex {@code B}
	 */
	public Vertex3D getB() {
		return this.b;
	}
	
	/**
	 * Returns the {@link Vertex3D} instance that denotes the vertex {@code C}.
	 * 
	 * @return the {@code Vertex3D} instance that denotes the vertex {@code C}
	 */
	public Vertex3D getC() {
		return this.c;
	}
	
	/**
	 * Compares {@code object} to this {@code Triangle3D} instance for equality.
	 * <p>
	 * Returns {@code true} if, and only if, {@code object} is an instance of {@code Triangle3D}, and their respective values are equal, {@code false} otherwise.
	 * 
	 * @param object the {@code Object} to compare to this {@code Triangle3D} instance for equality
	 * @return {@code true} if, and only if, {@code object} is an instance of {@code Triangle3D}, and their respective values are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object object) {
		if(object == this) {
			return true;
		} else if(!(object instanceof Triangle3D)) {
			return false;
		} else if(!Objects.equals(this.surfaceNormal, Triangle3D.class.cast(object).surfaceNormal)) {
			return false;
		} else if(!Objects.equals(this.a, Triangle3D.class.cast(object).a)) {
			return false;
		} else if(!Objects.equals(this.b, Triangle3D.class.cast(object).b)) {
			return false;
		} else if(!Objects.equals(this.c, Triangle3D.class.cast(object).c)) {
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
	 * @param referencePoint the reference point on this {@code Triangle3D} instance
	 * @param referenceSurfaceNormal the reference surface normal on this {@code Triangle3D} instance
	 * @param point the point on this {@code Triangle3D} instance
	 * @param surfaceNormal the surface normal on this {@code Triangle3D} instance
	 * @return the probability density function (PDF) value for solid angle
	 * @throws NullPointerException thrown if, and only if, either {@code referencePoint}, {@code referenceSurfaceNormal}, {@code point} or {@code surfaceNormal} are {@code null}
	 */
	@Override
	public double calculateProbabilityDensityFunctionValueForSolidAngle(final Point3D referencePoint, final Vector3D referenceSurfaceNormal, final Point3D point, final Vector3D surfaceNormal) {
		return 0.0D;//TODO: Implement!
	}
	
	/**
	 * Returns the surface area of this {@code Triangle3D} instance.
	 * 
	 * @return the surface area of this {@code Triangle3D} instance
	 */
	@Override
	public double getSurfaceArea() {
		final Point3D a = this.a.getPosition();
		final Point3D b = this.b.getPosition();
		final Point3D c = this.c.getPosition();
		
		final Vector3D edgeAB = Vector3D.direction(a, b);
		final Vector3D edgeAC = Vector3D.direction(a, c);
		final Vector3D edgeABCrossEdgeAC = edgeAB.crossProduct(edgeAC);
		
		final double surfaceArea = edgeABCrossEdgeAC.length() * 0.5D;
		
		return surfaceArea;
	}
	
	/**
	 * Returns the surface area probability density function (PDF) value of this {@code Triangle3D} instance.
	 * <p>
	 * Note: This method has not been implemented yet.
	 * 
	 * @return the surface area probability density function (PDF) value of this {@code Triangle3D} instance
	 */
	@Override
	public double getSurfaceAreaProbabilityDensityFunctionValue() {
		return 0.0D;//TODO: Implement!
	}
	
	/**
	 * Returns the volume of this {@code Triangle3D} instance.
	 * <p>
	 * This method returns {@code 0.0D}.
	 * 
	 * @return the volume of this {@code Triangle3D} instance
	 */
	@Override
	public double getVolume() {
		return 0.0D;
	}
	
	/**
	 * Performs an intersection test between {@code ray} and this {@code Triangle3D} instance.
	 * <p>
	 * Returns {@code t}, the parametric distance from {@code ray} to this {@code Triangle3D} instance, or {@code Double.NaN} if no intersection exists.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3D} to perform an intersection test against this {@code Triangle3D} instance
	 * @return {@code t}, the parametric distance from {@code ray} to this {@code Triangle3D} instance, or {@code Double.NaN} if no intersection exists
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public double intersection(final Ray3D ray) {
		final Point3D origin = ray.origin;
		
		final Point3D a = this.a.getPosition();
		final Point3D b = this.b.getPosition();
		final Point3D c = this.c.getPosition();
		
		final Vector3D direction = ray.direction;
		
		final Vector3D edgeAB = Vector3D.direction(a, b);
		final Vector3D edgeAC = Vector3D.direction(a, c);
		
		final Vector3D v0 = direction.crossProduct(edgeAC);
		final Vector3D v1 = Vector3D.direction(a, origin);
		final Vector3D v2 = v1.crossProduct(edgeAB);
		
		final double determinant = edgeAB.dotProduct(v0);
		final double determinantReciprocal = 1.0D / determinant;
		
		if(determinant > -EPSILON && determinant > EPSILON) {
			return Double.NaN;
		}
		
		final double u = v1.dotProduct(v0) * determinantReciprocal;
		final double v = direction.dotProduct(v2) * determinantReciprocal;
		
		if(u < 0.0D || u > 1.0D || v < 0.0D || u + v > 1.0D) {
			return Double.NaN;
		}
		
		final double t = edgeAC.dotProduct(v2) * determinantReciprocal;
		
		if(t < EPSILON) {
			return Double.NaN;
		}
		
		return t;
	}
	
	/**
	 * Returns a hash code for this {@code Triangle3D} instance.
	 * 
	 * @return a hash code for this {@code Triangle3D} instance
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.surfaceNormal, this.a, this.b, this.c);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * A {@code Vertex3D} denotes a vertex of a {@link Triangle3D} instance.
	 * <p>
	 * This class is immutable and therefore thread-safe.
	 * 
	 * @since 1.0.0
	 * @author J&#246;rgen Lundgren
	 */
	public static final class Vertex3D {
		private final Point2D textureCoordinates;
		private final Point3D position;
		private final Vector3D normal;
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/**
		 * Constructs a new {@code Vertex3D} instance given its texture coordinates, position and surface normal.
		 * <p>
		 * If either {@code textureCoordinates}, {@code position} or {@code normal} are {@code null}, a {@code NullPointerException} will be thrown.
		 * 
		 * @param textureCoordinates the texture coordinates of this {@code Vertex3D} instance
		 * @param position the position of this {@code Vertex3D} instance
		 * @param normal the normal of this {@code Vertex3D} instance
		 * @throws NullPointerException thrown if, and only if, either {@code textureCoordinates}, {@code position} or {@code normal} are {@code null}
		 */
		public Vertex3D(final Point2D textureCoordinates, final Point3D position, final Vector3D normal) {
			this.textureCoordinates = Objects.requireNonNull(textureCoordinates, "textureCoordinates == null");
			this.position = Objects.requireNonNull(position, "position == null");
			this.normal = Objects.requireNonNull(normal, "normal == null");
		}
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/**
		 * Returns the texture coordinates associated with this {@code Vertex3D} instance.
		 * 
		 * @return the texture coordinates associated with this {@code Vertex3D} instance
		 */
		public Point2D getTextureCoordinates() {
			return this.textureCoordinates;
		}
		
		/**
		 * Returns the position associated with this {@code Vertex3D} instance.
		 * 
		 * @return the position associated with this {@code Vertex3D} instance
		 */
		public Point3D getPosition() {
			return this.position;
		}
		
		/**
		 * Returns a {@code String} representation of this {@code Vertex3D} instance.
		 * 
		 * @return a {@code String} representation of this {@code Vertex3D} instance
		 */
		@Override
		public String toString() {
			return String.format("new Vertex3D(%s, %s, %s)", this.textureCoordinates, this.position, this.normal);
		}
		
		/**
		 * Returns the normal associated with this {@code Vertex3D} instance.
		 * 
		 * @return the normal associated with this {@code Vertex3D} instance
		 */
		public Vector3D getNormal() {
			return this.normal;
		}
		
		/**
		 * Compares {@code object} to this {@code Vertex3D} instance for equality.
		 * <p>
		 * Returns {@code true} if, and only if, {@code object} is an instance of {@code Vertex3D}, and their respective values are equal, {@code false} otherwise.
		 * 
		 * @param object the {@code Object} to compare to this {@code Vertex3D} instance for equality
		 * @return {@code true} if, and only if, {@code object} is an instance of {@code Vertex3D}, and their respective values are equal, {@code false} otherwise
		 */
		@Override
		public boolean equals(final Object object) {
			if(object == this) {
				return true;
			} else if(!(object instanceof Vertex3D)) {
				return false;
			} else if(!Objects.equals(this.textureCoordinates, Vertex3D.class.cast(object).textureCoordinates)) {
				return false;
			} else if(!Objects.equals(this.position, Vertex3D.class.cast(object).position)) {
				return false;
			} else if(!Objects.equals(this.normal, Vertex3D.class.cast(object).normal)) {
				return false;
			} else {
				return true;
			}
		}
		
		/**
		 * Returns a hash code for this {@code Vertex3D} instance.
		 * 
		 * @return a hash code for this {@code Vertex3D} instance
		 */
		@Override
		public int hashCode() {
			return Objects.hash(this.textureCoordinates, this.position, this.normal);
		}
	}
}