package me.maxklyukin.fms_branch_registry.http;

import me.maxklyukin.fms_branch_registry.registry.RegistryRepository;
import me.maxklyukin.fms_branch_registry.registry.RegistryUpdateException;
import me.maxklyukin.fms_branch_registry.registry.RegistryUpdater;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ApplicationController {

    private final RegistryUpdater registryUpdater;
    private final RegistryRepository registryRepo;

    public ApplicationController(RegistryUpdater registryUpdater, RegistryRepository registryRepo) {
        this.registryUpdater = registryUpdater;
        this.registryRepo = registryRepo;
    }

    @RequestMapping(value = "/updates", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Response> update() {
        try {
            registryUpdater.update();
            return ResponseEntity.ok().body(Response.empty());
        } catch (RegistryUpdateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Response.error(e.getMessage()));
        }
    }

    @RequestMapping(value = "/branches/{code}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Response> getBranch(@PathVariable String code) {
        return registryRepo.findByCode(code)
                .map(branch -> ResponseEntity.ok().body(Response.data(branch)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.empty()));
    }
}
