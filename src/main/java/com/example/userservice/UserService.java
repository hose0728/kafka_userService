package com.example.userservice;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void signUp(SignUpRequestDto signUpRequestDto){
        User user = new User(signUpRequestDto.getEmail(),
                signUpRequestDto.getName(),
                signUpRequestDto.getPassword());

        User savedUser = userRepository.save(user);
        UserSignedUpEvent userSignedUpEvent = new UserSignedUpEvent(savedUser.getId(), savedUser.getEmail(), savedUser.getName());
        kafkaTemplate.send("user.signed-up", toJsonString(userSignedUpEvent));
    }

    private String toJsonString(Object object){
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}
