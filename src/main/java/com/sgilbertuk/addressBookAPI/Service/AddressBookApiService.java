package com.sgilbertuk.addressBookAPI.Service;

import com.sgilbertuk.addressBookAPI.Excpetions.UserNotFoundException;
import com.sgilbertuk.addressBookAPI.Model.User;
import com.sgilbertuk.addressBookAPI.Repository.AddressBookApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookApiService {

    @Autowired
    public AddressBookApiRepository addressBookRepository;
    public List<User> getName(String name) throws UserNotFoundException {
        try{
            return addressBookRepository.findByLastName(name);
        } catch (UserNotFoundException ex){
            throw new UserNotFoundException("No user with last name " + name + " was found", ex);
        }
    }
}
