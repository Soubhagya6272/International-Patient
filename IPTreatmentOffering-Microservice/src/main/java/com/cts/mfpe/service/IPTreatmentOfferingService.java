package com.cts.mfpe.service;

import java.util.List;

import com.cts.mfpe.exception.IPTreatmentPackageNotFoundException;
import com.cts.mfpe.exception.SpecialistNotFoundException;
import com.cts.mfpe.model.AilmentCategory;
import com.cts.mfpe.model.IPTreatmentPackage;
import com.cts.mfpe.model.SpecialistDetail;

public interface IPTreatmentOfferingService {
	
	List<IPTreatmentPackage> findAllIPTreatmentPackages();
	IPTreatmentPackage findIPTreatmentPackageByName(AilmentCategory ailment, String packageName) throws IPTreatmentPackageNotFoundException;
	List<SpecialistDetail> findAllSpecialists();
	List<SpecialistDetail> findSpecialistByExpertise(AilmentCategory areaOfExpertise);
	SpecialistDetail addSpecialists(SpecialistDetail specialist);
	boolean deleteSpecialists(int specialistId) throws SpecialistNotFoundException, SpecialistNotFoundException;
	IPTreatmentPackage updatePackage(IPTreatmentPackage ipTreatmentPackage) throws IPTreatmentPackageNotFoundException;
}
