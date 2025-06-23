# ✅ 3.2 Fragments

### ⚡ Navigation Component Overview

The Navigation Component is a part of Android Jetpack used to simplify navigation between fragments or activities. It helps manage:

Fragment transactions (replacing, adding, removing)
Back stack handling
Argument passing between destinations (fragments/activities)
Type-safe navigation with Safe Args

## 👉  Navigation Graph
### ⚡What is it?

The Navigation Graph (nav_graph.xml) is an XML file that shows all destinations (fragments/activities) and how users can navigate between them.

**Why use it?**
Visual overview of app navigation
No need to write FragmentTransaction manually
Auto-handles back stack
Integrates easily with Toolbar, Bottom Navigation, etc.


**How to create it?**

Step 1: Add Dependencies in build.gradle

```
// For Navigation Component
implementation "androidx.navigation:navigation-fragment-ktx:2.7.7"
implementation "androidx.navigation:navigation-ui-ktx:2.7.7"
```

Step 2: Create the Navigation Graph

Right click res > New > Android Resource File
Name: nav_graph.xml, Resource type: navigation
Inside res/navigation/nav_graph.xml, add:

```
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
app:startDestination="@id/homeFragment">

    <fragment
        android:id="@+id/homeFragment"
        android:name="com.example.app.ui.HomeFragment"
        android:label="Home" >
        <action
            android:id="@+id/action_homeFragment_to_detailFragment"
            app:destination="@id/detailFragment" />
    </fragment>

    <fragment
        android:id="@+id/detailFragment"
        android:name="com.example.app.ui.DetailFragment"
        android:label="Details" />
</navigation>
```

Step 3: Add NavHostFragment in activity_main.xml

```
<androidx.fragment.app.FragmentContainerView
android:id="@+id/nav_host_fragment"
android:name="androidx.navigation.fragment.NavHostFragment"
android:layout_width="match_parent"
android:layout_height="match_parent"
app:navGraph="@navigation/nav_graph"
app:defaultNavHost="true" />
```

Step 4: Navigate in Kotlin Code
```
findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
```
Or with view.findNavController()

---

## 👉️ Safe Args Plugin
### ⚡What is it?

Safe Args is a Gradle plugin that generates type-safe classes to pass data between fragments.

**Why use it?**
No need to use Bundles manually
Prevents runtime key errors
Ensures type safety (compile-time checking)

**How to setup?**
Add Safe Args Plugin

In Project build.gradle:
```
buildscript {
dependencies {
classpath "androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7"
}
}
```

In App build.gradle:
```
plugins {
id 'androidx.navigation.safeargs.kotlin'
}
```

### ⚡ Example: Passing data

From HomeFragment to DetailFragment

In nav_graph.xml:
```
<fragment
android:id="@+id/detailFragment"
android:name="com.example.app.ui.DetailFragment"
android:label="Details">
<argument
android:name="userName"
app:argType="string" />
</fragment>
```

In HomeFragment.kt:
```
val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment("Raviteja")
findNavController().navigate(action)
```
In DetailFragment.kt:

```
val name = arguments?.let {
DetailFragmentArgs.fromBundle(it).userName
}
```

Or with val args: DetailFragmentArgs by navArgs()

---

## 👉️ Back Stack Handling
### ⚡ What is it?

Back stack is a stack of previous fragments a user navigated through. Android handles it automatically using Navigation Component.

**Why it matters?**
You can control whether a screen should go back to previous or clear back stack
You can prevent certain fragments from being returned to (e.g., splash/login)

### ⚡ Examples

Navigate and pop current fragment from back stack:
```
findNavController().popBackStack()
```
🔹 Navigate and clear back stack up to a destination:
```
findNavController().popBackStack(R.id.homeFragment, false)
```
🔹 Navigate and clear back stack completely:
```
findNavController().navigate(R.id.loginFragment) {
popUpTo(R.id.nav_graph) {
inclusive = true
}
}
```
---

**Summary Table**

| Feature             | Why Use It                           | Key Class / Tool                   |
| ------------------- | ------------------------------------ | ---------------------------------- |
| Navigation Graph    | Visualize and manage fragment routes | `nav_graph.xml`, `NavHostFragment` |
| Safe Args Plugin    | Safe data passing between fragments  | `Directions`, `Args` classes       |
| Back Stack Handling | Control how user navigates backward  | `NavController.popBackStack()`     |


---

### ⚡ Mini App  - Login Flow using the Navigation Component

**Login Flow App Features**
Splash Screen (3 sec auto redirect)
Login Screen → accepts username
Home Screen → shows "Welcome, {username}"
Logout → clears backstack and goes to Login

**TECH STACK**
Language: Kotlin
Navigation Component
Safe Args
Back Stack Handling


Navigation Flow
SplashFragment → LoginFragment → HomeFragment
↘︎    ↖︎
(Safe Args)

**Step-by-Step Implementation**

Dependencies in app/build.gradle
```
buildscript {
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
    }
}
```
```
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("androidx.navigation.safeargs.kotlin") // Safe Args plugin
}
```

```
dependencies {
implementation ("androidx.navigation:navigation-fragment-ktx:2.7.7")
implementation ("androidx.navigation:navigation-ui-ktx:2.7.7")
}
```

Create nav_graph.xml

```
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
app:startDestination="@id/splashFragment">

    <fragment
        android:id="@+id/splashFragment"
        android:name="com.example.loginflow.SplashFragment"
        android:label="Splash">
        <action
            android:id="@+id/action_splashFragment_to_loginFragment"
            app:destination="@id/loginFragment" />
    </fragment>

    <fragment
        android:id="@+id/loginFragment"
        android:name="com.example.loginflow.LoginFragment"
        android:label="Login">
        <action
            android:id="@+id/action_loginFragment_to_homeFragment"
            app:destination="@id/homeFragment" />
    </fragment>

    <fragment
        android:id="@+id/homeFragment"
        android:name="com.example.loginflow.HomeFragment"
        android:label="Home">
        <argument
            android:name="username"
            app:argType="string" />
    </fragment>
</navigation>
```

activity_main.xml

```
<androidx.fragment.app.FragmentContainerView
android:id="@+id/nav_host_fragment"
android:name="androidx.navigation.fragment.NavHostFragment"
android:layout_width="match_parent"
android:layout_height="match_parent"
app:navGraph="@navigation/nav_graph"
app:defaultNavHost="true" />
```

Fragments Code

```
class SplashFragment : Fragment(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }, 3000)
    }
}
```

LoginFragment.kt
```
class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val edtUsername = view.findViewById<EditText>(R.id.edtUsername)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val username = edtUsername.text.toString()
            if (username.isNotBlank()) {
                val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment(username)
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "Enter username", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
```

HomeFragment.kt
```
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val args: HomeFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.txtWelcome).text = "Welcome, ${args.username}"

        view.findViewById<Button>(R.id.btnLogout).setOnClickListener {
            findNavController().navigate(R.id.loginFragment) {
                popUpTo(R.id.nav_graph) { inclusive = true }
            }
        }
    }
}
```

**Layout XML Files**

fragment_splash.xml

```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:orientation="vertical" android:gravity="center"
android:layout_width="match_parent" android:layout_height="match_parent">
<TextView android:text="Splash Screen" android:textSize="20sp"
android:layout_width="wrap_content" android:layout_height="wrap_content"/>
</LinearLayout>
```

fragment_login.xml
```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:orientation="vertical" android:padding="16dp" android:gravity="center"
android:layout_width="match_parent" android:layout_height="match_parent">

    <EditText android:id="@+id/edtUsername"
        android:hint="Enter Username" android:layout_width="match_parent"
        android:layout_height="wrap_content"/>

    <Button android:id="@+id/btnLogin" android:text="Login"
        android:layout_width="wrap_content" android:layout_height="wrap_content"/>
</LinearLayout>
```

fragment_home.xml
```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:orientation="vertical" android:gravity="center"
android:layout_width="match_parent" android:layout_height="match_parent">

    <TextView android:id="@+id/txtWelcome" android:textSize="20sp"
        android:layout_width="wrap_content" android:layout_height="wrap_content"/>

    <Button android:id="@+id/btnLogout" android:text="Logout"
        android:layout_width="wrap_content" android:layout_height="wrap_content"/>
</LinearLayout>
```

Summary of Key Concepts Used:
| Feature             | Purpose                                 |
| ------------------- | --------------------------------------- |
| Navigation Graph    | Handles flow from splash → login → home |
| Safe Args           | Passes username safely to HomeFragment  |
| Back Stack Handling | Clears backstack on logout              |
| Handler             | Delays splash screen transition         |
