package com.example.android.dessertclicker

import android.app.Application
import timber.log.Timber

/**
 * Created by Syekh Syihabuddin Azmil Umri on 23/11/2020.
 */
class ClickerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}