package com.example.rentagown.v2.data.source

import com.example.rentagown.v2.data.model.Bank
import com.example.rentagown.v2.data.model.BasePagingResp
import io.reactivex.rxjava3.core.Single

interface BankDataSource {

    fun getBanks(): Single<BasePagingResp<Bank>>

}