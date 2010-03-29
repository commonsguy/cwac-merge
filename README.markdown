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
You can use `MergeAdapter` directly or subclass it. The
latter is needed to support controlling which rows are/are
not enabled.

### Constructors

There is only one, no-argument constructor at this time. Just
call `new MergeAdapter()` and you are on your way!

### Adding Content

You have three methods for defining what goes into the
MergeAdapter. You can call addAdapter() to have all of that
adapter's rows appear in the combined roster. You can call
addView() to add a single View as a row. You can also call
addViews() to add a List of View objects to use as rows.

Each of these will appear in combined roster in the order
they were added.

### Other Methods to Override

You are welcome to override other methods as well, since this
is just an `Adapter`. In particular, if all rows are not
enabled for selection, you will want to override `isEnabled()`
and return `true` or `false` as needed to indicate which rows are
selectable and which are not (e.g., header rows).

### Timing

You must pour the contents into the `MergeAdapter` *before*
calling `setListAdapter()` to associate the `MergeAdapter`
with a `ListView`. This limitation may be corrected in future
releases of this class.

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

Who Made This?
--------------
<a href="http://commonsware.com">![CommonsWare](http://commonsware.com/images/logo.png)</a>

[gg]: http://groups.google.com/group/cw-android
[sacklist]: http://github.com/commonsguy/cwac-sacklist/tree/master
