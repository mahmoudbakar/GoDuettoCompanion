package com.undecode.goduettocompanion.fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.undecode.goduettocompanion.CompanionSearchResultActivity;
import com.undecode.goduettocompanion.R;
import com.undecode.goduettocompanion.bakar.network.API;
import com.undecode.goduettocompanion.bakar.network.OnDataReady;
import com.undecode.goduettocompanion.bakar.utils.FillSpinner;
import com.undecode.goduettocompanion.models.CitiesModel;
import com.undecode.goduettocompanion.models.CountriesLangModel;
import com.undecode.goduettocompanion.models.LanguageModel;
import com.undecode.goduettocompanion.models.ProfileModel;
import com.undecode.goduettocompanion.models.dataholder.CountryLangHolder;
import com.undecode.goduettocompanion.models.dataholder.LangIDHolder;
import com.undecode.goduettocompanion.models.dataholder.SearchQuery;
import com.undecode.goduettocompanion.models.searchcompanion.CompanionSearch;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeFragment extends Fragment implements OnDataReady, View.OnClickListener, AdapterView.OnItemSelectedListener {
    @BindView(R.id.spCountry)
    Spinner spCountry;
    @BindView(R.id.spCity)
    Spinner spCity;
    @BindView(R.id.rgGender)
    RadioGroup rgGender;
    @BindView(R.id.checkChildren)
    CheckBox checkChildren;
    @BindView(R.id.edFrom)
    EditText edFrom;
    @BindView(R.id.edTo)
    EditText edTo;
    Unbinder unbinder;
    @BindView(R.id.txtDate)
    TextView txtDate;
    @BindView(R.id.imagePickDate)
    ImageView imagePickDate;
    private TextView advancedButton, txtSearchButton;
    private LinearLayout linearLayout;
    private List<CompanionSearch> companionSearches;
    private List<LanguageModel> languageModels;
    private List<CountriesLangModel> countriesLangModels;
    private List<CitiesModel> citiesModels;
    private FillSpinner fillSpinner;


    int mYear, mMonth, mDay;
    Calendar cal;
    boolean dt;
    String goingDate;

    SearchQuery searchQuery;
    public static HomeFragment newInstance()
    {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public static HomeFragment newInstance(Bundle bundle)
    {
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        searchQuery = new SearchQuery();
        searchQuery.setGender(true);
        searchQuery.setWorksWithChildren(true);
        searchQuery.setHourlyrateMore(0);
        searchQuery.setHourlyrateLess(9999);
        searchQuery.setLang("50B8E00D-93AC-40D7-945C-7743B3212CFB");

        unbinder = ButterKnife.bind(this, view);

        fillSpinner = new FillSpinner();
        advancedButton = view.findViewById(R.id.advancedButton);
        advancedButton.setOnClickListener(this);
        txtSearchButton = view.findViewById(R.id.txtButtonSearch);
        txtSearchButton.setOnClickListener(this);
        linearLayout = view.findViewById(R.id.advancedLayout);
        LangIDHolder langIDHolder = new LangIDHolder();
        langIDHolder.setLang("c06bb0c0-2110-4309-88a7-bc46e888e8e2");
        API.getInstance().getCountriesByLangID(getContext(), langIDHolder, this, "Getting Languages..");
        return view;
    }

    @Override
    public void onArrayReady(List list) {
        Gson gson = new Gson();
        if (list.size() > 0) {
            try {
                if (list.size() == 0) {
                    Toast.makeText(getContext(), "no companions\nmatches\nyour search query.", Toast.LENGTH_SHORT).show();
                } else {
                    CompanionSearch test = (CompanionSearch) list.get(0);
                    companionSearches = list;
                    Intent intent = new Intent(getContext(), CompanionSearchResultActivity.class);
                    intent.putExtra("result", gson.toJson(list));
                    startActivity(intent);
                    Toast.makeText(getContext(), companionSearches.get(0).getCName(), Toast.LENGTH_SHORT).show();
                }
            } catch (ClassCastException e) {
                try {
                    LanguageModel test2 = (LanguageModel) list;
                    languageModels = list;
                    Toast.makeText(getContext(), "Languages" + languageModels.get(0).getLanguageName(), Toast.LENGTH_SHORT).show();
                } catch (ClassCastException e1) {

                }
            }

            try {
                CountriesLangModel countriesLangModel = (CountriesLangModel) list.get(0);
                countriesLangModels = list;
                ArrayAdapter arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.item_spinner_black_text, list);
                fillSpinner.fillSpinner(arrayAdapter, spCountry);
                spCountry.setOnItemSelectedListener(this);
            } catch (ClassCastException e) {
            }

            try {
                CitiesModel citiesModel = (CitiesModel) list.get(0);
                citiesModels = list;
                ArrayAdapter arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.item_spinner_black_text, list);
                fillSpinner.fillSpinner(arrayAdapter, spCity);
                spCity.setOnItemSelectedListener(this);
                spCity.setVisibility(View.VISIBLE);
            } catch (ClassCastException e) {
            }
        }

    }

    @Override
    public void onObjectReady(Object object) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.advancedButton:
                if (linearLayout.getVisibility() == View.GONE) {
                    linearLayout.setVisibility(View.VISIBLE);
                } else {
                    linearLayout.setVisibility(View.GONE);
                }
                break;
            case R.id.txtButtonSearch:
                searchQuery.setUserId(ProfileModel.getInstance().getId());
                searchQuery.setDate(goingDate);
                API.getInstance().searchCompanion(getContext(), searchQuery, this, "Searching for companions..");
//                JSONObject jsonObject = new JSONObject();
//                try {
//                    jsonObject.put("userId", ProfileModel.getInstance().getId());
//                    jsonObject.put("city", ProfileModel.getInstance().getNationalityID());
//                    jsonObject.put("date", getString(R.string.datePrefix) + "559877600" + getString(R.string.datePostfix));
//                    jsonObject.put("gender", null);
//                    jsonObject.put("hourlyrateMore", null);
//                    jsonObject.put("hourlyrateLess", null);
//                    jsonObject.put("worksWithChildren", null);
//                    jsonObject.put("lang", "C06BB0C0-2110-4309-88A7-BC46E888E8E2");
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spCountry:
                CountryLangHolder countryLangHolder = new CountryLangHolder();
                countryLangHolder.setLang("50b8e00d-93ac-40d7-945c-7743b3212cfb");
                countryLangHolder.setCountryId(countriesLangModels.get(position).getCountryID());
                API.getInstance().getCitiesByCountryLangID(getContext(), countryLangHolder, this, "Getting Cities..");
                break;
            case R.id.spCity:
                searchQuery.setCity("A17CFA3A-AE47-4AF1-94F1-4059AF32475C");
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }

    @OnClick(R.id.imagePickDate)
    public void onViewClicked()
    {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        final Calendar cal = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener()
                {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                    {
                        String month = String.valueOf(monthOfYear + 1), day = String.valueOf(dayOfMonth);
                        if (String.valueOf(monthOfYear).length() < 2) {
                            month = "0" + String.valueOf(monthOfYear + 1);
                        }
                        if (String.valueOf(dayOfMonth).length() < 2) {
                            day = "0" + String.valueOf(dayOfMonth);
                        }

                        cal.set(Calendar.YEAR, year);
                        cal.set(Calendar.MONTH, monthOfYear);
                        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        Date startDate = cal.getTime();
                        goingDate = (getString(R.string.datePrefix) + String.valueOf(startDate.getTime()) + getString(R.string.datePostfix));
                        dt = true;
                        txtDate.setText(day + "-" + month + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
