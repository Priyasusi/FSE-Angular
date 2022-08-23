package com.cts.pensionmanagementsystem.processpension.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.pensionmanagementsystem.processpension.entity.PensionDetail;

@Repository
public interface ProcessPensionRepository extends JpaRepository<PensionDetail,Double> {}
