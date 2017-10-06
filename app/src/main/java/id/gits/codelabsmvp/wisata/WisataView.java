package id.gits.codelabsmvp.wisata;

import java.util.List;

import id.gits.codelabsmvp.model.Wisata;

/**
 * Created by Firman on 10/6/2017.
 */

public interface WisataView {
    void onTravelResponse(List<Wisata.WisataData> wisataData);
    void onBannerResponse(List<Wisata.WisataData> wisataData);
    void updateToolbarTitle(String title);
}
