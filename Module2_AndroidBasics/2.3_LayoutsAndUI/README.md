# ✅ 2.3 : UI Elements & Layouts

# Android UI Elements Showcase (Kotlin)

This sample Android project demonstrates the most common **UI elements** in Android using **Kotlin**. It covers TextViews, EditTexts, Buttons, CheckBoxes, RadioButtons, Spinners, Toolbars, and more — all in one activity.

---  

## 👉 Common Views

**What are wrap_content and match_parent?**

These are values used for the layout_width and layout_height attributes of any view (like TextView, EditText, Button, etc.) in Android XML.

**wrap_content**

- Meaning: Size the view just enough to fit its content.
- The view “wraps” around its content (text, image, etc.)
- Best for views like labels, buttons, icons, etc.

**Example:**
```
<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Hello Android!" />
```

**This TextView will only be as wide/tall as needed to show “Hello Android!”**

---

**match_parent**

- Meaning: Make the view as big as its parent allows
- The view stretches to fill the entire width or height of its parent
- Useful for full-width fields, dividers, or backgrounds

**Example:**

```
<EditText
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Enter your name" />
```

```This EditText will stretch across the entire screen width, but will only be as tall as needed for one line of text.```    
<br>

| Value          | Behavior                                            |
| -------------- | --------------------------------------------------- |
| `wrap_content` | Fits content size (small as possible)               |
| `match_parent` | Expands to fill available space in parent container |

---

### ⚡ TextView

`TextView` is a **UI widget** used in Android to **display text** to the user.
It’s commonly used for labels, instructions, or any non-editable text in your layout.

- **Used For:** Labels, headings, messages.

**XML-Syntax:**
```  
<TextView  
 android:id="@+id/textView" 
 android:layout_width="wrap_content" 
 android:layout_height="wrap_content" 
 android:text="Hello, World!" />```  
```  

**Common Attributes**

| Attribute                               | Description                       |
| --------------------------------------- | --------------------------------- |
| `android:text`                          | The text to display               |
| `android:textSize`                      | Size of the text (use `sp` units) |
| `android:textColor`                     | Color of the text                 |
| `android:gravity`                       | Text alignment inside the view    |
| `android:padding`                       | Space inside the view boundary    |
| `android:maxLines`                      | Limits the number of lines        |
| `android:ellipsize`                     | Add "..." if text is too long     |
| `android:drawableLeft/Right/Top/Bottom` | Add icons next to text            |

**Access in Kotlin**
```
val textView = findViewById<TextView>(R.id.textViewHello)
textView.text = "Welcome to Android!"
```  

You can also change color, size, etc. programmatically:
```
textView.setTextColor(Color.RED)
textView.textSize = 20f
```
---

### ⚡ EditText

`EditText` is a subclass of **TextView** that allows the user to enter and edit text (e.g., name, password, email, etc.).

- **Used For:** Forms, search bars, login screens.

**XML-Syntax:**
```  
<EditText  
 android:id="@+id/editText" 
 android:layout_width="match_parent" 
 android:layout_height="wrap_content" 
 android:hint="Enter your name" />```  
```
<br>

**Common Attributes**
| Attribute                                | Description                                         |
| ---------------------------------------- | --------------------------------------------------- |
| `android:hint`                           | Shows placeholder text when empty                   |
| `android:text`                           | Default text                                        |
| `android:inputType`                      | Type of input (text, number, email, password, etc.) |
| `android:maxLength`                      | Maximum characters allowed                          |
| `android:lines` / `android:maxLines`     | Control text area height                            |
| `android:textColor` / `android:textSize` | Appearance settings                                 |

<br>

**Common inputType values**
| Value              | Purpose                       |
| ------------------ | ----------------------------- |
| `text`             | Plain text                    |
| `textPersonName`   | Person name                   |
| `textEmailAddress` | Email input                   |
| `number`           | Numbers only                  |
| `phone`            | Phone number input            |
| `textPassword`     | Obscures text (for passwords) |

<br>

**Access & Use in Kotlin**

Get value from EditText:
```  
val editText = findViewById<EditText>(R.id.editTextName)
val name = editText.text.toString() 
```  
```
Set value:
editText.setText("Hello Ravi")
```

**Example: Password Field**
```
<EditText
    android:id="@+id/editTextPassword"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Password"
    android:inputType="textPassword"
/>
```
<br>

**Example in Kotlin Activity**
```
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameEditText = findViewById<EditText>(R.id.editTextName)
        val button = findViewById<Button>(R.id.buttonSubmit)

        button.setOnClickListener {
            val name = nameEditText.text.toString()
            Toast.makeText(this, "Hello, $name", Toast.LENGTH_SHORT).show()
        }
    }
}
```

 --- 

### ⚡ Button
A `Button` is a clickable UI element used to trigger actions like submitting a form, opening a new screen, or displaying a message.

- **Used For:** Submitting forms, navigation.

**XML-Syntax:**
```  
<Button  
 android:id="@+id/button" 
 android:layout_width="wrap_content" 
 android:layout_height="wrap_content" 
 android:text="Submit" />  
```

**Common Attributes**
| Attribute                    | Description                        |
| ---------------------------- | ---------------------------------- |
| `android:text`               | Button label (visible text)        |
| `android:background`         | Change button color/image          |
| `android:textColor`          | Change text color                  |
| `android:onClick`            | Set a click method name (optional) |
| `android:enabled`            | Enable/disable the button          |
| `android:drawableLeft/Right` | Add icon inside button             |

<br>

**What is setOnClickListener?**

`setOnClickListener` is a method used to tell Android what to do when the user taps a button.
You pass a lambda or a listener object, and it executes your code when the click happens.

**Handling Clicks in Kotlin**

```
val button = findViewById<Button>(R.id.buttonSubmit)

button.setOnClickListener {
    Toast.makeText(this, "Button clicked!", Toast.LENGTH_SHORT).show()
}
```  

**Full Example with EditText and Button**

activity_main.xml

```
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <EditText
        android:id="@+id/editTextName"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Enter your name" />

    <Button
        android:id="@+id/buttonGreet"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Say Hello" />
</LinearLayout>
```

MainActivity.kt

```
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editTextName)
        val button = findViewById<Button>(R.id.buttonGreet)

        button.setOnClickListener {
            val name = editText.text.toString()
            Toast.makeText(this, "Hello, $name!", Toast.LENGTH_SHORT).show()
        }
    }
}
```
---

### ⚡ SeekBar

A SeekBar is a slider UI component that lets users select a value from a continuous range (e.g., volume, brightness, rating). It’s a subclass of ProgressBar.

- **Used For:** Volume, brightness.

**XML-Syntax:**
```
<SeekBar
    android:id="@+id/seekBarVolume"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:max="100"
    android:progress="50" />
```

**Common Attributes**
| Attribute              | Description                             |
| ---------------------- | --------------------------------------- |
| `android:max`          | Sets the maximum value (default is 100) |
| `android:progress`     | Current value                           |
| `android:thumb`        | Customize the slider thumb              |
| `android:progressTint` | Change progress color                   |

**What is OnSeekBarChangeListener?**
SeekBar.OnSeekBarChangeListener is an interface used to track events when the user:

- Starts touching the seek bar
- Moves the slider (changes the value)
- Stops touching the slider

**Methods in OnSeekBarChangeListener**

Here are the 3 callback methods you must override:
| Method                 | Called When...                                                  | Common Use                               |
| ---------------------- | --------------------------------------------------------------- | ---------------------------------------- |
| `onProgressChanged`    | The progress value changes (either by user or programmatically) | Update a `TextView`, change volume, etc. |
| `onStartTrackingTouch` | User **starts** touching the SeekBar                            | Optional: show UI hint or effect         |
| `onStopTrackingTouch`  | User **stops** touching the SeekBar                             | Save the value, apply setting, etc.      |


Handling Value Changes in Kotlin
```
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val seekBar = findViewById<SeekBar>(R.id.seekBarVolume)
        val textView = findViewById<TextView>(R.id.textViewVolume)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Called when the user changes the value
                // `progress` gives the current value
                // `fromUser` is true if user changed it, false if programmatically changed
                textView.text = "Volume: $progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Optional: when user starts moving the thumb
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Optional: when user stops moving the thumb
                Toast.makeText(applicationContext, "Selected Volume: ${seekBar?.progress}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

```

---

### ⚡ RatingBar

A RatingBar lets users rate items with stars (or other shapes). Commonly used for user reviews (e.g., 4.5 stars out of 5).

- **Used For:**
  User reviews (e.g., "Rate this app"), Product or service feedback, Rating movie, food, etc.

**XML-Syntax**
```
<RatingBar
    android:id="@+id/ratingBar"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:numStars="5"             <!-- Total stars -->
    android:rating="3.5"             <!-- Default rating -->
    android:stepSize="0.5"           <!-- Allows half-star steps -->
    android:isIndicator="false"      <!-- If true, user can't change rating -->
/>
```

**Common Attributes**

| Attribute     | Description                              |
| ------------- | ---------------------------------------- |
| `numStars`    | Total number of stars                    |
| `rating`      | Default/initial rating                   |
| `stepSize`    | Increments (e.g., 1.0, 0.5, 0.1)         |
| `isIndicator` | If true, disables user input (read-only) |

**What is setOnRatingBarChangeListener?**

The setOnRatingBarChangeListener is a method of RatingBar that lets you listen for changes in the user's rating input (like when they tap a star).

**Kotlin Code with Listener**

```
val ratingBar = findViewById<RatingBar>(R.id.ratingBar)

ratingBar.setOnRatingBarChangeListener { bar, rating, fromUser ->
     // ratingBar: the RatingBar view
    // rating: the current float value (e.g. 4.5)
    // fromUser: true if user changed it, false if changed in code
    Log.d("RatingBar", "Rating: $rating, From User: $fromUser")
    Toast.makeText(this, "You rated: $rating stars", Toast.LENGTH_SHORT).show()
}
```

---

### ⚡ CheckBox

A CheckBox in Android is a UI widget that allows the user to select one or more options from a set. Each box can be checked (true) or unchecked (false).

- **Used For:** Accepting terms and conditions, Selecting multiple options (e.g., hobbies, food preferences)

**XML-Syntax**

```  
<CheckBox  
 android:id="@+id/checkBox" 
 android:layout_width="wrap_content" 
 android:layout_height="wrap_content" 
 android:text="Accept Terms" />
```  

**Common Attributes**

| Attribute          | Purpose                           |
| ------------------ | --------------------------------- |
| `android:text`     | Label shown to the user           |
| `android:checked`  | Initial state (checked/unchecked) |
| `android:textSize` | Controls how big the text appears |

**What is OnCheckedChangeListener?**

OnCheckedChangeListener is an interface used to listen for check/uncheck events on CheckBox, Switch, or any CompoundButton.

**Kotlin Code with Listener**

```
val checkbox = findViewById<CheckBox>(R.id.checkbox1)

checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
    if (isChecked) {
        Toast.makeText(this, "Checked!", Toast.LENGTH_SHORT).show()
    } else {
        Toast.makeText(this, "Unchecked!", Toast.LENGTH_SHORT).show()
    }
} 
```  
---

### ⚡ RadioButton + RadioGroup

What is a RadioGroup?
A RadioGroup is a container that ensures only one RadioButton is selected at a time within it.

A RadioButton is used to select only one option from a group of choices.

- **Used For:** Gender selection, Payment mode selection, Answering a single-choice question

**XML-Syntax**

```  
<RadioGroup android:id="@+id/radioGroup"  
 android:layout_width="match_parent" 
 android:layout_height="wrap_content"> 

 <RadioButton android:id="@+id/option1" 
 android:text="Option 1" /> 

 <RadioButton 
 android:id="@+id/option2" 
 android:text="Option 2" />
 </RadioGroup>  
```  

**Common Attributes**

| Attribute             | Use                                       |
| --------------------- | ----------------------------------------- |
| `android:text`        | Label text for RadioButton                |
| `android:checked`     | Preselect an option                       |
| `android:orientation` | (RadioGroup) horizontal / vertical layout |

**Kotlin Code with Listener**

```  
val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)

radioGroup.setOnCheckedChangeListener { group, checkedId ->
    val selectedRadioButton = findViewById<RadioButton>(checkedId)
    val selectedText = selectedRadioButton.text.toString()
    Toast.makeText(this, "Selected: $selectedText", Toast.LENGTH_SHORT).show()
} 
```  

---

✅ Mini Project

UI Components:

TextView – to label inputs

EditText – to enter name

SeekBar – to select age

RatingBar – to rate experience

CheckBox – to agree to terms

RadioGroup + RadioButtons – to select gender

Button – to submit the form

After clicking the button, selected values will be shown in a Textview.

activity_main.xml
```
<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <LinearLayout
        android:orientation="vertical"
        android:padding="24dp"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <!-- Name -->
        <TextView android:text="Enter Name:" />
        <EditText
            android:id="@+id/etName"
            android:hint="Your name"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />

        <!-- Gender -->
        <TextView
            android:text="Select Gender:"
            android:layout_marginTop="16dp" />
        <RadioGroup
            android:id="@+id/radioGroupGender"
            android:orientation="horizontal"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content">
            <RadioButton
                android:id="@+id/radioMale"
                android:text="Male" />
            <RadioButton
                android:id="@+id/radioFemale"
                android:text="Female" />
        </RadioGroup>

        <!-- Age (SeekBar) -->
        <TextView
            android:text="Select Age:"
            android:layout_marginTop="16dp" />
        <SeekBar
            android:id="@+id/seekBarAge"
            android:max="100"
            android:progress="25"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />
        <TextView
            android:id="@+id/tvAgeValue"
            android:text="Age: 25"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />

        <!-- RatingBar -->
        <TextView
            android:text="Rate Experience:"
            android:layout_marginTop="16dp" />
        <RatingBar
            android:id="@+id/ratingBar"
            android:numStars="5"
            android:stepSize="1.0"
            android:rating="3"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />

        <!-- CheckBox -->
        <CheckBox
            android:id="@+id/checkBoxTerms"
            android:text="I agree to terms and conditions"
            android:layout_marginTop="16dp" />

        <!-- Submit Button -->
        <Button
            android:id="@+id/btnSubmit"
            android:text="Submit"
            android:layout_marginTop="24dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />

        <!-- Result TextView -->
        <TextView
            android:id="@+id/tvResult"
            android:text=""
            android:textSize="16sp"
            android:textColor="#000000"
            android:layout_marginTop="24dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />

    </LinearLayout>
</ScrollView>
```

MainActivity.kt
```
package com.example.yourapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // View references
        val etName = findViewById<EditText>(R.id.etName)
        val radioGroupGender = findViewById<RadioGroup>(R.id.radioGroupGender)
        val seekBarAge = findViewById<SeekBar>(R.id.seekBarAge)
        val tvAgeValue = findViewById<TextView>(R.id.tvAgeValue)
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val checkBoxTerms = findViewById<CheckBox>(R.id.checkBoxTerms)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        // Age SeekBar listener
        seekBarAge.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvAgeValue.text = "Age: $progress"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Button click logic
        btnSubmit.setOnClickListener {

            val name = etName.text.toString().trim()
            val selectedGenderId = radioGroupGender.checkedRadioButtonId
            val gender = if (selectedGenderId != -1) {
                findViewById<RadioButton>(selectedGenderId).text.toString()
            } else {
                "Not selected"
            }

            val age = seekBarAge.progress
            val rating = ratingBar.rating
            val agreed = checkBoxTerms.isChecked

            // Validation
            if (name.isEmpty()) {
                tvResult.text = "⚠️ Please enter your name"
            } else if (!agreed) {
                tvResult.text = "⚠️ Please agree to the terms and conditions"
            } else {
                // Show result in TextView
                val result = """
                    ✅ Submission Details:
                    • Name: $name
                    • Gender: $gender
                    • Age: $age
                    • Rating: $rating stars
                    • Terms Accepted: Yes
                """.trimIndent()

                tvResult.text = result
            }
        }
    }
}
```

---

### ⚡ ImageView

An ImageView is a UI widget used to display images in Android apps — from resources, drawable folders, or even from URLs (with libraries like Glide or Picasso).

- **Used For:** Icons, banners, photos.

**XML-Syntax**

```
<ImageView
    android:src="@drawable/sample_image"
    android:contentDescription="My Image"
    android:scaleType="centerCrop" />
```  

**Common Attributes**
| Attribute                    | Description                                                                         |
| ---------------------------- | ----------------------------------------------------------------------------------- |
| `android:src`                | Sets the image (from drawable or mipmap)                                            |
| `android:contentDescription` | For accessibility                                                                   |
| `android:scaleType`          | Controls how the image is resized/scaled (e.g., `fitCenter`, `centerCrop`, `fitXY`) |
| `layout_width/height`        | Size of the image view                                                              |


**Kotlin: Load Image from Drawable**

```  
val imageView = findViewById<ImageView>(R.id.imageView)
imageView.setImageResource(R.drawable.sample_image)
```  
---

### ⚡ ToggleButton

A ToggleButton is a button that switches between two states: ON and OFF.
You can customize the text for each state (like "Start"/"Stop" or "ON"/"OFF").

**XML-Syntax**
```
<ToggleButton
    android:textOn="Dark Mode"
    android:textOff="Light Mode"
    android:checked="false" />
```

**Common Attributes**
| Attribute         | Purpose                           |
| ----------------- | --------------------------------- |
| `android:textOn`  | Text when ON                      |
| `android:textOff` | Text when OFF                     |
| `android:checked` | Initial state (`true` or `false`) |

<br>

**Kotlin Code with Listener**
```
val toggleButton = findViewById<ToggleButton>(R.id.toggleButton)

toggleButton.setOnCheckedChangeListener { _, isChecked ->
    if (isChecked) {
        // ON state
        Toast.makeText(this, "Dark Mode Enabled", Toast.LENGTH_SHORT).show()
    } else {
        // OFF state
        Toast.makeText(this, "Light Mode Enabled", Toast.LENGTH_SHORT).show()
    }
}
```

---

✅ Mini Project

When we click toggles button, the image changes (e.g., "Light ON" vs "Light OFF").

activity_main.xml
```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:gravity="center"
    android:padding="24dp"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="200dp"
        android:layout_height="200dp"
        android:src="@drawable/light_off"
        android:scaleType="centerCrop"
        android:contentDescription="Light Image" />

    <ToggleButton
        android:id="@+id/toggleButton"
        android:textOn="Turn OFF"
        android:textOff="Turn ON"
        android:layout_marginTop="24dp"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

</LinearLayout>
```

Place two images in res/drawable/ folder:

    light_off.png

    light_on.png

MainActivity.kt
```
package com.example.imageviewtoggle

import android.os.Bundle
import android.widget.ImageView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val toggleButton = findViewById<ToggleButton>(R.id.toggleButton)

        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Toggle is ON
                imageView.setImageResource(R.drawable.light_on)
            } else {
                // Toggle is OFF
                imageView.setImageResource(R.drawable.light_off)
            }
        }
    }
}
```    
---

### ⚡ ProgressBar

A ProgressBar is used to show progress of an operation like downloading, uploading, loading data, etc.

- **Used For:** File download/upload, Loading screen, App update status

**Types of ProgressBars:**
| Type              | Description                                        |
| ----------------- | -------------------------------------------------- |
| **Indeterminate** | Spins continuously (used when duration is unknown) |
| **Determinate**   | Shows progress from 0% to 100%                     |


<br>

**XML-Syntax**

**Indeterminate (default spinner):**

```  
<ProgressBar
    android:id="@+id/progressBarIndeterminate"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:indeterminate="true" />  
```  

**Determinate**

```
<ProgressBar
    android:id="@+id/progressBarDeterminate"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:progress="30"
    android:max="100"
    style="?android:attr/progressBarStyleHorizontal" />
```

**Common Attributes**

| Attribute                                | Purpose                                                                        |
| ---------------------------------------- | ------------------------------------------------------------------------------ |
| `android:indeterminate`                  | `true` = spinning loader, `false` = shows actual progress                      |
| `android:progress`                       | Current progress value (used for determinate bars)                             |
| `android:max`                            | Maximum progress value (default = 100)                                         |
| `style`                                  | Sets style: spinner or horizontal (`?android:attr/progressBarStyleHorizontal`) |

**Access & Use in Kotlin**

**activity_main**
```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:padding="24dp"
    android:gravity="center"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ProgressBar
        android:id="@+id/progressBar"
        style="?android:attr/progressBarStyleHorizontal"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:max="100"
        android:progress="0" />

    <Button
        android:id="@+id/btnStart"
        android:text="Start Progress"
        android:layout_marginTop="24dp"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
</LinearLayout>
```

**MainActivity.kt**
```
class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private var progressStatus = 0
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)
        val btnStart = findViewById<Button>(R.id.btnStart)

        btnStart.setOnClickListener {
            progressStatus = 0
            progressBar.progress = 0

            Thread {
                while (progressStatus < 100) {
                    progressStatus += 1
                    Thread.sleep(50)  // Simulate work

                    handler.post {
                        progressBar.progress = progressStatus
                    }
                }
            }.start()
        }
    }
}
```

What happens:

    Button starts progress from 0 to 100

    Progress bar animates gradually

    Simulates a download or task progress

---

### ⚡ Spinner (Dropdown)

A Spinner is a dropdown menu that lets users select one item from a list.

- **Used For:** Selection lists (e.g., country, Age etc).

**XML-Syntax**

```  
<Spinner  
 android:id="@+id/spinner" 
 android:layout_width="wrap_content" 
 android:layout_height="wrap_content" /> 
 ``` 

<br>

**What is an Adapter in Android?**

An Adapter acts like a bridge between your data source and the UI component (like Spinner).
It prepares data (like a list of strings) and shows it properly inside the widget.

**Why do we need an Adapter for a Spinner?**

The Spinner is just a UI dropdown. It doesn't know what to show inside it until you give it data.
So, we use an Adapter to:

Take your list of data (e.g., gender options)

Format it into views (like dropdown rows)

Feed it to the Spinner

<br>

**Common Adapter for Spinner**

```ArrayAdapter<T>```

    Simple and commonly used

    Works well with lists of strings

    Automatically creates basic dropdown views


**Example:**

```
val adapter = ArrayAdapter(
    this,                                  // context
    android.R.layout.simple_spinner_item,  // layout for each item
    listOf("Male", "Female", "Other")      // data source
)
spinner.adapter = adapter
```

 <br>

| Concept        | Purpose                                             |
| -------------- | --------------------------------------------------- |
| `Spinner`      | UI component to display selected item from dropdown |
| `Adapter`      | Supplies data to the Spinner and formats it         |
| `ArrayAdapter` | Common adapter for lists (like strings, objects)    |


**Access & Use in Kotlin**

```
val spinner = findViewById<Spinner>(R.id.spinnerGender)

// List of items
val genderOptions = listOf("Select Gender", "Male", "Female", "Other")

// Adapter connects data to spinner UI
val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genderOptions)
adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

spinner.adapter = adapter

// Listener to detect selection
spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
        val selected = parent.getItemAtPosition(position).toString()
        Toast.makeText(this@MainActivity, "Selected: $selected", Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Do nothing
    }
}
```

---  

✅ Mini Project: Spinner + Button + TextView

activity_main.xml

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:orientation="vertical"
android:padding="24dp"
android:layout_width="match_parent"
android:layout_height="match_parent">

    <Spinner
        android:id="@+id/spinnerGender" />

    <Button
        android:id="@+id/btnSubmit"
        android:text="Submit"
        android:layout_marginTop="24dp" />

    <TextView
        android:id="@+id/tvResult"
        android:text=""
        android:layout_marginTop="16dp"
        android:textSize="18sp" />
</LinearLayout>

MainActivity.kt

```
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinnerGender)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        val genderOptions = listOf("Select Gender", "Male", "Female", "Other")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genderOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        btnSubmit.setOnClickListener {
            val selected = spinner.selectedItem.toString()
            tvResult.text = "Selected: $selected"
        }
    }
}
```

---

## 👉 Layouts (Containers)
Layouts position and organize UI elements on the screen.

### ⚡ LinearLayout

A LinearLayout is a ViewGroup that arranges its child views in a single direction: either vertically or horizontally.

**Key Points**

- It’s one of the most commonly used layout containers.

- You choose the direction using:
  android:orientation="vertical" or "horizontal"

-   All child views are aligned one after another in that direction.

**XML-Syntax**

```  
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"   <!-- or "horizontal" -->
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <!-- Child views go here -->
</LinearLayout>
```  

**Common Attributes**
| Attribute                | Description                                               |
| ------------------------ | --------------------------------------------------------- |
| `android:orientation`    | Direction: `"vertical"` or `"horizontal"`                 |
| `android:gravity`        | Aligns all children inside the layout (e.g., center, end) |
| `android:layout_gravity` | Aligns this layout inside its parent                      |
| `android:weightSum`      | Total weight to distribute among children                 |
| `layout_weight` (child)  | Shares space proportionally among children                |

---

### ⚡ Relative

RelativeLayout is a ViewGroup that lets you position child views relative to each other or to the parent.
It gives you more flexibility than LinearLayout when placing elements.

**Why use RelativeLayout?**

It’s useful when you want to:

- Align one view to the left/right/top/bottom of another
- Center views horizontally or vertically
- Overlay views (like putting a label on top of an image)

**XML-Syntax**

```  
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <!-- Child views here -->

</RelativeLayout>
```  

**Common Attributes (for child views)**

| Attribute                                | Meaning                                      |
| ---------------------------------------- | -------------------------------------------- |
| `layout_alignParentTop="true"`           | Align to top of parent                       |
| `layout_alignParentBottom="true"`        | Align to bottom of parent                    |
| `layout_alignParentLeft="true"`          | Align to left of parent                      |
| `layout_centerInParent="true"`           | Center both horizontally and vertically      |
| `layout_centerHorizontal="true"`         | Center only horizontally                     |
| `layout_centerVertical="true"`           | Center only vertically                       |
| `layout_below="@id/anotherView"`         | Place this view below another view           |
| `layout_toRightOf="@id/anotherView"`     | Place this view to the right of another view |
| `layout_above="@id/anotherView"`         | Place this view above another view           |
| `layout_alignBaseline="@id/anotherView"` | Align baseline with another view             |

---


### ⚡ Constraint

ConstraintLayout is a powerful and flexible ViewGroup that lets you position and size views using constraints — instead of nested layouts. It’s the recommended layout for modern Android UI.

**XML-Syntax**

```  
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <!-- Child views here -->

</androidx.constraintlayout.widget.ConstraintLayout>  
```  

**Common Attributes (for child views)**
| Attribute                                | Purpose                                           |
| ---------------------------------------- | ------------------------------------------------- |
| `app:layout_constraintTop_toTopOf`       | Align top to parent or another view               |
| `app:layout_constraintBottom_toBottomOf` | Align bottom to parent or another view            |
| `app:layout_constraintStart_toStartOf`   | Align start (left)                                |
| `app:layout_constraintEnd_toEndOf`       | Align end (right)                                 |
| `app:layout_constraintHorizontal_bias`   | Adjust horizontal position (0 = left, 1 = right)  |
| `app:layout_constraintVertical_bias`     | Adjust vertical position (0 = top, 1 = bottom)    |
| `app:layout_constraintWidth_percent`     | Set width as a percentage of parent (needs `0dp`) |

---

✅ mini project using ConstraintLayout with:

    ImageView (logo or banner)

    EditText (user input)

    Button (submit)

    TextView (shows result)

This project shows a simple login-style screen built entirely with ConstraintLayout.

activity_main.xml
```
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="24dp">

    <ImageView
        android:id="@+id/imageViewLogo"
        android:src="@drawable/ic_launcher_foreground"
        android:contentDescription="Logo"
        android:layout_width="100dp"
        android:layout_height="100dp"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintTop_margin="32dp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <EditText
        android:id="@+id/etName"
        android:hint="Enter your name"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:padding="12dp"
        app:layout_constraintTop_toBottomOf="@id/imageViewLogo"
        app:layout_constraintTop_margin="32dp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <Button
        android:id="@+id/btnSubmit"
        android:text="Submit"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toBottomOf="@id/etName"
        app:layout_constraintTop_margin="24dp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <TextView
        android:id="@+id/tvResult"
        android:text=""
        android:textSize="18sp"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toBottomOf="@id/btnSubmit"
        app:layout_constraintTop_margin="32dp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

MainActivity.kt
```
package com.example.constraintminiproject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName = findViewById<EditText>(R.id.etName)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnSubmit.setOnClickListener {
            val name = etName.text.toString().trim()
            if (name.isEmpty()) {
                tvResult.text = "⚠️ Please enter your name"
            } else {
                tvResult.text = "Hello, $name! 👋"
            }
        }
    }
}
```

---

### ⚡ FrameLayout

FrameLayout is a simple ViewGroup that stacks all child views on top of each other, like layers in Photoshop.

Key Points

    The first child is at the bottom, last child is on top

    Best used when you want to display one view at a time, or overlay views

    No automatic positioning — each view is placed at the top-left by default

**XML-Syntax**

```  
<FrameLayout  
 android:id="@+id/container" 
 android:layout_width="match_parent"
android:layout_height="match_parent"> 
<ImageView android:src="@drawable/bg" /> 
<TextView android:text="Welcome" />
</FrameLayout>  
```

**Common Use Cases**

| Use Case               | Example                                        |
| ---------------------- | ---------------------------------------------- |
| **Splash screen**      | Show background image + logo/text overlay      |
| **Video player**       | Video view + play/pause button overlaid        |
| **Fragment container** | Hosting different fragments dynamically        |
| **ImageView overlay**  | Like watermarks, captions, or badges on images |

✅ Mini Example: Image with Text Overlaid

activity_main.xml
```
<FrameLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="300dp"
    android:layout_height="300dp"
    android:layout_gravity="center"
    android:layout_marginTop="50dp">

    <ImageView
        android:src="@drawable/sample_image"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:scaleType="centerCrop" />

    <TextView
        android:text="Overlay Text"
        android:textColor="#FFFFFF"
        android:textSize="18sp"
        android:background="#80000000"
        android:padding="8dp"
        android:layout_gravity="bottom|center_horizontal"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
</FrameLayout>
```

layout_gravity is used inside FrameLayout to position children (center, bottom, right, etc.)