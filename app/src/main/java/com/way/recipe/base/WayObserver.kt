package com.way.recipe.base

import cn.leancloud.AVException
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by Otway on 2021/4/6.
 */
abstract class WayObserver<T> : Observer<T> {
    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
    }

    final override fun onNext(data: T) {
        onSuccess(data)
    }

    final override fun onError(e: Throwable) {
        onFailure(AVException(e))
    }

    abstract fun onSuccess(data: T)

    abstract fun onFailure(e: AVException)
}