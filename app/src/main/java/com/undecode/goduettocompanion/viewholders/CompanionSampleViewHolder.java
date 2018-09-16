package com.undecode.goduettocompanion.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.undecode.goduettocompanion.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class CompanionSampleViewHolder extends RecyclerView.ViewHolder
{
    public CircleImageView image;
    public TextView txtName, txtPrice;

    public CompanionSampleViewHolder(View v)
    {
        super(v);
        image = v.findViewById(R.id.image);
        txtName = v.findViewById(R.id.txtName);
        txtPrice = v.findViewById(R.id.txtPrice);
    }
}
