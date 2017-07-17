package com.schoolofskills.myfirebasesample.photomultiple;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ViewSwitcher;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.schoolofskills.myfirebasesample.R;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import android.os.Handler;



public class FileUploadActivity extends AppCompatActivity implements View.OnClickListener {


    private LayoutInflater inflater;
    private View firstView;
    private View secondView;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    private Button openCustomGallery;
    private Button mUploadPhoto;
    private GridView selectedImageGridView;

    private List<String> selectedImages;
    private GalleryAdapter adapter;

    private static final int CustomGallerySelectId = 1;//Set Intent Id
    public static final String CustomGalleryIntentKey = "ImageArray";//Set Intent Key Value
    private EditText mEditTextValue;
    private ArrayList<View> view = new ArrayList<>();
    private ProgressDialog uploadProgress;
    private String remarkPhoto;
    private int currentprogress;
    private ImageView currentImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_upload);

        initViews();
        setListeners();
        getSharedImages();
        //initImageLoader();

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        firstView = inflater.inflate(R.layout.customgridview_item, null); // inflate firstview.xml layout file

        uploadProgress = new ProgressDialog(FileUploadActivity.this);
        uploadProgress.setTitle("Uploading Picture");
        uploadProgress.setMessage("Uploading picture...");
        uploadProgress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        uploadProgress.setProgress(0);


        firebaseStorage = FirebaseStorage.getInstance();

        storageReference = firebaseStorage.getReferenceFromUrl("gs://crackling-heat-2430.appspot.com");

        StorageReference imagesRefPath = storageReference.child("images").child("image.jpg");

        mUploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadTask uploadTask;

                Log.i("Selected image size: ", "" + selectedImages.size());


                for(int i=0;i<selectedImages.size();i++) {
                    final Uri uri = Uri.parse("file://" + selectedImages.get(i));

                    final int j = i;

                    final StorageReference newImage = storageReference.child("images/" + uri.getLastPathSegment());

                    uploadTask = newImage.putFile(uri);

                    uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * (taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount());
                            System.out.println("Upload is " + progress + "% done");
                            currentprogress = (int) progress;

                            uploadProgress.setMessage("Uploading Image: " + uri.getLastPathSegment() + "...");
                            uploadProgress.show();

                            uploadProgress.setProgress(currentprogress);

                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            uploadProgress.dismiss();

                            View currentView;

                            currentView = selectedImageGridView.getChildAt(j);
                            if (currentView instanceof ViewGroup) {
                                for (int k = 0; k < ((ViewGroup) currentView).getChildCount(); k++) {
                                    if (((ViewGroup) currentView).getChildAt(k) instanceof EditText) {
                                        remarkPhoto = ((EditText) ((ViewGroup) currentView).getChildAt(k)).getText().toString();
                                        if (remarkPhoto.length() == 0) {
                                            remarkPhoto = uri.getLastPathSegment();
                                        }
                                    }else if(((ViewGroup) currentView).getChildAt(k) instanceof ImageView){
                                        currentImage = (ImageView) ((ViewGroup) currentView).getChildAt(k);
                                        currentImage.setAlpha(1F);
                                    }
                                }
                            }

                            StorageMetadata storageMetadata = new StorageMetadata.Builder()
                                    .setCustomMetadata("Remark", remarkPhoto)
                                    .build();

                            newImage.updateMetadata(storageMetadata).addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
                                @Override
                                public void onSuccess(StorageMetadata storageMetadata) {

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {
                                    Toast.makeText(FileUploadActivity.this, "Could not write remark to server", Toast.LENGTH_SHORT).show();
                                }
                            });


                            Uri downloadUrl = taskSnapshot.getDownloadUrl();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {
                            uploadProgress.dismiss();
                            Toast.makeText(FileUploadActivity.this, "Upload failed because: " + e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    });

                }
            }
        });


    }

//    private void initImageLoader() {
//        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(
//                getApplicationContext());
//        config.threadPriority(Thread.NORM_PRIORITY - 2);
//        config.denyCacheImageMultipleSizesInMemory();
//        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
//        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
//        config.tasksProcessingOrder(QueueProcessingType.LIFO);
//        config.writeDebugLogs(); // Remove for release app
//
//        // Initialize ImageLoader with configuration.
//        ImageLoader.getInstance().init(config.build());
//    }

    private void initViews() {
        openCustomGallery = (Button) findViewById(R.id.openCustomGallery);
        selectedImageGridView = (GridView) findViewById(R.id.selectedImagesGridView);
        mUploadPhoto = (Button) findViewById(R.id.UploadPhotos);
    }

    //set Listeners
    private void setListeners() {
        openCustomGallery.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.openCustomGallery:
                //Start Custom Gallery Activity by passing intent id
                startActivityForResult(new Intent(FileUploadActivity.this, CustomGalleryActivity.class), CustomGallerySelectId);
                break;
        }

    }

    protected void onActivityResult(int requestcode, int resultcode,
                                    Intent imagereturnintent) {
        super.onActivityResult(requestcode, resultcode, imagereturnintent);
        switch (requestcode) {
            case CustomGallerySelectId:
                if (resultcode == RESULT_OK) {
                    String imagesArray = imagereturnintent.getStringExtra(CustomGalleryIntentKey);//get Intent data
                    //Convert string array into List by splitting by ',' and substring after '[' and before ']'
                    selectedImages = Arrays.asList(imagesArray.substring(1, imagesArray.length() - 1).split(", "));
                    //loadGridView(new ArrayList<String>(selectedImages));//call load gridview method by passing converted list into arrayList
                    adapter = new GalleryAdapter(FileUploadActivity.this,new ArrayList<>(selectedImages),false);
                    selectedImageGridView.setAdapter(adapter);


                }
                break;

        }
    }

    //Load GridView
    private void loadGridView(ArrayList<String> imagesArray) {
        GalleryAdapter adapter = new GalleryAdapter(FileUploadActivity.this, imagesArray, false);
        selectedImageGridView.setAdapter(adapter);
    }

    //Read Shared Images
    private void getSharedImages() {

        //If Intent Action equals then proceed
        if (Intent.ACTION_SEND_MULTIPLE.equals(getIntent().getAction())
                && getIntent().hasExtra(Intent.EXTRA_STREAM)) {
            ArrayList<Parcelable> list =
                    getIntent().getParcelableArrayListExtra(Intent.EXTRA_STREAM);//get Parcelabe list
            ArrayList<String> selectedImages = new ArrayList<>();

            //Loop to all parcelable list
            for (Parcelable parcel : list) {
                Uri uri = (Uri) parcel;//get URI
                String sourcepath = getPath(uri);//Get Path of URI
                selectedImages.add(sourcepath);//add images to arraylist
            }
            loadGridView(selectedImages);//call load gridview
        }
    }

    //get actual path of uri
    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        startManagingCursor(cursor);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


}
