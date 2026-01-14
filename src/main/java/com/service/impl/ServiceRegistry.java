package com.service.impl;

import com.model.DataStore;
import com.service.*;

public class ServiceRegistry {
    private final DataStore store;
    private final PersonaService personaService;
    private final IncidentService incidentService;
    private final LoanService loanService;
    private final ReportService reportService;

    public ServiceRegistry() {
        this(new DataStore());
    }

    // Nuevo constructor que usa un DataStore ya existente
    public ServiceRegistry(DataStore store) {
        this.store = store;
        this.personaService = new com.service.impl.PersonaServiceImpl(store);
        this.incidentService = new com.service.impl.IncidentServiceImpl(store);
        this.loanService = new com.service.impl.LoanServiceImpl(store);
        this.reportService = new com.service.impl.ReportServiceImpl(store);
    }

    public DataStore getStore() { return store; }
    public PersonaService getPersonaService() { return personaService; }
    public IncidentService getIncidentService() { return incidentService; }
    public LoanService getLoanService() { return loanService; }
    public ReportService getReportService() { return reportService; }
}
