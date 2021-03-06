package com.example.exitpoll.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.exitpoll.R;
import com.example.exitpoll.model.Item;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class NameListAdapter extends ArrayAdapter<Item> {
    private Context mContext;
    private int mResource;
    private List<Item> mNameItemList;

    public NameListAdapter(@NonNull Context context,
                           int resource,
                           @NonNull List<Item> nameItemList) {
        super(context, resource, nameItemList);
        this.mContext = context;
        this.mResource = resource;
        this.mNameItemList = nameItemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResource, parent, false);

        TextView titleTextView = view.findViewById(R.id.point_text);
        ImageView imageView = view.findViewById(R.id.image_view);

        Item nameItem = mNameItemList.get(position);
        String point =nameItem.point;
        String filename = nameItem.image;

        titleTextView.setText(point);
        //imageView.setImageResource(R.drawable.xxx);
        AssetManager am = mContext.getAssets();
        try {
            InputStream is = am.open(filename);
            Drawable drawable = Drawable.createFromStream(is, "");
            imageView.setImageDrawable(drawable);
        } catch (IOException e) {
            File privateDir = mContext.getFilesDir();
            File logoFile = new File(privateDir, filename);

            Bitmap bitmap = BitmapFactory.decodeFile(logoFile.getAbsolutePath(), null);
            imageView.setImageBitmap(bitmap);

            e.printStackTrace();
        }

        return view;
    }
}
