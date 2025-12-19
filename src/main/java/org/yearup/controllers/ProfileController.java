package org.yearup.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProfileDao;
import org.yearup.data.UserDao;
import org.yearup.models.Profile;
import org.yearup.models.User;

import java.security.Principal;

@RestController
@RequestMapping("/profile")
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class ProfileController {

    private ProfileDao profileDao;
    private UserDao userDao;

    @Autowired
    public ProfileController(ProfileDao profileDao, UserDao userDao) {
        this.profileDao = profileDao;
        this.userDao = userDao;
    }

    @GetMapping("")
    public Profile getProfile(Principal principal) {
        try {
            //gets current logged-in username
            String userName = principal.getName();

            //Finds the user by username
            User user = userDao.getByUserName(userName);
            int userId = user.getId();

            //gets the profile of the user
            Profile profile = profileDao.getByUserId(userId);

            if (profile == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found");
            }

            return profile;
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    @PutMapping("")
    public void updateProfile(@RequestBody Profile profile, Principal principal) {
        try {
            //
            String userName = principal.getName();
            User user = userDao.getByUserName(userName);
            int userId = user.getId();

            // Verify profile exists
            Profile existing = profileDao.getByUserId(userId);
            if (existing == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found");
            }

            // Validate required fields
            if (profile.getFirstName() == null || profile.getFirstName().isBlank() ||
                    profile.getLastName() == null || profile.getLastName().isBlank() ||
                    profile.getEmail() == null || profile.getEmail().isBlank()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "First name, last name, and email are required");
            }

            profileDao.update(userId, profile);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
}
