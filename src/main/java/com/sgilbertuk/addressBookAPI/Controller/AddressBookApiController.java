package com.sgilbertuk.addressBookAPI.Controller;

import com.sgilbertuk.addressBookAPI.Model.User;
import com.sgilbertuk.addressBookAPI.Service.AddressBookApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AddressBookApiController {

    @Autowired
    public AddressBookApiService addressBookService;

    @GetMapping(path = "/users/{lastName}")
    public @ResponseBody
    List<User> getUser(@PathVariable("lastName") String lastName) throws Exception {
        return addressBookService.getName(lastName);
    }

}
