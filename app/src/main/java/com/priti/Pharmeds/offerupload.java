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
import android.util.Log;
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

public class offerupload extends AppCompatActivity {
    ImageView offerimage;
    Uri FilePathUri;
    Button uploadoffer;
    EditText title,description,percentoff;
    ProgressDialog progressDialog;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Offers");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference("OffersImage");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offerupload);
        offerimage=findViewById(R.id.uploadimageoffer);
        title=findViewById(R.id.title);
        description=findViewById(R.id.descoffer);
        percentoff=findViewById(R.id.percent);
        uploadoffer=findViewById(R.id.uploadoffer);
        progressDialog = new ProgressDialog(offerupload.this);
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    assert data != null;
                    FilePathUri = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
                        offerimage.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d("error in image","not captured image");
                    }
                }
            }
        });
        offerimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                activityResultLauncher.launch(intent);
            }
        });
        uploadoffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             setUpload();
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

                            String title1 = title.getText().toString();
                            String description1 = description.getText().toString();
                            String percentoff1 = percentoff.getText().toString();

                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Post Uploaded Successfully!", Toast.LENGTH_LONG).show();

                            storageReference2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String url = uri.toString();
//                                    ProductModel(String categories, String medname, String mfgname, String price, String mfgdate, String expdate, String meddescription,String fileurl)
                                    OfferModel offerModel = new OfferModel(title1,description1,url,percentoff1);
                                    String offerInfo = databaseReference.push().getKey();
                                    databaseReference.child(percentoff1).child(offerInfo).setValue(offerModel);
                                }
                            });
                        }
                    });
        } else {
            Toast.makeText(offerupload.this, "Something went wrong!", Toast.LENGTH_LONG).show();
        }
    }
}