package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.project.model.Patient;
import com.example.project.service.PatientService;
import ai.djl.ModelException;
import ai.djl.inference.Predictor;
import ai.djl.modality.Classifications;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.ImageFactory;
import ai.djl.modality.cv.translator.ImageClassificationTranslator;
import ai.djl.modality.cv.util.NDImageUtils;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.translate.TranslateException;
import ai.djl.translate.Translator;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.UUID;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("numeroDossier") String numeroDossier,
            @RequestParam("hospitalName") String hospitalName,
            @RequestPart("image") MultipartFile image) throws IllegalStateException, IOException {

        UUID uuid = UUID.randomUUID();
        String jsonId = uuid.toString();

        String fileName = image.getOriginalFilename();
        Path filePath = Paths.get("/Downloads", fileName);
        image.transferTo(filePath.toFile());
       

 
       String diagnosis = predictPneumonia(filePath);

        Patient patient = new Patient(jsonId, nom, prenom, fileName.toString(), numeroDossier, hospitalName, diagnosis);
        return patientService.sauvgarder(patient);
    }

   private String predictPneumonia(Path imagePath) {
        try {
            Translator<Image, Classifications> translator =
                    ImageClassificationTranslator.builder()
                            .addTransform(a -> NDImageUtils.resize(a, 224).div(255.0f))
                            .optSynset(Arrays.asList("Normal", "Pneumonia"))
                            .build();

            Criteria<Image, Classifications> criteria =
                    Criteria.builder()
                            .setTypes(Image.class, Classifications.class)
                            .optModelPath(Paths.get("C:/Users/fatim/Downloads/mon_modele"))
                            .optModelName("serve")
                            .optTranslator(translator)
                            .build();

            try (ZooModel<Image, Classifications> model = criteria.loadModel();
                 Predictor<Image, Classifications> predictor = model.newPredictor()) {

                Image image = ImageFactory.getInstance().fromFile(imagePath);
                Classifications result = predictor.predict(image);

                
                return result.best().getClassName();
            }
        } catch (IOException | TranslateException | ModelException e) {
            e.printStackTrace();
            return "Prediction Error";
        }
   
