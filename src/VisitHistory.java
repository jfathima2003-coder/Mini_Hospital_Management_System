public class VisitHistory {
    private VisitNode head;

    public boolean isEmpty() { return head == null; }

    public boolean addVisit(Visit visit) {
        if (searchVisit(visit.getVisitId()) != null) return false;
        VisitNode newNode = new VisitNode(visit);
        if (head == null) {
            head = newNode;
        } else {
            VisitNode current = head;
            while (current.getNext() != null) current = current.getNext();
            current.setNext(newNode);
        }
        return true;
    }

    public Visit searchVisit(int visitId) {
        VisitNode current = head;
        while (current != null) {
            if (current.getVisit().getVisitId() == visitId) return current.getVisit();
            current = current.getNext();
        }
        return null;
    }

    public boolean removeVisit(int visitId) {
        VisitNode previous = null;
        VisitNode current = head;
        while (current != null) {
            if (current.getVisit().getVisitId() == visitId) {
                if (previous == null) head = current.getNext();
                else previous.setNext(current.getNext());
                return true;
            }
            previous = current;
            current = current.getNext();
        }
        return false;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("This patient has no visit history.");
            return;
        }
        VisitNode current = head;
        while (current != null) {
            System.out.println(current.getVisit());
            current = current.getNext();
        }
    }
}
