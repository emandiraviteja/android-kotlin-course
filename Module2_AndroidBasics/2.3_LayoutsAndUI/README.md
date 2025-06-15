# 📱 Android UI Elements Showcase (Kotlin)

This sample Android project demonstrates the most common **UI elements** in Android using **Kotlin**. It covers TextViews, EditTexts, Buttons, CheckBoxes, RadioButtons, Spinners, RecyclerViews, Toolbars, and more — all in one activity.

---

## ✅ UI Elements Explained

---

### 📋 1. TextView
- **Description:** Displays static text.
- **Used For:** Labels, headings, messages.

```xml
<TextView
    android:id="@+id/textView"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Hello, World!" />

val textView = findViewById<TextView>(R.id.textView)
textView.text = "Updated Text"


🧑‍💼 2. EditText

    Description: Input field for text.

    Used For: Forms, search bars, login fields.

<EditText
    android:id="@+id/editText"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Enter your name" />

val editText = findViewById<EditText>(R.id.editText)
val input = editText.text.toString()



🔘 3. Button

    Description: Clickable button.

    Used For: Submitting forms, navigation.

<Button
    android:id="@+id/button"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Submit" />

val button = findViewById<Button>(R.id.button)
button.setOnClickListener {
    // Handle click
}


✅ 4. CheckBox

    Description: Selectable checkbox.

    Used For: Multiple choices, settings.

<CheckBox
    android:id="@+id/checkBox"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Accept Terms" />

val checkBox = findViewById<CheckBox>(R.id.checkBox)
if (checkBox.isChecked) {
    // do something
}

🔘 5. RadioButton + RadioGroup

    Description: Select one option from many.

    Used For: Single-choice questions.

<RadioGroup android:id="@+id/radioGroup"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">
    <RadioButton android:id="@+id/option1" android:text="Option 1" />
    <RadioButton android:id="@+id/option2" android:text="Option 2" />
</RadioGroup>

val group = findViewById<RadioGroup>(R.id.radioGroup)
val selectedId = group.checkedRadioButtonId
val selected = findViewById<RadioButton>(selectedId)
val text = selected.text.toString()


🎚️ 6. SeekBar

    Description: Slider to select a value.

    Used For: Volume, brightness.

<SeekBar
    android:id=\"@+id/seekBar\"
    android:layout_width=\"match_parent\"
    android:layout_height=\"wrap_content\" />

seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        // use progress value
    }
    override fun onStartTrackingTouch(seekBar: SeekBar?) {}
    override fun onStopTrackingTouch(seekBar: SeekBar?) {}
})


⭐ 7. RatingBar

    Description: Star-based rating input.

    Used For: Feedback forms.

<RatingBar
    android:id="@+id/ratingBar"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:numStars="5"
    android:stepSize="1.0" />

val rating = ratingBar.rating

🔄 8. Switch

    Description: Toggle between ON/OFF states.

    Used For: Settings toggles.

<Switch
    android:id="@+id/switch1"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Enable Notifications" />

val isChecked = switch1.isChecked

📋 9. Spinner

    Description: Dropdown menu.

    Used For: Selection lists (e.g., country).

<Spinner
    android:id="@+id/spinner"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />

val spinner = findViewById<Spinner>(R.id.spinner)
val items = arrayOf("Option 1", "Option 2")
val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
spinner.adapter = adapter


🖼️ 10. ImageView

    Description: Displays images.

    Used For: Icons, banners, photos.

<ImageView
    android:id="@+id/imageView"
    android:layout_width="100dp"
    android:layout_height="100dp"
    android:src="@drawable/sample_image" />

imageView.setImageResource(R.drawable.sample_image)


📜 11. RecyclerView

    Description: Displays a scrollable list of items.

    Used For: Messages, lists, items.

<androidx.recyclerview.widget.RecyclerView
    android:id=\"@+id/recyclerView\"
    android:layout_width=\"match_parent\"
    android:layout_height=\"wrap_content\"/>

recyclerView.layoutManager = LinearLayoutManager(this)
recyclerView.adapter = YourAdapter(list)


📦 12. Layouts (Linear / Relative / Constraint)

    Description: Containers for organizing other views.

    Used For: Structuring UI.

<LinearLayout
    android:orientation=\"vertical\"
    android:layout_width=\"match_parent\"
    android:layout_height=\"wrap_content\">
    
    <TextView ... />
    <Button ... />
</LinearLayout>


🆕 13. ProgressBar

    Description: Shows progress/loading status.

    Used For: Loading indicators.

<ProgressBar
    android:id=\"@+id/progressBar\"
    android:layout_width=\"wrap_content\"
    android:layout_height=\"wrap_content\" />

progressBar.visibility = View.VISIBLE // or GONE

🧭 14. Toolbar

    Description: Top navigation bar.

    Used For: App title, menus, back buttons.

<androidx.appcompat.widget.Toolbar
    android:id=\"@+id/toolbar\"
    android:layout_width=\"match_parent\"
    android:layout_height=\"?attr/actionBarSize\" />

val toolbar = findViewById<Toolbar>(R.id.toolbar)
setSupportActionBar(toolbar)

