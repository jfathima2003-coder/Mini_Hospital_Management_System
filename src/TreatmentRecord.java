public class TreatmentRecord {
    private final int treatmentId;
    private final int patientId;
    private final String patientName;
    private final String doctorName;
    private final String diagnosis;
    private final String date;

    public TreatmentRecord(int treatmentId, int patientId, String patientName, String doctorName,
                           String diagnosis, String date) {
        this.treatmentId = treatmentId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
        this.date = date;
    }

    public int getTreatmentId() { return treatmentId; }
    public int getPatientId() { return patientId; }
    public String getPatientName() { return patientName; }
    public String getDoctorName() { return doctorName; }
    public String getDiagnosis() { return diagnosis; }
    public String getDate() { return date; }

    @Override
    public String toString() {
        return "Treatment ID: " + treatmentId + " | Patient: " + patientId + " - " + patientName
                + " | Doctor: " + doctorName + " | Diagnosis/Treatment: " + diagnosis + " | Date: " + date;
    }
}
