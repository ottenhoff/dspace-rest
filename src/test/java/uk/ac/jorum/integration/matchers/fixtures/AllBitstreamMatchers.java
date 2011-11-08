package uk.ac.jorum.integration.matchers.fixtures;

import static uk.ac.jorum.integration.matchers.BitstreamsMatchers.isBitstream;
import static uk.ac.jorum.integration.matchers.BitstreamsMatchers.isBitstreamWithId;

import java.util.ArrayList;

import org.hamcrest.Matcher;
import org.json.simple.JSONObject;

public class AllBitstreamMatchers {

	public static final Matcher<JSONObject> firstBitstream() {
		return isBitstream(1, "firstUpload.txt", "Text", "text/plain",
				AllBundleMatchers.firstBundleIdList());
	}

	public static final Matcher<JSONObject> firstBitstreamId() {
		return isBitstreamWithId(1);
	}

	public static final Matcher<JSONObject> secondBitstreamId() {
		return isBitstreamWithId(2);
	}
	
	public static final Matcher<JSONObject> thirdBitstream() {
		return isBitstream(3, "secondUpload.txt", "Text", "text/plain",
				AllBundleMatchers.secondBundleIdList());
	}

	public static final Matcher<JSONObject> thirdBitstreamId() {
		return isBitstreamWithId(3);
	}

	public static final Matcher<JSONObject> fourthBitstreamId() {
		return isBitstreamWithId(4);
	}

	public static final ArrayList<Matcher<JSONObject>> firstBitstreamList() {
		return new ArrayList<Matcher<JSONObject>>() {
			{
				add(firstBitstream());
			}
		};
	}
	
	public static final ArrayList<Matcher<JSONObject>> secondBitstreamList() {
		return new ArrayList<Matcher<JSONObject>>() {
			{
				add(thirdBitstream());
			}
		};
	}

	public static final ArrayList<Matcher<JSONObject>> firstBitstreamIdList() {
		return new ArrayList<Matcher<JSONObject>>() {
			{
				add(firstBitstreamId());
				add(secondBitstreamId());
			}
		};
	}
	
	public static final ArrayList<Matcher<JSONObject>> secondBitstreamIdList() {
		return new ArrayList<Matcher<JSONObject>>() {
			{
				add(thirdBitstreamId());
				add(fourthBitstreamId());
			}
		};
	}
}
