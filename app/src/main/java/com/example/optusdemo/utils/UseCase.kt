package com.example.optusdemo.utils

import com.example.optusdemo.executer.IExecuterThread
import com.example.optusdemo.executer.UIThread
import rx.Observable
import rx.Subscriber
import rx.Subscription
import rx.subscriptions.Subscriptions

/*
* Base usecase class.
 */
abstract class UseCase<T>(executorThreadI: IExecuterThread, postExecutionThread: UIThread) :
    BaseInteractor<T> {
    private var executorThreadI: IExecuterThread? = null
    private var postExecutionThread: UIThread? = null
    private var subscription: Subscription? = Subscriptions.empty()

    init {
        this.executorThreadI = executorThreadI
        this.postExecutionThread = postExecutionThread
    }

    /**
     * Builds an {@link Observable} which will be used when executing the current {@link UseCase}.
     */
    abstract fun createObservable(): Observable<T>

    /*
    API call
    subscribe on executorThread(background) thread
    observes on postExecutionThread (main) thread
     */
    override fun execute(subscriber: Subscriber<T>) {
        this.subscription = createObservable()
            .subscribeOn(executorThreadI!!.getScheduler())
            .observeOn(postExecutionThread!!.getScheduler())
            .subscribe(subscriber)
    }

    /**
     * Unsubscribes from current {@link Subscription}.
     */
    fun unsubscribe() {
        if (!subscription!!.isUnsubscribed) {
            subscription!!.unsubscribe()
        }
    }
}
