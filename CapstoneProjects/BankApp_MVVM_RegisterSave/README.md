✅ APP FEATURES
1. Splash Screen

   Checks if user is logged in.

   If logged in → redirect to Home Screen.

   If not logged in → redirect to Login Screen.

2. Login Screen

   Fields: Email, Password

   Buttons:

        Login: Verifies user from DB

            If user exists & credentials match → Save session → Go to Home

            If user does not exist → Show alert with “Register” option

        Register (Bottom): Redirects to Register Screen

3. Register Screen

   Fields: Name, Email, Password, Phone Number

   Button:

        Register: Save user to DB and redirect to Login Screen

4. Home Screen

   Shows user-specific transactions in a RecyclerView

   Top-right Profile Icon:

        Opens Profile Screen

5. Profile Screen

   Shows user details from DB

   Editable fields (name, email, phone)

   Buttons:

        Update: Saves changes to DB

        Logout: Clears session → Goes to Login Screen

        Delete Account: Deletes user from DB → Goes to Register Screen

PROJECT STRUCTURE

com.example.myapp/
│
├── data/
│   ├── db/
│   │   ├── AppDatabase.kt
│   │   └── UserDao.kt
│   │
│   ├── model/
│   │   ├── User.kt
│   │   └── Transaction.kt
│   │
│   └── repository/
│       ├── UserRepository.kt
│       └── TransactionRepository.kt
│
├── ui/
│   ├── splash/
│   │   └── SplashActivity.kt
│   │
│   ├── login/
│   │   ├── LoginFragment.kt
│   │   └── LoginViewModel.kt
│   │
│   ├── register/
│   │   ├── RegisterFragment.kt
│   │   └── RegisterViewModel.kt
│   │
│   ├── home/
│   │   ├── HomeFragment.kt
│   │   ├── HomeViewModel.kt
│   │   └── TransactionAdapter.kt
│   │
│   ├── profile/
│   │   ├── ProfileFragment.kt
│   │   └── ProfileViewModel.kt
│   │
│   └── MainActivity.kt (hosts fragments via Navigation Component)
│
├── utils/
│   ├── SessionManager.kt (SharedPreferences for login session)
│   └── Extensions.kt (common helpers)
│
├── navigation/
│   └── nav_graph.xml (Navigation graph)
│
└── res/
├── layout/
├── drawable/
└── values/


✅ Step 1: Setup Basic Project with Required Dependencies
1️⃣ Create a new Android project

    Language: Kotlin

    Minimum SDK: 21 or higher

    Use Empty Activity

    Name: LoginRoomApp (or your choice)

2️⃣ Add Required Dependencies in build.gradle(:app)

// ViewModel & LiveData
implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0"
implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.7.0"

// Navigation Component
implementation "androidx.navigation:navigation-fragment-ktx:2.7.7"
implementation "androidx.navigation:navigation-ui-ktx:2.7.7"

// Room components
implementation "androidx.room:room-runtime:2.6.1"
kapt "androidx.room:room-compiler:2.6.1"
implementation "androidx.room:room-ktx:2.6.1"

// Coroutines
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3"

// Material Design
implementation 'com.google.android.material:material:1.11.0'

// ViewBinding
buildFeatures {
viewBinding true
}

Also add in build.gradle(:project) if missing:

plugins {
id 'kotlin-kapt'
}

✅ Step 2: Define Room Entities and DAO
🔹 User.kt

@Entity(tableName = "users")
data class User(
@PrimaryKey(autoGenerate = true) val id: Int = 0,
val name: String,
val email: String,
val password: String,
val phone: String
)

🔹 Transaction.kt (for later use)

@Entity(tableName = "transactions")
data class Transaction(
@PrimaryKey(autoGenerate = true) val id: Int = 0,
val userId: Int,
val amount: Double,
val description: String,
val date: String
)

✅ Step 3: Create DAO
🔹 UserDao.kt

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun login(email: String, password: String): User?

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}

✅ Step 4: Create Room Database
🔹 AppDatabase.kt

@Database(entities = [User::class, Transaction::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "login_room_app_db"
                ).build().also { instance = it }
            }
        }
    }
}

✅ Step 5: Create Repository
🔹 UserRepository.kt

class UserRepository(private val userDao: UserDao) {

    suspend fun insertUser(user: User) = userDao.insertUser(user)

    suspend fun login(email: String, password: String): User? = userDao.login(email, password)

    suspend fun getUserByEmail(email: String): User? = userDao.getUserByEmail(email)

    suspend fun updateUser(user: User) = userDao.updateUser(user)

    suspend fun deleteUser(user: User) = userDao.deleteUser(user)
}

✅ Step 6: Create Session Manager
🔹 SessionManager.kt

class SessionManager(context: Context) {
private val prefs = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    fun saveLogin(userId: Int) {
        prefs.edit().putInt("USER_ID", userId).apply()
    }

    fun getUserId(): Int = prefs.getInt("USER_ID", -1)

    fun logout() {
        prefs.edit().clear().apply()
    }

    fun isLoggedIn(): Boolean = getUserId() != -1
}

✅ You're now ready with:

    Room DB setup

    User entity and DAO

    User repository

    Session manager

Nxt, Splash screen and navigation setup step by step

✅ Step 7: Navigation Component Setup
🔹 1. Add Navigation Graph

📁 Create this file:
res/navigation/nav_graph.xml

<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
app:startDestination="@id/splashFragment">

    <fragment
        android:id="@+id/splashFragment"
        android:name="com.example.loginroomapp.ui.splash.SplashFragment"
        android:label="SplashFragment" >
        <action
            android:id="@+id/action_splashFragment_to_loginFragment"
            app:destination="@id/loginFragment"
            app:popUpTo="@id/splashFragment"
            app:popUpToInclusive="true"/>
        <action
            android:id="@+id/action_splashFragment_to_homeFragment"
            app:destination="@id/homeFragment"
            app:popUpTo="@id/splashFragment"
            app:popUpToInclusive="true"/>
    </fragment>

    <fragment
        android:id="@+id/loginFragment"
        android:name="com.example.loginroomapp.ui.login.LoginFragment"
        android:label="LoginFragment" >
        <action
            android:id="@+id/action_loginFragment_to_registerFragment"
            app:destination="@id/registerFragment" />
        <action
            android:id="@+id/action_loginFragment_to_homeFragment"
            app:destination="@id/homeFragment" />
    </fragment>

    <fragment
        android:id="@+id/registerFragment"
        android:name="com.example.loginroomapp.ui.register.RegisterFragment"
        android:label="RegisterFragment" >
        <action
            android:id="@+id/action_registerFragment_to_loginFragment"
            app:destination="@id/loginFragment" />
    </fragment>

    <fragment
        android:id="@+id/homeFragment"
        android:name="com.example.loginroomapp.ui.home.HomeFragment"
        android:label="HomeFragment" >
        <action
            android:id="@+id/action_homeFragment_to_profileFragment"
            app:destination="@id/profileFragment" />
    </fragment>

    <fragment
        android:id="@+id/profileFragment"
        android:name="com.example.loginroomapp.ui.profile.ProfileFragment"
        android:label="ProfileFragment" >
        <action
            android:id="@+id/action_profileFragment_to_loginFragment"
            app:destination="@id/loginFragment"
            app:popUpTo="@id/splashFragment"
            app:popUpToInclusive="true"/>
        <action
            android:id="@+id/action_profileFragment_to_registerFragment"
            app:destination="@id/registerFragment"
            app:popUpTo="@id/splashFragment"
            app:popUpToInclusive="true"/>
    </fragment>
</navigation>

🔹 2. Setup MainActivity.kt with NavHost

📁 MainActivity.kt

class MainActivity : AppCompatActivity() {
private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment

        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_container)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}

🗂 activity_main.xml

<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
android:layout_width="match_parent"
android:layout_height="match_parent">

    <fragment
        android:id="@+id/nav_host_fragment_container"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:navGraph="@navigation/nav_graph"
        app:defaultNavHost="true" />

</androidx.constraintlayout.widget.ConstraintLayout>

✅ Step 8: Splash Screen Fragment
🔹 SplashFragment.kt

📁 ui/splash/SplashFragment.kt

class SplashFragment : Fragment() {
private var _binding: FragmentSplashBinding? = null
private val binding get() = _binding!!

    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sessionManager = SessionManager(requireContext())

        Handler(Looper.getMainLooper()).postDelayed({
            if (sessionManager.isLoggedIn()) {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }
        }, 2000) // 2 seconds splash delay
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

🔹 fragment_splash.xml

📁 res/layout/fragment_splash.xml

<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:background="#FFFFFF"
android:gravity="center">

    <TextView
        android:text="Login Room App"
        android:textSize="24sp"
        android:textStyle="bold"
        android:textColor="#000"
        android:layout_gravity="center"/>
</FrameLayout>

✅ What’s Next?

    Login Screen UI + Logic (with ViewModel and DB check)

✅ Step 9: Login Screen – UI & Logic
🔹 1. Create LoginFragment.kt

📁 ui/login/LoginFragment.kt

class LoginFragment : Fragment() {
private var _binding: FragmentLoginBinding? = null
private val binding get() = _binding!!

    private lateinit var viewModel: LoginViewModel
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sessionManager = SessionManager(requireContext())

        val userDao = AppDatabase.getInstance(requireContext()).userDao()
        val repository = UserRepository(userDao)
        viewModel = ViewModelProvider(this, LoginViewModelFactory(repository))[LoginViewModel::class.java]

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val user = viewModel.loginUser(email, password)
                if (user != null) {
                    sessionManager.saveLogin(user.id)
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                } else {
                    val userExists = viewModel.checkUserExists(email)
                    if (userExists) {
                        Toast.makeText(requireContext(), "Incorrect password", Toast.LENGTH_SHORT).show()
                    } else {
                        AlertDialog.Builder(requireContext())
                            .setTitle("User Not Registered")
                            .setMessage("Would you like to register?")
                            .setPositiveButton("Register") { _, _ ->
                                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
                            }
                            .setNegativeButton("Cancel", null)
                            .show()
                    }
                }
            }
        }

        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

🔹 2. Create ViewModel for Login

📁 ui/login/LoginViewModel.kt

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    suspend fun loginUser(email: String, password: String): User? {
        return repository.login(email, password)
    }

    suspend fun checkUserExists(email: String): Boolean {
        return repository.getUserByEmail(email) != null
    }
}

class LoginViewModelFactory(private val repository: UserRepository) :
ViewModelProvider.Factory {
override fun <T : ViewModel> create(modelClass: Class<T>): T {
return LoginViewModel(repository) as T
}
}

🔹 3. Create Login Screen Layout

📁 res/layout/fragment_login.xml

<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:padding="24dp"
android:background="#FFFFFF">

    <LinearLayout
        android:orientation="vertical"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center">

        <TextView
            android:text="Login"
            android:textSize="24sp"
            android:textStyle="bold"
            android:layout_gravity="center"
            android:layout_marginBottom="32dp" />

        <EditText
            android:id="@+id/etEmail"
            android:hint="Email"
            android:inputType="textEmailAddress"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="16dp" />

        <EditText
            android:id="@+id/etPassword"
            android:hint="Password"
            android:inputType="textPassword"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="24dp" />

        <Button
            android:id="@+id/btnLogin"
            android:text="Login"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="16dp" />

        <TextView
            android:id="@+id/tvRegister"
            android:text="Don't have an account? Register"
            android:textColor="#1976D2"
            android:gravity="center"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:paddingTop="16dp" />
    </LinearLayout>
</ScrollView>


✅ Step 10: Register Screen – UI & Logic
🔹 1. Create RegisterFragment.kt

📁 ui/register/RegisterFragment.kt

class RegisterFragment : Fragment() {
private var _binding: FragmentRegisterBinding? = null
private val binding get() = _binding!!

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val userDao = AppDatabase.getInstance(requireContext()).userDao()
        val repository = UserRepository(userDao)
        viewModel = ViewModelProvider(this, RegisterViewModelFactory(repository))[RegisterViewModel::class.java]

        binding.btnRegister.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val phone = binding.etPhone.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
                Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val existingUser = viewModel.checkUserExists(email)
                if (existingUser) {
                    Toast.makeText(requireContext(), "User already exists", Toast.LENGTH_SHORT).show()
                } else {
                    val user = User(name = name, email = email, password = password, phone = phone)
                    viewModel.registerUser(user)
                    Toast.makeText(requireContext(), "Registered successfully", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

🔹 2. Create ViewModel for Register

📁 ui/register/RegisterViewModel.kt

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {

    suspend fun checkUserExists(email: String): Boolean {
        return repository.getUserByEmail(email) != null
    }

    suspend fun registerUser(user: User) {
        repository.insertUser(user)
    }
}

class RegisterViewModelFactory(private val repository: UserRepository) :
ViewModelProvider.Factory {
override fun <T : ViewModel> create(modelClass: Class<T>): T {
return RegisterViewModel(repository) as T
}
}

🔹 3. Create Register Screen Layout

📁 res/layout/fragment_register.xml

<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:padding="24dp"
android:background="#FFFFFF">

    <LinearLayout
        android:orientation="vertical"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center">

        <TextView
            android:text="Register"
            android:textSize="24sp"
            android:textStyle="bold"
            android:layout_gravity="center"
            android:layout_marginBottom="32dp" />

        <EditText
            android:id="@+id/etName"
            android:hint="Name"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="16dp" />

        <EditText
            android:id="@+id/etEmail"
            android:hint="Email"
            android:inputType="textEmailAddress"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="16dp" />

        <EditText
            android:id="@+id/etPassword"
            android:hint="Password"
            android:inputType="textPassword"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="16dp" />

        <EditText
            android:id="@+id/etPhone"
            android:hint="Phone Number"
            android:inputType="phone"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="24dp" />

        <Button
            android:id="@+id/btnRegister"
            android:text="Register"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />
    </LinearLayout>
</ScrollView>

✅ Done! Your Register Fragment is complete.

You can now register a new user and get redirected to the login screen automatically.

✅ Step 11: Home Screen – Retrofit + RecyclerView
🔹 1. Add Retrofit Dependencies

In your build.gradle(:app):

// Retrofit
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

🔹 2. Create Data Model for API

📁 model/Post.kt

data class Post(
val id: Int,
val title: String,
val body: String
)

🔹 3. Create Retrofit API Interface

📁 network/ApiService.kt

interface ApiService {
@GET("posts")
suspend fun getPosts(): List<Post>
}

🔹 4. Create Retrofit Client

📁 network/RetrofitClient.kt

object RetrofitClient {
private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

🔹 5. Create Home Repository

📁 repository/PostRepository.kt

class PostRepository {
suspend fun getPosts(): List<Post> {
return RetrofitClient.apiService.getPosts()
}
}

🔹 6. Create Home ViewModel

📁 ui/home/HomeViewModel.kt

class HomeViewModel(private val repository: PostRepository) : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    fun fetchPosts() {
        viewModelScope.launch {
            try {
                _posts.value = repository.getPosts()
            } catch (e: Exception) {
                _posts.value = emptyList()
            }
        }
    }
}

class HomeViewModelFactory(private val repository: PostRepository) :
ViewModelProvider.Factory {
override fun <T : ViewModel> create(modelClass: Class<T>): T {
return HomeViewModel(repository) as T
}
}

🔹 7. Create RecyclerView Adapter

📁 ui/home/PostAdapter.kt

class PostAdapter(private val posts: List<Post>) :
RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.binding.tvTitle.text = post.title
        holder.binding.tvBody.text = post.body
        holder.binding.tvId.text = "ID: ${post.id}"
    }

    override fun getItemCount(): Int = posts.size
}

🔹 8. Create Item Layout for Post

📁 res/layout/item_post.xml

<?xml version="1.0" encoding="utf-8"?>
<androidx.cardview.widget.CardView xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_margin="8dp"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:elevation="4dp"
android:padding="12dp">

    <LinearLayout
        android:orientation="vertical"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <TextView
            android:id="@+id/tvId"
            android:textStyle="bold"
            android:textSize="14sp"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"/>

        <TextView
            android:id="@+id/tvTitle"
            android:textStyle="bold"
            android:textSize="16sp"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="4dp"/>

        <TextView
            android:id="@+id/tvBody"
            android:textSize="14sp"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="2dp"/>
    </LinearLayout>
</androidx.cardview.widget.CardView>

🔹 9. Implement HomeFragment

📁 ui/home/HomeFragment.kt

class HomeFragment : Fragment() {
private var _binding: FragmentHomeBinding? = null
private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val repository = PostRepository()
        viewModel = ViewModelProvider(this, HomeViewModelFactory(repository))[HomeViewModel::class.java]

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.posts.observe(viewLifecycleOwner) { posts ->
            binding.recyclerView.adapter = PostAdapter(posts)
        }

        viewModel.fetchPosts()

        binding.ivProfile.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

🔹 10. Create Layout for HomeFragment

📁 res/layout/fragment_home.xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent">

    <ImageView
        android:id="@+id/ivProfile"
        android:layout_width="40dp"
        android:layout_height="40dp"
        android:layout_alignParentEnd="true"
        android:layout_margin="16dp"
        android:src="@drawable/ic_person"
        android:contentDescription="Profile" />

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_below="@id/ivProfile"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:padding="8dp" />
</RelativeLayout>

✅ Done! Home screen fetches and displays posts using Retrofit.

