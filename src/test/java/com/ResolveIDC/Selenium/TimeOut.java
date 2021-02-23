package com.ResolveIDC.Selenium;

import com.ResolveIDC.Utils.Utils;

public interface TimeOut{
    int VERYSHORTWAIT = Utils.getWaitTime("VERYSHORTWAIT");
    int SHORTWAIT = Utils.getWaitTime("SHORTWAIT");
    int MEDIUMWAIT = Utils.getWaitTime("MEDIUMWAIT");
    int LONGWAIT = Utils.getWaitTime("LONGWAIT");
    int VERYLONGWAIT = Utils.getWaitTime("VERYLONGWAIT");
    int IMPLICITWAIT = Utils.getWaitTime("IMPLICITWAIT");
}
