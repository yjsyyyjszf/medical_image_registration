package medicalimagebrowser.controller;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import medicalimagebrowser.entity.Instance;
import medicalimagebrowser.entity.Patient;
import medicalimagebrowser.entity.Series;
import medicalimagebrowser.entity.Study;
import medicalimagebrowser.repository.InstanceRepository;
import medicalimagebrowser.repository.PatientRepository;
import medicalimagebrowser.repository.SeriesRepository;
import medicalimagebrowser.repository.StudyRepository;
import medicalimagebrowser.util.DicomUtil;

import javax.transaction.Transactional;

@Controller
public class DetailController {

    private final PatientRepository patientRepository;
    private final StudyRepository studyRepository;
    private final SeriesRepository seriesRepository;
    private final InstanceRepository instanceRepository;

    public DetailController(PatientRepository patientRepository, StudyRepository studyRepository, SeriesRepository seriesRepository, InstanceRepository instanceRepository) {
        this.patientRepository = patientRepository;
        this.studyRepository = studyRepository;
        this.seriesRepository = seriesRepository;
        this.instanceRepository = instanceRepository;
    }

    @GetMapping("/detail/patient/{id}")
    @Transactional
    public String detailPatient(@PathVariable Long id, Model model) {
        Patient patient = patientRepository.findById(id).orElseThrow();
        Hibernate.initialize(patient.getStudies());
        model.addAttribute("patient", patient);
        return "detail/patient";
    }

    @GetMapping("/detail/study/{id}")
    @Transactional
    public String detailStudy(@PathVariable Long id, Model model) {
        Study study = studyRepository.findById(id).orElseThrow();
        Hibernate.initialize(study.getSeries());
        model.addAttribute("study", study);
        return "detail/study";
    }

    @GetMapping("/detail/series/{id}")
    @Transactional
    public String detailSeries(@PathVariable Long id, Model model) {
        Series series = seriesRepository.findById(id).orElseThrow();
        Hibernate.initialize(series.getInstances());
        model.addAttribute("series", series);
        return "detail/series";
    }

    @GetMapping("/detail/instance/{id}")
    @Transactional
    public String detailInstance(@PathVariable Long id, Model model) {
        Instance instance = instanceRepository.findById(id).orElseThrow();
        model.addAttribute("instance", instance);
        return "detail/instance";
    }

    @GetMapping(value = "/metadata/{id}", produces = "application/json")
    @ResponseBody
    public byte[] getMetadata(@PathVariable Long id) {
        return DicomUtil.getMetadata(id);
    }

}
