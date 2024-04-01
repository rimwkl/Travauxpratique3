package com.example.travp3.web;

import com.example.travp3.entities.Patient;
import com.example.travp3.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor //OU BIEN AUTOWIRED OU BIEN CONTRUCTEUR :injection
public class PatientController {
    private PatientRepository patientRepository;

    //methode qui retourne une vue patients.html
    @GetMapping("/index")
    //si jaipas utiliser ca si je veux recuperer les donner de la requette je doit creec objet httpservletrequest et dans ce quoi il va prendre request.getparametrepage page...
    public String index(Model model, @RequestParam(name = "page",defaultValue = "0") int p,
                        @RequestParam(name = "size",defaultValue = "4") int s,
                        @RequestParam(name = "keyword",defaultValue = "") String kw){ //cette liste je vais la stocket dans Model
        //afficher la liste des patients
       // List<Patient> patientList=patientRepository.findAll(); //je vais vers la BD
       // Page<Patient> pagePatients=patientRepository.findAll(PageRequest.of(p,s));              //transmettre la page quon veut
        Page<Patient> pagePatients=patientRepository.findByNomContains(kw,PageRequest.of(p,s));
        model.addAttribute("listPatients",pagePatients.getContent());
        // faire la pagination :1-afficher les pages : retourne nbr total des pages
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",p);
        model.addAttribute("keyword",kw);
        return "patients";
    }
    /*@GetMapping("/delete")
    //pour faire delete
    public String delete(Long id,String keyword,int page){
        patientRepository.deleteById(id);
        //quand il supprime il doit revevir pour affiche "redirection"
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }*/
    @GetMapping("/delete")
    //pour faire delete
    public String delete(@RequestParam(name="id") Long id,@RequestParam(name = "keyword",defaultValue = "")String keyword,
                         @RequestParam(name = "page",defaultValue = "0")int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/index";
    }
}
