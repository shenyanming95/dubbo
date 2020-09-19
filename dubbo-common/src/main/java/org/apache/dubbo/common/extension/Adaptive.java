/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.common.extension;

import org.apache.dubbo.common.URL;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 提供有用的信息以供{@link ExtensionLoader}注入依赖项扩展实例
 *
 * @see ExtensionLoader
 * @see URL
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Adaptive {
    /**
     * 确定要注入的扩展类实例, 目标扩展名由{@link URL}中传递的参数决定, 参数名称由此方法来决定.
     * 如果从{@link URL}中找不到指定的参数, 则使用其扩展类接口中的{@link SPI}中指定.
     * <p>
     * 比如说, 此方法设置参数为: <code>String[] {"key1", "key2"}</code>:
     * 1.从{@link URL}找参数名为"key1"的参数, 用它的参数值作为扩展类的名称;
     * 2.如果找不到"key1"的参数, 转向找"key2";
     * 3.如果"key2"也找不到, 使用扩展类{@link SPI}中指定的默认扩展名称;
     * 4.若{@link SPI}也没有指定, 那么使用生成出来的代理调用方法时就会抛出异常{@link IllegalStateException}.
     * <p>
     * 如果此方法参数名称为空(即为空数组), 则会从接口的类名称生成默认参数名称, 做法为：
     * 将类全名按照大写字符分隔, 再用"."合并为一个完整的字符串, 例如：
     * {@code org.apache.dubbo.xxx.YyyInvokerWrapper} 会生成 "yyy.invoker.wrapper",
     * 然后再以这个为依据去{@link URL}找对应的参数
     *
     * @return parameter names in URL
     */
    String[] value() default {};

}