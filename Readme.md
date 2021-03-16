Android Kotlin Cheatsheet
=========================

## **Table of Content**

**Fundamental**
* [Get Started](#get-started)
* [Anatomy of Basic Android Project](#Anatomy-of-Basic-Android-Project)
* [Image Resource and Compability](#Image-Resource-and-Compability)
* [Additional Information](#additional-information)

**Layout**
* [Linear Layout using the Layout Editor](#Linear-Layout-using-the-Layout-Editor)
* [Add User Interactivity](#Add-User-Interactivity)
* [ConstraintLayout using the Layout Editor](#ConstraintLayout-using-the-Layout-Editor)
* [Data Binding Basic](#Data-Binding-Basic)

# **Get Started**

* To install Android Studio, go to [Android Studio](https://developer.android.com/studio) and follow the instructions to download and install it.
* To see an app's Android hierarchy in the Project pane, click the Project tab in the vertical tab column. Then select Android in the drop-down menu at the top.
* When you need to add new dependencies to your project or change dependency versions, edit the `build.gradle(Module:app)` file.
* All code and resources for an app are located within the `app` and `res` folders. The `java` folder includes activities, tests, and other components in Kotlin or Java source code (or both). The `res` folder holds resources, such as layouts, strings, and images.
* To add features, components, and permissions to your Android app, edit the `AndroidManifest.xml` file. All app components, such as additional activities, must be declared in this XML file.
* To create an Android virtual device (an emulator) to run your app, use the [AVD Manager](https://developer.android.com/studio/run/managing-avds).
* To run your app on a physical Android device using Android Studio, enable USB debugging on the device. To do this, open **Settings > About phone** and tap **Build number** seven times. Then open **Settings > Developer options** and select **USB debugging**.

# **Anatomy of Basic Android Project**


## **Activities**

*   `MainActivity` is a subclass of `AppCompatActivity`, which in turn is a subclass of `Activity`. An `Activity` is a core Android class that is responsible for drawing an Android app UI and receiving input events.
*   All activities have an associated layout file, which is an XML file in the app's resources. The layout file is named for the activity, for example `activity_main.xml`.
*   The `setContentView()` method in `MainActivity` associates the layout with the activity, and inflates that layout when the activity is created.
*   Layout inflation is a process where the views defined in the XML layout files are turned into (or "inflated" into) Kotlin view objects in memory. Once layout inflation happens, the `Activity` can draw these objects to the screen and dynamically modify them.

## **Views**

*   All UI elements in the app layout are subclasses of the [`View`](http://developer.android.com/reference/android/view/View.html) class and are called _views_. `TextView` and `Button` are examples of views.
*   `View` elements can be grouped inside a [`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html). A view group acts as a container for the views, or other view groups, within it. `LinearLayout` is an example of a view group that arranges its views linearly.

## **View attributes**

*   The `android:layout_width` and `android:layout_height` attributes indicate the width and height of a view. The `match_parent` value stretches the view to its parent's width or height. The `wrap_content` value shrinks the view to fit the view's contents.
*   The `android:text` attribute indicates the text that a view should display (if that view displays text.) For buttons, `android:text` is the button label.
*   The `android:orientation` attribute in a `LinearLayout` view group arranges the view elements it contains. A value of `horizontal` arranges views left to right. A value of `vertical` arranges the views top to bottom.
*   The `android:layout_gravity` attribute determines the placement of a view and all that view's children.
*   The `android:textSize` attribute defines the size of the text in a text view. Text sizes are specified in sp units (_scalable pixels_). By using sp units, you can size text independently of the device's display quality.

## **Strings**

*   Instead of hardcoding strings in the layout, it's a best practice to use string resources.
*   String resources are contained in the `res/values/string.xml` file.
*   To extract strings, use `Alt+Enter` (`Option+Enter` on a Mac). Select **Extract string resources** from the popup menu.

## **Using views**

*   To connect your Kotlin code to a view that you defined in the layout, you need to get a reference to the view object after the view has been inflated. Assign an ID (`android:id`) to the view in the layout, then use the [`findViewById()`](https://developer.android.com/reference/android/view/View#findViewById(int)) method to get the associated view object.
*   When you create an ID for a view in the XML layout file, Android Studio creates an integer constant with that ID's name in the generated `R` class. You can then use that `R.id` reference in the `findViewById()` method.
*   You can set the attributes of a view object in your Kotlin code directly by property name. For example, the text in a text view is defined by the `android:text` attribute in the XML, and it is defined by the `text` property in Kotlin.
*   A _click handler_ is a method that is invoked when the user clicks or taps on a UI element. To attach a click-handler method to a view such as a button, use the `setOnClickListener()` method.

## **Using toasts**

A [toast](https://developer.android.com/reference/android/widget/Toast.html) is a view that shows the user a simple message in a small popup window.

To create a toast, call the [`makeText()`](https://developer.android.com/reference/android/widget/Toast.html#makeText(android.content.Context,%20int,%20int)) factory method on the [`Toast`](https://developer.android.com/reference/android/widget/Toast.html) class with three arguments:

*   The [context](https://developer.android.com/reference/android/content/Context.html) of the app `Activity`
*   The message to display, for example a string resource
*   A duration, for example [`Toast.LENGTH_SHORT`](https://developer.android.com/reference/android/widget/Toast.html#LENGTH_SHORT)

To display the toast, call `show()`.

# **Image Resource and Compability**

App resources:

*   Your app's resources can include images and icons, standard colors used in the app, strings, and XML layouts. All of those resources are stored in the `res` folder.
*   The `drawable` resources folder is where you should put all the image resources for your app.

Using vector drawables in image views:

*   Vector drawables are images described in XML format. Vector drawables are more flexible than bitmap images (such as PNG files) because they can be scaled to any size or resolution.
*   To add a drawable to your app's layout, use an `<ImageView>` element. The source of the image is in the `android:src` attribute. To refer to the drawable resource folder, use `@drawable`, for example `"@drawable/image_name"`.
*   Use the `ImageView` view in your `MainActivity` code for the image. You can use `setImageResource()` to change the view's image to a different resource. Use `R.drawable` to refer to specific drawables, for example `setImageResource(R.drawable.image_name)`.

The `lateinit` keyword:

*   Minimize the calls to `findViewById()` in your code by declaring fields to hold those views, and initializing the fields in `onCreate()`. Use the `lateinit` keyword for the field to avoid needing to declare it nullable.

The `tools` namespace for design-time attributes:

*   Use the `tools:src` attribute in the `<ImageView>` element in your layout to display an image in only Android Studio's preview or design editor. You can then use an empty image for `android:src` for the final app.
*   Use the `tools` namespace in the Android layout file to create placeholder content or hints for layout in Android Studio. Data declared by `tools` attributes is not used in the final app.

API levels:

*   Each Android OS has an official version number and name (for example Android 9.0, "Pie") and an API level (API 28). Use the API levels in your app's Gradle files to indicate the versions of Android your app supports.
*   The `compileSdkVersion` parameter in the `build.gradle` file specifies the Android API level that Gradle should use to compile your app.
*   The `targetSdkVersion` parameter specifies the most recent API level that you have tested your app against. In many cases this parameter has the same value as `compileSdkVersion`.
*   The `minSdkVersion` parameter specifies the oldest API level your app can run on.

Android Jetpack:

*   Android Jetpack is a collection of libraries, developed by Google, that offers backward-compatible classes and helpful functions for supporting older versions of Android. Jetpack replaces and expands on the set of libraries formerly known as the Android Support Library.
*   Classes imported from the `androidx` package refer to the Jetpack libraries. Dependencies to Jetpack in your `build.gradle` file also start with `androidx`.

Backward compatibility for vector drawables:

*   Vector drawables are only natively supported in versions of Android higher than API 21\. In older versions, Gradle generates PNG images for those drawables when your app is built.
*   You can specify that the Android Support Library should be used for vector drawables in older API versions with the `vectorDrawables.useSupportLibrary = true` configuration parameter in the `build.gradle` file.
*   Once you've enabled the support library for vector drawables, use the `app:srcCompat` attribute in the `<ImageView>` element (instead of `android:src`) to specify the vector drawable source for that image.

The `app` namespace:

*   The `app` namespace in your XML layout file is for attributes that come from either your custom code or from libraries, not from the core Android framework.

# **Additional Information**
*   Official Android developer documentation is at [developer.android.com](http://developer.android.com/index.html).
*   _Material Design_ is a conceptual design philosophy that outlines how apps should look and function on mobile devices. Material Design isn't just for Android apps. The Material Design guidelines are at [material.io](https://material.io/).
*   Android Studio provides templates for common and recommended app and activity designs. These templates offer working code for common use cases.
*   When you create a project, you can choose a template for your first activity.
*   While you are developing your app, you can create activities and other app components from built-in templates.
*   [Google Samples](https://github.com/googlesamples) contains code samples that you can study, copy, and incorporate into your projects.

# **Linear Layout using the Layout Editor**

*   A `ViewGroup` is a view that can contain other views. [`LinearLayout`](https://developer.android.com/reference/android/widget/LinearLayout) and [`ScrollView`](https://developer.android.com/reference/android/widget/ScrollView) are view groups.
*   `LinearLayout` is a view group that arranges its child views horizontally or vertically.
*   Use a `ScrollView` when you need to display content on the screen, such as long text or a collection of images. A scroll view can contain only one child view. If you want to scroll more than one view, then add a `ViewGroup` such as a `LinearLayout` to the `ScrollView`, and put the views to be scrolled inside that `ViewGroup`.
*   The [Layout Editor](https://developer.android.com/studio/write/layout-editor) is a visual design editor inside Android Studio. You can use the Layout Editor to build your app's layout by dragging UI elements into the layout.
*   A [style](https://developer.android.com/guide/topics/ui/look-and-feel/themes) is a collection of attributes that specify the appearance for a view. For example, a style can specify font color, font size, background color, padding, and margin.
*   You can extract and collect all the formatting of a view into a style. To give your app a consistent look, reuse the style for other views.

# **Add User Interactivity**
*   The [Layout Editor](https://developer.android.com/studio/write/layout-editor) tool in Android Studio is a visual design editor. You can use the Layout Editor to build your app's layout by dragging UI elements into your layout.
*   [`EditText`](https://developer.android.com/reference/android/widget/EditText) is a UI element that lets the user enter and modify text.
*   A [`Button`](https://developer.android.com/reference/android/widget/Button) is a UI element that the user can tap to perform an action. A button can consist of text, an icon, or both text and an icon.

Click listeners

*   You can make any `View` respond to being tapped by adding a click listener to it.
*   The function that defines the click listener receives the `View` that is clicked.

You can attach a click-listener function to a `View` in either of two ways:

*   In the XML layout, add the [`android:onClick`](https://developer.android.com/reference/android/R.attr.html#onClick) attribute to the `<`_`View`_`>` element.
*   Programmatically, use the [`setOnClickListener(View.OnClickListener)`](https://developer.android.com/reference/android/view/View.html#setOnClickListener(android.view.View.OnClickListener)) function in the corresponding `Activity`.

# **ConstraintLayout using the Layout Editor**

*   A [`ConstraintLayout`](https://developer.android.com/reference/android/support/constraint/ConstraintLayout.html) is a [`ViewGroup`](http://developer.android.com/reference/android/view/ViewGroup.html) that allows you to position and size the layout's child views in a flexible way.
*   In a ConstraintLayout, each view's position is defined using at least one horizontal constraint, and at least one vertical constraint*.*
*   A [constraint](https://developer.android.com/training/constraint-layout/#constraints-overview) connects or aligns a view to another UI element, to the parent layout, or to an invisible guideline.

Advantages of using `ConstraintLayout`:

*   You can make a ConstraintLayout responsive to devices that have different screen sizes and resolutions.
*   `ConstraintLayout` usually results in a flatter view hierarchy than `LinearLayout`.
*   The design editor and the view inspector in Android Studio help you add and configure constraints.

Chains:

*   A [chain](https://developer.android.com/training/constraint-layout/#constrain-chain) is a group of views that are linked to each other with bidirectional constraints.
*   The views within a chain can be distributed either vertically or horizontally.

Design-time attributes:

*   Design-time attributes are used and applied only during the layout design, not at runtime. When you run the app, design-time attributes are ignored.
*   Design-time attributes are prefixed with the `tools` namespace. For example, the `tools:layout_editor_absoluteY` and `tools:text` attributes are design-time attributes.

Baseline constraints:

*   A baseline constraint aligns a view's text baseline to the text baseline of another view that has text.
*   Baseline constraints are helpful when views have different font sizes.

# **Data Binding Basic**
Steps to use data binding to replace calls to `findViewById()`:

1.  Enable data binding in the android section of the `build.gradle` file: `buildFeatures {` `dataBinding true` `}`

1.  Use `<layout>` as the root view in your XML layout.
2.  Define a binding variable: `private lateinit var binding: ActivityMainBinding`
3.  Create a binding object in `MainActivity`, replacing `setContentView`: `binding = DataBindingUtil.setContentView(this, R.layout.activity_main)`
4.  Replace calls to `findViewById()` with references to the view in the binding object. For example: `findViewById<Button>(R.id.done_button)` ⇒ `binding.doneButton` (In the example, the name of the view is generated camel case from the view's `id` in the XML.)

Steps for binding views to data:

1.  Create a data class for your data.
2.  Add a `<data>` block inside the `<layout>` tag.
3.  Define a `<variable>` with a name, and a type that is the data class.

```kotlin
    <data>   
        <variable       
        name="myName"       
        type="com.example.android.aboutme.MyName" />
    </data>
```

1.  In `MainActivity`, create a variable with an instance of the data class. For example: `private val myName: MyName = MyName("Aleks Haecky")`
2.  In the binding object, set the variable to the variable you just created: `binding.myName = myName`
3.  In the XML, set the content of the view to the variable that you defined in the `<data>` block. Use dot notation to access the data inside the data class. `android:text="@={myName.name}"`
