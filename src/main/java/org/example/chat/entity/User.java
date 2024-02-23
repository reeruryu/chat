package org.example.chat.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.chat.common.exception.BusinessExceptionHandler;
import org.example.chat.common.exception.ErrorCode;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USER")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "user_name", length = 255, nullable = false)
    private String userName;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    // Firebase Cloud Messaging 토큰 -> 보안
//    private String fcmToken;

    @Builder
    public User(String email, String password, String userName) {
        this.email = email;
        this.password = password;
        this.userName = userName;
    }

    public void updateProfile(Profile profile) {
        if (profile == null)
            throw new BusinessExceptionHandler(ErrorCode.ERROR_001);
        this.profile = profile;
    }

}
