package digitalhouse.android.a0317moacns1c_02.Entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Pablo on 26/05/2017.
 */

public class PersonListItem implements Parcelable {
    private Integer id;
    private String name;
    private String role;
    private String profileURL;

    public PersonListItem() {

    }

    protected PersonListItem(Parcel in) {
        id = in.readInt();
        name = in.readString();
        role = in.readString();
        profileURL = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(role);
        dest.writeString(profileURL);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PersonListItem> CREATOR = new Creator<PersonListItem>() {
        @Override
        public PersonListItem createFromParcel(Parcel in) {
            return new PersonListItem(in);
        }

        @Override
        public PersonListItem[] newArray(int size) {
            return new PersonListItem[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }

}
