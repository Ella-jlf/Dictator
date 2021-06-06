package android.bloodynation.ui.entities.database

import androidx.room.Embedded
import androidx.room.Relation

class QuestionWithInfluences(
    @Embedded val questionInstance: Question,
    @Relation(
        parentColumn = "questionId",
        entity = Influence::class,
        entityColumn = "questionId",
    )
    var inflns: List<Influence>
) {

    constructor(questionInstance: Question) : this(questionInstance, ArrayList<Influence>())

    fun registerInfluence(vararg influence: Influence) {
        val temp = inflns as ArrayList
        for (i in influence) {
            temp.add(i)
        }
        inflns = temp
    }
}