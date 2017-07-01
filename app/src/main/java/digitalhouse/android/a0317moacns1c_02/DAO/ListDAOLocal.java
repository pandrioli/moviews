package digitalhouse.android.a0317moacns1c_02.DAO;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Model.DTO.ListDTO;
import digitalhouse.android.a0317moacns1c_02.Model.DTO.ListItemDTO;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Pablo on 27/06/2017.
 */

public class ListDAOLocal {
    private Realm realm;
    private ListDTO listDTO;
    public ListDAOLocal() {
        realm = Realm.getDefaultInstance();
    }

    public ListDTO obtainAppList(String id) {
        return obtainList(id, false);
    }

    public ListDTO obtainUserList(String id) {
        return obtainList(id, true);
    }

    public void saveItemToAppList(String id, ListItemDTO listItemDTO) {
        saveItemToList(id, listItemDTO, false);
    }
    public void saveItemToUserList(String id, ListItemDTO listItemDTO) {
        saveItemToList(id, listItemDTO, true);
    }

    public void saveAndReplaceAppList(final String id, final RealmList<ListItemDTO> list) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ListDTO listDTO = new ListDTO();
                listDTO.setId(id);
                listDTO.setList(list);
                realm.copyToRealmOrUpdate(listDTO);
            }
        });
    }


    public void removeItemFromList(String listId, Integer itemId, String type) {
        final ListDTO listDTO = realm.where(ListDTO.class).equalTo("id", listId).findFirst();
        ListItemDTO itemToRemove = null;
        for (ListItemDTO item : listDTO.getList()) {
            if (item.getId().equals(itemId) && item.getType().equals(type)) {
                itemToRemove = item;
                break;
            }
        }
        if (itemToRemove!=null) {
            realm.beginTransaction();
            listDTO.getList().remove(itemToRemove);
            realm.commitTransaction();
        }
    }

    public List<ListDTO> obtainAllUserLists() {
        RealmResults<ListDTO> results = realm.where(ListDTO.class).equalTo("isUserList", true).findAll();
        return results;
    }

    // private methods

    private void saveItemToList(String id, final ListItemDTO listItemDTO, Boolean isUserList) {
        final ListDTO listDTO = obtainList(id, isUserList);
        if (!listDTO.getList().contains(listItemDTO)) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    listDTO.getList().add(listItemDTO);
                }
            });
        }
    }

    private ListDTO obtainList(String id, Boolean isUserList) {
        listDTO = realm.where(ListDTO.class).equalTo("id", id).findFirst();
        if (listDTO==null) {
            listDTO = new ListDTO();
            listDTO.setId(id);
            listDTO.setIsUserList(isUserList);
            listDTO.setList(new RealmList<ListItemDTO>());
            realm.beginTransaction();
            listDTO = realm.copyToRealmOrUpdate(listDTO);
            realm.commitTransaction();
        }
        return listDTO;
    }
}
