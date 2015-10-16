package fi.ukkosnetti.symprap.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fi.ukkosnetti.symprap.dto.DiseaseCreate;
import fi.ukkosnetti.symprap.dto.DiseaseGet;
import fi.ukkosnetti.symprap.dto.DiseaseUpdate;
import fi.ukkosnetti.symprap.service.DiseaseService;

@RestController
@RequestMapping(value = "/disease")
public class DiseaseController {

	@Autowired
	private DiseaseService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody DiseaseGet getDisease(@PathVariable("id") Long id) {
		return service.getDisease(id);
    }
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody DiseaseGet create(@RequestBody DiseaseCreate disease) {
		return service.createDisease(disease);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody DiseaseGet create(@RequestBody DiseaseUpdate disease) {
		return service.updateDisease(disease);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody List<DiseaseGet> getAll() {
		return service.getDiseases();
	}
}
