public class StackNode {
    private final TreatmentRecord record;
    private StackNode next;

    public StackNode(TreatmentRecord record) {
        this.record = record;
    }

    public TreatmentRecord getRecord() { return record; }
    public StackNode getNext() { return next; }
    public void setNext(StackNode next) { this.next = next; }
}
