package ecobridge.EcologyMap.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


//사용자의 정보를 담는 인터페이스
@Table(name= "user")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "username", unique = true, length = 11, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)

    private String password;

    @Column(name="email")
    private String email;


    @Builder
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Override //권한 반환
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override //계정 만료 여부 확인
    public boolean isAccountNonExpired() {
        return false; //false는 만료
    }

    @Override //계정 잠금 여부 확인
    public boolean isAccountNonLocked() {
        return false; //false는 잠금
    }

    @Override //패스워드 만료 여부 확인
    public boolean isCredentialsNonExpired() {
        return false; //false는 만료
    }

    @Override //계정 사용 가능 여부 확인
    public boolean isEnabled() {
        return false; //false는 사용 불가
    }
}
