package ca.tetervak.donutdata.repositories

import ca.tetervak.donutdata.database.DonutDao
import ca.tetervak.donutdata.database.DonutEntity
import ca.tetervak.donutdata.domain.Donut
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DonutRepositoryRoom @Inject constructor(private val donutDao: DonutDao)
    : DonutRepository {

    override fun getAll(): Flow<List<Donut>> {
        return donutDao.getAll().map{ list -> list.map { it.asDonut() }}
    }

    override suspend fun get(id: String): Donut {
        return donutDao.get(id.toLong()).asDonut()
    }

    override suspend fun insert(donut: Donut): Long {
        return donutDao.insert(donut.asEntity())
    }

    override suspend fun delete(donut: Donut) {
        if (donut.id != null) {
            donutDao.delete(donut.id.toLong())
        }
    }

    override suspend fun update(donut: Donut) {
        donutDao.update(donut.asEntity())
    }

    override suspend fun deleteAll() {
        donutDao.deleteAll()
    }
}

fun DonutEntity.asDonut(): Donut {
    return Donut(id.toString(), name, description, rating, lowFat)
}

fun Donut.asEntity(): DonutEntity {
    return DonutEntity(id?.toLong() ?: 0L, name, description, rating, lowFat)
}