package com.halit.repository.entity;


import com.halit.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UserProfile {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  Long authid;
  String username;
  String name;
  String email;
  String phone;
  String photo;
  String address;
  String about;
  Long created;
  Long updated;
  @Enumerated(EnumType.STRING)
  @Builder.Default
  Status status= Status.PENDING;



}
