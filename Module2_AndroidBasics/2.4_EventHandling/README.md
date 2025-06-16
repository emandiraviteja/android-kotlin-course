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
## 👉 Toasts
A Toast is a short popup message shown to the user. It disappears automatically.

**Syntax**
```
Toast.makeText(context, "Message", Toast.LENGTH_SHORT).show()
```
**Example**
```
Toast.makeText(this, "Welcome to Android!", Toast.LENGTH_LONG).show()
```
## 👉 Snackbars
A Snackbar is similar to a Toast but can include an action (like "Undo").

**Syntax**
```
Snackbar.make(view, "Message", Snackbar.LENGTH_SHORT).show()
```
**Example**
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
"Submit" → shows Snackbar with action
"Reset" → clears UI