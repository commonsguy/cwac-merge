CWAC MergeAdapter: Stitching Together Rows
==========================================

`MergeAdapter` accepts a mix of `Adapter`s and `View`s and
presents them as one contiguous whole to whatever `ListView`
it is poured into. This is good for cases where you have
multiple data sources, or if you have a handful of ordinary
`View`s to mix in with lists of data, or the like.

Simply create a `MergeAdapter` and call `addAdapter()`,
`addView()`, or `addViews()` (latter accepting a `List<View>`),
then attach your adapter to the `ListView`. You can also
extend `MergeAdapter` to override `isEnabled()`, so you can
control which positions are and are not enabled.

Usage
-----
Full instructions for using this module are forthcoming. Stay
tuned!

Dependencies
------------
This project requires the [CWAC SackOfViewsAdapter][sacklist].
A copy of a compatible JAR can be found in the `libs/` directory of
the project, though you are welcome to try newer ones, or
ones that you have patched yourself.

Version
-------
This is version 0.1 of this module, meaning it is pretty darn
new.

Demo
----
In the `com.commonsware.cwac.merge.demo` package you will find
a sample activity that demonstrates the use of `MergeAdapter`.

Note that when you build the JAR via `ant jar`, the sample
activity is not included, nor any resources -- only the
compiled classes for the actual library are put into the JAR.

License
-------
The code in this project is licensed under the Apache
Software License 2.0, per the terms of the included LICENSE
file.

Questions
---------
If you have questions regarding the use of this code, please
join and ask them on the [cw-android Google Group][gg]. Be sure to
indicate which CWAC module you have questions about.

[gg]: http://groups.google.com/group/cw-android
[sacklist]: http://github.com/commonsguy/cwac-sacklist/tree/master
