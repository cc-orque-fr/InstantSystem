package fr.cc.instantsystem

import android.app.Application
import fr.cc.instantsystem.di.networkModule
import fr.cc.instantsystem.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
        }
        loadKoinModules(listOf(viewModelModule, networkModule))
    }
}