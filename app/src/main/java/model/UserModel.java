package model;

public class UserModel {
    private String userId;
    private String image;
    private String firstName;
    private String lastName;
    private String phone;
    private String state;
    private String city;
    private String address;
    private String gender;
    private String email;
    private String password;
    private String usertype;

    public UserModel(String image, String firstName, String lastName, String phone, String state, String city, String address, String gender, String email, String password, String usertype) {
        this.image = image;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.state = state;
        this.city = city;
        this.address = address;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.usertype = usertype;


    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

//    @Override
//    public String toString() {
//        return "Heroes{" +
//                "_id='" + _id + '\'' +
//                ", name='" + name + '\'' +
//                ", desc='" + desc + '\'' +
//                ", image='" + image + '\'' +
//                ", comments=" + comments +
//                '}';
//    }
}
