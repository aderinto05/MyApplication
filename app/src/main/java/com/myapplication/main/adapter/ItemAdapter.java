package com.myapplication.main.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.myapplication.R;
import com.myapplication.main.model.Item;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<Item> itemList;
    private Activity mActivity;
    private OnItemClickList onItemClickList;
    private final int VIEW_TYPE_ITEM = 0;

    public ItemAdapter(Activity activity,
                               List<Item> items) {
        mActivity = activity;

        itemList = new ArrayList<>();
        if (items != null) {
            itemList.addAll(items);
        }

    }

    public Item getPrivateItem(int position) {
        if (position < itemList.size()) {
            return itemList.get(position);
        }

        return null;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_ITEM:
                final ViewHolderItem viewHolder;
                viewHolder = new ViewHolderItem(LayoutInflater.from(mActivity).inflate(R.layout.list_item, parent, false));
                return viewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_ITEM:
                Item item = getPrivateItem(position);
                if (item == null) {
                    return;
                }
                if (item != null) {
                    final ViewHolderItem videoViewHolder = (ViewHolderItem) holder;

                    InputStream ims = null;
                    try {
                        ims = mActivity.getResources().getAssets().open(item.getThumbnailUrl());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // load image as Drawable
                    Drawable d = Drawable.createFromStream(ims, null);
                    Glide.with(mActivity)
                            .load(d)
                            .into(videoViewHolder.imgView);
                }
                break;
        }
    }

    public class ViewHolderItem extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imgView;


        public ViewHolderItem(View convertView) {
            super(convertView);
            cardView = (CardView) convertView.findViewById(R.id.card_view_main);
            imgView = (ImageView) convertView.findViewById(R.id.img_thumbnail);
            //

            try {
                imgView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickList.onClickItemList(getAdapterPosition());
                    }
                });
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickList.onClickItemList(getAdapterPosition());
                    }
                });

            } catch (NoClassDefFoundError ex) {
            } catch (RuntimeException ex) {
            }

        }

    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void clear() {
        itemList.clear();
        notifyDataSetChanged();
    }

    public interface OnItemClickList {
        void onClickItemList(int position);
    }

    public void setOnItemClickList(OnItemClickList onItemClickList) {
        this.onItemClickList = onItemClickList;
    }
}
