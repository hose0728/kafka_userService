package com.example.userservice;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void signUp(SignUpRequestDto signUpRequestDto){
        User user = new User(signUpRequestDto.getEmail(),
                signUpRequestDto.getName(),
                signUpRequestDto.getPassword());

        userRepository.save(user);
    }

}
