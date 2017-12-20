package np.cnblabs.accountdemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.yalantis.ucrop.UCrop;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import np.cnblabs.accountdemo.utils.Util;

/**
 * Created by sanjogstha on 12/19/17.
 * Innovisto LLC
 * sanjogshrestha.nepal@gmail.com
 */

public class CameraActivity extends AppCompatActivity{
    @BindView(R.id.image) ImageView image;

    public static final int CAMERA = 777;
    public static final int GALLERY = 888;
    Uri cameraUri = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
    }

    public void openCamera(View view) {
        checkImageViewIfNull();

        if(!Util.checkCameraHardware(this)){
            Toast.makeText(this, " No camera found", Toast.LENGTH_SHORT).show();
            return;
        }

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE} , CAMERA);
        }else takePicture();
    }

    private void checkImageViewIfNull() {
        if(image.getDrawable() != null){
            cameraUri = null;
            image.setImageDrawable(null);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case CAMERA:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED
                        && grantResults[2] == PackageManager.PERMISSION_GRANTED){
                    takePicture();
                }else{
                    Toast.makeText(this, R.string.permission_required, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void takePicture() {
        Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String imageName = "LFA_IMG_" + System.currentTimeMillis() + "_";
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), imageName);
        cameraUri = Uri.fromFile(file);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            openCamera.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            cameraUri = FileProvider.getUriForFile(this, getPackageName() + ".provider", file);
        }

        openCamera.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
        startActivityForResult(openCamera, CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CAMERA:
                if(resultCode == RESULT_OK){
                    //Perform setImage here
                    startCropActivity(cameraUri);
                }
                break;

            case GALLERY:
                if(resultCode == RESULT_OK){
                    //Perform setImage here
                    if(data.getData() != null)
                        startCropActivity(data.getData());
                }
                break;

            case UCrop.REQUEST_CROP:
                if(resultCode == RESULT_OK){
                    //Perform setImage here
                    setImage(UCrop.getOutput(data));
                }
                break;
        }
    }

    private void startCropActivity(@NonNull Uri uri) {
        UCrop uCrop = UCrop.of(uri, Uri.fromFile(new File(this.getFilesDir().getAbsolutePath(), "temp_image")));
        uCrop.start(this);
    }

    private void setImage(Uri cameraUri) {
        if(cameraUri != null) image.setImageURI(cameraUri);
    }

    public void openGallery(View view) {
        checkImageViewIfNull();

        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(galleryIntent, GALLERY);
    }
}
