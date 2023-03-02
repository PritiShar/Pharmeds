package com.priti.Pharmeds;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class AdminHealthLibrary extends AppCompatActivity {
   ImageView image;
   EditText title , description;
   Button upload , Viewall;
    ProgressDialog progressDialog;
    Uri FilePathUri;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("HealthLibrary");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference("HealthLibraryImages");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_health_library);
        image = findViewById(R.id.healthlibraryimg);
        title= findViewById(R.id.title);
        description= findViewById(R.id.desc);
        upload=findViewById(R.id.uploadbtn);
        Viewall= findViewById(R.id.viewallbtn);
        progressDialog = new ProgressDialog(AdminHealthLibrary.this);
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    assert data != null;
                    FilePathUri = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
                        image.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpload();
            }
        });
           image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                activityResultLauncher.launch(intent);
            }
        });
    }
    public String GetFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    public void setUpload(){
        if (FilePathUri != null) {
            progressDialog.setTitle("Uploading! Please Wait!");
            progressDialog.show();
            StorageReference storageReference2 = storageReference.child(System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));
            storageReference2.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            String Title = title.getText().toString();
                            String Description = description.getText().toString();
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Post Uploaded Successfully!", Toast.LENGTH_LONG).show();

                            storageReference2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String url = uri.toString();
//                                    ProductModel(String categories, String medname, String mfgname, String price, String mfgdate, String expdate, String meddescription,String fileurl)
                                    HealthLibraryModel healthLibraryModel = new HealthLibraryModel (Title,Description,url);
                                    String healthInfo = databaseReference.push().getKey();
                                    databaseReference.child(healthInfo).setValue(healthLibraryModel);
                                }
                            });
                        }
                    });
                        }        else {
            Toast.makeText(AdminHealthLibrary.this, "Something went wrong!", Toast.LENGTH_LONG).show();
        }
    }

}