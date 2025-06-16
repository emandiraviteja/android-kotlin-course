# ✅  3.1: Multiple Activities in Android (Kotlin)

Most Android apps consist of multiple screens, and each screen is typically backed by an Activity. To switch between screens, we use Intents. We often need to pass data or get results back, such as from a form or a photo picker.

## 👉️ Starting Activities with Intent
An Intent is a messaging object that lets you start a new activity or service.

**Use Case:**
Navigating from Login to Dashboard screen
Going from a list to a detail page

**Syntax**
```
val intent = Intent(this, SecondActivity::class.java)
startActivity(intent)
```

**Example**
MainActivity.kt
```
val intent = Intent(this, SecondActivity::class.java)
startActivity(intent)
```
## 👉️ Passing Data Between Activities

Use Intent.putExtra() to send data, and intent.getStringExtra() to receive it.

**Use Case:**
Passing user name or ID from Login to Profile screen

Sender Code (MainActivity)
```
val intent = Intent(this, SecondActivity::class.java)
intent.putExtra("username", "Teja")
startActivity(intent)
```
Receiver Code (SecondActivity)
```
val name = intent.getStringExtra("username")
textView.text = "Welcome, $name"
```
## 👉️ Getting Results with ActivityResult API
The modern way to get results back from an activity (replaces startActivityForResult).

**Use Case:**
Picking an image from gallery
Submitting a form and returning the result

**Setup**

In MainActivity:
```
val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
if (result.resultCode == RESULT_OK) {
val message = result.data?.getStringExtra("result")
Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
}

val intent = Intent(this, SecondActivity::class.java)
launcher.launch(intent)
```
In SecondActivity:
```
val intent = Intent()
intent.putExtra("result", "Thanks for visiting!")
setResult(RESULT_OK, intent)
finish()
```

### ⚡ Mini Project: "Name Passing & Return"
**Objective:**
Send a name from MainActivity to SecondActivity, display it there, and return a thank you message back.

### ⚡ Summary Table
| Concept               | Purpose                       | Function                    |
| --------------------- | ----------------------------- | --------------------------- |
| Intent                | Navigate between screens      | `startActivity(intent)`     |
| `putExtra`/`getExtra` | Pass data to other activity   | `intent.putExtra(...)`      |
| ActivityResult API    | Get result back from activity | `registerForActivityResult` |
