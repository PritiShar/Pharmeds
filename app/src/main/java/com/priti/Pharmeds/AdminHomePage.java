package com.priti.Pharmeds;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.DatePickerDialog;

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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;


public class AdminHomePage extends AppCompatActivity {
    ImageView prodimage;
    Uri FilePathUri;
    Button upload, addmfgdate, addexpdate;
    TextView tvmfgdate, tvexpdate;
    ProgressDialog progressDialog;
    Spinner spinner,rx;
    EditText medname, mfgname, price, meddescription;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Products");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference("ProductsImage");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);
        prodimage = findViewById(R.id.prodImage);
        addmfgdate = findViewById(R.id.mfgdate);
        addexpdate = findViewById(R.id.expirydate);
        tvmfgdate = findViewById(R.id.tvmfgdate);
        upload = findViewById(R.id.upload);
        tvexpdate = findViewById(R.id.textView);
        progressDialog = new ProgressDialog(AdminHomePage.this);
        spinner = findViewById(R.id.spinner);
        medname = findViewById(R.id.medname);
        mfgname = findViewById(R.id.mfgname);
        price = findViewById(R.id.price);
        meddescription = findViewById(R.id.meddescription);
        rx = findViewById(R.id.rxrequired);
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    assert data != null;
                    FilePathUri = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
                        prodimage.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        prodimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                activityResultLauncher.launch(intent);
            }
        });

        View.OnClickListener showpicker = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.DAY_OF_MONTH, day);
                String currentDateString = DateFormat.getDateInstance().format(c.getTime());

                final View vv = v;
                DatePickerDialog datePickerDialog = new DatePickerDialog(AdminHomePage.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        if (vv.getId() == R.id.mfgdate) {

                            tvmfgdate.setText(year + "/" + month + "/" + dayOfMonth);
                            Toast.makeText(AdminHomePage.this, currentDateString, Toast.LENGTH_SHORT).show();
                        } else {
                            tvexpdate.setText(year + "/" + month + "/" + dayOfMonth);
                        }
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        };
        addmfgdate.setOnClickListener(showpicker);
        addexpdate.setOnClickListener(showpicker);
        //Add a firebase code to insert data into the database using ProductModel class
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpload();
            }
        });
        Button send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomePage.this,Medicinerecyclerview.class));
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
                            String Category = spinner.getSelectedItem().toString();
                            String rxrequired = rx.getSelectedItem().toString();
                            String MfgDate = tvmfgdate.getText().toString();
                            String ExpDate = tvexpdate.getText().toString();
                            String MedName = medname.getText().toString();
                            String MfgName = mfgname.getText().toString();
                            String Price = price.getText().toString();
                            String MedDescription = meddescription.getText().toString();
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Post Uploaded Successfully!", Toast.LENGTH_LONG).show();

                            storageReference2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String url = uri.toString();
//                                    ProductModel(String categories, String medname, String mfgname, String price, String mfgdate, String expdate, String meddescription,String fileurl)
                                    ProductModel productModel = new ProductModel(Category,MedName,MfgName,Price,MfgDate,ExpDate,MedDescription,rxrequired, url);
                                    String medInfo = databaseReference.push().getKey();
                                    databaseReference.child(medInfo).setValue(productModel);
                                }
                            });
                        }
                    });
        } else {
            Toast.makeText(AdminHomePage.this, "Something went wrong!", Toast.LENGTH_LONG).show();
        }
    }

}