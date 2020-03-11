package medicalimagebrowser.controller;

import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import medicalimagebrowser.entity.Instance;
import medicalimagebrowser.entity.Series;
import medicalimagebrowser.model.ImageModel;
import medicalimagebrowser.repository.InstanceRepository;
import medicalimagebrowser.repository.SeriesRepository;
import medicalimagebrowser.util.DicomUtil;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class ViewerController {

    final private InstanceRepository instanceRepository;

    public ViewerController(InstanceRepository instanceRepository) {
        this.instanceRepository = instanceRepository;
    }

    @GetMapping("/viewer/{id}")
    @Transactional
    public String viewer(@PathVariable Long id, Model model) {
        Instance instance = instanceRepository.findById(id).orElseThrow();
        List<Long> ids = instance.getSeries().getInstances().stream().map(Instance::getId).collect(Collectors.toList());
        model.addAttribute("id", id);
        model.addAttribute("ids", ids);
        return "viewer";
    }

    @GetMapping("/image/{id}")
    @ResponseBody
    public ImageModel getImage(@PathVariable Long id) {
        Optional<Instance> optionalInstance = instanceRepository.findById(id);
        ImageModel imageModel = new ImageModel();
        optionalInstance.ifPresent(instance -> BeanUtils.copyProperties(instance, imageModel));
        imageModel.setBase64PixelData(new String(DicomUtil.getImage(id)));
        return imageModel;
    }

}
