package com.tresor.base.view

import io.reactivex.observers.DisposableObserver

/**
 * @author sebastianuskh on 5/22/17.
 */
abstract class BaseObserver<T>: DisposableObserver<T>() {
    override fun onComplete() {

    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(e: Throwable?) {
        onFailed(e)
    }

    abstract fun onFailed(e: Throwable?)

    abstract fun onSuccess(t: T)
}