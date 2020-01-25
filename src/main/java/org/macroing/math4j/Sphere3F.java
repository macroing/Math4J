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

import static org.macroing.math4j.MathF.PI;
import static org.macroing.math4j.MathF.abs;
import static org.macroing.math4j.MathF.max;
import static org.macroing.math4j.MathF.pow;
import static org.macroing.math4j.MathF.sqrt;

import java.util.Objects;
import java.util.Optional;

/**
 * A {@code Sphere3F} denotes a sphere that uses the data type {@code float}.
 * <p>
 * This {@code Sphere3F} class implements both {@link BoundingVolume3F} and {@link Shape3F}. So it can be used as a bounding volume and a geometric shape.
 * <p>
 * This class is immutable and therefore thread-safe.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class Sphere3F implements BoundingVolume3F, Shape3F {
	private static final float EPSILON = 0.0001F;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final Point3F center;
	private final float radius;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructs a new {@code Sphere3F} instance given its center and radius.
	 * <p>
	 * If {@code center} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param center the center of the {@code Sphere3F} instance
	 * @param radius the radius of the {@code Sphere3F} instance
	 * @throws NullPointerException thrown if, and only if, {@code center} is {@code null}
	 */
	public Sphere3F(final Point3F center, final float radius) {
		this.center = Objects.requireNonNull(center, "center == null");
		this.radius = radius;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Samples this {@code Sphere3F} instance.
	 * <p>
	 * Returns an optional {@link SurfaceSample3F} with the surface sample.
	 * <p>
	 * If either {@code referencePoint} or {@code referenceSurfaceNormal} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param referencePoint the reference point on this {@code Sphere3F} instance
	 * @param referenceSurfaceNormal the reference surface normal on this {@code Sphere3F} instance
	 * @param u a random {@code float} with a uniform distribution between {@code 0.0F} and {@code 1.0F}
	 * @param v a random {@code float} with a uniform distribution between {@code 0.0F} and {@code 1.0F}
	 * @return an optional {@code SurfaceSample3F} with the surface sample
	 * @throws NullPointerException thrown if, and only if, either {@code referencePoint} or {@code referenceSurfaceNormal} are {@code null}
	 */
	@Override
	public Optional<SurfaceSample3F> sample(final Point3F referencePoint, final Vector3F referenceSurfaceNormal, final float u, final float v) {
		Objects.requireNonNull(referenceSurfaceNormal, "referenceSurfaceNormal == null");
		
		final Point3F center = getCenter();
		
		final Vector3F directionToCenter = Vector3F.direction(referencePoint, center);
		
		final float lengthSquared = directionToCenter.lengthSquared();
		final float radius = getRadius();
		final float radiusSquared = getRadiusSquared();
		
		if(lengthSquared < radiusSquared * 1.00001F) {
			final Vector3F surfaceNormal = SampleGeneratorF.sampleSphereUniformDistribution(u, v);
			
			final Point3F point = center.add(surfaceNormal, radius);
			
			final Vector3F directionToSurface = Vector3F.direction(point, referencePoint);
			final Vector3F directionToSurfaceNormalized = directionToSurface.normalize();
			
			final float probabilityDensityFunctionValue = directionToSurface.lengthSquared() * getSurfaceAreaProbabilityDensityFunctionValue() / abs(directionToSurfaceNormalized.dotProduct(surfaceNormal));
			
			return Optional.of(new SurfaceSample3F(point, surfaceNormal, probabilityDensityFunctionValue));
		}
		
		final float sinThetaMaxSquared = radiusSquared / lengthSquared;
		final float cosThetaMax = sqrt(max(0.0F, 1.0F - sinThetaMaxSquared));
		
		final OrthoNormalBasis33F orthoNormalBasis = new OrthoNormalBasis33F(directionToCenter);
		
		final Vector3F coneLocalSpace = SampleGeneratorF.sampleConeUniformDistribution(u, v, cosThetaMax);
		final Vector3F coneGlobalSpace = coneLocalSpace.transform(orthoNormalBasis).normalize();
		
		final Ray3F ray = new Ray3F(referencePoint, coneGlobalSpace);
		
		final float t0 = intersection(ray);
		final float t1 = Float.isNaN(t0) ? directionToCenter.dotProduct(coneGlobalSpace) : t0;
		
		final Point3F point = ray.origin.add(ray.direction, t1);
		
		final Vector3F surfaceNormal = Vector3F.direction(center, point).normalize();
		
		final float probabilityDensityFunctionValue = SampleGeneratorF.coneUniformDistributionProbabilityDensityFunction(cosThetaMax);
		
		return Optional.of(new SurfaceSample3F(point, surfaceNormal, probabilityDensityFunctionValue));
	}
	
	/**
	 * Returns an {@link OrthoNormalBasis33F} instance denoting the OrthoNormal Basis (ONB) of the surface of this {@code Sphere3F} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} instance that was used in a call to {@link #intersection(Ray3F)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Sphere3F} instance that was returned by {@code intersection(Ray3F)}
	 * @param isCorrectlyOriented {@code true} if, and only if, the {@code OrthoNormalBasis33F} must lie in the same hemisphere as {@code ray}, {@code false} otherwise
	 * @return an {@code OrthoNormalBasis33F} instance denoting the OrthoNormal Basis (ONB) of the surface of this {@code Sphere3F} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public OrthoNormalBasis33F calculateOrthoNormalBasis(final Ray3F ray, final float t, final boolean isCorrectlyOriented) {
		return new OrthoNormalBasis33F(calculateSurfaceNormal(ray, t, isCorrectlyOriented));
	}
	
	/**
	 * Returns a {@link Point2F} instance denoting the texture coordinates (or UV-coordinates) of the surface of this {@code Sphere3F} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} instance that was used in a call to {@link #intersection(Ray3F)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Sphere3F} instance that was returned by {@code intersection(Ray3F)}
	 * @return a {@code Point2F} instance denoting the texture coordinates (or UV-coordinates) of the surface of this {@code Sphere3F} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Point2F calculateTextureCoordinates(final Ray3F ray, final float t) {
		return Point2F.direction(ray.direction);
	}
	
	/**
	 * Returns a {@link Point3F} instance denoting the surface intersection point of the surface of this {@code Sphere3F} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} instance that was used in a call to {@link #intersection(Ray3F)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Sphere3F} instance that was returned by {@code intersection(Ray3F)}
	 * @return a {@code Point3F} instance denoting the surface intersection point of the surface of this {@code Sphere3F} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Point3F calculateSurfaceIntersectionPoint(final Ray3F ray, final float t) {
		return ray.origin.add(ray.direction, t);
	}
	
	/**
	 * Returns the center of this {@code Sphere3F} instance.
	 * 
	 * @return the center of this {@code Sphere3F} instance
	 */
	@Override
	public Point3F getCenter() {
		return this.center;
	}
	
	/**
	 * Returns a {@link Point3F} instance that represents the closest point to {@code p} and is contained inside this {@code Sphere3F} instance.
	 * <p>
	 * If {@code p} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param p a {@code Point3F} instance
	 * @return a {@code Point3F} instance that represents the closest point to {@code p} and is contained inside this {@code Sphere3F} instance
	 * @throws NullPointerException thrown if, and only if, {@code p} is {@code null}
	 */
	@Override
	public Point3F getClosestPointTo(final Point3F p) {
		final Point3F center = this.center;
		
		final float radius = this.radius;
		
		final Vector3F direction = Vector3F.direction(center, p).normalize();
		
		final Point3F surfaceIntersectionPoint = center.add(direction, radius);
		
		final float distanceToSurfaceIntersectionPointSquared = center.distanceToSquared(surfaceIntersectionPoint);
		final float distanceToPSquared = center.distanceToSquared(p);
		
		return distanceToSurfaceIntersectionPointSquared <= distanceToPSquared ? surfaceIntersectionPoint : p;
	}
	
	/**
	 * Returns a {@link Point3F} with the greatest X-, Y- and Z-coordinates that is contained in this {@code Sphere3F} instance.
	 * 
	 * @return a {@code Point3F} with the greatest X-, Y- and Z-coordinates that is contained in this {@code Sphere3F} instance
	 */
	@Override
	public Point3F getMaximum() {
		return new Point3F(this.center.x + this.radius, this.center.y + this.radius, this.center.z + this.radius);
	}
	
	/**
	 * Returns a {@link Point3F} with the smallest X-, Y- and Z-coordinates that is contained in this {@code Sphere3F} instance.
	 * 
	 * @return a {@code Point3F} with the smallest X-, Y- and Z-coordinates that is contained in this {@code Sphere3F} instance
	 */
	@Override
	public Point3F getMinimum() {
		return new Point3F(this.center.x - this.radius, this.center.y - this.radius, this.center.z - this.radius);
	}
	
	/**
	 * Performs a transformation.
	 * <p>
	 * Returns a new {@code Sphere3F} instance with the result of the transformation.
	 * <p>
	 * If {@code m} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param m the {@link Matrix44F} instance to perform the transformation with
	 * @return a new {@code Sphere3F} instance with the result of the transformation
	 * @throws NullPointerException thrown if, and only if, {@code m} is {@code null}
	 */
	@Override
	public Sphere3F transform(final Matrix44F m) {
		return new Sphere3F(this.center.transform(m), this.radius);
	}
	
	/**
	 * Returns a {@code String} representation of this {@code Sphere3F} instance.
	 * 
	 * @return a {@code String} representation of this {@code Sphere3F} instance
	 */
	@Override
	public String toString() {
		return String.format("new Sphere3F(%s, %s)", this.center, Float.toString(this.radius));
	}
	
	/**
	 * Returns a {@link Vector3F} instance denoting the surface normal of the surface of this {@code Sphere3F} instance where an intersection occurred.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} instance that was used in a call to {@link #intersection(Ray3F)} and resulted in {@code t} being returned
	 * @param t the parametric distance from {@code ray} to this {@code Sphere3F} instance that was returned by {@code intersection(Ray3F)}
	 * @param isCorrectlyOriented {@code true} if, and only if, the {@code Vector3F} must lie in the same hemisphere as {@code ray}, {@code false} otherwise
	 * @return a {@code Vector3F} instance denoting the surface normal of the surface of this {@code Sphere3F} instance where an intersection occurred
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public Vector3F calculateSurfaceNormal(final Ray3F ray, final float t, final boolean isCorrectlyOriented) {
		final Vector3F surfaceNormal0 = Vector3F.direction(this.center, calculateSurfaceIntersectionPoint(ray, t)).normalize();
		final Vector3F surfaceNormal1 = isCorrectlyOriented && surfaceNormal0.dotProduct(ray.direction) >= 0.0F ? surfaceNormal0.negate() : surfaceNormal0;
		
		return surfaceNormal1;
	}
	
	/**
	 * Returns {@code true} if, and only if, {@code p} is contained inside this {@code Sphere3F} instance, {@code false} otherwise.
	 * <p>
	 * If {@code p} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param p a {@link Point3F} instance
	 * @return {@code true} if, and only if, {@code p} is contained inside this {@code Sphere3F} instance, {@code false} otherwise
	 * @throws NullPointerException thrown if, and only if, {@code p} is {@code null}
	 */
	@Override
	public boolean contains(final Point3F p) {
		return this.center.distanceToSquared(p) < this.radius * this.radius;
	}
	
	/**
	 * Compares {@code object} to this {@code Sphere3F} instance for equality.
	 * <p>
	 * Returns {@code true} if, and only if, {@code object} is an instance of {@code Sphere3F}, and their respective values are equal, {@code false} otherwise.
	 * 
	 * @param object the {@code Object} to compare to this {@code Sphere3F} instance for equality
	 * @return {@code true} if, and only if, {@code object} is an instance of {@code Sphere3F}, and their respective values are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object object) {
		if(object == this) {
			return true;
		} else if(!(object instanceof Sphere3F)) {
			return false;
		} else if(!Objects.equals(this.center, Sphere3F.class.cast(object).center)) {
			return false;
		} else if(!MathF.equals(this.radius, Sphere3F.class.cast(object).radius)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Performs an intersection test between {@code boundingVolume} and this {@code Sphere3F} instance.
	 * <p>
	 * Returns {@code true} if, and only if, {@code boundingVolume} intersects this {@code Sphere3F} instance, {@code false} otherwise.
	 * <p>
	 * If {@code boundingVolume} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param boundingVolume the {@code BoundingVolume3F} to perform an intersection test against this {@code Sphere3F} instance
	 * @return {@code true} if, and only if, {@code boundingVolume} intersects this {@code Sphere3F} instance, {@code false} otherwise
	 * @throws NullPointerException thrown if, and only if, {@code boundingVolume} is {@code null}
	 */
	@Override
	public boolean intersects(final BoundingVolume3F boundingVolume) {
		return contains(boundingVolume.getClosestPointTo(this.center));
	}
	
	/**
	 * Performs an intersection test between {@code ray} and this {@code Sphere3F} instance.
	 * <p>
	 * Returns {@code true} if, and only if, {@code ray} intersects this {@code Sphere3F} instance, {@code false} otherwise.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} to perform an intersection test against this {@code Sphere3F} instance
	 * @return {@code true} if, and only if, {@code ray} intersects this {@code Sphere3F} instance, {@code false} otherwise
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public boolean intersects(final Ray3F ray) {
		return !Float.isNaN(intersection(ray));
	}
	
	/**
	 * Returns the probability density function (PDF) value for solid angle.
	 * <p>
	 * If either {@code referencePoint}, {@code referenceSurfaceNormal}, {@code point} or {@code surfaceNormal} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param referencePoint the reference point on this {@code Sphere3F} instance
	 * @param referenceSurfaceNormal the reference surface normal on this {@code Sphere3F} instance
	 * @param point the point on this {@code Sphere3F} instance
	 * @param surfaceNormal the surface normal on this {@code Sphere3F} instance
	 * @return the probability density function (PDF) value for solid angle
	 * @throws NullPointerException thrown if, and only if, either {@code referencePoint}, {@code referenceSurfaceNormal}, {@code point} or {@code surfaceNormal} are {@code null}
	 */
	@Override
	public float calculateProbabilityDensityFunctionValueForSolidAngle(final Point3F referencePoint, final Vector3F referenceSurfaceNormal, final Point3F point, final Vector3F surfaceNormal) {
		Objects.requireNonNull(referenceSurfaceNormal, "referenceSurfaceNormal == null");
		
		final Point3F center = getCenter();
		
		final Vector3F directionToCenter = Vector3F.direction(referencePoint, center);
		
		final float lengthSquared = directionToCenter.lengthSquared();
		final float radiusSquared = getRadiusSquared();
		
		if(lengthSquared < radiusSquared * 1.00001F) {
			final Vector3F directionToSurface = Vector3F.direction(point, referencePoint);
			final Vector3F directionToSurfaceNormalized = directionToSurface.normalize();
			
			final float probabilityDensityFunctionValue = directionToSurface.lengthSquared() * getSurfaceAreaProbabilityDensityFunctionValue() / abs(directionToSurfaceNormalized.dotProduct(surfaceNormal));
			
			return probabilityDensityFunctionValue;
		}
		
		final float sinThetaMaxSquared = radiusSquared / lengthSquared;
		final float cosThetaMax = sqrt(max(0.0F, 1.0F - sinThetaMaxSquared));
		final float probabilityDensityFunctionValue = SampleGeneratorF.coneUniformDistributionProbabilityDensityFunction(cosThetaMax);
		
		return probabilityDensityFunctionValue;
	}
	
	/**
	 * Returns the diameter of this {@code Sphere3F} instance.
	 * 
	 * @return the diameter of this {@code Sphere3F} instance
	 */
	public float getDiameter() {
		return this.radius * 2.0F;
	}
	
	/**
	 * Returns the radius of this {@code Sphere3F} instance.
	 * 
	 * @return the radius of this {@code Sphere3F} instance
	 */
	public float getRadius() {
		return this.radius;
	}
	
	/**
	 * Returns the squared radius of this {@code Sphere3F} instance.
	 * 
	 * @return the squared radius of this {@code Sphere3F} instance
	 */
	public float getRadiusSquared() {
		return this.radius * this.radius;
	}
	
	/**
	 * Returns the surface area of this {@code Sphere3F} instance.
	 * 
	 * @return the surface area of this {@code Sphere3F} instance
	 */
	@Override
	public float getSurfaceArea() {
		return 4.0F * PI * getRadiusSquared();
	}
	
	/**
	 * Returns the surface area probability density function (PDF) value of this {@code Sphere3F} instance.
	 * 
	 * @return the surface area probability density function (PDF) value of this {@code Sphere3F} instance
	 */
	@Override
	public float getSurfaceAreaProbabilityDensityFunctionValue() {
		return 3.0F / getSurfaceArea();
	}
	
	/**
	 * Returns the volume of this {@code Sphere3F} instance.
	 * 
	 * @return the volume of this {@code Sphere3F} instance
	 */
	@Override
	public float getVolume() {
		return 4.0F / 3.0F * PI * pow(this.radius, 3.0F);
	}
	
	/**
	 * Performs an intersection test between {@code ray} and this {@code Sphere3F} instance.
	 * <p>
	 * Returns {@code t}, the parametric distance from {@code ray} to this {@code Sphere3F} instance, or {@code Float.NaN} if no intersection exists.
	 * <p>
	 * If {@code ray} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param ray the {@link Ray3F} to perform an intersection test against this {@code Sphere3F} instance
	 * @return {@code t}, the parametric distance from {@code ray} to this {@code Sphere3F} instance, or {@code Float.NaN} if no intersection exists
	 * @throws NullPointerException thrown if, and only if, {@code ray} is {@code null}
	 */
	@Override
	public float intersection(final Ray3F ray) {
		final Point3F origin = ray.origin;
		final Point3F center = this.center;
		
		final Vector3F direction = ray.direction;
		final Vector3F centerToOrigin = Vector3F.direction(center, origin);
		
		final float radiusSquared = getRadiusSquared();
		
		final float a = direction.lengthSquared();
		final float b = 2.0F * centerToOrigin.dotProduct(direction);
		final float c = centerToOrigin.lengthSquared() - radiusSquared;
		
		final float[] t = MathF.solveQuadraticSystem(a, b, c);
		
		final float t0 = t[0];
		final float t1 = t[1];
		
		if(Float.isNaN(t0) && Float.isNaN(t1)) {
			return Float.NaN;
		} else if(t0 > EPSILON) {
			return t0;
		} else if(t1 > EPSILON) {
			return t1;
		} else {
			return Float.NaN;
		}
	}
	
	/**
	 * Returns a hash code for this {@code Sphere3F} instance.
	 * 
	 * @return a hash code for this {@code Sphere3F} instance
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.center, Float.valueOf(this.radius));
	}
}