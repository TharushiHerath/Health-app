package lk.sltc.healthapp;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import lk.sltc.healthapp.Person;

public class Preferences {
    private static Gson gson = new Gson();

    public static Person getCurrentPerson(SharedPreferences preferences) {
        Person currentPerson = null;
        String currentPersonJson = preferences.getString("CurrentPerson", null); //update key if necessary
        if (currentPersonJson != null) {
            currentPerson = gson.fromJson(currentPersonJson, Person.class);
        }

        return currentPerson;
    }

    public static void saveAsCurrentPerson(Person person, SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("CurrentPerson", gson.toJson(person)); //update key if necessary
        editor.commit();
    }

    public static List<Person> getPeople(SharedPreferences preferences) {
        List<Person> people = new ArrayList<>();
        String peopleJson = preferences.getString("People", null); //update key if necessary
        if (peopleJson != null) {
            Type type = new TypeToken<List<Person>>(){}.getType();
            people = gson.fromJson(peopleJson, type);
        }

        return people;
    }

    public static void savePeople(List<Person> people, SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("People", gson.toJson(people)); //update key if necessary
        editor.commit();
    }

    public static Person getPersonByEmail(String email, SharedPreferences preferences) {
        List<Person> people = getPeople(preferences);
        if (people != null) {
            for (Person per: people) {
                if (per.getEmail().equalsIgnoreCase(email)) {
                    return per;
                }
            }
        }

        return null;
    }

    public static void removePersonWithEmail(String email, SharedPreferences preferences) {
        List<Person> people = getPeople(preferences);
        if (people != null) {
            people.removeIf(new Predicate<Person>() {
                @Override
                public boolean test(Person person) {
                    return person.getEmail().equalsIgnoreCase(email);
                }
            });

            savePeople(people, preferences);
        }
    }

    public static void saveExistingPersonToPeople(Person person, SharedPreferences preferences) {
        List<Person> people = getPeople(preferences);
        if (people != null) {
            removePersonWithEmail(person.getEmail(), preferences);
            addToPeople(person, preferences);
        }
    }

    public static void addToPeople(Person person, SharedPreferences preferences) {
        List<Person> people = getPeople(preferences);
        if (people != null) {
            people.add(person);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("People", gson.toJson(people)); //update key if necessary
            editor.commit();
        }
    }

    public static int calculateCalorieRequirement(SharedPreferences preferences) {
        Person currentPerson = getCurrentPerson(preferences);

        int surplus = 10;
        if (currentPerson.getGender().equalsIgnoreCase("Female")) {
            surplus = -120;
        }

        double bmr = 10 * currentPerson.getWeight() + 6.25 * currentPerson.getHeight() + surplus;
        switch (currentPerson.getGoal().getCode()) {
            case "FatLoss":
                bmr = bmr - (bmr * 0.3);
                break;
            case "LeanGains":
                bmr = bmr - (bmr * 0.2);
                break;
            case "BuildMuscle":
                bmr = bmr + (bmr * 0.2);
                break;
            default:
                break;
        }

        return (int)bmr;
    }
}
