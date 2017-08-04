package domains;

/**
 * Created by Red8 on 04/08/2017.
 */
public abstract class AbstractAirplane {
    private Location currentLocation;
    private boolean isFueled;

    // Public
    public void refuelPlane(){
        isFueled = true;
    }

    public abstract void takeOff();

    public abstract <T extends PlaneLandableLocation> void toLand(T location);

    // Private
    private void setLocation(Location location){
        this.currentLocation = location;
    }

    private Location getCurrentLocation(){
        return this.currentLocation;
    }
}
