package android.bloodynation.ui.entities.database

import androidx.room.*


@Entity(tableName = "influences")
data class Influence(
    @PrimaryKey(autoGenerate = true)
    val uid:Int = 0,
    @ColumnInfo(name ="fractionName")
    val fractionName: String,
    @ColumnInfo(name = "yes")
    val yes: Int,
    @ColumnInfo(name = "no")
    val no :Int){
    constructor(_fractionName:String,
                _yes: Int,
                _no: Int
    ) : this(0,_fractionName,_yes,_no)
}
