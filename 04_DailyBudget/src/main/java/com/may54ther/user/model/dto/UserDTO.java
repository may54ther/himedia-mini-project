package com.may54ther.user.model.dto;

import com.may54ther.user.model.type.RoleType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserDTO {

    private int code;
    private String id;
    private String password;
    private String name;
    private LocalDate birth;
    private RoleType role;
    private String enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserDTO() {}

    public UserDTO(String id, String password) {
        this(id, password, null, null, null, null, null, null);
    }

    public UserDTO(String id, String password, String name, LocalDate birth, RoleType role, String enabled, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.role = role;
        this.enabled = enabled;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public void setBirth(String birth) {
        this.birth = birth.isEmpty() ? null : LocalDate.parse(birth);
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isAdminRole() {
        return role.equals(RoleType.ADMIN);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "code=" + code +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", birth=" + birth +
                ", role='" + role + '\'' +
                ", enabled='" + enabled + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
