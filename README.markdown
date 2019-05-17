CWAC MergeAdapter: Stitching Together Rows
==========================================

**NOTE**: This project has been discontinued. This repository will be made private on 1 June 2020.

`MergeAdapter` accepts a mix of `Adapter`s and `View`s and
presents them as one contiguous whole to whatever `ListView`
it is poured into. This is good for cases where you have
multiple data sources, or if you have a handful of ordinary
`View`s to mix in with lists of data, or the like.

Simply create a `MergeAdapter` and call `addAdapter()`,
`addView()`, or `addViews()` (latter accepting a `List<View>`),
then attach your adapter to the `ListView`. 

There is also `MergeSpinnerAdapter` for use with `Spinner`
widgets.

Installation
------------
This Android library project is 
[available as a JAR](https://github.com/commonsguy/cwac-merge/releases).
If you wish to use the JAR, you will need to also add the JAR from
[the CWAC-Sacklist project](http://github.com/commonsguy/cwac-sacklist) to your
project.

NOTE: The JAR name, as of v1.0.4, has a `cwac-` prefix, to help distinguish it from other JARs.

Also note that if you plan to use this as an Android library project
in source form, you
will also need to download [the CWAC-Sacklist project](http://github.com/commonsguy/cwac-sacklist)
(and, if needed, modify this project's configuration to point to your copy of
CWAC-Sacklist library project). Alternatively, download the CWAC-Sacklist JAR into
the `libs/` directory of your clone of this project and remove the dependency on
the CWAC-Sacklist library project.

This project is also available as
an artifact for use with Gradle. To use that, add the following
blocks to your `build.gradle` file:

```groovy
repositories {
    maven {
        url "https://s3.amazonaws.com/repo.commonsware.com"
    }
}

dependencies {
    compile 'com.commonsware.cwac:merge:1.1.2'
}
```

Or, if you cannot use SSL, use `http://repo.commonsware.com` for the repository
URL. This should automatically pull down the CWAC-Sacklist dependency.

Usage
-----
You can use `MergeAdapter` directly or subclass it. The
latter is needed to support controlling which rows are/are
not enabled.

### Constructors

There is only one, no-argument constructor at this time. Just
call `new MergeAdapter()` or `new MergeSpinnerAdapter()` and
you are on your way!

### Adding Content

You have three methods for defining what goes into the
`MergeAdapter`. You can call `addAdapter()` to have all of that
adapter's rows appear in the combined roster. You can call
`addView()` to add a single `View` as a row. You can also call
`addViews()` to add a `List` of `View` objects to use as rows.

**NOTE**: `MergeSpinnerAdapter` only supports `addAdapter()`.

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

### Supporting isEnabled()

If you wish, your adapters wrapped by `MergeAdapter` can override `isEnabled()`,
and `MergeAdapter` should enable and disable the rows as directed.

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

### Supporting `android:id/empty`

A `ListActivity` supports a widget in its layout, with an `android:id`
of `@android:id/empty`, which will be displayed if the list is
empty. More specifically, it will be displayed if the `MergeAdapter`
returns `true` for `isEmpty()`. The default implementation of
`isEmpty()` in `BaseAdapter` checks `getCount()` and compares it to `0`.

In many cases, this default will be fine. However, if you are
adding regular `View`s to the adapter (e.g., section headings),
then `getCount()` will include these additional rows, and so `isEmpty()`
will never return `true`. In this case, you may need to override
`isEmpty()` to implement your own business logic to determine
when the list is, indeed, "empty".

### Contents of `MergeSpinnerAdapter`

You should add properly-configured `SpinnerAdapter` implementations
(e.g., `ArrayAdapter`, `CursorAdapter`) to a `MergeSpinnerAdapter`
to have it work properly.

### Active/Inactive Contents

By default, everything that you add to the `MergeAdapter` is "active" and
contributes to the resulting compound contents. However, you can call
`setActive()` to toggle whether some piece is active or inactive. Inactive
pieces do not contribute to the contents, removing them entirely from the
`ListView` (or wherever you are using this).

There are two flavors of `setActive()`. Both take a `boolean` second parameter,
with `true` meaning the piece is active and `false` for inactive. The first
parameter is either a `ListAdapter` that you added already or a `View` that
you added already (individually or as part of a `List`).

### IGNORE_ITEM_VIEW_TYPE and ITEM_VIEW_TYPE_IGNORE

Neither `IGNORE_ITEM_VIEW_TYPE` from `Adapter` nor `ITEM_VIEW_TYPE_IGNORE` from
`AdapterView` are honored by `MergeAdapter`. That's mostly because there is no
evidence that either constant is used inside of Android, and the documentation
for each is shaky at best. If you find a use case for either of these values,
please post an [issue](https://github.com/commonsguy/cwac-merge/issues),
with enough information to reproduce the use case and therefore confirm that
a modified `MergeAdapter` does what it is supposed to.

### JavaDocs

[JavaDocs are available](http://javadocs.commonsware.com/cwac/merge/index.html)
for your JavaDocing pleasure.

Dependencies
------------
This project requires the [CWAC SackOfViewsAdapter][sacklist].
A copy of a compatible JAR can be found in the `libs/` directory of
the project, though you are welcome to try newer ones, or
ones that you have patched yourself. Users of the AAR artifact will automatically
download this dependency.

This project should work on API Level 4 and higher, except for any portions that
may be noted otherwise in this document. Please report bugs if you find features
that do not work on API Level 4 and are not noted as requiring a higher version.

Version
-------
This is version v1.1.2 of this module.

For those of you updating from a previous version, please note that you need
a new edition of the `SackOfViewsAdapter` JAR as well.

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
If you have questions regarding the use of this code, please post a question
on [Stack Overflow](http://stackoverflow.com/questions/ask) tagged with
`commonsware-cwac` and `android` after [searching to see if there already is an answer](https://stackoverflow.com/search?q=[android]+mergeadapter). Be sure to indicate
what CWAC module you are having issues with, and be sure to include source code 
and stack traces if you are encountering crashes.

If you have encountered what is clearly a bug, please post an [issue](https://github.com/commonsguy/cwac-merge/issues). Be certain to include complete steps
for reproducing the issue.
The [contribution guidelines](CONTRIBUTING.md)
provide some suggestions for how to create a bug report that will get
the problem fixed the fastest.

You are also welcome to join
[the CommonsWare Community](https://community.commonsware.com/)
and post questions
and ideas to [the CWAC category](https://community.commonsware.com/c/cwac).

Do not ask for help via social media.

Release Notes
-------------
- v1.1.2: reorganized project into Android Studio-standard structure
- v1.1.1: updated for Android Studio 1.0 and new AAR publishing system
- v1.1.0: added `android:minSdkVersion` to library manifest
- v1.0.4: added `cwac-` prefix to JAR
- v1.0.3: re-fixed manifest to better handle manifest merging per issue #14
- v1.0.2: fixed manifest to better handle manifest merging
- v1.0.1: added Gradle files and published AAR
- v1.0.0: milestone considered reached
- v0.4.0: added `setActive()`
- v0.3.1: added workaround for http://code.google.com/p/android/issues/detail?id=16155
- v0.3.0: added `MergeSpinnerAdapter` support
- v0.2.1: added `getAdapter()` method to return the `ListAdapter` associated with a given position
- v0.2.0: converted to Android library project, added enabled versions of `addView()` and `addViews()`, correctly cascades data set changes from underlying adapters

Who Made This?
--------------
<a href="http://commonsware.com">![CommonsWare](http://commonsware.com/images/logo.png)</a>

[sacklist]: http://github.com/commonsguy/cwac-sacklist/tree/master
