package com.prekdu;

import java.util.*;

// Abstract class for Library Resources
abstract class LibraryResource {
    protected String resourceId;
    protected String title;
    protected boolean availabilityStatus;

    public LibraryResource(String resourceId, String title, boolean availabilityStatus) {
        this.resourceId = resourceId;
        this.title = title;
        this.availabilityStatus = availabilityStatus;
    }

    public abstract double calculateLateFee(int daysLate);

    public abstract int getMaxLoanPeriod();

    public boolean isAvailable() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(boolean availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }
}

// Renewable Interface
interface Renewable {
    boolean renewLoan(LibraryMember member);
}

// Reservable Interface
interface Reservable {
    void reserve(LibraryMember member);
    void cancelReservation(LibraryMember member);
}

// Book Resource
class Book extends LibraryResource implements Renewable, Reservable {
    private String author;
    private String ISBN;

    public Book(String resourceId, String title, String author, String ISBN, boolean availabilityStatus) {
        super(resourceId, title, availabilityStatus);
        this.author = author;
        this.ISBN = ISBN;
    }

    @Override
    public double calculateLateFee(int daysLate) {
        return daysLate * 2.0; // $2 per day
    }

    @Override
    public int getMaxLoanPeriod() {
        return 14; // 14 days loan period
    }

    @Override
    public boolean renewLoan(LibraryMember member) {
        if (availabilityStatus) {
            System.out.println("Book renewed successfully.");
            return true;
        }
        return false;
    }

    @Override
    public void reserve(LibraryMember member) {
        System.out.println("Book reserved by: " + member.getName());
    }

    @Override
    public void cancelReservation(LibraryMember member) {
        System.out.println("Reservation cancelled by: " + member.getName());
    }
}

// DigitalContent Resource
class DigitalContent extends LibraryResource implements Renewable {
    private double fileSize;
    private String format;

    public DigitalContent(String resourceId, String title, double fileSize, String format, boolean availabilityStatus) {
        super(resourceId, title, availabilityStatus);
        this.fileSize = fileSize;
        this.format = format;
    }

    @Override
    public double calculateLateFee(int daysLate) {
        return daysLate * 1.0; // $1 per day
    }

    @Override
    public int getMaxLoanPeriod() {
        return 7; // 7 days loan period
    }

    @Override
    public boolean renewLoan(LibraryMember member) {
        System.out.println("Digital content renewed successfully.");
        return true;
    }
}

// Periodical Resource
class Periodical extends LibraryResource implements Renewable, Reservable {
    private int issueNumber;
    private String frequency;

    public Periodical(String resourceId, String title, int issueNumber, String frequency, boolean availabilityStatus) {
        super(resourceId, title, availabilityStatus);
        this.issueNumber = issueNumber;
        this.frequency = frequency;
    }

    @Override
    public double calculateLateFee(int daysLate) {
        return daysLate * 0.5; // $0.5 per day
    }

    @Override
    public int getMaxLoanPeriod() {
        return 7; // 7 days loan period
    }

    @Override
    public boolean renewLoan(LibraryMember member) {
        if (availabilityStatus) {
            System.out.println("Periodical renewed successfully.");
            return true;
        }
        return false;
    }

    @Override
    public void reserve(LibraryMember member) {
        System.out.println("Periodical reserved by: " + member.getName());
    }

    @Override
    public void cancelReservation(LibraryMember member) {
        System.out.println("Reservation cancelled by: " + member.getName());
    }
}

// Membership Types
enum MembershipType {
    STANDARD, PREMIUM;
}

// LibraryMember Class
class LibraryMember {
    private String memberId;
    private String name;
    private MembershipType membershipType;
    private List<LibraryResource> borrowedResources;

    public LibraryMember(String memberId, String name, MembershipType membershipType) {
        this.memberId = memberId;
        this.name = name;
        this.membershipType = membershipType;
        this.borrowedResources = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void borrowResource(LibraryResource resource) throws Exception {
        if (!resource.isAvailable()) {
            throw new ResourceNotAvailableException("Resource is not available.");
        }

        int maxLoanLimit = membershipType == MembershipType.PREMIUM ? 10 : 5;
        if (borrowedResources.size() >= maxLoanLimit) {
            throw new MaximumLoanExceededException("Loan limit exceeded.");
        }

        borrowedResources.add(resource);
        resource.setAvailabilityStatus(false);
        System.out.println("Resource borrowed successfully: " + resource.title);
    }

    public void returnResource(LibraryResource resource) {
        borrowedResources.remove(resource);
        resource.setAvailabilityStatus(true);
        System.out.println("Resource returned successfully: " + resource.title);
    }
}

// Custom Exceptions
class ResourceNotAvailableException extends Exception {
    public ResourceNotAvailableException(String message) {
        super(message);
    }
}

class MaximumLoanExceededException extends Exception {
    public MaximumLoanExceededException(String message) {
        super(message);
    }
}

class InvalidMembershipException extends Exception {
    public InvalidMembershipException(String message) {
        super(message);
    }
}

// Main Class to Test
public class LibraryManagementSystem {
    public static void main(String[] args) {
        try {
            // Create Resources
            Book book = new Book("B001", "Java Programming", "John Doe", "123456789", true);
            DigitalContent digitalContent = new DigitalContent("D001", "Python Tutorial", 15.5, "PDF", true);
            Periodical periodical = new Periodical("P001", "Tech Weekly", 45, "WEEKLY", true);

            // Create Members
            LibraryMember member1 = new LibraryMember("M001", "Alice", MembershipType.STANDARD);
            LibraryMember member2 = new LibraryMember("M002", "Bob", MembershipType.PREMIUM);

            // Borrow Resources
            member1.borrowResource(book);
            member2.borrowResource(digitalContent);

            // Renew Resource
            book.renewLoan(member1);

            // Return Resource
            member1.returnResource(book);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
