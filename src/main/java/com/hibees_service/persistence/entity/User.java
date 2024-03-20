package com.hibees_service.persistence.entity;


import com.hibees_service.core.util.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "full_name")
    private String fullname;
    @Column(name = "phone_number")
    private String phonenumber;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "password")
    private String password;

    @Column(name = "last_login_date")
    private Date lastLoginDate;

    @Column(name = "last_login_ip")
    private String lastLoginIp;

    @Column(name = "user_lock")
    private Integer userLock;

    @Column(name = "user_lock_date")
    private Date userLockDate;

    @Column(name = "last_password_reset_date")
    private Date lastPasswordResetDate;

    @Basic(optional = false)
    @Column(name = "user_level")
    private int userLevel;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "role")
    private Set<UserRole> roles;

    @Column(name = "reset_token")
    private String resetToken;

    @Column(name = "reset_token_expiry_datetime")
    private LocalDateTime resetTokenExpiryDateTime;

      @ManyToOne
      @JoinColumn(name = "favorite_id")
      private Favorites favorites;

//    @ElementCollection(targetClass = UserRole.class)
//    @Enumerated(EnumType.STRING)
//    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
//    @Column(name = "role")
//    private Set<UserRole> roles;

}
