package com.cts.pensionmanagementsystem.pensionerdetail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.pensionmanagementsystem.pensionerdetail.entity.PensionerDetail;

@Repository
public interface PensionerDetailRepository extends JpaRepository<PensionerDetail, String> {
	PensionerDetail findByAadhaarNumber(long aadhaarNumber);
}
