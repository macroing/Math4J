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
 * A {@code Triangle2I} denotes a 2-dimensional triangle that uses the data type {@code int}.
 * <p>
 * This class is immutable and therefore thread-safe.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class Triangle2I implements Shape2I {
	private final Vertex2I a;
	private final Vertex2I b;
	private final Vertex2I c;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructs a new {@code Triangle2I} instance given its vertices {@code a}, {@code b} and {@code c}.
	 * <p>
	 * If either {@code a}, {@code b} or {@code c} are {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param a one of the vertices of this {@code Triangle2I} instance
	 * @param b one of the vertices of this {@code Triangle2I} instance
	 * @param c one of the vertices of this {@code Triangle2I} instance
	 * @throws NullPointerException thrown if, and only if, either {@code a}, {@code b} or {@code c} are {@code null}
	 */
	public Triangle2I(final Vertex2I a, final Vertex2I b, final Vertex2I c) {
		this.a = Objects.requireNonNull(a, "a == null");
		this.b = Objects.requireNonNull(b, "b == null");
		this.c = Objects.requireNonNull(c, "c == null");
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns a {@code String} representation of this {@code Triangle2I} instance.
	 * 
	 * @return a {@code String} representation of this {@code Triangle2I} instance
	 */
	@Override
	public String toString() {
		return String.format("new Triangle2I(%s, %s, %s)", this.a, this.b, this.c);
	}
	
	/**
	 * Returns the {@link Vertex2I} instance that denotes the vertex {@code A}.
	 * 
	 * @return the {@code Vertex2I} instance that denotes the vertex {@code A}
	 */
	public Vertex2I getA() {
		return this.a;
	}
	
	/**
	 * Returns the {@link Vertex2I} instance that denotes the vertex {@code B}.
	 * 
	 * @return the {@code Vertex2I} instance that denotes the vertex {@code B}
	 */
	public Vertex2I getB() {
		return this.b;
	}
	
	/**
	 * Returns the {@link Vertex2I} instance that denotes the vertex {@code C}.
	 * 
	 * @return the {@code Vertex2I} instance that denotes the vertex {@code C}
	 */
	public Vertex2I getC() {
		return this.c;
	}
	
	/**
	 * Compares {@code object} to this {@code Triangle2I} instance for equality.
	 * <p>
	 * Returns {@code true} if, and only if, {@code object} is an instance of {@code Triangle2I}, and their respective values are equal, {@code false} otherwise.
	 * 
	 * @param object the {@code Object} to compare to this {@code Triangle2I} instance for equality
	 * @return {@code true} if, and only if, {@code object} is an instance of {@code Triangle2I}, and their respective values are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object object) {
		if(object == this) {
			return true;
		} else if(!(object instanceof Triangle2I)) {
			return false;
		} else if(!Objects.equals(this.a, Triangle2I.class.cast(object).a)) {
			return false;
		} else if(!Objects.equals(this.b, Triangle2I.class.cast(object).b)) {
			return false;
		} else if(!Objects.equals(this.c, Triangle2I.class.cast(object).c)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returns a hash code for this {@code Triangle2I} instance.
	 * 
	 * @return a hash code for this {@code Triangle2I} instance
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.a, this.b, this.c);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * A {@code Vertex2I} denotes a vertex of a {@link Triangle2I} instance.
	 * <p>
	 * This class is immutable and therefore thread-safe.
	 * 
	 * @since 1.0.0
	 * @author J&#246;rgen Lundgren
	 */
	public static final class Vertex2I {
		private final Point2I position;
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/**
		 * Constructs a new {@code Vertex2I} instance given its position.
		 * <p>
		 * If {@code position} is {@code null}, a {@code NullPointerException} will be thrown.
		 * 
		 * @param position the position of this {@code Vertex2I} instance
		 * @throws NullPointerException thrown if, and only if, {@code position} is {@code null}
		 */
		public Vertex2I(final Point2I position) {
			this.position = Objects.requireNonNull(position, "position == null");
		}
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/**
		 * Returns the position associated with this {@code Vertex2I} instance.
		 * 
		 * @return the position associated with this {@code Vertex2I} instance
		 */
		public Point2I getPosition() {
			return this.position;
		}
		
		/**
		 * Returns a {@code String} representation of this {@code Vertex2I} instance.
		 * 
		 * @return a {@code String} representation of this {@code Vertex2I} instance
		 */
		@Override
		public String toString() {
			return String.format("new Vertex2I(%s)", this.position);
		}
		
		/**
		 * Compares {@code object} to this {@code Vertex2I} instance for equality.
		 * <p>
		 * Returns {@code true} if, and only if, {@code object} is an instance of {@code Vertex2I}, and their respective values are equal, {@code false} otherwise.
		 * 
		 * @param object the {@code Object} to compare to this {@code Vertex2I} instance for equality
		 * @return {@code true} if, and only if, {@code object} is an instance of {@code Vertex2I}, and their respective values are equal, {@code false} otherwise
		 */
		@Override
		public boolean equals(final Object object) {
			if(object == this) {
				return true;
			} else if(!(object instanceof Vertex2I)) {
				return false;
			} else if(!Objects.equals(this.position, Vertex2I.class.cast(object).position)) {
				return false;
			} else {
				return true;
			}
		}
		
		/**
		 * Returns a hash code for this {@code Vertex2I} instance.
		 * 
		 * @return a hash code for this {@code Vertex2I} instance
		 */
		@Override
		public int hashCode() {
			return Objects.hash(this.position);
		}
	}
}