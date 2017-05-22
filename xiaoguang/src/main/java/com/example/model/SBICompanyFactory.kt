package com.example.model

import com.example.feature.ICheckStand
import com.example.feature.ICompanyFactory
import com.example.feature.IStore
import com.example.feature.ITableWare

/**
 * Created by catherine on 17/5/18.
 */
class SBICompanyFactory : ICompanyFactory {
    override fun createStore(): IStore {
        return SbiStore(); }

    override fun createCheckStand(): ICheckStand {
        return SbiCheckStand(); }

    override fun createTableWare(): ITableWare {
        return SbiTableWare(); }

}