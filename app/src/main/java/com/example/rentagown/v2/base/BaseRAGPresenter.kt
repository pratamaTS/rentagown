    package com.example.rentagown.v2.base

import android.util.Log
import com.example.rentagown.Model.ResponseError
import com.example.rentagown.v2.data.model.Address
import com.example.rentagown.v2.data.model.BasePagingResp
import com.example.rentagown.v2.data.model.BaseResp
import com.example.rentagown.v2.data.model.DateResponse
import com.example.rentagown.v2.util.ErrorUtils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import java.net.ConnectException
import java.util.concurrent.TimeUnit

@Suppress("UNCHECKED_CAST")
abstract class BaseRAGPresenter<V: BaseView<*>> : BasePresenter {

    protected var view: V? = null
    protected var cd: CompositeDisposable? = null

    override fun start() { }

    override fun attachView(mView: Any?) {
        if(mView != null) {
            this.view = mView as V
        }
        if(cd == null) cd = CompositeDisposable()
    }

    override fun detachView() {
        this.view = null
    }

    override fun cleanUp() {
        cd?.dispose()
    }

    override fun onBackPressed() {
        view?.pressBack()
    }

    protected inline fun <reified D> safeCall(obs: Single<BaseResp<D>>, listener: Listener<D>, requestConfiguration: RequestConfiguration = RequestConfiguration()) {
        Completable.timer(1000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                cd?.add(obs
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<BaseResp<D>>() {
                        override fun onError(e: Throwable?) {
                            if(requestConfiguration.updateLoadingContentIndicator) view?.showLoadingContent(false)
                            if(requestConfiguration.updateLoadingIndicator) view?.showLoading(false)
                            if(requestConfiguration.showErrorMessage) view?.showErrorMessage(if(e is ConnectException) "tidak dapat terhubung ke server" else "error")

                            listener.onFailed(e?.message)
                        }

                        override fun onSuccess(t: BaseResp<D>?) {
                            if(requestConfiguration.updateLoadingContentIndicator) view?.showLoadingContent(false)
                            if(requestConfiguration.updateLoadingIndicator) view?.showLoading(false)

                            listener.onSuccess(t?.data)
                        }
                    }))
            }
    }

    protected inline fun <reified D> safeCallPayment(obs: Single<BaseResp<D>>, listener: Listener<D>, requestConfiguration: RequestConfiguration = RequestConfiguration()) {
        Completable.timer(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    cd?.add(obs
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(object : DisposableSingleObserver<BaseResp<D>>() {
                                override fun onError(e: Throwable?) {
                                    if(requestConfiguration.updateLoadingContentIndicator) view?.showLoadingContent(false)
                                    if(requestConfiguration.updateLoadingIndicator) view?.showLoading(false)
                                    if(requestConfiguration.showErrorMessage) view?.showErrorMessage(if(e is ConnectException) "tidak dapat terhubung ke server" else "your payment is different with the invoice amount")

                                    listener.onFailed(e?.message)
                                }

                                override fun onSuccess(t: BaseResp<D>?) {
                                    if(requestConfiguration.updateLoadingContentIndicator) view?.showLoadingContent(false)
                                    if(requestConfiguration.updateLoadingIndicator) view?.showLoading(false)

                                    listener.onSuccess(t?.data)
                                }
                            }))
                }
    }

    protected inline fun safeCallDate(obs: Single<DateResponse>, listener: ListenerDate, requestConfiguration: RequestConfiguration = RequestConfiguration()) {
        Completable.timer(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    cd?.add(obs
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(object : DisposableSingleObserver<DateResponse>() {

                                override fun onError(e: Throwable?) {
                                    if(requestConfiguration.updateLoadingContentIndicator) view?.showLoadingContent(false)
                                    if(requestConfiguration.updateLoadingIndicator) view?.showLoading(false)
                                    if(requestConfiguration.showErrorMessage) view?.showErrorMessage(if(e is ConnectException) "tidak dapat terhubung ke server" else "your booking is more than 7 days")

                                    listener.onFailed(e?.message)
                                }

                                override fun onSuccess(t: DateResponse?) {
                                    if(requestConfiguration.updateLoadingContentIndicator) view?.showLoadingContent(false)
                                    if(requestConfiguration.updateLoadingIndicator) view?.showLoading(false)

                                    listener.onSuccess(t?.error)
                                }
                            }))
                }
    }

    protected inline fun safeCallDefaultAddress(obs: Single<Address>, listener: ListenerAddress, requestConfiguration: RequestConfiguration = RequestConfiguration()) {
        Completable.timer(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    cd?.add(obs
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(object : DisposableSingleObserver<Address>() {

                                override fun onError(e: Throwable?) {
                                    if(requestConfiguration.updateLoadingContentIndicator) view?.showLoadingContent(false)
                                    if(requestConfiguration.updateLoadingIndicator) view?.showLoading(false)
                                    if(requestConfiguration.showErrorMessage) view?.showErrorMessage(if(e is ConnectException) "tidak dapat terhubung ke server" else "error")

                                    listener.onFailed(e?.message)
                                }

                                override fun onSuccess(t: Address?) {
                                    if(requestConfiguration.updateLoadingContentIndicator) view?.showLoadingContent(false)
                                    if(requestConfiguration.updateLoadingIndicator) view?.showLoading(false)

                                    Log.d("address", t.toString())
                                    listener.onSuccess(t)
                                }
                            }))
                }
    }

    protected inline fun <reified D> safeCallPaging(obs: Single<BasePagingResp<D>>, listener: Listener<List<D>>, requestConfiguration: RequestConfiguration = RequestConfiguration()) {
        Completable.timer(1000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                cd?.add(obs
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<BasePagingResp<D>>() {
                        override fun onError(e: Throwable?) {
                            if(requestConfiguration.updateLoadingContentIndicator) view?.showLoadingContent(false)
                            if(requestConfiguration.updateLoadingIndicator) view?.showLoading(false)
                            if(requestConfiguration.showErrorMessage) view?.showErrorMessage(if(e is ConnectException) "tidak dapat terhubung ke server" else "error")

                            listener.onFailed(e?.message)
                        }

                        override fun onSuccess(t: BasePagingResp<D>?) {
                            if(requestConfiguration.updateLoadingContentIndicator) view?.showLoadingContent(false)
                            if(requestConfiguration.updateLoadingIndicator) view?.showLoading(false)

                            listener.onSuccess(t?.data)
                        }
                    })
                )
            }

    }

    interface Listener<T> {

        fun onSuccess(data: T?)
        fun onFailed(message: String?) { }

    }

    interface ListenerDate {

        fun onSuccess(message: String?)
        fun onFailed(message: String?) { }

    }

    interface ListenerAddress {

        fun onSuccess(address: Address?)
        fun onFailed(message: String?) { }

    }

    data class RequestConfiguration (
        val showErrorMessage: Boolean = true,
        val updateLoadingIndicator: Boolean = true,
        val updateLoadingContentIndicator: Boolean = true
    )

}