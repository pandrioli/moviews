package digitalhouse.android.a0317moacns1c_02.Model.DTO;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Pablo on 08/07/2017.
 */

public class SeasonDTO extends RealmObject {
    @PrimaryKey
    private String id;
    private Integer serieId;
    private String airDate;
    private Integer episodeCount;
    private String posterPath;
    private Integer seasonNumber;
    private String name;
    private String overview;
    private RealmList<EpisodeDTO> episodes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSerieId() {
        return serieId;
    }

    public void setSerieId(Integer serieId) {
        this.serieId = serieId;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public Integer getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(Integer episodeCount) {
        this.episodeCount = episodeCount;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
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

    public RealmList<EpisodeDTO> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(RealmList<EpisodeDTO> episodes) {
        this.episodes = episodes;
    }
}
