package com.sgilbertuk.addressBookAPI.Service;

import com.sgilbertuk.addressBookAPI.Excpetions.UserNotFoundException;
import com.sgilbertuk.addressBookAPI.Model.User;
import com.sgilbertuk.addressBookAPI.Repository.AddressBookApiRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class AddressBookApiServiceTest {

    @Mock
    private AddressBookApiRepository apiRepository;

    @InjectMocks
    private final AddressBookApiService apiService = new AddressBookApiService();

    public User user = new User();

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        user.setLastName("lovelace");
    }

    @Test
    void given_validLastName_andLastNamePresent_whenGetUserByLastName_Expect200() throws UserNotFoundException {
        // Given/When
        when(apiRepository.findByLastName(user.getLastName())).thenReturn(List.of(user));
        apiService.getName(user.getLastName());
        // Then
        assertThat((apiService.getName(user.getLastName())).get(0).getLastName()).isEqualTo("lovelace");
    }

    @Test
    void given_validLastName_andLastNameNotPresent_whenGetUserByLastName_expectThrowNotFoundException() throws UserNotFoundException {
        // Given
        String wrongName = "smith";
        // When
        when(apiRepository.findByLastName(wrongName)).thenThrow(UserNotFoundException.class);
        try {
            apiService.getName(wrongName);
        } catch (UserNotFoundException ex){
            Assertions.assertThrows(UserNotFoundException.class, () -> apiRepository.findByLastName(wrongName),
                    "No user with last name" + wrongName + " was found");
        }
    }
}
