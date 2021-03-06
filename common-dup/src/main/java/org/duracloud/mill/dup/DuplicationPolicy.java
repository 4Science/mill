/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 *     http://duracloud.org/license/
 */
package org.duracloud.mill.dup;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * A set of data showing, for a single DuraCloud account, which spaces
 * from which providers should be duplicated to another space on another
 * provider.
 *
 * This class also handles the work of loading this data set from a file
 * stream, allowing it to be stored in a DuraCloud account and be read here.
 *
 * @author Bill Branan
 *         Date: 10/18/13
 */
public class DuplicationPolicy {

    private static final ObjectMapper objMapper = new ObjectMapper();

    private Map<String, LinkedHashSet<DuplicationStorePolicy>>
        spaceDuplicationStorePolicies = new HashMap<>();

    public Map<String, LinkedHashSet<DuplicationStorePolicy>>
    getSpaceDuplicationStorePolicies() {
        return spaceDuplicationStorePolicies;
    }

    @JsonIgnore
    public Set<String> getSpaces() {
        return spaceDuplicationStorePolicies.keySet();
    }

    public Set<DuplicationStorePolicy> getDuplicationStorePolicies(String spaceId) {
        return spaceDuplicationStorePolicies.get(spaceId);
    }

    /**
     * Adds a DuplicationStorePolicy for the specified space ID.
     * @param spaceId the space ID to add the DuplicationStorePolicy for
     * @param dupStore the DuplicationStorePolicy
     * @return true if added, false otherwise.  False will be returned if the
     * Set<DuplicationStorePolicy> for the specified space already contains the
     * dupStore.
     */
    public boolean addDuplicationStorePolicy(String spaceId,
                                             DuplicationStorePolicy dupStore) {
        LinkedHashSet<DuplicationStorePolicy> dupStores =
            spaceDuplicationStorePolicies.get(spaceId);
        if(dupStores == null) {
            dupStores = new LinkedHashSet<>();
            spaceDuplicationStorePolicies.put(spaceId, dupStores);
        }
        return dupStores.add(dupStore);
    }

    public static DuplicationPolicy unmarshall(InputStream policyStream)
        throws IOException {
        return objMapper.readValue(policyStream, DuplicationPolicy.class);
    }

    public static String marshall(DuplicationPolicy duplicationPolicy)
            throws IOException {
        return objMapper.writeValueAsString(duplicationPolicy);
    }
}
