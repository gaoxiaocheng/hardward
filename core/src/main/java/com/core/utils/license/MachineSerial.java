package com.core.utils.license;

public interface MachineSerial {

    static String getCode(){
        MachineSerial o;
        if(getOSName().contains("windows")){
            o = new WinMachineSerial();
        }else
            o = new LinuxMachineSerial();
        return s_code(o);
    }
    static String getOSName() {
        return System.getProperty("os.name").toLowerCase();
    }

    String getMainBordId();

    String getMAC();

    String getCPUID();

    static String s_code(MachineSerial o) {
        return o.getMainBordId()+"@"+o.getMAC()+"#"+o.getCPUID();
    }
}
