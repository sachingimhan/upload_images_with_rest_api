package lk.sample.controller;

import lk.sample.service.FileStoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/file")
@CrossOrigin
public class UploadController {
    private final FileStoreService service;

    public UploadController(FileStoreService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<URL> uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws MalformedURLException {
        String s = service.saveFile(file, request.getSession().getServletContext().getRealPath("/"));
        URL url = new URL(request.getScheme(), request.getServerName(), request.getServerPort(), "/".concat(s));
        return ResponseEntity.ok(url);
    }

    @GetMapping
    public ResponseEntity getAllFiles(HttpServletRequest request) throws MalformedURLException {
        List<String> files = service.getAllFiles(request.getSession().getServletContext().getRealPath("/"));

        for (int i = 0; i < files.size(); i++) {
            String url = new URL(request.getScheme(), request.getServerName(), request.getServerPort(), files.get(i)).toString();
            files.remove(i);
            files.add(i, url);
        }
        return ResponseEntity.ok().body(files);
    }

}
