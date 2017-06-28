package digitalhouse.android.a0317moacns1c_02.Mappers;

import digitalhouse.android.a0317moacns1c_02.Model.Credits.Cast;
import digitalhouse.android.a0317moacns1c_02.Model.Credits.Crew;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.CastDTO;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.CrewDTO;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.GenreDTO;
import digitalhouse.android.a0317moacns1c_02.Model.Genres.Genre;

/**
 * Created by Pablo on 27/06/2017.
 */

public class DTOGeneralMapper {

    //GENERAL DTO TO POJO MAPPERS
    public static Genre map(GenreDTO genreDTO) {
        Genre genre = new Genre();
        genre.setId(genreDTO.getId());
        genre.setName(genreDTO.getName());
        return genre;
    }
    public static Cast map(CastDTO castDTO) {
        Cast cast = new Cast();
        cast.setId(castDTO.getId());
        cast.setName(castDTO.getName());
        cast.setCharacter(castDTO.getCharacter());
        cast.setProfile_path(castDTO.getProfilePath());
        cast.setOrder(castDTO.getOrder());
        return cast;
    }
    public static Crew map(CrewDTO crewDTO) {
        Crew crew = new Crew();
        crew.setId(crewDTO.getId());
        crew.setDepartment(crewDTO.getDepartment());
        crew.setJob(crewDTO.getJob());
        crew.setName(crewDTO.getName());
        crew.setProfile_path(crewDTO.getProfilePath());
        return crew;
    }

    //GENERAL POJO TO DTO MAPPERS
    public static GenreDTO map(Genre genre) {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(genre.getId());
        genreDTO.setName(genre.getName());
        return genreDTO;
    }
    public static CastDTO map(Cast cast) {
        CastDTO castDTO = new CastDTO();
        castDTO.setId(cast.getId());
        castDTO.setName(cast.getName());
        castDTO.setCharacter(cast.getCharacter());
        castDTO.setProfilePath(cast.getProfile_path());
        castDTO.setOrder(cast.getOrder());
        return castDTO;
    }
    public static CrewDTO map(Crew crew) {
        CrewDTO crewDTO = new CrewDTO();
        crewDTO.setId(crew.getId());
        crewDTO.setDepartment(crew.getDepartment());
        crewDTO.setJob(crew.getJob());
        crewDTO.setName(crew.getName());
        crewDTO.setProfilePath(crew.getProfile_path());
        return crewDTO;
    }




}
