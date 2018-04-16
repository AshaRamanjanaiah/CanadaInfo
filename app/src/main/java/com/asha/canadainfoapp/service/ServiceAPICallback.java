package com.asha.canadainfoapp.service;

import com.asha.canadainfoapp.data.TitleData;

/**
 * Created by Asha on 4/2/2018.
 */

public interface ServiceAPICallback {
    void serviceSuccess(TitleData titleData);
    void serviceFailure(Exception error);
}
