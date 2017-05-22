package com.tresor.base.domain

import com.tresor.base.domain.PostExecutionThread
import com.tresor.base.domain.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * @author sebastianuskh on 5/22/17.
 */

abstract class UseCase<P, T>
protected constructor(private val threadExecutor: ThreadExecutor, private val postExecutionThread: PostExecutionThread) {

    private var subscription = CompositeDisposable()

    protected abstract fun getObservable(param: P): Observable<T>

    fun execute(param: P, subscriber: DisposableObserver<T>) {
        subscription.add(
                getObservable(param)
                        .subscribeOn(Schedulers.from(threadExecutor))
                        .unsubscribeOn(Schedulers.from(threadExecutor))
                        .observeOn(postExecutionThread.scheduler)
                        .subscribeWith(subscriber)
        )
    }

    fun dispose() {
        if (!subscription.isDisposed) {
            subscription.dispose()
        }
    }

}