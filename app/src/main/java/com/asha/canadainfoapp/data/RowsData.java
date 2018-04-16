package com.asha.canadainfoapp.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Asha on 4/2/2018.
 *
 * This class is to extract the subtitles, descriptions and images from REST service
 */

public class RowsData implements JSONPopulator {
    private String[] subtitle;
    private String[] description;
    private String[] imageHref;

    private int arrayLen;

    public String[] getSubtitle() {
        return subtitle;
    }

    public String[] getDescription() {
        return description;
    }

    public String[] getImageHref() {
        return imageHref;
    }

    public int getArrayLen() {
        return arrayLen;
    }

    @Override
    public void populator(JSONObject jsonObject) {
        try {
        JSONArray rowsData = jsonObject.optJSONArray("rows");
        arrayLen = rowsData.length();
        subtitle = new String[arrayLen];
        description = new String[arrayLen];
        imageHref = new String[arrayLen];

        if (rowsData != null) {
            for (int i = 0; i < arrayLen; i++) {
                JSONObject jsonObject1 = rowsData.getJSONObject(i);
                subtitle[i] = jsonObject1.getString("title");
                description[i] = jsonObject1.getString("description");
                imageHref[i] = jsonObject1.getString("imageHref");
            }
        }
    }catch (JSONException e){
            e.printStackTrace();
        }

    }
}
