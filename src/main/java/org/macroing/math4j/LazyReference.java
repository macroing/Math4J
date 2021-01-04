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

import java.util.Objects;
import java.util.function.Supplier;

final class LazyReference<T> {
	private final Supplier<T> valueSupplier;
	private volatile T value;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public LazyReference(final Supplier<T> valueSupplier) {
		this.valueSupplier = Objects.requireNonNull(valueSupplier, "valueSupplier == null");
		this.value = null;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public synchronized T get() {
		if(this.value == null) {
			this.value = Objects.requireNonNull(this.valueSupplier.get(), "valueSupplier.get() == null");
		}
		
		return this.value;
	}
}