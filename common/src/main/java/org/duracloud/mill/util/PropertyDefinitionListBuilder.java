/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 *     http://duracloud.org/license/
 */
package org.duracloud.mill.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.duracloud.mill.config.ConfigConstants;

/**
 * @author Daniel Bernstein
 *         Date: Dec 4, 2014
 */
public class PropertyDefinitionListBuilder {
     private List<PropertyDefinition> definitions = new LinkedList<>();
    /**
     * 
     */
    public PropertyDefinitionListBuilder addAws() {
        add(ConfigConstants.AWS_ACCESS_KEY_ID, true);
        add(ConfigConstants.AWS_SECRET_KEY, true, true);
        return this;
    }

    private void add(String name, boolean required, boolean sensitive) {
        definitions.add(new PropertyDefinition(name, null, required, sensitive));
    }

    private void add(String name, boolean required) {
        add(name, required, false);
    }

    public PropertyDefinitionListBuilder addMillDb() {
        add(ConfigConstants.MILL_DB_NAME, true);
        add(ConfigConstants.MILL_DB_HOST, true);
        add(ConfigConstants.MILL_DB_PORT, true);
        add(ConfigConstants.MILL_DB_USER, true);
        add(ConfigConstants.MILL_DB_PASS, true,true);
        return this;
    }

    public PropertyDefinitionListBuilder addMcDb() {
        add(ConfigConstants.MC_DB_NAME, true);
        add(ConfigConstants.MC_DB_HOST, true);
        add(ConfigConstants.MC_DB_PORT, true);
        add(ConfigConstants.MC_DB_USER, true);
        add(ConfigConstants.MC_DB_PASS, true,true);
        return this;
    }

    public PropertyDefinitionListBuilder addDeadLetterQueue() {
        add(ConfigConstants.QUEUE_NAME_DEAD_LETTER, true);
        return this;
    }
    
    public PropertyDefinitionListBuilder addAuditQueue(){
        add(ConfigConstants.QUEUE_NAME_AUDIT, true);
        return this;
    }


    public PropertyDefinitionListBuilder addDuplicationHighPriorityQueue(){
        add(ConfigConstants.QUEUE_NAME_DUP_HIGH_PRIORITY, true);
        return this;
    }

    public PropertyDefinitionListBuilder addDuplicationLowPriorityQueue(){
        add(ConfigConstants.QUEUE_NAME_DUP_LOW_PRIORITY, true);
        return this;
    }

    public PropertyDefinitionListBuilder addLocalDuplicationDir(){
        add(ConfigConstants.LOCAL_DUPLICATION_DIR, false);
        return this;
    }
    
    public PropertyDefinitionListBuilder addDuplicationPolicyBucketSuffix(){
        add(ConfigConstants.DUPLICATION_POLICY_BUCKET_SUFFIX, false);
        return this;
    }
    
    public PropertyDefinitionListBuilder addDuplicationPolicyRefreshFrequency(){
        add(ConfigConstants.DUPLICATION_POLICY_REFRESH_FREQUENCY, false);
        return this;
    }
    
    public PropertyDefinitionListBuilder addBitIntegrityQueue(){
        add(ConfigConstants.QUEUE_NAME_BIT_INTEGRITY, true);
        return this;
    }

    public PropertyDefinitionListBuilder addBitIntegrityErrorQueue(){
        add(ConfigConstants.QUEUE_NAME_BIT_ERROR, true);
        return this;
    }

    public PropertyDefinitionListBuilder addNotificationRecipients() {
        add(ConfigConstants.NOTIFICATION_RECIPIENTS, true);
        return this;
    }



    public PropertyDefinitionListBuilder addWorkDir() {
        add(ConfigConstants.WORK_DIRECTORY_PATH,true);
        return this;
    }

    public List<PropertyDefinition> build(){
        return new ArrayList<>(this.definitions);
    }

    public PropertyDefinitionListBuilder addMaxWorkers() {
       add(ConfigConstants.MAX_WORKERS, false);
       return this;
    }

    public PropertyDefinitionListBuilder addDuracloudAuditSpace() {
        add(ConfigConstants.AUDIT_LOG_GENERATOR_AUDIT_LOG_SPACE_ID, true);
        return this;
    }

    public PropertyDefinitionListBuilder addLoopingBitFrequency() {
        add(ConfigConstants.LOOPING_BIT_FREQUENCY,false);
        return this;
    }

    public PropertyDefinitionListBuilder addLoopingBitMaxQueueSize() {
        add(ConfigConstants.LOOPING_BIT_MAX_TASK_QUEUE_SIZE,false);
        return this;
    }
    
    public PropertyDefinitionListBuilder addLoopingBitStateFilePath(){
        add(ConfigConstants.LOOPING_BIT_STATE_FILE_PATH, true);
        return this;
    }
    
    public PropertyDefinitionListBuilder addLoopingDupFrequency() {
        add(ConfigConstants.LOOPING_DUP_FREQUENCY,false);
        return this;
    }

    public PropertyDefinitionListBuilder addLoopingDupMaxQueueSize() {
        add(ConfigConstants.LOOPING_DUP_MAX_TASK_QUEUE_SIZE,false);
        return this;
    }
    
    public PropertyDefinitionListBuilder addLoopingDupStateFilePath(){
        add(ConfigConstants.LOOPING_DUP_STATE_FILE_PATH, true);
        return this;
    }

    /**
     * @return
     */
    public PropertyDefinitionListBuilder addManifestExpirationDate() {
        add(ConfigConstants.MANIFEST_EXPIRATION_TIME, true);
        return this;
    }

    /**
     * @return
     */
    public PropertyDefinitionListBuilder addTaskQueueOrder() {
        add(ConfigConstants.QUEUE_TASK_ORDERED, true);
        return this;
    }
}