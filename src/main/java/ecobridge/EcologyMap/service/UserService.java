package ecobridge.EcologyMap.service;

import ecobridge.EcologyMap.domain.User;
import ecobridge.EcologyMap.dto.AddUserRequest;
import ecobridge.EcologyMap.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override //사용자 아이디로 사용자의 정보를 가져오는 메서드
    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException(username));
    }

    //객체를 인수로 받는 회원 정보 추가 메서드
    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder()
                .username(dto.getUsername())
                .password(bCryptPasswordEncoder.encode(dto.getPassword())) //빈을 사용한 패스워드 암호화
                .build()).getId();
    }
}
