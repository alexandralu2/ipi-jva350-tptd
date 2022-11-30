package com.ipi.jva350.service;

import com.ipi.jva350.exception.SalarieException;
import com.ipi.jva350.model.SalarieAideADomicile;
import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class SalarieAideADomicileServiceTest {

    @InjectMocks
    SalarieAideADomicileService salarieAideADomicileService;
    @Mock
    SalarieAideADomicileRepository salarieAideADomicileRepository;
    private SalarieAideADomicileService salarieService;

    @Test
    void testClotureMois() throws SalarieException {

        SalarieAideADomicile salarie = new SalarieAideADomicile("Jean", LocalDate.of(2021, 7, 1),
                LocalDate.now(), 0, 0, 10, 1, 0);

        salarieService.clotureMois(salarie, 20);
        Assertions.assertEquals(20, salarie.getJoursTravaillesAnneeN());
    }

    @ParameterizedTest(name = "{0} congés pris N-1, {1} congés acquis N-1, {2} mois en cours, {3} date début de contrat" +
            "{4} premier jour de congé, {5} dernier jour de congé")
    @CsvSource
            ({
                    "3, 22, '2022-07-30', '2020-04-01', '2022-12-01', '2022-12-31'",
            })
    void TestCalculeLimiteEntrepriseCongesPermis(double congePrisAnneeNmoins1, double congePayerAcquisAnneeNmoins1,
                                                 LocalDate moisEnCours, LocalDate debutContrat, LocalDate premierJour, LocalDate dernierJour) {
        when(salarieAideADomicileRepository.partCongesPrisTotauxAnneeNMoins1()).thenReturn(congePrisAnneeNmoins1);
        long result = salarieAideADomicileService.calculeLimiteEntrepriseCongesPermis(moisEnCours,
                congePayerAcquisAnneeNmoins1, debutContrat, premierJour, dernierJour);

        assertEquals(6, result);
    }
}