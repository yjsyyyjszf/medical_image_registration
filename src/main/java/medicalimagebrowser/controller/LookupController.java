package medicalimagebrowser.controller;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import medicalimagebrowser.entity.Patient;
import medicalimagebrowser.entity.Study;
import medicalimagebrowser.model.LookupModel;
import medicalimagebrowser.repository.PatientRepository;
import medicalimagebrowser.repository.StudyRepository;

@Controller
public class LookupController {

    private final PatientRepository patientRepository;

    private final StudyRepository studyRepository;

    public LookupController(PatientRepository patientRepository, StudyRepository studyRepository) {
        this.patientRepository = patientRepository;
        this.studyRepository = studyRepository;
    }

    @GetMapping("/lookup")
    public String lookup() {
        return "lookup";
    }

    @GetMapping("/lookup/patients")
    public String lookupPatients(Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        return "lookup";
    }

    @PostMapping("/lookup")
    public String lookup(LookupModel lookupModel, Model model) {
        Study study = new Study();
        study.setPatient(new Patient());

        if (!"".equals(lookupModel.getPatientID())) study.getPatient().setPatientID(lookupModel.getPatientID());
        if (!"".equals(lookupModel.getPatientName())) study.getPatient().setPatientName(lookupModel.getPatientName());
        if (!"".equals(lookupModel.getAccessionNumber())) study.setAccessionNumber(lookupModel.getAccessionNumber());
        if (!"".equals(lookupModel.getStudyDate())) study.setStudyDate(lookupModel.getStudyDate());
        if (!"".equals(lookupModel.getStudyDescription())) study.setStudyDescription(lookupModel.getStudyDescription());

        model.addAttribute("lookupModel", lookupModel);
        model.addAttribute("studies", studyRepository.findAll(Example.of(study)));
        return "lookup";
    }

}
