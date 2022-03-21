package com.STIproject.STI.controllers;

import com.STIproject.STI.exceptions.NotFoundException;
import com.STIproject.STI.projectData.ProjectDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Downloading files via http request
 */

@RestController
public class FilesController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/downloadData")
    public void download(){
        LOGGER.info("Downloading data");
        ProjectDataService pd = new ProjectDataService();
        if (pd.netIsAvailable()) {
            try {
                pd.downloadData();

            } finally {
                pd.close();
            }
            pd.edit_who_cov();
            pd.edit_oa();
            pd.edit_who_vac();
        }
    }


    @GetMapping("/checkConnection")
    public boolean ethernet_check(){
        ProjectDataService pd = new ProjectDataService();
        boolean responce = pd.netIsAvailable();
        return responce;
    }

}
