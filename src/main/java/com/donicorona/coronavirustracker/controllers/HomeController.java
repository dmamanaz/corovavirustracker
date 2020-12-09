package com.donicorona.coronavirustracker.controllers;

import com.donicorona.coronavirustracker.models.LocationStats;
import com.donicorona.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller  //all below methods return the response in HTML UI
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")   // ("/") is the root URL
    public String home(Model model) {
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases ", totalReportedCases);

        return "home";
    }

}
