package org.macroing.math4j;

import static org.macroing.math4j.MathD.PI;
import static org.macroing.math4j.MathD.PI_MULTIPLIED_BY_TWO;
import static org.macroing.math4j.MathD.cos;
import static org.macroing.math4j.MathD.max;
import static org.macroing.math4j.MathD.pow;
import static org.macroing.math4j.MathD.random;
import static org.macroing.math4j.MathD.sin;
import static org.macroing.math4j.MathD.sqrt;

/**
 * The class {@code SampleGeneratorD} contains methods for generating samples of type {@code double}.
 * 
 * @since 1.0.0
 * @author J&#246;rgen Lundgren
 */
public final class SampleGeneratorD {
	private SampleGeneratorD() {
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Samples a point on a concentric disk.
	 * <p>
	 * Returns a {@code Point2D} instance with the sampled point.
	 * <p>
	 * Calling this method is equivalent to the following:
	 * <pre>
	 * {@code
	 * SampleGeneratorD.sampleConcentricDisk(MathD.random(), MathD.random());
	 * }
	 * </pre>
	 * 
	 * @return a {@code Point2D} instance with the sampled point
	 */
	public static Point2D sampleConcentricDisk() {
		return sampleConcentricDisk(random(), random());
	}
	
	/**
	 * Samples a point on a concentric disk.
	 * <p>
	 * Returns a {@code Point2D} instance with the sampled point.
	 * <p>
	 * Calling this method is equivalent to the following:
	 * <pre>
	 * {@code
	 * SampleGeneratorD.sampleConcentricDisk(u, v, 1.0D);
	 * }
	 * </pre>
	 * 
	 * @param u a random {@code double} with a uniform distribution between {@code 0.0D} and {@code 1.0D}
	 * @param v a random {@code double} with a uniform distribution between {@code 0.0D} and {@code 1.0D}
	 * @return a {@code Point2D} instance with the sampled point
	 */
	public static Point2D sampleConcentricDisk(final double u, final double v) {
		return sampleConcentricDisk(u, v, 1.0D);
	}
	
	/**
	 * Samples a point on a concentric disk.
	 * <p>
	 * Returns a {@code Point2D} instance with the sampled point.
	 * 
	 * @param u a random {@code double} with a uniform distribution between {@code 0.0D} and {@code 1.0D}
	 * @param v a random {@code double} with a uniform distribution between {@code 0.0D} and {@code 1.0D}
	 * @param radius the radius of the disk
	 * @return a {@code Point2D} instance with the sampled point
	 */
	public static Point2D sampleConcentricDisk(final double u, final double v, final double radius) {
		if(MathD.equals(u, 0.0D) && MathD.equals(v, 0.0D)) {
			return new Point2D(0.0D, 0.0D);
		}
		
		final double a = u * 2.0D - 1.0D;
		final double b = v * 2.0D - 1.0D;
		
		final boolean isAGreaterThanB = a * a > b * b;
		
		final double phi = isAGreaterThanB ? (PI / 4.0D) * (b / a) : (PI / 2.0D) - ((PI / 4.0D) * (a / b));
		final double r   = isAGreaterThanB ? radius * a : radius * b;
		
		final double x = cos(phi) * r;
		final double y = sin(phi) * r;
		
		return new Point2D(x, y);
	}
	
	/**
	 * Samples a point on a disk with a uniform distribution.
	 * <p>
	 * Returns a {@code Point2D} instance with the sampled point.
	 * <p>
	 * Calling this method is equivalent to the following:
	 * <pre>
	 * {@code
	 * SampleGeneratorD.sampleDiskUniformDistribution(MathD.random(), MathD.random());
	 * }
	 * </pre>
	 * 
	 * @return a {@code Point2D} instance with the sampled point
	 */
	public static Point2D sampleDiskUniformDistribution() {
		return sampleDiskUniformDistribution(random(), random());
	}
	
	/**
	 * Samples a point on a disk with a uniform distribution.
	 * <p>
	 * Returns a {@code Point2D} instance with the sampled point.
	 * 
	 * @param u a random {@code double} with a uniform distribution between {@code 0.0D} and {@code 1.0D}
	 * @param v a random {@code double} with a uniform distribution between {@code 0.0D} and {@code 1.0D}
	 * @return a {@code Point2D} instance with the sampled point
	 */
	public static Point2D sampleDiskUniformDistribution(final double u, final double v) {
		final double phi = PI * 2.0D * v;
		final double r = sqrt(u);
		
		final double x = cos(phi) * r;
		final double y = sin(phi) * r;
		
		return new Point2D(x, y);
	}
	
	/**
	 * Samples a direction on a cone with a uniform distribution.
	 * <p>
	 * Returns a {@code Vector3D} instance with the sampled direction.
	 * 
	 * @param u a random {@code double} with a uniform distribution between {@code 0.0D} and {@code 1.0D}
	 * @param v a random {@code double} with a uniform distribution between {@code 0.0D} and {@code 1.0D}
	 * @param cosThetaMax the maximum cos theta value
	 * @return a {@code Vector3D} instance with the sampled direction
	 */
	public static Vector3D sampleConeUniformDistribution(final double u, final double v, final double cosThetaMax) {
		final double cosTheta = u * (cosThetaMax - 1.0D) + 1.0D;
		final double sinTheta = sqrt(max(0.0D, 1.0D - cosTheta * cosTheta));
		final double phi = PI_MULTIPLIED_BY_TWO * v;
		
		final double x = cos(phi) * sinTheta;
		final double y = sin(phi) * sinTheta;
		final double z = cosTheta;
		
		return new Vector3D(x, y, z);
	}
	
	/**
	 * Samples a direction on a hemisphere with a cosine distribution.
	 * <p>
	 * Returns a {@code Vector3D} instance with the sampled direction.
	 * <p>
	 * Calling this method is equivalent to the following:
	 * <pre>
	 * {@code
	 * SampleGeneratorD.sampleHemisphereCosineDistribution(MathD.random(), MathD.random());
	 * }
	 * </pre>
	 * 
	 * @return a {@code Vector3D} instance with the sampled direction
	 */
	public static Vector3D sampleHemisphereCosineDistribution() {
		return sampleHemisphereCosineDistribution(random(), random());
	}
	
	/**
	 * Samples a direction on a hemisphere with a cosine distribution.
	 * <p>
	 * Returns a {@code Vector3D} instance with the sampled direction.
	 * 
	 * @param u a random {@code double} with a uniform distribution between {@code 0.0D} and {@code 1.0D}
	 * @param v a random {@code double} with a uniform distribution between {@code 0.0D} and {@code 1.0D}
	 * @return a {@code Vector3D} instance with the sampled direction
	 */
	public static Vector3D sampleHemisphereCosineDistribution(final double u, final double v) {
		final Point2D point = sampleConcentricDisk(u, v);
		
		final double x = point.x;
		final double y = point.y;
		final double z = sqrt(max(0.0D, 1.0D - x * x - y * y));
		
		return new Vector3D(x, y, z);
	}
	
	/**
	 * Samples a direction on a hemisphere with a power-cosine distribution.
	 * <p>
	 * Returns a {@code Vector3D} instance with the sampled direction.
	 * <p>
	 * Calling this method is equivalent to the following:
	 * <pre>
	 * {@code
	 * SampleGeneratorD.sampleHemispherePowerCosineDistribution(MathD.random(), MathD.random());
	 * }
	 * </pre>
	 * 
	 * @return a {@code Vector3D} instance with the sampled direction
	 */
	public static Vector3D sampleHemispherePowerCosineDistribution() {
		return sampleHemispherePowerCosineDistribution(random(), random());
	}
	
	/**
	 * Samples a direction on a hemisphere with a power-cosine distribution.
	 * <p>
	 * Returns a {@code Vector3D} instance with the sampled direction.
	 * <p>
	 * Calling this method is equivalent to the following:
	 * <pre>
	 * {@code
	 * SampleGeneratorD.sampleHemispherePowerCosineDistribution(u, v, 20.0D);
	 * }
	 * </pre>
	 * 
	 * @param u a random {@code double} with a uniform distribution between {@code 0.0D} and {@code 1.0D}
	 * @param v a random {@code double} with a uniform distribution between {@code 0.0D} and {@code 1.0D}
	 * @return a {@code Vector3D} instance with the sampled direction
	 */
	public static Vector3D sampleHemispherePowerCosineDistribution(final double u, final double v) {
		return sampleHemispherePowerCosineDistribution(u, v, 20.0D);
	}
	
	/**
	 * Samples a direction on a hemisphere with a power-cosine distribution.
	 * <p>
	 * Returns a {@code Vector3D} instance with the sampled direction.
	 * 
	 * @param u a random {@code double} with a uniform distribution between {@code 0.0D} and {@code 1.0D}
	 * @param v a random {@code double} with a uniform distribution between {@code 0.0D} and {@code 1.0D}
	 * @param exponent the exponent to use
	 * @return a {@code Vector3D} instance with the sampled direction
	 */
	public static Vector3D sampleHemispherePowerCosineDistribution(final double u, final double v, final double exponent) {
		final double random = pow(1.0D - u, 1.0D / (exponent + 1.0D));
		
		final double phi = PI_MULTIPLIED_BY_TWO * v;
		final double radius = sqrt(max(0.0D, 1.0D - random * random));
		
		final double x = cos(phi) * radius;
		final double y = sin(phi) * radius;
		final double z = random;
		
		return new Vector3D(x, y, z);
	}
	
	/**
	 * Samples a direction on a hemisphere with a uniform distribution.
	 * <p>
	 * Returns a {@code Vector3D} instance with the sampled direction.
	 * <p>
	 * Calling this method is equivalent to the following:
	 * <pre>
	 * {@code
	 * SampleGeneratorD.sampleHemisphereUniformDistribution(MathD.random(), MathD.random());
	 * }
	 * </pre>
	 * 
	 * @return a {@code Vector3D} instance with the sampled direction
	 */
	public static Vector3D sampleHemisphereUniformDistribution() {
		return sampleHemisphereUniformDistribution(random(), random());
	}
	
	/**
	 * Samples a direction on a hemisphere with a uniform distribution.
	 * <p>
	 * Returns a {@code Vector3D} instance with the sampled direction.
	 * 
	 * @param u a random {@code double} with a uniform distribution between {@code 0.0D} and {@code 1.0D}
	 * @param v a random {@code double} with a uniform distribution between {@code 0.0D} and {@code 1.0D}
	 * @return a {@code Vector3D} instance with the sampled direction
	 */
	public static Vector3D sampleHemisphereUniformDistribution(final double u, final double v) {
		final double random = u;
		
		final double phi = PI_MULTIPLIED_BY_TWO * v;
		final double radius = sqrt(max(0.0D, 1.0D - random * random));
		
		final double x = cos(phi) * radius;
		final double y = sin(phi) * radius;
		final double z = random;
		
		return new Vector3D(x, y, z);
	}
	
	/**
	 * Samples a direction on a sphere with a uniform distribution.
	 * <p>
	 * Returns a {@code Vector3D} instance with the sampled direction.
	 * <p>
	 * Calling this method is equivalent to the following:
	 * <pre>
	 * {@code
	 * SampleGeneratorD.sampleSphereUniformDistribution(MathD.random(), MathD.random());
	 * }
	 * </pre>
	 * 
	 * @return a {@code Vector3D} instance with the sampled direction
	 */
	public static Vector3D sampleSphereUniformDistribution() {
		return sampleSphereUniformDistribution(random(), random());
	}
	
	/**
	 * Samples a direction on a sphere with a uniform distribution.
	 * <p>
	 * Returns a {@code Vector3D} instance with the sampled direction.
	 * 
	 * @param u a random {@code double} with a uniform distribution between {@code 0.0D} and {@code 1.0D}
	 * @param v a random {@code double} with a uniform distribution between {@code 0.0D} and {@code 1.0D}
	 * @return a {@code Vector3D} instance with the sampled direction
	 */
	public static Vector3D sampleSphereUniformDistribution(final double u, final double v) {
		final double random = 1.0D - 2.0D * u;
		
		final double phi = PI_MULTIPLIED_BY_TWO * v;
		final double radius = sqrt(max(0.0D, 1.0D - random * random));
		
		final double x = cos(phi) * radius;
		final double y = sin(phi) * radius;
		final double z = random;
		
		return new Vector3D(x, y, z);
	}
	
	/**
	 * Returns the probability density function (PDF) value for {@code cosThetaMax}.
	 * <p>
	 * This method is used together with {@link #sampleConeUniformDistribution(double, double, double)}.
	 * 
	 * @param cosThetaMax the maximum cos theta value
	 * @return the probability density function (PDF) value for {@code cosThetaMax}
	 */
	public static double coneUniformDistributionProbabilityDensityFunction(final double cosThetaMax) {
		return cosThetaMax >= 1.0D ? 0.0D : 1.0D / (2.0D * PI * (1.0D - cosThetaMax));
	}
	
	/**
	 * Computes the balance heuristic for multiple importance sampling (MIS).
	 * <p>
	 * Returns the result of the computation.
	 * 
	 * @param probabilityDensityFunctionValueA a probability density function (PDF) value
	 * @param probabilityDensityFunctionValueB a probability density function (PDF) value
	 * @param sampleCountA a sample count
	 * @param sampleCountB a sample count
	 * @return the result of the computation
	 */
	public static double multipleImportanceSamplingBalanceHeuristic(final double probabilityDensityFunctionValueA, final double probabilityDensityFunctionValueB, final int sampleCountA, final int sampleCountB) {
		return sampleCountA * probabilityDensityFunctionValueA / (sampleCountA * probabilityDensityFunctionValueA + sampleCountB * probabilityDensityFunctionValueB);
	}
	
	/**
	 * Computes the power heuristic for multiple importance sampling (MIS).
	 * <p>
	 * Returns the result of the computation.
	 * 
	 * @param probabilityDensityFunctionValueA a probability density function (PDF) value
	 * @param probabilityDensityFunctionValueB a probability density function (PDF) value
	 * @param sampleCountA a sample count
	 * @param sampleCountB a sample count
	 * @return the result of the computation
	 */
	public static double multipleImportanceSamplingPowerHeuristic(final double probabilityDensityFunctionValueA, final double probabilityDensityFunctionValueB, final int sampleCountA, final int sampleCountB) {
		final double weightA = sampleCountA * probabilityDensityFunctionValueA;
		final double weightB = sampleCountB * probabilityDensityFunctionValueB;
		
		return weightA * weightA / (weightA * weightA + weightB * weightB);
	}
}