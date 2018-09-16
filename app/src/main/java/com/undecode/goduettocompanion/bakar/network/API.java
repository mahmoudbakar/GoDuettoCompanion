package com.undecode.goduettocompanion.bakar.network;

import android.content.Context;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.undecode.goduettocompanion.data.Countries;
import com.undecode.goduettocompanion.interfaces.Constants;
import com.undecode.goduettocompanion.models.Bookings;
import com.undecode.goduettocompanion.models.CitiesModel;
import com.undecode.goduettocompanion.models.CompanionProfile;
import com.undecode.goduettocompanion.models.CountriesLangModel;
import com.undecode.goduettocompanion.models.LanguageModel;
import com.undecode.goduettocompanion.models.dataholder.BookingResponse;
import com.undecode.goduettocompanion.models.dataholder.CountryLangHolder;
import com.undecode.goduettocompanion.models.dataholder.CreateBookingHolder;
import com.undecode.goduettocompanion.models.dataholder.LangIDHolder;
import com.undecode.goduettocompanion.models.dataholder.SearchQuery;
import com.undecode.goduettocompanion.models.searchcompanion.CompanionSearch;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class API
{
    private BakarRequests bakarRequests;
    private Gson gson;

    private API()
    {
        this.bakarRequests = BakarRequests.getInstance();
        gson = new Gson();
    }

    private static API api = null;

    public static API getInstance()
    {
        if (api == null)
        {
            api = new API();
        }
        return api;
    }

    public void getMatches(Context context, final OnDataReady listener, String loadingMessage)
    {
        bakarRequests.jsonObjectGetRequest(context, Constants.API.EDIT_PROFILE_DATA + "?now=2018-08-07 10:38:10", new OnBakarReady()
        {
            @Override
            public void onSuccess(JSONObject response)
            {
                listener.onArrayReady(Arrays.asList(gson.fromJson(String.valueOf(response.optJSONArray("matches")), Countries[].class)));
            }

            @Override
            public void onError(VolleyError error)
            {

            }
        }, true, loadingMessage);
    }

    public void searchCompanion(Context context, SearchQuery searchQuery, final OnDataReady listener, String loadingMessage)
    {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId", searchQuery.getUserId());
            jsonObject.put("city", "A17CFA3A-AE47-4AF1-94F1-4059AF32475C");
            jsonObject.put("date", searchQuery.getDate());

            bakarRequests.jsonObjectPostRequest(context, Constants.API.SEARCH_COMPANION, jsonObject,
                    new OnBakarReady()
                    {
                        @Override
                        public void onSuccess(JSONObject response)
                        {
                            listener.onArrayReady(Arrays.asList(gson.fromJson(String.valueOf(response.optJSONArray("SearchCompanionResult")), CompanionSearch[].class)));
                        }

                        @Override
                        public void onError(VolleyError error)
                        {

                        }
                    }, true, loadingMessage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getFavoritesCompanion(Context context, String userID, final OnDataReady onDataReady, String message)
    {
        try
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId", userID);
            bakarRequests.jsonObjectPostRequest(context, Constants.API.FAVORITES_COMPANIONS, jsonObject,
                    new OnBakarReady() {
                        @Override
                        public void onSuccess(JSONObject response)
                        {
                            onDataReady.onArrayReady(Arrays.asList(gson.fromJson(response.optJSONArray("FavoritesCompanionsResult").toString(), CompanionSearch[].class)));
                        }

                        @Override
                        public void onError(VolleyError error)
                        {

                        }
                    }, true, message);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public void getBookings(Context context, String userID, final OnDataReady onDataReady, String message)
    {
        try
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId", userID);
            bakarRequests.jsonObjectPostRequest(context, Constants.API.VIEW_BOOKINGS, jsonObject,
                    new OnBakarReady() {
                        @Override
                        public void onSuccess(JSONObject response)
                        {
                            onDataReady.onArrayReady(Arrays.asList(gson.fromJson(response.optJSONArray("ViewBookingsResult").toString(), Bookings[].class)));
                        }

                        @Override
                        public void onError(VolleyError error)
                        {

                        }
                    }, true, message);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public void createBooking(Context context, CreateBookingHolder bookingHolder, final OnDataReady onDataReady, String message)
    {
        try
        {
            bakarRequests.jsonObjectPostRequest(context, Constants.API.CREATE_BOOKING, new JSONObject(gson.toJson(bookingHolder)),
                    new OnBakarReady()
                    {
                        @Override
                        public void onSuccess(JSONObject response)
                        {
                            onDataReady.onObjectReady(gson.fromJson(response.toString(), BookingResponse.class));
                        }

                        @Override
                        public void onError(VolleyError error)
                        {

                        }
                    }, true, message);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public void getLanguages(Context context, final OnDataReady onDataReady, String message)
    {
        bakarRequests.jsonObjectPostRequest(context, Constants.API.LIST_LANGUAGES, null,
                new OnBakarReady()
                {
                    @Override
                    public void onSuccess(JSONObject response)
                    {
                        onDataReady.onArrayReady(Arrays.asList(gson.fromJson(response.optJSONArray("ListLanguagesResult").toString(), LanguageModel[].class)));
                    }

                    @Override
                    public void onError(VolleyError error)
                    {

                    }
                }, true, message);
    }

    public void getCountriesByLangID(Context context, LangIDHolder langIDHolder, final OnDataReady onDataReady, String message)
    {
        try
        {
            bakarRequests.jsonObjectPostRequest(context, Constants.API.SELECT_COUNTRIES_BY_LANG_ID, new JSONObject(gson.toJson(langIDHolder)),
                    new OnBakarReady()
                    {
                        @Override
                        public void onSuccess(JSONObject response)
                        {
                            onDataReady.onArrayReady(Arrays.asList(gson.fromJson(response.optJSONArray("SelectCountriesByLangIdResult").toString(), CountriesLangModel[].class)));
                        }

                        @Override
                        public void onError(VolleyError error)
                        {

                        }
                    }, true, message);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public void getCitiesByCountryLangID(Context context, CountryLangHolder countryLangHolder, final OnDataReady onDataReady, String message)
    {
        try
        {
            bakarRequests.jsonObjectPostRequest(context, Constants.API.SELECT_CITIES_BY_LANG_AND_COUNTRY_ID, new JSONObject(gson.toJson(countryLangHolder)),
                    new OnBakarReady()
                    {
                        @Override
                        public void onSuccess(JSONObject response)
                        {
                            onDataReady.onArrayReady(Arrays.asList(gson.fromJson(response.optJSONArray("SelectCitiesByLangAndCountryIdResult").toString(), CitiesModel[].class)));
                        }

                        @Override
                        public void onError(VolleyError error)
                        {

                        }
                    }, true, message);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public void getBookDetails(Context context, String tripID, final OnDataReady onDataReady, String message)
    {
        try
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("tripId", tripID);
            bakarRequests.jsonObjectPostRequest(context, Constants.API.VIEW_BOOKING_DETAIL, jsonObject,
                    new OnBakarReady()
                    {
                        @Override
                        public void onSuccess(JSONObject response)
                        {
                            onDataReady.onObjectReady(gson.fromJson(response.optJSONObject("ViewBookingDetailResult").toString(), Bookings.class));
                        }

                        @Override
                        public void onError(VolleyError error)
                        {

                        }
                    }, true, message);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public void startCompanion(Context context, String tripID, String message)
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("tripId", tripID);
            bakarRequests.jsonObjectPostRequest(context, Constants.API.START_COMPANIONSHIP, jsonObject, new OnBakarReady() {
                @Override
                public void onSuccess(JSONObject response) {

                }

                @Override
                public void onError(VolleyError error) {

                }
            }, true, message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void endCompanion(Context context, String tripID, String message)
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("tripId", tripID);
            bakarRequests.jsonObjectPostRequest(context, Constants.API.END_COMPANIONSHIP, jsonObject, new OnBakarReady() {
                @Override
                public void onSuccess(JSONObject response) {

                }

                @Override
                public void onError(VolleyError error) {

                }
            }, true, message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void terminateCompanion(Context context, String tripID, int terminationReason, String message)
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("tripId", tripID);
            jsonObject.put("termsinationReason", terminationReason);
            bakarRequests.jsonObjectPostRequest(context, Constants.API.TERMINATE_COMPANIONSHIP, jsonObject, new OnBakarReady() {
                @Override
                public void onSuccess(JSONObject response) {

                }

                @Override
                public void onError(VolleyError error) {

                }
            }, true, message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void extendCompanion(Context context, String tripID, String extension, String message)
    {
        JSONObject jsonObject = new JSONObject();
        try
        {
            jsonObject.put("tripId", tripID);
            jsonObject.put("extention", extension);
            bakarRequests.jsonObjectPostRequest(context, Constants.API.EXTEND_COMPANIONSHIP, jsonObject, new OnBakarReady()
            {
                @Override
                public void onSuccess(JSONObject response)
                {

                }

                @Override
                public void onError(VolleyError error)
                {

                }
            }, true, message);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public void cancelBooking(Context context, String tripID, String message)
    {
        JSONObject jsonObject = new JSONObject();
        try
        {
            jsonObject.put("tripId", tripID);
            bakarRequests.jsonObjectPostRequest(context, Constants.API.CANCEL_BOOKING, jsonObject, new OnBakarReady()
            {
                @Override
                public void onSuccess(JSONObject response)
                {

                }

                @Override
                public void onError(VolleyError error)
                {

                }
            }, true, message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void deleteBooking(Context context, String tripID, String message)
    {
        JSONObject jsonObject = new JSONObject();
        try
        {
            jsonObject.put("tripId", tripID);
            bakarRequests.jsonObjectPostRequest(context, Constants.API.DELETE_BOOKING, jsonObject, new OnBakarReady()
            {
                @Override
                public void onSuccess(JSONObject response)
                {

                }

                @Override
                public void onError(VolleyError error)
                {

                }
            }, true, message);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public void getCompanionProfile(Context context, String companionID, OnDataReady onDataReady, String message)
    {
        try
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("companionId", companionID);
            bakarRequests.jsonObjectPostRequest(context, Constants.API.COMPANION_PROFILE, jsonObject, new OnBakarReady()
            {
                @Override
                public void onSuccess(JSONObject response)
                {
                    onDataReady.onObjectReady(gson.fromJson(response.optJSONObject("CompanionProfileResult").toString(), CompanionProfile.class));
                }

                @Override
                public void onError(VolleyError error)
                {

                }
            }, true, message);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
