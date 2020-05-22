package com.example.optusdemo.executer

import rx.Scheduler

interface IExecuterThread {
    fun getScheduler(): Scheduler?
}