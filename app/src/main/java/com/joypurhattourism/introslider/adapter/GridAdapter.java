package com.joypurhattourism.introslider.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.joypurhattourism.introslider.R;
import com.joypurhattourism.introslider.SettingsFragment;
import com.joypurhattourism.introslider.app.AppController;
import com.joypurhattourism.introslider.app.Config;
import com.joypurhattourism.introslider.model.TouristSpot;
import java.util.List;

public class GridAdapter extends Adapter<GridAdapter.ViewHolder> {
    String language;
    Context mContext;
    ImageLoader mImageLoader = AppController.getInstance().getImageLoader();
    LayoutInflater mInflater;
    SharedPreferences mSettings;
    List<TouristSpot> mTouristSpotList;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public interface OnItemClickListener {
        void onItemClicked(int i, View view);
    }

    public interface OnItemLongClickListener {
        void onItemLongClicked(int i, View view);
    }

    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        CardView card;
        ImageView imageThumbnail;
        TextView textName;

        public ViewHolder(View itemView) {
            super(itemView);
            this.card = (CardView) itemView.findViewById(R.id.card);
            this.textName = (TextView) itemView.findViewById(R.id.text_name);
            this.imageThumbnail = (ImageView) itemView.findViewById(R.id.image_thumbnail);
        }
    }

    public GridAdapter(Context context, List<TouristSpot> touristSpots) {
        this.mContext = context;
        this.mTouristSpotList = touristSpots;
        this.mInflater = LayoutInflater.from(this.mContext);
        this.mSettings = PreferenceManager.getDefaultSharedPreferences(this.mContext);
        this.language = this.mSettings.getString(SettingsFragment.KEY_PREF_LANGUAGE, SettingsFragment.LANGUAGE_ENGLISH);
    }

    public int getItemCount() {
        return this.mTouristSpotList.size();
    }

    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        TouristSpot touristSpot = (TouristSpot) this.mTouristSpotList.get(position);
        if (this.language.equalsIgnoreCase(SettingsFragment.LANGUAGE_BANGLA)) {
            viewHolder.textName.setText(touristSpot.getNameBN());
        } else {
            viewHolder.textName.setText(touristSpot.getNameEN());
        }
        Glide.with(this.mContext).load(Config.PHOTO_BASE_URL_SMALL + touristSpot.getPhoto()).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.ic_placeholder).into(viewHolder.imageThumbnail);
        viewHolder.card.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                GridAdapter.this.onItemClickListener.onItemClicked(position, v);
            }
        });
        viewHolder.card.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View v) {
                GridAdapter.this.onItemLongClickListener.onItemLongClicked(position, v);
                return true;
            }
        });
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (this.mInflater == null) {
            this.mInflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        return new ViewHolder(this.mInflater.inflate(R.layout.grid_item, parent, false));
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }
}
