package com.example.Amadeus.util;

public class Constants {
    public static final String[] AIRPORT_CITIES = {
            "Adana", "Ankara", "Antalya", "Bursa", "Diyarbakır", "Erzurum", "Eskişehir", "Gaziantep",
            "İzmir", "Istanbul", "Mersin", "Samsun", "Trabzon", "Istanbul", "Adıyaman", "Afyonkarahisar",
            "Adana", "Aksaray", "Alanya", "Amasya"
    };
    public static final String CITY_REQUIRED = "airportCity is required";
    public static final String AIRPORT_CREATION_SUCCESS = "Airport Created Successfully";

    public static final String AIRPORT_RETRIEVAL_SUCCESS = "Airport retrieved Successfully";

    public static final String DEPARTURE_AIRPORT_REQUIRED = "Departure Airport must be specified";


    public static final String FLIGHT_CREATION_SUCCESS = "Flight created Successfully";

    public static final String FLIGHT_RETRIEVAL_SUCCESS = "Flight retrieved Successfully";

    public static final String USER_CREATION_SUCCESS = "User created Successfully";

    public static final String ID_REQUIRED = "Id must be specified";

    public static final int ID_LOWER_LIMIT = 1;

    public static final String ID_LOWER_LIMIT_FAILED = "Given id should be numerical value above 0";

    public static final String DEPARTURE_AIRPORT_PLACE_REQUIRED = "departure airport should be set";

    public static final String ARRIVAL_AIRPORT_PLACE_REQUIRED = "departure airport should be set";

    public static final String USERNAME_REQUIRED = "Username is required";

    public static final String PASSWORD_REQUIRED = "Password is required";

    public static final int PASSWORD_LENGTH_LIMIT = 8;


    public static final String PASSWORD_LENGTH_LIMIT_FAILED = "Password must be at least 8 characters long";



    public static final String ARRIVAL_AIRPORT_REQUIRED = "Arrival Airport must be specified";

    public static final String RETURN_DATE_REQUIRED = "returnDateTime must be specified";

    public static final String DEPARTURE_DATE_REQUIRED = "Departure date must be specified";

    public static final String INVALID_USERNAME = "Invalid username";
    public static final int NUMBER_OF_MOCK_FLIGHTS = 100;

    public static final int MAX_DAYS_TO_FLIGHT = 10;

    public static final int MAX_DAYS_UNTIL_RETURN = 10;

    public static final String MOCK_API_ERROR = "Invalid username";

    public static final String NO_SUCH_FLIGHT = "There is no flight with this id";



    public static final String DATE_MISMATCH_DEPARTURE_AFTER_RETURN = "Departure time cannot be after return time";

    public static final String AIRPORT_MISMATCH = "Arrival and departure airports cannot be the same";

    public static final String NO_SUITABLE_FLIGHT = "No suitable flight";


    public static final String PRICE_REQUIRED = "Price must be specified";

    public static final String NO_AIRPORT_IN_DB = "There is no airport present yet";

    public static final String NO_FLIGHT_IN_DB = "There is no flight present yet";

    public static final String NO_SUCH_AIRPORT = "There is no airport with this Id";

    public static final String AIRPORT_UPDATE_SUCCESS = "Airport Updated successfully";
    public static final String FLIGHT_UPDATE_SUCCESS = "Flight Updated successfully";

    public static final String RETURN_DATE_SHOULD_BE_FUTURE = "Return date must be in the future";

    public static final String DEPARTURE_DATE_SHOULD_BE_FUTURE = "Departure date must be in the future";
    public static final int PRICE_PRECISION = 10;

    public static final int PRICE_SCALE = 2;



    public static final int MAX_CITY_NAME_LENGTH = 255;

    public static final int USERNAME_MAX_LENGTH = 255;

    public static final int PASSWORD_MAX_LENGTH = 255;


    public static final double MIN_FLIGHT_PRICE = 1000.0;
    public static final double MAX_FLIGHT_PRICE = 10000000.0;
}
