package uk.ac.jorum.integration.matchers.fixtures;

import static uk.ac.jorum.integration.matchers.ItemMatchers.isItem;
import static uk.ac.jorum.integration.matchers.ItemMatchers.isItemWithId;
import static uk.ac.jorum.integration.matchers.ItemMatchers.isItemWithMetadataId;

import java.util.ArrayList;

import org.hamcrest.Matcher;
import org.json.simple.JSONObject;

public class AllItemMatchers {
	public static final Matcher<JSONObject> firstItem(
			Matcher<JSONObject> owningCollection,
			ArrayList<Matcher<JSONObject>> communities,
			ArrayList<Matcher<JSONObject>> collections) {
		return isItem(1, "First Upload", true, false, owningCollection,
				AllUserMatchers.firstUser(), communities, collections,
				AllBundleMatchers.firstBundleList(),
				AllBitstreamMatchers.firstBitstreamList());
	}

	public static final Matcher<JSONObject> firstItemWithIdOnlyMetadata(
			Matcher<JSONObject> owningCollection,
			ArrayList<Matcher<JSONObject>> communities,
			ArrayList<Matcher<JSONObject>> collections) {
		return isItemWithMetadataId(1, "First Upload", true, false,
				owningCollection, AllUserMatchers.firstUser(), communities,
				collections, AllBundleMatchers.firstBundleIdList(),
				AllBitstreamMatchers.firstBitstreamIdList());
	}

	public static final ArrayList<Matcher<JSONObject>> firstItemIdList() {
		return new ArrayList<Matcher<JSONObject>>() {
			{
				add(AllItemMatchers.firstItemId());
			}
		};
	}

	public static final Matcher<JSONObject> firstItemId() {
		return isItemWithId(1);
	}

	public static final Matcher<JSONObject> secondItem(
			Matcher<JSONObject> owningCollection,
			ArrayList<Matcher<JSONObject>> communities,
			ArrayList<Matcher<JSONObject>> collections) {
		return isItem(2, "Second Upload", true, false, owningCollection,
				AllUserMatchers.firstUser(), communities, collections,
				AllBundleMatchers.secondBundleList(),
				AllBitstreamMatchers.secondBitstreamList());
	}

	public static final Matcher<JSONObject> secondItemWithIdOnlyMetadata(
			Matcher<JSONObject> owningCollection,
			ArrayList<Matcher<JSONObject>> communities,
			ArrayList<Matcher<JSONObject>> collections) {
		return isItemWithMetadataId(2, "Second Upload", true, false,
				owningCollection, AllUserMatchers.firstUser(), communities,
				collections, AllBundleMatchers.secondBundleIdList(),
				AllBitstreamMatchers.secondBitstreamIdList());
	}

	public static final ArrayList<Matcher<JSONObject>> secondItemIdList() {
		return new ArrayList<Matcher<JSONObject>>() {
			{
				add(AllItemMatchers.secondItemId());
			}
		};
	}

	public static final Matcher<JSONObject> secondItemId() {
		return isItemWithId(2);
	}
}
