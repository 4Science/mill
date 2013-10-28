/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 *     http://duracloud.org/license/
 */
package org.duracloud.mill.dup;

import org.duracloud.glacierstorage.GlacierStorageProvider;
import org.duracloud.mill.credentials.AccountCredentials;
import org.duracloud.mill.credentials.AccountCredentialsNotFoundException;
import org.duracloud.mill.credentials.CredentialRepo;
import org.duracloud.mill.credentials.ProviderCredentials;
import org.duracloud.mill.domain.DuplicationTask;
import org.duracloud.mill.domain.Task;
import org.duracloud.mill.workman.TaskProcessorCreationFailedException;
import org.duracloud.mill.workman.TaskProcessor;
import org.duracloud.mill.workman.TaskProcessorFactoryBase;
import org.duracloud.rackspacestorage.RackspaceStorageProvider;
import org.duracloud.s3storage.S3StorageProvider;
import org.duracloud.sdscstorage.SDSCStorageProvider;
import org.duracloud.storage.domain.StorageProviderType;
import org.duracloud.storage.provider.StorageProvider;

/**
 * This class is responsible for creating DuplicationTaskProcessors 
 * 
 * @author Daniel Bernstein
 * 
 */
public class DuplicationTaskProcessorFactory extends TaskProcessorFactoryBase {

    public DuplicationTaskProcessorFactory(CredentialRepo repo){
        super(repo);
    }
    
    @Override
    protected boolean isSupported(Task task) {
        return task.getType().equals(Task.Type.DUP);
    }

    @Override
    protected TaskProcessor createImpl(Task task) throws TaskProcessorCreationFailedException {

        DuplicationTask dtask = new DuplicationTask();
        dtask.readTask(task);
        String subdomain = dtask.getAccount();

        try {
            AccountCredentials account = getCredentialRepo().getAccoundCredentialsBySubdomain(
                    subdomain);
            account.getProviderCredentials(dtask.getSourceStoreId());
            StorageProvider sourceStore = createStorageProvider(
                    dtask.getSourceStoreId(), account);
            StorageProvider destStore = createStorageProvider(
                    dtask.getDestStoreId(), account);
            return new DuplicationTaskProcessor(task, sourceStore, destStore);
        } catch (AccountCredentialsNotFoundException e) {
            throw new TaskProcessorCreationFailedException(
                    "failed to create task: unable to locate credentials for subdomain: "
                            + subdomain);
        }
    }

    /**
     * @param sourceStoreId
     * @param account
     * @return
     */
    private StorageProvider createStorageProvider(String providerId,
            AccountCredentials account) {
        
        ProviderCredentials c = account.getProviderCredentials(providerId);
        StorageProviderType ptype = c.getProviderType();
        
        if(ptype.equals(StorageProviderType.AMAZON_S3)){
            return new S3StorageProvider(c.getAccessKey(),c.getSecretKey());
        }else if(ptype.equals(StorageProviderType.SDSC)){
            return new SDSCStorageProvider(c.getAccessKey(),c.getSecretKey());
        }else if(ptype.equals(StorageProviderType.AMAZON_GLACIER)){
            return new GlacierStorageProvider(c.getAccessKey(),c.getSecretKey());
        }else if(ptype.equals(StorageProviderType.RACKSPACE)){
            return new RackspaceStorageProvider(c.getAccessKey(),c.getSecretKey());
        }
        throw new RuntimeException(ptype + " is not a supported storage provider type");
    }    
}
