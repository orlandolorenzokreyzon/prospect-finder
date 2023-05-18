package com.kreyzon.prospectfinder.api.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kreyzon.prospectfinder.api.model.Setup;
import com.kreyzon.prospectfinder.api.repository.SetupRepository;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SetupService.class})
@ExtendWith(SpringExtension.class)
class SetupServiceTest {
    @MockBean
    private SetupRepository setupRepository;

    @Autowired
    private SetupService setupService;


    /**
     * Method under test: {@link SetupService#getSetup()}
     */
    @Test
    void testGetSetup() {
        Setup setup = new Setup();
        setup.setBusinessAvailableCredits(1);
        setup.setId(1);
        setup.setBusinessPassword("iloveyou");
        setup.setBusinessUsername("janedoe");

        ArrayList<Setup> setupList = new ArrayList<>();
        setupList.add(setup);
        when(setupRepository.findAll()).thenReturn(setupList);
        assertSame(setup, setupService.getSetup());
        verify(setupRepository).findAll();
    }
}

