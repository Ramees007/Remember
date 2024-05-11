package com.rms.feature.menu.presentation.ui.di

import com.rms.feature.menu.domain.GetMenuItemsUseCase
import com.rms.feature.menu.domain.GetMenuItemsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MenuListModule {

    @Binds
    fun bindGetMenuItemsUseCase(impl: GetMenuItemsUseCaseImpl): GetMenuItemsUseCase
}