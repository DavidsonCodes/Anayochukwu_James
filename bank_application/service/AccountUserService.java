package org.example.bank_application.service;

import org.example.bank_application.model.AccountUser;
import org.example.bank_application.repository.AccountUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountUserService {
    private final AccountUserRepository accountUserRepository;
    public AccountUserService(AccountUserRepository accountUserRepository) {
        this.accountUserRepository = accountUserRepository;
    }
    public ResponseEntity<AccountUser> createUserAccount(AccountUser accountUser){
        return new ResponseEntity<>(accountUserRepository.save(accountUser), HttpStatus.CREATED);
    }
//    public ResponseEntity<AccountUser> createUserAccount(AccountUser accountUser){
//        if(accountUserRepository.existsByUsernameOrPhoneNumber(accountUser.getUsername(), accountUser.getPhoneNumber())){
//            throw new RuntimeException("User with this Email or Phone number has already been registered");
//        }
//        AccountUser accountUser1 = accountUserRepository.save(accountUser);
//        return new ResponseEntity<>(accountUser1, HttpStatus.CREATED);
//    }
    public ResponseEntity<String> delete (Long id){
        AccountUser accountUser = accountUserRepository.findById(id).get();
        accountUserRepository.deleteById(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
    public ResponseEntity<AccountUser> updateAccountUser(Long id, AccountUser accountUser){
        AccountUser accountUser1 = accountUserRepository.findById(id).get();
        accountUser1.setFirstName(accountUser.getFirstName());
        accountUser1.setLastName(accountUser.getLastName());
        accountUser1.setMiddleName(accountUser.getMiddleName());
        accountUser1.setUsername(accountUser.getUsername());
        accountUser1.setPassword(accountUser.getPassword());
        accountUser1.setPhoneNumber(accountUser.getPhoneNumber());
        return new ResponseEntity<>(accountUserRepository.save(accountUser1),HttpStatus.ACCEPTED);
    }

    public ResponseEntity<List<AccountUser>> getAllAccountUsers(){
        return new ResponseEntity<>(accountUserRepository.findAll(), HttpStatus.OK);
    }
    public ResponseEntity<AccountUser> getAccountById(Long id){
        return new ResponseEntity<>(accountUserRepository.findById(id).get(), HttpStatus.OK);
    }
    public ResponseEntity<AccountUser> getAccountUserByUsername(String username){
        return new ResponseEntity<>(accountUserRepository.findByUsername(username), HttpStatus.OK);

    }
    public ResponseEntity<AccountUser> getAccountUserByPhoneNumber(String phoneNumber){
        return new ResponseEntity<>(accountUserRepository.findAccountUserByPhoneNumber(phoneNumber), HttpStatus.OK);
    }

}
