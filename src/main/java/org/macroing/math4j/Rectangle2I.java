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

import static org.macroing.math4j.MathI.max;
import static org.macroing.math4j.MathI.min;

import java.util.Objects;

/**
 * A {@code Rectangle2I} denotes a 2-dimensional rectangle, of type {@code int}.
 * <p>
 * This class is immutable and therefore thread-safe.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class Rectangle2I implements Shape2I {
	private final Point2I a;
	private final Point2I b;
	private final Point2I c;
	private final Point2I d;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructs a new {@code Rectangle2I} instance based on {@code x} and {@code y}.
	 * <p>
	 * If either {@code x} or {@code y} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param x a {@link Point2I} instance
	 * @param y a {@code Point2I} instance
	 * @throws NullPointerException thrown if, and only if, either {@code x} or {@code y} are {@code null}
	 */
	public Rectangle2I(final Point2I x, final Point2I y) {
		this.a = new Point2I(min(x.x, y.x), min(x.y, y.y));
		this.b = new Point2I(min(x.x, y.x), max(x.y, y.y));
		this.c = new Point2I(max(x.x, y.x), max(x.y, y.y));
		this.d = new Point2I(max(x.x, y.x), min(x.y, y.y));
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns the {@link Point2I} with the minimum X and minimum Y coordinate values.
	 * 
	 * @return the {@code Point2I} with the minimum X and minimum Y coordinate values
	 */
	public Point2I getA() {
		return this.a;
	}
	
	/**
	 * Returns the {@link Point2I} with the minimum X and maximum Y coordinate values.
	 * 
	 * @return the {@code Point2I} with the minimum X and maximum Y coordinate values
	 */
	public Point2I getB() {
		return this.b;
	}
	
	/**
	 * Returns the {@link Point2I} with the maximum X and maximum Y coordinate values.
	 * 
	 * @return the {@code Point2I} with the maximum X and maximum Y coordinate values
	 */
	public Point2I getC() {
		return this.c;
	}
	
	/**
	 * Returns the {@link Point2I} with the maximum X and minimum Y coordinate values.
	 * 
	 * @return the {@code Point2I} with the maximum X and minimum Y coordinate values
	 */
	public Point2I getD() {
		return this.d;
	}
	
	/**
	 * Returns a {@code String} representation of this {@code Rectangle2I} instance.
	 * 
	 * @return a {@code String} representation of this {@code Rectangle2I} instance
	 */
	@Override
	public String toString() {
		return String.format("new Rectangle2I(%s, %s)", this.a, this.c);
	}
	
	/**
	 * Compares {@code object} to this {@code Rectangle2I} instance for equality.
	 * <p>
	 * Returns {@code true} if, and only if, {@code object} is an instance of {@code Rectangle2I}, and their respective values are equal, {@code false} otherwise.
	 * 
	 * @param object the {@code Object} to compare to this {@code Rectangle2I} instance for equality
	 * @return {@code true} if, and only if, {@code object} is an instance of {@code Rectangle2I}, and their respective values are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object object) {
		if(object == this) {
			return true;
		} else if(!(object instanceof Rectangle2I)) {
			return false;
		} else if(!Objects.equals(this.a, Rectangle2I.class.cast(object).a)) {
			return false;
		} else if(!Objects.equals(this.b, Rectangle2I.class.cast(object).b)) {
			return false;
		} else if(!Objects.equals(this.c, Rectangle2I.class.cast(object).c)) {
			return false;
		} else if(!Objects.equals(this.d, Rectangle2I.class.cast(object).d)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returns the height of this {@code Rectangle2I} instance.
	 * 
	 * @return the height of this {@code Rectangle2I} instance
	 */
	public int getHeight() {
		return min(this.c.x - this.a.x, this.c.y - this.a.y);
	}
	
	/**
	 * Returns the width of this {@code Rectangle2I} instance.
	 * 
	 * @return the width of this {@code Rectangle2I} instance
	 */
	public int getWidth() {
		return max(this.c.x - this.a.x, this.c.y - this.a.y);
	}
	
	/**
	 * Returns a hash code for this {@code Rectangle2I} instance.
	 * 
	 * @return a hash code for this {@code Rectangle2I} instance
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.a, this.b, this.c, this.d);
	}
}