public class EmergencyQueue {
    private QueueNode front;
    private QueueNode rear;

    public boolean isEmpty() { return front == null; }

    public void enqueue(Patient patient) {
        QueueNode newNode = new QueueNode(patient);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
    }

    public Patient dequeue() {
        if (isEmpty()) return null;
        Patient patient = front.getPatient();
        front = front.getNext();
        if (front == null) rear = null;
        return patient;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("The emergency queue is empty.");
            return;
        }
        QueueNode current = front;
        int position = 1;
        while (current != null) {
            System.out.println(position++ + ". " + current.getPatient());
            current = current.getNext();
        }
    }
}
