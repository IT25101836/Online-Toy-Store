package com.toystore.onlinetoystore.controller;

import com.toystore.onlinetoystore.model.Toy;
import com.toystore.onlinetoystore.service.ToyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class ToyController {

    private ToyService toyService = new ToyService();

    @GetMapping("/toys")
    public String showToys(@RequestParam(required = false) String search, Model model) {
        List<Toy> toys = toyService.getAllToys();
        System.out.println("Number of toys: " + toys.size());
        for (Toy toy : toys) {
            System.out.println("Toy: " + toy.getToyId() + " - " + toy.getName());
        }
        if (search != null && !search.isEmpty()) {
            model.addAttribute("toys", toyService.searchToys(search));
            model.addAttribute("search", search);
        } else {
            model.addAttribute("toys", toys);
        }
        return "toys";
    }

    @GetMapping("/admin/add-toy")
    public String showAddToyPage() {
        return "add-toy";
    }

    @PostMapping("/admin/add-toy")
    public String addToy(@RequestParam String name,
                         @RequestParam String category,
                         @RequestParam double price,
                         @RequestParam int stock,
                         @RequestParam String description,
                         @RequestParam(required = false, defaultValue = "") String imageUrl,
                         Model model) {
        toyService.addToy(name, category, price, stock, description, imageUrl);
        model.addAttribute("message", "Toy added successfully!");
        model.addAttribute("toys", toyService.getAllToys());
        return "toys";
    }

    @GetMapping("/admin/delete-toy")
    public String deleteToy(@RequestParam String toyId, Model model) {
        toyService.deleteToy(toyId);
        model.addAttribute("toys", toyService.getAllToys());
        model.addAttribute("message", "Toy deleted!");
        return "toys";
    }
}