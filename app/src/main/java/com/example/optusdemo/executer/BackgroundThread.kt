package com.example.optusdemo.executer

import rx.Scheduler
import rx.schedulers.Schedulers

/*
* Creates the Background thread
 */

class BackgroundThread : IExecuterThread {
    override fun getScheduler(): Scheduler? {
        return Schedulers.newThread()
    }
}