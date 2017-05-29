package digitalhouse.android.a0317moacns1c_02.Entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Pablo on 29/05/2017.
 */

public class CrewListItem implements Parcelable {
    private Integer id;
    private String name;
    private String department;
    private String job;

    public CrewListItem() {

    }

    protected CrewListItem(Parcel in) {
        name = in.readString();
        department = in.readString();
        job = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(department);
        dest.writeString(job);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CrewListItem> CREATOR = new Creator<CrewListItem>() {
        @Override
        public CrewListItem createFromParcel(Parcel in) {
            return new CrewListItem(in);
        }

        @Override
        public CrewListItem[] newArray(int size) {
            return new CrewListItem[size];
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
