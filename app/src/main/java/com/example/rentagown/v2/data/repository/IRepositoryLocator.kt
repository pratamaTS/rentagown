package com.example.rentagown.v2.data.repository

import com.example.rentagown.v2.data.remote.AddressRemoteRepository

interface IRepositoryLocator {

    val addressRepository: AddressRepository
    val bankRepository: BankRepository
    val productRepository: ProductRepository
    val bookingRepository: BookingRepository

}