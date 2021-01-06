package org.hzl.aknife.time.pojo;

import java.util.Date;

public class WINTime {

    private final long value;

    public WINTime(){
        this(System.currentTimeMillis() / 1000L + 2208988800L);
    }

    public WINTime(long value){
        this.value = value;
    }

    public long value(){
        return value;
    }

    @Override
    public String toString() {
        return new Date((value() - 2208988800L) * 1000L).toString();
    }
}
