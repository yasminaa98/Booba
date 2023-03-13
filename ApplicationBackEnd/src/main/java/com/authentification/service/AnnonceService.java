package com.authentification.service;

import com.authentification.entities.Annonce;
import com.authentification.repositories.AnnonceRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class AnnonceService {

    @Autowired
    private AnnonceRepository annonceRepository ;

    // Add new announce :

    public Annonce addAnnonce ( String name, String category, String state,
                               String ageChild, String ageToy, String description) {
        Annonce annonce = new Annonce() ;
        annonce.getName();
        annonce.getCategory();
        annonce.getState();
        annonce.getAgeChild();
        annonce.getAgeToy();
        annonce.getDescription();
        return annonceRepository.save(annonce) ;
    }

    // Modify Annonce :

    public Annonce modifyAnnonce(Long id_annonce , String name , String category , String state ,
                                 String ageChild , String ageToy , String description) {
        Annonce annonce = annonceRepository.findById(id_annonce).orElse(null) ;
        if ( annonce == null) {
            return null ;
        }
               annonce.setName(name);
               annonce.setCategory(category);
               annonce.setState(state);
               annonce.setAgeChild(ageChild);
               annonce.setAgeToy(ageToy);
               annonce.setDescription(description);

        return annonceRepository.save(annonce) ;
    }

    //  Archive Annonce :

    public Annonce archiveAnnonce(Long id_annonce) {
        Annonce annonce = annonceRepository.findById(id_annonce).orElse(null) ;
        if (annonce == null) {
            return null ;
        }
        annonce.setEstArchive(true);
        return annonceRepository.save(annonce);
    }

    public Annonce getAnnonceById(Long id_annonce) throws NotFoundException {
        Optional<Annonce> annonceOptional = annonceRepository.findById(id_annonce);
        if (annonceOptional.isPresent()) {
            return annonceOptional.get();
        } else {
            throw new NotFoundException("Annonce with id " + id_annonce + " not found.");
        }
    }
}
