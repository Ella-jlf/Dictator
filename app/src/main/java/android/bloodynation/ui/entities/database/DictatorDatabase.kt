package android.bloodynation.ui.entities.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(Influence::class), (Question::class)], version = 1,exportSchema = false)
    abstract class DictatorDatabase : RoomDatabase() {

        abstract fun questionDao(): QuestionDao

        abstract fun influenceDao(): InfluenceDao

        abstract fun questionWithInfluencesDao(): QuestionWithInfluencesDao

        // singleton
        companion object {
            private var sInstance: DictatorDatabase? = null

            @Synchronized
            fun getInstance(context: Context): DictatorDatabase {
                if (sInstance == null) {
                    sInstance = Room
                        .databaseBuilder(context.applicationContext, DictatorDatabase::class.java, "db_dictator")
                        .build()
                }
                return sInstance!!
            }
        }

    }
