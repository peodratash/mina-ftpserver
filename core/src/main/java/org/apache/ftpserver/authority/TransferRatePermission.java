/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.ftpserver.authority;

import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.AuthorizationRequest;
import org.apache.ftpserver.ftplet.TransferRateAuthorizationRequest;

/**
 * The max upload and download rate permission.  Can be used
 * to handle the AuthorizationRequest checked before a file
 * transfer takes place in order to restrict the bandwidth 
 * allocated to a particular user.
 *
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 */
public class TransferRatePermission implements Authority {

    private int maxDownloadRate;

    private int maxUploadRate;

    public TransferRatePermission(int maxDownloadRate, int maxUploadRate) {
        this.maxDownloadRate = maxDownloadRate;
        this.maxUploadRate = maxUploadRate;
    }

    /**
     * @see Authority#authorize(AuthorizationRequest)
     */
    public AuthorizationRequest authorize(AuthorizationRequest request) {
        if (request instanceof TransferRateAuthorizationRequest) {
            TransferRateAuthorizationRequest transferRateRequest = (TransferRateAuthorizationRequest) request;

            transferRateRequest.setMaxDownloadRate(maxDownloadRate);
            transferRateRequest.setMaxUploadRate(maxUploadRate);

            return transferRateRequest;
        } else {
            return null;
        }
    }

    /**
     * @see Authority#canAuthorize(AuthorizationRequest)
     */
    public boolean canAuthorize(AuthorizationRequest request) {
        return request instanceof TransferRateAuthorizationRequest;
    }
}
