package ca.tetervak.dicegame.data.module

import ca.tetervak.dicegame.data.RollerService
import ca.tetervak.dicegame.data.impl.RollerServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RollerServiceModule {

    @Binds
    abstract fun bindRollerService(
        rollerServiceImpl: RollerServiceImpl
    ): RollerService
}