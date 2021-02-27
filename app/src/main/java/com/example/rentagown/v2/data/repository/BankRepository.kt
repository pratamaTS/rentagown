package com.example.rentagown.v2.data.repository

import com.example.rentagown.v2.data.model.Bank
import com.example.rentagown.v2.data.model.BasePagingResp
import com.example.rentagown.v2.data.source.BankDataSource
import io.reactivex.rxjava3.core.Single

class BankRepository(private val remoteRepository: BankDataSource) : BankDataSource {

    override fun getBanks(): Single<BasePagingResp<Bank>> {
        return remoteRepository.getBanks()
    }

}