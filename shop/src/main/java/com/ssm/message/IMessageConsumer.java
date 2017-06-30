/*
 * #{copyright}#
 */

package com.ssm.message;

/**
 * 
 * @author meixl
 * @param <T>
 *            消息类型
 */
public interface IMessageConsumer<T> {
    String DEFAULT_METHOD_NAME = "onMessage";

    /**
     * 
     * @param message
     *            经过反序列化的消息
     * @param pattern
     *            list name or channel
     */
    void onMessage(T message, String pattern);
}
