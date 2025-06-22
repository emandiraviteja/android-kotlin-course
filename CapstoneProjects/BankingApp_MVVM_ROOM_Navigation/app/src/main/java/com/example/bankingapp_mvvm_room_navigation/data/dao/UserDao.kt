@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM User WHERE id = 1")
    fun getUser(): LiveData<User?>

    @Query("UPDATE User SET isLoggedIn = :loggedIn WHERE id = 1")
    suspend fun setLoginStatus(loggedIn: Boolean)
}