public class PatientBST {
    private PatientNode root;

    public boolean insert(Patient patient) {
        if (search(patient.getPatientId()) != null) {
            return false;
        }
        root = insertRecursive(root, patient);
        return true;
    }

    private PatientNode insertRecursive(PatientNode node, Patient patient) {
        if (node == null) return new PatientNode(patient);
        if (patient.getPatientId() < node.getPatient().getPatientId()) {
            node.setLeft(insertRecursive(node.getLeft(), patient));
        } else {
            node.setRight(insertRecursive(node.getRight(), patient));
        }
        return node;
    }

    public Patient search(int patientId) {
        PatientNode current = root;
        while (current != null) {
            int currentId = current.getPatient().getPatientId();
            if (patientId == currentId) return current.getPatient();
            current = patientId < currentId ? current.getLeft() : current.getRight();
        }
        return null;
    }

    public boolean delete(int patientId) {
        if (search(patientId) == null) return false;
        root = deleteRecursive(root, patientId);
        return true;
    }

    private PatientNode deleteRecursive(PatientNode node, int patientId) {
        if (node == null) return null;
        if (patientId < node.getPatient().getPatientId()) {
            node.setLeft(deleteRecursive(node.getLeft(), patientId));
        } else if (patientId > node.getPatient().getPatientId()) {
            node.setRight(deleteRecursive(node.getRight(), patientId));
        } else {
            if (node.getLeft() == null) return node.getRight();
            if (node.getRight() == null) return node.getLeft();
            PatientNode successor = findMinimum(node.getRight());
            PatientNode replacement = new PatientNode(successor.getPatient());
            replacement.setLeft(node.getLeft());
            replacement.setRight(deleteRecursive(node.getRight(), successor.getPatient().getPatientId()));
            node = replacement;
        }
        return node;
    }

    private PatientNode findMinimum(PatientNode node) {
        while (node.getLeft() != null) node = node.getLeft();
        return node;
    }

    public void displayInOrder() {
        if (root == null) {
            System.out.println("No patient records found.");
            return;
        }
        inOrder(root);
    }

    private void inOrder(PatientNode node) {
        if (node == null) return;
        inOrder(node.getLeft());
        System.out.println(node.getPatient());
        inOrder(node.getRight());
    }
}
