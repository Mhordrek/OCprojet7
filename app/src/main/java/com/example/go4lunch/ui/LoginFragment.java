package com.example.go4lunch.ui;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.go4lunch.R;
import com.example.go4lunch.databinding.FragmentLoginBinding;
import com.example.go4lunch.manager.UserManager;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;


public class LoginFragment extends Fragment {

    private static final int RC_SIGN_IN = 123;
    private FragmentLoginBinding binding;
    private UserManager userManager = UserManager.getInstance();
    private CallbackManager callbackManager;
    private LoginButton facebookLoginButton;
    private GoogleSignInClient googleSignInClient;


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();


        //Facebook authentication
        facebookLoginButton = binding.buttonFacebook.findViewById(R.id.buttonFacebook);
        facebookLoginButton.setFragment(this);
        callbackManager = CallbackManager.Factory.create();
        facebookLoginButton.setFragment(LoginFragment.this);
        facebookLoginButton.setPermissions("email", "public_profile");
        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                userManager.handleFacebookAccessToken(loginResult.getAccessToken());
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (userManager.isCurrentUserLogged()) {
                            NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_loginFragment_to_mainFragment);
                        }
                    }
                }, 2000);

            }

            @Override
            public void onCancel() {
                showSnackBar(getString(R.string.error_authentication_canceled));
            }

            @Override
            public void onError(@NonNull FacebookException error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        //Google authentication
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso);
        SignInButton googleSignInButton = binding.buttonGoogle.findViewById(R.id.buttonGoogle);
        googleSignInButton.setSize(SignInButton.SIZE_STANDARD);
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });


        return view;

    }

    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.handleResponseAfterSignIn(requestCode, resultCode, data);
    }


    // Show Snack Bar with a message
    private void showSnackBar(String message) {
        Snackbar.make(binding.loginFragment, message, Snackbar.LENGTH_SHORT).show();
    }

    // Method that handles response after SignIn Activity close
    private void handleResponseAfterSignIn(int requestCode, int resultCode, Intent data) {

        IdpResponse response = IdpResponse.fromResultIntent(data);

        if (requestCode == RC_SIGN_IN) {
            // SUCCESS
            if (resultCode == RESULT_OK) {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                handleSignInResult(task);
                userManager.createUser();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (userManager.isCurrentUserLogged()) {
                            NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_loginFragment_to_mainFragment);
                        }
                    }
                }, 2000);

                showSnackBar(getString(R.string.connection_succeed));

            } else {
                // ERRORS
                if (response == null) {
                    showSnackBar(getString(R.string.error_authentication_canceled));
                } else if (response.getError() != null) {
                    if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                        showSnackBar(getString(R.string.error_no_internet));
                    } else if (response.getError().getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                        showSnackBar(getString(R.string.error_unknown_error));
                    }
                }
            }
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            userManager.firebaseAuthWithGoogle(account.getIdToken());

            // Signed in successfully, show authenticated UI.
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());

        }

    }

   /*@Override
    public void onResume() {
        super.onResume();
        if(userManager.isCurrentUserLogged()){
            NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_loginFragment_to_mainFragment);
        }
    }*/
}
