# ✅  7.1 Jetpack Compose
---

## 👉 Jetpack Compose (Intro)
Jetpack Compose is Android’s modern toolkit for building native UI using Kotlin. Instead of writing XML layouts, you define UI directly in Kotlin code using composable functions.

---

## 👉 Compose UI Basics

- @Composable functions are used to create UI components.
- Everything is a function: buttons, text, images, etc.

**Example:**
```
@Composable
fun Greeting(name: String) {
Text(text = "Hello, $name!")
}
```
**Explanation:**
- `@Composable`: Marks a function that builds UI.
- `Text`: A basic UI element to show text.
- `"Hello, $name!"`: String interpolation to personalize output.

Full Example with `setContent`
```
class MainActivity : ComponentActivity() {
override fun onCreate(savedInstanceState: Bundle?) {
super.onCreate(savedInstanceState)
setContent {
Greeting("Raviteja")
}
}
}
```

---

## 👉 State Handling (State, MutableState)
- `State`: Used to hold UI data that can change.
- `remember`: Remembers the state across recompositions.
- `mutableStateOf`: Creates a mutable value that triggers UI updates.

**Example:**
```
@Composable
fun CounterApp() {
var count by remember { mutableStateOf(0) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Count: $count")
        Button(onClick = { count++ }) {
            Text("Increment")
        }
    }
}
```

**Explanation:**
- `var count by remember { mutableStateOf(0) }`: Holds and remembers the count.
- `count++`: Increments the state, which causes recomposition.
- `Text(...)` and `Button(...)`: Basic UI controls.

---

## 👉 Lazy Lists (LazyColumn)

A performant way to show large or scrollable lists.

**Example:**
```
@Composable
fun NameList(names: List<String>) {
LazyColumn {
items(names) { name ->
Text(text = "Name: $name", modifier = Modifier.padding(8.dp))
}
}
}
```

**Explanation:**
- LazyColumn: Like RecyclerView, but easier.
- items: Iterates over the list and creates a composable for each item.

---

## 👉 Navigation in Compose
Jetpack Compose provides Navigation via a special library:

Add dependency:
```
implementation "androidx.navigation:navigation-compose:2.7.0"
```

**Example with 2 screens:**
```
@Composable
fun ScreenA(navController: NavController) {
Button(onClick = { navController.navigate("screenB") }) {
Text("Go to Screen B")
}
}

@Composable
fun ScreenB() {
Text("Welcome to Screen B")
}

@Composable
fun MyAppNavHost(navController: NavHostController) {
NavHost(navController, startDestination = "screenA") {
composable("screenA") { ScreenA(navController) }
composable("screenB") { ScreenB() }
}
}
```

In MainActivity.kt
```
setContent {
val navController = rememberNavController()
MyAppNavHost(navController)
}
```

**Explanation:**
- `NavController`: Handles screen navigation.
- `NavHost`: Defines navigation graph.
- `composable("screenX")`: Maps routes to screens.

**Summary**
| Concept            | Used For                   | Key API                |
| ------------------ | -------------------------- | ---------------------- |
| `@Composable`      | Building UI                | `Text`, `Button`, etc. |
| `mutableStateOf()` | Hold UI state              | `remember`             |
| `LazyColumn`       | Efficient vertical lists   | `items()`              |
| `NavController`    | Navigation between screens | `navigate()`           |

---

## 👉 Mini Project: Counter + List + Navigation

Dependencies in build.gradle(:app)

Make sure you add this for navigation:
```
implementation "androidx.navigation:navigation-compose:2.7.0"
```
And for Material3 (if not added):
```
implementation "androidx.compose.material3:material3:1.1.0"
```

in build.gradle(app)
```
kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
```

Files:
MainActivity.kt
navigation/AppNavigation.kt
screens/CounterScreen.kt
screens/ListScreen.kt
ui/theme/Color.kt

Color.kt defines the color palette for your app — just like colors.xml in XML-based UI.
In Compose, instead of using XML, you define colors in a Kotlin file, like:
```
val Purple40 = Color(0xFF6750A4)
val Pink80 = Color(0xFFEFB8C8)
```

In XML UI:
```
<color name="primaryColor">#6750A4</color>
```
In Compose:
```
val Purple40 = Color(0xFF6750A4)
```

What is 0xFF6750A4?
0xAARRGGBB
| Part | Meaning               | Example             |
| ---- | --------------------- | ------------------- |
| `AA` | Alpha (opacity)       | `FF` = 100% visible |
| `RR` | Red (0–255, in hex)   | `67` = 103          |
| `GG` | Green (0–255, in hex) | `50` = 80           |
| `BB` | Blue (0–255, in hex)  | `A4` = 164          |

ui/theme/Typography.kt
ui/theme/Theme.kt