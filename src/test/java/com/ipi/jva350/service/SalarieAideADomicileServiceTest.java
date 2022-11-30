package com.ipi.jva350.service;

import com.ipi.jva350.exception.SalarieException;
import com.ipi.jva350.model.SalarieAideADomicile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SalarieAideADomicileServiceTest {

    @Autowired
    private SalarieAideADomicileService salarieService;

    @Test
    void testClotureMois() throws SalarieException {

        SalarieAideADomicile salarie = new SalarieAideADomicile("Jean", LocalDate.of(2021, 7, 1),
                LocalDate.now(), 0, 0, 10, 1, 0);

        salarieService.clotureMois(salarie, 20);
        Assertions.assertEquals(20, salarie.getJoursTravaillesAnneeN());
    }
}