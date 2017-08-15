package micro.service.dubbox.demo.service.impl;

import micro.service.dubbox.demo.service.IpWhiteList;

import java.util.Arrays;
import java.util.List;


public class IpWhiteListImpl implements IpWhiteList {
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public List<String> getAllowedIps() {
        return Arrays.asList( "127.0.0.1","192.168.100.225");
    }
}
