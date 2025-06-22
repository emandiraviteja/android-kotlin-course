@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true) val txId: Int = 0,
    val date: String,
    val desc: String,
    val amount: String
)