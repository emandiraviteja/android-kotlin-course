class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao = AppDatabase.getInstance(application).userDao()
    private val repository = UserRepository(userDao)

    val user: LiveData<User?> = repository.user

    fun saveUser(user: User) {
        viewModelScope.launch {
            repository.saveUser(user)
        }
    }

    fun setLoggedIn(status: Boolean) {
        viewModelScope.launch {
            repository.setLoginStatus(status)
        }
    }
}