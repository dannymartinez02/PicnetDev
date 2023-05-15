package Controllers;

import Model.Image;
import Services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Model.Image image = imageService.findById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(image.getData().length);
        return new ResponseEntity<>(image.getData(), headers, HttpStatus.OK);
    }

    @PostMapping("/image")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        Model.Image image = new Model.Image(file.getBytes(), file.getContentType(), 100, 100);
        imageService.save(image);
        return "redirect:/";
    }




    @PostMapping("/image/{id}/delete")
    public String deleteImage(@PathVariable Long id) {
        imageService.deleteById(id);
        return "redirect:/";
    }
}
