package digitalhouse.android.a0317moacns1c_02.DAO.Credits;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Pablo on 25/05/2017.
 */

public class Credits implements Parcelable {
    private Integer id;
    private ArrayList<Cast> cast;
    private ArrayList<Crew> crew;

    protected Credits(Parcel in) {
        cast = in.createTypedArrayList(Cast.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(cast);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Credits> CREATOR = new Creator<Credits>() {
        @Override
        public Credits createFromParcel(Parcel in) {
            return new Credits(in);
        }

        @Override
        public Credits[] newArray(int size) {
            return new Credits[size];
        }
    };

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
}
