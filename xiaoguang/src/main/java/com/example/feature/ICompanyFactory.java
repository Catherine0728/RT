package com.example.feature;

/**
 * Created by catherine on 17/5/18.
 */

public interface ICompanyFactory {
    IStore createStore();

    ICheckStand createCheckStand();

    ITableWare createTableWare();
}
