package ca.tetervak.dicegame.data.remote

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private const val baseUrl = "http://10.0.2.2:8080/api/"

    @Provides
    @Singleton
    fun retrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun rollDataApi(retrofit: Retrofit): RollDataApi =
        retrofit.create(RollDataApi::class.java)

}