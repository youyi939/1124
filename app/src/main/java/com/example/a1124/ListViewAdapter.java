package com.example.a1124;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;

public class ListViewAdapter extends ArrayAdapter<News> {
    Bitmap bitmap = null;
    private int resourceId;

    public ListViewAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        final TextView name = view.findViewById(R.id.tittle);
        final ImageView imageView = view.findViewById(R.id.img);
        final TextView price = view.findViewById(R.id.msg);

        final News news = getItem(position);

        name.setText(news.getTittle());
        price.setText(news.getMessage());

        Glide.with(parent.getContext()).load(news.getImage()).into(imageView);              //Glide框架


        return view;
    }

}