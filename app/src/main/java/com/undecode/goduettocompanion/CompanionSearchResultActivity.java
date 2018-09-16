package com.undecode.goduettocompanion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.undecode.goduettocompanion.adapters.CompanionSampleAdapter;
import com.undecode.goduettocompanion.models.searchcompanion.CompanionSearch;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanionSearchResultActivity extends AppCompatActivity
{
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    private CompanionSampleAdapter adapter;
    private List<CompanionSearch> list;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companion_search_result);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Companion Search");
        Gson gson = new Gson();
        list = Arrays.asList(gson.fromJson(getIntent().getStringExtra("result"), CompanionSearch[].class));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new CompanionSampleAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
