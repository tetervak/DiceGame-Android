package ca.tetervak.dicegame.data.module

import ca.tetervak.dicegame.data.impl.PreferenceRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceRepositoryModule {

    @Binds
    abstract fun bindPreferenceRepository(
        repository: PreferenceRepositoryImpl
    ): PreferenceRepository

}