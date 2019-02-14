package com.fekrah.dolphin.client.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.facebook.drawee.view.SimpleDraweeView;
import com.fekrah.dolphin.R;
import com.fekrah.dolphin.client.ClientHomeActivity;
import com.fekrah.dolphin.helper.SamplePresenter;
import com.fekrah.dolphin.helper.SharedHelper;
import com.fekrah.dolphin.models.Countries;
import com.fekrah.dolphin.models.FuckenResponse;
import com.fekrah.dolphin.models.RegisterResponse;
import com.fekrah.dolphin.server.Apis;
import com.fekrah.dolphin.server.BaseClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.yalantis.ucrop.UCrop;
import com.yayandroid.locationmanager.base.LocationBaseActivity;
import com.yayandroid.locationmanager.configuration.Configurations;
import com.yayandroid.locationmanager.configuration.LocationConfiguration;
import com.yayandroid.locationmanager.constants.ProcessType;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.fekrah.dolphin.client.activities.ClientLoginActivity.IS_LOGIN_TYPE;

public class RegisterActivity extends LocationBaseActivity implements
        GoogleApiClient.OnConnectionFailedListener, NavigationView.OnNavigationItemSelectedListener,
        GoogleApiClient.ConnectionCallbacks, SamplePresenter.SampleView {

    public static final String USER_NAME = "user_name";
    public static final String IS_LOGIN = "LOGIN";
    public static final String USER_ID = "user_id";
    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String SECOND_NAME = "SECOND_NAME";
    public static final String EMAIL = "EMAIL";
    public static final String PHONE = "PHONE";
    public static final String CITY = "CITY";
    public static final String ADDRESS = "DESTRICT";
    public static final String IMAGE = "IMAGE";
    public static final String TOAKEN = "TOAKEN";
    public static final String ACTIVE = "ACTIVR";

    public static final int PLACE_PICKER_REQUEST = 3;
    public static final int MY_PERMISSION = 34;
    private static final String PROFILE_IMAGE_REQUEST = "PROFILE_IMAGE_REQUEST";
    private static final String TAG = "MainActivityDriver";
    public static String CITY_ID = "";
    public static String COUNTRY_ID = "";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.password)
    EditText pass;
    @BindView(R.id.phone)
    EditText phone;

    @BindView(R.id.user_address)
    EditText user_address;

    @BindView(R.id.choose_image)
    FrameLayout choose_image;

    @BindView(R.id.profile_image)
    SimpleDraweeView profile_image;

    SimpleDraweeView image;
    UCrop.Options options;
    byte[] profilebyte;
    ProgressDialog progressDialog;
    ProgressDialog progressCreate;
    @BindView(R.id.city_spinner)

    Spinner citiesSpinner;
    @BindView(R.id.countries_spinner)

    Spinner countriesSpinner;
    List<String> cities = new ArrayList<>();
    List<String> citiesId = new ArrayList<>();
    List<String> countries = new ArrayList<>();
    List<String> countriesId = new ArrayList<>();
    String string;
    private SamplePresenter samplePresenter;
    private String locationAdress;
    private String REQUEST_FOR_PICTURE;
    private Bitmap thumbBitmap = null;
    private Uri imageUri;
    private List<Countries.City> citiesBody = new ArrayList<>();
    private List<Countries> countriesList = new ArrayList<>();
    private Location location;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.gimming_location));
        progressCreate= new ProgressDialog(this);
        progressCreate.setMessage(getString(R.string.creating_account));

        samplePresenter = new SamplePresenter(this);
        buildGoogleApiClient();
        mGoogleApiClient.connect();
        // phoneInputLayout.setTextColor(R.color.white);
        options = new UCrop.Options();
        options.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        options.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        getcities();

        choose_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                REQUEST_FOR_PICTURE = PROFILE_IMAGE_REQUEST;
                ImagePicker.create(RegisterActivity.this)
                        .limit(1)
                        .theme(R.style.UCrop)
                        .folderMode(true)
                        .start();
            }
        });

    }

    @OnClick(R.id.signBtn)
    void creatAccount() {
        progressCreate.show();
        if (
                name.getText().equals("") ||
                        pass.getText().equals("") ||
                        email.getText().equals("")||
                        imageUri==null
        ) {
            progressCreate.dismiss();
            Toast.makeText(this, getString(R.string.fill_all), Toast.LENGTH_LONG).show();
            return;
        } else {

            if (location == null) {
                progressCreate.dismiss();
                Toast.makeText(this, getString(R.string.no_address_found), Toast.LENGTH_SHORT).show();
                return;
            }

            Apis apis = BaseClient.getBaseClient().create(Apis.class);
            File file2 = new File(imageUri.getPath());
            RequestBody surveyBody = RequestBody.create(MediaType.parse("image/*"), file2);
            MultipartBody.Part image = MultipartBody.Part.createFormData("user_photo", String.valueOf(System.currentTimeMillis() + ".jpg"), surveyBody);

            apis.registerUser(
                    1,
                    name.getText().toString(),
                    pass.getText().toString(),
                    phone.getText().toString(),
                    email.getText().toString(),
                    String.valueOf(location.getLatitude()),
                    String.valueOf(location.getLongitude()),
                    CITY_ID,
                    phone.getText().toString(),
                    COUNTRY_ID,
                    user_address.getText().toString(),
                    1
                   , image

            ).enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                    Log.d("getUser_type", "getUser_type  " + response.body().getSuccess_signup());
                    if (response.body().getSuccess_signup()==1){
                        RegisterResponse user = response.body();
                        SharedHelper.putKey(getApplicationContext(), IS_LOGIN, "yes");
                        SharedHelper.putKey(getApplicationContext(), IS_LOGIN_TYPE, "عميل");
                        SharedHelper.putKey(getApplicationContext(), CITY, user.getAr_city_title());
                        SharedHelper.putKey(getApplicationContext(), USER_NAME, user.getUser_full_name());
                        SharedHelper.putKey(getApplicationContext(), ADDRESS, user.getUser_address());
                        SharedHelper.putKey(getApplicationContext(), EMAIL, user.getUser_email());
                        SharedHelper.putKey(getApplicationContext(), IMAGE, user.getUser_photo());
                        SharedHelper.putKey(getApplicationContext(), TOAKEN, user.getUser_token_id());
                        SharedHelper.putKey(getApplicationContext(), PHONE, user.getUser_photo());
                        SharedHelper.putKey(getApplicationContext(), USER_ID, user.getUser_id());
                        Log.d("llllll", "getUser_type  " + user.getUser_type());
                        Log.d("llllll", "getUser_specialization_id_fk =  " + user.getUser_specialization_id_fk());
                        Log.d("llllll", "onResponse: " + user.getUser_id());
                        Log.d("llllll", "onResponse:  =  http://dolphin-ksa.com/uploads/images/" + user.getUser_photo());
                        startActivity(new Intent(getApplicationContext(), ClientHomeActivity.class));
                        progressCreate.dismiss();
                        setResult(123);
                        finish();


                    }else if (response.body().getSuccess_signup()==2){
                        progressCreate.dismiss();
                        for (String  error :response.body().getError_msg() )
                        {
                            Toast.makeText(RegisterActivity.this, error, Toast.LENGTH_LONG).show();
                        }

                    }

                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, "حدث خطا", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onFailure: "+t.getMessage());
                    progressCreate.dismiss();
                }
            });
        }

    }

    private void getcities() {

        BaseClient.getApi().getCountries().enqueue(new Callback<List<Countries>>() {
            @Override
            public void onResponse(Call<List<Countries>> call, Response<List<Countries>> response) {
                if (response.body() != null) {
                    if (response.body().size() > 0) {
                        countriesList = response.body();
                        Log.d("onItemSelected", "onItemSelected: " + countriesList.get(0).getCities().get(0).getAr_city_title());

                        for (Countries country : countriesList) {
                            countries.add(country.getAr_name());
                            countriesId.add(country.getId_country());
                        }

                        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, countries);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        countriesSpinner.setAdapter(adapter);
                        countriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                COUNTRY_ID = String.valueOf(countriesList.get(position).getAr_name());
                                citiesBody = countriesList.get(position).getCities();


                                for (Countries.City city : citiesBody) {
                                    cities.add(city.getAr_city_title());
                                    citiesId.add(city.getId_city());
                                }

                                ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, cities);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                citiesSpinner.setAdapter(adapter);
                                citiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        CITY_ID = String.valueOf(citiesBody.get(position).getAr_city_title());

                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });


                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Countries>> call, Throwable t) {

            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    protected synchronized void buildGoogleApiClient() {
        Log.i(TAG, "Building GoogleApiClient");
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

    @Override
    public void onLocationChanged(Location location2) {
        location = location2;
        samplePresenter.onLocationChanged(location);
        //   locationTv.setText("" + location2.getLatitude() + " " + location2.getLongitude());
        getName();
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

    @CallSuper
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        locationManager.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            //Toast.makeText(this, "", Toast.LENGTH_LONG).show();
            return;
        }
        String destinationFileName = "SAMPLE_CROPPED_IMAGE_NAME" + ".jpg";

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
            profile_image.setImageURI(resultUri);

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
    public void back(View view) {
        onBackPressed();
    }

    void getName() {
        String errorMessage = "";

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());


        // ...

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
            locationAdress = address.getAddressLine(0);
            user_address.setText(address.getAddressLine(0).toString() + " " + address.getFeatureName());
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
}
