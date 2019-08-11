package com.myapplication.util;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.myapplication.R;
import com.myapplication.model.ItemChild;

import java.io.IOException;
import java.io.InputStream;

public class ItemGalleryFragment extends Fragment {
    private ItemChild image;
    private ImageView productImg;

    public static ItemGalleryFragment newInstance(ItemChild image) {
        ItemGalleryFragment fragment = new ItemGalleryFragment();
        fragment.setImage(image);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gallery_item, container, false);
        productImg = (ImageView) rootView.findViewById(R.id.picture_img_slide);
        if(image !=null){
            LoadNetworkImage(productImg, image.getThumbnailUrl());
        }
        return rootView;
    }

    public void LoadNetworkImage(final ImageView imageView, final String imageUrl){
        InputStream ims = null;
        try {
            ims = getActivity().getResources().getAssets().open(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // load image as Drawable
        Drawable d = Drawable.createFromStream(ims, null);

        Glide.with(getActivity()).
                load(d).
                into(imageView);

    }

    public ItemChild getImage() {
        return image;
    }

    public void setImage(ItemChild image) {
        this.image = image;
    }
}

