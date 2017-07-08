package com.tresor.home.bottomsheet;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tresor.R;
import com.tresor.home.model.IconModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kris on 7/6/17. Tokopedia
 */

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.ImageHolder>{

    private List<IconModel> iconModelList;

    public IconAdapter(List<IconModel> iconModelList) {
        this.iconModelList = iconModelList;
    }

    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View adapterView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.icon_list_layout, parent, false);
        return new ImageHolder(adapterView);
    }

    @Override
    public void onBindViewHolder(ImageHolder holder, int position) {
        holder.iconImage.setImageResource(iconModelList.get(position).getIconImageId());
    }

    @Override
    public int getItemCount() {
        return iconModelList.size();
    }

    class ImageHolder extends RecyclerView.ViewHolder {
        private ImageView iconImage;
        ImageHolder(View itemView) {
            super(itemView);
            iconImage = (ImageView) itemView.findViewById(R.id.icon_image);
        }
    }
}
