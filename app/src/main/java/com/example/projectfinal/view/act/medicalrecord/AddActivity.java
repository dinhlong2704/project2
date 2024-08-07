package com.example.projectfinal.view.act.medicalrecord;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.projectfinal.Database;
import com.example.projectfinal.databinding.M009MedicalrecordAddBinding;
import com.example.projectfinal.view.act.BaseAct;
import com.example.projectfinal.viewmodel.CommonVM;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddActivity extends BaseAct<M009MedicalrecordAddBinding, CommonVM> {

    String DATABASE_NAME = "BenhAn.db";
    private static final int CHON_HINH = 1;
    private static final int CHUP_ANH = 2;
    @Override
    protected void initView() {
        binding.btChonhinh.setOnClickListener(v -> chonHinh());
        binding.btChuphinh.setOnClickListener(v -> chupAnh());
        binding.btLuu.setOnClickListener(v -> insert());
        binding.btHuy.setOnClickListener(v -> cancel());
    }



    private void insert() {

        String tenba = binding.edtName.getText().toString();
        String ngay = binding.edtDate.getText().toString();
        byte[] anh = getByteArrayFromImageView(binding.ivAnhbenhan);

        ContentValues contentValues = new ContentValues();
        contentValues.put("tenbenhan", tenba);
        contentValues.put("ngay", ngay);
        contentValues.put("anh", anh);

        SQLiteDatabase database = Database.initDatabase(this, "BenhAn.db");
        database.insert("benhan", null,contentValues);
        Intent intent = new Intent(this, MedicalRecordActivity.class);
        startActivity(intent);
    }

    private void cancel() {
        Intent intent = new Intent(this, MedicalRecordActivity.class);
        startActivity(intent);
    }

    private byte[] getByteArrayFromImageView(ImageView v) {
        BitmapDrawable drawable = (BitmapDrawable) v.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    private void chonHinh() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, CHON_HINH);

    }

    private void chupAnh() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CHUP_ANH);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CHON_HINH) {
                try {
                    Uri imageUri = data.getData();
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    binding.ivAnhbenhan.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == CHUP_ANH) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                binding.ivAnhbenhan.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    protected M009MedicalrecordAddBinding initViewBinding() {
        return M009MedicalrecordAddBinding.inflate(getLayoutInflater());
    }

    @Override
    protected Class<CommonVM> initViewModel() {
        return CommonVM.class;
    }

    @Override
    public void backToPrevious() {

    }

    @Override
    public void checkMapPermission() {

    }
}
