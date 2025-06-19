# ✅ 2.4 Event Handling
---

## 👉 Click Listeners
A Click Listener detects and responds to user clicks on views like Button, ImageView, etc.

**Syntax**
```
button.setOnClickListener {
    // Code to execute when button is clicked
}
```
**Example**
```
val button = findViewById<Button>(R.id.myButton)
button.setOnClickListener {
    Toast.makeText(this, "Button clicked!", Toast.LENGTH_SHORT).show()
}
```

---

## 👉 Toasts

A Toast is a small popup message that appears briefly on the screen to show feedback to the user.
It automatically disappears after a short time and does not require user interaction.

**When to Use Toasts**
| Use Case                     | Example                  |
| ---------------------------- | ------------------------ |
| Show success or failure      | "Saved successfully"     |
| Show input feedback          | "Please enter a name"    |
| Temporary status or warning  | "No internet connection" |
| Simple messages (no actions) | "Button clicked!"        |

<br>

**Limitations**
- Can't include buttons or icons
- Not suitable for error handling or confirmations

<br>

**Basic Syntax (Kotlin)**
```
Toast.makeText(context, "Your message", Toast.LENGTH_SHORT).show()
```

**Parameters:**
- context → Usually this or applicationContext
- "Your message" → Message to show
- Toast.LENGTH_SHORT or Toast.LENGTH_LONG → Duration

<br>

**Example**
```
val btn = findViewById<Button>(R.id.btnClick)

btn.setOnClickListener {
    Toast.makeText(this, "Button clicked!", Toast.LENGTH_SHORT).show()
}
```

---

## 👉 Snackbars

A Snackbar is like a Toast — it shows a brief message — but with more features:
- It can have an action button
- It stays on screen longer
- It respects Material Design
- It can be swiped to dismiss

**When to Use a Snackbar**
| Use Case                     | Use                            |
| ---------------------------- | ------------------------------ |
| Show short feedback          | ✅ (Like Toast)                 |
| User should be able to react | ✅ ("Undo", "Retry", "Dismiss") |
| Persistent UI state change   | ✅ ("Item deleted")             |
| Requires action              | ✅ ("UNDO" button)              |
| Needs design consistency     | ✅ (Material Design apps)       |

<br>

**Basic Syntax (Kotlin)**
```
Snackbar.make(view, "Message", Snackbar.LENGTH_SHORT).show()
```

**Add an Action (e.g., "UNDO")**
```
Snackbar.make(view, "Item deleted", Snackbar.LENGTH_LONG)
    .setAction("UNDO") {
        // Handle undo here
        Toast.makeText(this, "Undo clicked", Toast.LENGTH_SHORT).show()
    }
    .show()
```

```
val layout = findViewById<View>(android.R.id.content)
Snackbar.make(layout, "Item deleted", Snackbar.LENGTH_LONG)
    .setAction("Undo") {
        // Undo logic here
    }
.show()
``` 

## 👉 Mini Project: "Feedback Buttons"

Create an app with three buttons:

"Like" → shows Toast

"Submit" → shows Snack bar with action

"Reset" → clears UI 