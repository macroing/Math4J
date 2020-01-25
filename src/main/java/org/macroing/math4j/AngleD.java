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

import static org.macroing.math4j.MathD.PI_MULTIPLIED_BY_TWO;
import static org.macroing.math4j.MathD.asin;
import static org.macroing.math4j.MathD.atan;
import static org.macroing.math4j.MathD.atan2;
import static org.macroing.math4j.MathD.max;
import static org.macroing.math4j.MathD.min;
import static org.macroing.math4j.MathD.tan;
import static org.macroing.math4j.MathD.toDegrees;
import static org.macroing.math4j.MathD.toRadians;
import static org.macroing.math4j.MathD.wrapAround;

import java.util.Objects;

/**
 * An {@code AngleD} encapsulates angles in forms such as degrees and radians using the data type {@code double}.
 * <p>
 * This class is immutable and therefore thread-safe.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class AngleD {
	private static final double DEGREES_MAXIMUM = 360.0D;
	private static final double DEGREES_MAXIMUM_PITCH = 90.0D;
	private static final double DEGREES_MINIMUM = 0.0D;
	private static final double DEGREES_MINIMUM_PITCH = -90.0D;
	private static final double RADIANS_MAXIMUM = PI_MULTIPLIED_BY_TWO;
	private static final double RADIANS_MINIMUM = 0.0D;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * The angle in degrees.
	 */
	public final double degrees;
	
	/**
	 * The maximum angle in degrees.
	 */
	public final double degreesMaximum;
	
	/**
	 * The minimum angle in degrees.
	 */
	public final double degreesMinimum;
	
	/**
	 * The angle in radians.
	 */
	public final double radians;
	
	/**
	 * The maximum angle in radians.
	 */
	public final double radiansMaximum;
	
	/**
	 * The minimum angle in radians.
	 */
	public final double radiansMinimum;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private AngleD(final double degrees, final double degreesMinimum, final double degreesMaximum, final double radians, final double radiansMinimum, final double radiansMaximum) {
		this.degrees = degrees;
		this.degreesMinimum = degreesMinimum;
		this.degreesMaximum = degreesMaximum;
		this.radians = radians;
		this.radiansMinimum = radiansMinimum;
		this.radiansMaximum = radiansMaximum;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Adds {@code angle} to this {@code AngleD} instance.
	 * <p>
	 * Returns a new {@code AngleD} instance with the result of the addition.
	 * <p>
	 * If {@code angle} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param angle the {@code AngleD} to add
	 * @return a new {@code AngleD} instance with the result of the addition
	 * @throws NullPointerException thrown if, and only if, {@code angle} is {@code null}
	 */
	public AngleD add(final AngleD angle) {
		final double degreesMinimum = this.degreesMinimum;
		final double degreesMaximum = this.degreesMaximum;
		final double degrees = wrapAround(this.degrees + angle.degrees, degreesMinimum, degreesMaximum);
		
		return degrees(degrees, degreesMinimum, degreesMaximum);
	}
	
	/**
	 * Returns a new {@code AngleD} instance that represents half of this {@code AngleD} instance.
	 * 
	 * @return a new {@code AngleD} instance that represents half of this {@code AngleD} instance
	 */
	public AngleD half() {
		final double degreesMinimum = this.degreesMinimum;
		final double degreesMaximum = this.degreesMaximum;
		final double degrees = wrapAround(this.degrees * 0.5D, degreesMinimum, degreesMaximum);
		
		return degrees(degrees, degreesMinimum, degreesMaximum);
	}
	
	/**
	 * Subtracts {@code angle} from this {@code AngleD} instance.
	 * <p>
	 * Returns a new {@code AngleD} instance with the result of the subtraction.
	 * <p>
	 * If {@code angle} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param angle the {@code AngleD} to subtract
	 * @return a new {@code AngleD} instance with the result of the subtraction
	 * @throws NullPointerException thrown if, and only if, {@code angle} is {@code null}
	 */
	public AngleD subtract(final AngleD angle) {
		final double degreesMinimum = this.degreesMinimum;
		final double degreesMaximum = this.degreesMaximum;
		final double degrees = wrapAround(this.degrees - angle.degrees, degreesMinimum, degreesMaximum);
		
		return degrees(degrees, degreesMinimum, degreesMaximum);
	}
	
	/**
	 * Returns a {@code String} representation of this {@code AngleD} instance.
	 * 
	 * @return a {@code String} representation of this {@code AngleD} instance
	 */
	@Override
	public String toString() {
		return String.format("AngleD.degrees(%s, %s, %s)", Double.toString(this.degrees), Double.toString(this.degreesMinimum), Double.toString(this.degreesMaximum));
	}
	
	/**
	 * Compares {@code object} to this {@code AngleD} instance for equality.
	 * <p>
	 * Returns {@code true} if, and only if, {@code object} is an instance of {@code AngleD}, and their respective values are equal, {@code false} otherwise.
	 * 
	 * @param object the {@code Object} to compare to this {@code AngleD} instance for equality
	 * @return {@code true} if, and only if, {@code object} is an instance of {@code AngleD}, and their respective values are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object object) {
		if(object == this) {
			return true;
		} else if(!(object instanceof AngleD)) {
			return false;
		} else if(!MathD.equals(this.degrees, AngleD.class.cast(object).degrees)) {
			return false;
		} else if(!MathD.equals(this.degreesMaximum, AngleD.class.cast(object).degreesMaximum)) {
			return false;
		} else if(!MathD.equals(this.degreesMinimum, AngleD.class.cast(object).degreesMinimum)) {
			return false;
		} else if(!MathD.equals(this.radians, AngleD.class.cast(object).radians)) {
			return false;
		} else if(!MathD.equals(this.radiansMaximum, AngleD.class.cast(object).radiansMaximum)) {
			return false;
		} else if(!MathD.equals(this.radiansMinimum, AngleD.class.cast(object).radiansMinimum)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returns the angle in degrees.
	 * 
	 * @return the angle in degrees
	 */
	public double getDegrees() {
		return this.degrees;
	}
	
	/**
	 * Returns the maximum angle in degrees.
	 * 
	 * @return the maximum angle in degrees
	 */
	public double getDegreesMaximum() {
		return this.degreesMaximum;
	}
	
	/**
	 * Returns the minimum angle in degrees.
	 * 
	 * @return the minimum angle in degrees
	 */
	public double getDegreesMinimum() {
		return this.degreesMinimum;
	}
	
	/**
	 * Returns the angle in radians.
	 * 
	 * @return the angle in radians
	 */
	public double getRadians() {
		return this.radians;
	}
	
	/**
	 * Returns the maximum angle in radians.
	 * 
	 * @return the maximum angle in radians
	 */
	public double getRadiansMaximum() {
		return this.radiansMaximum;
	}
	
	/**
	 * Returns the minimum angle in radians.
	 * 
	 * @return the minimum angle in radians
	 */
	public double getRadiansMinimum() {
		return this.radiansMinimum;
	}
	
	/**
	 * Returns a hash code for this {@code AngleD} instance.
	 * 
	 * @return a hash code for this {@code AngleD} instance
	 */
	@Override
	public int hashCode() {
		return Objects.hash(Double.valueOf(this.degrees), Double.valueOf(this.degreesMaximum), Double.valueOf(this.degreesMinimum), Double.valueOf(this.radians), Double.valueOf(this.radiansMaximum), Double.valueOf(this.radiansMinimum));
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns a new {@code AngleD} instance based on an angle in degrees.
	 * <p>
	 * Calling this method is equivalent to the following:
	 * <pre>
	 * {@code
	 * AngleD.degrees(degrees, 0.0D, 360.0D)
	 * }
	 * </pre>
	 * 
	 * @param degrees the angle in degrees
	 * @return a new {@code AngleD} instance based on an angle in degrees
	 */
	public static AngleD degrees(final double degrees) {
		return degrees(degrees, DEGREES_MINIMUM, DEGREES_MAXIMUM);
	}
	
	/**
	 * Returns a new {@code AngleD} instance based on an angle in degrees and an interval of valid degrees.
	 * 
	 * @param degrees the angle in degrees
	 * @param degreesA the degrees that represents one of the ends of the interval of valid degrees
	 * @param degreesB the degrees that represents one of the ends of the interval of valid degrees
	 * @return a new {@code AngleD} instance based on an angle in degrees and an interval of valid degrees
	 */
	public static AngleD degrees(final double degrees, final double degreesA, final double degreesB) {
		final double newDegreesMinimum = min(degreesA, degreesB);
		final double newDegreesMaximum = max(degreesA, degreesB);
		final double newDegrees = wrapAround(degrees, newDegreesMinimum, newDegreesMaximum);
		
		final double newRadians = toRadians(newDegrees);
		final double newRadiansMinimum = toRadians(newDegreesMinimum);
		final double newRadiansMaximum = toRadians(newDegreesMaximum);
		
		return new AngleD(newDegrees, newDegreesMinimum, newDegreesMaximum, newRadians, newRadiansMinimum, newRadiansMaximum);
	}
	
	/**
	 * Returns a Field of View (FoV) {@code AngleD} based on {@code focalDistance} and {@code resolution}.
	 * <p>
	 * This method allows you to use {@code resolution} in either X- or Y-direction. So, either width or height.
	 * 
	 * @param focalDistance the focal distance (also known as focal length}
	 * @param resolution the resolution in X- or Y-direction (width or height)
	 * @return a Field of View (FoV) {@code AngleD} based on {@code focalDistance} and {@code resolution}
	 */
	public static AngleD fieldOfView(final double focalDistance, final double resolution) {
		return radians(2.0D * atan(resolution * 0.5D / focalDistance));
	}
	
	/**
	 * Returns a horizontal field of view (FOV) {@code AngleD} based on {@code fieldOfViewY}, {@code resolutionX} and {@code resolutionY}.
	 * <p>
	 * If {@code fieldOfViewY} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param fieldOfViewY the vertical field of view
	 * @param resolutionX the resolution of the X-axis
	 * @param resolutionY the resolution of the Y-axis
	 * @return a horizontal field of view (FOV) {@code AngleD} based on {@code fieldOfViewY}, {@code resolutionX} and {@code resolutionY}
	 * @throws NullPointerException thrown if, and only if, {@code fieldOfViewY} is {@code null}
	 */
	public static AngleD fieldOfViewX(final AngleD fieldOfViewY, final float resolutionX, final float resolutionY) {
		return radians(2.0D * atan(tan(fieldOfViewY.radians * 0.5D) * (resolutionX / resolutionY)));
	}
	
	/**
	 * Returns a vertical field of view (FOV) {@code AngleD} based on {@code fieldOfViewX}, {@code resolutionX} and {@code resolutionY}.
	 * <p>
	 * If {@code fieldOfViewX} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param fieldOfViewX the horizontal field of view
	 * @param resolutionX the resolution of the X-axis
	 * @param resolutionY the resolution of the Y-axis
	 * @return a vertical field of view (FOV) {@code AngleD} based on {@code fieldOfViewX}, {@code resolutionX} and {@code resolutionY}
	 * @throws NullPointerException thrown if, and only if, {@code fieldOfViewX} is {@code null}
	 */
	public static AngleD fieldOfViewY(final AngleD fieldOfViewX, final float resolutionX, final float resolutionY) {
		return radians(2.0D * atan(tan(fieldOfViewX.radians * 0.5D) * (resolutionY / resolutionX)));
	}
	
	/**
	 * Returns a new pitch {@code AngleD} instance based on {@code eye} and {@code lookAt}.
	 * <p>
	 * If either {@code eye} or {@code lookAt} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param eye the {@link Point3D} on which the "eye" is positioned
	 * @param lookAt the {@code Point3D} to which the "eye" is looking
	 * @return a new pitch {@code AngleD} instance based on {@code eye} and {@code lookAt}
	 * @throws NullPointerException thrown if, and only if, either {@code eye} or {@code lookAt} are {@code null}
	 */
	public static AngleD pitch(final Point3D eye, final Point3D lookAt) {
		return pitch(Vector3D.direction(eye, lookAt).normalize());
	}
	
	/**
	 * Returns a new pitch {@code AngleD} instance based on {@code direction}.
	 * <p>
	 * If {@code direction} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param direction a normalized direction {@link Vector3D}
	 * @return a new pitch {@code AngleD} instance based on {@code direction}
	 * @throws NullPointerException thrown if, and only if, {@code direction} is {@code null}
	 */
	public static AngleD pitch(final Vector3D direction) {
		return degrees(toDegrees(asin(direction.y)), DEGREES_MINIMUM_PITCH, DEGREES_MAXIMUM_PITCH);
	}
	
	/**
	 * Returns a new {@code AngleD} instance based on an angle in radians.
	 * <p>
	 * Calling this method is equivalent to the following:
	 * <pre>
	 * {@code
	 * AngleD.radians(radians, 0.0D, PI * 2.0D)
	 * }
	 * </pre>
	 * 
	 * @param radians the angle in radians
	 * @return a new {@code AngleD} instance based on an angle in radians
	 */
	public static AngleD radians(final double radians) {
		return radians(radians, RADIANS_MINIMUM, RADIANS_MAXIMUM);
	}
	
	/**
	 * Returns a new {@code AngleD} instance based on an angle in radians and an interval of valid radians.
	 * 
	 * @param radians the angle in radians
	 * @param radiansA the radians that represents one of the ends of the interval of valid radians
	 * @param radiansB the radians that represents one of the ends of the interval of valid radians
	 * @return a new {@code AngleD} instance based on an angle in radians and an interval of valid radians
	 */
	public static AngleD radians(final double radians, final double radiansA, final double radiansB) {
		final double newRadiansMinimum = min(radiansA, radiansB);
		final double newRadiansMaximum = max(radiansA, radiansB);
		final double newRadians = wrapAround(radians, newRadiansMinimum, newRadiansMaximum);
		
		final double newDegrees = toDegrees(newRadians);
		final double newDegreesMinimum = toDegrees(newRadiansMinimum);
		final double newDegreesMaximum = toDegrees(newRadiansMaximum);
		
		return new AngleD(newDegrees, newDegreesMinimum, newDegreesMaximum, newRadians, newRadiansMinimum, newRadiansMaximum);
	}
	
	/**
	 * Returns a new yaw {@code AngleD} instance based on {@code eye} and {@code lookAt}.
	 * <p>
	 * If either {@code eye} or {@code lookAt} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param eye the {@link Point3D} on which the "eye" is positioned
	 * @param lookAt the {@code Point3D} to which the "eye" is looking
	 * @return a new yaw {@code AngleD} instance based on {@code eye} and {@code lookAt}
	 * @throws NullPointerException thrown if, and only if, either {@code eye} or {@code lookAt} are {@code null}
	 */
	public static AngleD yaw(final Point3D eye, final Point3D lookAt) {
		return yaw(Vector3D.direction(eye, lookAt).normalize());
	}
	
	/**
	 * Returns a new yaw {@code AngleD} instance based on {@code direction}.
	 * <p>
	 * If {@code direction} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param direction a normalized direction {@link Vector3D}
	 * @return a new yaw {@code AngleD} instance based on {@code direction}
	 * @throws NullPointerException thrown if, and only if, {@code direction} is {@code null}
	 */
	public static AngleD yaw(final Vector3D direction) {
		return degrees(toDegrees(atan2(direction.x, direction.z)));
	}
}