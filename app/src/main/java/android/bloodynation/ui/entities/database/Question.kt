package android.bloodynation.ui.entities.database

import androidx.room.*

@Entity(
    tableName = "questions",
    foreignKeys = [ForeignKey(
        entity = Influence::class,
        parentColumns = arrayOf("uid"),
        childColumns = arrayOf("influence_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
class Question(
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0,
    @ColumnInfo(name = "question")
    var question: String,
    @Ignore
    val inflns: ArrayList<Influence> = ArrayList(),
    @ColumnInfo(name = "influences_id")
    val influence_id: ArrayList<Int> = ArrayList()
) {

    constructor(_question:String): this(0,_question)

    fun addInfluence(influence: Influence) {
        inflns.add(influence)
    }

}