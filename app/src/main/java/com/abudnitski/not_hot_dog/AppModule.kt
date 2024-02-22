package com.abudnitski.not_hot_dog

import com.abudnitski.not_hot_dog.domain.LabelMapper
import com.abudnitski.not_hot_dog.presentation.LabelUiMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideLabelsMapper(): LabelMapper {
        return LabelMapper()
    }

    @Provides
    fun provideLabelUiMapper(): LabelUiMapper{
        return LabelUiMapper()
    }
}