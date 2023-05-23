package com.example.validatelibrary;

import android.util.Patterns;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ValidateClass {
    /**
     * checks if the provided string match with the email known pattern
     * @param email
     * @return true if string matches the pattern false if not
     */
    public static boolean checkEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * checks if the provided string match with the phone number's known pattern
     * @param number
     * @return true if string matches the pattern, false otherwise
     * Match a phone number with or without "-" and/or country code.
     * the patterns that are accepted:
     * +919367788755
     * 8989829304
     * +16308520397
     * 786-307-3615
     */
    public static boolean checkPhoneNumber(String number){
        String regex = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$";
        return number.matches(regex);
    }

    /**
     * checks if the provided string match the full name' known pattern
     * @param name
     * @return true if matches the pattern, false otherwise
     * example of the pattern : John Johny
     */
    public static boolean checkValidName(String name){
        String regex = "(\\b[A-Z]{1}[a-z]+)( )([A-Z]{1}[a-z]+\\b)";
        return name.matches(regex);
    }

    /**
     * checks if the provided string match the age's known pattern
     * @param age
     * @return true if matches the pattern, false otherwise
     */
    public static boolean checkAge(String age){
        String regex = "^(?:[1-9][0-9]?|1[01][0-9]|120)$";
        return age.matches(regex);
    }

    /**
     * checks if the provided age is bigger than the provided minimum age
     * @param age - the age we want to check
     * @param minAge - the minimum age
     * @return true if age > minAge, false otherwise
     */
    public static boolean checkLegalAge(int age, int minAge){
        return age > minAge;
    }

    /**
     * checks if the provided string considered weak password
     * @param password
     * @return true if string is weak password, false otherwise
     * weak password -> contains only up to 7 characters
     */
    public static boolean checkWeakPassword(String password){
        String regex = "^.{1,7}$";
        return password.matches(regex);

    }

    /**
     * checks if the provided string considered medium password
     * @param password
     * @return true if string is medium password, false otherwise
     * medium password ->
     * - contains more than 8 characters
     * - must have at least one lowercase letter
     * - must have at least one uppercase letter
     * - must have at least one digit
     */
    public static boolean checkMediumPassword(String password){
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        return password.matches(regex);
    }

    /**
     * checks if the provided string considered strong password
     * @param password
     * @return true if string is strong password, false otherwise
     * strong  password ->
     * - contains more than 8 characters
     * - must have at least one lowercase letter
     * - must have at least one uppercase letter
     * - must have at least one digit
     * - must have at least one special character : @#$%^&+=
     */
    public static boolean checkStrongPassword(String password){
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$";
        return password.matches(regex);
    }

    /**
     * checks the kind of password that was provided
     * @param password
     * @return int that represent the type according to the enum
     * WEAK - 0
     * MEDIUM - 1
     * STRONG - 2
     * INVALID - 3
     */
    public static PasswordTypes checkPasswordType(String password){
        PasswordTypes type = PasswordTypes.INVALID;
        if(checkWeakPassword(password))
            type = PasswordTypes.WEAK;
        if(checkMediumPassword(password))
            type = PasswordTypes.MEDIUM;
        if(checkStrongPassword(password))
            type = PasswordTypes.STRONG;
        return type;
    }

    /**
     * checks if the provided string considered valid date
     * @param date
     * @return true if according to the format, false otherwise
     * the format-> dd-mm-yyyy , the hyphen separator can be replaced ith / or .
     */
    public static boolean checkDateFormat(String date){
        String regex = "^(0[1-9]|[1-2][0-9]|3[0-1])([-./])(0[1-9]|1[0-2])\\2\\d{4}$";
        return date.matches(regex);
    }

    /**
     * checks if the date that entered can be a legal date of birth
     * @param date - the date entered
     * @param minimumAge - the minimum age that can be in order for the date to be legal
     * @return true if the date isnt in the future and if the age is bigger than the minimum
     */
    public static boolean checkBirthDate(String date, int minimumAge){
        if(!checkDateFormat(date))
            return false;
        // Remove optional separators from the date string
        String cleanedDateOfBirth = date.replaceAll("[-./]", "");

        // Define the expected date format
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        dateFormat.setLenient(false); // Disable lenient parsing

        try {
            // Parse the date string
            Date parsedDate = dateFormat.parse(cleanedDateOfBirth);

            // Perform additional checks
            Date currentDate = new Date();

            // Check if the date is not in the future
            if (parsedDate.after(currentDate)) {
                return false;
            }

            Calendar currentDateCal = Calendar.getInstance();
            Calendar birthDate = Calendar.getInstance();
            birthDate.setTime(parsedDate);

            int age = currentDateCal.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

            // Check if the current date is before the birth date
            // to account for cases where the birthday hasn't happened yet this year
            if (currentDateCal.get(Calendar.MONTH) < birthDate.get(Calendar.MONTH)) {
                age--;
            } else if (currentDateCal.get(Calendar.MONTH) == birthDate.get(Calendar.MONTH)
                    && currentDateCal.get(Calendar.DAY_OF_MONTH) < birthDate.get(Calendar.DAY_OF_MONTH)) {
                age--;
            }

            return checkLegalAge(age, minimumAge);

        } catch (ParseException e) {
            // The date string is not in a valid format
            return false;
        }
    }



}
