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
package org.apache.dubbo.config;

import org.apache.dubbo.common.utils.StringUtils;

/**
 * 服务消费者缺省值配置, 是 ReferenceConfig 的缺省配置
 *
 * @export
 */
public class ConsumerConfig extends AbstractReferenceConfig {

    private static final long serialVersionUID = 2827274711143680600L;

    /**
     * 是否为缺省协议，用于多协议
     */
    private Boolean isDefault;

    /**
     * 协议的客户端实现类型，比如：dubbo协议的mina,netty等.
     * dubbo协议缺省为netty
     */
    private String client;

    /**
     * 服务消费者的线程池类型，可选：fixed/cached/limit(2.5.3以上)/eager(2.6.x以上), 默认fixed
     */
    private String threadpool;

    /**
     * 服务消费者的线程池的核心线程数
     */
    private Integer corethreads;

    /**
     * 服务线程池大小(固定大小), 默认200
     */
    private Integer threads;

    /**
     * 线程池队列大小
     */
    private Integer queues;

    /**
     * 默认情况下，在消费方进程和提供方进程之间共享TCP长连接通信。可以将此属性设置为共享多个TCP长连接通信。请注意，只有dubbo协议才能生效.
     */
    private Integer shareconnections;

    @Override
    public void setTimeout(Integer timeout) {
        super.setTimeout(timeout);
        String rmiTimeout = System.getProperty("sun.rmi.transport.tcp.responseTimeout");
        if (timeout != null && timeout > 0
                && (StringUtils.isEmpty(rmiTimeout))) {
            System.setProperty("sun.rmi.transport.tcp.responseTimeout", String.valueOf(timeout));
        }
    }

    public Boolean isDefault() {
        return isDefault;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getThreadpool() {
        return threadpool;
    }

    public void setThreadpool(String threadpool) {
        this.threadpool = threadpool;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getCorethreads() {
        return corethreads;
    }

    public void setCorethreads(Integer corethreads) {
        this.corethreads = corethreads;
    }

    public Integer getThreads() {
        return threads;
    }

    public void setThreads(Integer threads) {
        this.threads = threads;
    }

    public Integer getQueues() {
        return queues;
    }

    public void setQueues(Integer queues) {
        this.queues = queues;
    }

    public Integer getShareconnections() {
        return shareconnections;
    }

    public void setShareconnections(Integer shareconnections) {
        this.shareconnections = shareconnections;
    }
}
