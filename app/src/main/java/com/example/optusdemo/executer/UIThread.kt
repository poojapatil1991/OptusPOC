package com.example.optusdemo.executer

import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers

/*
* Creates the UI thread
 */

class UIThread : IExecuterThread {
    override fun getScheduler(): Scheduler? {
        return AndroidSchedulers.mainThread()
    }
}