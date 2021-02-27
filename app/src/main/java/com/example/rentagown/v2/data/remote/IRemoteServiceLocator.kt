package com.example.rentagown.v2.data.remote

interface IRemoteServiceLocator {

    val addressRemoteRepository: AddressRemoteRepository
    val bankRemoteRepository: BankRemoteRepository
    val productRemoteRepository: ProductRemoteRepository
    val bookingRemoteRepository: BookingRemoteRepository

}