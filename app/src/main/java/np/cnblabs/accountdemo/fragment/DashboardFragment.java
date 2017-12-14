package np.cnblabs.accountdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import np.cnblabs.accountdemo.R;

/**
 * Created by sanjogstha on 12/12/17.
 * Innovisto LLC
 * sanjogshrestha.nepal@gmail.com
 */

public class DashboardFragment extends Fragment{
    public static DashboardFragment newInstance(){
        return new DashboardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }
}
