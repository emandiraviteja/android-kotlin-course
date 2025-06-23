# ✅  5.1 ViewModel & LiveData

---

## 👉️ MVVM Architecture Basics

**Definition:**

MVVM (Model-View-ViewModel) is an architecture pattern that helps separate concerns:

Model: Data layer (Room, Retrofit, etc.)
View: UI (Activity/Fragment/Composable)
ViewModel: Holds UI logic and LiveData

**Why use MVVM?**
Separation of concerns
Easier testing
Cleaner, maintainable code
Handles config changes better

**Where to use:**
Any Android app with complex UI and data handling.

**Example:**
// Model (e.g., Data class)
```
data class User(val name: String, val age: Int)
```

// ViewModel
```
class UserViewModel : ViewModel() {
    val user = MutableLiveData<User>()
    fun updateUser(name: String, age: Int) {
        user.value = User(name, age)
    }
}
```

// View (Fragment)
```
class UserFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.user.observe(viewLifecycleOwner) {
            // update UI here
        }
    }
}
```

**ViewModel**

ViewModel is a class designed to store and manage UI-related data in a lifecycle conscious way.

**Why use ViewModel?**
Survives configuration changes (e.g., screen rotation)
Keeps UI data separate from Activity/Fragment

**Where to use:**
In any screen (Activity/Fragment) that has data to display or logic to maintain (e.g., form, list, counter).

**Example:**

```
class CounterViewModel : ViewModel() {
    var counter = MutableLiveData(0)

    fun increment() {
        counter.value = (counter.value ?: 0) + 1
    }
}
```

Use in Fragment:
```
val viewModel = ViewModelProvider(this).get(CounterViewModel::class.java)

binding.button.setOnClickListener {
    viewModel.increment()
}
viewModel.counter.observe(viewLifecycleOwner) {
    binding.textView.text = it.toString()
}
```

**LiveData**
LiveData is an observable data holder class that is lifecycle-aware. UI components observe it and automatically update when the data changes.

**Why use LiveData?**
Prevents memory leaks
Automatically updates UI on data change
Handles lifecycle of observer (Fragment/Activity)

**Where to use:**
When data needs to update UI (e.g., user profile, form validation, API data)

**Example:**

```
val userName = MutableLiveData<String>()

fun updateName(newName: String) {
    userName.value = newName
}
```

In Fragment:

```
viewModel.userName.observe(viewLifecycleOwner) {
    binding.userNameText.text = it
}
```

## 👉️ Lifecycle-aware Components
**Definition:**
Lifecycle-aware components (like ViewModel and LiveData) automatically respond to lifecycle changes (e.g., onStart, onDestroy).

**Why use them?**
Avoid memory leaks
Avoid crashes from null views
Simplify data handling

**Where to use:**
Any component that depends on Activity/Fragment lifecycle (e.g., LiveData, LifecycleObserver)

**Example:**

```
viewModel.liveData.observe(viewLifecycleOwner) {
    // Automatically stops observing when Fragment is destroyed
}
```

## 👉️ Sharing Data Across Fragments

**Definition:**
When multiple fragments (e.g., in ViewPager or BottomNavigation) need to share data, we use a shared ViewModel scoped to the Activity.

**Why use this?**
Avoid passing data manually
Avoid using Bundles or interfaces
Centralized data source

**Where to use:**
Fragment-to-fragment communication (e.g., step forms, multi-tab screens)

**Example:**

// SharedViewModel
```
class SharedViewModel : ViewModel() {
    val selectedItem = MutableLiveData<String>()
}
```

// In both fragments
```
val viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
```

// Fragment A - set data
```
viewModel.selectedItem.value = "Apple"
```

// Fragment B - observe data
```
viewModel.selectedItem.observe(viewLifecycleOwner) {
    binding.resultText.text = it
}
```

---

**Summary Table**

| Concept          | What it is                             | Why use                          | Where to use                |
| ---------------- | -------------------------------------- | -------------------------------- | --------------------------- |
| MVVM             | App architecture pattern               | Clean separation of UI and logic | Any modern Android app      |
| ViewModel        | Holds UI data                          | Survives config changes          | All Fragments/Activities    |
| LiveData         | Observable lifecycle-aware data holder | Auto-updates UI, avoids leaks    | With ViewModel for UI       |
| Lifecycle-aware  | Reacts to lifecycle changes            | No leaks/crashes                 | LiveData, LifecycleObserver |
| Shared ViewModel | Share data across Fragments            | Avoid bundles/interfaces         | Multi-tab or step flows     |

---

**Mini App: Profile App (MVVM + ViewModel + LiveData)**

**Features:**
Show user profile (name, email, age)
Edit profile and update LiveData
Shared ViewModel across fragments
Lifecycle-safe UI updates

**Project Structure (MVVM)**
com.example.profileapp
├── model
│   └── User.kt
├── view
│   ├── ProfileFragment.kt
│   └── EditProfileFragment.kt
├── viewmodel
│   └── ProfileViewModel.kt
└── MainActivity.kt

**What This Project Teaches**

| Concept          | Implementation                                              |
| ---------------- | ----------------------------------------------------------- |
| MVVM             | Clear separation: Model, ViewModel, Fragments               |
| ViewModel        | Holds profile data, survives config change                  |
| LiveData         | Updates UI automatically when data changes                  |
| Shared ViewModel | Shared between ProfileFragment and EditProfileFragment      |
| Lifecycle-aware  | Observers use `viewLifecycleOwner` to prevent leaks/crashes |