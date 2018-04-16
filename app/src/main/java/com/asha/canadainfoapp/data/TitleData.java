package com.asha.canadainfoapp.data;

import org.json.JSONObject;

/**
 * Created by Asha on 4/2/2018.
 *
 * This class is to extract the title from REST service
 */

public class TitleData implements JSONPopulator {
    private String title;
    private RowsData rowsData;

    public String getTitle() {
        return title;
    }

    public RowsData getRowsData() {
        return rowsData;
    }

    @Override
    public void populator(JSONObject jsonObject) {
        rowsData = new RowsData();

        rowsData.populator(jsonObject);

        title = jsonObject.optString("title");
    }
}
