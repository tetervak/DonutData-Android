package ca.tetervak.donutdata.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object used to retrieve and store data from/to the underlying database.
 * This API is not used directly; instead, callers should use the Repository which calls into
 * this DAO.
 */
@Dao
interface DonutDao {
    @Query("SELECT * FROM donuts")
    fun getAll(): Flow<List<DonutEntity>>

    @Query("SELECT * FROM donuts WHERE id = :id")
    suspend fun get(id: Long): DonutEntity

    @Insert
    suspend fun insert(donut: DonutEntity): Long

    @Delete
    suspend fun delete(donut: DonutEntity)

    @Query("DELETE FROM donuts WHERE id=:id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM donuts")
    suspend fun deleteAll()

    @Update
    suspend fun update(donut: DonutEntity)
}
