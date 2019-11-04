package com.exercises.spring.controller.files;

import com.exercises.spring.model.dto.files.RestTemplateUploadAlbumRequest;
import com.exercises.spring.model.dto.files.UploadAlbumRequest;
import com.exercises.spring.model.dto.files.UploadAlbumResponse;
import com.exercises.spring.model.util.files.MultipartFileData;
import com.exercises.spring.model.util.files.MultipartFileUtil;
import com.exercises.spring.model.util.messages.MessagePair;
import com.exercises.spring.model.util.messages.MessagePairTypeCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.exercises.spring.model.messagescodes.FilesMessageCode.FILE_1101;
import static com.exercises.spring.model.messagescodes.FilesMessageCode.FILE_1102;
import static com.exercises.spring.model.util.messages.MessagePairUtils.messagePair;

@Log4j2
@Controller
@RequestMapping("files")
public class FilesWebController {

    @ResponseBody
    @PostMapping(value = "/uploadAlbum", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UploadAlbumResponse uploadData(@RequestParam(value = "photos") final List<MultipartFile> multipartFileListPhotos, final UploadAlbumRequest uploadAlbumRequest) {

        UploadAlbumResponse uploadAlbumResponse = new UploadAlbumResponse();

        List<MultipartFileData> multipartFileDataListPhotos = new ArrayList<>();
        for (MultipartFile photo : multipartFileListPhotos) {
            try {
                multipartFileDataListPhotos.add(MultipartFileUtil.dataSerializable(photo));
            } catch (IOException e) {
                String desciption = FILE_1101.getDescription() + " : " + photo.getOriginalFilename();
                MessagePair messagePair = new MessagePair(FILE_1101.getCode(), desciption, FILE_1101.getMessagePairTypeCode());
                uploadAlbumResponse.addMessagePair(messagePair);
                log.error("@@@ uploadData() " + desciption, e);
            }
        }

        if (uploadAlbumResponse.has(MessagePairTypeCode.ERROR)) {
            return uploadAlbumResponse;
        }

        RestTemplateUploadAlbumRequest restTemplateUploadAlbumRequest = new RestTemplateUploadAlbumRequest();
        restTemplateUploadAlbumRequest.setPhotos(multipartFileDataListPhotos);
        restTemplateUploadAlbumRequest.setUploadAlbumRequest(uploadAlbumRequest);
        //send by RestTemplate
        //exampleService.send(restTemplateRequest);

        uploadAlbumResponse.setRestTemplateUploadAlbumRequest(restTemplateUploadAlbumRequest);
        uploadAlbumResponse.addMessagePair(messagePair(FILE_1102));

        return uploadAlbumResponse;
    }

}