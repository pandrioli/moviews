package digitalhouse.android.a0317moacns1c_02.Model.General;

import java.io.Serializable;

/**
 * Created by forev on 18-Jun-17.
 */

public class ExternalIDs implements Serializable {
    String imdb_id;
    protected Integer tvdb_id;

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }



    public Integer getTvdb_id() {
        return tvdb_id;
    }

    public void setTvdb_id(Integer tvdb_id) {
        this.tvdb_id = tvdb_id;
    }
}
