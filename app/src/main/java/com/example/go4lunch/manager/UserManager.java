package com.example.go4lunch.manager;

import android.content.Context;

import com.example.go4lunch.model.User;
import com.example.go4lunch.repository.UserRepository;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

public class UserManager {
    private static volatile UserManager instance;
    private UserRepository userRepository;

    private UserManager() {
        userRepository = UserRepository.getInstance();
    }

    public static UserManager getInstance() {
        UserManager result = instance;
        if (result != null) {
            return result;
        }
        synchronized(UserRepository.class) {
            if (instance == null) {
                instance = new UserManager();
            }
            return instance;
        }
    }

    public void createUser(){
        userRepository.createUser();
    }

    public Task<User> getUserData(){
        // Get the user from Firestore and cast it to a User model Object
        return userRepository.getUserData().continueWith(task -> task.getResult().toObject(User.class)) ;
    }

    public Task<Void> updateUsername(String username){
        return userRepository.updateUsername(username);
    }

    public Boolean isCurrentUserLogged(){
        return (userRepository.getCurrentUser() != null);
    }

    public FirebaseUser getCurrentUser(){
        return userRepository.getCurrentUser();
    }

    public Task<Void> signOut(Context context){
        return userRepository.signOut(context);
    }

    public Task<Void> deleteUser(Context context){
        // Delete the user account from the Auth
        return userRepository.deleteUser(context).addOnCompleteListener(task -> {
            // Once done, delete the user datas from Firestore
            userRepository.deleteUserFromFirestore();
        });
    }

}
