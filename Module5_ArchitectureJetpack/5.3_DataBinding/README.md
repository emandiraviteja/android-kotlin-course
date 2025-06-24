# ✅  5.3 Data Binding

---

## 👉️ What is Data Binding?

Data Binding is a Jetpack library that allows you to bind UI components in your XML layout directly to data sources (like ViewModel or variables) in a type-safe and efficient way.

It reduces the need for `findViewById()` or `ViewBinding`, and supports both one-way and two-way data binding.

### ⚡ Enabling Data Binding

In your build.gradle (app level):

```
android {
    buildFeatures {
        dataBinding true
    }
}
```

---

## 👉️ Binding UI Layouts to Data (One-way Data Binding)

One-way data binding lets you display data in your XML from a data source (like a ViewModel) in one direction only — from data to UI.

XML: activity_main.xml

```
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>
        <variable
            name="user"
            type="com.example.User" />
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:padding="16dp">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{user.name}" />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{user.email}" />
    </LinearLayout>
</layout>
```

Kotlin: MainActivity.kt

```
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val user = User("Ravi", "ravi@example.com")
        binding.user = user
    }
}
```

User.kt
```
data class User(val name: String, val email: String)
```

**Now the UI will show the user's name and email automatically from the data object.**

---

## 👉️ Two-way Data Binding

Two-way data binding allows the UI and the data model to stay in sync — changes in the UI update the data, and changes in the data update the UI.

Used mainly with form inputs like `EditText`.

XML: activity_main.xml
```
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <data>
        <variable
            name="viewModel"
            type="com.example.UserViewModel" />
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:padding="16dp">

        <EditText
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@={viewModel.name}" />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{viewModel.name}" />
    </LinearLayout>
</layout>
```

ViewModel: UserViewModel.kt
```
class UserViewModel : ViewModel() {
    val name = MutableLiveData<String>()
}
```

Activity: MainActivity.kt
```
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}
```

**@={} vs @{}**

| Syntax | Type    | Use                         |
| ------ | ------- | --------------------------- |
| `@{}`  | One-way | From ViewModel ➝ View       |
| `@={}` | Two-way | View ↔ ViewModel (LiveData) |


**Summary**

| Concept              | Description                                                      |
| -------------------- | ---------------------------------------------------------------- |
| One-way Data Binding | Binds data to UI elements. Updates when data changes.            |
| Two-way Data Binding | Syncs data and UI both ways. Best for form inputs like EditText. |
| Benefits             | Less boilerplate, cleaner code, tightly integrated with MVVM.    |

---

## 👉 Mini-project using two-way data binding with form input, validation, and MVVM architecture.

**Features:**
Name and Email input using EditText
Real-time updates to ViewModel via two-way binding
Simple form validation (e.g., name/email not empty)
UI shows error messages if inputs are invalid

**Project Structure**

com.example.userformbinding
├── model
│   └── User.kt
├── viewmodel
│   └── UserViewModel.kt
├── ui
│   ├── MainActivity.kt
│   └── activity_main.xml

**STEP 1. Enable Data Binding**
In build.gradle (app level):

```
android {
    buildFeatures {
        dataBinding true
    }
}
```

**STEP 2. Add ViewModel Dependency**
In build.gradle (app level):
```
dependencies {
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0'
    implementation 'androidx.databinding:databinding-runtime:8.4.0'
}
```

**STEP 3. Model – `User.kt`**
```
package com.example.userformbinding.model

data class User(
    var name: String = "",
    var email: String = ""
)
```

**STEP 4. ViewModel – `UserViewModel.kt`**
```
package com.example.userformbinding.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val errorMessage = MutableLiveData<String>()
    val successMessage = MutableLiveData<String>()

    fun onSubmit() {
        val currentName = name.value ?: ""
        val currentEmail = email.value ?: ""

        if (currentName.isBlank() || currentEmail.isBlank()) {
            errorMessage.value = "Please fill all fields"
            successMessage.value = ""
        } else {
            errorMessage.value = ""
            successMessage.value = "Form submitted: $currentName, $currentEmail"
        }
    }
}
```

**STEP 5. XML – `activity_main.xml`**
```
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>
        <variable
            name="viewModel"
            type="com.example.userformbinding.viewmodel.UserViewModel" />
    </data>

    <ScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:padding="16dp">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">

            <EditText
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:hint="Name"
                android:text="@={viewModel.name}" />

            <EditText
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:hint="Email"
                android:text="@={viewModel.email}" />

            <Button
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Submit"
                android:onClick="@{() -> viewModel.onSubmit()}" />

            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="@{viewModel.errorMessage}"
                android:textColor="@android:color/holo_red_dark" />

            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="@{viewModel.successMessage}"
                android:textColor="@android:color/holo_green_dark" />
        </LinearLayout>
    </ScrollView>
</layout>
```

**STEP 6. Activity – `MainActivity.kt`**
```
package com.example.userformbinding.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.userformbinding.R
import com.example.userformbinding.databinding.ActivityMainBinding
import com.example.userformbinding.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
}
```

**Done!**
This mini-project demonstrates:

Two-way data binding with @={}
LiveData + ViewModel architecture
Real-time validation and UI feedback