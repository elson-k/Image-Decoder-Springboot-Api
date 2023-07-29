package com.eviro.assessment.grad001.njabulokhoza.repository;

import com.eviro.assessment.grad001.njabulokhoza.model.AccountProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountProfileRepository extends JpaRepository<AccountProfile,Long> {

    AccountProfile findByAccountHolderNameAndAccountHolderSurname(String name, String surname);



}
