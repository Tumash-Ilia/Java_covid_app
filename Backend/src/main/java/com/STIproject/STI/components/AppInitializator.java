package com.STIproject.STI.components;


import com.STIproject.STI.controllers.FilesController;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
class AppInitializator {

    @PostConstruct
    private void init() {
        FilesController fk = new FilesController();
        fk.download();
    }
}
