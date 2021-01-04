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

import static org.macroing.math4j.MathF.max;
import static org.macroing.math4j.MathF.min;
import static org.macroing.math4j.MathF.normalize;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * A {@code RectangularCuboid3F} denotes a 3-dimensional rectangular cuboid that uses the data type {@code float}.
 * <p>
 * This class is immutable and therefore thread-safe.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class RectangularCuboid3F implements BoundingVolume3F, Shape3F {
	private static final float EPSILON = 0.0001F;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final Point3F maximum;
	private final Point3F minimum;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructs a new {@code RectangularCuboid3F} instance.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new RectangularCuboid3F(new Point3F(-0.5F, -0.5F, -0.5F), new Point3F(0.5F, 0.5F, 0.5F));
	 * }
	 * </pre>
	 */
	public RectangularCuboid3F() {
		this(new Point3F(-0.5F, -0.5F, -0.5F), new Point3F(0.5F, 0.5F, 0.5F));
	}
	
	/**
	 * Constructs a new {@code RectangularCuboid3F} instance.
	 * <p>
	 * If either {@code a} or {@code b} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param a a reference {@link Point3F}
	 * @param b a reference {@code Point3F}
	 * @throws NullPointerException thrown if, and only if, either {@code a} or {@code b} are {@code null}
	 */
	public RectangularCuboid3F(final Point3F a, final Point3F b) {
		this.maximum = Point3F.maximum(a, b);
		this.minimum = Point3F.minimum(a, b);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns a {@link BoundingVolume3F} instance that contains this {@code RectangularCuboid3F} instance.
	 * 
	 * @return a {@code BoundingVolume3F} instance that contains this {@code RectangularCuboid3F} instance
	 */
	@Override
	public BoundingVolume3F getBoundingVolume() {
		return this;
	}
	
	/**
	 * Samples this {@code RectangularCuboid3F} instance.
	 * <p>
	 * Returns an optional {@link SurfaceSample3F} with the surface sample.
	 * <p>
	 * If either {@code referencePoint} or {@code referenceSurfaceNormal} are {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * Note: This method has not been implemented yet.
	 * 
	 * @param referencePoint the reference point on this {@code RectangularCuboid3F} instance
	 * @param referenceSurfaceNormal the reference surface normal on this {@code RectangularCuboid3F} instance
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
	 * Performs an intersection test between {@code ray} and this {@code RectangularCuboid3F} instance.
	 * <p>
	 * Returns an {@code Optional} with an optional {@link Intersection3F} instance that contains information about the intersection, if it was found.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} to perform an intersection test against this {@code RectangularCuboid3F} instance
	 * @return an {@code Optional} with an optional {@code Intersection3F} instance that contains information about the intersection, if it was found
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Optional<Intersection3F> intersection(final Ray3F ray) {
		float tMaximum = Float.MAX_VALUE;
		float tMinimum = Float.MIN_VALUE;
		
		int sideIn = 0;
//		int sideOut = 0;
		
		final float maximumX = this.maximum.x;
		final float maximumY = this.maximum.y;
		final float maximumZ = this.maximum.z;
		
		final float minimumX = this.minimum.x;
		final float minimumY = this.minimum.y;
		final float minimumZ = this.minimum.z;
		
//		Calculate the intersections on the X-axis:
		final float originX = ray.origin.x;
		
		final float directionReciprocalX = 1.0F / ray.direction.x;
		
		final float t0X = (minimumX - originX) * directionReciprocalX;
		final float t1X = (maximumX - originX) * directionReciprocalX;
		
		if(directionReciprocalX > 0.0F) {
			if(t0X > tMinimum) {
				tMinimum = t0X;
				
				sideIn = 1;
			}
			
			if(t1X < tMaximum) {
				tMaximum = t1X;
				
//				sideOut = 2;
			}
		} else {
			if(t1X > tMinimum) {
				tMinimum = t1X;
				
				sideIn = 2;
			}
			
			if(t0X < tMaximum) {
				tMaximum = t0X;
				
//				sideOut = 1;
			}
		}
		
		if(tMinimum > tMaximum) {
			return Optional.empty();
		}
		
//		Calculate the intersections on the Y-axis:
		final float originY = ray.origin.y;
		
		final float directionReciprocalY = 1.0F / ray.direction.y;
		
		final float t0Y = (minimumY - originY) * directionReciprocalY;
		final float t1Y = (maximumY - originY) * directionReciprocalY;
		
		if(directionReciprocalY > 0.0F) {
			if(t0Y > tMinimum) {
				tMinimum = t0Y;
				
				sideIn = 3;
			}
			
			if(t1Y < tMaximum) {
				tMaximum = t1Y;
				
//				sideOut = 4;
			}
		} else {
			if(t1Y > tMinimum) {
				tMinimum = t1Y;
				
				sideIn = 4;
			}
			
			if(t0Y < tMaximum) {
				tMaximum = t0Y;
				
//				sideOut = 3;
			}
		}
		
		if(tMinimum > tMaximum) {
			return Optional.empty();
		}
		
//		Calculate the intersections on the Z-axis:
		final float originZ = ray.origin.z;
		
		final float directionReciprocalZ = 1.0F / ray.direction.z;
		
		final float t0Z = (minimumZ - originZ) * directionReciprocalZ;
		final float t1Z = (maximumZ - originZ) * directionReciprocalZ;
		
		if(directionReciprocalZ > 0.0F) {
			if(t0Z > tMinimum) {
				tMinimum = t0Z;
				
				sideIn = 5;
			}
			
			if(t1Z < tMaximum) {
				tMaximum = t1Z;
				
//				sideOut = 6;
			}
		} else {
			if(t1Z > tMinimum) {
				tMinimum = t1Z;
				
				sideIn = 6;
			}
			
			if(t0Z < tMaximum) {
				tMaximum = t0Z;
				
//				sideOut = 5;
			}
		}
		
		if(tMaximum < 0.0F || tMinimum > tMaximum) {
			return Optional.empty();
		}
		
		final int side = sideIn;
		
		final float t = tMinimum;
		
		final Supplier<OrthoNormalBasis33F> orthoNormalBasisSupplier = () -> {
			switch(side) {
				case 1:
					return OrthoNormalBasis33F.POSITIVE_ONE_X;
				case 2:
					return OrthoNormalBasis33F.NEGATIVE_ONE_X;
				case 3:
					return OrthoNormalBasis33F.POSITIVE_ONE_Y;
				case 4:
					return OrthoNormalBasis33F.NEGATIVE_ONE_Y;
				case 5:
					return OrthoNormalBasis33F.POSITIVE_ONE_Z;
				case 6:
					return OrthoNormalBasis33F.NEGATIVE_ONE_Z;
				default:
					return new OrthoNormalBasis33F(new Vector3F());
			}
		};
		final Supplier<Point2F> textureCoordinatesSupplier = () -> {
			final Point3F surfaceIntersectionPoint = calculateSurfaceIntersectionPoint(ray, t);
			
			switch(side) {
				case 1:
					return new Point2F(normalize(surfaceIntersectionPoint.z, minimumZ, maximumZ), normalize(surfaceIntersectionPoint.y, minimumY, maximumY));
				case 2:
					return new Point2F(normalize(surfaceIntersectionPoint.z, minimumZ, maximumZ), normalize(surfaceIntersectionPoint.y, minimumY, maximumY));
				case 3:
					return new Point2F(normalize(surfaceIntersectionPoint.x, minimumX, maximumX), normalize(surfaceIntersectionPoint.z, minimumZ, maximumZ));
				case 4:
					return new Point2F(normalize(surfaceIntersectionPoint.x, minimumX, maximumX), normalize(surfaceIntersectionPoint.z, minimumZ, maximumZ));
				case 5:
					return new Point2F(normalize(surfaceIntersectionPoint.x, minimumX, maximumX), normalize(surfaceIntersectionPoint.y, minimumY, maximumY));
				case 6:
					return new Point2F(normalize(surfaceIntersectionPoint.x, minimumX, maximumX), normalize(surfaceIntersectionPoint.y, minimumY, maximumY));
				default:
					return new Point2F(0.5F, 0.5F);
			}
		};
		final Supplier<Point3F> surfaceIntersectionPointSupplier = () -> ray.origin.add(ray.direction, t);
		final Supplier<Vector3F> surfaceNormalSupplier = () -> {
			switch(side) {
				case 1:
					return Vector3F.POSITIVE_ONE_X;
				case 2:
					return Vector3F.NEGATIVE_ONE_X;
				case 3:
					return Vector3F.POSITIVE_ONE_Y;
				case 4:
					return Vector3F.NEGATIVE_ONE_Y;
				case 5:
					return Vector3F.POSITIVE_ONE_Z;
				case 6:
					return Vector3F.NEGATIVE_ONE_Z;
				default:
					return Vector3F.ZERO;
			}
		};
		
		return Optional.of(new Intersection3F(ray, this, t, orthoNormalBasisSupplier, textureCoordinatesSupplier, surfaceIntersectionPointSupplier, surfaceNormalSupplier));
	}
	
	/**
	 * Returns a {@link Point2F} instance denoting the texture coordinates (or UV-coordinates) of the surface of this {@code RectangularCuboid3F} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} instance that was used in a call to {@link #intersectionT(Ray3F)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code RectangularCuboid3F} instance that was returned by {@code intersectionT(Ray3F)}
	 * @return a {@code Point2F} instance denoting the texture coordinates (or UV-coordinates) of the surface of this {@code RectangularCuboid3F} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Point2F calculateTextureCoordinates(final Ray3F ray, final float t) {
		final Point3F maximum = getMaximum();
		final Point3F midpoint = getMidpoint();
		final Point3F minimum = getMinimum();
		final Point3F surfaceIntersectionPoint = calculateSurfaceIntersectionPoint(ray, t);
		
		final float halfX = (maximum.x - minimum.x) * 0.5F;
		final float halfY = (maximum.y - minimum.y) * 0.5F;
		final float halfZ = (maximum.z - minimum.z) * 0.5F;
		
		final int x = surfaceIntersectionPoint.x < midpoint.x && surfaceIntersectionPoint.x + halfX - EPSILON < midpoint.x ? -1 : surfaceIntersectionPoint.x > midpoint.x && surfaceIntersectionPoint.x - halfX + EPSILON > midpoint.x ? 1 : 0;
		final int y = surfaceIntersectionPoint.y < midpoint.y && surfaceIntersectionPoint.y + halfY - EPSILON < midpoint.y ? -1 : surfaceIntersectionPoint.y > midpoint.y && surfaceIntersectionPoint.y - halfY + EPSILON > midpoint.y ? 1 : 0;
		final int z = surfaceIntersectionPoint.z < midpoint.z && surfaceIntersectionPoint.z + halfZ - EPSILON < midpoint.z ? -1 : surfaceIntersectionPoint.z > midpoint.z && surfaceIntersectionPoint.z - halfZ + EPSILON > midpoint.z ? 1 : 0;
		
		final int face = x == -1 ? 1 : x == 1 ? 2 : y == -1 ? 3 : y == 1 ? 4 : z == -1 ? 5 : z == 1 ? 6 : 0;
		
		switch(face) {
			case 1:
				return new Point2F(normalize(surfaceIntersectionPoint.z, minimum.z, maximum.z), normalize(surfaceIntersectionPoint.y, minimum.y, maximum.y));
			case 2:
				return new Point2F(normalize(surfaceIntersectionPoint.z, minimum.z, maximum.z), normalize(surfaceIntersectionPoint.y, minimum.y, maximum.y));
			case 3:
				return new Point2F(normalize(surfaceIntersectionPoint.x, minimum.x, maximum.x), normalize(surfaceIntersectionPoint.z, minimum.z, maximum.z));
			case 4:
				return new Point2F(normalize(surfaceIntersectionPoint.x, minimum.x, maximum.x), normalize(surfaceIntersectionPoint.z, minimum.z, maximum.z));
			case 5:
				return new Point2F(normalize(surfaceIntersectionPoint.x, minimum.x, maximum.x), normalize(surfaceIntersectionPoint.y, minimum.y, maximum.y));
			case 6:
				return new Point2F(normalize(surfaceIntersectionPoint.x, minimum.x, maximum.x), normalize(surfaceIntersectionPoint.y, minimum.y, maximum.y));
			default:
				return new Point2F(0.5F, 0.5F);
		}
	}
	
	/**
	 * Returns a {@link Point3F} instance that represents the closest point to {@code p} and is contained inside this {@code RectangularCuboid3F} instance.
	 * <p>
	 * If {@code p} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param p a {@code Point3F} instance
	 * @return a {@code Point3F} instance that represents the closest point to {@code p} and is contained inside this {@code RectangularCuboid3F} instance
	 * @throws NullPointerException thrown if, and only if, {@code p} is {@code null}
	 */
	@Override
	public Point3F getClosestPointTo(final Point3F p) {
		final float maximumX = this.maximum.x;
		final float maximumY = this.maximum.y;
		final float maximumZ = this.maximum.z;
		
		final float minimumX = this.minimum.x;
		final float minimumY = this.minimum.y;
		final float minimumZ = this.minimum.z;
		
		final float x = p.x < minimumX ? minimumX : p.x > maximumX ? maximumX : p.x;
		final float y = p.y < minimumY ? minimumY : p.y > maximumY ? maximumY : p.y;
		final float z = p.z < minimumZ ? minimumZ : p.z > maximumZ ? maximumZ : p.z;
		
		return new Point3F(x, y, z);
	}
	
	/**
	 * Returns a {@link Point3F} with the greatest X-, Y- and Z-coordinates that is contained in this {@code RectangularCuboid3F} instance.
	 * 
	 * @return a {@code Point3F} with the greatest X-, Y- and Z-coordinates that is contained in this {@code RectangularCuboid3F} instance
	 */
	@Override
	public Point3F getMaximum() {
		return this.maximum;
	}
	
	/**
	 * Returns a {@link Point3F} with the smallest X-, Y- and Z-coordinates that is contained in this {@code RectangularCuboid3F} instance.
	 * 
	 * @return a {@code Point3F} with the smallest X-, Y- and Z-coordinates that is contained in this {@code RectangularCuboid3F} instance
	 */
	@Override
	public Point3F getMinimum() {
		return this.minimum;
	}
	
	/**
	 * Performs a transformation.
	 * <p>
	 * Returns a new {@code RectangularCuboid3F} instance with the result of the transformation.
	 * <p>
	 * If {@code m} is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * Note: This method has not been implemented yet.
	 * 
	 * @param m the {@link Matrix44F} instance to perform the transformation with
	 * @return a new {@code RectangularCuboid3F} instance with the result of the transformation
	 * @throws NullPointerException thrown if, and only if, {@code m} is {@code null}
	 */
	@Override
	public RectangularCuboid3F transform(final Matrix44F m) {
		Objects.requireNonNull(m, "m == null");
		
		return this;//TODO: Implement!
	}
	
	/**
	 * Returns a {@code String} representation of this {@code RectangularCuboid3F} instance.
	 * 
	 * @return a {@code String} representation of this {@code RectangularCuboid3F} instance
	 */
	@Override
	public String toString() {
		return String.format("new RectangularCuboid3F(%s, %s)", this.maximum, this.minimum);
	}
	
	/**
	 * Returns a {@link Vector3F} instance denoting the surface normal of the surface of this {@code RectangularCuboid3F} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} instance that was used in a call to {@link #intersectionT(Ray3F)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code RectangularCuboid3F} instance that was returned by {@code intersectionT(Ray3F)}
	 * @return a {@code Vector3F} instance denoting the surface normal of the surface of this {@code RectangularCuboid3F} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Vector3F calculateSurfaceNormal(final Ray3F ray, final float t) {
		final Point3F maximum = getMaximum();
		final Point3F midpoint = getMidpoint();
		final Point3F minimum = getMinimum();
		final Point3F surfaceIntersectionPoint = calculateSurfaceIntersectionPoint(ray, t);
		
		final float halfX = (maximum.x - minimum.x) * 0.5F;
		final float halfY = (maximum.y - minimum.y) * 0.5F;
		final float halfZ = (maximum.z - minimum.z) * 0.5F;
		
		final float x = surfaceIntersectionPoint.x < midpoint.x && surfaceIntersectionPoint.x + halfX - EPSILON < midpoint.x ? -1.0F : surfaceIntersectionPoint.x > midpoint.x && surfaceIntersectionPoint.x - halfX + EPSILON > midpoint.x ? 1.0F : 0.0F;
		final float y = surfaceIntersectionPoint.y < midpoint.y && surfaceIntersectionPoint.y + halfY - EPSILON < midpoint.y ? -1.0F : surfaceIntersectionPoint.y > midpoint.y && surfaceIntersectionPoint.y - halfY + EPSILON > midpoint.y ? 1.0F : 0.0F;
		final float z = surfaceIntersectionPoint.z < midpoint.z && surfaceIntersectionPoint.z + halfZ - EPSILON < midpoint.z ? -1.0F : surfaceIntersectionPoint.z > midpoint.z && surfaceIntersectionPoint.z - halfZ + EPSILON > midpoint.z ? 1.0F : 0.0F;
		
		return new Vector3F(x, y, z);
	}
	
	/**
	 * Returns {@code true} if, and only if, {@code p} is contained inside this {@code RectangularCuboid3F} instance, {@code false} otherwise.
	 * <p>
	 * If {@code p} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param p a {@link Point3F} instance
	 * @return {@code true} if, and only if, {@code p} is contained inside this {@code RectangularCuboid3F} instance, {@code false} otherwise
	 * @throws NullPointerException thrown if, and only if, {@code p} is {@code null}
	 */
	@Override
	public boolean contains(final Point3F p) {
		final boolean containsX = p.x >= this.minimum.x && p.x <= this.maximum.x;
		final boolean containsY = p.y >= this.minimum.y && p.y <= this.maximum.y;
		final boolean containsZ = p.z >= this.minimum.z && p.z <= this.maximum.z;
		final boolean containsP = containsX && containsY && containsZ;
		
		return containsP;
	}
	
	/**
	 * Compares {@code object} to this {@code RectangularCuboid3F} instance for equality.
	 * <p>
	 * Returns {@code true} if, and only if, {@code object} is an instance of {@code RectangularCuboid3F}, and their respective values are equal, {@code false} otherwise.
	 * 
	 * @param object the {@code Object} to compare to this {@code RectangularCuboid3F} instance for equality
	 * @return {@code true} if, and only if, {@code object} is an instance of {@code RectangularCuboid3F}, and their respective values are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object object) {
		if(object == this) {
			return true;
		} else if(!(object instanceof RectangularCuboid3F)) {
			return false;
		} else if(!Objects.equals(this.maximum, RectangularCuboid3F.class.cast(object).maximum)) {
			return false;
		} else if(!Objects.equals(this.minimum, RectangularCuboid3F.class.cast(object).minimum)) {
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
	 * @param referencePoint the reference point on this {@code RectangularCuboid3F} instance
	 * @param referenceSurfaceNormal the reference surface normal on this {@code RectangularCuboid3F} instance
	 * @param point the point on this {@code RectangularCuboid3F} instance
	 * @param surfaceNormal the surface normal on this {@code RectangularCuboid3F} instance
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
	 * Returns the surface area of this {@code RectangularCuboid3F} instance.
	 * 
	 * @return the surface area of this {@code RectangularCuboid3F} instance
	 */
	@Override
	public float getSurfaceArea() {
		final float x = this.maximum.x - this.minimum.x;
		final float y = this.maximum.y - this.minimum.y;
		final float z = this.maximum.z - this.minimum.z;
		final float surfaceArea = 2.0F * (x * y + y * z + z * x);
		
		return surfaceArea;
	}
	
	/**
	 * Returns the surface area probability density function (PDF) value of this {@code RectangularCuboid3F} instance.
	 * 
	 * @return the surface area probability density function (PDF) value of this {@code RectangularCuboid3F} instance
	 */
	@Override
	public float getSurfaceAreaProbabilityDensityFunctionValue() {
		return 1.0F / getSurfaceArea();
	}
	
	/**
	 * Returns the volume of this {@code RectangularCuboid3F} instance.
	 * 
	 * @return the volume of this {@code RectangularCuboid3F} instance
	 */
	@Override
	public float getVolume() {
		final float x = this.maximum.x - this.minimum.x;
		final float y = this.maximum.y - this.minimum.y;
		final float z = this.maximum.z - this.minimum.z;
		final float volume = x * y * z;
		
		return volume;
	}
	
	/**
	 * Performs an intersection test between {@code ray} and this {@code RectangularCuboid3F} instance.
	 * <p>
	 * Returns {@code t}, the parametric distance from {@code ray} to this {@code RectangularCuboid3F} instance, or {@code Float.NaN} if no intersection exists.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} to perform an intersection test against this {@code RectangularCuboid3F} instance
	 * @return {@code t}, the parametric distance from {@code ray} to this {@code RectangularCuboid3F} instance, or {@code Float.NaN} if no intersection exists
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public float intersectionT(final Ray3F ray) {
		final float originX = ray.origin.x;
		final float originY = ray.origin.y;
		final float originZ = ray.origin.z;
		
		final float directionReciprocalX = 1.0F / ray.direction.x;
		final float directionReciprocalY = 1.0F / ray.direction.y;
		final float directionReciprocalZ = 1.0F / ray.direction.z;
		
		final float maximumX = this.maximum.x;
		final float maximumY = this.maximum.y;
		final float maximumZ = this.maximum.z;
		
		final float minimumX = this.minimum.x;
		final float minimumY = this.minimum.y;
		final float minimumZ = this.minimum.z;
		
		final float t0X = (minimumX - originX) * directionReciprocalX;
		final float t0Y = (minimumY - originY) * directionReciprocalY;
		final float t0Z = (minimumZ - originZ) * directionReciprocalZ;
		
		final float t1X = (maximumX - originX) * directionReciprocalX;
		final float t1Y = (maximumY - originY) * directionReciprocalY;
		final float t1Z = (maximumZ - originZ) * directionReciprocalZ;
		
		final float tMaximum = min(max(t0X, t1X), max(t0Y, t1Y), max(t0Z, t1Z));
		final float tMinimum = max(min(t0X, t1X), min(t0Y, t1Y), min(t0Z, t1Z));
		
		return tMaximum < 0.0F ? Float.NaN : tMinimum > tMaximum ? Float.NaN : tMinimum;
	}
	
	/**
	 * Returns a hash code for this {@code RectangularCuboid3F} instance.
	 * 
	 * @return a hash code for this {@code RectangularCuboid3F} instance
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.maximum, this.minimum);
	}
}