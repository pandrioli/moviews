package digitalhouse.android.a0317moacns1c_02.Model.DTO;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Pablo on 26/06/2017.
 */

public class CrewDTO extends RealmObject {
    @PrimaryKey
    private Integer id;
    private String department;
    private String job;
    private String name;
    private String profilePath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
}
