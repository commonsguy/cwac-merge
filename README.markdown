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

This is packaged as an Android library project, though a simple
JAR is also available from the Downloads section of this
GitHub repository.

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
`MergeAdapter`. You can call `addAdapter()` to have all of that
adapter's rows appear in the combined roster. You can call
`addView()` to add a single `View` as a row. You can also call
`addViews()` to add a `List` of `View` objects to use as rows.

Each of these will appear in combined roster in the order
they were added.

The `addView()` and `addViews()` methods have a variant that
accepts a boolean 2nd parameter. Set this boolean to true if you
want the rows represented by these views to be enabled (i.e.,
selectable). The default is that they are disabled, for use
as if they were header rows. For adapters added via `addAdapter()`,
the determination of whether or not rows are enabled is determined
by the underlying adapter.

### Other Methods to Override

You are welcome to override other methods as well, since this
is just an `Adapter`.

### Timing

You must pour the contents into the `MergeAdapter` *before*
calling `setListAdapter()` to associate the `MergeAdapter`
with a `ListView`. This limitation is required because Android
only calls getViewTypeCount() once, and adding more views or
adapters adds more view types.

Note, though, that you can modify the underlying adapters. So,
for example, if you add a `CursorAdapter` to the `MergeAdapter`,
and you `requery()` the `Cursor`, the changes should be reflected
via the `MergeAdapter` to whatever `AdapterView` the `MergeAdapter`
is connected to.

Dependencies
------------
This project requires the [CWAC SackOfViewsAdapter][sacklist].
A copy of a compatible JAR can be found in the `libs/` directory of
the project, though you are welcome to try newer ones, or
ones that you have patched yourself.

Version
-------
This is version v0.2.1 of this module, meaning it is slowly
progressing towards respectability.

Demo
----
In the `demo/` sub-project you will find
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

Release Notes
-------------
v0.2.1: added `getAdapter()` method to return the `ListAdapter` associated with a given position
v0.2.0: converted to Android library project, added enabled versions of `addView()` and `addViews()`, correctly cascades data set changes from underlying adapters

Who Made This?
--------------
<a href="http://commonsware.com">![CommonsWare](http://commonsware.com/images/logo.png)</a>

[gg]: http://groups.google.com/group/cw-android
[sacklist]: http://github.com/commonsguy/cwac-sacklist/tree/master
