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
 * A {@code Triangle3F} denotes a triangle that uses the data type {@code float}.
 * <p>
 * This class is immutable and therefore thread-safe.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class Triangle3F implements Shape3F {
	private static final float EPSILON = 0.0001F;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final Vector3F surfaceNormal;
	private final Vertex3F a;
	private final Vertex3F b;
	private final Vertex3F c;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructs a new {@code Triangle3F} instance given its vertices {@code a}, {@code b} and {@code c}.
	 * <p>
	 * If either {@code a}, {@code b} or {@code c} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param a one of the vertices of this {@code Triangle3F} instance
	 * @param b one of the vertices of this {@code Triangle3F} instance
	 * @param c one of the vertices of this {@code Triangle3F} instance
	 * @throws NullPointerException thrown if, and only if, either {@code a}, {@code b} or {@code c} are {@code null}
	 */
	public Triangle3F(final Vertex3F a, final Vertex3F b, final Vertex3F c) {
		this.a = Objects.requireNonNull(a, "a == null");
		this.b = Objects.requireNonNull(b, "b == null");
		this.c = Objects.requireNonNull(c, "c == null");
		this.surfaceNormal = Vector3F.normalNormalized(a.getPosition(), b.getPosition(), c.getPosition());
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Samples this {@code Triangle3F} instance.
	 * <p>
	 * Returns an optional {@link SurfaceSample3F} with the surface sample.
	 * <p>
	 * If either {@code referencePoint} or {@code referenceSurfaceNormal} are {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * Note: This method has not been implemented yet.
	 * 
	 * @param referencePoint the reference point on this {@code Triangle3F} instance
	 * @param referenceSurfaceNormal the reference surface normal on this {@code Triangle3F} instance
	 * @param u a random {@code float} with a uniform distribution between {@code 0.0F} and {@code 1.0F}
	 * @param v a random {@code float} with a uniform distribution between {@code 0.0F} and {@code 1.0F}
	 * @return an optional {@code SurfaceSample3F} with the surface sample
	 * @throws NullPointerException thrown if, and only if, either {@code referencePoint} or {@code referenceSurfaceNormal} are {@code null}
	 */
	@Override
	public Optional<SurfaceSample3F> sample(final Point3F referencePoint, final Vector3F referenceSurfaceNormal, final float u, final float v) {
		return Optional.empty();//TODO: Implement!
	}
	
	/**
	 * Returns an {@link OrthoNormalBasis33F} instance denoting the OrthoNormal Basis (ONB) of the surface of this {@code Triangle3F} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} instance that was used in a call to {@link #intersection(Ray3F)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Triangle3F} instance that was returned by {@code intersection(Ray3F)}
	 * @param isCorrectlyOriented {@code true} if, and only if, the {@code OrthoNormalBasis33F} must lie in the same hemisphere as {@code ray}, {@code false} otherwise
	 * @return an {@code OrthoNormalBasis33F} instance denoting the OrthoNormal Basis (ONB) of the surface of this {@code Triangle3F} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public OrthoNormalBasis33F calculateOrthoNormalBasis(final Ray3F ray, final float t, final boolean isCorrectlyOriented) {
		return new OrthoNormalBasis33F(calculateSurfaceNormal(ray, t, isCorrectlyOriented));
	}
	
	/**
	 * Returns a {@link Point2F} instance denoting the texture coordinates (or UV-coordinates) of the surface of this {@code Triangle3F} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} instance that was used in a call to {@link #intersection(Ray3F)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Triangle3F} instance that was returned by {@code intersection(Ray3F)}
	 * @return a {@code Point2F} instance denoting the texture coordinates (or UV-coordinates) of the surface of this {@code Triangle3F} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Point2F calculateTextureCoordinates(final Ray3F ray, final float t) {
		final Point2F textureCoordinatesA = this.a.getTextureCoordinates();
		final Point2F textureCoordinatesB = this.b.getTextureCoordinates();
		final Point2F textureCoordinatesC = this.c.getTextureCoordinates();
		
		final Point3F barycentricCoordinates = calculateBarycentricCoordinates(ray, t);
		
		final float barycentricU = barycentricCoordinates.x;
		final float barycentricV = barycentricCoordinates.y;
		final float barycentricW = barycentricCoordinates.z;
		
		final float u = barycentricU * textureCoordinatesA.x + barycentricV * textureCoordinatesB.x + barycentricW * textureCoordinatesC.x;
		final float v = barycentricU * textureCoordinatesA.y + barycentricV * textureCoordinatesB.y + barycentricW * textureCoordinatesC.y;
		
		return new Point2F(u, v);
	}
	
	/**
	 * Returns a {@link Point3F} instance denoting the Barycentric coordinates of the surface of this {@code Triangle3F} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} instance that was used in a call to {@link #intersection(Ray3F)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Triangle3F} instance that was returned by {@code intersection(Ray3F)}
	 * @return a {@code Point3F} instance denoting the Barycentric coordinates of the surface of this {@code Triangle3F} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	public Point3F calculateBarycentricCoordinates(final Ray3F ray, final float t) {
		return doCalculateBarycentricCoordinates0(ray);
	}
	
	/**
	 * Returns a {@link Point3F} instance denoting the surface intersection point of the surface of this {@code Triangle3F} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} instance that was used in a call to {@link #intersection(Ray3F)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Triangle3F} instance that was returned by {@code intersection(Ray3F)}
	 * @return a {@code Point3F} instance denoting the surface intersection point of the surface of this {@code Triangle3F} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Point3F calculateSurfaceIntersectionPoint(final Ray3F ray, final float t) {
		return ray.origin.add(ray.direction, t);
	}
	
	/**
	 * Returns a {@code String} representation of this {@code Triangle3F} instance.
	 * 
	 * @return a {@code String} representation of this {@code Triangle3F} instance
	 */
	@Override
	public String toString() {
		return String.format("new Triangle3F(%s, %s, %s)", this.a, this.b, this.c);
	}
	
	/**
	 * Returns a {@link Vector3F} instance denoting the surface normal of the surface of this {@code Triangle3F} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} instance that was used in a call to {@link #intersection(Ray3F)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Triangle3F} instance that was returned by {@code intersection(Ray3F)}
	 * @param isCorrectlyOriented {@code true} if, and only if, the {@code Vector3F} must lie in the same hemisphere as {@code ray}, {@code false} otherwise
	 * @return a {@code Vector3F} instance denoting the surface normal of the surface of this {@code Triangle3F} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Vector3F calculateSurfaceNormal(final Ray3F ray, final float t, final boolean isCorrectlyOriented) {
		final Vector3F normalA = this.a.getNormal();
		final Vector3F normalB = this.b.getNormal();
		final Vector3F normalC = this.c.getNormal();
		
		final Point3F barycentricCoordinates = calculateBarycentricCoordinates(ray, t);
		
		final float barycentricU = barycentricCoordinates.x;
		final float barycentricV = barycentricCoordinates.y;
		final float barycentricW = barycentricCoordinates.z;
		
		final Vector3F surfaceNormal = Vector3F.normalNormalized(normalA, normalB, normalC, barycentricU, barycentricV, barycentricW);
		final Vector3F surfaceNormalCorrectlyOriented = isCorrectlyOriented && surfaceNormal.dotProduct(ray.direction) >= 0.0F ? surfaceNormal.negate() : surfaceNormal;
		
		return surfaceNormalCorrectlyOriented;
	}
	
	/**
	 * Returns the {@link Vertex3F} instance that denotes the vertex {@code A}.
	 * 
	 * @return the {@code Vertex3F} instance that denotes the vertex {@code A}
	 */
	public Vertex3F getA() {
		return this.a;
	}
	
	/**
	 * Returns the {@link Vertex3F} instance that denotes the vertex {@code B}.
	 * 
	 * @return the {@code Vertex3F} instance that denotes the vertex {@code B}
	 */
	public Vertex3F getB() {
		return this.b;
	}
	
	/**
	 * Returns the {@link Vertex3F} instance that denotes the vertex {@code C}.
	 * 
	 * @return the {@code Vertex3F} instance that denotes the vertex {@code C}
	 */
	public Vertex3F getC() {
		return this.c;
	}
	
	/**
	 * Compares {@code object} to this {@code Triangle3F} instance for equality.
	 * <p>
	 * Returns {@code true} if, and only if, {@code object} is an instance of {@code Triangle3F}, and their respective values are equal, {@code false} otherwise.
	 * 
	 * @param object the {@code Object} to compare to this {@code Triangle3F} instance for equality
	 * @return {@code true} if, and only if, {@code object} is an instance of {@code Triangle3F}, and their respective values are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object object) {
		if(object == this) {
			return true;
		} else if(!(object instanceof Triangle3F)) {
			return false;
		} else if(!Objects.equals(this.surfaceNormal, Triangle3F.class.cast(object).surfaceNormal)) {
			return false;
		} else if(!Objects.equals(this.a, Triangle3F.class.cast(object).a)) {
			return false;
		} else if(!Objects.equals(this.b, Triangle3F.class.cast(object).b)) {
			return false;
		} else if(!Objects.equals(this.c, Triangle3F.class.cast(object).c)) {
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
	 * @param referencePoint the reference point on this {@code Triangle3F} instance
	 * @param referenceSurfaceNormal the reference surface normal on this {@code Triangle3F} instance
	 * @param point the point on this {@code Triangle3F} instance
	 * @param surfaceNormal the surface normal on this {@code Triangle3F} instance
	 * @return the probability density function (PDF) value for solid angle
	 * @throws NullPointerException thrown if, and only if, either {@code referencePoint}, {@code referenceSurfaceNormal}, {@code point} or {@code surfaceNormal} are {@code null}
	 */
	@Override
	public float calculateProbabilityDensityFunctionValueForSolidAngle(final Point3F referencePoint, final Vector3F referenceSurfaceNormal, final Point3F point, final Vector3F surfaceNormal) {
		return 0.0F;//TODO: Implement!
	}
	
	/**
	 * Returns the surface area of this {@code Triangle3F} instance.
	 * 
	 * @return the surface area of this {@code Triangle3F} instance
	 */
	@Override
	public float getSurfaceArea() {
		final Point3F a = this.a.getPosition();
		final Point3F b = this.b.getPosition();
		final Point3F c = this.c.getPosition();
		
		final Vector3F edgeAB = Vector3F.direction(a, b);
		final Vector3F edgeAC = Vector3F.direction(a, c);
		final Vector3F edgeABCrossEdgeAC = edgeAB.crossProduct(edgeAC);
		
		final float surfaceArea = edgeABCrossEdgeAC.length() * 0.5F;
		
		return surfaceArea;
	}
	
	/**
	 * Returns the surface area probability density function (PDF) value of this {@code Triangle3F} instance.
	 * <p>
	 * Note: This method has not been implemented yet.
	 * 
	 * @return the surface area probability density function (PDF) value of this {@code Triangle3F} instance
	 */
	@Override
	public float getSurfaceAreaProbabilityDensityFunctionValue() {
		return 0.0F;//TODO: Implement!
	}
	
	/**
	 * Returns the volume of this {@code Triangle3F} instance.
	 * <p>
	 * This method returns {@code 0.0F}.
	 * 
	 * @return the volume of this {@code Triangle3F} instance
	 */
	@Override
	public float getVolume() {
		return 0.0F;
	}
	
	/**
	 * Performs an intersection test between {@code ray} and this {@code Triangle3F} instance.
	 * <p>
	 * Returns {@code t}, the parametric distance from {@code ray} to this {@code Triangle3F} instance, or {@code Float.NaN} if no intersection exists.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} to perform an intersection test against this {@code Triangle3F} instance
	 * @return {@code t}, the parametric distance from {@code ray} to this {@code Triangle3F} instance, or {@code Float.NaN} if no intersection exists
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public float intersection(final Ray3F ray) {
		return doCalculateIntersection0(ray);
	}
	
	/**
	 * Returns a hash code for this {@code Triangle3F} instance.
	 * 
	 * @return a hash code for this {@code Triangle3F} instance
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.surfaceNormal, this.a, this.b, this.c);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * A {@code Vertex3F} denotes a vertex of a {@link Triangle3F} instance.
	 * <p>
	 * This class is immutable and therefore thread-safe.
	 * 
	 * @since 1.0.0
	 * @author J&#246;rgen Lundgren
	 */
	public static final class Vertex3F {
		private final Point2F textureCoordinates;
		private final Point3F position;
		private final Vector3F normal;
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/**
		 * Constructs a new {@code Vertex3F} instance given its texture coordinates, position and surface normal.
		 * <p>
		 * If either {@code textureCoordinates}, {@code position} or {@code normal} are {@code null}, a {@code NullPointerException} will be thrown.
		 * 
		 * @param textureCoordinates the texture coordinates of this {@code Vertex3F} instance
		 * @param position the position of this {@code Vertex3F} instance
		 * @param normal the normal of this {@code Vertex3F} instance
		 * @throws NullPointerException thrown if, and only if, either {@code textureCoordinates}, {@code position} or {@code normal} are {@code null}
		 */
		public Vertex3F(final Point2F textureCoordinates, final Point3F position, final Vector3F normal) {
			this.textureCoordinates = Objects.requireNonNull(textureCoordinates, "textureCoordinates == null");
			this.position = Objects.requireNonNull(position, "position == null");
			this.normal = Objects.requireNonNull(normal, "normal == null");
		}
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/**
		 * Returns the texture coordinates associated with this {@code Vertex3F} instance.
		 * 
		 * @return the texture coordinates associated with this {@code Vertex3F} instance
		 */
		public Point2F getTextureCoordinates() {
			return this.textureCoordinates;
		}
		
		/**
		 * Returns the position associated with this {@code Vertex3F} instance.
		 * 
		 * @return the position associated with this {@code Vertex3F} instance
		 */
		public Point3F getPosition() {
			return this.position;
		}
		
		/**
		 * Returns a {@code String} representation of this {@code Vertex3F} instance.
		 * 
		 * @return a {@code String} representation of this {@code Vertex3F} instance
		 */
		@Override
		public String toString() {
			return String.format("new Vertex3F(%s, %s, %s)", this.textureCoordinates, this.position, this.normal);
		}
		
		/**
		 * Returns the normal associated with this {@code Vertex3F} instance.
		 * 
		 * @return the normal associated with this {@code Vertex3F} instance
		 */
		public Vector3F getNormal() {
			return this.normal;
		}
		
		/**
		 * Compares {@code object} to this {@code Vertex3F} instance for equality.
		 * <p>
		 * Returns {@code true} if, and only if, {@code object} is an instance of {@code Vertex3F}, and their respective values are equal, {@code false} otherwise.
		 * 
		 * @param object the {@code Object} to compare to this {@code Vertex3F} instance for equality
		 * @return {@code true} if, and only if, {@code object} is an instance of {@code Vertex3F}, and their respective values are equal, {@code false} otherwise
		 */
		@Override
		public boolean equals(final Object object) {
			if(object == this) {
				return true;
			} else if(!(object instanceof Vertex3F)) {
				return false;
			} else if(!Objects.equals(this.textureCoordinates, Vertex3F.class.cast(object).textureCoordinates)) {
				return false;
			} else if(!Objects.equals(this.position, Vertex3F.class.cast(object).position)) {
				return false;
			} else if(!Objects.equals(this.normal, Vertex3F.class.cast(object).normal)) {
				return false;
			} else {
				return true;
			}
		}
		
		/**
		 * Returns a hash code for this {@code Vertex3F} instance.
		 * 
		 * @return a hash code for this {@code Vertex3F} instance
		 */
		@Override
		public int hashCode() {
			return Objects.hash(this.textureCoordinates, this.position, this.normal);
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private Point3F doCalculateBarycentricCoordinates0(final Ray3F ray) {
		final Point3F origin = ray.origin;
		
		final Point3F a = this.a.getPosition();
		final Point3F b = this.b.getPosition();
		final Point3F c = this.c.getPosition();
		
		final Vector3F direction = ray.direction;
		
		final Vector3F edgeAB = Vector3F.direction(a, b);
		final Vector3F edgeAC = Vector3F.direction(a, c);
		
		final Vector3F v0 = direction.crossProduct(edgeAC);
		final Vector3F v1 = Vector3F.direction(a, origin);
		final Vector3F v2 = v1.crossProduct(edgeAB);
		
		final float determinant = edgeAB.dotProduct(v0);
		final float determinantReciprocal = 1.0F / determinant;
		
		final float u = v1.dotProduct(v0) * determinantReciprocal;
		final float v = direction.dotProduct(v2) * determinantReciprocal;
		final float w = 1.0F - u - v;
		
		return new Point3F(u, v, w);
	}
	
	@SuppressWarnings("unused")
	private Point3F doCalculateBarycentricCoordinates1(final Ray3F ray, final float t) {
		final Point3F origin = ray.origin;
		
		final Point3F a = this.a.getPosition();
		final Point3F b = this.b.getPosition();
		final Point3F c = this.c.getPosition();
		
		final Vector3F direction = ray.direction;
		final Vector3F normal = Vector3F.normal(a, b, c);
		
		final float denominator = normal.lengthSquared();
		final float normalDotProductDirection = normal.dotProduct(direction);
		
		if(normalDotProductDirection > -EPSILON && normalDotProductDirection < EPSILON) {
			throw new IllegalArgumentException();
		}
		
//		final float normalDotProductA = normal.dotProduct(new Vector3F(a));
//		final float t = (normal.dotProduct(new Vector3F(origin)) + normalDotProductA) / normalDotProductDirection;
		
		if(t < 0.0F) {
			throw new IllegalArgumentException();
		}
		
		final Point3F surfaceIntersectionPoint = origin.add(direction, t);
		
		final Vector3F directionAB = Vector3F.direction(a, b);
		final Vector3F directionASurfaceIntersectionPoint = Vector3F.direction(a, surfaceIntersectionPoint);
		final Vector3F directionABCrossProductDirectionASurfaceIntersectionPoint = directionAB.crossProduct(directionASurfaceIntersectionPoint);
		
		if(normal.dotProduct(directionABCrossProductDirectionASurfaceIntersectionPoint) < 0.0F) {
			throw new IllegalArgumentException();
		}
		
		final Vector3F directionBC = Vector3F.direction(b, c);
		final Vector3F directionBSurfaceIntersectionPoint = Vector3F.direction(b, surfaceIntersectionPoint);
		final Vector3F directionBCCrossProductDirectionBSurfaceIntersectionPoint = directionBC.crossProduct(directionBSurfaceIntersectionPoint);
		
		final float u0 = normal.dotProduct(directionBCCrossProductDirectionBSurfaceIntersectionPoint);
		
		if(u0 < 0.0F) {
			throw new IllegalArgumentException();
		}
		
		final Vector3F directionCA = Vector3F.direction(c, a);
		final Vector3F directionCSurfaceIntersectionPoint = Vector3F.direction(c, surfaceIntersectionPoint);
		final Vector3F directionCACrossProductDirectionCSurfaceIntersectionPoint = directionCA.crossProduct(directionCSurfaceIntersectionPoint);
		
		final float v0 = normal.dotProduct(directionCACrossProductDirectionCSurfaceIntersectionPoint);
		
		if(v0 < 0.0F) {
			throw new IllegalArgumentException();
		}
		
		final float u1 = u0 / denominator;
		final float v1 = v0 / denominator;
		final float w1 = 1.0F - u1 - v1;
		
		return new Point3F(u1, v1, w1);
	}
	
	private float doCalculateIntersection0(final Ray3F ray) {
		final Point3F origin = ray.origin;
		
		final Point3F a = this.a.getPosition();
		final Point3F b = this.b.getPosition();
		final Point3F c = this.c.getPosition();
		
		final Vector3F direction = ray.direction;
		
		final Vector3F edgeAB = Vector3F.direction(a, b);
		final Vector3F edgeAC = Vector3F.direction(a, c);
		
		final Vector3F v0 = direction.crossProduct(edgeAC);
		final Vector3F v1 = Vector3F.direction(a, origin);
		final Vector3F v2 = v1.crossProduct(edgeAB);
		
		final float determinant = edgeAB.dotProduct(v0);
		final float determinantReciprocal = 1.0F / determinant;
		
		if(determinant > -EPSILON && determinant < EPSILON) {
			return Float.NaN;
		}
		
		final float u = v1.dotProduct(v0) * determinantReciprocal;
		final float v = direction.dotProduct(v2) * determinantReciprocal;
		
		if(u < 0.0F || u > 1.0F || v < 0.0F || u + v > 1.0F) {
			return Float.NaN;
		}
		
		final float t = edgeAC.dotProduct(v2) * determinantReciprocal;
		
		if(t < EPSILON) {
			return Float.NaN;
		}
		
		return t;
	}
	
	@SuppressWarnings("unused")
	private float doCalculateIntersection1(final Ray3F ray) {
		final Point3F origin = ray.origin;
		
		final Point3F a = this.a.getPosition();
		final Point3F b = this.b.getPosition();
		final Point3F c = this.c.getPosition();
		
		final Vector3F direction = ray.direction;
		final Vector3F normal = Vector3F.normal(a, b, c);
		
//		final float denominator = normal.lengthSquared();
		final float normalDotProductDirection = normal.dotProduct(direction);
		
		if(normalDotProductDirection > -EPSILON && normalDotProductDirection < EPSILON) {
			return Float.NaN;
		}
		
		final float normalDotProductA = normal.dotProduct(new Vector3F(a));
		final float t = (normal.dotProduct(new Vector3F(origin)) + normalDotProductA) / normalDotProductDirection;
		
		if(t < 0.0F) {
			return Float.NaN;
		}
		
		final Point3F surfaceIntersectionPoint = origin.add(direction, t);
		
		final Vector3F directionAB = Vector3F.direction(a, b);
		final Vector3F directionASurfaceIntersectionPoint = Vector3F.direction(a, surfaceIntersectionPoint);
		final Vector3F directionABCrossProductDirectionASurfaceIntersectionPoint = directionAB.crossProduct(directionASurfaceIntersectionPoint);
		
		if(normal.dotProduct(directionABCrossProductDirectionASurfaceIntersectionPoint) < 0.0F) {
			return Float.NaN;
		}
		
		final Vector3F directionBC = Vector3F.direction(b, c);
		final Vector3F directionBSurfaceIntersectionPoint = Vector3F.direction(b, surfaceIntersectionPoint);
		final Vector3F directionBCCrossProductDirectionBSurfaceIntersectionPoint = directionBC.crossProduct(directionBSurfaceIntersectionPoint);
		
		final float u0 = normal.dotProduct(directionBCCrossProductDirectionBSurfaceIntersectionPoint);
		
		if(u0 < 0.0F) {
			return Float.NaN;
		}
		
		final Vector3F directionCA = Vector3F.direction(c, a);
		final Vector3F directionCSurfaceIntersectionPoint = Vector3F.direction(c, surfaceIntersectionPoint);
		final Vector3F directionCACrossProductDirectionCSurfaceIntersectionPoint = directionCA.crossProduct(directionCSurfaceIntersectionPoint);
		
		final float v0 = normal.dotProduct(directionCACrossProductDirectionCSurfaceIntersectionPoint);
		
		if(v0 < 0.0F) {
			return Float.NaN;
		}
		
//		final float u1 = u0 / denominator;
//		final float v1 = v0 / denominator;
//		final float w1 = 1.0F - u1 - v1;
		
		return t;
	}
}