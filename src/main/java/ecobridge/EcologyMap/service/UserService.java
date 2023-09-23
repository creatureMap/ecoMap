package ecobridge.EcologyMap.service;

import ecobridge.EcologyMap.domain.User;
import ecobridge.EcologyMap.dto.CreateAccessTokenRequest;
import ecobridge.EcologyMap.dto.UserDTO;
import ecobridge.EcologyMap.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
//유저 정보를 가져오는 인터페이스
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    //사용자 아이디로 사용자의 정보를 가져오는 메서드
    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid User!!"));
    }

    //객체를 인수로 받는 회원 정보 추가 메서드
    public Long save(UserDTO dto) {
        return userRepository.save(User.builder()
                .username(dto.getUsername())
                .password(bCryptPasswordEncoder.encode(dto.getPassword())) //빈을 사용한 패스워드 암호화
                .email(dto.getEmail())
                .build()).getId();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}

