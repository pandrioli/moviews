package digitalhouse.android.a0317moacns1c_02.Model.Misc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dh3 on 24/05/17.
 */

public class Company implements Parcelable{
    private Integer id;
    private String name;

    public Company()
    {

    }

    public Company(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    protected Company(Parcel in) {
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Company> CREATOR = new Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
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
}
