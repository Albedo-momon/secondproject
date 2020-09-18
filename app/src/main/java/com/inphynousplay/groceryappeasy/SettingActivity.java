package com.inphynousplay.groceryappeasy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.inphynousplay.groceryappeasy.Prevelent.Prevelent;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingActivity extends AppCompatActivity {
    private CircleImageView circleImageView;
    EditText updateMobile,updateName,updateAddress;
    TextView changeProfile,closeSettingbtn,saveSettingbtn;
    private Uri imageUri;
    private  String myurl = "";
    private StorageReference storageProfilePictureRef;
    private  String checker = "";
    private StorageTask uploadTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        circleImageView = findViewById(R.id.update_profile_pic);
        updateName = findViewById(R.id.update_name);
        updateAddress=findViewById(R.id.update_address);
        updateMobile = findViewById(R.id.update_mobile_number);
        changeProfile = findViewById(R.id.changeprofile);
        closeSettingbtn = findViewById(R.id.close_settings);
        saveSettingbtn = findViewById(R.id.update_account_settings);
        storageProfilePictureRef = FirebaseStorage.getInstance().getReference().child("Profile Pictures");






        userInfoDisplay(circleImageView,updateName,updateMobile,updateAddress);

        closeSettingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        saveSettingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checker.equals("clicked")){
                    userInfoSaved();
                    Intent intent = new Intent(SettingActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    updateOnlyUserInfo();
                    Intent intent = new Intent(SettingActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        changeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checker = "clicked";
                CropImage.activity(imageUri)
                        .setAspectRatio(1,1)
                        .start(SettingActivity.this);


            }
        });
    }

    private void updateOnlyUserInfo() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("firstname",updateName.getText().toString());

        hashMap.put("Alternate phone",updateMobile.getText().toString());
        hashMap.put("address",updateAddress.getText().toString());
        ref.child(Prevelent.currentOnlineUser.getPhone()).updateChildren(hashMap);

        startActivity(new Intent(SettingActivity.this,HomeActivity.class));
        finish();
        Toast.makeText(SettingActivity.this, "Updated info successfully", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE &&  data !=null){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            imageUri = result.getUri();
            circleImageView.setImageURI(imageUri);
        }else {
            Toast.makeText(this, "Error try again", Toast.LENGTH_SHORT).show();
        }
    }

    private void userInfoSaved() {

        if(TextUtils.isEmpty(updateName.getText().toString())){
            Toast.makeText(this, "name is mandatory", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(updateMobile.getText().toString())){
            Toast.makeText(this, "mobile is mandatory", Toast.LENGTH_SHORT).show();

        }else if(TextUtils.isEmpty(updateAddress.getText().toString())){
            Toast.makeText(this, "address is mandatory", Toast.LENGTH_SHORT).show();

        }else  if (checker.equals("clicked")){
            uploadImage();
        }
    }

    private void uploadImage() {
        final ProgressDialog loadingBar = new ProgressDialog(this);
        loadingBar.setTitle("Update Profile");
        loadingBar.setMessage("Please wait, while we are updating your account information");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();
        if(imageUri != null){
            final  StorageReference fileRef = storageProfilePictureRef
                    .child(Prevelent.currentOnlineUser.getPhone() + ".jpg");
            uploadTask = fileRef.putFile(imageUri);
            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if (!task.isSuccessful()){
                        throw task.getException();
                    }

                    return fileRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){
                        Uri downloadUrl = task.getResult();
                        myurl = downloadUrl.toString();
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("firstname",updateName.getText().toString());
                        hashMap.put("image",myurl);
                        hashMap.put("Alternate phone",updateMobile.getText().toString());
                        hashMap.put("address",updateAddress.getText().toString());
                        ref.child(Prevelent.currentOnlineUser.getPhone()).updateChildren(hashMap);


                        loadingBar.dismiss();
                        startActivity(new Intent(SettingActivity.this,HomeActivity.class));
                        Toast.makeText(SettingActivity.this, "Updated info successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    }else{
                        loadingBar.dismiss();
                        Toast.makeText(SettingActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            Toast.makeText(this, "Image is not selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void userInfoDisplay(CircleImageView circleImageView, EditText updateName, EditText updateMobile, EditText updateAddress) {
        DatabaseReference UserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(Prevelent.currentOnlineUser.getPhone());

        UserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    if(snapshot.child("image").exists()){
                        String image = snapshot.child("image").getValue().toString();
                        String name = snapshot.child("firstname").getValue().toString();
                        String phone = snapshot.child("phone").getValue().toString();
                        String address = snapshot.child("address").getValue().toString();

                        Picasso.get().load(image).into(circleImageView);
                        updateName.setText(name);
                        updateMobile.setText(phone);
                        updateAddress.setText(address);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}