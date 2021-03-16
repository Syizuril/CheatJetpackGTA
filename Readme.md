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

**Navigation**
* [Fragment](#fragment)
* [Define Navigation Paths](#Define-Navigation-Paths)
* [Start an external Activity](#Start-an-external-Activity)

**Activity and Fragment Lifecycle**
* [Activity and Fragment Lifecycles](#Activity-and-Fragment-Lifecycles)
* [Complex Lifecycle](#Complex-Lifecycle)


**Architecture components**
* [ViewModel](#ViewModel)
* [Livedata and Livedata Observer](#Livedata-and-Livedata-Observer)

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

# **Fragment**
*   A _Fragment_ is a modular section of an activity.
*   A Fragment has its own lifecycle and receives its own input events.
*   Use the `<fragment>` tag to define the layout for the Fragment in the XML layout file.
*   Inflate the layout for a Fragment in `onCreateView()`.
*   You can add or remove a Fragment while the activity is running.

# **Define Navigation Paths**

## Navigation components

To use the Android _navigation library_, you need to do some setup:

*   Add dependencies for `navigation-fragment-ktx` and `navigation-ui-ktx` in the module-level `build.gradle` file.
*   Add an `ext` variable for the `navigationVersion` in the project-level `build.gradle` file.

_Navigation destinations_ are fragments, activities, or other app components that the user navigates to. A _navigation graph_ defines the possible paths from one navigation destination to the next.

*   To create a navigation graph, create a new Android resource file of type **Navigation**. This file defines the navigation flow through the app. The file is in the `res/navigation` folder, and it's typically called `navigation.xml`.
*   To see the navigation graph in the Navigation Editor, open the `navigation.xml` file and click the **Design** tab.
*   Use the Navigation Editor to add destinations such as fragments to the navigation graph.
*   To define the path from one destination to another, use the Navigation Graph to create an action that connects the destinations. In the `navigation.xml` file, each of these connections is represented as an `action` that has an `ID`.

A _navigation host Fragment_, usually named `NavHostFragment`, acts as a host for the fragments in the navigation graph:

*   As the user moves between destinations defined in the navigation graph, the `NavHostFragment` swaps the fragments in and out and manages the Fragment back stack.
*   In the `activity_main.xml` layout file, the `NavHostFragment` is represented by a `fragment` element with the name `android:name="androidx.navigation.fragment.NavHostFragment"`.

To define which Fragment is displayed when the user taps a view (for example a button), set the `onClick` listener for the view:

*   In the `onClick` listener, call `findNavController().navigate()` on the view.
*   Specify the `ID` of the `action` that leads to the destination.

_Conditional navigation_ navigates to one screen in one case, and to a different screen in another case. To create conditional navigation:

1.  Use the Navigation Editor to create a connection from the starting Fragment to each of the possible destination fragments.
2.  Give each connection a unique ID.
3.  In the click-listener method for the `View`, add code to detect the conditions. Then call `findNavController().navigate()` on the view, passing in the ID for the appropriate action.

## The Back button

The system's _Back button_ is usually at the bottom of the device. By default, the Back button navigates the user back to the screen they viewed most recently. In some situations, you can control where the Back button takes the user:

*   In the Navigation Editor, you can use the **Attributes** pane to change an action's **popUpTo** setting. This setting removes destinations from the back stack, which has the effect of determining where the Back button takes the user.
*   The **popUpTo** setting appears as the `popUpTo` attribute in the `navigation.xml` file.

![1f8e86b02d795270.png](https://developer.android.com/codelabs/kotlin-android-training-add-navigation/img/1f8e86b02d795270.png)

*   Selecting the **popUpToInclusive** checkbox sets the `popUpToInclusive` attribute to `true`. All destinations up to and _including_ this destination are removed from the back stack.
*   If an action's `popUpTo` attribute is set to the app's starting destination and `popUpToInclusive` is set to `true`, the Back button takes the user all the way out of the app.

## The Up button

Screens in an Android app can have an on-screen _Up button_ that appears at the top left of the [_app bar_](https://developer.android.com/topic/libraries/architecture/navigation/navigation-ui#top_app_bar). (The app bar is sometimes called the _action bar._) The Up button navigates "upwards" within the app's screens, based on the hierarchical relationships between screens.

The navigation controller's [`NavigationUI`](https://developer.android.com/topic/libraries/architecture/navigation/navigation-ui) library integrates with the app bar to allow the user to tap the Up button on the app bar to get back to the app's home screen from anywhere in the app.

To link the navigation controller to the app bar:

1.  In `onCreate()`, call `setupActionBarWithNavController()` on the `NavigationUI` class, passing in the navigation controller:

```kotlin

    val navController = this.findNavController(R.id.myNavHostFragment)
    NavigationUI.setupActionBarWithNavController(this,navController)

```

1.  Override the `onSupportNavigateUp()` method to call `navigateUp()` in the navigation controller:

```kotlin

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()    
        }
    }

```

## The options menu

The _options menu_ is a menu that the user accesses from the app bar by tapping the icon with the three vertical dots ![4cdd17fa43bfbe6.png](https://developer.android.com/codelabs/kotlin-android-training-add-navigation/img/4cdd17fa43bfbe6.png). To create an options menu with a menu item that displays a Fragment, make sure the Fragment has an ID. Then define the options menu and code the `onOptionsItemSelected()` handler for the menu items.

1.  Make sure the Fragment has an ID:

*   Add the destination Fragment to the navigation graph and note the ID of the Fragment. (You can change the ID if you like.)

1.  Define the options menu:

*   Create an Android resource file of type **Menu**, typically named `options_menu.xml`. The file is stored in the **Res > Menu** folder.
*   Open the `options_menu.xml` file in the design editor and drag a **Menu Item** widget from the **Palette** pane to the menu.
*   For convenience, make the ID of the menu item the same as the ID of the Fragment to display when the user clicks this menu item. This step is not required, but it makes it easier to code the `onClick` behavior for the menu item.

1.  Code the `onClick` handler for the menu item:

*   In the Fragment or Activity that displays the options menu, in `onCreateView()`, call `setHasOptionsMenu(true)` to enable the options menu.
*   Implement `onCreateOptionsMenu()` to inflate the options menu:

```kotlin

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

```

*   Override the `onOptionsItemSelected()` method to take the appropriate action when the menu item is clicked. The following code displays the Fragment that has the same ID as the menu item. (This code only works if the menu item and the Fragment have identical ID values.)

```kotlin

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,requireView().findNavController()) || super.onOptionsItemSelected(item)
    }

```

## The navigation drawer

The _navigation drawer_ is a panel that slides out from the edge of the screen. There are two ways for the user to open the navigation drawer:

*   Swipe from the starting edge (usually the left) on any screen.
*   Use the drawer button (three lines) ![7277f85db3a1ad13.png](https://developer.android.com/codelabs/kotlin-android-training-add-navigation/img/7277f85db3a1ad13.png) on the app bar at the top of the app.

To add a navigation drawer to your app:

1.  Add dependencies to `build.gradle (app)`.
2.  Make sure each destination Fragment has an ID.
3.  Create the menu for the drawer.
4.  Add the drawer to the layout for the Fragment.
5.  Connect the drawer to the navigation controller.
6.  Set up the drawer button in the app bar.

These steps are explained in more detail below.

1.  Add dependencies to `build.gradle`:

*   The navigation drawer is part of the Material Components for Android library. Add the Material library to the `build.gradle (app)` file:

```kotlin

    dependencies {   
        ...
        implementation "com.google.android.material:material:$supportlibVersion"
        ...
    }

```

1.  Give each destination Fragment an ID:

*   If a Fragment is reachable from the navigation drawer, open it in the navigation graph to make sure that it has an ID.

1.  Create the menu for the drawer:

*   Create an Android resource file of type **Menu** (typically called `navdrawer_menu`) for a navigation drawer menu. This creates a new `navdrawer_menu.xml` file in the `Res > Menu` folder.
*   In the design editor, add **Menu Item** widgets to the **Menu**.

1.  Add the drawer to the layout for the Fragment:

*   In the layout that contains the navigation host Fragment (which is typically the main layout), use `<androidx.drawerlayout.widget.DrawerLayout>` as the root view.
*   Add a `<com.google.android.material.navigation.NavigationView>` view to the layout.

1.  Connect the drawer to the navigation controller:

*   Open the Activity that creates the navigation controller. (The main Activity is typically the one you want here.) In `onCreate()`, use `NavigationUI.setupWithNavController()`to connect the navigation drawer with the navigation controller:

```kotlin

    val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    NavigationUI.setupWithNavController(binding.navView, navController)

```

1.  Set up the drawer button in the app bar:

*   In `onCreate()` in the Activity that creates the navigation controller (which is typically the main Activity), pass the drawer layout as the third parameter to `NavigationUI.setupActionBarWithNavController`:

```kotlin

    val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)

```

*   To make the Up button work with the drawer button, edit `onSupportNavigateUp()` to return `NavigationUI.navigateUp()`. Pass the navigation controller and the drawer layout to `navigateUp()`.

```kotlin

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

```

# **Start an external Activity**
Safe Args:

*   To help catch errors caused by missing keys or mismatched types when you pass data from one Fragment to another, use a Gradle plugin called [_Safe Args_](https://developer.android.com/topic/libraries/architecture/navigation/navigation-pass-data#Safe-args).
*   For each Fragment in your app, the Safe Args plugin generates a corresponding `NavDirection` class. You add the `NavDirection` class to the Fragment code, then use the class to pass arguments between the Fragment and other fragments.
*   The `NavDirection` classes represent navigation from all the app's actions.

Implicit intents:

*   An [_implicit intent_](https://developer.android.com/training/basics/intents/sending) declares an action that your app wants some other app (such as a camera app or email app) to perform on its behalf.
*   If several Android apps could handle an implicit intent, Android shows the user a chooser. For example, when the user taps the **share** icon in the AndroidTrivia app, the user can select which app they want to use to share their game results.
*   To build an intent, you declare an action to perform, for example [`ACTION_SEND`](https://developer.android.com/reference/android/content/Intent.html#ACTION_SEND).
*   Several [`Intent()`](https://developer.android.com/reference/android/content/Intent.html#public-constructors_1) constructors are available to help you build intents.

Sharing functionality:

*   In the case of sharing your success with your friends, the `Intent` action would be `Intent.ACTION_SEND.`
*   To add an options menu to a Fragment, set the [`setHasOptionsMenu()`](https://developer.android.com/reference/android/support/v4/app/Fragment#sethasoptionsmenu) method to `true` in the Fragment code.
*   In the Fragment code, override the `onCreateOptionsMenu()` method to inflate the menu.
*   Override the `onOptionsItemSelected()` to use `startActivity()` to send the `Intent` to other apps that can handle it.

When the user taps the menu item, the intent is fired, and the user sees a chooser for the `SEND` action.

# **Activity and Fragment Lifecycles**
## **Activity lifecycle**

*   The _activity lifecycle_ is a set of states through which an activity migrates. The activity lifecycle begins when the activity is first created and ends when the activity is destroyed.
*   As the user navigates between activities and inside and outside of your app, each activity moves between states in the activity lifecycle.
*   Each state in the activity lifecycle has a corresponding callback method you can override in your `Activity` class. There are seven lifecycle methods: [`onCreate()`](https://developer.android.com/reference/android/app/Activity.html#onCreate(android.os.Bundle))[`onStart()`](https://developer.android.com/reference/android/app/Activity.html#onStart())[`onPause()`](https://developer.android.com/reference/android/app/Activity.html#onPause())[`onRestart()`](https://developer.android.com/reference/android/app/Activity.html#onRestart())[`onResume()`](https://developer.android.com/reference/android/app/Activity.html#onResume())[`onStop()`](https://developer.android.com/reference/android/app/Activity.html#onStop())[`onDestroy()`](https://developer.android.com/reference/android/app/Activity.html#onDestroy())
*   To add behavior that occurs when your activity transitions into a lifecycle state, override that state's callback method.
*   To add skeleton override methods to your classes in Android Studio, select **Code > Override Methods** or press `Control+o`.

## **Logging with Log**

*   The Android logging API, and specifically the `Log` class, enables you to write short messages that are displayed in the Logcat within Android Studio.
*   Use `Log.i()` to write an informational message. This method takes two arguments: the log _tag_, typically the name of the class, and the log _message_, a short string.
*   Use the **Logcat** pane in Android Studio to view the system logs, including the messages you write.

## **Logging with Timber**

[`Timber`](https://github.com/JakeWharton/timber) is a logging library with several advantages over the Android logging API. In particular, the `Timber` library:

*   Generates the log tag for you based on the class name.
*   Helps avoid showing logs in a release version of your Android app.
*   Allows for integration with crash reporting libraries.

To use `Timber`, add its dependency to your Gradle file and extend the [`Application`](https://developer.android.com/reference/android/app/Application) class to initialize it:

*   `Application` is a base class that contains global application state for your entire app. There is a default `Application` class that is used by Android if you don't specify one. You can create your own `Application` subclass to initialize app-wide libraries such as `Timber`.
*   Add your custom `Application` class to your app by adding the `android:name` attribute to the `<application>` element in the Android manifest. Do not forget to do this!
*   Use `Timber.i()` to write log messages with `Timber`. This method only takes one argument: the message to write. The log tag (the name of the class) is added for you automatically.

# **Complex Lifecycle**
## **Lifecycle tips**

*   If you set up or start something in a lifecycle callback, stop or remove that thing in the corresponding callback. By stopping the thing, you make sure it doesn't keep running when it's no longer needed. For example, if you set up a timer in `onStart()`, you need to pause or stop the timer in `onStop()`.
*   Use `onCreate()` only to initialize the parts of your app that run once, when the app first starts. Use `onStart()` to start the parts of your app that run both when the app starts, and each time the app returns to the foreground.

## **Lifecycle library**

*   Use the Android lifecycle library to shift lifecycle control from the activity or fragment to the actual component that needs to be lifecycle-aware.
*   Lifecycle _owners_ are components that have (and thus "own") lifecycles, including `Activity` and `Fragment`. Lifecycle owners implement the `LifecycleOwner` interface.
*   Lifecycle _observers_ pay attention to the current lifecycle state and perform tasks when the lifecycle changes. Lifecycle observers implement the `LifecycleObserver` interface.
*   `Lifecycle` objects contain the actual lifecycle states, and they trigger events when the lifecycle changes.

To create a lifecycle-aware class:

*   Implement the `LifecycleObserver` interface in classes that need to be lifecycle-aware.
*   Initialize a lifecycle observer class with the lifecycle object from the activity or fragment.
*   In the lifecycle observer class, annotate lifecycle-aware methods with the lifecycle state change they are interested in.

For example, the `@OnLifecycleEvent(Lifecycle.Event.ON_START)`annotation indicates that the method is watching the `onStart` lifecycle event.

## **Process shutdowns and saving activity state**

*   Android regulates apps running in the background so that the foreground app can run without problems. This regulation includes limiting the amount of processing that apps in the background can do, and sometimes even shutting down your entire app process.
*   The user cannot tell if the system has shut down an app in the background. The app still appears in the recents screen and should restart in the same state in which the user left it.
*   The Android Debug Bridge (`adb`) is a command-line tool that lets you send instructions to emulators and devices attached to your computer. You can use `adb` to simulate a process shutdown in your app.
*   When Android shuts down your app process, the `onDestroy()` lifecycle method is not called. The app just stops.

## **Preserving activity and fragment state**

*   When your app goes into the background, just after `onStop()` is called, app data is saved to a bundle. Some app data, such as the contents of an `EditText`, is automatically saved for you.
*   The bundle is an instance of `Bundle`, which is a collection of keys and values. The keys are always strings.
*   Use the `onSaveInstanceState()` callback to save other data to the bundle that you want to retain, even if the app was automatically shut down. To put data into the bundle, use the bundle methods that start with `put`, such as `putInt()`.
*   You can get data back out of the bundle in the `onRestoreInstanceState()` method, or more commonly in `onCreate()`. The `onCreate()` method has a `savedInstanceState` parameter that holds the bundle.
*   If the `savedInstanceState` variable contains `null`, the activity was started without a state bundle and there is no state data to retrieve.
*   To retrieve data from the bundle with a key, use the `Bundle` methods that start with `get`, such as `getInt()`.

## **Configuration changes**

*   A _configuration change_ happens when the state of the device changes so radically that the easiest way for the system to resolve the change is to shut down and rebuild the activity.
*   The most common example of a configuration change is when the user rotates the device from portrait to landscape mode, or from landscape to portrait mode. A configuration change can also occur when the device language changes or a hardware keyboard is plugged in.
*   When a configuration change occurs, Android invokes all the activity lifecycle's shutdown callbacks. Then Android restarts the activity from scratch, running all the lifecycle startup callbacks.
*   When Android shuts down an app because of a configuration change, it restarts the activity with the state bundle that is available to `onCreate()`.
*   As with process shutdown, save your app's state to the bundle in `onSaveInstanceState()`.

# **ViewModel**
*   The Android [app architecture](https://developer.android.com/jetpack/docs/guide) guidelines recommend separating classes that have different responsibilities.
*   A _UI controller_ is UI-based class like [`Activity`](https://developer.android.com/reference/android/app/Activity.html) or [`Fragment`](https://developer.android.com/reference/android/app/Fragment.html). UI controllers should only contain logic that handles UI and operating system interactions; they shouldn't contain data to be displayed in the UI. Put that data in a `ViewModel`.
*   The [`ViewModel`](https://developer.android.com/reference/android/arch/lifecycle/ViewModel.html) class stores and manages UI-related data. The `ViewModel` class allows data to survive configuration changes such as screen rotations.
*   `ViewModel` is one of the recommended [Android Architecture Components](https://developer.android.com/jetpack/#architecture-components).
*   `ViewModelProvider.Factory` is an interface you can use to create a `ViewModel` object.

The table below compares UI controllers with the `ViewModel` instances that hold data for them:

<div class="devsite-table-wrapper">

<table>

<tbody>

<tr>

<td colspan="1" rowspan="1">

**UI controller**

</td>

<td colspan="1" rowspan="1">

**ViewModel**

</td>

</tr>

<tr>

<td colspan="1" rowspan="1">

An example of a UI controller is the `ScoreFragment` that you created in this codelab.

</td>

<td colspan="1" rowspan="1">

An example of a `ViewModel` is the `ScoreViewModel` that you created in this codelab.

</td>

</tr>

<tr>

<td colspan="1" rowspan="1">

Doesn't contain any data to be displayed in the UI.

</td>

<td colspan="1" rowspan="1">

Contains data that the UI controller displays in the UI.

</td>

</tr>

<tr>

<td colspan="1" rowspan="1">

Contains code for displaying data, and user-event code such as click listeners.

</td>

<td colspan="1" rowspan="1">

Contains code for data processing.

</td>

</tr>

<tr>

<td colspan="1" rowspan="1">

Destroyed and re-created during every configuration change.

</td>

<td colspan="1" rowspan="1">

Destroyed only when the associated UI controller goes away permanently—for an activity, when the activity finishes, or for a fragment, when the fragment is detached.

</td>

</tr>

<tr>

<td colspan="1" rowspan="1">

Contains views.

</td>

<td colspan="1" rowspan="1">

Should never contain references to activities, fragments, or views, because they don't survive configuration changes, but the `ViewModel` does.

</td>

</tr>

<tr>

<td colspan="1" rowspan="1">

Contains a reference to the associated `ViewModel`.

</td>

<td colspan="1" rowspan="1">

Doesn't contain any reference to the associated UI controller.

</td>

</tr>

</tbody>

</table>

# *Livedata and Livedata Observer*
## LiveData

*   [`LiveData`](https://developer.android.com/topic/libraries/architecture/livedata) is an observable data holder class that is lifecycle-aware, one of the [Android Architecture Components](https://developer.android.com/topic/libraries/architecture).
*   You can use `LiveData` to enable your UI to update automatically when the data updates.
*   `LiveData` is observable, which means that an observer like an activity or an fragment can be notified when the data held by the `LiveData` object changes.
*   `LiveData` holds data; it is a wrapper that can be used with any data.
*   `LiveData` is lifecycle-aware, meaning that it only updates observers that are in an active lifecycle state such as [`STARTED`](https://developer.android.com/reference/android/arch/lifecycle/Lifecycle.State.html#STARTED) or [`RESUMED`](https://developer.android.com/reference/android/arch/lifecycle/Lifecycle.State.html#RESUMED).

### To add LiveData

*   Change the type of the data variables in `ViewModel` to `LiveData` or [`MutableLiveData`](https://developer.android.com/reference/android/arch/lifecycle/MutableLiveData).

`MutableLiveData` is a `LiveData` object whose value can be changed. `MutableLiveData` is a generic class, so you need to specify the type of data that it holds.

*   To change the value of the data held by the `LiveData`, use the `setValue()` method on the `LiveData` variable.

### To encapsulate LiveData

*   The `LiveData` inside the `ViewModel` should be editable. Outside the `ViewModel`, the `LiveData` should be readable. This can be implemented using a Kotlin [backing property](https://kotlinlang.org/docs/reference/properties.html#backing-properties).
*   A Kotlin backing property allows you to return something from a getter other than the exact object.
*   To encapsulate the `LiveData`, use `private` `MutableLiveData` inside the `ViewModel` and return a `LiveData` backing property outside the `ViewModel`.

### Observable LiveData

*   `LiveData` follows an observer pattern. The "observable" is the `LiveData` object, and the observers are the methods in the UI controllers, like fragments. Whenever the data wrapped inside `LiveData` changes, the observer methods in the UI controllers are notified.
*   To make the `LiveData` observable, attach an observer object to the `LiveData` reference in the observers (such as activities and fragments) using the [`observe()`](https://developer.android.com/reference/android/arch/lifecycle/LiveData.html#observe(android.arch.lifecycle.LifecycleOwner,%0Aandroid.arch.lifecycle.Observer%3CT%3E)) method.
*   This `LiveData` observer pattern can be used to communicate from the `ViewModel` to the UI controllers.