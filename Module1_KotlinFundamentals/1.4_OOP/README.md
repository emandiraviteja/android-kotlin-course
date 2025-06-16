# ✅ 1.4 Object-Oriented Programming

This lesson covers core object-oriented programming (OOP) concepts in Kotlin with real-world examples and syntax breakdowns.

---

## 👉️ Classes & Objects

### ⚡ Class Declaration
```
class Person {
    var name: String = ""
    var age: Int = 0

    fun introduce() {
        println("Hi, my name is $name and I'm $age years old.")
    }
}
```

### ⚡ Creating an Object (Instance)
```
val person = Person()
person.name = "Laalasa"
person.age = 25
person.introduce()

Output:
Hi, my name is Laalasa and I'm 25 years old.
```

## 👉️ Constructors
### ⚡ What is a Constructor?
A constructor is a special function that is automatically called when an object is created. It initializes the properties of the class.

Primary Constructor
Secondary Constructor
Initializer Block (init)


### ⚡  Primary Constructor

**Definition:**
Concise way to declare and initialize properties in the class header.

**Syntax:**
```
class ClassName(val param1: Type, var param2: Type)
```

**Example:**
```
class Student(val name: String, var age: Int)

fun main() {
    val s = Student("Ravi", 21)
    println("${s.name} is ${s.age} years old")
}
```

### ⚡ Secondary Constructor

**Use Case:**
Used when you need multiple constructors or to add custom logic.

**Syntax:**
```
class Book {
    var title: String
    var author: String

    constructor(title: String, author: String) {
        this.title = title
        this.author = author
    }
}
```

**Example:**
```
fun main() {
    val b = Book("Kotlin Basics", "JetBrains")
    println("${b.title} by ${b.author}")
}
```

### ⚡ Initializer Block (init)

Used to write logic that runs when the object is created.

Example:
```
class Person(val name: String, var age: Int) {
    init {
        println("Person Created: $name is $age years old")
    }
}

fun main() {
    val p = Person("Anita", 22)
}

Output:
Person Created: Anita is 22 years old
```

### ⚡ Combining Primary & Secondary Constructors
If both are used, the secondary constructor must call the primary constructor using : this(...).

Syntax:
```
class Car(val brand: String) {
    var year: Int = 0

    constructor(brand: String, year: Int): this(brand) {
        this.year = year
    }
}

fun main() {
    val c = Car("Toyota", 2020)
    println("${c.brand} - ${c.year}")
}
```

### ⚡ Constructor Summary
| Type       | Location        | Use Case                                |
|------------|------------------|------------------------------------------|
| Primary    | Class Header     | Basic property initialization            |
| Secondary  | Inside Class     | Overloading or adding extra logic        |
| `init` Block | Inside Class   | Logic that runs after primary constructor |

## 👉️ Inheritance & Polymorphism

### ⚡ Inheritance
**Syntax:**
```
open class Animal {
    open fun sound() {
        println("Animal sound")
    }
}

class Dog : Animal() {
    override fun sound() {
        println("Woof!")
    }
}

val dog = Dog()
dog.sound() // Output: Woof!

open: Allows a class/method to be inherited or overridden
override: Overrides a superclass method
```

### ⚡ Polymorphism
```
val animal: Animal = Dog()
animal.sound() // Output: Woof! (determined at runtime)
```

## 👉️ Interfaces
```
interface Drivable {
    fun drive()
}
```

**Implementing an Interface**
```
class Car : Drivable {
    override fun drive() {
        println("Car is driving")
    }
}

val car = Car()
car.drive() // Output: Car is driving
```

## 👉️ Data Classes
What is a Data Class?
A data class is a special class in Kotlin that is meant to hold data only. When you declare a class as a data class, Kotlin automatically provides:

equals() – to compare two objects
hashCode() – used in hashing collections like HashMap
toString() – readable string format of the object
copy() – to clone the object with or without changes
componentN() – for destructuring (e.g., val (name, age) = user)

Syntax
```
data class User(val name: String, val age: Int)

fun main() {
    val u1 = User("Ravi", 25)
    val u2 = u1.copy(age = 26)

    println(u1) // Output: User(name=Ravi, age=25)
    println(u2) // Output: User(name=Ravi, age=26)
}
```

### ⚡ Rules for Data Classes

    Must have at least one val or var in the primary constructor
    Cannot be abstract, open, sealed, or inner
    Best used as pure data holders

## 👉️ Sealed Classes
What is a Sealed Class?
A sealed class is used when you want to restrict a class hierarchy to a fixed set of types. It's great for modeling things like:

    API responses (Success, Error)
    UI states (Loading, Content, Error)
    Actions (Start, Stop, Pause)

Example: Sealed Class for Result
```
// Define a sealed class named Result
sealed class Result
```
sealed means the class can't be directly instantiated.
You can only subclass it within the same file.
Think of it as a sealed group of child types.
```
class Success(val data: String) : Result()
class Error(val message: String) : Result()

fun handle(result: Result) {
    when (result) {
        is Success -> println("Data: ${result.data}")
        is Error -> println("Error: ${result.message}")
    }
}

fun main() {
    handle(Success("Loaded"))
    handle(Error("404 Not Found"))
}
```

### ⚡Rules:

    All subclasses must be in the same file
    Cannot be instantiated directly
    Ensures exhaustive when expressions in Kotlin

