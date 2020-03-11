package medicalimagebrowser.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import medicalimagebrowser.entity.Dicmfile;
import medicalimagebrowser.model.ResponseModel;
import medicalimagebrowser.repository.DicmfileRepository;

import java.util.List;

@Controller
@RequestMapping("/dicmfile")
public class DicmfileController {

    private final DicmfileRepository dicmfileRepository;

    public DicmfileController(DicmfileRepository dicmfileRepository) {
        this.dicmfileRepository = dicmfileRepository;
    }
    @GetMapping("/list")
    public String getDicmFile(){ return "dicmfile"; }

    @GetMapping
    @ResponseBody
    public ResponseModel<Dicmfile> userList() {
        ResponseModel<Dicmfile> responseModel = new ResponseModel<>();
        List<Dicmfile> dicmfiles = dicmfileRepository.findAll();
        responseModel.setRows(dicmfiles);
        responseModel.setTotal(dicmfiles.size());
        return responseModel;
    }
}
