package com.richardson.cruiseports.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.google.appengine.repackaged.com.google.gson.JsonArray;
import com.google.appengine.repackaged.com.google.gson.JsonObject;
import com.google.appengine.repackaged.com.google.gson.JsonParser;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * All ports are initialized and sent to client upon client request
 */

@Api(name="portEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain="richardson.com",
        ownerName="richardson.com.cruise_ports", packagePath=""))
public class PortEndpoint {

        final static int PARKING_LIST = 1,
                         HOTEL_LIST = 2,
                        PLACE_DETAIL = 3;

        List<Port>ports = new ArrayList<>();

        public PortEndpoint(){
            initializePorts();
        }

        private void initializePorts(){
            Port baltimore = new Port("Cruise Maryland", "Baltimore", "2001 East McComas Street/n" +
                    "Baltimore, Maryland 21230", "$15 per day", 39.2649944,-76.5985853);
            baltimore.setAirport("Baltimore International Airport", "11 miles" ,39.1753, -76.6683);
            ports.add(baltimore);

            Port boston = new Port ("Cruiseport Boston", "Boston", "Tide Street/n" +
                    "Boston, Massachusetts 02210", "$16 per day", 42.3479614,-71.0472503);
            boston.setAirport("Logan International Airport", "3.2 miles", 42.3631, -71.0064);
            ports.add(boston);

            Port charleston = new Port("Port of Charleston", "Charleston",
                    "32 Washington Street/n" + "Charleston, South Carolina 29401", "17 per day",
                    32.7861554,-79.9247939);
            charleston.setAirport("Charleston International Airport", "12 miles", 32.8986, -80.0406);
            ports.add(charleston);

            Port fortLauderdale = new Port("Port Everglades", "Fort Lauderdale",
                    "1850 Eller Drive/n" + "Fort Lauderdale, Florida 33316", "$15 per day",
                    26.0937112,-80.1246643);
            fortLauderdale.setAirport("Fort Lauderdale International Airport", "5 miles", 26.0725,
                    -80.1528);
            fortLauderdale.setAirport("Miami International Airport", "30 miles", 25.7933, -80.2906);
            ports.add(fortLauderdale);

            Port galveston = new Port("Texas Cruise Ship Terminal/nOn Galveston Island", "Galveston",
                    "2503 Harborside Drive/n" +
                            "Galveston, Texas 77550", "10 per day", 29.4491434,-94.8944681);
            galveston.setAirport("George Bush Intercontinental Airport", "71 miles",
                    29.9844, -95.3414);
            galveston.setAirport("William P. Hobby Airport", "42 miles", 29.6456, -95.2789);
            ports.add(galveston);

            Port honolulu = new Port("Aloha Tower Cruise Terminal\nPier 2 Cruise Terminal", "Honolulu",
                    "1 Aloha Tower Drive\nHonolulu, HI, 96813", "Parking at terminal not available",
                    21.307929, -157.864446);
            honolulu.setAirport("Honolulu International Airport", " 4.7 miles",
                    21.3186, -157.9225);
            ports.add(honolulu);

            Port jacksonville = new Port("JaxPort Cruise Terminal", "Jacksonville",
                    "9810 August Drive\nJacksonville, FL 32226", "$15 per day",
                    30.4083, -81.5775);
            jacksonville.setAirport("Jacksonville International Airport", "11.6 miles",
                    30.4942, -81.6878);
            ports.add(jacksonville);

            Port longBeach = new Port ("Port of Long Beach","Long Beach", "231 Windsor Way\n" +
                    "Long Beach, CA 90802", "$19 per day", 33.7513521,-118.188752);
            longBeach.setAirport("Los Angeles International Airport", "22.2 miles",
                    33.9425, -118.4081);
            longBeach.setAirport("Long Beach Airport", "11.5 miles", 33.8178, -118.1517);
            ports.add(longBeach);

            Port miami = new Port("Port Miami", "Miami", "1000 Caribbean Way\n " +
                    "Miami, FL 33132", "$20 per day", 25.7774265,-80.1782048);
            miami.setAirport("Miami International Airport", "8.5 miles", 25.7933, -80.2906);
            miami.setAirport("Fort Lauderdale International Airport", "31.6 miles", 26.0725, -80.1528);
            ports.add(miami);

            Port newOrleans = new Port("Port of New Orleans", "New Orleans",
                    "1100 Port of New Orleans Place\n" +
                    "New Orleans, LA 70130", "$18 per day", 29.9367164,-90.0625356);
            newOrleans.setAirport("New Orleans Airport", "16. 6 miles", 29.9933, -90.2581);
            ports.add(newOrleans);

            Port nyBrooklyn = new Port("Brooklyn Cruise Terminal", "New York City - Brooklyn",
                    "72 Bowne Street\nBrooklyn, NY 11231", "18 dollars per day", 40.6820, -74.0143);
            nyBrooklyn.setAirport("John F. Kennedy International Airport", "25.3 miles",
                    40.6397, -73.7789);
            nyBrooklyn.setAirport("LaGuardia International Airport", "16.2 miles",
                    40.7772, -73.8726);

            Port nyManhattan = new Port("Manhattan Cruise Terminal", "New York City - Manhattan",
                    "711 12th Avenue\nNew York, NY 10019", "$40 per day", 40.7680, -73.9966);
            nyManhattan.setAirport("John F. Kennedy International Airport", "25.3 miles",
                    40.6397, -73.7789);
            nyManhattan.setAirport("LaGuardia International Airport", "16.2 miles",
                    40.7772, -73.8726);
            ports.add(nyManhattan);

            Port portCanaveral = new Port ("Port Canaveral", "Port Canaveral (Orlando)",
                    "9050 Discovery Pl\nPort Canaveral, FL 32920", "$16 - $20 per day",
                    28.405512, -80.632573);
            portCanaveral.setAirport("Orlando International Airport", "45.1 miles",
                    28.4294, -81.3089);
            ports.add(portCanaveral);

            Port sanFrancisco = new Port("James R. Herman Cruise Terminal", "San Francisco",
                    "Pier 35 The Embarcadero\nSan Francisco, CA 94133",
                    "Parking at terminal not available", 37.805477, -122.401595);
            sanFrancisco.setAirport("San Francisco International Airport", "15.8 miles",
                    37.6189, -122.3750);
            sanFrancisco.setAirport("Oakland International Airport", "20.9 miles",
                    37.7214, -122.2208);

            Port seattle = new Port("Port of Seattle", "Seattle", "2001 W Garfield St\n" +
                    "Seattle, WA 98119", "$26 per day", 47.632036, -122.380180);
            seattle.setAirport("eattle-Tacoma International Airport", "15 miles",
                    47.4489, -122.3094);
            ports.add(seattle);

            Port tampa = new Port("Tampa Cruise Port", "Tampa", "800 Channelside Dr\n" +
                    "Tampa, FL 33602", "$15 per day", 27.9480575, -82.4488813);
            tampa.setAirport("Tampa International Airport", "9.5 miles", 27.9756, -82.5333);
            ports.add(tampa);

        }

        private String buildUrlString(int portNum, int listNum, String idNum){

            final String keyString = "&key=AIzaSyAKgTrwDaVAicDJjOfSw19sIC5Fh1dl9vI";
            final String parkingQuery = "&radius=10000&type=parking";
            final String hotelQuery = "&radius=5000&type=lodging";

            double latitudeNum = ports.get(portNum).getLatitude();
            double longitudeNum = ports.get(portNum).getLongitude();
            String location = "&location=" + latitudeNum + "," + longitudeNum;

            String urlString = "";

            switch(listNum){

                case PARKING_LIST:
                    urlString = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"
                            + keyString + location + parkingQuery;
                    break;

                case HOTEL_LIST:
                    urlString = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"
                            + keyString + location + hotelQuery;
                    break;

                case PLACE_DETAIL:
                    urlString = "https://maps.googleapis.com/maps/api/place/details/json?" + keyString
                            + "&placeid=" + idNum;
                    break;

            }
            return urlString;

        }

        private void findIDs(int port, String urlString, int listNum){
            URLFetchService fetchService = URLFetchServiceFactory.getURLFetchService();
            JsonParser parser = new JsonParser();

            try{
                URL url = new URL(urlString);
                Future<HTTPResponse> responseFuture = fetchService.fetchAsync(url);

                HTTPResponse httpResponse = responseFuture.get();
                byte[] responseArray = httpResponse.getContent();

                InputStreamReader reader = new InputStreamReader(new ByteArrayInputStream(responseArray));
                JsonObject mainObject = parser.parse(reader).getAsJsonObject();
                JsonArray resultsArray = mainObject.get("results").getAsJsonArray();
                if(resultsArray.size() != 0){
                    for(int objectNum = 0; objectNum < resultsArray.size(); ++objectNum){
                        JsonObject resultsObject = resultsArray.get(objectNum).getAsJsonObject();
                        String placeID = resultsObject.get("place_id").getAsString();

                        getPlaceDetails(placeID, port, listNum);
                    }
                }
            } catch (MalformedURLException ex){
                System.out.println("find IDs build url malformed url");
            } catch (ExecutionException ex){
                System.out.println("find IDs execution exception - responseFuture.get");
            } catch (InterruptedException ex){
                System.out.println("find IDsinterrupted exception - responseFuture.get");
            }
        }

        private void getPlaceDetails(String id, int port, int listNum){
            //port number and list number are not needed for this string
            String urlString = buildUrlString(0, 0, id);

            URLFetchService fetchService = URLFetchServiceFactory.getURLFetchService();
            JsonParser parser = new JsonParser();

            try{
                URL url = new URL(urlString);
                System.out.println("URL: " + urlString);
                Future<HTTPResponse> responseFuture = fetchService.fetchAsync(url);

                HTTPResponse httpResponse = responseFuture.get();
                byte[] responseArray = httpResponse.getContent();

                InputStreamReader reader = new InputStreamReader(new ByteArrayInputStream(responseArray));
                JsonObject mainObject = parser.parse(reader).getAsJsonObject();
                JsonObject resultObject = mainObject.get("result").getAsJsonObject();

                JsonObject geometryObject = resultObject.get("geometry").getAsJsonObject();
                JsonObject locationObject = geometryObject.get("location").getAsJsonObject();
                double latitude = locationObject.get("lat").getAsDouble();
                double longitude = locationObject.get("lng").getAsDouble();

                String address = resultObject.get("formatted_address").getAsString();
                String name = resultObject.get("name").getAsString();


                switch(listNum){
                    case PARKING_LIST:
                        Place parking = new Place(id);
                        parking.setName(name);
                        parking.setAddress(address);
                        parking.setLatitude(latitude);
                        parking.setLongitude(longitude);
                        ports.get(port).addParkingPlace(parking);
                        break;
                    case HOTEL_LIST:
                        Place hotel = new Place(id);
                        hotel.setName(name);
                        hotel.setAddress(address);
                        hotel.setLatitude(latitude);
                        hotel.setLongitude(longitude);
                        ports.get(port).addHotelPlace(hotel);
                        break;
                }

            } catch (MalformedURLException ex){
                System.out.println("Place Details malformed url");
            } catch (ExecutionException ex){
                System.out.println("Place Details execution exception - responseFuture.get");
            } catch (InterruptedException ex){
                System.out.println("Place Details interrupted exception - responseFuture.get");
            }
        }

    @ApiMethod(name="getPorts")
    public List<Port> getPortDetails(){

        for(int portNum = 0; portNum < ports.size(); ++portNum){
            String urlString1 = buildUrlString(portNum, 1, null);
            findIDs(portNum, urlString1, 1);

            String urlString2 = buildUrlString(portNum, 2, null);
            findIDs(portNum, urlString2, 2);
        }

        return ports;
    }


}
