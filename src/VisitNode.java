public class VisitNode {
    private final Visit visit;
    private VisitNode next;

    public VisitNode(Visit visit) {
        this.visit = visit;
    }

    public Visit getVisit() { return visit; }
    public VisitNode getNext() { return next; }
    public void setNext(VisitNode next) { this.next = next; }
}
