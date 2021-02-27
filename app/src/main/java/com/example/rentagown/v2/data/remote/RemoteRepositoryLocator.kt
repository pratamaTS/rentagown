package com.example.rentagown.v2.data.remote

import com.example.rentagown.v2.data.network.RAGApiService

class RemoteRepositoryLocator(service: RAGApiService) : IRemoteServiceLocator {

    companion object {
        @Volatile private var INSTANCE: RemoteRepositoryLocator? = null

        fun getInstance(service: RAGApiService): RemoteRepositoryLocator {
            if(INSTANCE == null) {
                INSTANCE = RemoteRepositoryLocator(service)
            }
            return INSTANCE!!
        }

    }

    override val addressRemoteRepository: AddressRemoteRepository by lazy {
        AddressRemoteRepository(service)
    }

    override val bankRemoteRepository: BankRemoteRepository by lazy {
        BankRemoteRepository(service)
    }

    override val productRemoteRepository: ProductRemoteRepository by lazy {
        ProductRemoteRepository(service)
    }

    override val bookingRemoteRepository: BookingRemoteRepository by lazy {
        BookingRemoteRepository(service)
    }

}