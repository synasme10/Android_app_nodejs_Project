package model;

public class FavouriteModel {
    private String favId;
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
    private String image;
    private String firstName;
    private String lastName;
    private String phone;
    private String state;
    private String city;
    private String address;
    private String email;
    private String gender;


    public String getFavId() {
        return favId;
    }

    public void setFavId(String favId) {
        this.favId = favId;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public FavouriteModel(String employeeId, String userId, String skill, String experiance, String jobCompleted, String language, String payment, String working, String cost, String available, String image, String firstName, String lastName, String phone, String state, String city, String address, String email, String gender) {
        this.employeeId = employeeId;
        this.userId = userId;
        Skill = skill;
        Experiance = experiance;
        JobCompleted = jobCompleted;
        Language = language;
        Payment = payment;
        Working = working;
        Cost = cost;
        Available = available;
        this.image = image;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.state = state;
        this.city = city;
        this.address = address;
        this.email = email;
        this.gender = gender;


    }
}
