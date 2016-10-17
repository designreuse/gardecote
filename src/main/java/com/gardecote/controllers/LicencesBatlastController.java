/*
 * Created on 27 sept. 2016 ( Time 21:28:14 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.gardecote.controllers;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//--- Common classes
import com.gardecote.web.common.AbstractController;
import com.gardecote.web.common.FormMode;
import com.gardecote.web.common.Message;
import com.gardecote.web.common.MessageType;
import com.gardecote.business.service.qLicenceBatLastService;
import com.gardecote.entities.qLicenceBatLast;
//--- Entities

//--- Services


/**
 * Spring MVC controller for 'LicencesBatlast' management.
 */
@Controller
@RequestMapping("/licencesBatlast")
public class LicencesBatlastController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "licencesBatlast";
	private static final String MAIN_LIST_NAME   = "list";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_FORM   = "licencesBatlast/form";
	private static final String JSP_LIST   = "licencesBatlast/list";

	//--- SAVE ACTION ( in the HTML form )
	private static final String SAVE_ACTION_CREATE   = "/licencesBatlast/create";
	private static final String SAVE_ACTION_UPDATE   = "/licencesBatlast/update";

	//--- Main entity service
	@Autowired
    private qLicenceBatLastService  licencesBatlastService; // Injected by Spring

	//--- Other service(s)

	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public LicencesBatlastController() {
		super(LicencesBatlastController.class, MAIN_ENTITY_NAME );
		log("LicencesBatlastController created.");
	}

	//--------------------------------------------------------------------------------------
	// Spring MVC model management
	//--------------------------------------------------------------------------------------

	/**
	 * Populates the Spring MVC model with the given entity and eventually other useful data
	 * @param model
	 * @param licencesBatlast
	 */
	private void populateModel(Model model, qLicenceBatLast licencesBatlast, FormMode formMode) {
		//--- Main entity
		model.addAttribute(MAIN_ENTITY_NAME, licencesBatlast);
		if ( formMode == FormMode.CREATE ) {
			model.addAttribute(MODE, MODE_CREATE); // The form is in "create" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_CREATE); 			
			//--- Other data useful in this screen in "create" mode (all fields)
		}
		else if ( formMode == FormMode.UPDATE ) {
			model.addAttribute(MODE, MODE_UPDATE); // The form is in "update" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_UPDATE); 			
			//--- Other data useful in this screen in "update" mode (only non-pk fields)
		}
	}

	//--------------------------------------------------------------------------------------
	// Request mapping
	//--------------------------------------------------------------------------------------
	/**
	 * Shows a list with all the occurrences of LicencesBatlast found in the database
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping()
	public String list(Model model) {
		log("Action 'list'");
		List<qLicenceBatLast> list = licencesBatlastService.findAll();
		model.addAttribute(MAIN_LIST_NAME, list);		
		return JSP_LIST;
	}

	/**
	 * Shows a form page in order to create a new LicencesBatlast
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping("/form")
	public String formForCreate(Model model) {
		log("Action 'formForCreate'");
		//--- Populates the model with a new instance
		qLicenceBatLast licencesBatlast = new qLicenceBatLast();
		populateModel( model, licencesBatlast, FormMode.CREATE);
		return JSP_FORM;
	}

	/**
	 * Shows a form page in order to update an existing LicencesBatlast
	 * @param model Spring MVC model
	 * @param idLic  primary key element
	 * @return
	 */
	@RequestMapping(value = "/form/{idLic}")
	public String formForUpdate(Model model, @PathVariable("idLic") Long idLic ) {
		log("Action 'formForUpdate'");
		//--- Search the entity by its primary key and stores it in the model 
		qLicenceBatLast licencesBatlast = licencesBatlastService.findById(idLic);
		populateModel( model, licencesBatlast, FormMode.UPDATE);		
		return JSP_FORM;
	}

	/**
	 * 'CREATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param licencesBatlast  entity to be created
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/create" ) // GET or POST
	public String create(@Valid qLicenceBatLast licencesBatlast, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'create'");
		try {
			if (!bindingResult.hasErrors()) {
				qLicenceBatLast licencesBatlastCreated = licencesBatlastService.create(licencesBatlast);
				model.addAttribute(MAIN_ENTITY_NAME, licencesBatlastCreated);

				//---
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				return redirectToForm(httpServletRequest, licencesBatlast.getIdLic() );
			} else {
				populateModel( model, licencesBatlast, FormMode.CREATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			log("Action 'create' : Exception - " + e.getMessage() );
			messageHelper.addException(model, "licencesBatlast.error.create", e);
			populateModel( model, licencesBatlast, FormMode.CREATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'UPDATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param licencesBatlast  entity to be updated
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/update" ) // GET or POST
	public String update(@Valid qLicenceBatLast licencesBatlast, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
		log("Action 'update'");
		try {
			if (!bindingResult.hasErrors()) {
				//--- Perform database operations
				qLicenceBatLast licencesBatlastSaved = licencesBatlastService.update(licencesBatlast);
				model.addAttribute(MAIN_ENTITY_NAME, licencesBatlastSaved);
				//--- Set the result message
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				log("Action 'update' : update done - redirect");
				return redirectToForm(httpServletRequest, licencesBatlast.getIdLic());
			} else {
				log("Action 'update' : binding errors");
				populateModel( model, licencesBatlast, FormMode.UPDATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			messageHelper.addException(model, "licencesBatlast.error.update", e);
			log("Action 'update' : Exception - " + e.getMessage() );
			populateModel( model, licencesBatlast, FormMode.UPDATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'DELETE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param redirectAttributes
	 * @param idLic  primary key element
	 * @return
	 */
	@RequestMapping(value = "/delete/{idLic}") // GET or POST
	public String delete(RedirectAttributes redirectAttributes, @PathVariable("idLic") Long idLic) {
		log("Action 'delete'" );
		try {
			licencesBatlastService.delete( idLic );
			//--- Set the result message
			messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));	
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "licencesBatlast.error.delete", e);
		}
		return redirectToList();
	}

}
