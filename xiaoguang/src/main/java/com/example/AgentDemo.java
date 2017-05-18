package com.example;

import com.example.model.AgentXiaoGuang;
import com.example.model.DaLong;

/**
 * Created by catherine on 17/5/18.
 */

public class AgentDemo {

    public static void main(String[] args) {

        DaLong daLong = new DaLong(new AgentXiaoGuang());

        daLong.signing(120);

        daLong.signing(100);

        daLong.signing(80);

    }
}
