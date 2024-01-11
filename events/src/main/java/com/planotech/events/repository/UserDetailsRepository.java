package com.planotech.events.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planotech.events.dto.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer>
{

	List<UserDetails> findByEmailAndMobile(String email, long mobile);
	
}
