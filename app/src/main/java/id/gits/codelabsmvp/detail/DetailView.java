package id.gits.codelabsmvp.detail;

import id.gits.codelabsmvp.model.Detail;

/**
 * Created by Firman on 10/6/2017.
 */

public interface DetailView {
    void onDetailResponse(Detail detailData);
    void onErrorResponse(String message);
}

