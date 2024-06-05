package com.pms.controller;


import com.pms.entity.KPI;
import com.pms.service.KPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kpis")
public class KPIController {
    @Autowired
    private KPIService kpiService;

    @GetMapping("/getAllKpi")
    public List<KPI> getAllKPIs() {
        return kpiService.getAllKPIs();
    }

    @PostMapping("/createKpi")
    public KPI createKPI(@RequestBody KPI kpi) {
        return kpiService.createKPI(kpi);
    }

    @PutMapping("/{id}")
    public KPI updateKPI(@PathVariable Long id, @RequestBody KPI kpiDetails) {
        return kpiService.updateKPI(id, kpiDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteKPI(@PathVariable Long id) {
        kpiService.deleteKPI(id);
        return ResponseEntity.ok().build();
    }
}
