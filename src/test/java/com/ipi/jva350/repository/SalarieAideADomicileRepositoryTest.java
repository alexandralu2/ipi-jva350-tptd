package com.ipi.jva350.repository;

import com.ipi.jva350.model.SalarieAideADomicile;
import com.ipi.jva350.service.SalarieAideADomicileService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class SalarieAideADomicileRepositoryTest {

    @Autowired
    private SalarieAideADomicileRepository salarieAideADomicileRepository;

    /**
     * Réinitialise la base avant chaque test
     * (en fait utile uniquement si on utilisait SalarieAideADomicileService.creerSalarieAideADomicile()
     * au lieu de salarieAideADomicileRepository.save(new SalarieAideADomicile(...))
     */
    @BeforeEach
    void setUp() {
        salarieAideADomicileRepository.deleteAll();
    }

    @Test
    void findByNom() {
        // Given (aurait aussi pu être mis en méthode @BeforeAll pour toutes les autres méthodes de test)
        SalarieAideADomicile aide = new SalarieAideADomicile("Jeanne",
                LocalDate.of(2021, 7, 1), LocalDate.now(),
                0, 0, 9,
                1, 0);
        salarieAideADomicileRepository.save(aide);

        // When
        SalarieAideADomicile res = salarieAideADomicileRepository.findByNom(aide.getNom());
        // Then
        assertNotNull(res);
    }

    @Test
    void testPartCongesPrisTotauxAnneeNMoins1Avec2() {
        SalarieAideADomicile aide = new SalarieAideADomicile("Jeanne",
                LocalDate.of(2021, 7, 1), LocalDate.now(),
                0, 0, 9,
                4, 2);

        salarieAideADomicileRepository.save(aide);
        Double res = salarieAideADomicileRepository.partCongesPrisTotauxAnneeNMoins1();

        Assertions.assertEquals(0.5, res);
    }

    @Test
    void testPartCongesPrisTotauxAnneeNMoins1Avec0() {
        SalarieAideADomicile aide = new SalarieAideADomicile("Jeanne",
                LocalDate.of(2021, 7, 1), LocalDate.now(),
                0, 0, 9,
                4, 0);

        salarieAideADomicileRepository.save(aide);
        Double res = salarieAideADomicileRepository.partCongesPrisTotauxAnneeNMoins1();

        Assertions.assertEquals(0, res);
    }

    @Test
    void testPartCongesPrisTotauxAnneeNMoins1Negatif() {
        SalarieAideADomicile aide = new SalarieAideADomicile("Jeanne",
                LocalDate.of(2021, 7, 1), LocalDate.now(),
                0, 0, 9,
                1, -1);

        salarieAideADomicileRepository.save(aide);
        Double res = salarieAideADomicileRepository.partCongesPrisTotauxAnneeNMoins1();

        Assertions.assertEquals(-1, res);
    }


}