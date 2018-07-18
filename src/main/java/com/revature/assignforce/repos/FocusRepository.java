package com.revature.assignforce.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.assignforce.beans.Focus;

@Repository
public interface FocusRepository extends JpaRepository<Focus, Integer>{

}
