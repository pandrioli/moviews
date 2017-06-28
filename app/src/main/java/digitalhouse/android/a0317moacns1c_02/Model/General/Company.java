package digitalhouse.android.a0317moacns1c_02.Model.General;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dh3 on 24/05/17.
 */

public class Company implements Serializable {
    private Integer id;
    private String name;

    public Company()
    {

    }

    public Company(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getCompaniesInString(List<Company> companyList, String separator){
        String companies = "";
        if (!companyList.isEmpty()) {
            companies = companyList.get(0).getName();
            for (int i=1; i<companyList.size(); i++) {
                companies += separator + companyList.get(i).getName();
            }
        }
        return companies;
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
