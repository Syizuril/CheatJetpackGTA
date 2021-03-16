Android Kotlin Cheatsheet
=========================

**Table of Content**
**Fundamental**
[Get Started](#get-started)
[Anatomy of Basic Android Project](#Anatomy-of-Basic-Android-Project)

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


