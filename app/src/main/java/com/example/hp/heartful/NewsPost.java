package com.example.hp.heartful;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class NewsPost extends AppCompatActivity {

    private ImageButton userImage;
    private EditText title,userDesc;
    private Button submitbtn;
    private DatabaseReference mdatabase;
    private Uri imageUri=null;
    private ProgressDialog progress;
    private StorageReference newsPhotos;
    private FirebaseStorage mstorage;
    private final static int GALLERY_REQUEST=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_post);
     //   FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mstorage=FirebaseStorage.getInstance();
        mdatabase= FirebaseDatabase.getInstance().getReference().child("News");
        newsPhotos=mstorage.getReference().child("NewsImages");
        submitbtn=(Button)findViewById(R.id.submit_button);
        title=(EditText)findViewById(R.id.title);
        userDesc=(EditText)findViewById(R.id.user_des);
        progress=new ProgressDialog(this);
        userImage=(ImageButton)findViewById(R.id.user_image);
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery=new Intent(Intent.ACTION_GET_CONTENT);
                gallery.setType("image/jpeg");
                gallery.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(gallery, "Complete action using"),GALLERY_REQUEST);
            }
        });
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setMessage("News Posting, please wait...");
                userPost();


            }
        });
    }
    private void userPost(){
        final String postTitle=title.getText().toString();
        final String postDes= userDesc.getText().toString();
        if (!TextUtils.isEmpty(postTitle)&&!TextUtils.isEmpty(postDes)&&imageUri!=null){
            progress.show();
           StorageReference filePath=newsPhotos.child(imageUri.getLastPathSegment());
            filePath.putFile(imageUri).addOnSuccessListener(this,new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    @SuppressWarnings("VisibleForTests")
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    DatabaseReference newPost=mdatabase.push();
                    newPost.child("Title").setValue(postTitle);
                    newPost.child("Description").setValue(postDes);
                   newPost.child("Image").setValue(downloadUrl.toString());
                    progress.dismiss();
                    startActivity(new Intent(NewsPost.this,FragmentTwo.class));
                }
            });

        }
        else{
            Toast.makeText(NewsPost.this,"Please fill all the blanks",Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==GALLERY_REQUEST&&resultCode==RESULT_OK){
            imageUri=data.getData();
            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                imageUri=resultUri;
                userImage.setImageURI(imageUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Toast.makeText(NewsPost.this, (CharSequence) error,Toast.LENGTH_LONG).show();
            }
        }

    }
}
