package uk.ac.jorum.integration.searching;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.ac.jorum.integration.RestApiBaseTest;

public class SearchVolumeTest extends RestApiBaseTest {
    private static final String SEARCH_RESULTS_KEY = "search_collection";
    private static final int NUMBER_OF_HEADER_RECORDS = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;

    @BeforeClass
    public static void createFixture() throws Exception {
      loadFixture("searchDatabase");
      startJetty();
    }

    private int countResults(String extraparameters) throws Exception {
        return count(requestJSON(extraparameters), SEARCH_RESULTS_KEY) - NUMBER_OF_HEADER_RECORDS;
    }

    private JSONObject requestJSON(String extraparameters) throws Exception {
        String queryString = "query=search.resourcetype:2" + extraparameters;
        String result = makeRequest("/search", queryString);
        JSONObject resultJSON = (JSONObject)JSONValue.parse(result);
        return resultJSON;
    }

    private int count(JSONObject resultJSON, String key) {
        return ((JSONArray) resultJSON.get(key)).size();
    }
    
    private void defaultNumberOfItemsInPage(int page) throws Exception {
        assertEquals("Result count should be " + DEFAULT_PAGE_SIZE, 
                DEFAULT_PAGE_SIZE, countResults("&_page=" + page));    
    }
    
    @Test
    public void searchShouldReturnDefaultPageSize() throws Exception {
        assertEquals("Result count should be " + DEFAULT_PAGE_SIZE, 
                DEFAULT_PAGE_SIZE, countResults(""));
    }

    @Test
    public void searchShouldReturnDefaultNumberOfItemsWhenPageIsSetTo1() throws Exception {
        defaultNumberOfItemsInPage(1);
    }

    @Test
    public void firstAndSecondPagesShouldShareNoCommonItems() throws Exception {
//        final Set<Integer> firstPageIds = pageIds(1);
//        final Set<Integer> secondPageIds = pageIds(2);
//        assertTrue("No shared items should appear on the first and second pages." + firstPageIds + "," + secondPageIds, CollectionUtils.intersection(firstPageIds, secondPageIds).isEmpty());
    }
}
