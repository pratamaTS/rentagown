package com.example.rentagown.v2.data.remote

import com.example.rentagown.v2.data.model.Address
import com.example.rentagown.v2.data.model.BasePagingResp
import com.example.rentagown.v2.data.model.BaseResp
import com.example.rentagown.v2.data.model.ReqDeleteAddress
import com.example.rentagown.v2.data.network.RAGApiService
import com.example.rentagown.v2.data.source.AddressDataSource
import io.reactivex.rxjava3.core.Single

class AddressRemoteRepository(private val apiService: RAGApiService): AddressDataSource {

    override fun getAddressList(): Single<BasePagingResp<Address>> {
        return apiService.getUserAddresses()
    }

    override fun addAddress(address: Address): Single<BaseResp<Address>> {
        return apiService.addUserAddress(address)
    }

    override fun deleteAddress(addressId: String) : Single<BaseResp<Address>>{
        return apiService.deleteUserAddress(addressId, ReqDeleteAddress(addressId))
    }

    override fun updateAddress(addressId: String, address: Address) : Single<BaseResp<Address>>{
        return apiService.updateUserAddress(addressId, address)
    }

}