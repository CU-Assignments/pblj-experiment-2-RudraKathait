import java.util.Scanner;

// Video class
class Video {
    String title;
    boolean isCheckedOut;
    double averageRating;
    int ratingCount;
    double totalRating;

    // Constructor
    public Video(String title) {
        this.title = title;
        this.isCheckedOut = false;
        this.averageRating = 0;
        this.ratingCount = 0;
        this.totalRating = 0;
    }

    // Method to check out the video
    public void checkOut() {
        if (!isCheckedOut) {
            isCheckedOut = true;
            System.out.println(title + " has been checked out.");
        } else {
            System.out.println(title + " is already checked out.");
        }
    }

    // Method to return the video
    public void returnVideo() {
        if (isCheckedOut) {
            isCheckedOut = false;
            System.out.println(title + " has been returned.");
        } else {
            System.out.println(title + " was not checked out.");
        }
    }

    // Method to receive a rating
    public void receiveRating(int rating) {
        totalRating += rating;
        ratingCount++;
        averageRating = totalRating / ratingCount;
        System.out.println("New rating for " + title + " is: " + averageRating);
    }

    // Method to display the video info
    public void displayInfo() {
        String status = isCheckedOut ? "Checked out" : "Available";
        System.out.println("Title: " + title + ", Status: " + status + ", Average Rating: " + averageRating);
    }
}

// VideoStore class
class VideoStore {
    Video[] inventory;
    int inventoryCount;

    // Constructor
    public VideoStore(int size) {
        inventory = new Video[size];
        inventoryCount = 0;
    }

    // Method to add a new video to the inventory
    public void addVideo(String title) {
        if (inventoryCount < inventory.length) {
            inventory[inventoryCount++] = new Video(title);
            System.out.println(title + " has been added to the inventory.");
        } else {
            System.out.println("Inventory is full! Cannot add " + title);
        }
    }

    // Method to check out a video
    public void checkOut(String title) {
        for (int i = 0; i < inventoryCount; i++) {
            if (inventory[i].title.equals(title)) {
                inventory[i].checkOut();
                return;
            }
        }
        System.out.println(title + " is not available in the inventory.");
    }

    // Method to return a video
    public void returnVideo(String title) {
        for (int i = 0; i < inventoryCount; i++) {
            if (inventory[i].title.equals(title)) {
                inventory[i].returnVideo();
                return;
            }
        }
        System.out.println(title + " is not found in the inventory.");
    }

    // Method to receive a rating for a video
    public void receiveRating(String title, int rating) {
        for (int i = 0; i < inventoryCount; i++) {
            if (inventory[i].title.equals(title)) {
                inventory[i].receiveRating(rating);
                return;
            }
        }
        System.out.println(title + " is not available for rating.");
    }

    // Method to list all inventory
    public void listInventory() {
        System.out.println("Video Store Inventory:");
        for (int i = 0; i < inventoryCount; i++) {
            inventory[i].displayInfo();
        }
    }
}

// Main class to test the system
public class VideoRentalStore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VideoStore store = new VideoStore(10);

        while (true) {
            System.out.println("\n--- Video Rental Store ---");
            System.out.println("1. Add a new video");
            System.out.println("2. List inventory");
            System.out.println("3. Check out a video");
            System.out.println("4. Return a video");
            System.out.println("5. Receive a rating");
            System.out.println("6. Exit");
            System.out.print("Please select an option (1-6): ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter the title of the new video: ");
                    String title = scanner.nextLine();
                    store.addVideo(title);
                    break;
                case 2:
                    store.listInventory();
                    break;
                case 3:
                    System.out.print("Enter the title of the video to check out: ");
                    title = scanner.nextLine();
                    store.checkOut(title);
                    break;
                case 4:
                    System.out.print("Enter the title of the video to return: ");
                    title = scanner.nextLine();
                    store.returnVideo(title);
                    break;
                case 5:
                    System.out.print("Enter the title of the video to rate: ");
                    title = scanner.nextLine();
                    System.out.print("Enter your rating (1-5): ");
                    int rating = scanner.nextInt();
                    store.receiveRating(title, rating);
                    break;
                case 6:
                    System.out.println("Exiting the system...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
