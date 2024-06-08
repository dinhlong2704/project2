package com.example.projectfinal.view.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfinal.Database;
import com.example.projectfinal.R;
import com.example.projectfinal.model.MedicalRecords;
import com.example.projectfinal.view.act.medicalrecord.UpdateActivity;

import java.util.List;

public class AdapterMedicalRecord extends RecyclerView.Adapter<AdapterMedicalRecord.MedicalRecordHoder> {
    private final Activity context;
    private final List<MedicalRecords> listMedicalRecord;

    public AdapterMedicalRecord(Activity context, List<MedicalRecords> listMedicalRecord) {
        this.context = context;
        this.listMedicalRecord = listMedicalRecord;

    }

    @NonNull
    @Override
    public MedicalRecordHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_medicarecord, parent, false);
        return new AdapterMedicalRecord.MedicalRecordHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicalRecordHoder holder, int position) {
        MedicalRecords medicalRecords = listMedicalRecord.get(position);
        holder.tvId.setText(medicalRecords.id+"");
        holder.tvName.setText(medicalRecords.ten);
        holder.tvDate.setText(medicalRecords.ngay);

        if (medicalRecords.anh != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(medicalRecords.anh, 0, medicalRecords.anh.length);
            holder.ivAnh.setImageBitmap(bitmap);
        } else {
            // Gán hình ảnh mặc định nếu mảng byte là null
            holder.ivAnh.setImageResource(R.drawable.ic_launcher); // Thay R.drawable.default_image bằng ID hình ảnh mặc định của bạn
        }
        holder.btUpdate.setOnClickListener(v -> holder.gotoUpdate(medicalRecords.id));
        holder.btDelete.setOnClickListener(v -> holder.gotoDelete(medicalRecords.id));
    }

    @Override
    public int getItemCount() {
        return listMedicalRecord.size();
    }

    public class MedicalRecordHoder extends RecyclerView.ViewHolder {
        Button btUpdate, btDelete;
        ImageView ivAnh;
        TextView tvName, tvDate,tvId;


        public MedicalRecordHoder(@NonNull View v) {
            super(v);
            btUpdate = v.findViewById(R.id.bt_update);
            btDelete = v.findViewById(R.id.bt_delete);
            ivAnh = v.findViewById(R.id.iv_anh);
            tvId = v.findViewById(R.id.tv_id);
            tvName = v.findViewById(R.id.tv_name);
            tvDate = v.findViewById(R.id.tv_date);
//            btUpdate.setOnClickListener(v1 -> MedicalRecordHoder.this.gotoUpdate());
//            btDelete.setOnClickListener(v12 -> MedicalRecordHoder.this.gotoDelete());

        }

        private void gotoDelete(int id) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setIcon(R.drawable.ic_launcher);
            builder.setTitle("Xác nhận xóa");
            builder.setMessage("Bạn có chắc chắn muốn xóa bệnh án này");
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    deleteRecord(id);
                }
            });
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        private void deleteRecord(int id) {
            SQLiteDatabase database = Database.initDatabase(context, "BenhAn.db");
            database.delete("benhan", " idBenh = ?", new String[]{id+""});

            for (int i = 0; i < listMedicalRecord.size(); i++) {
                if (listMedicalRecord.get(i).id == id) {
                    listMedicalRecord.remove(i);
                    break;
                }
            }
//            listMedicalRecord.clear();
//            Cursor cursor = database.rawQuery("SELECT * FROM benhan", null);
//            while (cursor.moveToNext()) {
//                int idbenhan = cursor.getInt(0);
//                String ten = cursor.getString(1);
//                String date = cursor.getString(2);
//                byte[] anh = cursor.getBlob(3);
//                listMedicalRecord.add(new MedicalRecords(idbenhan, ten, date, anh));
//            }
            notifyDataSetChanged();
        }

        private void gotoUpdate(int id) {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("idBenh", id);
            context.startActivity(intent);
        }
    }
}
