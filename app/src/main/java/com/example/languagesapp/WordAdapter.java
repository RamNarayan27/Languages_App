package com.example.languagesapp;
import android.app.Activity;
import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends ArrayAdapter<Words> {
    private Context context;
    private List<Words> words=new ArrayList<Words>();
    private int colorResource;
    public WordAdapter(@NonNull Context context, ArrayList<Words> words,int colorResource){
        super(context,0,words);
        this.context=context;
        this.words=words;
        this.colorResource=colorResource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View  listItem=convertView;
        if(listItem==null)
            listItem= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        Words currentWord=words.get(position);
        ImageView imageView = (ImageView) listItem.findViewById(R.id.img);
        if(currentWord.hasImage()) {
            imageView.setImageResource(currentWord.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        }
        else
        {
            imageView.setVisibility(View.GONE);
        }
        View textContainer=listItem.findViewById(R.id.text_Container);
        int color= ContextCompat.getColor(getContext(),colorResource);
        textContainer.setBackgroundColor(color);
        TextView def=(TextView)listItem.findViewById(R.id.eng);
        def.setText(currentWord.getDefaultTranslation());
        TextView miw=(TextView)listItem.findViewById(R.id.miwok);
        miw.setText(currentWord.getMiwokTranslation());
        return listItem;
    }
}