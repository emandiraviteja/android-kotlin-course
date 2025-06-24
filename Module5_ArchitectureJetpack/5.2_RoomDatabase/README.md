# ✅  5.2 Room Database

---

### ⚡ What is Room Database?

Room is a SQLite object mapping library provided by Android Jetpack. It helps you easily work with SQLite databases in a clean and structured way using annotations and Kotlin/Java objects.

**Components of Room**
Room has 3 main components:
- Entity – Represents a table in the database.
- DAO (Data Access Object) – Defines methods to access the database (insert, query, delete, etc.)
- Database class – Connects everything and creates the database instance.

## 👉️ Entity

An Entity is a data class that represents a table in the Room database. Each instance of the class is a row in the table.

**Example:

```
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val email: String
)
```

- `@Entity`: Marks the class as a database table.
- `@PrimaryKey`: Specifies the primary key column.

---

## 👉️ DAO (Data Access Object)

DAO is an interface where you define all your SQL operations using annotations like `@Insert`, `@Query`, `@Delete`, etc.

```
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users")
    fun getAllUsers(): LiveData<List<User>>

    @Delete
    suspend fun deleteUser(user: User)

    @Update
    suspend fun updateUser(user: User)
}
```

- `suspend` is used for background operations (coroutines).
- `LiveData` is used to observe data changes.

---

## 👉️ Database Class

An abstract class that extends RoomDatabase and links the DAOs and entities.

```
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
```

- `@Database`: Specifies the entities and version.
- Singleton pattern used for creating only one instance of the database.
-
---

## 👉️ Querying

Queries are defined using the @Query annotation inside DAO. You write SQL-like syntax to fetch, filter, and update data.

```
@Query("SELECT * FROM users WHERE name LIKE :searchName")
fun searchUserByName(searchName: String): LiveData<List<User>>
```
- we can use parameters like `:searchName`.
- Supports filtering, ordering, and pagination.
-

---

## 👉️  Observing Data with LiveData

LiveData is an observable data holder class. When Room returns LiveData, your UI will be automatically updated if the data changes in the database.

```
// In DAO
@Query("SELECT * FROM users")
fun getAllUsers(): LiveData<List<User>>

// In ViewModel
val allUsers: LiveData<List<User>> = userDao.getAllUsers()

// In Fragment/Activity
userViewModel.allUsers.observe(viewLifecycleOwner) { users ->
    // update RecyclerView
}
```

**Benefits:**
Lifecycle-aware
Auto-updates UI when database changes
Easy to combine with MVVM

**Summary Table**

| Concept  | Description                           | Keyword     |
| -------- | ------------------------------------- | ----------- |
| Entity   | Defines table structure               | `@Entity`   |
| DAO      | Interface for database operations     | `@Dao`      |
| Database | Abstract class extending RoomDatabase | `@Database` |
| Querying | SQL queries in DAO                    | `@Query`    |
| LiveData | Observes data changes automatically   | `LiveData`  |

**build.gradle (Project level)**

Make sure to have:
```
buildscript {
    ext {
        kotlin_version = '1.9.0' // Use latest stable
    }
}
```

**build.gradle (App level)**

```
plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'kotlin-kapt'
}

android {
    compileSdk 34

    defaultConfig {
        applicationId "com.example.usermanager"
        minSdk 21
        targetSdk 34
        versionCode 1
        versionName "1.0"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Core
    implementation 'androidx.core:core-ktx:1.13.1'
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.11.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'

    // RecyclerView
    implementation 'androidx.recyclerview:recyclerview:1.3.2'

    // Lifecycle components (LiveData, ViewModel)
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.7.0'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0'

    // Room components
    implementation 'androidx.room:room-runtime:2.6.1'
    kapt 'androidx.room:room-compiler:2.6.1'
    implementation 'androidx.room:room-ktx:2.6.1'

    // Coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3'

    // Optional - for testing
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
}

```

---
## 👉 Mini-project using Room with MVVM

- Room Database
- MVVM Architecture
- LiveData + ViewModel
- Repository Pattern

**Project: Simple User Manager App**

**Features:**
Add user (name, email)
View all users in a list (LiveData + RecyclerView)
Update and delete user (optional)

**Step-by-Step Structure**

com.example.usermanager
├── data
│   ├── User.kt               // Entity
│   ├── UserDao.kt            // DAO
│   └── AppDatabase.kt        // Room DB
│
├── repository
│   └── UserRepository.kt     // Repository
│
├── viewmodel
│   └── UserViewModel.kt      // ViewModel
│
├── ui
│   ├── MainActivity.kt       // UI
│   ├── UserAdapter.kt        // RecyclerView Adapter
│   └── activity_main.xml     // Layout