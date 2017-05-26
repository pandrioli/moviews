package digitalhouse.android.a0317moacns1c_02.Entities.API.Misc;

import java.io.Serializable;

/**
 * Created by dh3 on 24/05/17.
 */

public class CompanyAPI implements Serializable {
    private Integer id;
    private String name;

    public CompanyAPI()
    {

    }

    public CompanyAPI(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

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
