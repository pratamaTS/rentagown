package com.example.rentagown.v2.data.repository

import com.example.rentagown.v2.data.model.Address
import com.example.rentagown.v2.data.model.BasePagingResp
import com.example.rentagown.v2.data.model.BaseResp
import com.example.rentagown.v2.data.source.AddressDataSource
import io.reactivex.rxjava3.core.Single

class AddressRepository(private val remoteRepository: AddressDataSource) : AddressDataSource {

    override fun getAddressList(): Single<BasePagingResp<Address>> {
        return remoteRepository.getAddressList()
    }

    override fun addAddress(address: Address): Single<BaseResp<Address>> {
        return remoteRepository.addAddress(address)
    }

    override fun deleteAddress(addressId: String) : Single<BaseResp<Address>>{
        return remoteRepository.deleteAddress(addressId)
    }

    override fun updateAddress(addressId: String, address: Address) : Single<BaseResp<Address>>{
        return remoteRepository.updateAddress(addressId, address)
    }

}