package digitalhouse.android.a0317moacns1c_02.Model.ListItems;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import java.io.Serializable;

/**
 * Created by Pablo on 26/05/2017.
 */

public class ImageListItem implements View.OnClickListener, Serializable {
    private Integer id;
    private String title;
    private String subtitle;
    private String imageURL;
    private String imagePath;

    public ImageListItem() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public void onClick(View v) {

    }
}
