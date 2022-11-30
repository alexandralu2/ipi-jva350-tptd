package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static com.ipi.jva350.model.Entreprise.*;
import static org.junit.jupiter.api.Assertions.*;

class EntrepriseTest {

    @ParameterizedTest(name = "{0} doit être compris entre {1} et {2}")
    @CsvSource({
            "'2022-07-07', '2022-07-01', '2022-07-10'",
            "'2022-06-01', '2022-06-01', '2022-07-03'",
            " '2022-07-04', '2022-07-02', '2022-07-04'",
            " '2022-07-03', '2022-07-02', '2022-07-04'",
    })
    void testEstDansPlage(LocalDate d, LocalDate debut, LocalDate fin) {
        assertEquals(true, estDansPlage(d, debut, fin));
    }

    @ParameterizedTest(name = "{0} doit être compris entre {1} et {2}")
    @CsvSource({
            "'2022-07-20', '2022-07-01', '2022-07-10'",
            "'2022-05-30', '2022-06-01', '2022-07-03'",
            " '2022-07-05', '2022-07-02', '2022-07-04'",
            " '2022-07-06', '2022-07-02', '2022-07-04'",
    })
    void testNEstPasDansPlage(LocalDate d, LocalDate debut, LocalDate fin) {
        assertEquals(false, estDansPlage(d, debut, fin));
    }

    @ParameterizedTest(name = "{0} est un jour ferie")
    @CsvSource({
            "'2022-01-01'",
            "'2022-05-01'",
            " '2022-07-14'",
            " '2024-08-15'",
    })
    void testEstJourFerie(LocalDate jour){
        assertEquals(true, estJourFerie(jour));
    }

    @ParameterizedTest(name = "Premier jour de congé selon {0}")
    @CsvSource({
            "'2022-05-01'",
            " '2022-04-01'",
    })
    void testGetPremierJourAnneeDeCongesAvantJuin(LocalDate d){
        assertEquals(LocalDate.of(2021, 6, 1), getPremierJourAnneeDeConges(d));
    }

    @ParameterizedTest(name = "Premier jour de congé selon {0}")
    @CsvSource({
            "'2022-07-01'",
            " '2022-06-01'",
    })
    void testGetPremierJourAnneeDeCongesApresJuin(LocalDate d){
        assertEquals(LocalDate.of(2022, 6, 1), getPremierJourAnneeDeConges(d));
    }
}