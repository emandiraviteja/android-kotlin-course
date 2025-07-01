# ✅  6.1  Permissions & Connectivity

---

## 👉 1. What is an API?
🔹 Definition:

API (Application Programming Interface) is a bridge that allows two systems (like your app and a server) to communicate and exchange data.

GET request → Customer asks for food → Waiter brings it from kitchen

POST request → Customer tells waiter a new dish to add → Waiter updates kitchen

HTTP Methods

| Method | Purpose       | Example        |
| ------ | ------------- | -------------- |
| GET    | Get data      | Get user list  |
| POST   | Send new data | Add new user   |
| PUT    | Update data   | Update profile |
| DELETE | Remove data   | Delete user    |


2. What is JSON?
   🔹 Definition:

JSON (JavaScript Object Notation) is a lightweight format to send structured data between app and server.
🔹 Example JSON:

```
{
  "id": 101,
  "name": "Ravi Teja",
  "email": "ravi@example.com"
}
```

Convert JSON to Kotlin:

data class User(
val id: Int,
val name: String,
val email: String
)

📌 JSON is the language of APIs. Almost every API sends and receives data in JSON format.

3. What is Retrofit?
   🔹 Definition:

Retrofit is a powerful HTTP client library for Android, created by Square.
It simplifies calling REST APIs and handles JSON conversion automatically.
❌ Without Retrofit:

You need to:

    Use HttpURLConnection

    Write boilerplate code

    Parse JSON manually

✅ With Retrofit:

    Just define interface methods with @GET, @POST

    It handles everything:

        Network calls

        JSON parsing

        Background threads

Retrofit Features:
| Feature         | Benefit                      |
| --------------- | ---------------------------- |
| `@GET`, `@POST` | Easy API declaration         |
| Gson support    | Auto JSON to Kotlin model    |
| Interceptors    | Logging and header injection |
| OkHttp support  | Fast, efficient connections  |


4. Add Dependencies

Add these in build.gradle (app):
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
implementation 'com.squareup.okhttp3:logging-interceptor:4.12.0'


5. Create Model Class (User.kt)

data class User(
val id: Int,
val name: String,
val email: String
)

6. Create API Interface (ApiService.kt)

import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("users")
    fun getUsers(): Call<List<User>>

    @POST("users")
    fun createUser(@Body user: User): Call<User>
}

7. Retrofit Singleton (RetrofitClient.kt)

object RetrofitClient {
private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

ℹ️ We use jsonplaceholder.typicode.com for free testing.

8. GET Request Example

RetrofitClient.instance.getUsers().enqueue(object : Callback<List<User>> {
override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
if (response.isSuccessful) {
response.body()?.forEach {
Log.d("GET", "User: ${it.name} - ${it.email}")
}
}
}

    override fun onFailure(call: Call<List<User>>, t: Throwable) {
        Log.e("GET Error", t.message ?: "Unknown error")
    }
})

✅ 9. POST Request Example

val newUser = User(0, "Teja", "teja@email.com")

RetrofitClient.instance.createUser(newUser).enqueue(object : Callback<User> {
override fun onResponse(call: Call<User>, response: Response<User>) {
if (response.isSuccessful) {
val createdUser = response.body()
Log.d("POST", "Created: ${createdUser?.name}")
}
}

    override fun onFailure(call: Call<User>, t: Throwable) {
        Log.e("POST Error", t.message ?: "Unknown error")
    }
})

10. Interceptors (Logging / Auth)
    🔹 Logging Interceptor

Used to debug API requests/responses in Logcat.

val logging = HttpLoggingInterceptor().apply {
level = HttpLoggingInterceptor.Level.BODY
}

🔹 Authorization Interceptor (Bearer Token)

val authInterceptor = Interceptor { chain ->
val request = chain.request().newBuilder()
.addHeader("Authorization", "Bearer YOUR_API_TOKEN")
.build()
chain.proceed(request)
}

Attach both in OkHttpClient:

val client = OkHttpClient.Builder()
.addInterceptor(authInterceptor)
.addInterceptor(logging)
.build()


| Concept      | Purpose                       |
| ------------ | ----------------------------- |
| API          | Communication with server     |
| JSON         | Data format used in APIs      |
| Retrofit     | Simplifies API calls          |
| Gson         | Auto JSON ↔ Kotlin conversion |
| Interceptors | Logging and headers           |


Mini Project

Create UserApp that:

    Uses GET to fetch users and show in RecyclerView

    Uses POST to add a new user from EditText

    Shows response logs in Logcat

Bonus:

    Add ProgressBar while loading

    Show error using Toast

Includes:

    MainActivity.kt: GET & POST API calls + RecyclerView

    User.kt, ApiService.kt, RetrofitClient.kt

    UserAdapter.kt: RecyclerView Adapter

    activity_main.xml + item_user.xml

    Manifest with internet permission
    Tapping on a user item in RecyclerView shows a Toast with their name.

UserAdapter.kt uses a lambda onItemClick(user) for cleaner code.

MainActivity.kt handles item click inside adapter initialization.