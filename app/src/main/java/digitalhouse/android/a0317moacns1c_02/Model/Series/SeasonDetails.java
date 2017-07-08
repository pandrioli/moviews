package digitalhouse.android.a0317moacns1c_02.Model.Series;

import java.io.Serializable;
import java.util.List;

/**
 * Created by forev on 18-Jun-17.
 */

public class SeasonDetails extends SeasonResult implements Serializable {
    protected Integer serieId;
    protected String _id;
    protected String name;
    protected String overview;
    protected List<EpisodeDetails> episodes;

    public Integer getSerieId() {
        return serieId;
    }

    public void setSerieId(Integer serieId) {
        this.serieId = serieId;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<EpisodeDetails> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<EpisodeDetails> episodes) {
        this.episodes = episodes;
    }
}
