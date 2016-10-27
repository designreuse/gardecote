/*
 * Created on 26 sept. 2016 ( Time 23:15:13 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.gardecote.controllers;

import java.util.LinkedList;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Page;
import com.gardecote.web.listitem.LicencesListItem;

import com.gardecote.entities.qLicence;
import com.gardecote.business.service.qLicenceService;
/**
 * Spring MVC controller for 'LicencesBatlast' management.
 */
@RestController
@RequestMapping("/RestGC")
public class LicencesBatlastRestController {

	@Autowired
	private qLicenceService licencesBatlastService;
	
	@RequestMapping( value="/items/licencesBatlast",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<LicencesListItem> findAllAsListItems() {
		List<qLicence> list = licencesBatlastService.findAll();
		List<LicencesListItem> items = new LinkedList<LicencesListItem>();
		for ( qLicence licencesBatlast : list ) {
			items.add(new LicencesListItem(licencesBatlast) );
		}
		return items;
	}
	@RequestMapping(value="/autocomBats",method = RequestMethod.GET)
	public Page<qLicence> getAutocompleteLocals(@RequestParam String nomnav) {
		System.out.println(licencesBatlastService.returnSuggNomNav(nomnav));
		return licencesBatlastService.returnSuggNomNav(nomnav);
	}
	@RequestMapping( value="/licencesBatlast",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<qLicence> findAll() {
		return licencesBatlastService.findAll();
	}

	@RequestMapping( value="/licencesBatlast/{idLic}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public qLicence findOne(@PathVariable("idLic") Long idLic) {
		return licencesBatlastService.findById(idLic);
	}
	
	@RequestMapping( value="/licencesBatlast",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public qLicence create(@RequestBody qLicence licencesBatlast) {
		return licencesBatlastService.create(licencesBatlast);
	}

	@RequestMapping( value="/licencesBatlast/{idLic}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public qLicence update(@PathVariable("idLic") Long idLic, @RequestBody qLicence licencesBatlast) {
		return licencesBatlastService.update(licencesBatlast);
	}

	@RequestMapping( value="/licencesBatlast/{idLic}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("idLic") Long idLic) {
		licencesBatlastService.delete(idLic);
	}
	
}
