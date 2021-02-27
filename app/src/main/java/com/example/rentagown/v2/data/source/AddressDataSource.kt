package com.example.rentagown.v2.data.source

import com.example.rentagown.v2.data.model.Address
import com.example.rentagown.v2.data.model.BasePagingResp
import com.example.rentagown.v2.data.model.BaseResp
import io.reactivex.rxjava3.core.Single

interface AddressDataSource : BaseDataSource {

    fun getAddressList(): Single<BasePagingResp<Address>>
    fun addAddress(address: Address): Single<BaseResp<Address>>
    fun deleteAddress(addressId: String): Single<BaseResp<Address>>
    fun updateAddress(addressId: String, address: Address): Single<BaseResp<Address>>

}