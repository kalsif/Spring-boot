package com.example.ex2services.services;

import com.example.ex2services.DTOs.DownloadResponseDto;
import com.example.ex2services.entities.User;
import com.example.ex2services.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class FileService {

    private final UserRepository userRepository;

    @Value("${uploadDir}")
    private String fileFolder;

    public String upload(MultipartFile file) throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String newFileName = UUID.randomUUID().toString();
        String completeFileName = newFileName + "." + extension;

        File finalFolder = new File(fileFolder);
        if (!finalFolder.exists()) throw new IOException("Final folder does not exists");
        if (!finalFolder.isDirectory()) throw new IOException("Final folder is not a directory");

        File finalDestination = new File(fileFolder + "\\" + completeFileName);
        if (finalDestination.exists()) throw new IOException("File conflict");

        file.transferTo(finalDestination);
        return completeFileName;

        //TODO ASSOCCIARE PERCORSO FOTO AL RELATIVO UTENTE,POTREI FARLO NELLO USER SERVICE

    }

    public DownloadResponseDto download(Long id) throws IOException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            File fileFromRepository = new File(fileFolder + "\\" + user.get().getProfileImg());
            if (!fileFromRepository.exists()) throw new IOException("File does not exists");
            return new DownloadResponseDto(IOUtils.toByteArray(new FileInputStream(fileFromRepository)),
                    user.get().getProfileImg());

        } else {
            return null;
        }
    }


}
