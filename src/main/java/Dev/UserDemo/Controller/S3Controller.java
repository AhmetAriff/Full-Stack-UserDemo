package Dev.UserDemo.Controller;

import Dev.UserDemo.Service.Abstract.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;

    @PostMapping("upload")
    public String upload(@RequestParam("file")MultipartFile file){
        return s3Service.saveFile(file);
    }

    @GetMapping("download/{filename}")
    public byte[] download(@PathVariable("filename") String filename){
        return s3Service.downloadFile(filename);
    }

    @DeleteMapping("{filename}")
    public String deleteFile(@PathVariable("filename") String filename){
        return s3Service.deleteFile(filename);
    }

    @GetMapping("list")
    public List<String> getAllFiles(){
        return s3Service.listAllFiles();
    }
}
