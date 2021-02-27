package com.example.rentagown.v2.data.remote

import com.example.rentagown.v2.data.model.Bank
import com.example.rentagown.v2.data.model.BasePagingResp
import com.example.rentagown.v2.data.network.RAGApiService
import com.example.rentagown.v2.data.source.BankDataSource
import io.reactivex.rxjava3.core.Single

class BankRemoteRepository(private val apiService: RAGApiService) : BankDataSource {

    override fun getBanks(): Single<BasePagingResp<Bank>> {
        return apiService.getBanks()
    }

}