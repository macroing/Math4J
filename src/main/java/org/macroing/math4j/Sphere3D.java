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

import static org.macroing.math4j.MathD.PI;
import static org.macroing.math4j.MathD.abs;
import static org.macroing.math4j.MathD.max;
import static org.macroing.math4j.MathD.pow;
import static org.macroing.math4j.MathD.sqrt;

import java.util.Objects;
import java.util.Optional;

/**
 * A {@code Sphere3D} denotes a sphere that uses the data type {@code double}.
 * <p>
 * This {@code Sphere3D} class implements both {@link BoundingVolume3D} and {@link Shape3D}. So it can be used as a bounding volume and a geometric shape.
 * <p>
 * This class is immutable and therefore thread-safe.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class Sphere3D implements BoundingVolume3D, Shape3D {
	private static final double EPSILON = 0.0001D;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final Point3D center;
	private final double radius;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructs a new {@code Sphere3D} instance given its center and radius.
	 * <p>
	 * If {@code center} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param center the center of the {@code Sphere3D} instance
	 * @param radius the radius of the {@code Sphere3D} instance
	 * @throws NullPointerException thrown if, and only if, {@code center} is {@code null}
	 */
	public Sphere3D(final Point3D center, final double radius) {
		this.center = Objects.requireNonNull(center, "center == null");
		this.radius = radius;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Samples this {@code Sphere3D} instance.
	 * <p>
	 * Returns an optional {@link SurfaceSample3D} with the surface sample.
	 * <p>
	 * If either {@code referencePoint} or {@code referenceSurfaceNormal} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param referencePoint the reference point on this {@code Sphere3D} instance
	 * @param referenceSurfaceNormal the reference surface normal on this {@code Sphere3D} instance
	 * @param u a random {@code double} with a uniform distribution between {@code 0.0D} and {@code 1.0D}
	 * @param v a random {@code double} with a uniform distribution between {@code 0.0D} and {@code 1.0D}
	 * @return an optional {@code SurfaceSample3D} with the surface sample
	 * @throws NullPointerException thrown if, and only if, either {@code referencePoint} or {@code referenceSurfaceNormal} are {@code null}
	 */
	@Override
	public Optional<SurfaceSample3D> sample(final Point3D referencePoint, final Vector3D referenceSurfaceNormal, final double u, final double v) {
		Objects.requireNonNull(referenceSurfaceNormal, "referenceSurfaceNormal == null");
		
		final Point3D center = getCenter();
		
		final Vector3D directionToCenter = Vector3D.direction(referencePoint, center);
		
		final double lengthSquared = directionToCenter.lengthSquared();
		final double radius = getRadius();
		final double radiusSquared = getRadiusSquared();
		
		if(lengthSquared < radiusSquared * 1.00001D) {
			final Vector3D surfaceNormal = SampleGeneratorD.sampleSphereUniformDistribution(u, v);
			
			final Point3D point = center.add(surfaceNormal, radius);
			
			final Vector3D directionToSurface = Vector3D.direction(point, referencePoint);
			final Vector3D directionToSurfaceNormalized = directionToSurface.normalize();
			
			final double probabilityDensityFunctionValue = directionToSurface.lengthSquared() * getSurfaceAreaProbabilityDensityFunctionValue() / abs(directionToSurfaceNormalized.dotProduct(surfaceNormal));
			
			return Optional.of(new SurfaceSample3D(point, surfaceNormal, probabilityDensityFunctionValue));
		}
		
		final double sinThetaMaxSquared = radiusSquared / lengthSquared;
		final double cosThetaMax = sqrt(max(0.0D, 1.0D - sinThetaMaxSquared));
		
		final OrthoNormalBasis33D orthoNormalBasis = new OrthoNormalBasis33D(directionToCenter);
		
		final Vector3D coneLocalSpace = SampleGeneratorD.sampleConeUniformDistribution(u, v, cosThetaMax);
		final Vector3D coneGlobalSpace = coneLocalSpace.transform(orthoNormalBasis).normalize();
		
		final Ray3D ray = new Ray3D(referencePoint, coneGlobalSpace);
		
		final double t0 = intersectionT(ray);
		final double t1 = Double.isNaN(t0) ? directionToCenter.dotProduct(coneGlobalSpace) : t0;
		
		final Point3D point = ray.origin.add(ray.direction, t1);
		
		final Vector3D surfaceNormal = Vector3D.direction(center, point).normalize();
		
		final double probabilityDensityFunctionValue = SampleGeneratorD.coneUniformDistributionProbabilityDensityFunction(cosThetaMax);
		
		return Optional.of(new SurfaceSample3D(point, surfaceNormal, probabilityDensityFunctionValue));
	}
	
	/**
	 * Returns a {@link Point2D} instance denoting the texture coordinates (or UV-coordinates) of the surface of this {@code Sphere3D} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3D} instance that was used in a call to {@link #intersectionT(Ray3D)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Sphere3D} instance that was returned by {@code intersectionT(Ray3D)}
	 * @return a {@code Point2D} instance denoting the texture coordinates (or UV-coordinates) of the surface of this {@code Sphere3D} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Point2D calculateTextureCoordinates(final Ray3D ray, final double t) {
		return Point2D.direction(ray.direction);
	}
	
	/**
	 * Returns the center of this {@code Sphere3D} instance.
	 * 
	 * @return the center of this {@code Sphere3D} instance
	 */
	public Point3D getCenter() {
		return this.center;
	}
	
	/**
	 * Returns a {@link Point3D} instance that represents the closest point to {@code p} and is contained inside this {@code Sphere3D} instance.
	 * <p>
	 * If {@code p} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param p a {@code Point3D} instance
	 * @return a {@code Point3D} instance that represents the closest point to {@code p} and is contained inside this {@code Sphere3D} instance
	 * @throws NullPointerException thrown if, and only if, {@code p} is {@code null}
	 */
	@Override
	public Point3D getClosestPointTo(final Point3D p) {
		final Point3D center = this.center;
		
		final double radius = this.radius;
		
		final Vector3D direction = Vector3D.direction(center, p).normalize();
		
		final Point3D surfaceIntersectionPoint = center.add(direction, radius);
		
		final double distanceToSurfaceIntersectionPointSquared = center.distanceToSquared(surfaceIntersectionPoint);
		final double distanceToPSquared = center.distanceToSquared(p);
		
		return distanceToSurfaceIntersectionPointSquared <= distanceToPSquared ? surfaceIntersectionPoint : p;
	}
	
	/**
	 * Returns a {@link Point3D} with the greatest X-, Y- and Z-coordinates that is contained in this {@code Sphere3D} instance.
	 * 
	 * @return a {@code Point3D} with the greatest X-, Y- and Z-coordinates that is contained in this {@code Sphere3D} instance
	 */
	@Override
	public Point3D getMaximum() {
		return new Point3D(this.center.x + this.radius, this.center.y + this.radius, this.center.z + this.radius);
	}
	
	/**
	 * Returns a {@link Point3D} with the smallest X-, Y- and Z-coordinates that is contained in this {@code Sphere3D} instance.
	 * 
	 * @return a {@code Point3D} with the smallest X-, Y- and Z-coordinates that is contained in this {@code Sphere3D} instance
	 */
	@Override
	public Point3D getMinimum() {
		return new Point3D(this.center.x - this.radius, this.center.y - this.radius, this.center.z - this.radius);
	}
	
	/**
	 * Performs a transformation.
	 * <p>
	 * Returns a new {@code Sphere3D} instance with the result of the transformation.
	 * <p>
	 * If {@code m} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param m the {@link Matrix44D} instance to perform the transformation with
	 * @return a new {@code Sphere3D} instance with the result of the transformation
	 * @throws NullPointerException thrown if, and only if, {@code m} is {@code null}
	 */
	@Override
	public Sphere3D transform(final Matrix44D m) {
		return new Sphere3D(this.center.transform(m), this.radius);
	}
	
	/**
	 * Returns a {@code String} representation of this {@code Sphere3D} instance.
	 * 
	 * @return a {@code String} representation of this {@code Sphere3D} instance
	 */
	@Override
	public String toString() {
		return String.format("new Sphere3D(%s, %s)", this.center, Double.toString(this.radius));
	}
	
	/**
	 * Returns a {@link Vector3D} instance denoting the surface normal of the surface of this {@code Sphere3D} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3D} instance that was used in a call to {@link #intersectionT(Ray3D)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Sphere3D} instance that was returned by {@code intersectionT(Ray3D)}
	 * @return a {@code Vector3D} instance denoting the surface normal of the surface of this {@code Sphere3D} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Vector3D calculateSurfaceNormal(final Ray3D ray, final double t) {
		return Vector3D.direction(this.center, calculateSurfaceIntersectionPoint(ray, t)).normalize();
	}
	
	/**
	 * Returns {@code true} if, and only if, {@code p} is contained inside this {@code Sphere3D} instance, {@code false} otherwise.
	 * <p>
	 * If {@code p} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param p a {@link Point3D} instance
	 * @return {@code true} if, and only if, {@code p} is contained inside this {@code Sphere3D} instance, {@code false} otherwise
	 * @throws NullPointerException thrown if, and only if, {@code p} is {@code null}
	 */
	@Override
	public boolean contains(final Point3D p) {
		return this.center.distanceToSquared(p) < this.radius * this.radius;
	}
	
	/**
	 * Compares {@code object} to this {@code Sphere3D} instance for equality.
	 * <p>
	 * Returns {@code true} if, and only if, {@code object} is an instance of {@code Sphere3D}, and their respective values are equal, {@code false} otherwise.
	 * 
	 * @param object the {@code Object} to compare to this {@code Sphere3D} instance for equality
	 * @return {@code true} if, and only if, {@code object} is an instance of {@code Sphere3D}, and their respective values are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object object) {
		if(object == this) {
			return true;
		} else if(!(object instanceof Sphere3D)) {
			return false;
		} else if(!Objects.equals(this.center, Sphere3D.class.cast(object).center)) {
			return false;
		} else if(!MathD.equals(this.radius, Sphere3D.class.cast(object).radius)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returns the probability density function (PDF) value for solid angle.
	 * <p>
	 * If either {@code referencePoint}, {@code referenceSurfaceNormal}, {@code point} or {@code surfaceNormal} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param referencePoint the reference point on this {@code Sphere3D} instance
	 * @param referenceSurfaceNormal the reference surface normal on this {@code Sphere3D} instance
	 * @param point the point on this {@code Sphere3D} instance
	 * @param surfaceNormal the surface normal on this {@code Sphere3D} instance
	 * @return the probability density function (PDF) value for solid angle
	 * @throws NullPointerException thrown if, and only if, either {@code referencePoint}, {@code referenceSurfaceNormal}, {@code point} or {@code surfaceNormal} are {@code null}
	 */
	@Override
	public double calculateProbabilityDensityFunctionValueForSolidAngle(final Point3D referencePoint, final Vector3D referenceSurfaceNormal, final Point3D point, final Vector3D surfaceNormal) {
		Objects.requireNonNull(referenceSurfaceNormal, "referenceSurfaceNormal == null");
		
		final Point3D center = getCenter();
		
		final Vector3D directionToCenter = Vector3D.direction(referencePoint, center);
		
		final double lengthSquared = directionToCenter.lengthSquared();
		final double radiusSquared = getRadiusSquared();
		
		if(lengthSquared < radiusSquared * 1.00001D) {
			final Vector3D directionToSurface = Vector3D.direction(point, referencePoint);
			final Vector3D directionToSurfaceNormalized = directionToSurface.normalize();
			
			final double probabilityDensityFunctionValue = directionToSurface.lengthSquared() * getSurfaceAreaProbabilityDensityFunctionValue() / abs(directionToSurfaceNormalized.dotProduct(surfaceNormal));
			
			return probabilityDensityFunctionValue;
		}
		
		final double sinThetaMaxSquared = radiusSquared / lengthSquared;
		final double cosThetaMax = sqrt(max(0.0D, 1.0D - sinThetaMaxSquared));
		final double probabilityDensityFunctionValue = SampleGeneratorD.coneUniformDistributionProbabilityDensityFunction(cosThetaMax);
		
		return probabilityDensityFunctionValue;
	}
	
	/**
	 * Returns the diameter of this {@code Sphere3D} instance.
	 * 
	 * @return the diameter of this {@code Sphere3D} instance
	 */
	public double getDiameter() {
		return this.radius * 2.0D;
	}
	
	/**
	 * Returns the radius of this {@code Sphere3D} instance.
	 * 
	 * @return the radius of this {@code Sphere3D} instance
	 */
	public double getRadius() {
		return this.radius;
	}
	
	/**
	 * Returns the squared radius of this {@code Sphere3D} instance.
	 * 
	 * @return the squared radius of this {@code Sphere3D} instance
	 */
	public double getRadiusSquared() {
		return this.radius * this.radius;
	}
	
	/**
	 * Returns the surface area of this {@code Sphere3D} instance.
	 * 
	 * @return the surface area of this {@code Sphere3D} instance
	 */
	@Override
	public double getSurfaceArea() {
		return 4.0D * PI * (this.radius * this.radius);
	}
	
	/**
	 * Returns the surface area probability density function (PDF) value of this {@code Sphere3D} instance.
	 * 
	 * @return the surface area probability density function (PDF) value of this {@code Sphere3D} instance
	 */
	@Override
	public double getSurfaceAreaProbabilityDensityFunctionValue() {
		return 3.0D / getSurfaceArea();
	}
	
	/**
	 * Returns the volume of this {@code Sphere3D} instance.
	 * 
	 * @return the volume of this {@code Sphere3D} instance
	 */
	@Override
	public double getVolume() {
		return 4.0D / 3.0D * PI * pow(this.radius, 3.0D);
	}
	
	/**
	 * Performs an intersection test between {@code ray} and this {@code Sphere3D} instance.
	 * <p>
	 * Returns {@code t}, the parametric distance from {@code ray} to this {@code Sphere3D} instance, or {@code Double.NaN} if no intersection exists.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3D} to perform an intersection test against this {@code Sphere3D} instance
	 * @return {@code t}, the parametric distance from {@code ray} to this {@code Sphere3D} instance, or {@code Double.NaN} if no intersection exists
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public double intersectionT(final Ray3D ray) {
		final Point3D origin = ray.origin;
		final Point3D center = this.center;
		
		final Vector3D direction = ray.direction;
		final Vector3D centerToOrigin = Vector3D.direction(center, origin);
		
		final double radiusSquared = getRadiusSquared();
		
		final double a = direction.lengthSquared();
		final double b = 2.0D * centerToOrigin.dotProduct(direction);
		final double c = centerToOrigin.lengthSquared() - radiusSquared;
		
		final double[] t = MathD.solveQuadraticSystem(a, b, c);
		
		final double t0 = t[0];
		final double t1 = t[1];
		
		if(Double.isNaN(t0) && Double.isNaN(t1)) {
			return Double.NaN;
		} else if(!Double.isNaN(t0) && t0 > EPSILON) {
			return t0;
		} else if(!Double.isNaN(t1) && t1 > EPSILON) {
			return t1;
		} else {
			return Double.NaN;
		}
	}
	
	/**
	 * Returns a hash code for this {@code Sphere3D} instance.
	 * 
	 * @return a hash code for this {@code Sphere3D} instance
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.center, Double.valueOf(this.radius));
	}
}