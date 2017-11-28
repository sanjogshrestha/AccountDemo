package np.cnblabs.accountdemo.realm;

import android.support.annotation.NonNull;

import io.realm.Realm;
import np.cnblabs.accountdemo.model.RealmModel;
import np.cnblabs.accountdemo.model.SignUpModel;
import np.cnblabs.accountdemo.model.UserModel;

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

    public static SignUpModelData toRealm(@NonNull SignUpModel signUpModel,
                                          Realm realm){
        SignUpModelData signUpModelData = realm.where(SignUpModelData.class)
                .equalTo(SignUpModelData.EMAIL_ID, signUpModel.getEmail())
                .findFirst();

        if(signUpModelData == null){
            signUpModelData = realm.createObject(SignUpModelData.class, signUpModel.getEmail());
        }

        signUpModelData.setFirstName(signUpModel.getFirstName());
        signUpModelData.setLastName(signUpModel.getLastName());
        signUpModelData.setGender(signUpModel.getGender());
        signUpModelData.setPassword(signUpModel.getPassword());

        return signUpModelData;
    }

    public static SignUpModel fromRealm(SignUpModelData signUpModelData){
        return new SignUpModel(signUpModelData.getEmail(),
                signUpModelData.getFirstName(), signUpModelData.getLastName(),
                signUpModelData.getPassword(), signUpModelData.getGender());
    }

    public static UserModelData toRealm(@NonNull UserModel userModel,
                                          Realm realm){
        UserModelData userModelData = realm.where(UserModelData.class)
                .equalTo(SignUpModelData.EMAIL_ID, userModel.getEmail())
                .findFirst();

        if(userModelData == null){
            userModelData = realm.createObject(UserModelData.class, userModel.getEmail());
        }

        userModelData.setFirstName(userModel.getFirstName());
        userModelData.setLastName(userModel.getLastName());
        userModelData.setGender(userModel.getGender());

        return userModelData;
    }

    public static UserModel fromRealm(UserModelData userModelData){
        return new UserModel(userModelData.getEmail(),
                userModelData.getFirstName(), userModelData.getLastName(),
                userModelData.getPassword(), userModelData.getGender());
    }
}
