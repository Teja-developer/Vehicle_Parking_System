import java.util.ArrayList;
import java.util.HashMap;

public interface VehicleParking {

    int  countUnassignedVehicles();
    ArrayList<Vehicle> getAllVehicles(String s);
    boolean updateAllottedTime(int i,String s);

}
