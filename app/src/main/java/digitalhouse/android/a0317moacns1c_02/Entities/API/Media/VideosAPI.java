package digitalhouse.android.a0317moacns1c_02.Entities.API.Media;

import java.util.ArrayList;

/**
 * Created by Pablo on 25/05/2017.
 */

public class VideosAPI {
    private Integer id;
    private ArrayList<VideoItemAPI> results;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<VideoItemAPI> getResults() {
        return results;
    }

    public void setResults(ArrayList<VideoItemAPI> results) {
        this.results = results;
    }
}
