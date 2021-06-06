package android.bloodynation.ui.entities.database

import androidx.room.*


@Entity(tableName = "influences")
data class Influence(
    @PrimaryKey(autoGenerate = true)
    val influenceId:Int = 0,
    @ColumnInfo(name ="fractionName")
    val fractionName: String,
    @ColumnInfo(name = "yes")
    val yes: Int,
    @ColumnInfo(name = "no")
    val no :Int,
    @ColumnInfo(name = "questionId")
    var questionId: Int = 0){
    constructor(_fractionName:String,
                _yes: Int,
                _no: Int
    ) : this(0,_fractionName,_yes,_no)

    fun setParentQuestionId(id: Long){
        questionId = id.toInt()
    }
}
