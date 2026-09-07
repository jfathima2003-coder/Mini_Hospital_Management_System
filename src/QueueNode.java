public class QueueNode {
    private final Patient patient;
    private QueueNode next;

    public QueueNode(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() { return patient; }
    public QueueNode getNext() { return next; }
    public void setNext(QueueNode next) { this.next = next; }
}
