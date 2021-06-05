package android.bloodynation.ui.entities.database

import androidx.room.*

@Entity(
    tableName = "questions",
)
class Question(
    @PrimaryKey(autoGenerate = true)
    var questionId: Int = 0,
    @ColumnInfo(name = "question")
    var question: String? = null
) {
    constructor(_question: String) : this(0, _question)
}


class QuestionsWithInfluences(
    @Embedded val questionInstance: Question,
    @Relation(
        parentColumn = "questionId",
        entity = Influence::class,
        entityColumn = "influenceId",
        associateBy = Junction(
            QuestionsInfluencesCrossRef::class,
            parentColumn = "questionId",
            entityColumn = "influenceId"
        )
    )
    val inflns: ArrayList<Influence>
) {

    constructor(questionInstance: Question) : this(questionInstance, ArrayList<Influence>())

    fun registerInfluence(vararg influence: Influence) {
        for (i in influence) {
            inflns.add(i)
        }
    }
}