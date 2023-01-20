package sg.edu.nus.iss.app.workshop18.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.app.workshop18.model.Result;
import sg.edu.nus.iss.app.workshop18.service.LoveCalculatorService;

@Controller
public class LoveCalculatorController {

    @Autowired
    private LoveCalculatorService loveSvc;

    @GetMapping("result")
    public String getResult(@RequestParam(required = true) String fname,
    @RequestParam(required = true) String sname, Model model) throws Exception {

        Optional<Result> r = loveSvc.getResult(fname, sname);
        model.addAttribute("result", r.get());
        return "result";
    }

    @GetMapping("list")
    public String getList(Model model) {
        List<Result> resultMap = loveSvc.findAll();
        if (resultMap!=null) {
            model.addAttribute("result", resultMap);
        }
        return "list";
    }

    @GetMapping("/")
    public String loveCalculatorForm() {
        return "lovecalculator";
    }

    @PostMapping("/")
    public String loveCalculatorSubmit(@RequestParam String name1 , @RequestParam String name2, Model model) {
        int loveScore = calculateLoveScore(name1, name2);
        model.addAttribute("name1", name1);
        model.addAttribute("name2", name2);
        model.addAttribute("loveScore", loveScore);
        return "result";
    }

    private int calculateLoveScore(String name1, String name2) {
        // This method would calculate the love score based on the given names
        // For the sake of example, we'll just return a random number
        return (int) (Math.random()  * 100);
    }
    
}
