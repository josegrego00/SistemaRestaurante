package pd.proyectomrburger.proyectomrburger.models.responseDTO;

public class UserResponseDTO {

    private String username;
    private String email;
    private String role;
    private Boolean enabled;

    public UserResponseDTO() {
    }

    public UserResponseDTO(String username, String email, String role, Boolean enabled) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}
