package digitalhouse.android.a0317moacns1c_02.Mappers;

import java.util.ArrayList;

import digitalhouse.android.a0317moacns1c_02.Model.DTO.RealmString;
import io.realm.RealmList;

/**
 * Created by Pablo on 04/07/2017.
 */

public class RealmStringMapper {
    public static ArrayList<String> map(RealmList<RealmString> realmStrings) {
        ArrayList<String> strings = new ArrayList<>();
        for (RealmString realmString : realmStrings) {
            strings.add(realmString.getValue());
        }
        return strings;
    }
    public static RealmList<RealmString> map(ArrayList<String> strings) {
        RealmList<RealmString> realmStrings = new RealmList<>();
        for (String string : strings) {
            realmStrings.add(new RealmString(string));
        }
        return realmStrings;
    }
}
