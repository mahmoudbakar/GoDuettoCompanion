package com.undecode.goduettocompanion.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.undecode.goduettocompanion.CompanionBookingActivity;
import com.undecode.goduettocompanion.R;
import com.undecode.goduettocompanion.models.searchcompanion.CompanionSearch;
import com.undecode.goduettocompanion.viewholders.CompanionSampleViewHolder;

import java.util.List;

public class CompanionSampleAdapter extends RecyclerView.Adapter<CompanionSampleViewHolder>
{
    private Context context;
    private List<CompanionSearch> list;

    public CompanionSampleAdapter(Context context, List<CompanionSearch> list)
    {
        this.context = context;
        this.list = list;
    }

    public void setList(List<CompanionSearch> list)
    {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CompanionSampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_companion, parent, false);

        return new CompanionSampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanionSampleViewHolder holder, int position)
    {
        final CompanionSearch companionSearch = list.get(position);
        Glide.with(holder.image).load(companionSearch.getCPicture()).into(holder.image);
        holder.txtName.setText(companionSearch.getCName());
        try
        {
            holder.txtPrice.setText(String.valueOf(companionSearch.getWorkingHours().get(0).getHourlyWages())+" $");
        }catch (IndexOutOfBoundsException e)
        {
            holder.txtPrice.setText("N/A");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Gson gson = new Gson();
                Intent intent = new Intent(context, CompanionBookingActivity.class);
                intent.putExtra("companion", gson.toJson(companionSearch));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}
