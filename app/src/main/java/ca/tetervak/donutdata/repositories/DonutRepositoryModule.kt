package ca.tetervak.donutdata.repositories

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DonutRepositoryModule {
    @Binds
    abstract fun bindEnvelopeRepository(repository: DonutRepositoryFirestore): DonutRepository
}