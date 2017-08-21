package micro.service.dubbox.demo.service;

import java.util.List;

public interface IpWhiteList {

    boolean isEnabled();

    List<String> getAllowedIps();
}
