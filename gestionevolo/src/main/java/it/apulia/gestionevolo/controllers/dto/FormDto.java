package it.apulia.gestionevolo.controllers.dto;

public class FormDto {

    private String firstName;
    private String lastName;
    private Long flightId;

    public FormDto(String firstName, String lastName, Long flightId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.flightId = flightId;
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

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

}
