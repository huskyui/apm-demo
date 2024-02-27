package org.example;

import org.example.plugin.custom.CustomerPlugin;
import org.example.plugin.tomcat.TomcatPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王鹏
 */
public class PluginGroup {

    public static List<InterceptorPoint> POINT_LIST = new ArrayList<>();

    static{
        POINT_LIST.add(new CustomerPlugin());
        POINT_LIST.add(new TomcatPlugin());
    }



}
