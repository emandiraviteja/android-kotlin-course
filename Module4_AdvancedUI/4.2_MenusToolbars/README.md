# ✅ 4.2 Menus & Toolbars

## 👉 AppBar Customization (Toolbar)

The AppBar is usually a Toolbar that appears at the top of your screen.

**Add Toolbar in XML**

```
<androidx.appcompat.widget.Toolbar
    android:id="@+id/toolbar"
    android:layout_height="wrap_content"
    android:layout_width="match_parent"
    android:background="?attr/colorPrimary"
    android:titleTextColor="@android:color/white"
    android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar" />
```

**Setup Toolbar in Activity**

```
setSupportActionBar(findViewById(R.id.toolbar))
supportActionBar?.title = "Home"
```

### ⚡ For Fragment

```
(requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
```

---

## 👉 Options Menu (Three Dots)

Use this when you want menu items in the AppBar or overflow menu.

**res/menu/menu_main.xml**

```
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/action_settings"
        android:title="Settings"
        android:icon="@drawable/ic_settings"
        android:showAsAction="always" />
</menu>
```

**In Activity/Fragment**

```
override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.menu_main, menu)
    super.onCreateOptionsMenu(menu, inflater)
}

override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
        R.id.action_settings -> {
            Toast.makeText(requireContext(), "Settings clicked", Toast.LENGTH_SHORT).show()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
```

**Don’t forget:**

```
setHasOptionsMenu(true) // in fragment's onCreate()
```

## 👉 Context Menu (on Long Press)

Use this for items like lists that need contextual actions.

**Register Context Menu**

```
registerForContextMenu(view)
```

**Create Context Menu**

```
override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
    super.onCreateContextMenu(menu, v, menuInfo)
    menu.setHeaderTitle("Choose an action")
    menu.add(0, v.id, 0, "Edit")
    menu.add(0, v.id, 1, "Delete")
}
```

**Handle Selection**

```
override fun onContextItemSelected(item: MenuItem): Boolean {
    return when (item.title) {
        "Edit" -> { /* Handle edit */ true }
        "Delete" -> { /* Handle delete */ true }
        else -> super.onContextItemSelected(item)
    }
}
```

## 👉 Popup Menu (Anchored Dropdown)

Use this when clicking on a button/image to show quick options.

**Kotlin Code:**

```
val popup = PopupMenu(context, view)
popup.menuInflater.inflate(R.menu.menu_main, popup.menu)
popup.setOnMenuItemClickListener {
    Toast.makeText(context, "Clicked: ${it.title}", Toast.LENGTH_SHORT).show()
    true
}
popup.show()
```

### ⚡ Summary Table

| Menu Type      | Use Case                             | UI Placement |
| -------------- | ------------------------------------ | ------------ |
| AppBar Toolbar | Show screen title & menu             | Top bar      |
| Options Menu   | Global actions like Settings, Search | Action bar   |
| Context Menu   | Per-item long-press actions          | Contextual   |
| Popup Menu     | Quick dropdown actions on a button   | Next to view |


---

## 👉 Mini Project: Menu Showcase App that demonstrates:

Features

- Toolbar with custom title and Options Menu
- Popup Menu triggered by a button
- Context Menu on a TextView
- Project Structure
- MainActivity.kt

<br>

|
├── res/
│   ├── layout/
│   │   └── activity_main.xml
│   └── menu/
│       └── menu_main.xml

<br>

Step 1: Create menu_main.xml

Step 2: Create UI activity_main.xml

Step 3: Kotlin Code MainActivity.kt

<br>

**Output Summary**

| Feature          | Trigger                                    |
| ---------------- | ------------------------------------------ |
| **Toolbar Menu** | Appears at top, with icons (3-dot options) |
| **Popup Menu**   | Click button → shows dropdown beside it    |
| **Context Menu** | Long press `TextView` → shows edit/delete  |
