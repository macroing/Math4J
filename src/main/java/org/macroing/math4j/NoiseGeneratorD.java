/**
 * Copyright 2019 J&#246;rgen Lundgren
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

import static org.macroing.math4j.MathD.abs;
import static org.macroing.math4j.MathD.floor;
import static org.macroing.math4j.MathI.abs;

/**
 * A {@code NoiseGeneratorD} is able to generate noise using various algorithms and with a data type of {@code double}.
 * <p>
 * The currently supported algorithms are the following:
 * <ul>
 * <li>Perlin noise in 3 dimensions</li>
 * <li>Perlin fractal noise in 3 dimensions</li>
 * <li>Perlin fractional Brownian motion (fBm) noise in 3 dimensions</li>
 * <li>Perlin turbulence noise in 3 dimensions</li>
 * <li>Simplex noise in 1, 2, 3 and 4 dimensions</li>
 * <li>Simplex fractal noise in 1, 2, 3 and 4 dimensions</li>
 * <li>Simplex fractional Brownian motion (fBm) noise in 1, 2, 3 and 4 dimensions</li>
 * <li>Simplex turbulence noise in 1, 2, 3 and 4 dimensions</li>
 * </ul>
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class NoiseGeneratorD {
	private static final double SIMPLEX_F2 = 0.3660254037844386D;
	private static final double SIMPLEX_F3 = 1.0D / 3.0D;
	private static final double SIMPLEX_F4 = 0.30901699437494745D;
	private static final double SIMPLEX_G2 = 0.21132486540518713D;
	private static final double SIMPLEX_G3 = 1.0D / 6.0D;
	private static final double SIMPLEX_G4 = 0.1381966011250105D;
	private static final double[] SIMPLEX_GRADIENT_3 = doCreateSimplexGradient3();
	private static final double[] SIMPLEX_GRADIENT_4 = doCreateSimplexGradient4();
	private static final int[] PERMUTATIONS_B = doCreatePermutationsB();
	private static final int[] PERMUTATIONS_B_MODULO_12 = doCreatePermutationsBModulo12();
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private final double amplitude;
	private final double frequency;
	private final double gain;
	private final double lacunarity;
	private final int octaves;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructs a new {@code NoiseGeneratorD} instance.
	 * <p>
	 * Calling this constructor is equivalent to the following:
	 * <pre>
	 * {@code
	 * new NoiseGeneratorD(0.5D, 0.2D, 1.0D / 2.0D, 2.0D, 2);
	 * }
	 * </pre>
	 */
	public NoiseGeneratorD() {
		this(0.5D, 0.2D, 1.0D / 2.0D, 2.0D, 2);
	}
	
	/**
	 * Constructs a new {@code NoiseGeneratorD} instance.
	 * 
	 * @param amplitude the amplitude to use
	 * @param frequency the frequency to use
	 * @param gain the gain to use
	 * @param lacunarity the lacunarity to use
	 * @param octaves the octaves to use
	 */
	public NoiseGeneratorD(final double amplitude, final double frequency, final double gain, final double lacunarity, final int octaves) {
		this.amplitude = amplitude;
		this.frequency = frequency;
		this.gain = gain;
		this.lacunarity = lacunarity;
		this.octaves = octaves;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns the amplitude.
	 * 
	 * @return the amplitude
	 */
	public double getAmplitude() {
		return this.amplitude;
	}
	
	/**
	 * Returns the frequency.
	 * 
	 * @return the frequency
	 */
	public double getFrequency() {
		return this.frequency;
	}
	
	/**
	 * Returns the gain.
	 * 
	 * @return the gain
	 */
	public double getGain() {
		return this.gain;
	}
	
	/**
	 * Returns the lacunarity.
	 * 
	 * @return the lacunarity
	 */
	public double getLacunarity() {
		return this.lacunarity;
	}
	
	/**
	 * Returns a {@code double} with noise computed by a Perlin-based fractal algorithm using the coordinates X, Y and Z.
	 * 
	 * @param x the X-coordinate
	 * @param y the Y-coordinate
	 * @param z the Z-coordinate
	 * @return a {@code double} with noise computed by a Perlin-based fractal algorithm using the coordinates X, Y and Z
	 */
	public double perlinFractal(final double x, final double y, final double z) {
		return perlinFractal(x, y, z, getAmplitude(), getFrequency(), getGain(), getLacunarity(), getOctaves());
	}
	
	/**
	 * Returns a {@code double} with noise computed by a Perlin-based turbulence algorithm using the coordinates X, Y and Z.
	 * 
	 * @param x the X-coordinate
	 * @param y the Y-coordinate
	 * @param z the Z-coordinate
	 * @return a {@code double} with noise computed by a Perlin-based turbulence algorithm using the coordinates X, Y and Z
	 */
	public double perlinTurbulence(final double x, final double y, final double z) {
		return perlinTurbulence(x, y, z, getAmplitude(), getFrequency(), getGain(), getLacunarity(), getOctaves());
	}
	
	/**
	 * Returns a {@code double} with noise computed by a Simplex-based fractal algorithm using the coordinate X.
	 * 
	 * @param x the X-coordinate
	 * @return a {@code double} with noise computed by a Simplex-based fractal algorithm using the coordinate X
	 */
	public double simplexFractal(final double x) {
		return simplexFractal(x, getAmplitude(), getFrequency(), getGain(), getLacunarity(), getOctaves());
	}
	
	/**
	 * Returns a {@code double} with noise computed by a Simplex-based fractal algorithm using the coordinates X and Y.
	 * 
	 * @param x the X-coordinate
	 * @param y the Y-coordinate
	 * @return a {@code double} with noise computed by a Simplex-based fractal algorithm using the coordinates X and Y
	 */
	public double simplexFractal(final double x, final double y) {
		return simplexFractal(x, y, getAmplitude(), getFrequency(), getGain(), getLacunarity(), getOctaves());
	}
	
	/**
	 * Returns a {@code double} with noise computed by a Simplex-based fractal algorithm using the coordinates X, Y and Z.
	 * 
	 * @param x the X-coordinate
	 * @param y the Y-coordinate
	 * @param z the Z-coordinate
	 * @return a {@code double} with noise computed by a Simplex-based fractal algorithm using the coordinates X, Y and Z
	 */
	public double simplexFractal(final double x, final double y, final double z) {
		return simplexFractal(x, y, z, getAmplitude(), getFrequency(), getGain(), getLacunarity(), getOctaves());
	}
	
	/**
	 * Returns a {@code double} with noise computed by a Simplex-based fractal algorithm using the coordinates X, Y, Z and W.
	 * 
	 * @param x the X-coordinate
	 * @param y the Y-coordinate
	 * @param z the Z-coordinate
	 * @param w the W-coordinate
	 * @return a {@code double} with noise computed by a Simplex-based fractal algorithm using the coordinates X, Y, Z and W
	 */
	public double simplexFractal(final double x, final double y, final double z, final double w) {
		return simplexFractal(x, y, z, w, getAmplitude(), getFrequency(), getGain(), getLacunarity(), getOctaves());
	}
	
	/**
	 * Returns a {@code double} with noise computed by a Simplex-based turbulence algorithm using the coordinate X.
	 * 
	 * @param x the X-coordinate
	 * @return a {@code double} with noise computed by a Simplex-based turbulence algorithm using the coordinate X
	 */
	public double simplexTurbulence(final double x) {
		return simplexTurbulence(x, getAmplitude(), getFrequency(), getGain(), getLacunarity(), getOctaves());
	}
	
	/**
	 * Returns a {@code double} with noise computed by a Simplex-based turbulence algorithm using the coordinates X and Y.
	 * 
	 * @param x the X-coordinate
	 * @param y the Y-coordinate
	 * @return a {@code double} with noise computed by a Simplex-based turbulence algorithm using the coordinates X and Y
	 */
	public double simplexTurbulence(final double x, final double y) {
		return simplexTurbulence(x, y, getAmplitude(), getFrequency(), getGain(), getLacunarity(), getOctaves());
	}
	
	/**
	 * Returns a {@code double} with noise computed by a Simplex-based turbulence algorithm using the coordinates X, Y and Z.
	 * 
	 * @param x the X-coordinate
	 * @param y the Y-coordinate
	 * @param z the Z-coordinate
	 * @return a {@code double} with noise computed by a Simplex-based turbulence algorithm using the coordinates X, Y and Z
	 */
	public double simplexTurbulence(final double x, final double y, final double z) {
		return simplexTurbulence(x, y, z, getAmplitude(), getFrequency(), getGain(), getLacunarity(), getOctaves());
	}
	
	/**
	 * Returns a {@code double} with noise computed by a Simplex-based turbulence algorithm using the coordinates X, Y, Z and W.
	 * 
	 * @param x the X-coordinate
	 * @param y the Y-coordinate
	 * @param z the Z-coordinate
	 * @param w the W-coordinate
	 * @return a {@code double} with noise computed by a Simplex-based turbulence algorithm using the coordinates X, Y, Z and W
	 */
	public double simplexTurbulence(final double x, final double y, final double z, final double w) {
		return simplexTurbulence(x, y, z, w, getAmplitude(), getFrequency(), getGain(), getLacunarity(), getOctaves());
	}
	
	/**
	 * Returns the octaves.
	 * 
	 * @return the octaves
	 */
	public int getOctaves() {
		return this.octaves;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns a {@code double} with noise computed by a Perlin-based fractal algorithm using the coordinates X, Y and Z.
	 * 
	 * @param x the X-coordinate
	 * @param y the Y-coordinate
	 * @param z the Z-coordinate
	 * @param amplitude the amplitude to start at
	 * @param frequency the frequency to start at
	 * @param gain the amplitude multiplier
	 * @param lacunarity the frequency multiplier
	 * @param octaves the number of iterations to perform
	 * @return a {@code double} with noise computed by a Perlin-based fractal algorithm using the coordinates X, Y and Z
	 */
	public static double perlinFractal(final double x, final double y, final double z, final double amplitude, final double frequency, final double gain, final double lacunarity, final int octaves) {
		double result = 0.0D;
		
		double currentAmplitude = amplitude;
		double currentFrequency = frequency;
		
		for(int i = 0; i < octaves; i++) {
			result += currentAmplitude * perlinNoise(x * currentFrequency, y * currentFrequency, z * currentFrequency);
			
			currentAmplitude *= gain;
			currentFrequency *= lacunarity;
		}
		
		return result;
	}
	
	/**
	 * Returns a {@code double} with noise computed by a Perlin-based fractional Brownian motion (fBm) algorithm using the coordinates X, Y and Z.
	 * 
	 * @param x the X-coordinate
	 * @param y the Y-coordinate
	 * @param z the Z-coordinate
	 * @param frequency the frequency to start at
	 * @param gain the amplitude multiplier
	 * @param minimum the minimum value to return
	 * @param maximum the maximum value to return
	 * @param octaves the number of iterations to perform
	 * @return a {@code double} with noise computed by a Perlin-based fractional Brownian motion (fBm) algorithm using the coordinates X, Y and Z
	 */
	public static double perlinFractionalBrownianMotion(final double x, final double y, final double z, final double frequency, final double gain, final double minimum, final double maximum, final int octaves) {
		double currentAmplitude = 1.0D;
		double maximumAmplitude = 0.0D;
		
		double currentFrequency = frequency;
		
		double noise = 0.0D;
		
		for(int i = 0; i < octaves; i++) {
			noise += perlinNoise(x * currentFrequency, y * currentFrequency, z * currentFrequency) * currentAmplitude;
			
			maximumAmplitude += currentAmplitude;
			currentAmplitude *= gain;
			
			currentFrequency *= 2.0D;
		}
		
		noise /= maximumAmplitude;
		noise = noise * (maximum - minimum) / 2.0D + (maximum + minimum) / 2.0D;
		
		return noise;
	}
	
	/**
	 * Returns a {@code double} with noise computed by the Perlin algorithm using the coordinates X, Y and Z.
	 * 
	 * @param x the X-coordinate
	 * @param y the Y-coordinate
	 * @param z the Z-coordinate
	 * @return a {@code double} with noise computed by the Perlin algorithm using the coordinates X, Y and Z
	 */
	public static double perlinNoise(final double x, final double y, final double z) {
		final double floorX = floor(x);
		final double floorY = floor(y);
		final double floorZ = floor(z);
		
		final int x0 = (int)(floorX) & 0xFF;
		final int y0 = (int)(floorY) & 0xFF;
		final int z0 = (int)(floorZ) & 0xFF;
		
		final double x1 = x - floorX;
		final double y1 = y - floorY;
		final double z1 = z - floorZ;
		
		final double u = x1 * x1 * x1 * (x1 * (x1 * 6.0D - 15.0D) + 10.0D);
		final double v = y1 * y1 * y1 * (y1 * (y1 * 6.0D - 15.0D) + 10.0D);
		final double w = z1 * z1 * z1 * (z1 * (z1 * 6.0D - 15.0D) + 10.0D);
		
		final int a0 = PERMUTATIONS_B[x0] + y0;
		final int a1 = PERMUTATIONS_B[a0] + z0;
		final int a2 = PERMUTATIONS_B[a0 + 1] + z0;
		final int b0 = PERMUTATIONS_B[x0 + 1] + y0;
		final int b1 = PERMUTATIONS_B[b0] + z0;
		final int b2 = PERMUTATIONS_B[b0 + 1] + z0;
		final int hash0 = PERMUTATIONS_B[a1] & 15;
		final int hash1 = PERMUTATIONS_B[b1] & 15;
		final int hash2 = PERMUTATIONS_B[a2] & 15;
		final int hash3 = PERMUTATIONS_B[b2] & 15;
		final int hash4 = PERMUTATIONS_B[a1 + 1] & 15;
		final int hash5 = PERMUTATIONS_B[b1 + 1] & 15;
		final int hash6 = PERMUTATIONS_B[a2 + 1] & 15;
		final int hash7 = PERMUTATIONS_B[b2 + 1] & 15;
		
		final double gradient0U = hash0 < 8 || hash0 == 12 || hash0 == 13 ? x1 : y1;
		final double gradient0V = hash0 < 4 || hash0 == 12 || hash0 == 13 ? y1 : z1;
		final double gradient0 = ((hash0 & 1) == 0 ? gradient0U : -gradient0U) + ((hash0 & 2) == 0 ? gradient0V : -gradient0V);
		final double gradient1U = hash1 < 8 || hash1 == 12 || hash1 == 13 ? x1 - 1.0D : y1;
		final double gradient1V = hash1 < 4 || hash1 == 12 || hash1 == 13 ? y1 : z1;
		final double gradient1 = ((hash1 & 1) == 0 ? gradient1U : -gradient1U) + ((hash1 & 2) == 0 ? gradient1V : -gradient1V);
		final double gradient2U = hash2 < 8 || hash2 == 12 || hash2 == 13 ? x1 : y1 - 1.0D;
		final double gradient2V = hash2 < 4 || hash2 == 12 || hash2 == 13 ? y1 - 1.0D : z1;
		final double gradient2 = ((hash2 & 1) == 0 ? gradient2U : -gradient2U) + ((hash2 & 2) == 0 ? gradient2V : -gradient2V);
		final double gradient3U = hash3 < 8 || hash3 == 12 || hash3 == 13 ? x1 - 1.0D : y1 - 1.0D;
		final double gradient3V = hash3 < 4 || hash3 == 12 || hash3 == 13 ? y1 - 1.0D : z1;
		final double gradient3 = ((hash3 & 1) == 0 ? gradient3U : -gradient3U) + ((hash3 & 2) == 0 ? gradient3V : -gradient3V);
		final double gradient4U = hash4 < 8 || hash4 == 12 || hash4 == 13 ? x1 : y1;
		final double gradient4V = hash4 < 4 || hash4 == 12 || hash4 == 13 ? y1 : z1 - 1.0D;
		final double gradient4 = ((hash4 & 1) == 0 ? gradient4U : -gradient4U) + ((hash4 & 2) == 0 ? gradient4V : -gradient4V);
		final double gradient5U = hash5 < 8 || hash5 == 12 || hash5 == 13 ? x1 - 1.0D : y1;
		final double gradient5V = hash5 < 4 || hash5 == 12 || hash5 == 13 ? y1 : z1 - 1.0D;
		final double gradient5 = ((hash5 & 1) == 0 ? gradient5U : -gradient5U) + ((hash5 & 2) == 0 ? gradient5V : -gradient5V);
		final double gradient6U = hash6 < 8 || hash6 == 12 || hash6 == 13 ? x1 : y1 - 1.0D;
		final double gradient6V = hash6 < 4 || hash6 == 12 || hash6 == 13 ? y1 - 1.0D : z1 - 1.0D;
		final double gradient6 = ((hash6 & 1) == 0 ? gradient6U : -gradient6U) + ((hash6 & 2) == 0 ? gradient6V : -gradient6V);
		final double gradient7U = hash7 < 8 || hash7 == 12 || hash7 == 13 ? x1 - 1.0D : y1 - 1.0D;
		final double gradient7V = hash7 < 4 || hash7 == 12 || hash7 == 13 ? y1 - 1.0D : z1 - 1.0D;
		final double gradient7 = ((hash7 & 1) == 0 ? gradient7U : -gradient7U) + ((hash7 & 2) == 0 ? gradient7V : -gradient7V);
		
		final double lerp0 = gradient0 + u * (gradient1 - gradient0);
		final double lerp1 = gradient2 + u * (gradient3 - gradient2);
		final double lerp2 = gradient4 + u * (gradient5 - gradient4);
		final double lerp3 = gradient6 + u * (gradient7 - gradient6);
		final double lerp4 = lerp0 + v * (lerp1 - lerp0);
		final double lerp5 = lerp2 + v * (lerp3 - lerp2);
		final double lerp6 = lerp4 + w * (lerp5 - lerp4);
		
		return lerp6;
	}
	
	/**
	 * Returns a {@code double} with noise computed by a Perlin-based turbulence algorithm using the coordinates X, Y and Z.
	 * 
	 * @param x the X-coordinate
	 * @param y the Y-coordinate
	 * @param z the Z-coordinate
	 * @param amplitude the amplitude to start a
	 * @param frequency the frequency to start at
	 * @param gain the amplitude multiplier
	 * @param lacunarity the frequency multiplier
	 * @param octaves the number of iterations to perform
	 * @return a {@code double} with noise computed by a Perlin-based turbulence algorithm using the coordinates X, Y and Z
	 */
	public static double perlinTurbulence(final double x, final double y, final double z, final double amplitude, final double frequency, final double gain, final double lacunarity, final int octaves) {
		double currentAmplitude = amplitude;
		double currentFrequency = frequency;
		
		double noise = 0.0D;
		
		for(int i = 0; i < octaves; i++) {
			noise += currentAmplitude * abs(perlinNoise(x * currentFrequency, y * currentFrequency, z * currentFrequency));
			
			currentAmplitude *= gain;
			currentFrequency *= lacunarity;
		}
		
		return noise;
	}
	
	/**
	 * Returns a {@code double} with noise computed by a Simplex-based fractal algorithm using the coordinate X.
	 * 
	 * @param x the X-coordinate
	 * @param amplitude the amplitude to start at
	 * @param frequency the frequency to start at
	 * @param gain the amplitude multiplier
	 * @param lacunarity the frequency multiplier
	 * @param octaves the number of iterations to perform
	 * @return a {@code double} with noise computed by a Simplex-based fractal algorithm using the coordinate X
	 */
	public static double simplexFractal(final double x, final double amplitude, final double frequency, final double gain, final double lacunarity, final int octaves) {
		double result = 0.0D;
		
		double currentAmplitude = amplitude;
		double currentFrequency = frequency;
		
		for(int i = 0; i < octaves; i++) {
			result += currentAmplitude * simplexNoise(x * currentFrequency);
			
			currentAmplitude *= gain;
			currentFrequency *= lacunarity;
		}
		
		return result;
	}
	
	/**
	 * Returns a {@code double} with noise computed by a Simplex-based fractal algorithm using the coordinates X and Y.
	 * 
	 * @param x the X-coordinate
	 * @param y the Y-coordinate
	 * @param amplitude the amplitude to start at
	 * @param frequency the frequency to start at
	 * @param gain the amplitude multiplier
	 * @param lacunarity the frequency multiplier
	 * @param octaves the number of iterations to perform
	 * @return a {@code double} with noise computed by a Simplex-based fractal algorithm using the coordinates X and Y
	 */
	public static double simplexFractal(final double x, final double y, final double amplitude, final double frequency, final double gain, final double lacunarity, final int octaves) {
		double result = 0.0D;
		
		double currentAmplitude = amplitude;
		double currentFrequency = frequency;
		
		for(int i = 0; i < octaves; i++) {
			result += currentAmplitude * simplexNoise(x * currentFrequency, y * currentFrequency);
			
			currentAmplitude *= gain;
			currentFrequency *= lacunarity;
		}
		
		return result;
	}
	
	/**
	 * Returns a {@code double} with noise computed by a Simplex-based fractal algorithm using the coordinates X, Y and Z.
	 * 
	 * @param x the X-coordinate
	 * @param y the Y-coordinate
	 * @param z the Z-coordinate
	 * @param amplitude the amplitude to start at
	 * @param frequency the frequency to start at
	 * @param gain the amplitude multiplier
	 * @param lacunarity the frequency multiplier
	 * @param octaves the number of iterations to perform
	 * @return a {@code double} with noise computed by a Simplex-based fractal algorithm using the coordinates X, Y and Z
	 */
	public static double simplexFractal(final double x, final double y, final double z, final double amplitude, final double frequency, final double gain, final double lacunarity, final int octaves) {
		double result = 0.0D;
		
		double currentAmplitude = amplitude;
		double currentFrequency = frequency;
		
		for(int i = 0; i < octaves; i++) {
			result += currentAmplitude * simplexNoise(x * currentFrequency, y * currentFrequency, z * currentFrequency);
			
			currentAmplitude *= gain;
			currentFrequency *= lacunarity;
		}
		
		return result;
	}
	
	/**
	 * Returns a {@code double} with noise computed by a Simplex-based fractal algorithm using the coordinates X, Y, Z and W.
	 * 
	 * @param x the X-coordinate
	 * @param y the Y-coordinate
	 * @param z the Z-coordinate
	 * @param w the W-coordinate
	 * @param amplitude the amplitude to start at
	 * @param frequency the frequency to start at
	 * @param gain the amplitude multiplier
	 * @param lacunarity the frequency multiplier
	 * @param octaves the number of iterations to perform
	 * @return a {@code double} with noise computed by a Simplex-based fractal algorithm using the coordinates X, Y, Z and W
	 */
	public static double simplexFractal(final double x, final double y, final double z, final double w, final double amplitude, final double frequency, final double gain, final double lacunarity, final int octaves) {
		double result = 0.0D;
		
		double currentAmplitude = amplitude;
		double currentFrequency = frequency;
		
		for(int i = 0; i < octaves; i++) {
			result += currentAmplitude * simplexNoise(x * currentFrequency, y * currentFrequency, z * currentFrequency, w * currentFrequency);
			
			currentAmplitude *= gain;
			currentFrequency *= lacunarity;
		}
		
		return result;
	}
	
	/**
	 * Returns a {@code double} with noise computed by a Simplex-based fractional Brownian motion (fBm) algorithm using the coordinate X.
	 * 
	 * @param x the X-coordinate
	 * @param frequency the frequency to start at
	 * @param gain the amplitude multiplier
	 * @param minimum the minimum value to return
	 * @param maximum the maximum value to return
	 * @param octaves the number of iterations to perform
	 * @return a {@code double} with noise computed by a Simplex-based fractional Brownian motion (fBm) algorithm using the coordinate X
	 */
	public static double simplexFractionalBrownianMotion(final double x, final double frequency, final double gain, final double minimum, final double maximum, final int octaves) {
		double currentAmplitude = 1.0D;
		double maximumAmplitude = 0.0D;
		
		double currentFrequency = frequency;
		
		double noise = 0.0D;
		
		for(int i = 0; i < octaves; i++) {
			noise += simplexNoise(x * currentFrequency) * currentAmplitude;
			
			maximumAmplitude += currentAmplitude;
			currentAmplitude *= gain;
			
			currentFrequency *= 2.0D;
		}
		
		noise /= maximumAmplitude;
		noise = noise * (maximum - minimum) / 2.0D + (maximum + minimum) / 2.0D;
		
		return noise;
	}
	
	/**
	 * Returns a {@code double} with noise computed by a Simplex-based fractional Brownian motion (fBm) algorithm using the coordinates X and Y.
	 * 
	 * @param x the X-coordinate
	 * @param y the Y-coordinate
	 * @param frequency the frequency to start at
	 * @param gain the amplitude multiplier
	 * @param minimum the minimum value to return
	 * @param maximum the maximum value to return
	 * @param octaves the number of iterations to perform
	 * @return a {@code double} with noise computed by a Simplex-based fractional Brownian motion (fBm) algorithm using the coordinates X and Y
	 */
	public static double simplexFractionalBrownianMotion(final double x, final double y, final double frequency, final double gain, final double minimum, final double maximum, final int octaves) {
		double currentAmplitude = 1.0D;
		double maximumAmplitude = 0.0D;
		
		double currentFrequency = frequency;
		
		double noise = 0.0D;
		
		for(int i = 0; i < octaves; i++) {
			noise += simplexNoise(x * currentFrequency, y * currentFrequency) * currentAmplitude;
			
			maximumAmplitude += currentAmplitude;
			currentAmplitude *= gain;
			
			currentFrequency *= 2.0D;
		}
		
		noise /= maximumAmplitude;
		noise = noise * (maximum - minimum) / 2.0D + (maximum + minimum) / 2.0D;
		
		return noise;
	}
	
	/**
	 * Returns a {@code double} with noise computed by a Simplex-based fractional Brownian motion (fBm) algorithm using the coordinates X, Y and Z.
	 * 
	 * @param x the X-coordinate
	 * @param y the Y-coordinate
	 * @param z the Z-coordinate
	 * @param frequency the frequency to start at
	 * @param gain the amplitude multiplier
	 * @param minimum the minimum value to return
	 * @param maximum the maximum value to return
	 * @param octaves the number of iterations to perform
	 * @return a {@code double} with noise computed by a Simplex-based fractional Brownian motion (fBm) algorithm using the coordinates X, Y and Z
	 */
	public static double simplexFractionalBrownianMotion(final double x, final double y, final double z, final double frequency, final double gain, final double minimum, final double maximum, final int octaves) {
		double currentAmplitude = 1.0D;
		double maximumAmplitude = 0.0D;
		
		double currentFrequency = frequency;
		
		double noise = 0.0D;
		
		for(int i = 0; i < octaves; i++) {
			noise += simplexNoise(x * currentFrequency, y * currentFrequency, z * currentFrequency) * currentAmplitude;
			
			maximumAmplitude += currentAmplitude;
			currentAmplitude *= gain;
			
			currentFrequency *= 2.0D;
		}
		
		noise /= maximumAmplitude;
		noise = noise * (maximum - minimum) / 2.0D + (maximum + minimum) / 2.0D;
		
		return noise;
	}
	
	/**
	 * Returns a {@code double} with noise computed by a Simplex-based fractional Brownian motion (fBm) algorithm using the coordinates X, Y, Z and W.
	 * 
	 * @param x the X-coordinate
	 * @param y the Y-coordinate
	 * @param z the Z-coordinate
	 * @param w the W-coordinate
	 * @param frequency the frequency to start at
	 * @param gain the amplitude multiplier
	 * @param minimum the minimum value to return
	 * @param maximum the maximum value to return
	 * @param octaves the number of iterations to perform
	 * @return a {@code double} with noise computed by a Simplex-based fractional Brownian motion (fBm) algorithm using the coordinates X, Y, Z and W
	 */
	public static double simplexFractionalBrownianMotion(final double x, final double y, final double z, final double w, final double frequency, final double gain, final double minimum, final double maximum, final int octaves) {
		double currentAmplitude = 1.0D;
		double maximumAmplitude = 0.0D;
		
		double currentFrequency = frequency;
		
		double noise = 0.0D;
		
		for(int i = 0; i < octaves; i++) {
			noise += simplexNoise(x * currentFrequency, y * currentFrequency, z * currentFrequency, w * currentFrequency) * currentAmplitude;
			
			maximumAmplitude += currentAmplitude;
			currentAmplitude *= gain;
			
			currentFrequency *= 2.0D;
		}
		
		noise /= maximumAmplitude;
		noise = noise * (maximum - minimum) / 2.0D + (maximum + minimum) / 2.0D;
		
		return noise;
	}
	
	/**
	 * Returns a {@code double} with noise computed by the Simplex algorithm using the coordinate X.
	 * 
	 * @param x the X-coordinate
	 * @return a {@code double} with noise computed by the Simplex algorithm using the coordinate X
	 */
	public static double simplexNoise(final double x) {
		final int i0 = doFastFloor(x);
		final int i1 = i0 + 1;
		
		final double x0 = x - i0;
		final double x1 = x0 - 1.0D;
		
		final double t00 = 1.0D - x0 * x0;
		final double t01 = t00 * t00;
		
		final double t10 = 1.0D - x1 * x1;
		final double t11 = t10 * t10;
		
		final double n0 = t01 * t01 * doGradientX(doHash(i0), x0);
		final double n1 = t11 * t11 * doGradientX(doHash(i1), x1);
		
		return 0.395D * (n0 + n1);
	}
	
	/**
	 * Returns a {@code double} with noise computed by the Simplex algorithm using the coordinates X and Y.
	 * 
	 * @param x the X-coordinate
	 * @param y the Y-coordinate
	 * @return a {@code double} with noise computed by the Simplex algorithm using the coordinates X and Y
	 */
	public static double simplexNoise(final double x, final double y) {
		final double s = (x + y) * SIMPLEX_F2;
		
		final int i = doFastFloor(x + s);
		final int j = doFastFloor(y + s);
		
		final double t = (i + j) * SIMPLEX_G2;
		
		final double x0 = x - (i - t);
		final double y0 = y - (j - t);
		
		final int i1 = x0 > y0 ? 1 : 0;
		final int j1 = x0 > y0 ? 0 : 1;
		
		final double x1 = x0 - i1 + SIMPLEX_G2;
		final double y1 = y0 - j1 + SIMPLEX_G2;
		final double x2 = x0 - 1.0D + 2.0D * SIMPLEX_G2;
		final double y2 = y0 - 1.0D + 2.0D * SIMPLEX_G2;
		
		final int ii = i & 0xFF;
		final int jj = j & 0xFF;
		
		final int gi0 = PERMUTATIONS_B_MODULO_12[ii + PERMUTATIONS_B[jj]];
		final int gi1 = PERMUTATIONS_B_MODULO_12[ii + i1 + PERMUTATIONS_B[jj + j1]];
		final int gi2 = PERMUTATIONS_B_MODULO_12[ii + 1 + PERMUTATIONS_B[jj + 1]];
		
		final double t0 = 0.5D - x0 * x0 - y0 * y0;
		final double n0 = t0 < 0.0D ? 0.0D : (t0 * t0) * (t0 * t0) * doDotXY(SIMPLEX_GRADIENT_3[gi0 * 3 + 0], SIMPLEX_GRADIENT_3[gi0 * 3 + 1], x0, y0);
		
		final double t1 = 0.5D - x1 * x1 - y1 * y1;
		final double n1 = t1 < 0.0D ? 0.0D : (t1 * t1) * (t1 * t1) * doDotXY(SIMPLEX_GRADIENT_3[gi1 * 3 + 0], SIMPLEX_GRADIENT_3[gi1 * 3 + 1], x1, y1);
		
		final double t2 = 0.5D - x2 * x2 - y2 * y2;
		final double n2 = t2 < 0.0D ? 0.0D : (t2 * t2) * (t2 * t2) * doDotXY(SIMPLEX_GRADIENT_3[gi2 * 3 + 0], SIMPLEX_GRADIENT_3[gi2 * 3 + 1], x2, y2);
		
		return 70.0D * (n0 + n1 + n2);
	}
	
	/**
	 * Returns a {@code double} with noise computed by the Simplex algorithm using the coordinates X, Y and Z.
	 * 
	 * @param x the X-coordinate
	 * @param y the Y-coordinate
	 * @param z the Z-coordinate
	 * @return a {@code double} with noise computed by the Simplex algorithm using the coordinates X, Y and Z
	 */
	public static double simplexNoise(final double x, final double y, final double z) {
		final double s = (x + y + z) * SIMPLEX_F3;
		
		final int i = doFastFloor(x + s);
		final int j = doFastFloor(y + s);
		final int k = doFastFloor(z + s);
		
		final double t = (i + j + k) * SIMPLEX_G3;
		
		final double x0 = x - (i - t);
		final double y0 = y - (j - t);
		final double z0 = z - (k - t);
		
		int i1 = 0;
		int j1 = 0;
		int k1 = 0;
		int i2 = 0;
		int j2 = 0;
		int k2 = 0;
		
		if(x0 >= y0) {
			if(y0 >= z0) {
				i1 = 1;
				j1 = 0;
				k1 = 0;
				i2 = 1;
				j2 = 1;
				k2 = 0;
			} else if(x0 >= z0) {
				i1 = 1;
				j1 = 0;
				k1 = 0;
				i2 = 1;
				j2 = 0;
				k2 = 1;
			} else {
				i1 = 0;
				j1 = 0;
				k1 = 1;
				i2 = 1;
				j2 = 0;
				k2 = 1;
			}
		} else {
			if(y0 < z0) {
				i1 = 0;
				j1 = 0;
				k1 = 1;
				i2 = 0;
				j2 = 1;
				k2 = 1;
			} else if(x0 < z0) {
				i1 = 0;
				j1 = 1;
				k1 = 0;
				i2 = 0;
				j2 = 1;
				k2 = 1;
			} else {
				i1 = 0;
				j1 = 1;
				k1 = 0;
				i2 = 1;
				j2 = 1;
				k2 = 0;
			}
		}
		
		final double x1 = x0 - i1 + SIMPLEX_G3;
		final double y1 = y0 - j1 + SIMPLEX_G3;
		final double z1 = z0 - k1 + SIMPLEX_G3;
		final double x2 = x0 - i2 + 2.0D * SIMPLEX_G3;
		final double y2 = y0 - j2 + 2.0D * SIMPLEX_G3;
		final double z2 = z0 - k2 + 2.0D * SIMPLEX_G3;
		final double x3 = x0 - 1.0D + 3.0D * SIMPLEX_G3;
		final double y3 = y0 - 1.0D + 3.0D * SIMPLEX_G3;
		final double z3 = z0 - 1.0D + 3.0D * SIMPLEX_G3;
		
		final int ii = i & 0xFF;
		final int jj = j & 0xFF;
		final int kk = k & 0xFF;
		
		final int gi0 = PERMUTATIONS_B_MODULO_12[ii + PERMUTATIONS_B[jj + PERMUTATIONS_B[kk]]];
		final int gi1 = PERMUTATIONS_B_MODULO_12[ii + i1 + PERMUTATIONS_B[jj + j1 + PERMUTATIONS_B[kk + k1]]];
		final int gi2 = PERMUTATIONS_B_MODULO_12[ii + i2 + PERMUTATIONS_B[jj + j2 + PERMUTATIONS_B[kk + k2]]];
		final int gi3 = PERMUTATIONS_B_MODULO_12[ii + 1 + PERMUTATIONS_B[jj + 1 + PERMUTATIONS_B[kk + 1]]];
		
		final double t0 = 0.6D - x0 * x0 - y0 * y0 - z0 * z0;
		final double n0 = t0 < 0.0D ? 0.0D : (t0 * t0) * (t0 * t0) * doDotXYZ(SIMPLEX_GRADIENT_3[gi0 * 3 + 0], SIMPLEX_GRADIENT_3[gi0 * 3 + 1], SIMPLEX_GRADIENT_3[gi0 * 3 + 2], x0, y0, z0);
		
		final double t1 = 0.6D - x1 * x1 - y1 * y1 - z1 * z1;
		final double n1 = t1 < 0.0D ? 0.0D : (t1 * t1) * (t1 * t1) * doDotXYZ(SIMPLEX_GRADIENT_3[gi1 * 3 + 0], SIMPLEX_GRADIENT_3[gi1 * 3 + 1], SIMPLEX_GRADIENT_3[gi1 * 3 + 2], x1, y1, z1);
		
		final double t2 = 0.6D - x2 * x2 - y2 * y2 - z2 * z2;
		final double n2 = t2 < 0.0D ? 0.0D : (t2 * t2) * (t2 * t2) * doDotXYZ(SIMPLEX_GRADIENT_3[gi2 * 3 + 0], SIMPLEX_GRADIENT_3[gi2 * 3 + 1], SIMPLEX_GRADIENT_3[gi2 * 3 + 2], x2, y2, z2);
		
		final double t3 = 0.6D - x3 * x3 - y3 * y3 - z3 * z3;
		final double n3 = t3 < 0.0D ? 0.0D : (t3 * t3) * (t3 * t3) * doDotXYZ(SIMPLEX_GRADIENT_3[gi3 * 3 + 0], SIMPLEX_GRADIENT_3[gi3 * 3 + 1], SIMPLEX_GRADIENT_3[gi3 * 3 + 2], x3, y3, z3);
		
		return 32.0D * (n0 + n1 + n2 + n3);
	}
	
	/**
	 * Returns a {@code double} with noise computed by the Simplex algorithm using the coordinates X, Y, Z and W.
	 * 
	 * @param x the X-coordinate
	 * @param y the Y-coordinate
	 * @param z the Z-coordinate
	 * @param w the W-coordinate
	 * @return a {@code double} with noise computed by the Simplex algorithm using the coordinates X, Y, Z and W
	 */
	public static double simplexNoise(final double x, final double y, final double z, final double w) {
		final double s = (x + y + z + w) * SIMPLEX_F4;
		
		final int i = doFastFloor(x + s);
		final int j = doFastFloor(y + s);
		final int k = doFastFloor(z + s);
		final int l = doFastFloor(w + s);
		
		final double t = (i + j + k + l) * SIMPLEX_G4;
		
		final double x0 = x - (i - t);
		final double y0 = y - (j - t);
		final double z0 = z - (k - t);
		final double w0 = w - (l - t);
		
		int rankX = 0;
		int rankY = 0;
		int rankZ = 0;
		int rankW = 0;
		
		if(x0 > y0) {
			rankX++;
		} else {
			rankY++;
		}
		
		if(x0 > z0) {
			rankX++;
		} else {
			rankZ++;
		}
		
		if(x0 > w0) {
			rankX++;
		} else {
			rankW++;
		}
		
		if(y0 > z0) {
			rankY++;
		} else {
			rankZ++;
		}
		
		if(y0 > w0) {
			rankY++;
		} else {
			rankW++;
		}
		
		if(z0 > w0) {
			rankZ++;
		} else {
			rankW++;
		}
		
		final int i1 = rankX >= 3 ? 1 : 0;
		final int j1 = rankY >= 3 ? 1 : 0;
		final int k1 = rankZ >= 3 ? 1 : 0;
		final int l1 = rankW >= 3 ? 1 : 0;
		final int i2 = rankX >= 2 ? 1 : 0;
		final int j2 = rankY >= 2 ? 1 : 0;
		final int k2 = rankZ >= 2 ? 1 : 0;
		final int l2 = rankW >= 2 ? 1 : 0;
		final int i3 = rankX >= 1 ? 1 : 0;
		final int j3 = rankY >= 1 ? 1 : 0;
		final int k3 = rankZ >= 1 ? 1 : 0;
		final int l3 = rankW >= 1 ? 1 : 0;
		
		final double x1 = x0 - i1 + SIMPLEX_G4;
		final double y1 = y0 - j1 + SIMPLEX_G4;
		final double z1 = z0 - k1 + SIMPLEX_G4;
		final double w1 = w0 - l1 + SIMPLEX_G4;
		final double x2 = x0 - i2 + 2.0D * SIMPLEX_G4;
		final double y2 = y0 - j2 + 2.0D * SIMPLEX_G4;
		final double z2 = z0 - k2 + 2.0D * SIMPLEX_G4;
		final double w2 = w0 - l2 + 2.0D * SIMPLEX_G4;
		final double x3 = x0 - i3 + 3.0D * SIMPLEX_G4;
		final double y3 = y0 - j3 + 3.0D * SIMPLEX_G4;
		final double z3 = z0 - k3 + 3.0D * SIMPLEX_G4;
		final double w3 = w0 - l3 + 3.0D * SIMPLEX_G4;
		final double x4 = x0 - 1.0D + 4.0D * SIMPLEX_G4;
		final double y4 = y0 - 1.0D + 4.0D * SIMPLEX_G4;
		final double z4 = z0 - 1.0D + 4.0D * SIMPLEX_G4;
		final double w4 = w0 - 1.0D + 4.0D * SIMPLEX_G4;
		
		final int ii = i & 0xFF;
		final int jj = j & 0xFF;
		final int kk = k & 0xFF;
		final int ll = l & 0xFF;
		
		final int gi0 = PERMUTATIONS_B[ii + PERMUTATIONS_B[jj + PERMUTATIONS_B[kk + PERMUTATIONS_B[ll]]]] % 32;
		final int gi1 = PERMUTATIONS_B[ii + i1 + PERMUTATIONS_B[jj + j1 + PERMUTATIONS_B[kk + k1 + PERMUTATIONS_B[ll + l1]]]] % 32;
		final int gi2 = PERMUTATIONS_B[ii + i2 + PERMUTATIONS_B[jj + j2 + PERMUTATIONS_B[kk + k2 + PERMUTATIONS_B[ll + l2]]]] % 32;
		final int gi3 = PERMUTATIONS_B[ii + i3 + PERMUTATIONS_B[jj + j3 + PERMUTATIONS_B[kk + k3 + PERMUTATIONS_B[ll + l3]]]] % 32;
		final int gi4 = PERMUTATIONS_B[ii + 1 + PERMUTATIONS_B[jj + 1 + PERMUTATIONS_B[kk + 1 + PERMUTATIONS_B[ll + 1]]]] % 32;
		
		final double t0 = 0.6D - x0 * x0 - y0 * y0 - z0 * z0 - w0 * w0;
		final double n0 = t0 < 0.0D ? 0.0D : (t0 * t0) * (t0 * t0) * doDotXYZW(SIMPLEX_GRADIENT_4[gi0 * 4 + 0], SIMPLEX_GRADIENT_4[gi0 * 4 + 1], SIMPLEX_GRADIENT_4[gi0 * 4 + 2], SIMPLEX_GRADIENT_4[gi0 * 4 + 3], x0, y0, z0, w0);
		
		final double t1 = 0.6D - x1 * x1 - y1 * y1 - z1 * z1 - w1 * w1;
		final double n1 = t1 < 0.0D ? 0.0D : (t1 * t1) * (t1 * t1) * doDotXYZW(SIMPLEX_GRADIENT_4[gi1 * 4 + 0], SIMPLEX_GRADIENT_4[gi1 * 4 + 1], SIMPLEX_GRADIENT_4[gi1 * 4 + 2], SIMPLEX_GRADIENT_4[gi1 * 4 + 3], x1, y1, z1, w1);
		
		final double t2 = 0.6D - x2 * x2 - y2 * y2 - z2 * z2 - w2 * w2;
		final double n2 = t2 < 0.0D ? 0.0D : (t2 * t2) * (t2 * t2) * doDotXYZW(SIMPLEX_GRADIENT_4[gi2 * 4 + 0], SIMPLEX_GRADIENT_4[gi2 * 4 + 1], SIMPLEX_GRADIENT_4[gi2 * 4 + 2], SIMPLEX_GRADIENT_4[gi2 * 4 + 3], x2, y2, z2, w2);
		
		final double t3 = 0.6D - x3 * x3 - y3 * y3 - z3 * z3 - w3 * w3;
		final double n3 = t3 < 0.0D ? 0.0D : (t3 * t3) * (t3 * t3) * doDotXYZW(SIMPLEX_GRADIENT_4[gi3 * 4 + 0], SIMPLEX_GRADIENT_4[gi3 * 4 + 1], SIMPLEX_GRADIENT_4[gi3 * 4 + 2], SIMPLEX_GRADIENT_4[gi3 * 4 + 3], x3, y3, z3, w3);
		
		final double t4 = 0.6D - x4 * x4 - y4 * y4 - z4 * z4 - w4 * w4;
		final double n4 = t4 < 0.0D ? 0.0D : (t4 * t4) * (t4 * t4) * doDotXYZW(SIMPLEX_GRADIENT_4[gi4 * 4 + 0], SIMPLEX_GRADIENT_4[gi4 * 4 + 1], SIMPLEX_GRADIENT_4[gi4 * 4 + 2], SIMPLEX_GRADIENT_4[gi4 * 4 + 3], x4, y4, z4, w4);
		
		return 27.0D * (n0 + n1 + n2 + n3 + n4);
	}
	
	/**
	 * Returns a {@code double} with noise computed by a Simplex-based turbulence algorithm using the coordinate X.
	 * 
	 * @param x the X-coordinate
	 * @param amplitude the amplitude to start a
	 * @param frequency the frequency to start at
	 * @param gain the amplitude multiplier
	 * @param lacunarity the frequency multiplier
	 * @param octaves the number of iterations to perform
	 * @return a {@code double} with noise computed by a Simplex-based turbulence algorithm using the coordinate X
	 */
	public static double simplexTurbulence(final double x, final double amplitude, final double frequency, final double gain, final double lacunarity, final int octaves) {
		double currentAmplitude = amplitude;
		double currentFrequency = frequency;
		
		double noise = 0.0D;
		
		for(int i = 0; i < octaves; i++) {
			noise += currentAmplitude * abs(simplexNoise(x * currentFrequency));
			
			currentAmplitude *= gain;
			currentFrequency *= lacunarity;
		}
		
		return noise;
	}
	
	/**
	 * Returns a {@code double} with noise computed by a Simplex-based turbulence algorithm using the coordinates X and Y.
	 * 
	 * @param x the X-coordinate
	 * @param y the Y-coordinate
	 * @param amplitude the amplitude to start a
	 * @param frequency the frequency to start at
	 * @param gain the amplitude multiplier
	 * @param lacunarity the frequency multiplier
	 * @param octaves the number of iterations to perform
	 * @return a {@code double} with noise computed by a Simplex-based turbulence algorithm using the coordinates X and Y
	 */
	public static double simplexTurbulence(final double x, final double y, final double amplitude, final double frequency, final double gain, final double lacunarity, final int octaves) {
		double currentAmplitude = amplitude;
		double currentFrequency = frequency;
		
		double noise = 0.0D;
		
		for(int i = 0; i < octaves; i++) {
			noise += currentAmplitude * abs(simplexNoise(x * currentFrequency, y * currentFrequency));
			
			currentAmplitude *= gain;
			currentFrequency *= lacunarity;
		}
		
		return noise;
	}
	
	/**
	 * Returns a {@code double} with noise computed by a Simplex-based turbulence algorithm using the coordinates X, Y and Z.
	 * 
	 * @param x the X-coordinate
	 * @param y the Y-coordinate
	 * @param z the Z-coordinate
	 * @param amplitude the amplitude to start a
	 * @param frequency the frequency to start at
	 * @param gain the amplitude multiplier
	 * @param lacunarity the frequency multiplier
	 * @param octaves the number of iterations to perform
	 * @return a {@code double} with noise computed by a Simplex-based turbulence algorithm using the coordinates X, Y and Z
	 */
	public static double simplexTurbulence(final double x, final double y, final double z, final double amplitude, final double frequency, final double gain, final double lacunarity, final int octaves) {
		double currentAmplitude = amplitude;
		double currentFrequency = frequency;
		
		double noise = 0.0D;
		
		for(int i = 0; i < octaves; i++) {
			noise += currentAmplitude * abs(simplexNoise(x * currentFrequency, y * currentFrequency, z * currentFrequency));
			
			currentAmplitude *= gain;
			currentFrequency *= lacunarity;
		}
		
		return noise;
	}
	
	/**
	 * Returns a {@code double} with noise computed by a Simplex-based turbulence algorithm using the coordinates X, Y, Z and W.
	 * 
	 * @param x the X-coordinate
	 * @param y the Y-coordinate
	 * @param z the Z-coordinate
	 * @param w the W-coordinate
	 * @param amplitude the amplitude to start a
	 * @param frequency the frequency to start at
	 * @param gain the amplitude multiplier
	 * @param lacunarity the frequency multiplier
	 * @param octaves the number of iterations to perform
	 * @return a {@code double} with noise computed by a Simplex-based turbulence algorithm using the coordinates X, Y, Z and W
	 */
	public static double simplexTurbulence(final double x, final double y, final double z, final double w, final double amplitude, final double frequency, final double gain, final double lacunarity, final int octaves) {
		double currentAmplitude = amplitude;
		double currentFrequency = frequency;
		
		double noise = 0.0D;
		
		for(int i = 0; i < octaves; i++) {
			noise += currentAmplitude * abs(simplexNoise(x * currentFrequency, y * currentFrequency, z * currentFrequency, w * currentFrequency));
			
			currentAmplitude *= gain;
			currentFrequency *= lacunarity;
		}
		
		return noise;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private static double doDotXY(final double x0, final double y0, final double x1, final double y1) {
		return x0 * x1 + y0 * y1;
	}
	
	private static double doDotXYZ(final double x0, final double y0, final double z0, final double x1, final double y1, final double z1) {
		return x0 * x1 + y0 * y1 + z0 * z1;
	}
	
	private static double doDotXYZW(final double x0, final double y0, final double z0, final double w0, final double x1, final double y1, final double z1, final double w1) {
		return x0 * x1 + y0 * y1 + z0 * z1 + w0 * w1;
	}
	
	private static double[] doCreateSimplexGradient3() {
		return new double[] {
			+1.0D, +1.0D, +0.0D, -1.0D, +1.0D, +0.0D, +1.0D, -1.0D, +0.0D, -1.0D, -1.0D, +0.0D,
			+1.0D, +0.0D, +1.0D, -1.0D, +0.0D, +1.0D, +1.0D, +0.0D, -1.0D, -1.0D, +0.0D, -1.0D,
			+0.0D, +1.0D, +1.0D, +0.0D, -1.0D, +1.0D, +0.0D, +1.0D, -1.0D, +0.0D, -1.0D, -1.0D
		};
	}
	
	private static double[] doCreateSimplexGradient4() {
		return new double[] {
			+0.0D, +1.0D, +1.0D, +1.0D, +0.0D, +1.0D, +1.0D, -1.0D, +0.0D, +1.0D, -1.0D, +1.0D, +0.0D, +1.0D, -1.0D, -1.0D,
			+0.0D, -1.0D, +1.0D, +1.0D, +0.0D, -1.0D, +1.0D, -1.0D, +0.0D, -1.0D, -1.0D, +1.0D, +0.0D, -1.0D, -1.0D, -1.0D,
			+1.0D, +0.0D, +1.0D, +1.0D, +1.0D, +0.0D, +1.0D, -1.0D, +1.0D, +0.0D, -1.0D, +1.0D, +1.0D, +0.0D, -1.0D, -1.0D,
			-1.0D, +0.0D, +1.0D, +1.0D, -1.0D, +0.0D, +1.0D, -1.0D, -1.0D, +0.0D, -1.0D, +1.0D, -1.0D, +0.0D, -1.0D, -1.0D,
			+1.0D, +1.0D, +0.0D, +1.0D, +1.0D, +1.0D, +0.0D, -1.0D, +1.0D, -1.0D, +0.0D, +1.0D, +1.0D, -1.0D, +0.0D, -1.0D,
			-1.0D, +1.0D, +0.0D, +1.0D, -1.0D, +1.0D, +0.0D, -1.0D, -1.0D, -1.0D, +0.0D, +1.0D, -1.0D, -1.0D, +0.0D, -1.0D,
			+1.0D, +1.0D, +1.0D, +0.0D, +1.0D, +1.0D, -1.0D, +0.0D, +1.0D, -1.0D, +1.0D, +0.0D, +1.0D, -1.0D, -1.0D, +0.0D,
			-1.0D, +1.0D, +1.0D, +0.0D, -1.0D, +1.0D, -1.0D, +0.0D, -1.0D, -1.0D, +1.0D, +0.0D, -1.0D, -1.0D, -1.0D, +0.0D
		};
	}
	
	private static int doFastFloor(final double value) {
		final int i = (int)(value);
		
		return value < i ? i - 1 : i;
	}
	
	private static double doGradientX(final int hash, final double x) {
		final int hash0 = hash & 0x0F;
		
		double gradient = 1.0D + (hash0 & 7);
		
		if((hash0 & 8) != 0) {
			gradient = -gradient;
		}
		
		return gradient * x;
	}
	
	private static int doHash(final int index) {
		return PERMUTATIONS_B[abs(index) % PERMUTATIONS_B.length];
	}
	
	private static int[] doCreatePermutationsA() {
		return new int[] {
			151, 160, 137,  91,  90,  15, 131,  13, 201,  95,  96,  53, 194, 233,   7, 225,
			140,  36, 103,  30,  69, 142,   8,  99,  37, 240,  21,  10,  23, 190,   6, 148,
			247, 120, 234,  75,   0,  26, 197,  62,  94, 252, 219, 203, 117,  35,  11,  32,
			 57, 177,  33,  88, 237, 149,  56,  87, 174,  20, 125, 136, 171, 168,  68, 175,
			 74, 165,  71, 134, 139,  48,  27, 166,  77, 146, 158, 231,  83, 111, 229, 122,
			 60, 211, 133, 230, 220, 105,  92,  41,  55,  46, 245,  40, 244, 102, 143,  54,
			 65,  25,  63, 161,   1, 216,  80,  73, 209,  76, 132, 187, 208,  89,  18, 169,
			200, 196, 135, 130, 116, 188, 159,  86, 164, 100, 109, 198, 173, 186,   3,  64,
			 52, 217, 226, 250, 124, 123,   5, 202,  38, 147, 118, 126, 255,  82,  85, 212,
			207, 206,  59, 227,  47,  16,  58,  17, 182, 189,  28,  42,  23, 183, 170, 213,
			119, 248, 152,   2,  44, 154, 163,  70, 221, 153, 101, 155, 167,  43, 172,   9,
			129,  22,  39, 253,  19,  98, 108, 110,  79, 113, 224, 232, 178, 185, 112, 104,
			218, 246,  97, 228, 251,  34, 242, 193, 238, 210, 144,  12, 191, 179, 162, 241,
			 81,  51, 145, 235, 249,  14, 239, 107,  49, 192, 214,  31, 181, 199, 106, 157,
			184,  84, 204, 176, 115, 121,  50,  45, 127,   4, 150, 254, 138, 236, 205,  93,
			222, 114,  67,  29,  24,  72, 243, 141, 128, 195,  78,  66, 215,  61, 156, 180
		};
	}
	
	private static int[] doCreatePermutationsB() {
		final int[] permutationsA = doCreatePermutationsA();
		final int[] permutationsB = new int[permutationsA.length * 2];
		
		for(int i = 0; i < permutationsB.length; i++) {
			permutationsB[i] = permutationsA[i % permutationsA.length];
		}
		
		return permutationsB;
	}
	
	private static int[] doCreatePermutationsBModulo12() {
		final int[] permutationsBModulo12 = doCreatePermutationsB();
		
		for(int i = 0; i < permutationsBModulo12.length; i++) {
			permutationsBModulo12[i] = permutationsBModulo12[i] % 12;
		}
		
		return permutationsBModulo12;
	}
}