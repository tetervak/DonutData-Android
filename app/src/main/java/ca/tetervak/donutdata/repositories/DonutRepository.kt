package ca.tetervak.donutdata.repositories

import ca.tetervak.donutdata.domain.Donut
import kotlinx.coroutines.flow.Flow

interface DonutRepository {
    fun getAll(): Flow<List<Donut>>
    suspend fun get(id: String): Donut?
    suspend fun insert(donut: Donut): String
    suspend fun delete(donut: Donut)
    suspend fun update(donut: Donut)
    //suspend fun deleteAll()
}