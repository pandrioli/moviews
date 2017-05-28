package digitalhouse.android.a0317moacns1c_02.Services;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.PersonCalls;
import digitalhouse.android.a0317moacns1c_02.APIs.TMDB.TMDBClient;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Media.ImageItemAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Person.PersonDetailsAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.API.Person.PersonImagesAPI;
import digitalhouse.android.a0317moacns1c_02.Entities.ImageData;
import digitalhouse.android.a0317moacns1c_02.Entities.PersonData;
import digitalhouse.android.a0317moacns1c_02.Helpers.DateHelper;

/**
 * Created by Pablo on 27/05/2017.
 */

public class PersonService {
    private static PersonService instance;
    private TMDBClient client;
    public PersonService() {
        this.client = ServiceGenerator.createService(TMDBClient.class);
    }
    public static PersonService getInstance() {
        if (instance == null) {
            instance = new PersonService();
        }
        return instance;
    }


    public void getPersonDetails(Integer id, final TMDBClient.APICallback callback) {
        PersonCalls.obtainDetails(id.toString(), client, new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                PersonDetailsAPI personAPI = (PersonDetailsAPI) result;
                PersonData person = new PersonData();
                person.setId(personAPI.getId());
                person.setImdbId(personAPI.getImdb_id());
                person.setName(personAPI.getName());
                person.setBirthday(DateHelper.parse(personAPI.getBirthday(), DateHelper.FORMAT_API));
                person.setDeathday(DateHelper.parse(personAPI.getDeathday(), DateHelper.FORMAT_API));
                person.setPlaceOfBirth(personAPI.getPlace_of_birth());
                person.setBiography(personAPI.getBiography());
                person.setPopularity(personAPI.getPopularity());
                person.setProfilePath(personAPI.getProfile_path());
                callback.onSuccess(person);
            }
        });
    }

    // Obtiene las imágenes de una persona por id
    // El callback devuelve ArrayList<ImageData>
    public void getPersonImages(Integer id, final TMDBClient.APICallback callback) {
        PersonCalls.obtainImages(id.toString(), client, new TMDBClient.APICallback() {
            @Override
            public void onSuccess(Object result) {
                PersonImagesAPI peopleImagesAPI = (PersonImagesAPI) result;
                List<ImageData> imageList = new ArrayList<>();
                for (ImageItemAPI imgAPI : peopleImagesAPI.getProfiles()) {
                    ImageData imgData = new ImageData();
                    imgData.setFilePath(imgAPI.getFile_path());
                    imgData.setWidth(imgAPI.getWidth());
                    imgData.setHeight(imgAPI.getHeight());
                    imgData.setAspectRatio(imgAPI.getAspect_ratio());
                    imageList.add(imgData);
                }
                callback.onSuccess(imageList);
            }
        });
    }

}
