package org.dspace.rest.params;

import org.dspace.rest.entities.ItemBuilder;
import org.sakaiproject.entitybus.entityprovider.extension.RequestStorage;

public class Parameters {

    private final RequestStorage requestStore;
    
    public Parameters(RequestStorage requestStore) {
        super();
        this.requestStore = requestStore;
    }

    public EntityBuildParameters getEntityBuild() {
        return EntityBuildParameters.build(requestStore);
    }

    public DetailDepthParameters getDetailDepth() {
        return DetailDepthParameters.build(requestStore);
    }

    public ItemBuilder itemBuilder() {
        return ItemBuilder.builder(getEntityBuild().isIdOnly(), getDetailDepth().getDepth());
    }
}
