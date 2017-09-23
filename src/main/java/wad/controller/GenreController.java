/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Genre;
import wad.repository.GenreRepository;
import wad.service.GenreService;

/**
 *
 * @author veke
 */

@Controller
@RequestMapping("/genres")
public class GenreController {
    
    @Autowired
    private GenreRepository genreRepo;
    
    @Autowired
    private GenreService genreService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model){
         model.addAttribute("genres",genreService.getGenres());
         return "genres";
    }
    
    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public String save(@RequestParam String name){
        genreService.save(name);
        return "redirect:/genres";
    }
    
    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable (value = "id")Long id){
        genreService.deleteGenre(id);
        return "redirect:/genres";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getBooksByGenre(@PathVariable (value = "id") Long id, Model model){ 
        model.addAttribute("genre", genreService.getGenre(id));
        return "genre";
    }
    
}
