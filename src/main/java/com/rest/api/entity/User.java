package com.rest.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("user_id")
    Long id;

    @JsonProperty("first_name")
    String firstName;

    @JsonProperty("last_name")
    String lastName;

    @JsonProperty("my_username")
    String username;

    @JsonProperty("my_email")
    String email;

    @JsonProperty("my_password")
    String password;

    @JsonProperty("my_phone")
    String phoneNumber;

    @JsonProperty("start_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date startTime;

    @JsonProperty("end_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date endTime;

    @JsonProperty("create_time")
    @JsonFormat(pattern = "HH:mm:ss")
    Date createDate;

    @JsonProperty("last_modified_time")
    @JsonFormat(pattern = "HH:mm:ss")
    Date lastModifiedDate;


}
