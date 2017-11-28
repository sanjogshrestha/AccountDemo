package np.cnblabs.accountdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import np.cnblabs.accountdemo.adapter.CustomRealmAdapter;
import np.cnblabs.accountdemo.model.RealmModel;
import np.cnblabs.accountdemo.realm.RealmMapper;
import np.cnblabs.accountdemo.realm.RealmModelData;

/**
 * Created by sanjogstha on 11/26/17.
 * Innovisto LLC
 * sanjogshrestha.nepal@gmail.com
 */

public class RealmActivity extends BaseActivity {

    @BindView(R.id.listView)
    ListView listView;

    @BindView(R.id.name_editText)
    EditText name_editText;

    @BindView(R.id.email_editText)
    EditText email_editText;

    @BindView(R.id.name_textInputLayout)
    TextInputLayout name_textInputLayout;

    @BindView(R.id.email_textInputLayout)
    TextInputLayout email_textInputLayout;

    private String email, name;
    private Realm realm;
    private List<RealmModelData> realmModelDataList;
    private CustomRealmAdapter customRealmAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);
        ButterKnife.bind(this);
        realm = DemoApplication.getDefaultRealm();
        initAdapter();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(@NonNull Realm realm) {
                        RealmModelData realmModelData = realmModelDataList.get(i);
                        realmModelData.deleteFromRealm();
                        Toast.makeText(RealmActivity.this, "Deleted Successfully",
                                Toast.LENGTH_SHORT).show();
                        customRealmAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private void initAdapter() {
        realmModelDataList = fetchData();
        customRealmAdapter = new CustomRealmAdapter(realmModelDataList, this);
        listView.setAdapter(customRealmAdapter);
    }

    private List<RealmModelData> fetchData() {
        return realm.where(RealmModelData.class)
                .beginsWith(RealmModelData.NAME,"sa")
                .findAll();
    }

    public void addToList(View view) {
        if(!validateName()) return;
        if(!validateEmail()) return;

        System.out.println("count="+ realm.where(RealmModelData.class).count());
        insertIntoRealm();

        realmModelDataList = fetchData();
        customRealmAdapter.notifyDataSetChanged();
    }

    private void insertIntoRealm() {
        final RealmModel realmModel = new RealmModel(name, email);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull  Realm realm) {
                RealmMapper.toRealm(realmModel, realm);
            }
        });
    }

    private boolean validateEmail() {
        email = email_editText.getText().toString();
        if(email.isEmpty()){
            email_textInputLayout.setErrorEnabled(true);
            email_textInputLayout.setError(getString(R.string.empty_field));
            return false;
        }else {
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                email_textInputLayout.setErrorEnabled(true);
                email_textInputLayout.setError(getString(R.string.invalid_email));
                return false;
            }
            email_textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateName() {
        name = name_editText.getText().toString();
        if(name.isEmpty()){
            name_textInputLayout.setError(getString(R.string.empty_field));
            name_textInputLayout.setErrorEnabled(true);
            return false;
        }else{
            name_textInputLayout.setErrorEnabled(false);
        }
        return true;
    }
}
