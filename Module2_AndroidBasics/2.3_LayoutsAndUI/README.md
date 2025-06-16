# ✅ Module 2.3 : UI Elements & Layouts

# Android UI Elements Showcase (Kotlin)

This sample Android project demonstrates the most common **UI elements** in Android using **Kotlin**. It covers TextViews, EditTexts, Buttons, CheckBoxes, RadioButtons, Spinners, Toolbars, and more — all in one activity.

---

## 👉 Common Views

### ⚡ TextView
- **Description:** TextView is a basic UI component in Android used to display static or dynamic text on the screen.
- **Used For:** Labels, headings, messages.

```
<TextView
    android:id="@+id/textView"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Hello, World!" />
```
```
val textView = findViewById<TextView>(R.id.textView)
textView.text = "Updated Text"
```

### ⚡ EditText
- **Description:** Input field for text.
- **Used For:** Forms, search bars, login fields.

```
<EditText
    android:id="@+id/editText"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Enter your name" />
```
```
val editText = findViewById<EditText>(R.id.editText)
val input = editText.text.toString()
```


### ⚡ Button
- **Description:** Clickable button.
- **Used For:** Submitting forms, navigation.
```
<Button
    android:id="@+id/button"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Submit" />
```
```
val button = findViewById<Button>(R.id.button)
button.setOnClickListener {
    // Handle click
}
```


### ⚡ CheckBox

  - **Description:** Selectable checkbox.
- **Used For:**  Multiple choices, settings.
```
<CheckBox
    android:id="@+id/checkBox"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Accept Terms" />
```
```
val checkBox = findViewById<CheckBox>(R.id.checkBox)
if (checkBox.isChecked) {
    // do something
}
```

### ⚡ RadioButton + RadioGroup

- **Description:** Select one option from many.
- **Used For:**  Single-choice questions.
```
<RadioGroup android:id="@+id/radioGroup"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">
    <RadioButton android:id="@+id/option1" android:text="Option 1" />
    <RadioButton android:id="@+id/option2" android:text="Option 2" />
</RadioGroup>
```
```
val group = findViewById<RadioGroup>(R.id.radioGroup)
val selectedId = group.checkedRadioButtonId
val selected = findViewById<RadioButton>(selectedId)
val text = selected.text.toString()
```

### ⚡ SeekBar

- **Description:** Slider to select a value.
- **Used For:**  Volume, brightness.
```
<SeekBar
    android:id=\"@+id/seekBar\"
    android:layout_width=\"match_parent\"
    android:layout_height=\"wrap_content\" />
    ```
    ```
seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        // use progress value
    }
    override fun onStartTrackingTouch(seekBar: SeekBar?) {}
    override fun onStopTrackingTouch(seekBar: SeekBar?) {}
})
```


### ⚡ RatingBar

- **Description:** Star-based rating input.
- **Used For:**  Feedback forms.
```
<RatingBar
    android:id="@+id/ratingBar"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:numStars="5"
    android:stepSize="1.0" />
```
```
val rating = ratingBar.rating
```

### ⚡ Switch/ ToggleButton

- **Description:** Toggle between ON/OFF states.
- **Used For:**  Settings toggles.
```
<Switch
    android:id="@+id/switch1"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Enable Notifications" />
```
```
val isChecked = switch1.isChecked
```

### ⚡ Spinner (Dropdown)
- **Description:** Dropdown menu.
- **Used For:**  Selection lists (e.g., country).

```
<Spinner
    android:id="@+id/spinner"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
    ```
    ```
val spinner = findViewById<Spinner>(R.id.spinner)
val items = arrayOf("Option 1", "Option 2")
val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
spinner.adapter = adapter
```

### ⚡ ImageView
- **Description:** Displays images.
- **Used For:**  Icons, banners, photos.

```
<ImageView
    android:id="@+id/imageView"
    android:layout_width="100dp"
    android:layout_height="100dp"
    android:src="@drawable/sample_image" />
```
```
imageView.setImageResource(R.drawable.sample_image)
```

### ⚡ ProgressBar

- **Description:** Shows progress/loading status.
- **Used For:**  Loading indicators.
```
<ProgressBar
    android:id=\"@+id/progressBar\"
    android:layout_width=\"wrap_content\"
    android:layout_height=\"wrap_content\" />
```
```
progressBar.visibility = View.VISIBLE // or GONE
```

### ⚡ Toolbar

- **Description:** Top navigation bar.
- **Used For:** : App title, menus, back buttons.
```
<androidx.appcompat.widget.Toolbar
    android:id=\"@+id/toolbar\"
    android:layout_width=\"match_parent\"
    android:layout_height=\"?attr/actionBarSize\" />
```    
```
val toolbar = findViewById<Toolbar>(R.id.toolbar)
setSupportActionBar(toolbar)
```

## 👉 Layouts (Containers)
Layouts position and organize UI elements on the screen.

 ### ⚡ Linear 

Arranges views vertically or horizontally.

```
<LinearLayout
    android:orientation=\"vertical\"
    android:layout_width=\"match_parent\"
    android:layout_height=\"wrap_content\">
    
    <TextView ... />
    <Button ... />
</LinearLayout>
```

### ⚡ Relative
Positions views relative to each other (e.g., to left/right/top of other views).
```
<RelativeLayout>
    <TextView android:id="@+id/tv1" android:text="Label" />
    <EditText android:layout_below="@id/tv1" />
</RelativeLayout>
```

### ⚡ Constraint
Most flexible. Allows you to position elements based on constraints.
```
<androidx.constraintlayout.widget.ConstraintLayout>
    <Button
        android:id="@+id/btn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```


### ⚡ Frame
Stack views on top of each other (used for fragment containers, splash screens).
```
<FrameLayout
    android:id="@+id/container"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <ImageView android:src="@drawable/bg" />
    <TextView android:text="Welcome" />
</FrameLayout>
```
