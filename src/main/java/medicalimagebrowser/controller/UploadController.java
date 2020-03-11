package medicalimagebrowser.controller;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
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
import java.io.IOException;
import java.util.Optional;

@Controller
public class UploadController {

    private final PatientRepository patientRepository;
    private final StudyRepository studyRepository;
    private final SeriesRepository seriesRepository;
    private final InstanceRepository instanceRepository;

    public UploadController(PatientRepository patientRepository, StudyRepository studyRepository, SeriesRepository seriesRepository, InstanceRepository instanceRepository) {
        this.patientRepository = patientRepository;
        this.studyRepository = studyRepository;
        this.seriesRepository = seriesRepository;
        this.instanceRepository = instanceRepository;
    }

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @PostMapping("/upload")
    public String upload(MultipartFile[] dicomFiles, Model model) throws IOException {
        for (MultipartFile dicomFile : dicomFiles) {
            save(dicomFile);
        }
        model.addAttribute("message", "上传成功！");
        return "upload";
//        StorageUtil.save(dicomFile);
//        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/")
//            .path(dicomFile.getOriginalFilename())
//            .toUriString();
    }

    @Transactional
    void save(MultipartFile dicomFile) throws IOException {

        Attributes attributes = DicomUtil.getAttributes(dicomFile);

        // 获取 patientID ，如果不存在则创建
        String patientID = attributes.getString(Tag.PatientID);
        Optional<Patient> optionalPatient = patientRepository.findByPatientID(patientID);

        Patient patient = optionalPatient.orElse(getPatientFromAttributes(attributes));
        if (optionalPatient.isEmpty()) {
            patientRepository.save(patient);
        }

        // 获取 studyID ，如果不存在则创建
        String studyID = attributes.getString(Tag.StudyID);
        Optional<Study> optionalStudy = studyRepository.findByStudyID(studyID);

        Study study = optionalStudy.orElse(getStudyFromAttributes(attributes));
        study.setPatient(patient);

        if (optionalStudy.isEmpty()) {
            studyRepository.save(study);
        }

        // 获取 seriesID ，不存在则创建
        String seriesInstanceUID = attributes.getString(Tag.SeriesInstanceUID);
        Optional<Series> optionalSeries = seriesRepository.findBySeriesInstanceUID(seriesInstanceUID);

        Series series = optionalSeries.orElse(getSeriesFromAttributes(attributes));
        series.setStudy(study);

        if (optionalSeries.isEmpty()) {
            seriesRepository.save(series);
        }

        // 创建 instance
        String SOPInstanceUID = attributes.getString(Tag.SOPInstanceUID);
        Optional<Instance> optionalInstance = instanceRepository.findBySOPInstanceUID(SOPInstanceUID);

        Instance instance = optionalInstance.orElse(getInstanceFromAttributes(attributes));
        instance.setSeries(series);

        if (optionalInstance.isEmpty()) {
            instanceRepository.save(instance);
        }

        // 单独存放图片
        byte[] pixelData = attributes.getBytes(Tag.PixelData);
        DicomUtil.saveImage(pixelData, instance.getId());
        attributes.remove(Tag.PixelData);
        DicomUtil.saveJson(attributes, instance.getId());

    }

    private Patient getPatientFromAttributes(Attributes attributes) {
        Patient patient = new Patient();
        patient.setPatientID(attributes.getString(Tag.PatientID));
        patient.setPatientName(attributes.getString(Tag.PatientName));
        patient.setPatientBirthDate(attributes.getString(Tag.PatientBirthDate));
        patient.setPatientSex(attributes.getString(Tag.PatientSex));
        return patient;
    }

    private Study getStudyFromAttributes(Attributes attributes) {
        Study study = new Study();
        study.setStudyID(attributes.getString(Tag.StudyID));
        study.setStudyDate(attributes.getString(Tag.StudyDate));
        study.setStudyInstanceUID(attributes.getString(Tag.StudyInstanceUID));
        study.setStudyDescription(attributes.getString(Tag.StudyDescription));
        study.setAccessionNumber(attributes.getString(Tag.AccessionNumber));
        study.setReferringPhysicianName(attributes.getString(Tag.ReferringPhysicianName));
        return study;
    }

    private Series getSeriesFromAttributes(Attributes attributes) {
        Series series = new Series();
        series.setSeriesDescription(attributes.getString(Tag.SeriesDescription));
        series.setSeriesInstanceUID(attributes.getString(Tag.SeriesInstanceUID));
        series.setSeriesNumber(attributes.getString(Tag.SeriesNumber));
        series.setNumberOfTemporalPositions(attributes.getString(Tag.NumberOfTemporalPositions));
        series.setStationName(attributes.getString(Tag.StationName));
        return series;
    }

    private Instance getInstanceFromAttributes(Attributes attributes) {
        Instance instance = new Instance();
        instance.setImageIndex(attributes.getString(Tag.ImageIndex));
        instance.setSOPInstanceUID(attributes.getString(Tag.SOPInstanceUID));
        instance.setRows(attributes.getInt(Tag.Rows, 512));
        instance.setColumns(attributes.getInt(Tag.Columns, 512));
        instance.setWindowWidth(attributes.getDouble(Tag.WindowWidth, 512));
        instance.setWindowCenter(attributes.getDouble(Tag.WindowCenter, 512));
        return instance;
    }

}
