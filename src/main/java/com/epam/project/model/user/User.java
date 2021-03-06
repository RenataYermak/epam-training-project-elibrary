package com.epam.project.model.user;

import java.sql.Timestamp;
import java.util.Objects;

public class User {
    private Long id;
    private String login;
    private String password;
    private Role role;
    private Status status;
    private Timestamp activationDate;
    private Timestamp deactivationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Timestamp getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Timestamp activationDate) {
        this.activationDate = activationDate;
    }

    public Timestamp getDeactivationDate() {
        return deactivationDate;
    }

    public void setDeactivationDate(Timestamp deactivationDate) {
        this.deactivationDate = deactivationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && login.equals(user.login) &&
                password.equals(user.password) && role == user.role &&
                status == user.status && activationDate.equals(user.activationDate) &&
                Objects.equals(deactivationDate, user.deactivationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, role, status, activationDate, deactivationDate);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", activationDate=" + activationDate +
                ", deactivationDate=" + deactivationDate +
                '}';
    }
}
