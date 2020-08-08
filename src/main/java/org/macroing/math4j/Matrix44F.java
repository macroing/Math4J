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

import static org.macroing.math4j.MathF.abs;
import static org.macroing.math4j.MathF.cos;
import static org.macroing.math4j.MathF.sin;

import java.util.Objects;

/**
 * A {@code Matrix44F} denotes a 4 x 4 matrix with 16 elements of type {@code float}.
 * <p>
 * The default order of this {@code Matrix44F} class is row-major.
 * <p>
 * This class is immutable and therefore thread-safe.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class Matrix44F {
	/**
	 * The value of the element at index 0 or row 1 and column 1.
	 */
	public final float element11;
	
	/**
	 * The value of the element at index 1 or row 1 and column 2.
	 */
	public final float element12;
	
	/**
	 * The value of the element at index 2 or row 1 and column 3.
	 */
	public final float element13;
	
	/**
	 * The value of the element at index 3 or row 1 and column 4.
	 */
	public final float element14;
	
	/**
	 * The value of the element at index 4 or row 2 and column 1.
	 */
	public final float element21;
	
	/**
	 * The value of the element at index 5 or row 2 and column 2.
	 */
	public final float element22;
	
	/**
	 * The value of the element at index 6 or row 2 and column 3.
	 */
	public final float element23;
	
	/**
	 * The value of the element at index 7 or row 2 and column 4.
	 */
	public final float element24;
	
	/**
	 * The value of the element at index 8 or row 3 and column 1.
	 */
	public final float element31;
	
	/**
	 * The value of the element at index 9 or row 3 and column 2.
	 */
	public final float element32;
	
	/**
	 * The value of the element at index 10 or row 3 and column 3.
	 */
	public final float element33;
	
	/**
	 * The value of the element at index 11 or row 3 and column 4.
	 */
	public final float element34;
	
	/**
	 * The value of the element at index 12 or row 4 and column 1.
	 */
	public final float element41;
	
	/**
	 * The value of the element at index 13 or row 4 and column 2.
	 */
	public final float element42;
	
	/**
	 * The value of the element at index 14 or row 4 and column 3.
	 */
	public final float element43;
	
	/**
	 * The value of the element at index 15 or row 4 and column 4.
	 */
	public final float element44;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructs a new {@code Matrix44F} instance denoting the identity matrix.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new Matrix44F(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
	 * }
	 * </pre>
	 */
	public Matrix44F() {
		this(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
	}
	
	/**
	 * Constructs a new {@code Matrix44F} instance given its element values.
	 * 
	 * @param element11 the value of the element at index 0 or row 1 and column 1
	 * @param element12 the value of the element at index 1 or row 1 and column 2
	 * @param element13 the value of the element at index 2 or row 1 and column 3
	 * @param element14 the value of the element at index 3 or row 1 and column 4
	 * @param element21 the value of the element at index 4 or row 2 and column 1
	 * @param element22 the value of the element at index 5 or row 2 and column 2
	 * @param element23 the value of the element at index 6 or row 2 and column 3
	 * @param element24 the value of the element at index 7 or row 2 and column 4
	 * @param element31 the value of the element at index 8 or row 3 and column 1
	 * @param element32 the value of the element at index 9 or row 3 and column 2
	 * @param element33 the value of the element at index 10 or row 3 and column 3
	 * @param element34 the value of the element at index 11 or row 3 and column 4
	 * @param element41 the value of the element at index 12 or row 4 and column 1
	 * @param element42 the value of the element at index 13 or row 4 and column 2
	 * @param element43 the value of the element at index 14 or row 4 and column 3
	 * @param element44 the value of the element at index 15 or row 4 and column 4
	 */
	public Matrix44F(final float element11, final float element12, final float element13, final float element14, final float element21, final float element22, final float element23, final float element24, final float element31, final float element32, final float element33, final float element34, final float element41, final float element42, final float element43, final float element44) {
		this.element11 = element11;
		this.element12 = element12;
		this.element13 = element13;
		this.element14 = element14;
		this.element21 = element21;
		this.element22 = element22;
		this.element23 = element23;
		this.element24 = element24;
		this.element31 = element31;
		this.element32 = element32;
		this.element33 = element33;
		this.element34 = element34;
		this.element41 = element41;
		this.element42 = element42;
		this.element43 = element43;
		this.element44 = element44;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns a new {@code Matrix44F} instance that is the inverse of this {@code Matrix44F} instance.
	 * <p>
	 * If this {@code Matrix44F} cannot be inverted, an {@code IllegalStateException} will be thrown.
	 * <p>
	 * To make sure this {@code Matrix44F} instance is invertible, consider calling {@link #isInvertible()}.
	 * 
	 * @return a new {@code Matrix44F} instance that is the inverse of this {@code Matrix44F} instance
	 * @throws IllegalStateException thrown if, and only if, this {@code Matrix44F} cannot be inverted
	 */
	public Matrix44F inverse() {
		final float a = this.element11 * this.element22 - this.element12 * this.element21;
		final float b = this.element11 * this.element23 - this.element13 * this.element21;
		final float c = this.element11 * this.element24 - this.element14 * this.element21;
		final float d = this.element12 * this.element23 - this.element13 * this.element22;
		final float e = this.element12 * this.element24 - this.element14 * this.element22;
		final float f = this.element13 * this.element24 - this.element14 * this.element23;
		final float g = this.element31 * this.element42 - this.element32 * this.element41;
		final float h = this.element31 * this.element43 - this.element33 * this.element41;
		final float i = this.element31 * this.element44 - this.element34 * this.element41;
		final float j = this.element32 * this.element43 - this.element33 * this.element42;
		final float k = this.element32 * this.element44 - this.element34 * this.element42;
		final float l = this.element33 * this.element44 - this.element34 * this.element43;
		
		final float determinant = a * l - b * k + c * j + d * i - e * h + f * g;
		
		if(abs(determinant) < 1.0e-12F) {
			throw new IllegalStateException("This Matrix44F cannot be inverted!");
		}
		
		final float determinantReciprocal = 1.0F / determinant;
		
		final float element11 = (+this.element22 * l - this.element23 * k + this.element24 * j) * determinantReciprocal;
		final float element12 = (-this.element12 * l + this.element13 * k - this.element14 * j) * determinantReciprocal;
		final float element13 = (+this.element42 * f - this.element43 * e + this.element44 * d) * determinantReciprocal;
		final float element14 = (-this.element32 * f + this.element33 * e - this.element34 * d) * determinantReciprocal;
		final float element21 = (-this.element21 * l + this.element23 * i - this.element24 * h) * determinantReciprocal;
		final float element22 = (+this.element11 * l - this.element13 * i + this.element14 * h) * determinantReciprocal;
		final float element23 = (-this.element41 * f + this.element43 * c - this.element44 * b) * determinantReciprocal;
		final float element24 = (+this.element31 * f - this.element33 * c + this.element34 * b) * determinantReciprocal;
		final float element31 = (+this.element21 * k - this.element22 * i + this.element24 * g) * determinantReciprocal;
		final float element32 = (-this.element11 * k + this.element12 * i - this.element14 * g) * determinantReciprocal;
		final float element33 = (+this.element41 * e - this.element42 * c + this.element44 * a) * determinantReciprocal;
		final float element34 = (-this.element31 * e + this.element32 * c - this.element34 * a) * determinantReciprocal;
		final float element41 = (-this.element21 * j + this.element22 * h - this.element23 * g) * determinantReciprocal;
		final float element42 = (+this.element11 * j - this.element12 * h + this.element13 * g) * determinantReciprocal;
		final float element43 = (-this.element41 * d + this.element42 * b - this.element43 * a) * determinantReciprocal;
		final float element44 = (+this.element31 * d - this.element32 * b + this.element33 * a) * determinantReciprocal;
		
		return new Matrix44F(element11, element12, element13, element14, element21, element22, element23, element24, element31, element32, element33, element34, element41, element42, element43, element44);
	}
	
	/**
	 * Multiplies the element values of this {@code Matrix44F} instance with the element values of {@code m}.
	 * <p>
	 * Returns a new {@code Matrix44F} instance with the result of the multiplication.
	 * <p>
	 * If {@code m} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param m the {@code Matrix44F} instance to multiply this {@code Matrix44F} instance with
	 * @return a new {@code Matrix44F} instance with the result of the multiplication
	 * @throws NullPointerException thrown if, and only if, {@code m} is {@code null}
	 */
	public Matrix44F multiply(final Matrix44F m) {
		final float element11 = this.element11 * m.element11 + this.element12 * m.element21 + this.element13 * m.element31 + this.element14 * m.element41;
		final float element12 = this.element11 * m.element12 + this.element12 * m.element22 + this.element13 * m.element32 + this.element14 * m.element42;
		final float element13 = this.element11 * m.element13 + this.element12 * m.element23 + this.element13 * m.element33 + this.element14 * m.element43;
		final float element14 = this.element11 * m.element14 + this.element12 * m.element24 + this.element13 * m.element34 + this.element14 * m.element44;
		final float element21 = this.element21 * m.element11 + this.element22 * m.element21 + this.element23 * m.element31 + this.element24 * m.element41;
		final float element22 = this.element21 * m.element12 + this.element22 * m.element22 + this.element23 * m.element32 + this.element24 * m.element42;
		final float element23 = this.element21 * m.element13 + this.element22 * m.element23 + this.element23 * m.element33 + this.element24 * m.element43;
		final float element24 = this.element21 * m.element14 + this.element22 * m.element24 + this.element23 * m.element34 + this.element24 * m.element44;
		final float element31 = this.element31 * m.element11 + this.element32 * m.element21 + this.element33 * m.element31 + this.element34 * m.element41;
		final float element32 = this.element31 * m.element12 + this.element32 * m.element22 + this.element33 * m.element32 + this.element34 * m.element42;
		final float element33 = this.element31 * m.element13 + this.element32 * m.element23 + this.element33 * m.element33 + this.element34 * m.element43;
		final float element34 = this.element31 * m.element14 + this.element32 * m.element24 + this.element33 * m.element34 + this.element34 * m.element44;
		final float element41 = this.element41 * m.element11 + this.element42 * m.element21 + this.element43 * m.element31 + this.element44 * m.element41;
		final float element42 = this.element41 * m.element12 + this.element42 * m.element22 + this.element43 * m.element32 + this.element44 * m.element42;
		final float element43 = this.element41 * m.element13 + this.element42 * m.element23 + this.element43 * m.element33 + this.element44 * m.element43;
		final float element44 = this.element41 * m.element14 + this.element42 * m.element24 + this.element43 * m.element34 + this.element44 * m.element44;
		
		return new Matrix44F(element11, element12, element13, element14, element21, element22, element23, element24, element31, element32, element33, element34, element41, element42, element43, element44);
	}
	
	/**
	 * Returns a new {@code Matrix44F} instance that is the transpose of this {@code Matrix44F} instance.
	 * 
	 * @return a new {@code Matrix44F} instance that is the transpose of this {@code Matrix44F} instance
	 */
	public Matrix44F transpose() {
		return new Matrix44F(this.element11, this.element21, this.element31, this.element41, this.element12, this.element22, this.element32, this.element42, this.element13, this.element23, this.element33, this.element43, this.element14, this.element24, this.element34, this.element44);
	}
	
	/**
	 * Returns a {@code String} representation of this {@code Matrix44F} instance.
	 * 
	 * @return a {@code String} representation of this {@code Matrix44F} instance
	 */
	@Override
	public String toString() {
		final String element11 = Float.toString(this.element11);
		final String element12 = Float.toString(this.element12);
		final String element13 = Float.toString(this.element13);
		final String element14 = Float.toString(this.element14);
		final String element21 = Float.toString(this.element21);
		final String element22 = Float.toString(this.element22);
		final String element23 = Float.toString(this.element23);
		final String element24 = Float.toString(this.element24);
		final String element31 = Float.toString(this.element31);
		final String element32 = Float.toString(this.element32);
		final String element33 = Float.toString(this.element33);
		final String element34 = Float.toString(this.element34);
		final String element41 = Float.toString(this.element41);
		final String element42 = Float.toString(this.element42);
		final String element43 = Float.toString(this.element43);
		final String element44 = Float.toString(this.element44);
		
		return String.format("new Matrix44F(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)", element11, element12, element13, element14, element21, element22, element23, element24, element31, element32, element33, element34, element41, element42, element43, element44);
	}
	
	/**
	 * Compares {@code object} to this {@code Matrix44F} instance for equality.
	 * <p>
	 * Returns {@code true} if, and only if, {@code object} is an instance of {@code Matrix44F}, and their respective values are equal, {@code false} otherwise.
	 * 
	 * @param object the {@code Object} to compare to this {@code Matrix44F} instance for equality
	 * @return {@code true} if, and only if, {@code object} is an instance of {@code Matrix44F}, and their respective values are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object object) {
		if(object == this) {
			return true;
		} else if(!(object instanceof Matrix44F)) {
			return false;
		} else if(!MathF.equals(this.element11, Matrix44F.class.cast(object).element11)) {
			return false;
		} else if(!MathF.equals(this.element12, Matrix44F.class.cast(object).element12)) {
			return false;
		} else if(!MathF.equals(this.element13, Matrix44F.class.cast(object).element13)) {
			return false;
		} else if(!MathF.equals(this.element14, Matrix44F.class.cast(object).element14)) {
			return false;
		} else if(!MathF.equals(this.element21, Matrix44F.class.cast(object).element21)) {
			return false;
		} else if(!MathF.equals(this.element22, Matrix44F.class.cast(object).element22)) {
			return false;
		} else if(!MathF.equals(this.element23, Matrix44F.class.cast(object).element23)) {
			return false;
		} else if(!MathF.equals(this.element24, Matrix44F.class.cast(object).element24)) {
			return false;
		} else if(!MathF.equals(this.element31, Matrix44F.class.cast(object).element31)) {
			return false;
		} else if(!MathF.equals(this.element32, Matrix44F.class.cast(object).element32)) {
			return false;
		} else if(!MathF.equals(this.element33, Matrix44F.class.cast(object).element33)) {
			return false;
		} else if(!MathF.equals(this.element34, Matrix44F.class.cast(object).element34)) {
			return false;
		} else if(!MathF.equals(this.element41, Matrix44F.class.cast(object).element41)) {
			return false;
		} else if(!MathF.equals(this.element42, Matrix44F.class.cast(object).element42)) {
			return false;
		} else if(!MathF.equals(this.element43, Matrix44F.class.cast(object).element43)) {
			return false;
		} else if(!MathF.equals(this.element44, Matrix44F.class.cast(object).element44)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returns {@code true} if, and only if, this {@code Matrix44F} instance is the identity matrix, {@code false} otherwise.
	 * 
	 * @return {@code true} if, and only if, this {@code Matrix44F} instance is the identity matrix, {@code false} otherwise
	 */
	public boolean isIdentity() {
		final boolean isIdentityRow1 = MathF.equals(this.element11, 1.0F) && MathF.equals(this.element12, 0.0F) && MathF.equals(this.element13, 0.0F) && MathF.equals(this.element14, 0.0F);
		final boolean isIdentityRow2 = MathF.equals(this.element21, 0.0F) && MathF.equals(this.element22, 1.0F) && MathF.equals(this.element23, 0.0F) && MathF.equals(this.element24, 0.0F);
		final boolean isIdentityRow3 = MathF.equals(this.element31, 0.0F) && MathF.equals(this.element32, 0.0F) && MathF.equals(this.element33, 1.0F) && MathF.equals(this.element34, 0.0F);
		final boolean isIdentityRow4 = MathF.equals(this.element41, 0.0F) && MathF.equals(this.element42, 0.0F) && MathF.equals(this.element43, 0.0F) && MathF.equals(this.element44, 1.0F);
		
		return isIdentityRow1 && isIdentityRow2 && isIdentityRow3 && isIdentityRow4;
	}
	
	/**
	 * Returns {@code true} if, and only if, this {@code Matrix44F} instance is invertible, {@code false} otherwise.
	 * 
	 * @return {@code true} if, and only if, this {@code Matrix44F} instance is invertible, {@code false} otherwise
	 */
	public boolean isInvertible() {
		return abs(determinant()) >= 1.0e-12F;
	}
	
	/**
	 * Returns the determinant of this {@code Matrix44F} instance.
	 * 
	 * @return the determinant of this {@code Matrix44F} instance
	 */
	public float determinant() {
		final float a = this.element11 * this.element22 - this.element12 * this.element21;
		final float b = this.element11 * this.element23 - this.element13 * this.element21;
		final float c = this.element11 * this.element24 - this.element14 * this.element21;
		final float d = this.element12 * this.element23 - this.element13 * this.element22;
		final float e = this.element12 * this.element24 - this.element14 * this.element22;
		final float f = this.element13 * this.element24 - this.element14 * this.element23;
		final float g = this.element31 * this.element42 - this.element32 * this.element41;
		final float h = this.element31 * this.element43 - this.element33 * this.element41;
		final float i = this.element31 * this.element44 - this.element34 * this.element41;
		final float j = this.element32 * this.element43 - this.element33 * this.element42;
		final float k = this.element32 * this.element44 - this.element34 * this.element42;
		final float l = this.element33 * this.element44 - this.element34 * this.element43;
		
		return a * l - b * k + c * j + d * i - e * h + f * g;
	}
	
	/**
	 * Returns the value of the element at index {@code index}.
	 * <p>
	 * If {@code index} is less than {@code 0} or greater than {@code 15}, an {@code IllegalArgumentException} will be thrown.
	 * 
	 * @param index the index of the element whose value to return
	 * @return the value of the element at index {@code index}
	 * @throws IllegalArgumentException thrown if, and only if, {@code index} is less than {@code 0} or greater than {@code 15}
	 */
	public float getElement(final int index) {
		switch(index) {
			case 0:
				return this.element11;
			case 1:
				return this.element12;
			case 2:
				return this.element13;
			case 3:
				return this.element14;
			case 4:
				return this.element21;
			case 5:
				return this.element22;
			case 6:
				return this.element23;
			case 7:
				return this.element24;
			case 8:
				return this.element31;
			case 9:
				return this.element32;
			case 10:
				return this.element33;
			case 11:
				return this.element34;
			case 12:
				return this.element41;
			case 13:
				return this.element42;
			case 14:
				return this.element43;
			case 15:
				return this.element44;
			default:
				throw new IllegalArgumentException(String.format("Illegal index: index=%s", Integer.toString(index)));
		}
	}
	
	/**
	 * Returns the value of the element at row {@code row} and column {@code column}.
	 * <p>
	 * If either {@code row} or {@code column} are less than {@code 1} or greater than {@code 4}, an {@code IllegalArgumentException} will be thrown.
	 * <p>
	 * <strong>Note:</strong> Both {@code row} and {@code column} starts at {@code 1}, rather than {@code 0}, as is sometimes the case.
	 * 
	 * @param row the row of the element whose value to return
	 * @param column the column of the element whose value to return
	 * @return the value of the element at row {@code row} and column {@code column}
	 */
	public float getElement(final int row, final int column) {
		if(row < 1 || row > 4) {
			throw new IllegalArgumentException(String.format("Illegal row: row=%s", Integer.toString(row)));
		}
		
		if(column < 1 || column > 4) {
			throw new IllegalArgumentException(String.format("Illegal column: column=%s", Integer.toString(column)));
		}
		
		final int index = (row - 1) * 4 + (column - 1);
		
		return getElement(index);
	}
	
	/**
	 * Returns the value of the element at index 0 or row 1 and column 1.
	 * 
	 * @return the value of the element at index 0 or row 1 and column 1
	 */
	public float getElement11() {
		return this.element11;
	}
	
	/**
	 * Returns the value of the element at index 1 or row 1 and column 2.
	 * 
	 * @return the value of the element at index 1 or row 1 and column 2
	 */
	public float getElement12() {
		return this.element12;
	}
	
	/**
	 * Returns the value of the element at index 2 or row 1 and column 3.
	 * 
	 * @return the value of the element at index 2 or row 1 and column 3
	 */
	public float getElement13() {
		return this.element13;
	}
	
	/**
	 * Returns the value of the element at index 3 or row 1 and column 4.
	 * 
	 * @return the value of the element at index 3 or row 1 and column 4
	 */
	public float getElement14() {
		return this.element14;
	}
	
	/**
	 * Returns the value of the element at index 4 or row 2 and column 1.
	 * 
	 * @return the value of the element at index 4 or row 2 and column 1
	 */
	public float getElement21() {
		return this.element21;
	}
	
	/**
	 * Returns the value of the element at index 5 or row 2 and column 2.
	 * 
	 * @return the value of the element at index 5 or row 2 and column 2
	 */
	public float getElement22() {
		return this.element22;
	}
	
	/**
	 * Returns the value of the element at index 6 or row 2 and column 3.
	 * 
	 * @return the value of the element at index 6 or row 2 and column 3
	 */
	public float getElement23() {
		return this.element23;
	}
	
	/**
	 * Returns the value of the element at index 7 or row 2 and column 4.
	 * 
	 * @return the value of the element at index 7 or row 2 and column 4
	 */
	public float getElement24() {
		return this.element24;
	}
	
	/**
	 * Returns the value of the element at index 8 or row 3 and column 1.
	 * 
	 * @return the value of the element at index 8 or row 3 and column 1
	 */
	public float getElement31() {
		return this.element31;
	}
	
	/**
	 * Returns the value of the element at index 9 or row 3 and column 2.
	 * 
	 * @return the value of the element at index 9 or row 3 and column 2
	 */
	public float getElement32() {
		return this.element32;
	}
	
	/**
	 * Returns the value of the element at index 10 or row 3 and column 3.
	 * 
	 * @return the value of the element at index 10 or row 3 and column 3
	 */
	public float getElement33() {
		return this.element33;
	}
	
	/**
	 * Returns the value of the element at index 11 or row 3 and column 4.
	 * 
	 * @return the value of the element at index 11 or row 3 and column 4
	 */
	public float getElement34() {
		return this.element34;
	}
	
	/**
	 * Returns the value of the element at index 12 or row 4 and column 1.
	 * 
	 * @return the value of the element at index 12 or row 4 and column 1
	 */
	public float getElement41() {
		return this.element41;
	}
	
	/**
	 * Returns the value of the element at index 13 or row 4 and column 2.
	 * 
	 * @return the value of the element at index 13 or row 4 and column 2
	 */
	public float getElement42() {
		return this.element42;
	}
	
	/**
	 * Returns the value of the element at index 14 or row 4 and column 3.
	 * 
	 * @return the value of the element at index 14 or row 4 and column 3
	 */
	public float getElement43() {
		return this.element43;
	}
	
	/**
	 * Returns the value of the element at index 15 or row 4 and column 4.
	 * 
	 * @return the value of the element at index 15 or row 4 and column 4
	 */
	public float getElement44() {
		return this.element44;
	}
	
	/**
	 * Returns a hash code for this {@code Matrix44F} instance.
	 * 
	 * @return a hash code for this {@code Matrix44F} instance
	 */
	@Override
	public int hashCode() {
		final Float element11 = Float.valueOf(this.element11);
		final Float element12 = Float.valueOf(this.element12);
		final Float element13 = Float.valueOf(this.element13);
		final Float element14 = Float.valueOf(this.element14);
		final Float element21 = Float.valueOf(this.element21);
		final Float element22 = Float.valueOf(this.element22);
		final Float element23 = Float.valueOf(this.element23);
		final Float element24 = Float.valueOf(this.element24);
		final Float element31 = Float.valueOf(this.element31);
		final Float element32 = Float.valueOf(this.element32);
		final Float element33 = Float.valueOf(this.element33);
		final Float element34 = Float.valueOf(this.element34);
		final Float element41 = Float.valueOf(this.element41);
		final Float element42 = Float.valueOf(this.element42);
		final Float element43 = Float.valueOf(this.element43);
		final Float element44 = Float.valueOf(this.element44);
		
		return Objects.hash(element11, element12, element13, element14, element21, element22, element23, element24, element31, element32, element33, element34, element41, element42, element43, element44);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns a new {@code Matrix44F} instance from an {@link OrthoNormalBasis33F} instance.
	 * <p>
	 * If {@code orthoNormalBasis} is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * The layout looks like this:
	 * <pre>
	 * {@code
	 * u.x, v.x, w.x, 0
	 * u.y, v.y, w.y, 0
	 * u.z, v.z, w.z, 0
	 *   0,   0,   0, 1
	 * }
	 * </pre>
	 * 
	 * @param orthoNormalBasis an {@code OrthoNormalBasis33F} instance
	 * @return a new {@code Matrix44F} instance from an {@code OrthoNormalBasis33F} instance
	 * @throws NullPointerException thrown if, and only if, {@code orthoNormalBasis} is {@code null}
	 */
	public static Matrix44F fromOrthoNormalBasis(final OrthoNormalBasis33F orthoNormalBasis) {
		final Vector3F u = Vector3F.x().transform(orthoNormalBasis);
		final Vector3F v = Vector3F.y().transform(orthoNormalBasis);
		final Vector3F w = Vector3F.z().transform(orthoNormalBasis);
		
		return new Matrix44F(u.x, v.x, w.x, 0.0F, u.y, v.y, w.y, 0.0F, u.z, v.z, w.z, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
	}
	
	/**
	 * Returns a {@code Matrix44F} instance that is a rotation matrix.
	 * <p>
	 * If {@code q} is {@code null}, a {@code NullPointerException} will be thrown.
	 * 
	 * @param q a {@code QuaternionF} instance
	 * @return a {@code Matrix44F} instance that is a rotation matrix
	 * @throws NullPointerException thrown if, and only if, {@code q} is {@code null}
	 */
	public static Matrix44F rotation(final QuaternionF q) {
		final Vector3F w = new Vector3F(2.0F * (q.x * q.z - q.w * q.y), 2.0F * (q.y * q.z + q.w * q.x), 1.0F - 2.0F * (q.x * q.x + q.y * q.y));
		final Vector3F v = new Vector3F(2.0F * (q.x * q.y + q.w * q.z), 1.0F - 2.0F * (q.x * q.x + q.z * q.z), 2.0F * (q.y * q.z - q.w * q.x));
		final Vector3F u = new Vector3F(1.0F - 2.0F * (q.y * q.y + q.z * q.z), 2.0F * (q.x * q.y - q.w * q.z), 2.0F * (q.x * q.z + q.w * q.y));
		
		return rotation(w, v, u);
	}
	
	/**
	 * Returns a {@code Matrix44F} instance that is a rotation matrix.
	 * <p>
	 * If either {@code w} or {@code v} are {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * The layout looks like this:
	 * <pre>
	 * {@code
	 * u.x, u.y, u.z, 0
	 * v.x, v.y, v.z, 0
	 * w.x, w.y, w.z, 0
	 *   0,   0,   0, 1
	 * }
	 * </pre>
	 * <p>
	 * <strong>Note:</strong> The layout above does not use the {@link Vector3F} instances {@code w} and {@code v} supplied as arguments to this method directly. They are only used as a starting point to construct an OrthoNormal Basis (ONB).
	 * 
	 * @param w a {@code Vector3F} instance
	 * @param v a {@code Vector3F} instance
	 * @return a {@code Matrix44F} instance that is a rotation matrix
	 * @throws NullPointerException thrown if, and only if, either {@code w} or {@code v} are {@code null}
	 */
	public static Matrix44F rotation(final Vector3F w, final Vector3F v) {
		final Vector3F orthoNormalBasisW = w.normalize();
		final Vector3F orthoNormalBasisU = v.normalize().crossProduct(orthoNormalBasisW);
		final Vector3F orthoNormalBasisV = orthoNormalBasisW.crossProduct(orthoNormalBasisU);
		
		return rotation(orthoNormalBasisW, orthoNormalBasisV, orthoNormalBasisU);
	}
	
	/**
	 * Returns a {@code Matrix44F} instance that is a rotation matrix.
	 * <p>
	 * If either {@code w}, {@code v} or {@code u} are {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * The layout looks like this:
	 * <pre>
	 * {@code
	 * u.x, u.y, u.z, 0
	 * v.x, v.y, v.z, 0
	 * w.x, w.y, w.z, 0
	 *   0,   0,   0, 1
	 * }
	 * </pre>
	 * <p>
	 * <strong>Note:</strong> The {@link Vector3F} instances {@code w}, {@code v} and {@code u} should be normalized. This method does not normalize them.
	 * 
	 * @param w a {@code Vector3F} instance
	 * @param v a {@code Vector3F} instance
	 * @param u a {@code Vector3F} instance
	 * @return a {@code Matrix44F} instance that is a rotation matrix
	 * @throws NullPointerException thrown if, and only if, either {@code w}, {@code v} or {@code u} are {@code null}
	 */
	public static Matrix44F rotation(final Vector3F w, final Vector3F v, final Vector3F u) {
		return new Matrix44F(u.x, u.y, u.z, 0.0F, v.x, v.y, v.z, 0.0F, w.x, w.y, w.z, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
	}
	
	/**
	 * Returns a {@code Matrix44F} instance that is a rotation matrix along the X-axis.
	 * <p>
	 * If {@code angle}, is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * The layout looks like this:
	 * <pre>
	 * {@code
	 * 1,   0,    0, 0
	 * 0, cos, -sin, 0
	 * 0, sin,  cos, 0
	 * 0,   0,    0, 1
	 * }
	 * </pre>
	 * 
	 * @param angle an {@code AngleF} instance
	 * @return a {@code Matrix44F} instance that is a rotation matrix along the X-axis
	 * @throws NullPointerException thrown if, and only if, {@code angle} is {@code null}
	 */
	public static Matrix44F rotationX(final AngleF angle) {
		return new Matrix44F(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, cos(angle.radians), -sin(angle.radians), 0.0F, 0.0F, sin(angle.radians), cos(angle.radians), 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
	}
	
	/**
	 * Returns a {@code Matrix44F} instance that is a rotation matrix along the Y-axis.
	 * <p>
	 * If {@code angle}, is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * The layout looks like this:
	 * <pre>
	 * {@code
	 *  cos, 0, sin, 0
	 *    0, 1,   0, 0
	 * -sin, 0, cos, 0
	 *    0, 0,   0, 1
	 * }
	 * </pre>
	 * 
	 * @param angle an {@code AngleF} instance
	 * @return a {@code Matrix44F} instance that is a rotation matrix along the Y-axis
	 * @throws NullPointerException thrown if, and only if, {@code angle} is {@code null}
	 */
	public static Matrix44F rotationY(final AngleF angle) {
		return new Matrix44F(cos(angle.radians), 0.0F, sin(angle.radians), 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, -sin(angle.radians), 0.0F, cos(angle.radians), 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
	}
	
	/**
	 * Returns a {@code Matrix44F} instance that is a rotation matrix along the Z-axis.
	 * <p>
	 * If {@code angle}, is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * The layout looks like this:
	 * <pre>
	 * {@code
	 * cos, -sin, 0, 0
	 * sin,  cos, 0, 0
	 *   0,    0, 1, 0
	 *   0,    0, 0, 1
	 * }
	 * </pre>
	 * 
	 * @param angle an {@code AngleF} instance
	 * @return a {@code Matrix44F} instance that is a rotation matrix along the Z-axis
	 * @throws NullPointerException thrown if, and only if, {@code angle} is {@code null}
	 */
	public static Matrix44F rotationZ(final AngleF angle) {
		return new Matrix44F(cos(angle.radians), -sin(angle.radians), 0.0F, 0.0F, sin(angle.radians), cos(angle.radians), 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
	}
	
	/**
	 * Returns a {@code Matrix44F} instance that is a scaling matrix.
	 * <p>
	 * The layout looks like this:
	 * <pre>
	 * {@code
	 * x, 0, 0, 0
	 * 0, y, 0, 0
	 * 0, 0, z, 0
	 * 0, 0, 0, 1
	 * }
	 * </pre>
	 * 
	 * @param x the scale factor along the X-axis
	 * @param y the scale factor along the Y-axis
	 * @param z the scale factor along the Z-axis
	 * @return a {@code Matrix44F} instance that is a scaling matrix
	 */
	public static Matrix44F scaling(final float x, final float y, final float z) {
		return new Matrix44F(x, 0.0F, 0.0F, 0.0F, 0.0F, y, 0.0F, 0.0F, 0.0F, 0.0F, z, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
	}
	
	/**
	 * Returns a {@code Matrix44F} instance that is a translation matrix.
	 * <p>
	 * If {@code p} is {@code null}, a {@code NullPointerException} will be thrown.
	 * <p>
	 * The layout looks like this:
	 * <pre>
	 * {@code
	 * 1, 0, 0, p.x
	 * 0, 1, 0, p.y
	 * 0, 0, 1, p.z
	 * 0, 0, 0,   1
	 * }
	 * </pre>
	 * 
	 * @param p a {@link Point3F}
	 * @return a {@code Matrix44F} instance that is a translation matrix
	 * @throws NullPointerException thrown if, and only if, {@code p} is {@code null}
	 */
	public static Matrix44F translation(final Point3F p) {
		return translation(p.x, p.y, p.z);
	}
	
	/**
	 * Returns a {@code Matrix44F} instance that is a translation matrix.
	 * <p>
	 * The layout looks like this:
	 * <pre>
	 * {@code
	 * 1, 0, 0, x
	 * 0, 1, 0, y
	 * 0, 0, 1, z
	 * 0, 0, 0, 1
	 * }
	 * </pre>
	 * 
	 * @param x the translation factor along the X-axis
	 * @param y the translation factor along the Y-axis
	 * @param z the translation factor along the Z-axis
	 * @return a {@code Matrix44F} instance that is a translation matrix
	 */
	public static Matrix44F translation(final float x, final float y, final float z) {
		return new Matrix44F(1.0F, 0.0F, 0.0F, x, 0.0F, 1.0F, 0.0F, y, 0.0F, 0.0F, 1.0F, z, 0.0F, 0.0F, 0.0F, 1.0F);
	}
}