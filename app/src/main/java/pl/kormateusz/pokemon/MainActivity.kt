package pl.kormateusz.pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import pl.kormateusz.pokemon.modules.appModule
import pl.kormateusz.pokemon.modules.networkModule
import pl.kormateusz.pokemon.modules.repositoriesModule
import pl.kormateusz.pokemon.modules.useCaseModule
import pl.kormateusz.pokemon.modules.viewModelModule
import pl.kormateusz.pokemon.ui.MainApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(this@MainActivity)
            modules(
                viewModelModule,
                useCaseModule,
                repositoriesModule,
                networkModule,
                appModule,
            )
        }

        setContent {
            MainApp()
        }
    }
}