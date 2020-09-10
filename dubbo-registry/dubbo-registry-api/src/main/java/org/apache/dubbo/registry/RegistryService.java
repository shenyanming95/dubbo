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
package org.apache.dubbo.registry;

import org.apache.dubbo.common.URL;

import java.util.List;

/**
 * 注册服务接口
 *
 * @see org.apache.dubbo.registry.Registry
 * @see org.apache.dubbo.registry.RegistryFactory#getRegistry(URL)
 */
public interface RegistryService {

    /**
     * 注册数据, 例如：提供者服务、使用者地址、路由规则、覆盖规则、其他数据：
     * 1.URL设置check = false, 注册失败时, 不会在后台引发异常并重试异常.否则, 将引发异常;
     * 2.URL设置dynamic = false时, 它需要永久存储. 否则, 当注册方法异常退出时, 应将其自动删除;
     * 3.URL设置category = routers时, 表示分类存储, 默认分类为提供者, 并且可以通过分类部分通知数据;
     * 4.重新启动注册表时, 网络抖动, 数据不会丢失, 包括从虚线自动删除数据;
     * 5.允许具有相同URL但参数不同的URL共存, 它们不能互相覆盖
     *
     * @param url 待注册的url, 不为为空, 内容大概为：dubbo://10.20.153.10/org.apache.dubbo.foo.BarService?version=1.0.0&application=kylin
     */
    void register(URL url);

    /**
     * 取消注册：
     * 1.若配置dynamic = false的持久存储数据, 找不到注册数据则抛出IllegalStateException, 否则将被忽略;
     * 2.根据完整的URL匹配取消注册
     *
     * @param url 准备取消注册的url, 不能为空, 内容大概为：dubbo://10.20.153.10/org.apache.dubbo.foo.BarService?version=1.0.0&application=kylin
     */
    void unregister(URL url);

    /**
     * 订阅符合条件的注册数据，并在更改注册数据时自动推送：
     * 1.URL设置check = false时, 注册失败, 不会在后台引发异常并重试异常;
     * 2.URL设置category = routers时, 仅通知指定的分类数据. 多个分类用逗号分隔, 并允许星号匹配, 这表示所有分类数据都已订阅;
     * 3.允许将接口、组、版本、分类器等, 作为条件查询, 例如：interface = org.apache.dubbo.foo.BarService＆version = 1.0.0;
     * 4.查询条件允许星号匹配, 订阅所有接口的所有分组的所有版本.例如: interface =＆group =＆version =＆classifier =;
     * 5.重新启动注册表并出现网络抖动时, 有必要自动恢复订阅请求;
     * 6.允许具有相同URL但不同参数的URL共存, 它们不能互相覆盖;
     * 7.在完成第一个通知并返回之前, 必须阻止订阅过程
     *
     * @param url      注册条件, 不能为空, 内容大概为：consumer://10.20.153.10/org.apache.dubbo.foo.BarService?version=1.0.0&application=kylin
     * @param listener 在数据发生变化时, 通过这个监听器作出响应
     */
    void subscribe(URL url, NotifyListener listener);

    /**
     * 取消订阅：
     * 1.如果不订阅, 直接忽略;
     * 2.取消订阅完整的URL匹配
     *
     * @param url      准备取消订阅的url, 内容大概为：consumer://10.20.153.10/org.apache.dubbo.foo.BarService?version=1.0.0&application=kylin
     * @param listener 在数据发生变化时, 通过这个监听器作出响应
     */
    void unsubscribe(URL url, NotifyListener listener);

    /**
     * 查询符合条件的注册数据:
     *
     * @param url 查询条件
     * @return 注册信息集合
     * @see org.apache.dubbo.registry.NotifyListener#notify(List)
     */
    List<URL> lookup(URL url);

}