/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 *     http://duracloud.org/license/
 */
package org.duracloud.mill.credentials;

import org.duracloud.storage.domain.StorageProviderType;

/**
 * A class containing all necessary information for connecting to a provider.
 * 
 * @author Daniel Bernstein
 */
public class StorageProviderCredentials {
    private String providerId;
    private String accessKey;
    private String secretKey;
    private StorageProviderType providerType;
    private boolean primary;

    public StorageProviderCredentials(){
        
    }
    /**
     * @param providerId
     * @param accessKey
     * @param secretKey
     * @param providerType
     * @param primary 
     */
    public StorageProviderCredentials(String providerId, String accessKey,
            String secretKey, StorageProviderType providerType, boolean primary) {
        super();
        this.providerId = providerId;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.providerType = providerType;
        this.setPrimary(primary);
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public StorageProviderType getProviderType() {
        return providerType;
    }

    public void setProviderType(StorageProviderType providerType) {
        this.providerType = providerType;
    }
    public boolean isPrimary() {
        return primary;
    }
    public void setPrimary(boolean primary) {
        this.primary = primary;
    }
}
