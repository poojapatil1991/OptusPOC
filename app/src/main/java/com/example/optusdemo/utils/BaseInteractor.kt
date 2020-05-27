package com.example.optusdemo.utils

import rx.Subscriber
/*
* Base Interactor extended in Usecase
 */
interface BaseInteractor<T> {
    fun execute(subscriber: Subscriber<T>)
}