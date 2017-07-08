package digitalhouse.android.a0317moacns1c_02.Mappers;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Model.DTO.EpisodeDTO;
import digitalhouse.android.a0317moacns1c_02.Model.Series.EpisodeDetails;
import io.realm.RealmList;

/**
 * Created by Pablo on 08/07/2017.
 */

public class DTOEpisodeMapper {
    public static EpisodeDTO map(EpisodeDetails episode) {
        EpisodeDTO episodeDTO = new EpisodeDTO();
        episodeDTO.setId(episode.getId());
        episodeDTO.setEpisodeNumber(episode.getEpisodeNumber());
        episodeDTO.setName(episode.getName());
        episodeDTO.setOverview(episode.getOverview());
        episodeDTO.setAirDate(episode.getAirDate());
        episodeDTO.setSeasonNumber(episode.getSeasonNumber());
        episodeDTO.setStillPath(episode.getStillPath());
        return episodeDTO;
    }
    public static EpisodeDetails map(EpisodeDTO episodeDTO) {
        EpisodeDetails episode = new EpisodeDetails();
        episode.setId(episodeDTO.getId());
        episode.setEpisodeNumber(episodeDTO.getEpisodeNumber());
        episode.setName(episodeDTO.getName());
        episode.setOverview(episodeDTO.getOverview());
        episode.setAirDate(episodeDTO.getAirDate());
        episode.setSeasonNumber(episodeDTO.getSeasonNumber());
        episode.setStillPath(episodeDTO.getStillPath());
        return episode;
    }

    public static RealmList<EpisodeDTO> map(List<EpisodeDetails> episodes) {
        RealmList<EpisodeDTO> episodeDTOs = new RealmList<>();
        for (EpisodeDetails episode : episodes) {
            episodeDTOs.add(map(episode));
        }
        return episodeDTOs;
    }

    public static List<EpisodeDetails> map(RealmList<EpisodeDTO> episodeDTOs) {
        List<EpisodeDetails> episodes = new ArrayList<>();
        for (EpisodeDTO episodeDTO : episodeDTOs) {
            episodes.add(map(episodeDTO));
        }
        return episodes;
    }

}
