# ✅ 4.3 Dialogs

## 👉 AlertDialog

**What is it?**

A simple dialog that shows title, message, and buttons. Common for confirmations, messages, and choices.

**Syntax:**

```
AlertDialog.Builder(this)
    .setTitle("Exit")
    .setMessage("Are you sure you want to exit?")
    .setPositiveButton("Yes") { _, _ ->
        Toast.makeText(this, "Exiting...", Toast.LENGTH_SHORT).show()
    }
    .setNegativeButton("No", null)
    .show()
```

**When to use:**

Confirm logout

Confirm delete

Show info with OK

---

## 👉 TimePicker Dialog

**What is it?**

Dialog that lets user pick a time (hour & minute).

**Syntax:**

```
val calendar = Calendar.getInstance()
val hour = calendar.get(Calendar.HOUR_OF_DAY)
val minute = calendar.get(Calendar.MINUTE)

TimePickerDialog(this, { _, selectedHour, selectedMinute ->
    Toast.makeText(this, "Time: $selectedHour:$selectedMinute", Toast.LENGTH_SHORT).show()
}, hour, minute, true).show()
```

Use `this` in Activity, `requireContext()` in Fragment

Last parameter: `true` for 24-hr format

---

## 👉 DatePicker Dialog

**What is it?**

Dialog that allows the user to select a date (day, month, year).

**Syntax:**

```
val calendar = Calendar.getInstance()
val year = calendar.get(Calendar.YEAR)
val month = calendar.get(Calendar.MONTH)
val day = calendar.get(Calendar.DAY_OF_MONTH)

DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
    val date = "$selectedDay/${selectedMonth + 1}/$selectedYear"
    Toast.makeText(this, "Date: $date", Toast.LENGTH_SHORT).show()
}, year, month, day).show()
```

Month is `0-indexed` → always add +1 when displaying

---

## 👉 Bottom Sheet Dialog

**What is it?**

A dialog that slides up from the bottom — great for showing:

- Options (e.g., share, download)
- Menus
- Forms

**layout_bottom_sheet.xml**

```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:padding="24dp"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <TextView
        android:text="Choose Option"
        android:textSize="18sp"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>

    <Button
        android:id="@+id/btnOption"
        android:text="Do Action"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
</LinearLayout>
```

**Kotlin Code:**

```
val view = layoutInflater.inflate(R.layout.layout_bottom_sheet, null)
val dialog = BottomSheetDialog(this)
dialog.setContentView(view)
dialog.show()

val btnOption = view.findViewById<Button>(R.id.btnOption)
btnOption.setOnClickListener {
    Toast.makeText(this, "Action clicked", Toast.LENGTH_SHORT).show()
    dialog.dismiss()
}
```

<br>

**Summary Table**

| Dialog Type           | Purpose                       | Use Case Example           |
| --------------------- | ----------------------------- | -------------------------- |
| **AlertDialog**       | Basic info, Yes/No, OK/Cancel | Confirm exit, delete       |
| **TimePickerDialog**  | Time input                    | Set reminder, alarm        |
| **DatePickerDialog**  | Date input                    | Pick DOB, event date       |
| **BottomSheetDialog** | Action menu or options        | Share menu, upload options |

---

## 👉 Mini Project: Dialog Demo App that demonstrates:

Features

- AlertDialog (Yes/No)
- TimePickerDialog
- DatePickerDialog
- BottomSheetDialog (Custom layout)


**Project Structure**

MainActivity.kt
|
├── res/
│   ├── layout/
│   │   └── activity_main.xml
│   │   └── layout_bottom_sheet.xml
│   └── drawable/ (optional icons)

<br>

Step 1: activity_main.xml

Step 2: layout_bottom_sheet.xml

Step 3: MainActivity.kt

**Result**

| Button Clicked    | Result                             |
| ----------------- | ---------------------------------- |
| Show AlertDialog  | Shows confirm dialog with Yes/No   |
| Show Time Picker  | Pops up time selection dialog      |
| Show Date Picker  | Shows calendar date picker         |
| Show Bottom Sheet | Slides up bottom sheet with button |