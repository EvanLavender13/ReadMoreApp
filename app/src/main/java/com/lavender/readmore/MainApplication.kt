package com.lavender.readmore

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Main application class. Uses Hilt to inject dependencies through the application.
 *
 * @see HiltAndroidApp
 */
@HiltAndroidApp
class MainApplication : Application()