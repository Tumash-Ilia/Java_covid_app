package com.STIproject.STI.components;


import com.STIproject.STI.controllers.FilesController;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ShedulerComponent {

    @Scheduled(cron = "0 0 16 * * ?")
    public void reportCurrentData() {
        FilesController fk = new FilesController();
        fk.download();
    }
}