package com.example.optusdemo.module

import com.example.optusdemo.executer.BackgroundThread
import com.example.optusdemo.executer.IExecuterThread
import com.example.optusdemo.executer.UIThread

class ThreadModule {
    //Function returns the background thread
    fun provideExecutorThread(): IExecuterThread {
        return BackgroundThread()
    }

    // Function returns the UI thread
    fun providePostExecutionThread(): UIThread {
        return UIThread()
    }
}