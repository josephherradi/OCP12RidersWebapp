package com.ocp12.ridewebapp;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.ocp12.ridebusiness.SortieManager;
import com.ocp12.ridebusiness.UtilisateurManager;
import com.ocp12.ridebusiness.businessRules.Brules;
import com.ocp12.ridebusiness.exceptions.FunctionalException;
import com.ocp12.ridemodele.Participant;
import com.ocp12.ridemodele.Sortie;
import com.ocp12.ridemodele.Utilisateur;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.transaction.Transactional;


@DatabaseSetup("/data.xml")
@SpringBootTest(classes={RideWebappApplication.class})
@ExtendWith(SpringExtension.class)

@TestExecutionListeners({
        TransactionalTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@Transactional
@TestPropertySource(locations="/application.properties")

public class RideWebappApplicationTests {
    @Autowired
    private Brules businessrules;

    @Autowired
    private SortieManager sortieManager;

    @Autowired
    private UtilisateurManager utilisateurManager;


    @Test
    public void checkRG1() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdentifiant("user");
        Assertions.assertThrows(FunctionalException.class, (() -> {
            businessrules.checkUserComment(utilisateur, 1);
        }));
    }

    @Test
    public void checkRG1bis() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdentifiant("userTest");
        Assertions.assertDoesNotThrow(() -> {
            businessrules.checkUserComment(utilisateur, 2);
        });

    }

    @Test
    public void checkRG2() {
        Assertions.assertThrows(FunctionalException.class, (() -> {
            businessrules.checkSortieStatut(2);
        }));
    }


    @Test
    public void checkRG2bis() {
        Assertions.assertDoesNotThrow(() ->{businessrules.checkSortieStatut(1);});
    }

    @Test
    public void checkRG3(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdentifiant("user");
        Assertions.assertThrows(FunctionalException.class,()->{businessrules.checkUserParticipant(utilisateur,1);});

    }

    @Test
    public void checkRG3bis(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdentifiant("userTest");
        Assertions.assertDoesNotThrow(()->{businessrules.checkUserParticipant(utilisateur,1);});

    }

    @Test
    public void checkRG4(){
        Utilisateur utilisateur=new Utilisateur();
        utilisateur.setIdentifiant("user");
        Assertions.assertThrows(FunctionalException.class,()->{businessrules.checkUserOrganisateur(utilisateur,1);});

    }

    @Test
    public void checkRG4bis(){
        Utilisateur utilisateur=new Utilisateur();
        utilisateur.setIdentifiant("userTest");
        Assertions.assertDoesNotThrow(()->{businessrules.checkUserOrganisateur(utilisateur,1);});
    }

    @Test
    public void checkRG5(){
        Participant participant =new Participant();
        Sortie sortie=new Sortie();
        sortie.setSortieId(2);
        participant.setSortie(sortie);
        Assertions.assertThrows(FunctionalException.class,()->{businessrules.checkParticipantsNumber(participant);});
    }

    @Test
    public void checkRG5bis(){
        Participant participant =new Participant();
        Sortie sortie=new Sortie();
        sortie.setSortieId(1);
        participant.setSortie(sortie);
        Assertions.assertDoesNotThrow(()->businessrules.checkParticipantsNumber(participant));

    }

    @Test void checkRG6(){
        Participant participant =new Participant();

        Utilisateur utilisateur=utilisateurManager.findByIdentifiant("userTest");

        Sortie sortie=sortieManager.findById(4);

        participant.setSortie(sortie);
        participant.setUtilisateur(utilisateur);

        Assertions.assertThrows(FunctionalException.class,()->{businessrules.checkAlreadJoinedSortie(participant);});

    }



    @Test
    public void checkRG6bis(){
        Participant participant =new Participant();

        Utilisateur utilisateur=utilisateurManager.findByIdentifiant("test");

        Sortie sortie=sortieManager.findById(4);

        participant.setSortie(sortie);
        participant.setUtilisateur(utilisateur);
        Assertions.assertDoesNotThrow(()->{businessrules.checkAlreadJoinedSortie(participant);});
    }

    @Test
    public void checkRG7(){
        Participant participant=new Participant();
        Sortie sortie=sortieManager.findById(1);
        participant.setSortie(sortie);

        Assertions.assertThrows(FunctionalException.class,()->{businessrules.checkStatutSortieBeforeJoinParticipant(participant);});

    }

    @Test
    public void checkRG7bis(){
        Participant participant=new Participant();
        Sortie sortie=sortieManager.findById(2);
        participant.setSortie(sortie);
        Assertions.assertDoesNotThrow(()->{businessrules.checkStatutSortieBeforeJoinParticipant(participant);});

    }

    @Test
    public void checkRG8(){
    String identifiant="userTest";
    Assertions.assertDoesNotThrow(()->{businessrules.checkRegistredUser(identifiant);});
    }

    @Test
    public void checkRG8bis(){
        String identifiant="test";
        Assertions.assertThrows(FunctionalException.class,()->{businessrules.checkRegistredUser(identifiant);});
    }



}




