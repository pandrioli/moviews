package digitalhouse.android.a0317moacns1c_02.Mappers;

import digitalhouse.android.a0317moacns1c_02.Model.DTO.SeasonDTO;
import digitalhouse.android.a0317moacns1c_02.Model.Series.Season;
import digitalhouse.android.a0317moacns1c_02.Model.Series.SeasonDetails;

/**
 * Created by Pablo on 08/07/2017.
 */

public class DTOSeasonMapper {
    public static SeasonDTO map(SeasonDetails season) {
        SeasonDTO seasonDTO = new SeasonDTO();
        seasonDTO.setId(season.get_id());
        seasonDTO.setSerieId(season.getSerieId());
        seasonDTO.setAirDate(season.getAirDate());
        seasonDTO.setEpisodeCount(season.getEpisodeCount());
        seasonDTO.setPosterPath(season.getPosterPath());
        seasonDTO.setSeasonNumber(season.getSeasonNumber());
        seasonDTO.setName(season.getName());
        seasonDTO.setOverview(season.getOverview());
        seasonDTO.setEpisodes(DTOEpisodeMapper.map(season.getEpisodes()));
        return seasonDTO;
    }
    public static SeasonDetails map(SeasonDTO seasonDTO) {
        SeasonDetails season = new SeasonDetails();
        season.set_id(seasonDTO.getId());
        season.setSerieId(seasonDTO.getSerieId());
        season.setAirDate(seasonDTO.getAirDate());
        season.setEpisodeCount(seasonDTO.getEpisodeCount());
        season.setPosterPath(seasonDTO.getPosterPath());
        season.setSeasonNumber(seasonDTO.getSeasonNumber());
        season.setName(seasonDTO.getName());
        season.setOverview(seasonDTO.getOverview());
        season.setEpisodes(DTOEpisodeMapper.map(seasonDTO.getEpisodes()));
        return season;
    }
}
