class Seat {
    private boolean booked;

    public Seat() {
        this.booked = false;
    }

    public synchronized boolean book() {
        if (!booked) {
            booked = true;
            return true;
        }
        return false;
    }
}

class BookingSystem {
    private Seat[] seats;

    public BookingSystem(int numberOfSeats) {
        seats = new Seat[numberOfSeats];
        for (int i = 0; i < numberOfSeats; i++) {
            seats[i] = new Seat();
        }
    }

    public void bookSeat(int seatNumber) {
        if (seatNumber >= 0 && seatNumber < seats.length) {
            if (seats[seatNumber].book()) {
                System.out.println(Thread.currentThread().getName() + " successfully booked seat " + seatNumber);
            } else {
                System.out.println(Thread.currentThread().getName() + " could not book seat " + seatNumber + " (already booked)");
            }
        } else {
            System.out.println("Invalid seat number");
        }
    }
}

class BookingThread extends Thread {
    private BookingSystem bookingSystem;
    private int seatNumber;

    public BookingThread(BookingSystem bookingSystem, int seatNumber) {
        this.bookingSystem = bookingSystem;
        this.seatNumber = seatNumber;
    }

    @Override
    public void run() {
        bookingSystem.bookSeat(seatNumber);
    }
}

public class TicketBookingSystem {
    public static void main(String[] args) {
        BookingSystem bookingSystem = new BookingSystem(10);

        Thread vip1 = new BookingThread(bookingSystem, 5);
        vip1.setPriority(Thread.MAX_PRIORITY);

        Thread vip2 = new BookingThread(bookingSystem, 5);
        vip2.setPriority(Thread.MAX_PRIORITY);

        Thread regular1 = new BookingThread(bookingSystem, 5);
        regular1.setPriority(Thread.NORM_PRIORITY);

        vip1.start();
        vip2.start();
        regular1.start();
    }
}
