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

import org.apache.dubbo.common.Node;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 代表一个可执行体, 可向它发起 invoke 调用, 有可能是一个本地的实现,
 * 也可能是一个远程的实现, 也可能一个集群实现. 它可以理解成一个dubbo api接口,
 * 就是使用{@link DubboService}标注的那个接口.
 *
 * @see org.apache.dubbo.rpc.Protocol#refer(Class, org.apache.dubbo.common.URL)
 * @see org.apache.dubbo.rpc.InvokerListener
 * @see org.apache.dubbo.rpc.protocol.AbstractInvoker
 */
public interface Invoker<T> extends Node {

    /**
     * 获取它关联的服务接口
     *
     * @return service interface.
     */
    Class<T> getInterface();

    /**
     * 调用接口内的方法
     *
     * @param invocation 调用过程中的变量，比如方法名，参数等
     * @return 执行结果
     * @throws RpcException rpc异常
     */
    Result invoke(Invocation invocation) throws RpcException;

}