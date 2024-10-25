package apiTestingFramework.pojo;

public class Customer {

    private String name;
    private String email;
    private String description;

    //Getters

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //Constructor

    public Customer(String name, String email, String description) {
        this.name = name;
        this.email = email;
        this.description = description;
    }
}
