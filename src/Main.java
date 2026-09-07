import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PatientBST patientBST = new PatientBST();
    private static final EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static final TreatmentStack treatmentStack = new TreatmentStack();
    private static Patient patientInTreatment;
    private static int nextTreatmentId = 1;

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("      MINI HOSPITAL EMERGENCY SYSTEM");
        System.out.println("========================================");

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = readInt("Enter your choice: ");
            System.out.println();
            switch (choice) {
                case 1 -> registerPatient();
                case 2 -> searchPatient();
                case 3 -> deletePatient();
                case 4 -> patientBST.displayInOrder();
                case 5 -> addToEmergencyQueue();
                case 6 -> emergencyQueue.display();
                case 7 -> callNextPatient();
                case 8 -> completeTreatment();
                case 9 -> treatmentStack.display();
                case 10 -> addPatientVisit();
                case 11 -> removePatientVisit();
                case 12 -> searchPatientVisit();
                case 13 -> displayPatientVisits();
                case 14 -> running = false;
                case 15 -> runSampleDemonstration();
                case 16 -> popLatestTreatment();
                default -> System.out.println("Invalid choice. Please select a number from 1 to 16.");
            }
            System.out.println();
        }
        System.out.println("Thank you for using the Mini Hospital Emergency System.");
    }

    private static void displayMenu() {
        System.out.println("\n1. Register New Patient");
        System.out.println("2. Search Patient");
        System.out.println("3. Delete Patient");
        System.out.println("4. Display All Patients (BST in-order)");
        System.out.println("5. Add Patient to Emergency Queue");
        System.out.println("6. View Emergency Queue");
        System.out.println("7. Call Next Patient for Treatment");
        System.out.println("8. Complete Treatment");
        System.out.println("9. View Treatment History");
        System.out.println("10. Add Patient Visit");
        System.out.println("11. Remove Patient Visit");
        System.out.println("12. Search Patient Visit");
        System.out.println("13. Display Patient Visit History");
        System.out.println("14. Exit");
        System.out.println("15. Run Sample Demonstration");
        System.out.println("16. Pop Most Recent Treatment");
    }

    private static void registerPatient() {
        int id = readPositiveInt("Patient ID: ");
        if (patientBST.search(id) != null) {
            System.out.println("A patient with that ID already exists.");
            return;
        }
        String name = readRequiredText("Patient name: ");
        int age = readAge();
        String contact = readRequiredText("Contact number: ");
        String condition = readRequiredText("Medical condition: ");
        patientBST.insert(new Patient(id, name, age, contact, condition));
        System.out.println("Patient registered successfully in the BST.");
    }

    private static void searchPatient() {
        Patient patient = findPatient();
        if (patient == null) return;
        System.out.println(patient);
    }

    private static void deletePatient() {
        int id = readPositiveInt("Patient ID to delete: ");
        if (patientBST.delete(id)) System.out.println("Patient deleted from the BST.");
        else System.out.println("Patient not found.");
    }

    private static void addToEmergencyQueue() {
        Patient patient = findPatient();
        if (patient == null) return;
        emergencyQueue.enqueue(patient);
        System.out.println("Patient added to the emergency queue.");
    }

    private static void callNextPatient() {
        if (patientInTreatment != null) {
            System.out.println("Complete the current treatment before calling another patient.");
            return;
        }
        patientInTreatment = emergencyQueue.dequeue();
        if (patientInTreatment == null) {
            System.out.println("The emergency queue is empty.");
        } else {
            System.out.println("Now treating: " + patientInTreatment);
        }
    }

    private static void completeTreatment() {
        if (patientInTreatment == null) {
            System.out.println("No patient is currently waiting for treatment completion.");
            return;
        }
        String doctor = readRequiredText("Doctor name: ");
        String diagnosis = readRequiredText("Treatment/diagnosis: ");
        String date = readRequiredText("Date: ");
        TreatmentRecord record = new TreatmentRecord(nextTreatmentId++, patientInTreatment.getPatientId(),
                patientInTreatment.getPatientName(), doctor, diagnosis, date);
        treatmentStack.push(record);
        System.out.println("Treatment completed and pushed onto the stack.");
        patientInTreatment = null;
    }

    private static void popLatestTreatment() {
        TreatmentRecord record = treatmentStack.pop();
        if (record == null) System.out.println("Treatment history is empty.");
        else System.out.println("Removed latest treatment: " + record);
    }

    private static void addPatientVisit() {
        Patient patient = findPatient();
        if (patient == null) return;
        int visitId = readPositiveInt("Visit ID: ");
        String date = readRequiredText("Visit date: ");
        String doctor = readRequiredText("Doctor name: ");
        String diagnosis = readRequiredText("Diagnosis: ");
        String treatment = readRequiredText("Treatment: ");
        if (patient.getVisitHistory().addVisit(new Visit(visitId, date, doctor, diagnosis, treatment))) {
            System.out.println("Visit added to the patient's singly linked list.");
        } else {
            System.out.println("A visit with that ID already exists for this patient.");
        }
    }

    private static void removePatientVisit() {
        Patient patient = findPatient();
        if (patient == null) return;
        int visitId = readPositiveInt("Visit ID to remove: ");
        if (patient.getVisitHistory().removeVisit(visitId)) System.out.println("Visit removed.");
        else System.out.println("Visit not found.");
    }

    private static void searchPatientVisit() {
        Patient patient = findPatient();
        if (patient == null) return;
        int visitId = readPositiveInt("Visit ID to search: ");
        Visit visit = patient.getVisitHistory().searchVisit(visitId);
        if (visit == null) System.out.println("Visit not found.");
        else System.out.println(visit);
    }

    private static void displayPatientVisits() {
        Patient patient = findPatient();
        if (patient != null) patient.getVisitHistory().display();
    }

    private static Patient findPatient() {
        int id = readPositiveInt("Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) System.out.println("Patient not found.");
        return patient;
    }

    private static int readAge() {
        while (true) {
            int age = readInt("Age (1-120): ");
            if (age >= 1 && age <= 120) return age;
            System.out.println("Age must be between 1 and 120.");
        }
    }

    private static int readPositiveInt(String prompt) {
        while (true) {
            int value = readInt(prompt);
            if (value > 0) return value;
            System.out.println("Please enter a positive whole number.");
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static String readRequiredText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) return value;
            System.out.println("This field cannot be empty.");
        }
    }

    private static void runSampleDemonstration() {
        System.out.println("--- Sample BST demonstration ---");
        PatientBST demoTree = new PatientBST();
        Patient p40 = new Patient(40, "Amina", 30, "010-400", "Asthma");
        Patient p20 = new Patient(20, "Brian", 45, "010-200", "Fever");
        Patient p60 = new Patient(60, "Chen", 28, "010-600", "Injury");
        Patient p10 = new Patient(10, "Diana", 35, "010-100", "Allergy");
        Patient p50 = new Patient(50, "Eli", 52, "010-500", "Pain");
        demoTree.insert(p40); demoTree.insert(p20); demoTree.insert(p60); demoTree.insert(p10); demoTree.insert(p50);
        System.out.println("In-order traversal (ascending IDs):");
        demoTree.displayInOrder();
        System.out.println("Search ID 20: " + demoTree.search(20));
        System.out.println("Search ID 999: " + (demoTree.search(999) == null ? "Patient not found" : "found"));
        demoTree.delete(20);
        System.out.println("After deleting ID 20:");
        demoTree.displayInOrder();

        System.out.println("--- Sample Queue demonstration (FIFO) ---");
        EmergencyQueue demoQueue = new EmergencyQueue();
        demoQueue.enqueue(p40); demoQueue.enqueue(p60); demoQueue.enqueue(p10);
        System.out.println("Waiting order:"); demoQueue.display();
        System.out.println("Dequeued first: " + demoQueue.dequeue().getPatientName());
        System.out.println("Dequeued second: " + demoQueue.dequeue().getPatientName());

        System.out.println("--- Sample Stack demonstration (LIFO) ---");
        TreatmentStack demoStack = new TreatmentStack();
        demoStack.push(new TreatmentRecord(1, 40, "Amina", "Dr. Lee", "Medication", "2026-09-01"));
        demoStack.push(new TreatmentRecord(2, 60, "Chen", "Dr. Khan", "Bandage", "2026-09-02"));
        System.out.println("Stack from newest to oldest:"); demoStack.display();
        System.out.println("Popped latest: " + demoStack.pop());

        System.out.println("--- Sample Visit linked-list demonstration ---");
        p40.getVisitHistory().addVisit(new Visit(1, "2026-08-01", "Dr. Lee", "Asthma", "Inhaler"));
        p40.getVisitHistory().addVisit(new Visit(2, "2026-09-01", "Dr. Khan", "Asthma", "Follow-up"));
        p40.getVisitHistory().display();
        System.out.println("Search visit 2: " + p40.getVisitHistory().searchVisit(2));
        p40.getVisitHistory().removeVisit(1);
        System.out.println("After removing visit 1:"); p40.getVisitHistory().display();
    }
}
