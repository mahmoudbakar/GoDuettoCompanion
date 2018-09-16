package com.undecode.goduettocompanion.interfaces;

public interface Constants
{

    public interface API
    {
        public static String SERVER = "http://api.citytnc.com/travellar/Service1.svc/";
        public static String SERVER_COMPANION = "http://api.citytnc.com/companion/Service1.svc/";
        public static String POST_FILE = "http://api.citytnc.com/fileupload/service1.svc/postFile";
        public static String LOGIN = SERVER+"login";
        public static String REGISTER = SERVER+"Register";
        public static String COMPANION_PROFILE = SERVER_COMPANION+"CompanionProfile";
        public static String IS_EXISTUSER = SERVER+"IsExistUser";
        public static String IS_BLOCKED = SERVER+"IsBlocked";
        public static String EDIT_PROFILE_DATA = SERVER+"EditProfileData";
        public static String CHANGE_PROFILE_PIC = SERVER+"ChangeProfilePic";
        public static String CHANGE_PASSWORD = SERVER+"ChangePassword";
        public static String RESET_PASSWORD = SERVER+"ResetPassword";
        public static String RETURN_BY_ID = SERVER+"ReturnById";
        public static String CREATE_BOOKING = SERVER+"CreateBooking";
        public static String EDIT_BOOKING = SERVER+"EditBooking";
        public static String CANCEL_BOOKING = SERVER+"CancelBooking";
        public static String DELETE_BOOKING = SERVER+"DeleteBooking";
        public static String SEARCH_COMPANION = SERVER+"SearchCompanion";
        public static String FAVORITES_COMPANIONS = SERVER+"FavoritesCompanions";
        public static String ADD_COMPANIONS_AS_FAVOURITE = SERVER+"AddCompanionsAsFavourite";
        public static String DELETE_COMPANIONSFROM_FAVOURITES = SERVER+"DeleteCompanionsFromFavourites";
        public static String START_COMPANIONSHIP = SERVER+"StartCompanionship";
        public static String END_COMPANIONSHIP = SERVER+"EndCompanionship";
        public static String EXTEND_COMPANIONSHIP = SERVER+"ExtendCompanionship";
        public static String TERMINATE_COMPANIONSHIP = SERVER+"TerminateCompanionship";
        public static String RATE_COMPANION = SERVER+"RateCompanion";
        public static String VIEW_BOOKING_DETAIL = SERVER+"ViewBookingDetail";
        public static String VIEW_BOOKINGS = SERVER+"ViewBookings";
        public static String CHANGE_TRIP_STATUS = SERVER+"ChangeTripStatus";
        public static String LIST_LANGUAGES = SERVER+"ListLanguages";
        public static String LIST_COUNTRIES = SERVER_COMPANION+"ListCountries";
        public static String SELECT_COUNTRIES_BY_LANG_ID = SERVER+"SelectCountriesByLangId";
        public static String SELECT_CITIES_BY_LANG_AND_COUNTRY_ID = SERVER+"SelectCitiesByLangAndCountryId";
        public static String SELECT_CANCELLATION_REASON_BY_LANG = SERVER+"SelectCancellationReasonByLang";
        public static String SELECT_GRANTING_BODIES_BY_LANG = SERVER+"SelectGrantinBodiesByLang";
        public static String SELECT_EDUCATION_LEVEL_BY_LANG = SERVER+"SelectEducationLevelByLang";
        public static String SELECT_EDUCATION_MAJOR_BY_LANG = SERVER+"SelectEducationMajorByLang";
        public static String SELECT_EXPERIENCE_FIELD_BY_LANG = SERVER+"SelectExperienceFieldByLang";
        public static String IS_EXIST_USER_BY_EMAIL = SERVER+"IsExistUserByEmail";
        public static String RESET_PASSWORD_BY_EMAIL = SERVER+"ResetPasswordByEmail";
    }

    public interface JsonKeys
    {
        String PHONE = "mobileNo";
        String PASSWORD = "password";
    }

    public interface Fragments
    {
        String HOME = "home";
        String NOTIFICATION = "notification";
        String PROFILE = "profile";
        String REPORT = "report";
        String SCHEDULE = "schedule";
    }

}
