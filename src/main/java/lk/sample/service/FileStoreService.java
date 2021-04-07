package lk.sample.service;

import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.util.List;

public interface FileStoreService {
    String saveFile(MultipartFile file,String rootPath) throws MalformedURLException;

    List<String> getAllFiles(String root);

    String getFile(String fileName);
}
