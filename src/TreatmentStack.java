public class TreatmentStack {
    private StackNode top;

    public boolean isEmpty() { return top == null; }

    public void push(TreatmentRecord record) {
        StackNode newNode = new StackNode(record);
        newNode.setNext(top);
        top = newNode;
    }

    public TreatmentRecord pop() {
        if (isEmpty()) return null;
        TreatmentRecord record = top.getRecord();
        top = top.getNext();
        return record;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Treatment history is empty.");
            return;
        }
        StackNode current = top;
        while (current != null) {
            System.out.println(current.getRecord());
            current = current.getNext();
        }
    }
}
