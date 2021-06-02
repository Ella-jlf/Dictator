package android.bloodynation.ui.entities.database

import androidx.room.*

@Entity(
    tableName = "questions",
)
class Question(
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0,
    @ColumnInfo(name = "question")
    var question: String? = null,
    @Ignore
    val inflns: ArrayList<Influence> = ArrayList(),
) {

    constructor(_question:String): this(0,_question)


    fun addInfluence(influence: Influence) {
        inflns.add(influence)
    }

}