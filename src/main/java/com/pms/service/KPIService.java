package com.pms.service;


import com.pms.entity.KPI;
import com.pms.repository.KPIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KPIService {
    @Autowired
    private KPIRepository kpiRepository;

    public List<KPI> getAllKPIs() {
        return kpiRepository.findAll();
    }

    public KPI createKPI(KPI kpi) {
        return kpiRepository.save(kpi);
    }

    public KPI updateKPI(Long id, KPI kpiDetails) {
        KPI kpi = kpiRepository.findById(id).orElseThrow(() -> new RuntimeException("KPI not found"));
        kpi.setName(kpiDetails.getName());
        kpi.setCategory(kpiDetails.getCategory());
        kpi.setValue(kpiDetails.getValue());
        kpi.setChangeVsPrevMonth(kpiDetails.getChangeVsPrevMonth());
        kpi.setVariance(kpiDetails.getVariance());
        kpi.setTarget(kpiDetails.getTarget());
        return kpiRepository.save(kpi);
    }

    public void deleteKPI(Long id) {
        KPI kpi = kpiRepository.findById(id).orElseThrow(() -> new RuntimeException("KPI not found"));
        kpiRepository.delete(kpi);
    }
}
