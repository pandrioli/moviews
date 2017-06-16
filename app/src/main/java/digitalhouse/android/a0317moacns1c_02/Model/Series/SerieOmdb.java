package digitalhouse.android.a0317moacns1c_02.Model.Series;

import digitalhouse.android.a0317moacns1c_02.Model.General.OmdbBaseResponse;

/**
 * Created by forev on 15-Jun-17.
 */

public class SerieOmdb extends OmdbBaseResponse {

    protected String totalSeasons;

    public String getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(String totalSeasons) {
        this.totalSeasons = totalSeasons;
    }
}
