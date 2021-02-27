package com.example.rentagown.v2.data.repository

import com.example.rentagown.v2.data.remote.RemoteRepositoryLocator

class RepositoryLocator(remoteLocator: RemoteRepositoryLocator) : IRepositoryLocator {

    companion object {
        @Volatile private var INSTANCE: RepositoryLocator? = null

        fun getInstance(remoteLocator: RemoteRepositoryLocator): RepositoryLocator {
            if(INSTANCE == null) {
                INSTANCE = RepositoryLocator(remoteLocator)
            }
            return INSTANCE!!
        }

    }

    override val addressRepository: AddressRepository by lazy {
        AddressRepository(remoteLocator.addressRemoteRepository)
    }

    override val bankRepository: BankRepository by lazy {
        BankRepository(remoteLocator.bankRemoteRepository)
    }

    override val productRepository: ProductRepository by lazy {
        ProductRepository(remoteLocator.productRemoteRepository)
    }

    override val bookingRepository: BookingRepository by lazy {
        BookingRepository(remoteLocator.bookingRemoteRepository)
    }

}