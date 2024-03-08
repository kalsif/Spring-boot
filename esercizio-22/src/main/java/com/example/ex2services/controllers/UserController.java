package com.example.ex2services.controllers;

import com.example.ex2services.DTOs.DownloadResponseDto;
import com.example.ex2services.DTOs.UserRequestDto;
import com.example.ex2services.DTOs.UserResponseDto;
import com.example.ex2services.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public UserResponseDto create(@RequestBody UserRequestDto user) {
        return userService.createUser(user);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/user/{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/user/photo/{id}")
    @ResponseBody
    public byte[] downloadPhoto(@PathVariable Long id,
                                HttpServletResponse response) throws IOException {
        DownloadResponseDto down = userService.download(id);
        String fileName = down.getFileName();

        String extension = FilenameUtils.getExtension(fileName);
        switch (extension) {
            case "gif":
                response.setContentType(MediaType.IMAGE_GIF_VALUE);
                break;
            case "jpg":
            case "jpeg":
                response.setContentType(MediaType.IMAGE_JPEG_VALUE);
                break;
            case "png":
                response.setContentType(MediaType.IMAGE_PNG_VALUE);
                break;
        }
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        return down.getPhoto();
    }

    @PostMapping("/user/photo/{id}")
    public String upload(@PathVariable Long id, @RequestParam MultipartFile file) throws IOException {
        return userService.upload(id, file);
    }


}
