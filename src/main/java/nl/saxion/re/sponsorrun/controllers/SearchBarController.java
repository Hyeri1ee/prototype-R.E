package nl.saxion.re.sponsorrun.controllers;

import nl.saxion.re.sponsorrun.model.MainWindow;
import nl.saxion.re.sponsorrun.model.Runner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SearchBarController {


    public List<Runner> search(String searchInput){

        List<Runner> runners = new ArrayList<>();
        for (Runner runner: MainWindow.runnersList){
                if (runner.event.toLowerCase().contains(searchInput.toLowerCase())){
                    runners.add(runner);
                }
        }
        return runners;

    }
}
