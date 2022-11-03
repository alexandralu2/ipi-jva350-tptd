package com.ipi.jva350.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SalarieAideADomicileTest {

    @Test
    void testaLegalementDroitADesCongesPayes() {
        // Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        SalarieAideADomicile salarie2 = new SalarieAideADomicile();

        salarie2.setJoursTravaillesAnneeNMoins1(11);
        // When
        boolean res = salarie.aLegalementDroitADesCongesPayes();
        // Then
        assertEquals(false, res);
        assertEquals(true, salarie2.aLegalementDroitADesCongesPayes());
        Assertions.assertThat(res).isFalse();

    }

    @Test
    void testcalculeJoursDeCongeDecomptesPourPlage() {
    }
}