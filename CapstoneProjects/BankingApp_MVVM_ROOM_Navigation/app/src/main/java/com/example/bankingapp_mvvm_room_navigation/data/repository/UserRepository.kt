class UserRepository(private val dao: UserDao) {
    val user: LiveData<User?> = dao.getUser()

    suspend fun saveUser(user: User) {
        dao.insertUser(user)
    }

    suspend fun setLoginStatus(status: Boolean) {
        dao.setLoginStatus(status)
    }
}