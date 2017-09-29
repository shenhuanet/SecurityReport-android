package com.shenhua.outer.security.report.core;

import com.squareup.otto.Bus;

/**
 * Created by shenhua on 2017-09-29-0029.
 * Email shenhuanet@126.com
 */
public class BusProvider {
    private static Bus sBus = new Bus();

    public static Bus get() {
        return sBus;
    }
}
