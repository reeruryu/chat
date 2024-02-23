package org.example.chat.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PROFILE")
public class Profile {
    @Id
    @Column(name = "profile_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /*@Column(name = "profile_image_url", length = 255)
    private String profileImageUrl;*/

    @Column(name = "nickname", length = 255, nullable = false)
    private String nickname;

    /*@Column(name = "introduction", columnDefinition = "TEXT")
    private String introduction;*/

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Profile(String profileImageUrl, String nickname, String introduction, User user){
        //this.profileImageUrl = profileImageUrl;
        this.nickname = nickname;
        //this.introduction = introduction;
        this.user = user;
    }

}

