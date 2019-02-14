package com.fekrah.dolphin.client;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.CallSuper;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.fekrah.dolphin.R;
import com.fekrah.dolphin.client.activities.RegisterActivity;
import com.fekrah.dolphin.client.activities.VideoCapture;
import com.fekrah.dolphin.client.adapters.SpecializationsAdapter;
import com.fekrah.dolphin.client.fragments.EmergencyBookingClientFragment;
import com.fekrah.dolphin.client.fragments.NormalBookingClientFragment;
import com.fekrah.dolphin.client.fragments.SpecialBookingClientFragment;
import com.fekrah.dolphin.helper.SamplePresenter;
import com.fekrah.dolphin.helper.SharedHelper;
import com.fekrah.dolphin.models.FuckenResponse;
import com.fekrah.dolphin.models.RegisterResponse;
import com.fekrah.dolphin.models.Specialize;
import com.fekrah.dolphin.server.BaseClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.rafakob.drawme.DrawMeEditText;
import com.rafakob.drawme.DrawMeTextView;
import com.yalantis.ucrop.UCrop;
import com.yayandroid.locationmanager.base.LocationBaseActivity;
import com.yayandroid.locationmanager.configuration.Configurations;
import com.yayandroid.locationmanager.configuration.LocationConfiguration;
import com.yayandroid.locationmanager.constants.ProcessType;

import net.alhazmy13.mediapicker.Video.VideoPicker;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cafe.adriel.androidaudiorecorder.AndroidAudioRecorder;
import cafe.adriel.androidaudiorecorder.model.AudioChannel;
import cafe.adriel.androidaudiorecorder.model.AudioSampleRate;
import cafe.adriel.androidaudiorecorder.model.AudioSource;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookTechnicalClientActivity extends LocationBaseActivity implements OnMapReadyCallback,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks, SamplePresenter.SampleView {

    private static final int REQUEST_RECORD_AUDIO = 0;

    private static final String audioPath =
            Environment.getExternalStorageDirectory().getPath() + "/recorded_audio.mp3";

    public static Specialize specialize;
    int orderType = 1;

    @BindView(R.id.date)
    TextView dateTvv;

    @BindView(R.id.hours)
    EditText hourse;

    UCrop.Options options;
    byte[] profilebyte;
    private String REQUEST_FOR_PICTURE;
    private Bitmap thumbBitmap = null;
    private Uri imageUri;

    private SamplePresenter samplePresenter;
    private Location location;
    private GoogleApiClient mGoogleApiClient;
    ProgressDialog progressDialog;
    String string;
    private String TAG ="BookTechnicalClientActivity" ;

    @BindView(R.id.location)
    DrawMeEditText user_address;

    @BindView(R.id.details)
    DrawMeEditText details;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.first_view)
    View firstView;

    @BindView(R.id.second_view)
    View secondView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_technical_client);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ActivityCompat.requestPermissions(this,
                new String[]{
                        Manifest.permission.MODIFY_AUDIO_SETTINGS,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.CAPTURE_VIDEO_OUTPUT,
                        Manifest.permission.CAPTURE_AUDIO_OUTPUT,
                        Manifest.permission.CAPTURE_SECURE_VIDEO_OUTPUT
                },
                56);

        if (getIntent()!=null){
            Intent intent = getIntent();
            specialize = (Specialize) intent.getSerializableExtra(SpecializationsAdapter.SPECIALIZE_DATA);
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.gimming_location));
        samplePresenter = new SamplePresenter(this);
        buildGoogleApiClient();
        mGoogleApiClient.connect();
        // phoneInputLayout.setTextColor(R.color.white);
        options = new UCrop.Options();
        options.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        options.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

    }


    private void initMap() {

        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    protected synchronized void buildGoogleApiClient() {
        Log.i("buildGoogleApiClient", "Building GoogleApiClient");
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addConnectionCallbacks(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addApi(LocationServices.API)
                .enableAutoManage(this, this)
                .build();
    }

    @Override
    public String getText() {
        return string;
    }

    @Override
    public void setText(String text) {
        string = text;
    }

    @Override
    public void updateProgress(String text) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.setMessage(text);
        }
    }

    @Override
    public void dismissProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        getLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onProcessTypeChanged(@ProcessType int processType) {
        samplePresenter.onProcessTypeChanged(processType);
    }

    boolean changed = false;
    @Override
    public void onLocationChanged(Location location2) {
        if (!changed){

            changed  =true;
            location = location2;

            samplePresenter.onLocationChanged(location);
            initMap();
        }

    }

    void getName() {
        String errorMessage = "";

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;

        try {
            addresses = geocoder.getFromLocation(
                    location.getLatitude(),
                    location.getLongitude(),
                    // In this sample, get just a single address.
                    1);
        } catch (IOException ioException) {
            // Catch network or other I/O problems.
            errorMessage = getString(R.string.no_internet);
            Log.e(TAG, errorMessage, ioException);
        } catch (IllegalArgumentException illegalArgumentException) {
            // Catch invalid latitude or longitude values.
            errorMessage = getString(R.string.invalid_lat_long_used);
            Log.e(TAG, errorMessage + ". " +
                    "Latitude = " + location.getLatitude() +
                    ", Longitude = " +
                    location.getLongitude(), illegalArgumentException);
        }

        // Handle case where no address was found.
        if (addresses == null || addresses.size() == 0) {
            if (errorMessage.isEmpty()) {
                errorMessage = getString(R.string.no_address_found);
                Log.e(TAG, errorMessage);
            }
            // deliverResultToReceiver(Constants.FAILURE_RESULT, errorMessage);
        } else {
            Address address = addresses.get(0);
            // ArrayList<String> addressFragments = new ArrayList<String>();
         //   locationAdress = address.getAddressLine(0);
            user_address.setText(address.getAddressLine(0).toString());
            moveCameraCurrentLocation(new LatLng(location.getLatitude(),location.getLongitude()),address.getFeatureName());
//            // Fetch the address lines using getAddressLine,
//            // join them, and send them to the thread.
//            for(int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
//                addressFragments.add(address.getAddressLine(i));
//            }
//            Log.i(TAG, getString(R.string.address_found));
////            deliverResultToReceiver(Constants.SUCCESS_RESULT,
////                    TextUtils.join(System.getProperty("line.separator"),
////                            addressFragments));
        }
    }

    @Override
    public void onLocationFailed(int type) {
        samplePresenter.onLocationFailed(type);
    }

    @Override
    public LocationConfiguration getLocationConfiguration() {
        return Configurations.defaultConfiguration(getString(R.string.get_location_permistion),
                getString(R.string.gps_message));
    }

    @CallSuper
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        locationManager.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getLocationManager().isWaitingForLocation()
                && !getLocationManager().isAnyDialogShowing()) {
            displayProgress();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        dismissProgress();
    }

    private void displayProgress() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.getWindow().addFlags(Window.FEATURE_NO_TITLE);
            progressDialog.setMessage(getString(R.string.getting_locations));
        }

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (samplePresenter != null)
            samplePresenter.destroy();
    }

    private void initOrderType(){
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(this.getString(R.string.normal_reservation), NormalBookingClientFragment.class)
                .add(this.getString(R.string.emergency_reservation), EmergencyBookingClientFragment.class)
                .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
        viewPagerTab.setOnTabClickListener(new SmartTabLayout.OnTabClickListener() {
            @Override
            public void onTabClicked(int position) {
                orderType = position+1;
                Log.d("onTabClicked", "onTabClicked: "+orderType);
            }
        });

    }

    String filePath;
    @OnClick(R.id.spread)
    void record(){
        AndroidAudioRecorder.with(this)
                // Required
                .setFilePath(filePath)
                .setRequestCode(REQUEST_RECORD_AUDIO)
                .setFilePath(audioPath)
                // Optional
                .setSource(AudioSource.MIC)
                .setChannel(AudioChannel.STEREO)
                .setSampleRate(AudioSampleRate.HZ_48000)
                .setAutoStart(false)
                .setKeepDisplayOn(true)
                // Start recording
                .record();
    }

    String vedioPath;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        locationManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_RECORD_AUDIO) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, getString(R.string.recorded), Toast.LENGTH_SHORT).show();
            }

        }
        if (requestCode == VideoPicker.VIDEO_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> mPaths =  data.getStringArrayListExtra(VideoPicker.EXTRA_VIDEO_PATH);

            vedioPath = mPaths.get(0);
            Log.d("onActivityResult", "onActivityResult: "+mPaths.get(0));
        }

        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {

            Image image = ImagePicker.getFirstImageOrNull(data);

            Uri res_url = Uri.fromFile(new File((image.getPath())));
            //imageUri = image.getPath();
            CropImage(image, res_url);

        } else if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            //  if (resultUri!=null)
            assert resultUri != null;
            bitmapCompress(resultUri);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            thumbBitmap.compress(Bitmap.CompressFormat.JPEG, 75, byteArrayOutputStream);

            profilebyte = byteArrayOutputStream.toByteArray();


            imageUri = resultUri;

        }


    }

    private void CropImage(Image image, Uri res_url) {
        UCrop.of(res_url, Uri.fromFile(new File(this.getCacheDir(), image.getName())))
                .withOptions(options)
                .start(this);
    }

    private void bitmapCompress(Uri resultUri) {
        final File thumbFilepathUri = new File(resultUri.getPath());

        try {
            thumbBitmap = new Compressor(this)
                    .setQuality(50)
                    .compressToBitmap(thumbFilepathUri);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectDate(View view) {

        Calendar newCalendar = Calendar.getInstance();
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy ");

                // Toast.makeText(getApplicationContext(), ""+format.format(new java.util.Date(year, monthOfYear, dayOfMonth)), Toast.LENGTH_SHORT).show();
                dateTvv.setText(format.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH)).show();



    }
    public static java.util.Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    public void selectVideo(View view) {
        new VideoPicker.Builder(this)
                .mode(VideoPicker.Mode.CAMERA_AND_GALLERY)
                .directory(VideoPicker.Directory.DEFAULT)
                .extension(VideoPicker.Extension.MP4)
                .enableDebuggingMode(true)
                .build();
    }

    public void selectImage(View view) {

        ImagePicker.create(this)
                .limit(1)
                .theme(R.style.UCrop)
                .folderMode(false)
                .start();

    }

    MarkerOptions currentLocationMarkerOption;
    Marker currentLocationMarker;

    private void moveCameraCurrentLocation(LatLng latLng, String location) {

        currentLocationMarkerOption = new MarkerOptions()
                .position(latLng)
                .icon(bitmapDescriptorFromVector(this, R.drawable.ic_my_location_marker))
                .title(location);
        CameraPosition SENDBIS = CameraPosition.builder()
                .target(latLng)
                .zoom(17)
                .build();
        Log.d(TAG, "movCamera: move to latlang " + latLng.toString());

        currentLocationMarker = mMap.addMarker(currentLocationMarkerOption);
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(SENDBIS), 5000, null);
        //  mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
        // mMap.addMarker(new MarkerOptions().position(latLng));


    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, @DrawableRes int vectorDrawableResourceId) {
        Drawable background = ContextCompat.getDrawable(context, vectorDrawableResourceId);
        background.setBounds(0, 0, background.getIntrinsicWidth(), background.getIntrinsicHeight());
        // Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorDrawableResourceId);
        //vectorDrawable.setBounds(40, 20, vectorDrawable.getIntrinsicWidth() + 40, vectorDrawable.getIntrinsicHeight() + 20);
        Bitmap bitmap = Bitmap.createBitmap(background.getIntrinsicWidth(), background.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        background.draw(canvas);
        //vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


    GoogleMap mMap;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap= googleMap;
        getName();

    }

    public void sendOrder(View view) {
        if (location==null){
            Log.d("onResponse", "returb: ");
            Toast.makeText(this, "برجاء ادخال البيانات", Toast.LENGTH_SHORT).show();
            return;

        }else {
            Dialog dialog = new Dialog(this);
            dialog.show();

//            Log.d("onResponse", "start: ");
//            File imageFile = new File(imageUri.getPath());
//            RequestBody imageBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
//            MultipartBody.Part image = MultipartBody.Part.createFormData("order_image", String.valueOf(System.currentTimeMillis() + ".jpg"), imageBody);
//
//            File voiceFile = new File(audioPath);
//            RequestBody voiceBody = RequestBody.create(MediaType.parse("audio/*"), voiceFile);
//            MultipartBody.Part voice = MultipartBody.Part.createFormData("order_image", String.valueOf(System.currentTimeMillis() + ".jpg"), voiceBody);
//
//            File videoFile = new File(vedioPath);
//            RequestBody videoBody = RequestBody.create(MediaType.parse("video/*"), videoFile);
//            MultipartBody.Part video = MultipartBody.Part.createFormData("order_image", String.valueOf(System.currentTimeMillis() + ".jpg"), videoBody);


            BaseClient.getApi().bookATechnical(
//                    Integer.parseInt(SharedHelper.getKey(this,RegisterActivity.USER_ID)),
//                    Integer.parseInt(specialize.getId_services()),
                    orderType,
                    Integer.parseInt(hourse.getText().toString()),
                    details.getText().toString(),
                    user_address.getText().toString(),
                    dateTvv.getText().toString(),
                    String.valueOf( location.getLatitude()),
                    String.valueOf( location.getLongitude())
//                    image,
//                    voice,
//                    video
            ).enqueue(new Callback<FuckenResponse>() {
                @Override
                public void onResponse(Call<FuckenResponse> call, Response<FuckenResponse> response) {
                    dialog.dismiss();
                    done();

                }

                @Override
                public void onFailure(Call<FuckenResponse> call, Throwable t) {
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("onFailure", "onFailure: "+t.getMessage());
                }
            });

        }
    }

    private void done() {
        AlertDialog.Builder dialog1 = new AlertDialog.Builder(this);
        dialog1.setMessage("تم ارسال الطلب وسوف يتم الرد عليك قريبا شكرا لك");
        dialog1.setPositiveButton(getResources().getText(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        dialog1.show();
    }

    @BindView(R.id.emergency)
    TextView emergency;

    @BindView(R.id.normal)
    TextView normal;


    public void chang(View view) {
        if (view.getId()==R.id.normal){
            orderType=1;
            normal.setBackgroundResource(R.drawable.ic_reversation_type_bg);
            emergency.setBackgroundResource(R.drawable.ic_reversation_type_bg_kohly);

        }else {
            orderType=2;
            normal.setBackgroundResource(R.drawable.ic_reversation_type_bg_kohly);
            emergency.setBackgroundResource(R.drawable.ic_reversation_type_bg);

        }

    }

    @Override
    public void onBackPressed() {
        if (secondView.getVisibility()==View.VISIBLE){
            secondView.setVisibility(View.GONE);
            firstView.setVisibility(View.VISIBLE);
        }else
        super.onBackPressed();
    }

    public void next(View view) {
        secondView.setVisibility(View.VISIBLE);
        firstView.setVisibility(View.GONE);
    }
}
