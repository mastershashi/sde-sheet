import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

// Enums
enum VehicleType {
    STANDARD, LARGE, MOTORCYCLE, HANDICAPPED
}

enum PaymentType {
    CASH, CREDIT_CARD, UPI
}

// Entities (POJOs)
class Vehicle {
    private String vehicleNumber;
    private VehicleType type;

    public Vehicle(String vehicleNumber, VehicleType type) {
        this.vehicleNumber = vehicleNumber;
        this.type = type;
    }

    // Getters
    public String getVehicleNumber() { return vehicleNumber; }
    public VehicleType getType() { return type; }
}

class Ticket {
    private String ticketNumber;
    private Vehicle vehicle;
    private LocalDateTime entryTime;
    private ParkingSpot assignedSpot;

    public Ticket(Vehicle vehicle, ParkingSpot assignedSpot) {
        this.ticketNumber = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.entryTime = LocalDateTime.now();
        this.assignedSpot = assignedSpot;
    }

    // Getters
    public String getTicketNumber() { return ticketNumber; }
    public Vehicle getVehicle() { return vehicle; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public ParkingSpot getAssignedSpot() { return assignedSpot; }
}

// Interfaces
interface ParkingSpot {
    String getId();
    boolean isAvailable();
    void occupy();
    void release();
    int getDistanceFromEntrance();
    boolean canAccommodate(VehicleType vehicleType);
    VehicleType getSpotType();
}

interface ParkingStrategy {
    ParkingSpot findSpot(VehicleType vehicleType, Set<ParkingSpot> availableSpots);
    void releaseSpot(ParkingSpot spot, Set<ParkingSpot> availableSpots);
}

interface PaymentStrategy {
    void processPayment(double amount);
}

interface ParkingService {
    Ticket parkVehicle(Vehicle vehicle);
    double exitParkingLot(String ticketNumber);
}

interface PaymentService {
    void processPayment(double amount, PaymentType paymentType);
}

// Implementations
class StandardParkingSpot implements ParkingSpot {
    private String id;
    private boolean isAvailable;
    private int distanceFromEntrance;

    public StandardParkingSpot(String id, int distanceFromEntrance) {
        this.id = id;
        this.isAvailable = true;
        this.distanceFromEntrance = distanceFromEntrance;
    }

    @Override
    public String getId() { return id; }
    @Override
    public boolean isAvailable() { return isAvailable; }
    @Override
    public void occupy() { isAvailable = false; }
    @Override
    public void release() { isAvailable = true; }
    @Override
    public int getDistanceFromEntrance() { return distanceFromEntrance; }
    @Override
    public boolean canAccommodate(VehicleType vehicleType) { return vehicleType == VehicleType.STANDARD; }
    @Override
    public VehicleType getSpotType() { return VehicleType.STANDARD; }
}

class LargeParkingSpot implements ParkingSpot {
    private String id;
    private boolean isAvailable;
    private int distanceFromEntrance;

    public LargeParkingSpot(String id, int distanceFromEntrance) {
        this.id = id;
        this.isAvailable = true;
        this.distanceFromEntrance = distanceFromEntrance;
    }

    @Override
    public String getId() { return id; }
    @Override
    public boolean isAvailable() { return isAvailable; }
    @Override
    public void occupy() { isAvailable = false; }
    @Override
    public void release() { isAvailable = true; }
    @Override
    public int getDistanceFromEntrance() { return distanceFromEntrance; }
    @Override
    public boolean canAccommodate(VehicleType vehicleType) { return true; } // Can accommodate any vehicle
    @Override
    public VehicleType getSpotType() { return VehicleType.LARGE; }
}

class MotorcycleParkingSpot implements ParkingSpot {
    private String id;
    private boolean isAvailable;
    private int distanceFromEntrance;

    public MotorcycleParkingSpot(String id, int distanceFromEntrance) {
        this.id = id;
        this.isAvailable = true;
        this.distanceFromEntrance = distanceFromEntrance;
    }

    @Override
    public String getId() { return id; }
    @Override
    public boolean isAvailable() { return isAvailable; }
    @Override
    public void occupy() { isAvailable = false; }
    @Override
    public void release() { isAvailable = true; }
    @Override
    public int getDistanceFromEntrance() { return distanceFromEntrance; }
    @Override
    public boolean canAccommodate(VehicleType vehicleType) { return vehicleType == VehicleType.MOTORCYCLE; }
    @Override
    public VehicleType getSpotType() { return VehicleType.MOTORCYCLE; }
}

class HandicappedParkingSpot implements ParkingSpot {
    private String id;
    private boolean isAvailable;
    private int distanceFromEntrance;

    public HandicappedParkingSpot(String id, int distanceFromEntrance) {
        this.id = id;
        this.isAvailable = true;
        this.distanceFromEntrance = distanceFromEntrance;
    }

    @Override
    public String getId() { return id; }
    @Override
    public boolean isAvailable() { return isAvailable; }
    @Override
    public void occupy() { isAvailable = false; }
    @Override
    public void release() { isAvailable = true; }
    @Override
    public int getDistanceFromEntrance() { return distanceFromEntrance; }
    @Override
    public boolean canAccommodate(VehicleType vehicleType) { return vehicleType == VehicleType.HANDICAPPED; }
    @Override
    public VehicleType getSpotType() { return VehicleType.HANDICAPPED; }
}

class NearestSpotStrategy implements ParkingStrategy {
    @Override
    public ParkingSpot findSpot(VehicleType vehicleType, Set<ParkingSpot> availableSpots) {
        PriorityQueue<ParkingSpot> minHeap = new PriorityQueue<>(
            Comparator.comparingInt(ParkingSpot::getDistanceFromEntrance)
        );

        for (ParkingSpot spot : availableSpots) {
            if (spot.canAccommodate(vehicleType)) {
                minHeap.offer(spot);
            }
        }

        return minHeap.poll();
    }

    @Override
    public void releaseSpot(ParkingSpot spot, Set<ParkingSpot> availableSpots) {
        spot.release();
        availableSpots.add(spot);
    }
}

class ParkingLotService implements ParkingService {
    private static final int CAPACITY = 50000;
    private static final double HOURLY_RATE = 10.0;
    private static final double FIXED_CHARGE = 30.0;

    private Set<ParkingSpot> availableSpots;
    private Map<String, Ticket> activeTickets;
    private ParkingStrategy parkingStrategy;
    private ReentrantLock lock;

    public ParkingLotService(ParkingStrategy parkingStrategy) {
        this.availableSpots = Collections.newSetFromMap(new ConcurrentHashMap<>());
        this.activeTickets = new ConcurrentHashMap<>();
        this.parkingStrategy = parkingStrategy;
        this.lock = new ReentrantLock();

        initializeParkingSpots();
    }

    private void initializeParkingSpots() {
        for (int i = 0; i < CAPACITY; i++) {
            ParkingSpot spot;
            if (i % 100 == 0) {
                spot = new HandicappedParkingSpot("H" + i, i);
            } else if (i % 10 == 0) {
                spot = new LargeParkingSpot("L" + i, i);
            } else if (i % 5 == 0) {
                spot = new MotorcycleParkingSpot("M" + i, i);
            } else {
                spot = new StandardParkingSpot("S" + i, i);
            }
            availableSpots.add(spot);
        }
    }

    @Override
    public Ticket parkVehicle(Vehicle vehicle) {
        lock.lock();
        try {
            ParkingSpot spot = parkingStrategy.findSpot(vehicle.getType(), availableSpots);
            if (spot == null) {
                throw new RuntimeException("No available parking spots");
            }
            spot.occupy();
            availableSpots.remove(spot);
            Ticket ticket = new Ticket(vehicle, spot);
            activeTickets.put(ticket.getTicketNumber(), ticket);
            return ticket;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public double exitParkingLot(String ticketNumber) {
        lock.lock();
        try {
            Ticket ticket = activeTickets.get(ticketNumber);
            if (ticket == null) {
                throw new RuntimeException("Invalid ticket");
            }
            double charge = calculateCharge(ticket);
            parkingStrategy.releaseSpot(ticket.getAssignedSpot(), availableSpots);
            activeTickets.remove(ticketNumber);
            return charge;
        } finally {
            lock.unlock();
        }
    }

    private double calculateCharge(Ticket ticket) {
        LocalDateTime exitTime = LocalDateTime.now();
        long hours = java.time.Duration.between(ticket.getEntryTime(), exitTime).toHours();
        return FIXED_CHARGE + (hours * HOURLY_RATE);
    }
}

class PaymentServiceImpl implements PaymentService {
    private Map<PaymentType, PaymentStrategy> paymentStrategies;

    public PaymentServiceImpl() {
        paymentStrategies = new HashMap<>();
        paymentStrategies.put(PaymentType.CASH, new CashPayment());
        paymentStrategies.put(PaymentType.CREDIT_CARD, new CreditCardPayment());
        paymentStrategies.put(PaymentType.UPI, new UPIPayment());
    }

    @Override
    public void processPayment(double amount, PaymentType paymentType) {
        PaymentStrategy strategy = paymentStrategies.get(paymentType);
        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported payment type");
        }
        strategy.processPayment(amount);
    }
}

class CashPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing cash payment of Rs. " + amount);
    }
}

class CreditCardPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of Rs. " + amount);
    }
}

class UPIPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing UPI payment of Rs. " + amount);
    }
}

class MonitoringSystem {
    private ParkingService parkingService;

    public MonitoringSystem(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    public void monitorParkingLot() {
        System.out.println("Monitoring parking lot status...");
        // Implementation for monitoring parking lot status
    }
}

public class ParkingLotSystem {
    public static void main(String[] args) {
        ParkingStrategy strategy = new NearestSpotStrategy();
        ParkingService parkingService = new ParkingLotService(strategy);
        PaymentService paymentService = new PaymentServiceImpl();
        MonitoringSystem monitoringSystem = new MonitoringSystem(parkingService);

        // Simulate parking and exiting
        Vehicle car = new Vehicle("ABC123", VehicleType.STANDARD);
        Vehicle truck = new Vehicle("XYZ789", VehicleType.LARGE);

        Ticket ticket1 = parkingService.parkVehicle(car);
        Ticket ticket2 = parkingService.parkVehicle(truck);

        // Simulate some time passing
        try {
            Thread.sleep(3600); // 1 minute
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double charge1 = parkingService.exitParkingLot(ticket1.getTicketNumber());
        paymentService.processPayment(charge1, PaymentType.CREDIT_CARD);

        double charge2 = parkingService.exitParkingLot(ticket2.getTicketNumber());
        paymentService.processPayment(charge2, PaymentType.CASH);

        System.out.println("Charge for ticket 1: Rs. " + charge1);
        System.out.println("Charge for ticket 2: Rs. " + charge2);

        monitoringSystem.monitorParkingLot();
    }
}