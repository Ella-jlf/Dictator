package android.bloodynation.ui.entities.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface InfluenceDao {
    @Query("SELECT * FROM influences")
    fun getAll(): List<Influence>

    @Query("SELECT * FROM influences WHERE influenceId IN (:influenceIds)")
    fun loadAllByIds(influenceIds: IntArray): List<Influence>

    @Query("DELETE FROM influences")
    fun deleteAll()

    @Insert
    fun insertAll(vararg influence: Influence)

    @Delete
    fun delete(influence: Influence)
}