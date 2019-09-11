Math4J v.0.0.1
==============
Math4J is a simple mathematics library for Java.

The mathematical concepts covered are mainly those used in 3D-graphics, such as vectors, points and matrices. This is the field from which this library originated.

Getting Started
---------------
To clone this repository and build the project, you can type the following in Git Bash. You need Apache Ant though.
```bash
git clone https://github.com/macroing/Math4J.git
cd Math4J
ant
```

Supported Features
------------------
This library provides the following data types.

* `AngleD` - An angle abstraction of type `double` that contains both degrees and radians.
* `AngleF` - An angle abstraction of type `float` that contains both degrees and radians.
* `BoundingVolume3D` - A 3-dimensional bounding volume representation of type `double`.
* `BoundingVolume3F` - A 3-dimensional bounding volume representation of type `float`.
* `MathD` - An extension of `java.lang.Math` for type `double`.
* `MathF` - An extension of `java.lang.Math` for type `float`.
* `MathI` - An extension of `java.lang.Math` for type `int`.
* `Matrix44D` - A 4x4 matrix with elements of type `double`.
* `Matrix44F` - A 4x4 matrix with elements of type `float`.
* `NoiseGeneratorD` - A class that can generate noise of type `double`.
* `NoiseGeneratorF` - A class that can generate noise of type `float`.
* `OrthoNormalBasis33D` - A 3-dimensional orthonormal basis of type `double`.
* `OrthoNormalBasis33F` - A 3-dimensional orthonormal basis of type `float`.
* `Plane3D` - A plane of type `double`.
* `Plane3F` - A plane of type `float`.
* `Point2D` - A 2-dimensional point of type `double`.
* `Point2F` - A 2-dimensional point of type `float`.
* `Point3D` - A 3-dimensional point of type `double`.
* `Point3F` - A 3-dimensional point of type `float`.
* `Ray3D` - A 3-dimensional ray of type `double`.
* `Ray3F` - A 3-dimensional ray of type `float`.
* `Shape3D` - A 3-dimensional shape of type `double`.
* `Shape3F` - A 3-dimensional shape of type `float`.
* `Sphere3D` - A sphere of type `double`.
* `Sphere3F` - A sphere of type `float`.
* `Vector3D` - A 3-dimensional vector of type `double`.
* `Vector3F` - A 3-dimensional vector of type `float`.

Dependencies
------------
 - [Java 8](http://www.java.com).

Note
----
This library hasn't been released yet. So, even though it says it's version 1.0.0 in all Java source code files, it shouldn't be treated as such. When this library reaches version 1.0.0, it will be tagged and available on the "releases" page.