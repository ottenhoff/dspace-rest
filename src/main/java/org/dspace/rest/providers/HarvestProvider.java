/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.rest.providers;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dspace.core.Context;
import org.dspace.rest.diagnose.ErrorDetail;
import org.dspace.rest.diagnose.Operation;
import org.dspace.rest.diagnose.RequestFormatEntityException;
import org.dspace.rest.diagnose.SQLFailureEntityException;
import org.dspace.rest.entities.HarvestResultsInfoEntity;
import org.dspace.rest.entities.ItemEntity;
import org.dspace.rest.entities.ItemEntityId;
import org.dspace.rest.params.DetailDepthParameters;
import org.dspace.rest.params.EntityBuildParameters;
import org.dspace.rest.params.PaginationParameters;
import org.dspace.rest.params.Parameters;
import org.dspace.rest.params.ScopeParameters;
import org.dspace.search.Harvest;
import org.dspace.search.HarvestedItemInfo;
import org.sakaiproject.entitybus.EntityReference;
import org.sakaiproject.entitybus.entityprovider.CoreEntityProvider;
import org.sakaiproject.entitybus.entityprovider.EntityProviderManager;
import org.sakaiproject.entitybus.entityprovider.search.Search;
import org.sakaiproject.entitybus.exception.EntityException;

/**
 * Provides interface for access to harvesting
 * Enables users to harvest items according to several queries, including
 * data range of publication, status of publication, containing elements etc
 * @see HarvestResultsInfoEntity
 * @author Bojan Suzic, bojan.suzic@gmail.com
 */
public class HarvestProvider extends AbstractBaseProvider implements CoreEntityProvider {

    public HarvestProvider(EntityProviderManager entityProviderManager) throws SQLException {
        super(entityProviderManager);
    }

    public String getEntityPrefix() {
        return "harvest";
    }

    public boolean entityExists(String id) {
        return true;
    }

    public Object getEntity(EntityReference reference) {
        throw new EntityException("Not Acceptable", "The data is not available", 406);
    }

    public List<?> getEntities(EntityReference ref, Search search) {
        return getAllHavested();
    }

    private List<?> getAllHavested() {
        final Parameters parameters = new Parameters(requestStore);
        final Context context = context();
        final Operation operation = Operation.GET_HARVEST;
        
        try {
            final List<Object> entities = new ArrayList<Object>();
            final List<HarvestedItemInfo>  harvestedItems = harvest(context, parameters);

            // check results and add entities
            entities.add(new HarvestResultsInfoEntity(harvestedItems.size()));
            for (int x = 0; x < harvestedItems.size(); x++) {
                entities.add(parameters.getEntityBuild().isIdOnly() 
                        ? new ItemEntityId(harvestedItems.get(x).item) : 
                            new ItemEntity(harvestedItems.get(x).item, 1, parameters.getDetailDepth().getDepth()));
            }

            // sort entities if the full info are requested and there are sorting fields
            parameters.sort(entities);

            // format results according to _limit, _perpage etc
            parameters.removeTrailing(entities);

            return entities;
        } catch (ParseException cause) {
            throw new RequestFormatEntityException(operation, cause, ErrorDetail.PARSE_REQUEST_DATE);
        } catch (SQLException cause) {
            throw new SQLFailureEntityException(operation, cause);
        } finally {
            complete(context);
        }
    }

    @SuppressWarnings("unchecked")
    private List<HarvestedItemInfo> harvest(final Context context, final Parameters parameters)
            throws SQLException, ParseException {
        List<HarvestedItemInfo> harvestedItems;
        harvestedItems = Harvest.harvest(context, ScopeParameters.build(requestStore, context).scope(), 
                _sdate, _edate, 
                parameters.getPagination().getStart(), parameters.getPagination().getLimit(), 
                true, true, withdrawn, true);
        return harvestedItems;
    }

    /**
     * Returns a Entity object with sample data
     */
    public Object getSampleEntity() {
        return null;
    }
}
