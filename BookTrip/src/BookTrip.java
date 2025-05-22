// PRINCIPLE: SRP (Single Responsibility Principle)

abstract class Trip {
    public abstract void book();
}

// PATTERN: Factory Pattern - for creating trip objects
class BusTrip extends Trip {
    public void book() {
        System.out.println("Bus trip booked.");
    }
}

class TrainTrip extends Trip {
    public void book() {
        System.out.println("Train trip booked.");
    }
}

// Factory class to create trips based on type
class TripFactory {
    public static Trip createTrip(String type) {
        if (type.equalsIgnoreCase("bus")) {
            return new BusTrip();
        } else if (type.equalsIgnoreCase("train")) {
            return new TrainTrip();
        }
        return null;
    }
}

// PRINCIPLE: SRP & High Cohesion - this class only handles trip booking
class TripService {
    public void bookTrip(Trip trip) {
        trip.book();
    }
}

// PRINCIPLE: SRP & Low Coupling - payment logic is separated from booking
class PaymentService {
    public void processPayment() {
        System.out.println("Payment processed successfully.");
    }
}

// PATTERN: Controller Pattern

class TripController {
    private TripService tripService = new TripService();
    private PaymentService paymentService = new PaymentService();

    public void bookTrip(String tripType) {
        Trip trip = TripFactory.createTrip(tripType);
        if (trip != null) {
            tripService.bookTrip(trip);              
            paymentService.processPayment();          
            System.out.println("Trip booking complete!");
        } else {
            System.out.println("Invalid trip type.");
        }
    }
}


public class BookTrip {
    public static void main(String[] args) {
        TripController controller = new TripController();
        controller.bookTrip("bus");   
    }
}

