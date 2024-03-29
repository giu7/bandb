package com.giu7.bandb.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.giu7.bandb.R;
import com.giu7.bandb.models.Camera;
import com.giu7.bandb.ui.activities.UpdateCameraActivity;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static com.giu7.bandb.utils.MyUtils.getSiNo;

public class CameraAdapter extends RecyclerView.Adapter {

    private static final String TAG = "CamereAdapter";

    private LayoutInflater inflater;
    private Context context;
    private List<Camera> camere;

    public CameraAdapter(Context context, List<Camera> camere) {
        inflater = LayoutInflater.from(context);
        this.camere = camere;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_camera, parent, false);
        return new CameraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CameraViewHolder cameraViewHolder = (CameraViewHolder) holder;
        Camera camera = camere.get(position);

        Glide.with(context).load(camera.getFotoUrl()).centerCrop().into(cameraViewHolder.fotoCameraIv);
        cameraViewHolder.nomeCameraTv.setText(StringUtils.capitalize(camera.getNome())+"");
        cameraViewHolder.lettiCameraTv.setText(camera.getLetti()+"");
        cameraViewHolder.tvCameraTv.setText(getSiNo(camera.isTv())+"");
        cameraViewHolder.bagnoCameraTv.setText(getSiNo(camera.isBagno())+"");
        cameraViewHolder.prezzoCameraTv.setText(camera.getPrezzo()+"");
    }

    @Override
    public int getItemCount() {
        return camere.size();
    }

    private class CameraViewHolder extends RecyclerView.ViewHolder {
        public ImageView fotoCameraIv;
        public TextView nomeCameraTv, lettiCameraTv, tvCameraTv, bagnoCameraTv, prezzoCameraTv;

        public CameraViewHolder(View view) {
            super(view);

            fotoCameraIv = itemView.findViewById(R.id.camera_foto_iv);
            nomeCameraTv = itemView.findViewById(R.id.camera_nome_tv);
            lettiCameraTv = itemView.findViewById(R.id.camera_letti_tv);
            tvCameraTv = itemView.findViewById(R.id.camera_tv_tv);
            bagnoCameraTv = itemView.findViewById(R.id.camera_bagno_tv);
            prezzoCameraTv = itemView.findViewById(R.id.camera_prezzo_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, UpdateCameraActivity.class);
                    intent.putExtra("nome", camere.get(getAdapterPosition()).getNome());
                    context.startActivity(intent);
                }
            });
        }
    }
}
