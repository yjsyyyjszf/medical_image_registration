package medicalimagebrowser.util;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.io.DicomInputStream;
import org.dcm4che3.json.JSONWriter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.stream.JsonGenerator;
import java.io.*;
import java.util.Base64;

public final class DicomUtil {

//    private static String DICOM_DIRECTORY = System.getProperty("user.home") + "/DicomDirectory/";
    private static String DICOM_DIRECTORY = "D:/DicomDirectory/";

    public static Attributes getAttributes(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        DicomInputStream dicomInputStream = new DicomInputStream(inputStream);
        Attributes attributes = dicomInputStream.readDataset(-1, -1);
        dicomInputStream.close();
        inputStream.close();
//        toAttributesObject(attributes);
        return attributes;
    }

    /*
    * 保存提取的json数据
    * */
    public static void saveJson(Attributes targetSeriesAttrs, Long id) throws IOException {
//        StringWriter strWriter = new StringWriter();
        File file = new File(DICOM_DIRECTORY, id + ".json");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        JsonGenerator gen = Json.createGenerator(fileOutputStream);
        JSONWriter writer = new JSONWriter(gen);
        writer.write(targetSeriesAttrs);
        gen.flush();
        gen.close();
        fileOutputStream.close();
        //return null;// Json.createReader(new StringReader(strWriter.toString())).readObject();
    }
    /*
    * 保存从dicm中提取的图像信息
    * */
    public static void saveImage(byte[] data, Long id) throws IOException {
        File file = new File(DICOM_DIRECTORY, id + ".dat");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(Base64.getEncoder().encode(data));
//        fileOutputStream.write(data);
        fileOutputStream.flush();
        fileOutputStream.close();
    }
    /*
    * 保存文件      .dat
    * */
    public static void saveFile(byte[] bytes, Long id) throws IOException {
        File file = new File(DICOM_DIRECTORY, id + ".dat");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(bytes);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    /*
    * 保存上传的多个文件
    * */
    public static void saveFile(MultipartFile multipartFile, Long id) throws IOException {
        File file = new File(DICOM_DIRECTORY, id + ".dat");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    /*public static void saveImage(Attributes targetSeriesAttrs, Long id) throws IOException {
        File file = new File(DICOM_DIRECTORY, id + ".img");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(targetSeriesAttrs.getBytes(Tag.PixelData));
        fileOutputStream.flush();
        fileOutputStream.close();
    }*/

    /*public static void save(MultipartFile srcFile) throws IOException {
        String filename = DicomUtil.getSOPInstanceUID(srcFile.getInputStream());
        Path destPath = Paths.get(DICOM_DIRECTORY, filename);
        srcFile.transferTo(destPath);
    }*/

    public static InputStreamResource getJsonFile(String jsonFile) throws FileNotFoundException {
        return new InputStreamResource(new FileInputStream(new File(DICOM_DIRECTORY, jsonFile)));
    }

    public static JsonObject getJsonObject(Long id) throws FileNotFoundException {
        File file = new File(DICOM_DIRECTORY, id + ".json");
        FileInputStream fileInputStream = new FileInputStream(file);
        return Json.createReader(fileInputStream).readObject();
    }

    public static byte[] getImage(Long id) {
        File file = new File(DICOM_DIRECTORY, id + ".dat");
        return getFile(file);
    }

    public static byte[] getMetadata(Long id) {
        File file = new File(DICOM_DIRECTORY, id + ".json");
        return getFile(file);
    }

    private static byte[] getFile(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /*public static String getSOPInstanceUID(InputStream inputStream) throws IOException {
        DicomInputStream dicomInputStream = new DicomInputStream(inputStream);
//        JsonGenerator jsonGen = createGenerator(System.out);
//        JSONWriter jsonWriter = new JSONWriter(jsonGen);
//        dicomInputStream.setDicomInputHandler(jsonWriter);
        Attributes attributes = dicomInputStream.readDataset(-1, -1);
//        jsonGen.flush();
//        jsonGen.close();
        dicomInputStream.close();
        String filename = attributes.getString(Tag.SOPInstanceUID);
        return filename;
    }*/

}
