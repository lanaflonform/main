package freshstart.domain.airport;

import freshstart.domain.Coordinates;
import freshstart.domain.Location;
import freshstart.domain.PrintService;
import freshstart.domain.TimeInMilliSec;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by aleonets on 21.08.2017.
 */
public class Airport extends Thread implements Location{
    private String airportName;
    private List<Airstrip> airstrips = new ArrayList<>();
    private List<PlaneParkingPlace> parkingPlaces = new ArrayList<>();
    private RadioTower radioTower;
    private PassengerService passengerService;
    private PlaneService planeService;
    private List<Airport> linkedAirports = new ArrayList<>();
    private Coordinates coordinates;

    public String getObjectName() {
        return "Airport("+airportName+")";
    }

    @Override
    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public void run() {
        super.run();
        iniAirport();
        PrintService.printMessageObj("The airport is online.", this);
        while(true){
            try {
                Thread.sleep(TimeInMilliSec.MINUTE.getTimeInMilliSecs());
            }catch (InterruptedException e){
                return;
            }
            PrintService.printMessageObj("Ping.", this);
        }
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getAirportName() {
        return airportName;
    }

    public Airport(String airportName){
        this.airportName = airportName;
        this.start();
    }

    public void addAirstrip(Airstrip airstrip){
        if (airstrips.indexOf(airstrip) == -1){
            airstrips.add(airstrip);
        }
    }

    public List<Airstrip> getAirstrips(){
        return this.airstrips;
    }

    public void addParkingPlace(PlaneParkingPlace parkingPlace){
        if (parkingPlaces.indexOf(parkingPlace) == -1){
            parkingPlaces.add(parkingPlace);
        }
    }

    public List<PlaneParkingPlace> getParkingPlaces(){
        return this.parkingPlaces;
    }

    public RadioTower getRadioTower() {
        return radioTower;
    }

    public PassengerService getPassengerService() {
        return passengerService;
    }

    public PlaneService getPlaneService() {
        return planeService;
    }

    public void setRadioTower(RadioTower radioTower) {
        this.radioTower = radioTower;
    }

    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    public void setPlaneService(PlaneService planeService) {
        this.planeService = planeService;
    }

    public void setLinkedAirports(List<Airport> linkedAirports) {
        for (Airport airport : linkedAirports){
            if(!airport.equals(this) && this.linkedAirports.indexOf(airport) == -1){
                this.linkedAirports.add(airport);
            }
        }
    }

    private void iniAirport(){
        //this.setLinkedAirports(airports);
        int numAirstrips = new Random().nextInt(2)+1;
        int numParks = new Random().nextInt(numAirstrips*10)+numAirstrips;

        for (int i = 0 ; i < numAirstrips ; i++){
            this.addAirstrip(new Airstrip());
        }

        for (int i = 0 ; i < numParks ; i++){
            this.addParkingPlace(new PlaneParkingPlace());
        }
        radioTower = new RadioTower(this);
        passengerService = new PassengerService(this);
        planeService = new PlaneService(this);
        if (coordinates == null){
            setCoordinates(new Coordinates(new Random().nextInt(10000),new Random().nextInt(10000)));
        }
    }
}