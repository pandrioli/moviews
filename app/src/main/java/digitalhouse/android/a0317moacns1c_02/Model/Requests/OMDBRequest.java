package digitalhouse.android.a0317moacns1c_02.Model.Requests;

import java.util.HashMap;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.OMDBClient;

/**
 * Created by Pablo on 21/06/2017.
 */

public class OMDBRequest {
    // por ahora es lo basico nomás, para buscar por id de IMDB
    private String imdbId;

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public HashMap<String, String> getQueryMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("apikey", OMDBClient.API_KEY);
        if (imdbId!=null) hashMap.put("i", imdbId);
        return hashMap;
    }

}
