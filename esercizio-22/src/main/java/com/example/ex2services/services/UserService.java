package com.example.ex2services.services;

import com.example.ex2services.DTOs.DownloadResponseDto;
import com.example.ex2services.DTOs.UserRequestDto;
import com.example.ex2services.DTOs.UserResponseDto;
import com.example.ex2services.entities.User;
import com.example.ex2services.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FileService fileService;

    public UserResponseDto createUser(UserRequestDto user) {

        User entityToSave = user.convertRequestToEntity();
        User savedEntity = userRepository.saveAndFlush(entityToSave);
        return savedEntity.convertEntityToUserResponse();
    }

    public List<UserResponseDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(User::convertEntityToUserResponse)
                .toList();
    }

    public UserResponseDto getUser(Long id) {

        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get().convertEntityToUserResponse();
        } else {
            return new UserResponseDto();
        }
    }

    public String upload(Long id, MultipartFile file) throws IOException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            String photoImg = fileService.upload(file);
            user.get().setProfileImg(photoImg);
            userRepository.saveAndFlush(user.get());
            return photoImg;
        } else {
            return null;
        }


    }

    public DownloadResponseDto download(Long id) throws IOException {
        return fileService.download(id);
    }
}
