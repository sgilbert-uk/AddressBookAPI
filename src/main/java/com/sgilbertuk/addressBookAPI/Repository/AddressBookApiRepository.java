package com.sgilbertuk.addressBookAPI.Repository;

import com.sgilbertuk.addressBookAPI.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressBookApiRepository  extends JpaRepository<User, Integer> {

    List<User> findByLastName(String name);

}
