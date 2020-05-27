package com.example.optusdemo.executer

import rx.Scheduler

/*
* Interface to create thread.
 */

interface IExecuterThread {
    fun getScheduler(): Scheduler?
}