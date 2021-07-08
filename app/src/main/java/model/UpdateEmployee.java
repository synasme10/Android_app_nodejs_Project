package model;

public class UpdateEmployee {
    private String employeeId;
    private String userId;
    private String Skill;
    private String Experiance;
    private String JobCompleted;
    private String Language;
    private String Payment;
    private String Working;
    private String Cost;
    private String Available;

    public UpdateEmployee(String skill, String experiance, String jobCompleted, String language, String payment, String working, String cost, String available) {
        Skill = skill;
        Experiance = experiance;
        JobCompleted = jobCompleted;
        Language = language;
        Payment = payment;
        Working = working;
        Cost = cost;
        Available = available;


    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSkill() {
        return Skill;
    }

    public void setSkill(String skill) {
        Skill = skill;
    }

    public String getExperiance() {
        return Experiance;
    }

    public void setExperiance(String experiance) {
        Experiance = experiance;
    }

    public String getJobCompleted() {
        return JobCompleted;
    }

    public void setJobCompleted(String jobCompleted) {
        JobCompleted = jobCompleted;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getPayment() {
        return Payment;
    }

    public void setPayment(String payment) {
        Payment = payment;
    }

    public String getWorking() {
        return Working;
    }

    public void setWorking(String working) {
        Working = working;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public String getAvailable() {
        return Available;
    }

    public void setAvailable(String available) {
        Available = available;
    }
}
