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
 * 注册中心数据发生变化时, 通过它来触发回调.
 *
 * @see org.apache.dubbo.registry.RegistryService#subscribe(URL, NotifyListener)
 */
public interface NotifyListener {

    /**
     * 收到服务更改通知时触发, 需要满足以下5个条件:
     * 1,始终在服务界面和数据类型的维度上发出通知;
     * 2.订阅时的第一个通知必须是服务的所有数据类型的完整通知;
     * 3.在更改时, 允许分别通知不同类型的数据, 例如：提供者，使用者，路由器，替代. 它仅允许通知这些类型中的一种，但是此类型的数据必须是完整的，而不是增量数据;
     * 4.如果数据类型为空，则需要使用url数据的类别参数标识通知空协议;
     * 5.通知要保证的通知顺序（即注册表的执行）。例如：单线程推送，队列序列化和版本比较
     *
     * @param urls 已经注册的url信息(不会是空集合), 它的值等于{@link org.apache.dubbo.registry.RegistryService#lookup(URL)}.
     */
    void notify(List<URL> urls);

}