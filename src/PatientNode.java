public class PatientNode {
    private final Patient patient;
    private PatientNode left;
    private PatientNode right;

    public PatientNode(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() { return patient; }
    public PatientNode getLeft() { return left; }
    public PatientNode getRight() { return right; }
    public void setLeft(PatientNode left) { this.left = left; }
    public void setRight(PatientNode right) { this.right = right; }
}
