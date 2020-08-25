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

import org.apache.dubbo.config.support.Parameter;

import java.util.Map;

/**
 * AbstractMethodConfig
 *
 * @export
 */
public abstract class AbstractMethodConfig extends AbstractConfig {

    private static final long serialVersionUID = 1L;

    /**
     * 远程服务调用超时时间(毫秒), 默认1000ms
     */
    protected Integer timeout;

    /**
     * 远程服务调用重试次数, 不包括第一次调用, 设置为0时表示不需要重试.
     * 默认值为2
     */
    protected Integer retries;

    /**
     * 每服务消费者每服务每方法最大并发调用数
     */
    protected Integer actives;

    /**
     * 负载均衡策略, 可选：random、roundrobin、leastactive,
     * 分别表示：随机，轮询，最少活跃调用. 默认 random
     */
    protected String loadbalance;

    /**
     * 是否异步执行, 这是一种不可靠的机制, 会忽略返回值并且不会阻塞线程.
     * 默认false
     */
    protected Boolean async;

    /**
     * Whether to ack async-sent
     */
    protected Boolean sent;

    /**
     * 服务接口调用失败的Mock实现类, 只在出现非业务异常(比如超时、网络异常等)时执行, mock在提供者端不支持
     */
    protected String mock;

    /**
     * Merger
     */
    protected String merger;

    /**
     * 以调用参数为key，缓存返回结果，可选：lru, threadlocal, jcache等
     */
    protected String cache;

    /**
     * 是否启用JSR303标准注解验证，如果启用(true)，将对方法参数上的注解进行校验
     */
    protected String validation;

    /**
     * The customized parameters
     */
    protected Map<String, String> parameters;

    /**
     * Forks for forking cluster
     */
    protected Integer forks;

    public Integer getForks() {
        return forks;
    }

    public void setForks(Integer forks) {
        this.forks = forks;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getRetries() {
        return retries;
    }

    public void setRetries(Integer retries) {
        this.retries = retries;
    }

    public String getLoadbalance() {
        return loadbalance;
    }

    public void setLoadbalance(String loadbalance) {
        this.loadbalance = loadbalance;
    }

    public Boolean isAsync() {
        return async;
    }

    public void setAsync(Boolean async) {
        this.async = async;
    }

    public Integer getActives() {
        return actives;
    }

    public void setActives(Integer actives) {
        this.actives = actives;
    }

    public Boolean getSent() {
        return sent;
    }

    public void setSent(Boolean sent) {
        this.sent = sent;
    }

    @Parameter(escaped = true)
    public String getMock() {
        return mock;
    }

    public void setMock(String mock) {
        this.mock = mock;
    }

    /**
     * Set the property "mock"
     *
     * @param mock the value of mock
     * @since 2.7.6
     */
    public void setMock(Object mock) {
        if (mock == null) {
            return;
        }
        this.setMock(String.valueOf(mock));
    }

    public String getMerger() {
        return merger;
    }

    public void setMerger(String merger) {
        this.merger = merger;
    }

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

}
