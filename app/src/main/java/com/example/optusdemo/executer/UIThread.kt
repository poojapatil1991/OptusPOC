package com.example.optusdemo.executer

import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers

class UIThread : IExecuterThread {
    override fun getScheduler(): Scheduler? {
        return AndroidSchedulers.mainThread()
    }
}