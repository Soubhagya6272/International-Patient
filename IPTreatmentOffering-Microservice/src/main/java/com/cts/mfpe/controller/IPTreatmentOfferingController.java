package com.cts.mfpe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.mfpe.exception.AuthorizationException;
import com.cts.mfpe.exception.IPTreatmentPackageNotFoundException;
import com.cts.mfpe.exception.SpecialistNotFoundException;
import com.cts.mfpe.feign.AuthorisingClient;
import com.cts.mfpe.model.AilmentCategory;
import com.cts.mfpe.model.IPTreatmentPackage;
import com.cts.mfpe.model.SpecialistDetail;
import com.cts.mfpe.service.IPTreatmentOfferingService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class IPTreatmentOfferingController {

	@Autowired
	private IPTreatmentOfferingService ipOfferingService;

	@Autowired
	private AuthorisingClient authorisingClient;

	/**
	 * @param requestTokenHeader
	 * @return
	 * @throws AuthorizationException
	 * @throws Exception
	 */
	@GetMapping("/ipTreatmentPackages")
	@ApiOperation(notes = "Returns the list of IP Treatment Packages", value = "Find IP Treatment Package")
	public List<IPTreatmentPackage> getAllIPTreatmentPackage(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
			throws AuthorizationException {
		if (authorisingClient.authorizeTheRequest(requestTokenHeader)) {
			return ipOfferingService.findAllIPTreatmentPackages();
		} else {
			throw new AuthorizationException("Not allowed");
		}

	}

	/**
	 * @param ailment
	 * @param packageName
	 * @param requestTokenHeader
	 * @return
	 * @throws AuthorizationException
	 * @throws IPTreatmentPackageNotFoundException
	 * @throws Exception
	 */
	@GetMapping("/ipTreatmentPackageByName/{ailment}/{packageName}")
	@ApiOperation(notes = "Returns the IP Treatment Package based on package name", value = "Find IP Treatment Package by name")
	public IPTreatmentPackage getIPTreatmentPackageByName(
			@ApiParam(name = "ailment", value = "ailment of the package") @PathVariable AilmentCategory ailment,
			@ApiParam(name = "packageName", value = "name of the package") @PathVariable String packageName,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
			throws AuthorizationException, IPTreatmentPackageNotFoundException {

		if (authorisingClient.authorizeTheRequest(requestTokenHeader)) {
			return ipOfferingService.findIPTreatmentPackageByName(ailment, packageName);
		} else {
			throw new AuthorizationException("Not allowed");
		}
	}

	/**
	 * @param requestTokenHeader
	 * @return
	 * @throws AuthorizationException
	 * @throws Exception
	 */
	@GetMapping("/specialists")
	@ApiOperation(notes = "Returns the list of specialists along with their experience and contact details", value = "Find specialists")
	public List<SpecialistDetail> getAllSpecialist(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
			throws AuthorizationException {
		System.out.println("Inside ================"+requestTokenHeader);
		if (authorisingClient.authorizeTheRequest(requestTokenHeader)) {
			return ipOfferingService.findAllSpecialists();
		} else {
			throw new AuthorizationException("Not allowed");
		}
	}
	@GetMapping("/specialists/{areaOfExpertise}")
	@ApiOperation(notes = "Returns the list of specialists based on their area of expertise", value = "Find specialist by expertise")
	public List<SpecialistDetail> getSpecialistByExpertise(
			@ApiParam(name = "ailment", value = "ailment of the package") @PathVariable AilmentCategory areaOfExpertise ,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
			throws AuthorizationException, IPTreatmentPackageNotFoundException {

		if (authorisingClient.authorizeTheRequest(requestTokenHeader)) {
			return ipOfferingService.findSpecialistByExpertise(areaOfExpertise);
		} else {
			throw new AuthorizationException("Not allowed");
		}
	}
	
	@PostMapping("/addSpecialist")
	@ApiOperation(notes = "Enables the admin to add new specialist details for International patients", value = " add specialists")
	public SpecialistDetail addSpecialist(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,
			@RequestBody SpecialistDetail specialist)
			throws AuthorizationException {
		
		if (authorisingClient.authorizeTheRequest(requestTokenHeader)) {
			return ipOfferingService.addSpecialists(specialist);
		} else {
			throw new AuthorizationException("Not allowed");
			
		}
	}
	
	@DeleteMapping("/deleteSpecialist/{specialistId}")
	@ApiOperation(notes = "Enables the admin to delete a specific specialist.", value = " delete specialists")
	public boolean DeleteSpecialist(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,
			@ApiParam(name = "id", value = "id of the specialist") @PathVariable int specialistId )
			throws AuthorizationException, SpecialistNotFoundException {
		
		if (authorisingClient.authorizeTheRequest(requestTokenHeader)) {
			return ipOfferingService.deleteSpecialists(specialistId);
		} else {
			throw new AuthorizationException("Not allowed");
		}
	}
	
	@PutMapping("/ipTreatmentPackage/{treatmentPackageId}")
	@ApiOperation(notes = "Enables the admin to delete a specific specialist.", value = " delete specialists")
	public IPTreatmentPackage updateTreatmentPackage(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,
			@ApiParam(name = "Treatment Package", value = "Details of the package to update") @RequestBody IPTreatmentPackage ipTreatmentPackage )
			throws AuthorizationException, IPTreatmentPackageNotFoundException {
		
		IPTreatmentPackage package1 = new IPTreatmentPackage();
		if (authorisingClient.authorizeTheRequest(requestTokenHeader)) {
			package1 = ipOfferingService.updatePackage(ipTreatmentPackage);
		} else {
			throw new AuthorizationException("Not allowed");
		}
		
		return package1;
	}

	@GetMapping("/health-check")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<>("Ok", HttpStatus.OK);
	}
}
