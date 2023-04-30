package Dev.UserDemo.Controller;

import Dev.UserDemo.Service.Abstract.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/s3")
@CrossOrigin("*")
public class S3Controller {

    private final S3Service s3Service;

    @PostMapping("/upload")
    public String upload(@RequestParam("file")MultipartFile file){
        return s3Service.saveFile(file);
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> download(@PathVariable("filename") String filename){
        byte[] imageBytes = s3Service.downloadFile(filename);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(imageBytes.length);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

    @DeleteMapping("/{filename}")
    public String deleteFile(@PathVariable("filename") String filename){
        return s3Service.deleteFile(filename);
    }

    @GetMapping("/list")
    public List<String> getAllFiles(){
        return s3Service.listAllFiles();
    }
}
