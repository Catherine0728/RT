package com.example;

import com.example.model.Company;
import com.example.model.OpticalValleyCompany;

/**
 * Created by catherine on 17/5/18.
 * <p>
 * 抽象工厂
 */

public class XiaoGuangCompany {

    public static void main(String[] args) {
        Company companyA = new OpticalValleyCompany();
        System.out.println(companyA);

        Company companyB = companyA.clone();
        companyB.setName("猴店");
        companyB.setDrinks("雪丽球");
        System.out.println(companyB);
        System.out.println(companyA);
    }
}
