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

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void given_validLastName_andLastNamePresent_whenGetUserByLastName_Expect200() throws Exception {
        // Given
        User user = new User();
        user.setLastName("lovelace");
        // When
        when(apiRepository.findByLastName(user.getLastName())).thenReturn(List.of(user));
        when(apiService.getName(user.getLastName())).thenReturn(List.of(user));
        // Then
        assertThat((apiService.getName(user.getLastName())).get(0).getLastName()).isEqualTo("lovelace");
    }

    @Test
    void given_validLastName_andLastNameNotPresent_whenGetUserByLastName_expectThrowNotFoundException() throws Exception {
        // Given
        User user = new User();
        user.setLastName("lovelace");
        // When
        when(apiRepository.findByLastName("smith")).thenThrow(UserNotFoundException.class);
        Assertions.assertThrows(UserNotFoundException.class, () -> apiRepository.findByLastName("smith"),
                "No user with last name smith was found");

    }

}
