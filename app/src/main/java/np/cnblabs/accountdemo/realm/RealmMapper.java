package np.cnblabs.accountdemo.realm;

import android.support.annotation.NonNull;

import io.realm.Realm;
import np.cnblabs.accountdemo.model.RealmModel;

/**
 * Created by sanjogstha on 11/26/17.
 * Innovisto LLC
 * sanjogshrestha.nepal@gmail.com
 */

public class RealmMapper {
    public static RealmModelData toRealm(@NonNull RealmModel realmModel, Realm realm){

        RealmModelData realmModelData = realm.where(RealmModelData.class)
                .equalTo(RealmModelData.EMAIL_ID, realmModel.getEmail())
                .findFirst();

        if(realmModelData == null){
            realmModelData = realm.createObject(RealmModelData.class, realmModel.getEmail());
        }

        realmModelData.setName(realmModel.getName());
        return realmModelData;
    }

    public static RealmModel fromRealm(@NonNull RealmModelData realmModelData){
        return new RealmModel(realmModelData.getName(), realmModelData.getEmail());
    }
}
