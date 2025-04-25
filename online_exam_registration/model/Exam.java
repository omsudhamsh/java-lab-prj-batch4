package model;

public class Exam {
    private int examId;
    private String subject;
    private String date;

    public Exam(int examId, String subject, String date) {
        this.examId = examId;
        this.subject = subject;
        this.date = date;
    }

    public int getExamId() {
        return examId;
    }

    public String getSubject() {
        return subject;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return examId + ": " + subject + " on " + date;
    }
}
