package digitalhouse.android.a0317moacns1c_02.Model.Credits;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

import digitalhouse.android.a0317moacns1c_02.Helpers.ImageHelper;
import digitalhouse.android.a0317moacns1c_02.Model.ListItems.ImageListItem;

/**
 * Created by Pablo on 25/05/2017.
 */

public class Credits implements Serializable {
    private Integer id;
    private ArrayList<Cast> cast;
    private ArrayList<Crew> crew;

    public Credits(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<Cast> getCast() {
        return cast;
    }

    public void setCast(ArrayList<Cast> cast) {
        this.cast = cast;
    }

    public ArrayList<Crew> getCrew() {
        return crew;
    }

    public void setCrew(ArrayList<Crew> crew) {
        this.crew = crew;
    }

    public ArrayList<ImageListItem> getImageListItems(){
        ArrayList<ImageListItem> imageList = new ArrayList<>();
        for (Cast castPerson : cast) {
            ImageListItem imageListItem = new ImageListItem();
            imageListItem.setId(castPerson.getId());
            imageListItem.setTitle(castPerson.getName());
            imageListItem.setSubtitle(castPerson.getCharacter());
            String url = ImageHelper.getProfileURL(castPerson.getProfile_path(), 1);
            imageListItem.setImageURL(url);
            imageList.add(imageListItem);
        }
        return imageList;
    }

}
