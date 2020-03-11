package medicalimagebrowser.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import medicalimagebrowser.entity.Dicmfile;
import medicalimagebrowser.repository.DicmfileRepository;

@Controller
public class DownloadController {
	
	private final DicmfileRepository dicmfileRepository;

    public DownloadController(DicmfileRepository dicmfileRepository) {
        this.dicmfileRepository = dicmfileRepository;
    }

    @GetMapping("/download/{id}")
    public String Download(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
        Dicmfile dicmfile = dicmfileRepository.findById(id).orElse(null);

        File file = new File(dicmfile.getFileurl());
        FileInputStream fis = new FileInputStream(file);
        response.setContentType("application/force-download");
        response.addHeader("Content-disposition","attachment;fileName=" + dicmfile.getFilename());
        OutputStream os = response.getOutputStream();
        byte[] buf = new byte[1024];
        int len = 0;
        while((len = fis.read(buf)) != -1) {
            os.write(buf, 0, len);
        }
        fis.close();
        return "download";
    }
    
	/*
	 * @GetMapping("/dicmfile") public String viewDownload() { return "dicmfile"; }
	 */

}
