# Mini Hospital Emergency Management System

## Project Description

A Java console-based hospital system for the CIT300 Data Structures and Algorithms individual assignment. It manages patient records, emergency treatment order, completed treatment history, and each patient's previous visits.

The project is intentionally written with beginner-friendly custom data structures so that every important operation can be explained during a university demonstration.

## Objectives

- Store and search patient records using a Binary Search Tree.
- Manage emergency patients in FIFO order with a linked queue.
- Store completed treatments in LIFO order with a linked stack.
- Keep a separate singly linked visit history for every patient.
- Validate normal user input without crashing.

## Technologies Used

- Java
- `Scanner` for console input
- No external libraries
- No built-in collection classes for the four core structures

## Data Structures Used

### 1. Binary Search Tree: Patient Records

`PatientBST` stores `Patient` objects in `PatientNode` objects. The patient ID is the key. Smaller IDs go left and larger IDs go right. Searching follows the same comparisons. In-order traversal visits left subtree, current node, then right subtree, producing ascending patient IDs.

Insertion rejects duplicate IDs. Deletion handles a leaf, a node with one child, and a node with two children. For two children, the smallest node in the right subtree replaces the deleted node.

### 2. Linked Queue: Emergency Patients

`EmergencyQueue` uses `QueueNode` objects and maintains `front` and `rear` references. New patients are linked at the rear. The next patient is removed from the front, so the first patient added is the first patient treated.

### 3. Linked Stack: Treatment History

`TreatmentStack` uses `StackNode` objects and a `top` reference. A completed `TreatmentRecord` is pushed at the top. Pop removes the top record, so the most recently completed treatment is displayed first.

### 4. Singly Linked List: Patient Visit History

Every `Patient` owns one `VisitHistory`. The history links `VisitNode` objects from `head` to the end. It supports adding, searching, removing, and traversing visits. Visit IDs are unique within one patient's history.

## Features

- Register a patient with ID, name, age, contact number, and medical condition.
- Search and delete patient records.
- Display all patients in sorted patient-ID order.
- Add patients to and view the emergency queue.
- Call the next patient and complete their treatment.
- View or pop completed treatment records.
- Add, remove, search, and display patient visits.
- Reject duplicate patient and visit IDs.
- Handle missing patients, visits, empty queues, and empty treatment history.
- Reject non-numeric values, invalid ages, and blank text fields.
- Run a built-in sample demonstration from menu option 15.

## Project Structure

```text
Mini_Hospital_Management_System/
├── README.md
├── out/                         # Generated .class files after compilation
└── src/
    ├── Main.java
    ├── Patient.java
    ├── PatientNode.java
    ├── PatientBST.java
    ├── QueueNode.java
    ├── EmergencyQueue.java
    ├── TreatmentRecord.java
    ├── StackNode.java
    ├── TreatmentStack.java
    ├── Visit.java
    ├── VisitNode.java
    └── VisitHistory.java
```

## Class Structure

- `Main`: menu, workflow, input validation, and sample demonstration.
- `Patient`: patient details and the patient's `VisitHistory`.
- `PatientNode`: a BST node containing one patient.
- `PatientBST`: insert, search, delete, and in-order traversal.
- `QueueNode`: one linked queue node.
- `EmergencyQueue`: enqueue, dequeue, display, and empty check.
- `TreatmentRecord`: completed treatment data.
- `StackNode`: one linked stack node.
- `TreatmentStack`: push, pop, display, and empty check.
- `Visit`: one historical hospital visit.
- `VisitNode`: one linked-list visit node.
- `VisitHistory`: add, remove, search, display, and empty check.

## Compile and Run

From the project root:

```text
javac -d out src\*.java
java -cp out Main
```

On macOS or Linux, use `/` instead of `\` in the commands:

```text
javac -d out src/*.java
java -cp out Main
```

## Sample Operations

1. Register patient `40`, then register patients `20`, `60`, `10`, and `50`.
2. Select option 4. The BST displays IDs in the order `10, 20, 40, 50, 60`.
3. Add patients `40`, `20`, and `60` to the queue. Option 7 calls `40` first, proving FIFO.
4. Complete treatments for multiple patients. Option 9 displays the newest treatment first, proving LIFO.
5. Use option 16 to pop the latest treatment and demonstrate LIFO removal.
6. Add two visits to patient `40`, search for one visit, remove one visit, and display the updated list.
7. Select option 15 to run all these demonstrations automatically.

## Example Output

```text
--- Sample BST demonstration ---
In-order traversal (ascending IDs):
ID: 10 | Name: Diana | Age: 35 | Contact: 010-100 | Condition: Allergy
ID: 20 | Name: Brian | Age: 45 | Contact: 010-200 | Condition: Fever
ID: 40 | Name: Amina | Age: 30 | Contact: 010-400 | Condition: Asthma
ID: 50 | Name: Eli | Age: 52 | Contact: 010-500 | Condition: Pain
ID: 60 | Name: Chen | Age: 28 | Contact: 010-600 | Condition: Injury
Search ID 20: ID: 20 | Name: Brian | Age: 45 | Contact: 010-200 | Condition: Fever
Search ID 999: Patient not found

--- Sample Queue demonstration (FIFO) ---
Dequeued first: Amina
Dequeued second: Chen

--- Sample Stack demonstration (LIFO) ---
Popped latest: Treatment ID: 2 | Patient: 60 - Chen | Doctor: Dr. Khan | Diagnosis/Treatment: Bandage | Date: 2026-09-02
```

## Testing Scenarios

### BST

- Insert at least five patients with unsorted IDs.
- Display all patients and confirm ascending order.
- Search for an existing and a missing ID.
- Attempt a duplicate ID.
- Delete a leaf, a one-child node, and a two-child node.

### Queue

- Enqueue three patients.
- Display the queue.
- Dequeue repeatedly and confirm insertion order.
- Dequeue once more after it becomes empty.

### Stack

- Complete several treatments.
- Display treatment history.
- Pop repeatedly and confirm reverse completion order.
- Pop once more after it becomes empty.

### Visit List

- Add multiple visits for one patient.
- Search for an existing and missing visit.
- Remove a visit and display the updated list.
- Display an empty patient's history.

### Input Validation

- Enter letters where a number is required.
- Enter an age outside 1-120.
- Enter blank text.
- Use duplicate patient or visit IDs.
- Use a missing patient ID.

## Suggested Progressive GitHub Commits

1. `Initial project structure`
2. `Added Patient class`
3. `Implemented Patient BST insertion`
4. `Added BST search and traversal`
5. `Added BST deletion`
6. `Implemented emergency queue`
7. `Implemented treatment stack`
8. `Implemented patient visit linked list`
9. `Added input validation and main menu`
10. `Added sample demonstration and testing`
11. `Updated README`

These are suggestions only. Create the commits yourself; this project does not contain fake Git history.

## Short Demonstration Video Script

1. Introduce the system and explain that patient IDs are stored in a custom BST.
2. Register five patients with unsorted IDs and show in-order traversal sorting them.
3. Search for an existing ID, search for a missing ID, and delete one patient.
4. Add three patients to the emergency queue and call them to show FIFO order.
5. Complete two treatments and display the treatment stack to show the newest record first.
6. Add, search, remove, and display visits for one patient to demonstrate the singly linked list.
7. Mention that all four structures use custom nodes and that invalid input is handled by `Scanner` validation.

## Author

**Student:** Add your name here

**Course:** CIT300 – Data Structures and Algorithms
