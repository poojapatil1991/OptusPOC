package com.example.optusdemo.utils

import rx.Subscriber

interface BaseInteractor <T> {
    fun execute(subscriber: Subscriber<T>)
}