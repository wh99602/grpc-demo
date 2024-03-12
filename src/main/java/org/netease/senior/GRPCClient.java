/**
 * @(#)GRPCClient.java, 2024-03-12.
 * <p>
 * Copyright 2024 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.netease.senior;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.netease.senior.protobuf.RPCDateRequest;
import org.netease.senior.protobuf.RPCDateResponse;
import org.netease.senior.protobuf.RPCDateServiceGrpc;

/**
 * GRPCClient
 *
 * @author wangheng
 * @since 2024/03/12
 */
public class GRPCClient {
    private static final String host = "localhost";
    private static final int serverPort = 9999;
    public static void main(String[] args) {
        //1,拿到一个通信channel
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, serverPort).
                usePlaintext()//无需加密或认证
                .build();
        try {
            //2.拿到stub对象
            RPCDateServiceGrpc.RPCDateServiceBlockingStub rpcDateService  = RPCDateServiceGrpc.newBlockingStub(channel);
            RPCDateRequest rpcDateRequest = RPCDateRequest.newBuilder()
                    .setUserName("拉拉")
                    .build();
            //3,请求
            RPCDateResponse rpcDateResponse = rpcDateService.getDate(rpcDateRequest);
            //4,输出结果
            System.out.println(rpcDateResponse.getServerDate());
        } finally {
            // 5.关闭channel, 释放资源.
            channel.shutdown();
        }
        
    }
}