import java.util.ArrayList;

import java.util.Scanner;

public class ParkingManagement implements VehicleParking{

    static ArrayList<Vehicle> car_list,truck_list, two_list, vehicles;
    static Vehicle vehicle;
    static ParkingManagement p;
    static Scanner s = new Scanner(System.in);
    static int[] two,car,truck;
    static int cnt1, cnt2, cnt3, unas_cnt;


    public static void main(String[] args){
        p = new ParkingManagement();

        two = new int[40];
        car = new int[40];
        truck = new int[40];

        car_list = new ArrayList<Vehicle>();
        two_list = new ArrayList<Vehicle>();
        truck_list = new ArrayList<Vehicle>();


        while(true) {
            System.out.println("Enter your choice\n");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Update Allotted time ");
            System.out.println("3. Count all vehicles");
            System.out.println("4. Get the no. of vehicles of a certain category: ");
            System.out.println("5. Count unassigned vehicles ");
            System.out.println("6.EXIT");
            int option = s.nextInt();

            switch(option) {
                case 1: addVehicle();
                    break;

                case 2: vehicleDetails();
                    break;

                case 3: System.out.println("Two Wheeler:"+ two_list.size()+"   "+"Car:"+car_list.size()+"   "+"Truck:"+truck_list.size());
                    break;


                case 4: System.out.println("Enter the category you want ..\n1.Car\n2.Truck\n3.Two wheelers");
                    int ch = s.nextInt();
                    String cate = (ch==1?"car":(ch==2)?"truck":"two");
                    ArrayList<Vehicle> a = p.getAllVehicles(cate);
                    for(int i=0;i<a.size();i++)
                        System.out.println(a.get(i).getParkingDuration() +" "+a.get(i).getVehicleType()+" "+a.get(i).getSlotNo()+" "+a.get(i).getVehicleNo());
                    System.out.println("Count of the vehicles: "+a.size());
                    break;


                case 5: System.out.println(p.countUnassignedVehicles());
                    break;

                case 6: System.exit(0);
                    break;

                default: System.out.println("Wrong option entered! Please try again");
            }
        }
    }


    private static void addVehicle() {

        vehicle = new Vehicle();

        System.out.println("Enter parking duration time: ");
        vehicle.setParkingDuration(s.nextInt());

        System.out.println("Enter vehicle no: ");
        vehicle.setVehicleNo(s.next());

        System.out.println("Enter vehicle type ");
        vehicle.setVehicleType(s.next());

        System.out.println("Do you want to enter the owner details: ");
        String check = s.next();
        if(check.contains("Y")||check.contains("y")) {
            Owner owner = new Owner();
            System.out.println("Enter the name of the Owner ");
            owner.setOwnerName(s.next());

            System.out.println("Enter the address of the owner ");
            owner.setOwnerAddress(s.next());

            System.out.println("Enter the email of the owner ");
            owner.setOwnerEmail(s.next());

            System.out.println("Enter the mobile no. of the owner ");
            owner.setMobileNo(s.nextLong());

            vehicle.setOwner(owner);
        }

        else {
            unas_cnt++;
        }

        System.out.println("Enter the slot no: ");
        int slt = s.nextInt();
        checkForException(slt);
        saveDetails(vehicle,vehicle.getVehicleType().toLowerCase(),slt);
        System.out.println("Two Wheeler:"+ cnt1 +" "+"Car:"+ cnt2 +" "+"Truck"+ cnt3);
    }

    private static void vehicleDetails() {
        System.out.println("Enter the type of the vehicle to filter ");
        String text = s.next();
        System.out.println("The current vehicle parking allotted time is "+vehicle.getParkingDuration()+"\nEnter the required time ");
        int i = s.nextInt();
        p.updateAllottedTime(i,text);
    }

    @Override
    public boolean updateAllottedTime(int i, String s) {
        vehicle.setParkingDuration(i);
        return false;
    }


    @Override
    public int countUnassignedVehicles() {
        return unas_cnt;
    }

    @Override
    public ArrayList<Vehicle> getAllVehicles(String s) {

        vehicles = new ArrayList<Vehicle>();
        if (s.contains("two"))
            vehicles = two_list;
        else if (s.contains("car"))
            vehicles = car_list;
        else if (s.contains("truck"))
            vehicles = truck_list;

        VehicleComparator vehicleComparator = new VehicleComparator();

        System.out.println(vehicles.size());

        for(int i = 0; i< vehicles.size(); i++)
            System.out.println(vehicles.get(i).slotNo+" ");

        for (int i = 0; i < vehicles.size()-1; i++) {
            for (int j = 0; j < vehicles.size()-i-1; j++) {
                if(vehicleComparator.comparator(vehicles.get(j), vehicles.get(j+1))==1){
                    int temp = vehicles.get(i).getSlotNo();
                    vehicles.get(i).setSlotNo(vehicles.get(i+1).getSlotNo());
                    vehicles.get(i+1).setSlotNo(temp);
                }
            }
        }
        return vehicles;
    }

    private static void checkForException(int slot) {
        if(slot>40){
            try {
                throw new InvalidSlotException("Cannot allocate a slot which is more than or equal to 40");
            }
            catch (InvalidSlotException e){
                System.out.println("Sorry! The maximum slot no. is 40");
            }
        }
        else
            vehicle.setSlotNo(slot);
    }

    private static void saveDetails(Vehicle vehicle, String vehicleType, int slot) {

        if(vehicleType.contains("two")) {
            if (two[slot] != 1) {
                two_list.add(vehicle);
                two[slot] = 1;
                cnt1++;
            }
            else {
                try {
                    throw new SlotNotFoundException("No slot allotted!");
                }
                catch (SlotNotFoundException e){
                    e.printStackTrace();
                }
            }
        }

        else if(vehicleType.contains("car")) {
            if (car[slot] != 1) {
                car_list.add(vehicle);
                car[slot] = 1;
                cnt2++;
            }
            else {
                try {
                    throw new SlotNotFoundException("No slot allocated");
                }
                catch (SlotNotFoundException e){
                    System.out.println("Sorry the slot has already been given to some other person.");
                }
            }
        }

        else if(vehicleType.contains("truck")) {
            if (truck[slot] != 1) {
                truck_list.add(vehicle);
                truck[slot] = 1;
                cnt3++;
            }
            else {
                try {
                    throw new SlotNotFoundException("No slot allotted");
                }
                catch (SlotNotFoundException e){
                    System.out.println("Sorry the slot has already been given to some other person.");
                }
            }
        }
    }

}
