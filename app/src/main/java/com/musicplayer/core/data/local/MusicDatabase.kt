package com.musicplayer.core.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

// --- Entity ---
@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val songId: Long,
    val addedAt: Long = System.currentTimeMillis()
)

// --- DAO ---
@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorites ORDER BY addedAt DESC")
    fun getAllFavorites(): Flow<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(entity: FavoriteEntity)

    @Query("DELETE FROM favorites WHERE songId = :songId")
    suspend fun removeFavorite(songId: Long)

    @Query("SELECT COUNT(*) > 0 FROM favorites WHERE songId = :songId")
    suspend fun isFavorite(songId: Long): Boolean
}

// --- Database ---
@Database(
    entities = [FavoriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MusicDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}
