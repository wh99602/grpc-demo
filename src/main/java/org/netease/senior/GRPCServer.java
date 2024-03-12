/**
 * @(#)GRPCServer.java, 2024-03-12.
 * <p>
 * Copyright 2024 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.netease.senior;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * GRPCServer
 *
 * @author wangheng
 * @since 2024/03/12
 */
public class GRPCServer {
    private static final int port = 9999;
    
    public static void main(String[] args) throws IOException, InterruptedException {
        //设置service端口
        Server server = ServerBuilder.forPort(port)
                .addService(new RPCDateServiceImpl())
                .build().start();
        System.out.println(String.format("GRpc服务端启动成功, 端口号: %d.", port));
        
        server.awaitTermination();
        
        
    }
}