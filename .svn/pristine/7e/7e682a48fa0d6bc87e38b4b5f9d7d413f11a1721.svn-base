/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drugstore.server.ws;

import com.drugstore.server.domain.Agreement;
import com.drugstore.server.domain.Container;
import com.drugstore.server.domain.ContainerType;
import com.drugstore.server.domain.Doctor;
import com.drugstore.server.domain.Drug;
import com.drugstore.server.domain.Manufacturer;
import com.drugstore.server.domain.Patient;
import com.drugstore.server.domain.Prescription;
import com.drugstore.server.domain.PrescriptionType;
import com.drugstore.server.domain.SocialSecurityProvider;
import com.drugstore.server.exception.DrugStoreRuntimeException;
import com.drugstore.server.repository.AgreementRepositoryJDBCImpl;
import com.drugstore.server.repository.ContainerRepositoryJDBCImpl;
import com.drugstore.server.repository.ContainerTypeRepositoryJDBCImpl;
import com.drugstore.server.repository.DoctorRepositoryJDBCImpl;
import com.drugstore.server.repository.DrugRepositoryJDBCImpl;
import com.drugstore.server.repository.ManufacturerRepositoryJDBCImpl;
import com.drugstore.server.repository.PatientRepositoryJDBCImpl;
import com.drugstore.server.repository.PrescriptionRepositoryJDBCImpl;
import com.drugstore.server.repository.PrescriptionTypeRepositoryJDBCImpl;
import com.drugstore.server.repository.SocialSecurityProviderRepositoryJDBCImpl;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author ekisa
 */
@WebService(serviceName = "DrugstoreWS")
public class DrugstoreWS {

    @WebMethod(operationName = "findDrug")
    public Drug findDrug(@WebParam(name = "key") Long key) throws DrugStoreRuntimeException {
        return DrugRepositoryJDBCImpl.getInstance().find(key);
    }
    
    @WebMethod(operationName = "findCheaperDrugAlternatives")
    public List<Drug> findCheaperDrugAlternatives(@WebParam(name = "drug") Drug drug) throws DrugStoreRuntimeException {
        return DrugRepositoryJDBCImpl.getInstance().findCheaperDrugAlternatives(drug);
    }
    
    @WebMethod(operationName = "persistDrug")
    public Drug persistDrug(@WebParam(name = "drug") Drug drug) throws DrugStoreRuntimeException {
        return DrugRepositoryJDBCImpl.getInstance().persist(drug);
    }

    @WebMethod(operationName = "removeDrug")
    public void removeDrug(@WebParam(name = "drug") Drug drug) throws DrugStoreRuntimeException {
        DrugRepositoryJDBCImpl.getInstance().delete(drug);
    }

    @WebMethod(operationName = "listDrugs")
    public List<Drug> listDrugs() throws DrugStoreRuntimeException {
        return DrugRepositoryJDBCImpl.getInstance().listAll(null);
    }

    @WebMethod(operationName = "findPrescription")
    public Prescription findPrescription(@WebParam(name = "key") Long key) throws DrugStoreRuntimeException {
        return PrescriptionRepositoryJDBCImpl.getInstance().find(key);
    }
    
    @WebMethod(operationName = "persistPrescription")
    public Prescription persistPrescription(@WebParam(name = "prescription") Prescription prescription) throws DrugStoreRuntimeException {
        return PrescriptionRepositoryJDBCImpl.getInstance().persist(prescription);
    }

    @WebMethod(operationName = "removePrescription")
    public void removePrescription(@WebParam(name = "prescription") Prescription prescription) throws DrugStoreRuntimeException {
        PrescriptionRepositoryJDBCImpl.getInstance().delete(prescription);
    }

    @WebMethod(operationName = "listPrescriptions")
    public List<Prescription> listPrescriptions() throws DrugStoreRuntimeException {
        return PrescriptionRepositoryJDBCImpl.getInstance().listAll(null);
    }
    
    @WebMethod(operationName = "findPrescriptionType")
    public PrescriptionType findPrescriptionType(@WebParam(name = "key") Long key) throws DrugStoreRuntimeException {
        return PrescriptionTypeRepositoryJDBCImpl.getInstance().find(key);
    }

    @WebMethod(operationName = "persistPrescriptionType")
    public PrescriptionType persistPrescriptionType(@WebParam(name = "prescriptionType") PrescriptionType prescriptionType) throws DrugStoreRuntimeException {
        return PrescriptionTypeRepositoryJDBCImpl.getInstance().persist(prescriptionType);
    }

    @WebMethod(operationName = "removePrescriptionType")
    public void removePrescriptionType(@WebParam(name = "prescriptionType") PrescriptionType prescriptionType) throws DrugStoreRuntimeException {
        PrescriptionTypeRepositoryJDBCImpl.getInstance().delete(prescriptionType);
    }

    @WebMethod(operationName = "listPrescriptionTypes")
    public List<PrescriptionType> listPrescriptionTypes() throws DrugStoreRuntimeException {
        return PrescriptionTypeRepositoryJDBCImpl.getInstance().listAll(null);
    }
    
    @WebMethod(operationName = "findPatient")
    public Patient findPatient(@WebParam(name = "key") Long key) throws DrugStoreRuntimeException {
        return PatientRepositoryJDBCImpl.getInstance().find(key);
    }
    
    @WebMethod(operationName = "persistPatient")
    public Patient persistPatient(@WebParam(name = "patient") Patient patient) throws DrugStoreRuntimeException {
        return PatientRepositoryJDBCImpl.getInstance().persist(patient);
    }

    @WebMethod(operationName = "removePatient")
    public void removePatient(@WebParam(name = "patient") Patient patient) throws DrugStoreRuntimeException {
        PatientRepositoryJDBCImpl.getInstance().delete(patient);
    }

    @WebMethod(operationName = "listPatients")
    public List<Patient> listPatients() throws DrugStoreRuntimeException {
        return PatientRepositoryJDBCImpl.getInstance().listAll(null);
    }
    
    @WebMethod(operationName = "findDoctor")
    public Doctor findDoctor(@WebParam(name = "key") Long key) throws DrugStoreRuntimeException {
        return DoctorRepositoryJDBCImpl.getInstance().find(key);
    }
    
    @WebMethod(operationName = "persistDoctor")
    public Doctor persistDoctor(@WebParam(name = "doctor") Doctor doctor) throws DrugStoreRuntimeException {
        return DoctorRepositoryJDBCImpl.getInstance().persist(doctor);
    }

    @WebMethod(operationName = "removeDoctor")
    public void removeDoctor(@WebParam(name = "doctor") Doctor doctor) throws DrugStoreRuntimeException {
        DoctorRepositoryJDBCImpl.getInstance().delete(doctor);
    }

    @WebMethod(operationName = "listDoctors")
    public List<Doctor> listDoctors() throws DrugStoreRuntimeException {
        return DoctorRepositoryJDBCImpl.getInstance().listAll(null);
    }
    
    @WebMethod(operationName = "findContainer")
    public Container findContainer(@WebParam(name = "key") Long key) throws DrugStoreRuntimeException {
        return ContainerRepositoryJDBCImpl.getInstance().find(key);
    }

    @WebMethod(operationName = "persistContainer")
    public Container persistContainer(@WebParam(name = "container") Container container) throws DrugStoreRuntimeException {
        return ContainerRepositoryJDBCImpl.getInstance().persist(container);
    }

    @WebMethod(operationName = "removeContainer")
    public void removeContainer(@WebParam(name = "container") Container container) throws DrugStoreRuntimeException {
        ContainerRepositoryJDBCImpl.getInstance().delete(container);
    }

    @WebMethod(operationName = "listContainers")
    public List<Container> listContainers() throws DrugStoreRuntimeException {
        return ContainerRepositoryJDBCImpl.getInstance().listAll(null);
    }
    
    @WebMethod(operationName = "findContainerType")
    public ContainerType findContainerType(@WebParam(name = "key") Long key) throws DrugStoreRuntimeException {
        return ContainerTypeRepositoryJDBCImpl.getInstance().find(key);
    }

    @WebMethod(operationName = "persistContainerType")
    public ContainerType persistContainerType(@WebParam(name = "containerType") ContainerType containerType) throws DrugStoreRuntimeException {
        return ContainerTypeRepositoryJDBCImpl.getInstance().persist(containerType);
    }

    @WebMethod(operationName = "removeContainerType")
    public void removeContainerType(@WebParam(name = "containerType") ContainerType containerType) throws DrugStoreRuntimeException {
        ContainerTypeRepositoryJDBCImpl.getInstance().delete(containerType);
    }

    @WebMethod(operationName = "listContainerTypes")
    public List<ContainerType> listContainerTypes() throws DrugStoreRuntimeException {
        return ContainerTypeRepositoryJDBCImpl.getInstance().listAll(null);
    }
    
    @WebMethod(operationName = "findManufacturer")
    public Manufacturer findManufacturer(@WebParam(name = "key") Long key) throws DrugStoreRuntimeException {
        return ManufacturerRepositoryJDBCImpl.getInstance().find(key);
    }

    @WebMethod(operationName = "persistManufacturer")
    public Manufacturer persistManufacturer(@WebParam(name = "manufacturer") Manufacturer manufacturer) throws DrugStoreRuntimeException {
        return ManufacturerRepositoryJDBCImpl.getInstance().persist(manufacturer);
    }

    @WebMethod(operationName = "removeManufacturer")
    public void removeManufacturer(@WebParam(name = "manufacturer") Manufacturer manufacturer) throws DrugStoreRuntimeException {
        ManufacturerRepositoryJDBCImpl.getInstance().delete(manufacturer);
    }

    @WebMethod(operationName = "listManufacturers")
    public List<Manufacturer> listManufacturers() throws DrugStoreRuntimeException {
        return ManufacturerRepositoryJDBCImpl.getInstance().listAll(null);
    }
    
    @WebMethod(operationName = "findSocialSecurityProvider")
    public SocialSecurityProvider findSocialSecurityProvider(@WebParam(name = "key") Long key) throws DrugStoreRuntimeException {
        return SocialSecurityProviderRepositoryJDBCImpl.getInstance().find(key);
    }

    @WebMethod(operationName = "persistSocialSecurityProvider")
    public SocialSecurityProvider persistSocialSecurityProvider(@WebParam(name = "socialSecurityProvider") SocialSecurityProvider socialSecurityProvider) throws DrugStoreRuntimeException {
        return SocialSecurityProviderRepositoryJDBCImpl.getInstance().persist(socialSecurityProvider);
    }

    @WebMethod(operationName = "removeSocialSecurityProvider")
    public void removeSocialSecurityProvider(@WebParam(name = "socialSecurityProvider") SocialSecurityProvider socialSecurityProvider) throws DrugStoreRuntimeException {
        SocialSecurityProviderRepositoryJDBCImpl.getInstance().delete(socialSecurityProvider);
    }

    @WebMethod(operationName = "listSocialSecurityProviders")
    public List<SocialSecurityProvider> listSocialSecurityProviders() throws DrugStoreRuntimeException {
        return SocialSecurityProviderRepositoryJDBCImpl.getInstance().listAll(null);
    }
    
    @WebMethod(operationName = "findAgreement")
    public Agreement findAgreement(@WebParam(name = "key") Long key) throws DrugStoreRuntimeException {
        return AgreementRepositoryJDBCImpl.getInstance().find(key);
    }

    @WebMethod(operationName = "persistAgreement")
    public Agreement persistAgreement(@WebParam(name = "agreement") Agreement agreement) throws DrugStoreRuntimeException {
        return AgreementRepositoryJDBCImpl.getInstance().persist(agreement);
    }

    @WebMethod(operationName = "removeAgreement")
    public void removeAgreement(@WebParam(name = "agreement") Agreement agreement) throws DrugStoreRuntimeException {
        AgreementRepositoryJDBCImpl.getInstance().delete(agreement);
    }

    @WebMethod(operationName = "listAgreements")
    public List<Agreement> listAgreements() throws DrugStoreRuntimeException {
        return AgreementRepositoryJDBCImpl.getInstance().listAll(null);
    }
}