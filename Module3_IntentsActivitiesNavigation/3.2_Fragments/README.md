# 📦 Android Kotlin: 3.2 – Fragments

This lesson covers how to create, use, and communicate with fragments in Android using Kotlin.

---

## 🔍 What is a Fragment?

A **Fragment** is a modular section of an `Activity` that has its own lifecycle and UI. Fragments are commonly used for:

- Reusable UI components
- Supporting tablet layouts
- Navigation via tabs or bottom nav
- Dynamic UIs

---

## 🧱 Creating a Fragment

### 1️⃣ Create the Fragment Class

```kotlin
class MyFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my, container, false)
    }
}

2️⃣ Layout for Fragment – fragment_my.xml

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <TextView
        android:id="@+id/fragmentText"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello from Fragment" />
</LinearLayout>

🧲 Adding Fragment to Activity
✅ Static (XML)

<fragment
    android:id="@+id/staticFragment"
    android:name="com.example.MyFragment"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />

✅ Dynamic (Kotlin)

val fragment = MyFragment()
supportFragmentManager.beginTransaction()
    .replace(R.id.fragmentContainer, fragment)
    .commit()

In your activity layout:

<FrameLayout
    android:id="@+id/fragmentContainer"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />

🔁 Fragment Lifecycle
| Lifecycle Method         | Description                      |
| ------------------------ | -------------------------------- |
| `onAttach()`             | Fragment is attached to Activity |
| `onCreate()`             | Initialization work              |
| `onCreateView()`         | Inflate layout                   |
| `onViewCreated()`        | View is fully created            |
| `onStart()`/`onResume()` | UI is visible/active             |
| `onPause()`/`onStop()`   | UI is no longer visible          |
| `onDestroyView()`        | View is destroyed                |
| `onDestroy()`            | Cleanup                          |
| `onDetach()`             | Fragment detached from Activity  |


🔗 Fragment ↔ Activity Communication
🔹 From Fragment to Activity (Interface)
interface MyListener {
    fun onMessageSent(msg: String)
}

class MyFragment : Fragment() {
    private var listener: MyListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? MyListener
    }

    fun sendMessage() {
        listener?.onMessageSent("Hello Activity!")
    }
}


class MainActivity : AppCompatActivity(), MyListener {
    override fun onMessageSent(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}

🔹 From Activity to Fragment

val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as MyFragment
fragment.updateUI("Message from Activity")

fun updateUI(text: String) {
    view?.findViewById<TextView>(R.id.fragmentText)?.text = text
}


💡 Common Use Cases
| Feature              | Fragments Example                         |
| -------------------- | ----------------------------------------- |
| Tabbed UI            | Each tab is a fragment                    |
| Master-Detail Layout | One for list, one for detail              |
| Settings Screen      | Each settings category is a fragment      |
| Bottom Navigation    | Fragments switch via BottomNavigationView |

📘 Summary
Fragments are reusable UI components.
You can load them statically or dynamically.
They have a rich lifecycle for fine control.
Communication is done using interfaces or FragmentManager.