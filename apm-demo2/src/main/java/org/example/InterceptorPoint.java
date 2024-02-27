package org.example;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

/**
 * @author 王鹏
 */
public interface InterceptorPoint {

    ElementMatcher<TypeDescription> buildTypeMatcher();

    ElementMatcher<MethodDescription> buildMethodMatcher();

    Class<?> adviceClass();
}
