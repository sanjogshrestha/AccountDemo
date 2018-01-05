package np.cnblabs.accountdemo;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import np.cnblabs.accountdemo.realm.UserModelData;
import np.cnblabs.accountdemo.utils.CircleTransform;

public class NavigationActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.webView) WebView webView;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    TextView userNameTV;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);
        realm = DemoApplication.getDefaultRealm();
        setSupportActionBar(toolbar);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.leapfrog.academy/");

        setUpNavigationHeader();
        setUpNavigationDrawer();
    }

    class WebViewClient extends android.webkit.WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(String.valueOf(request.getUrl().toString()));
            return true;
        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    private void setUpNavigationHeader() {
        UserModelData userModelData = realm.where(UserModelData.class).findFirst();
        if(userModelData == null || !userModelData.isValid()) logout();

        View header = navigationView.getHeaderView(0);
        userNameTV = header.findViewById(R.id.userNameTV);
        ImageView userProfileIV = header.findViewById(R.id.userProfileIV);
        Picasso.with(this)
                .load(R.drawable.kp)
                .transform(new CircleTransform())
                .into(userProfileIV);

        if(userModelData == null || !userModelData.isValid()) {
            logout();
            return;
        }

        userNameTV.setText(userModelData.getEmail());
    }

    private void setUpNavigationDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void logout() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                realm.deleteAll();
                finish();
                startActivity(new Intent(NavigationActivity.this, Login.class));
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "You have clicked setting", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.listView:
                startActivity(this, ListViewArrayAdapter.class);
                break;

            case R.id.recyclerView:
                startActivity(this, RecyclerActivity.class);
                break;

            case R.id.async:
                startActivity(new Intent(this, DownloadImageActivity.class));
                break;

            case R.id.json:
                startActivity(new Intent(this, JsonAPI.class));
                break;

            case R.id.retrofit:
                startActivity(new Intent(this, RetrofitActivity.class));
                break;

            case R.id.realm:
                startActivity(this, RealmActivity.class);
                break;

            case R.id.spinner:
                startActivity(this, SpinnerActivity.class);
                break;

            case R.id.picasso:
                startActivity(this, PicassoImage.class);
                break;

            case R.id.viewPager:
                startActivity(this, ViewPagerActivity.class);
                break;

            case R.id.logout:
                logout();
                break;

            case R.id.maps:
                startActivity(this, MapsActivity.class);
                break;

            case R.id.camera:
                startActivity(this, CameraActivity.class);
                break;

            case R.id.linkify:
                startActivity(this, LinkifyActivity.class);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
