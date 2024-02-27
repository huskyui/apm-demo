package org.example;

import org.example.plugin.custom.CustomerPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王鹏
 */
public class PluginGroup {

    public static List<InterceptorPoint> POINT_LIST = new ArrayList<>();

    static{
        POINT_LIST.add(new CustomerPlugin());
    }



}
