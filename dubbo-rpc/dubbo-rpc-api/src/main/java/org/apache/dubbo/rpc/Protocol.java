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
package org.apache.dubbo.rpc;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

import java.util.Collections;
import java.util.List;

/**
 * {@link Invoker}暴露和引用的功能入口, 负责{@link Invoker} 的生命周期管理
 */
@SPI("dubbo")
public interface Protocol {

    /**
     * 获取默认的端口号
     */
    int getDefaultPort();

    /**
     * 暴露用于远程调用的服务：
     * 1.Protocol在收到一个请求Request后, 需要记录请求Request的源地址:RpcContext.getContext().setRemoteAddress();
     * 2.此方法必须保证是幂等的, 同一个URL只会导出一次;
     * 3.{@link Invoker}由框架传进, Protocol无需关心
     *
     * @param <T>     服务类型
     * @param invoker 服务invoker实例
     * @return 已导出服务的引用, 一般可以用在取消导出
     */
    @Adaptive
    <T> Exporter<T> export(Invoker<T> invoker) throws RpcException;

    /**
     * 引用远程服务：
     * 1. When user calls `invoke()` method of `Invoker` object which's returned from `refer()` call, the protocol
     * needs to correspondingly execute `invoke()` method of `Invoker` object <br>
     * 2. It's protocol's responsibility to implement `Invoker` which's returned from `refer()`. Generally speaking,
     * protocol sends remote request in the `Invoker` implementation. <br>
     * 3. When there's check=false set in URL, the implementation must not throw exception but try to recover when
     * connection fails.
     *
     * @param <T>  Service type
     * @param type Service class
     * @param url  URL address for the remote service
     * @return invoker service's local proxy
     * @throws RpcException when there's any error while connecting to the service provider
     */
    @Adaptive
    <T> Invoker<T> refer(Class<T> type, URL url) throws RpcException;

    /**
     * 销毁Protocol：
     * 1.取消由此Protocol导出和引用的所有服务
     * 2.释放所有占用的资源, 如连接、端口...
     * 3.即使Protocol被销毁, Protocol也可以继续导出并引用新服务
     */
    void destroy();

    /**
     * 获取所有使用此Protocol的服务器
     */
    default List<ProtocolServer> getServers() {
        return Collections.emptyList();
    }

}